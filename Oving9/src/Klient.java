import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.nashorn.api.tree.Tree;

import java.io.IOException;
import java.util.ArrayList;

public class Klient extends Application {

    private static Stage primaryStage;

    public static ArrayList<Tribune> tribune = new ArrayList<>();

    public static void main(String[] args) {
        tribune.add(new Staa("Ståtribune 1", 100, 100));
        tribune.add(new Staa("Ståtribune 2", 200, 50));
        tribune.add(new Sitte("Sittetribune 1", 0, 150, 10, 20));
        tribune.add(new VIP("Viptribune 1", 0, 200, 5, 5));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Tribune ting");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //FXML elementer
    //Venste
    @FXML TreeTableView<String> tribunerView;
    @FXML TreeTableColumn<String, String> tribuner;
    //Høyre
    @FXML TextField tribuneNavn;
    @FXML TextField kapasitet;
    @FXML TextField pris;
    @FXML TextField solgteBilletter;
    @FXML TextField inntekt;


    @FXML private void initialize() {
        //Venstre
        tribuner.setCellValueFactory((TreeTableColumn.CellDataFeatures<String,String> p) ->
            new ReadOnlyStringWrapper(p.getValue().getValue()));
        TreeItem<String> root = new TreeItem<>("root");
        root.getChildren().addAll(getChildren());
        tribunerView.setRoot(root);
        tribunerView.setShowRoot(false);

        tribunerView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                    oppdaterTribune(newValue));

    }

    public static ObservableList<TreeItem<String>> getChildren() {
        ObservableList<TreeItem<String>> output = FXCollections.observableArrayList();

        ObservableList<TreeItem<String>> staa = FXCollections.observableArrayList();
        ObservableList<TreeItem<String>> sitte = FXCollections.observableArrayList();
        ObservableList<TreeItem<String>> vip = FXCollections.observableArrayList();
        //lag root
        final TreeItem<String> staaItem = new TreeItem<>("Stå tribuner");
        staaItem.setExpanded(true);
        final TreeItem<String> sitteItem = new TreeItem<>("Sitte tribuner");
        sitteItem.setExpanded(true);
        final TreeItem<String> vipItem = new TreeItem<>("VIP tribuner");
        vipItem.setExpanded(true);

        for(Tribune i : tribune) if(i instanceof VIP) {
            vip.add(new TreeItem<>(i.getTribunenavn()));
        }
        for(Tribune i : tribune) if(i instanceof Staa) {
            staa.add(new TreeItem<>(i.getTribunenavn()));
        }
        for(Tribune i : tribune) if(i instanceof Sitte && !(i instanceof VIP)) {
            sitte.add(new TreeItem<>(i.getTribunenavn()));
        }


        staaItem.getChildren().setAll(staa);
        sitteItem.getChildren().setAll(sitte);
        vipItem.getChildren().setAll(vip);

        output.add(staaItem);
        output.add(sitteItem);
        output.add(vipItem);

        return output;
    }
    TreeItem currentItem;
    private void oppdaterTribune(TreeItem item) {
        this.currentItem = item;
        for(Tribune i : tribune)if(i != null) if(i.getTribunenavn().equals(item.getValue())) {
            tribuneNavn.setText(i.getTribunenavn());
            kapasitet.setText(Integer.toString(i.getKapasitet()));
            pris.setText(Integer.toString(i.getPris()));
            solgteBilletter.setText(Integer.toString(i.finnAntallSolgteBilletter()));
            inntekt.setText(Integer.toString(i.finnInntekt()));

        }
    }


    //BILLETT KJØP --------------------------------------------------
    public void billettKjop() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Billettkjop.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Kjøp billetter");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            BillettkjopController controller = loader.getController();
            controller.setDialogStage(dialogStage, tribuneNavn.getText());

            dialogStage.showAndWait();
            oppdaterTribune(currentItem);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }



}