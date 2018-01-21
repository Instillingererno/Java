import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Client extends Application implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    @FXML TextField nr1;
    @FXML TextField nr2;
    @FXML TextField nr3;
    @FXML TextField nr4;
    @FXML TextField nr5;
    @FXML TextField nr6;
    @FXML TextField goal;
    @FXML TextFlow text;
    @FXML TextArea textArea;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
        Scene scene = new Scene(root, 600, 510);

        primaryStage.setTitle("Countdown client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void handle(ActionEvent event) {
        double[] tabell = new double[] {
            Double.parseDouble(nr1.getText()),
            Double.parseDouble(nr2.getText()),
            Double.parseDouble(nr3.getText()),
            Double.parseDouble(nr4.getText()),
            Double.parseDouble(nr5.getText()),
            Double.parseDouble(nr6.getText())
        };
        NumberGame ting = new NumberGame(tabell);
        Svar svar = ting.answer(Integer.parseInt(goal.getText()));
        textArea.setText(svar.getMethod());
    }
    public void handle2(ActionEvent event) {
        nr1.clear();
        nr2.clear();
        nr3.clear();
        nr4.clear();
        nr5.clear();
        nr6.clear();
        goal.clear();
        textArea.clear();
        nr1.requestFocus();
    }

    private LetterGame letterGame = new LetterGame();

    @FXML TextArea letterTextArea;
    @FXML TextField nr11;
    @FXML TextField nr21;
    @FXML TextField nr31;
    @FXML TextField nr41;
    @FXML TextField nr51;
    @FXML TextField nr61;
    @FXML TextField nr71;
    @FXML TextField nr81;
    @FXML TextField nr91;
    @FXML TextField letterWords;
    public void findWord(ActionEvent event) {
        String output = nr11.getText() +
                nr21.getText() +
                nr31.getText() +
                nr41.getText() +
                nr51.getText() +
                nr61.getText() +
                nr71.getText() +
                nr81.getText() +
                nr91.getText();
        String answer = letterGame.answer(output, Integer.parseInt(letterWords.getText()));
        letterTextArea.setText(answer);
    }
    public void letterRefresh(ActionEvent event) {
        nr11.clear();
        nr21.clear();
        nr31.clear();
        nr41.clear();
        nr51.clear();
        nr61.clear();
        nr71.clear();
        nr81.clear();
        nr91.clear();
        letterTextArea.clear();
        nr11.requestFocus();
    }
}
