
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Test extends Application implements EventHandler<ActionEvent> {
    public static Senter ntnu = new Senter();
    Stage primaryStage;

    public static void main(String[] args) {
        System.out.println("Lager nytt senter");
        System.out.println("Registrerer nytt rom: romnr 101, kap 10");
        ntnu.regNyttRom(101,10);
        System.out.println("Registrer ny reservasjon");
        ntnu.reserverRom(new Tidspunkt(201801121200L), new Tidspunkt(201801121400L), 10, "Ola Nordmann", "123456");
        System.out.println("Registrerer nytt rom: romnr 102, kap 20");
        ntnu.regNyttRom(102,20);
        System.out.println("Registrerer nytt rom: romnr 103, kap 30");
        ntnu.regNyttRom(103,30);
        System.out.println("Registrerer nytt rom: romnr 104, kap 40");
        ntnu.regNyttRom(104,40);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        //PropertyValueFactory test = new PropertyValueFactory<>("romNr");
        //System.out.println(test.call(new TableColumn.CellDataFeatures(RomTable, Rom, ntnu.rom.get(0).getRomNr())));

        Scene scene = new Scene(root, 600, 400);



        primaryStage.setTitle("Hello there");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println("Hello World");
    }

    private ObservableList<Rom> getRom() {
        ObservableList<Rom> rom = FXCollections.observableArrayList();
        for(Rom i : ntnu.getRom()) if(i != null) rom.add(i);
        return rom;
    }

    @FXML TableView<Rom> RomTable;
    @FXML TableColumn<Rom, Integer> Rom;
    @FXML TableColumn<Rom, Integer> Kapasitet;

    @FXML TableView<MellomSteg> reservasjoner;
    @FXML TableColumn<MellomSteg, String> tidFraCol;
    @FXML TableColumn<MellomSteg, String> tidTilCol;
    @FXML TableColumn<MellomSteg, String> kundeCol;

    private ObservableList<MellomSteg> output;
    @FXML private void initialize() {
        //Venstre
        Rom.setCellValueFactory(new PropertyValueFactory<>("romNr"));
        Kapasitet.setCellValueFactory(new PropertyValueFactory<>("kapasitet"));
        RomTable.setItems(getRom());
        //Høyre
        tidFraCol.setCellValueFactory(new PropertyValueFactory<>("tidFra"));
        tidTilCol.setCellValueFactory(new PropertyValueFactory<>("tidTil"));
        kundeCol.setCellValueFactory(new PropertyValueFactory<>("kunde"));

        showReservasjoner(ntnu.getRomFraIndeks(0));

        //initilize event listener
        RomTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showReservasjoner(newValue);
                });
    }

    private void showReservasjoner(Rom rom) {
        output = FXCollections.observableArrayList();
        if(rom != null) {
            for(Reservasjon i : rom.getReservasjoner()) if(i != null) output.add(new MellomSteg(i));
        }
        reservasjoner.setItems(output);
        reservasjoner.refresh();
    }

    public void showLeggTilReservasjon() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LeggTilReservasjon.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Legg til person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            LeggTilReservasjonController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
