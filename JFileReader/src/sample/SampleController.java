package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SampleController {

    /*
    TODO: Optimizations
    TODO: Refactor!
     */

    private int pageSize = 40;
    private int fontSize = 12;

    private String currentPage = "";

    private LargeFileReader reader = null;

    @FXML
    TextArea textarea;
    @FXML
    Text fontsize;
    @FXML
    Text pagenumber;
    @FXML
    TextArea linenumbers;
    @FXML
    SplitPane mainsplitpane;

    @FXML
    public void initialize() {
        textarea.scrollTopProperty().bindBidirectional(linenumbers.scrollTopProperty());
    }

    public void loadFile() {
        File selectedFile = FileChooserUtil.init("Choose file to read").openChooseDialog();
        if(selectedFile != null) {
            try {
                reader = new LargeFileReader(pageSize, selectedFile.getAbsolutePath());
                nextPage();
            } catch(IOException io) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("File not readable");
                alert.showAndWait();
            }
        }
    }

    public void nextPage() {
        if(reader==null) {
            loadFile();
            return;
        }
        if(reader.isEndOfFile()) return;
        currentPage = reader.readNextPage();
        updateTextArea(currentPage);
    }

    private void updateTextArea(String string) {
        textarea.setText(string);
        updatePageNumber();
        updateLineNumbers();
    }

    public void setPageSize() {
        if(reader != null) {
            showError("Changing the pagesize when a file has been opened will not have any effect before the file is re-loaded");
        }
        userInput("Set page size", "Please input a new pagesize", a -> {
            try {
                Integer number = Integer.valueOf(a.orElse("-404"));
                if(number > 0) pageSize = number;
            } catch (NumberFormatException nfe) {
                showError("Not a number");
            }
        });

    }

    public void incrementFontSize() {
        fontSize += 2;
        updateFontSize();
    }

    public void decreaseFontSize() {
        if(fontSize > 2) fontSize -= 2;
        updateFontSize();
    }

    private void updateFontSize() {
        textarea.setStyle(
                "-fx-font-size: " + fontSize + "px;"
        );
        linenumbers.setStyle(
                "-fx-font-size: " + fontSize + "px;"
        );
        fontsize.setText(String.valueOf(fontSize));
    }

    private void updatePageNumber() {
        pagenumber.setText(String.valueOf(reader.getCurPage()));
    }

    public void savePage() {
        if(reader==null) {
            loadFile();
            return;
        }
        File file = FileChooserUtil.init("Save file").openSaveDialog();

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(currentPage);
            fileWriter.close();
        } catch (IOException ex) {
            showError("Something went wrong");
        }
    }

    public void jumpToNextInstance() {
        if(reader==null) loadFile();
        Optional<String> query = userInput("Search for next", "Please input something to search for");
        if(query.isPresent()) {
            String string = query.get();
            Task task = new Task<Void>() {
                public Void call() {
                    Task updateSearchStatus = new Task<Void>() {
                        public Void call() {
                            return null;
                        }
                    };

                    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
                    scheduler.scheduleAtFixedRate(updateSearchStatus, 100, 200, TimeUnit.MILLISECONDS);

                    String page = currentPage;
                    boolean found = false;
                    while(!reader.isEndOfFile()) {
                        if(page.contains(string)) {
                            found = true;
                            break;
                        }
                        page = reader.readNextPage();
                    }
                    scheduler.shutdown();
                    currentPage = page;
                    updateTextArea(currentPage);
                    if(found) {
                        int index = currentPage.indexOf(string);
                        textarea.selectRange(index, index + string.length());
                    }
                    else showError("Query not found");
                    return null;
                }
            };
            new Thread(task).start();
        }

    }

    public void jumpPages() {
        if(reader == null) loadFile();
        userInput("Jump pages", "Please input number of pages to jump", a -> {
            try {
                Integer temp = Integer.valueOf(a.orElseThrow(NumberFormatException::new));
                if(temp > 0) {
                    Platform.runLater(() -> {
                        for(int i = 0; i < temp; i++) nextPage();
                    });
                }
            } catch(NumberFormatException nfe) {
                showError("Not a valid input");
            }
        });
    }

    private void updateLineNumbers() {
        int temp = (reader.getCurPage()-1) * pageSize+1;
        String lineNumbers = IntStream.range(temp, temp+pageSize).mapToObj(String::valueOf).collect(Collectors.joining("\n"));
        linenumbers.setText(lineNumbers + "\n");
    }


    private void showError(String contextText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(contextText);
        alert.showAndWait();
    }


    private void userInput(String title, String contextText, Consumer<Optional<String>> consumer) {
        Optional<String> temp = userInput(title, contextText);
        if(temp.isPresent()) {
            try {
                consumer.accept(temp);
            } catch (Exception e) {
                showError("Not a valid input");
            }
        }
    }

    private Optional<String> userInput(String title, String contextText) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setContentText(contextText);
        return dialog.showAndWait();
    }
}
