import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.font.NumericShaper;
import java.text.NumberFormat;

public class LeggTilReservasjonController {
    @FXML
    TextField textFraTid;
    @FXML
    TextField textTilTid;
    @FXML
    TextField textAntallPersoner;
    @FXML
    TextField textNavn;
    @FXML
    TextField textTlf;

    private Stage dialogStage;
    private Senter senter;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleOk() {
        if(isInputValid()) {
            Test.ntnu.reserverRom(
                    new Tidspunkt(Long.parseLong(textFraTid.getText())),
                    new Tidspunkt(Long.parseLong(textTilTid.getText())),
                    Integer.parseInt(textAntallPersoner.getText()),
                    textNavn.getText(),
                    textTlf.getText()
            );
            okClicked = true;
            System.out.println("YAY");
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (textFraTid.getText() == null || textFraTid.getText().length() == 0) {
            errorMessage += "Mangler tid fra!\n";
        } else {
            try {
                Long.parseLong(textFraTid.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Fra tid er ikke gyldig tall!\n";
            }
        }
        if (textTilTid.getText() == null || textTilTid.getText().length() == 0) {
            errorMessage += "Mangler tid til!\n";
        } else {
            try {
                Long.parseLong(textTilTid.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Til tid er ikke gyldig tall!\n";
            }
        }
        if (textAntallPersoner.getText() == null || textAntallPersoner.getText().length() == 0) {
            errorMessage += "Antall personer mangler!\n";
        } else {
            try {
                Integer.parseInt(textAntallPersoner.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Antall personer er ikke gyldig tall!\n";
            }
        }
        if (textNavn.getText() == null || textNavn.getText().length() == 0) {
            errorMessage += "Mangler navn!\n";
        }
        if (textTlf.getText() == null || textTlf.getText().length() == 0) {
            errorMessage += "Tlf mangler!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ugyldige felt");
            alert.setHeaderText("Fiks feltene som ikke stemmer");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

}
