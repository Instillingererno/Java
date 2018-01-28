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

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static javax.swing.JOptionPane.showMessageDialog;

public class Klient extends Application {

    public static Stage primaryStage;

    public static ArrayList<Tribune> tribune = new ArrayList<>();

    public static void main(String[] args) {
        lesTribune("tribunefil.ser");
        launch(args);
        lagreTribune("tribunefil.ser", tribune);
        Collections.sort(tribune, Comparator.comparingInt(Tribune::finnInntekt).reversed());
        showMessageDialog(null, tribune.toString());
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



    // LAGRE OG HENTE DATA

    private static boolean lagreTribune(String navn, ArrayList<Tribune> tribune) {
        try {
            FileOutputStream file = new FileOutputStream(navn);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(tribune);
            out.close();
            file.close();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean lesTribune(String navn) {
        try {
            FileInputStream file = new FileInputStream(navn);
            ObjectInputStream in = new ObjectInputStream(file);
            tribune = (ArrayList<Tribune>) in.readObject();
            in.close();
            file.close();
            return true;
        } catch (Exception e) {
            tribune.add(new Staa("Staaplass1", 200, 100));
            tribune.add(new Staa("Staaplass2", 100, 50));
            tribune.add(new Sitte("Sitteplass1", 100, 150, 15, 10));
            tribune.add(new VIP("VIP1", 100, 300, 10, 10));
            e.printStackTrace();
        }
        return false;
    }

}