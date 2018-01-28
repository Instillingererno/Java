import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SeBillettController {
    private Stage dialogStage;
    private Billett billett;


    @FXML
    Label tribuneNavn;
    @FXML
    Label pris;
    @FXML
    Label rad;
    @FXML
    Label plass;


    public void setDialogStage(Stage dialogStage, Billett billett) {
        this.dialogStage = dialogStage;
        this.billett = billett;

        tribuneNavn.setText(billett.getTribune());
        pris.setText(Integer.toString(billett.getPris()));

        if(billett instanceof SitteplassBillett) {
            rad.setText(Integer.toString(((SitteplassBillett) billett).getRad()));
            plass.setText(Integer.toString(((SitteplassBillett) billett).getPlass()));
        }
    }
    @FXML
    private void handleCancel() {
        this.dialogStage.close();
    }
}
