import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BillettkjopController {
    private Stage dialogStage;
    private String tribune;

    @FXML
    TextField inputBillett;


    public void setDialogStage(Stage dialogStage, String input) {
        this.dialogStage = dialogStage;
        this.tribune = input;
    }

    @FXML
    private void handleOk() throws IOException {
        Billett[] billett = null;
        boolean klar = false;
        for(Tribune i : Klient.tribune) {
            if(i.getTribunenavn().equals(tribune)) {
                try {
                    int antallBilletter = Integer.parseInt(inputBillett.getText());
                    billett = i.kjopBillett(antallBilletter);
                    klar = true;
                } catch (NumberFormatException e) {
                    String[] navn = inputBillett.getText().split(",");
                    if(navn.length > 0 && navn[0] != null) {
                        billett = i.kjopBillett(navn);
                    }
                    klar = true;
                }
            }
        }
        if(klar) {
            for(int i = 0; i < billett.length; i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("SeBillett.fxml"));
                AnchorPane page = loader.load();
                Stage billettStage = new Stage();
                billettStage.setTitle("Billett");
                billettStage.initModality(Modality.WINDOW_MODAL);
                billettStage.initOwner(Klient.primaryStage);
                Scene scene = new Scene(page);
                billettStage.setScene(scene);

                SeBillettController controller = loader.getController();
                controller.setDialogStage(billettStage, billett[i]);
                billettStage.show();
            }
        }
        dialogStage.close();
    }
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
