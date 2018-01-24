import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class BillettkjopController {
    private Stage dialogStage;
    private String tribune;

    @FXML
    ToggleButton toggleBillett;
    @FXML
    TextField inputBillett;

    public void setDialogStage(Stage dialogStage, String input) {
        this.dialogStage = dialogStage;
        this.tribune = input;
    }

    @FXML
    private void handleOk() {
        for(Tribune i : Klient.tribune) {
            if(i.getTribunenavn().equals(tribune)) {
                if(toggleBillett.isSelected()) {
                    i.kjopBillett(Integer.parseInt(inputBillett.getText()));
                } else {
                    i.kjopBillett(inputBillett.getText().split(","));
                }
            }
        }
        dialogStage.close();
    }
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
