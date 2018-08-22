package sample;

import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.File;

class FileChooserUtil {
    private FileChooser chooser = null;
    static FileChooserUtil init(String title) {
        FileChooserUtil util = new FileChooserUtil();
        util.chooser = new FileChooser();
        util.chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text file", "*.txt"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        util.chooser.setTitle(title);
        return util;
    }
    File openChooseDialog() {
        return this.chooser.showOpenDialog(new Popup());
    }
    File openSaveDialog() {
        return this.chooser.showSaveDialog(new Popup());
    }
}
