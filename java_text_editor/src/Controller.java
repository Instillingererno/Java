import javafx.fxml.FXML;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;

import java.io.File;

public class Controller {
    @FXML
    TreeView<String> dirTreeView;

    @FXML
    public void initialize() {

    }


    public void displayTreeView(String root) {
        CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<>(root);
        dirTreeView.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
        File rootFile = new File(root);
        File[] fileList = rootFile.listFiles();
        if(fileList == null) return;
        for(File file : fileList) {
            createTree(file, rootItem);
        }
        dirTreeView.setRoot(rootItem);
    }





    public static void createTree(File file, CheckBoxTreeItem<String> parent) {
        if (file.isDirectory()) {
            CheckBoxTreeItem<String> treeItem = new CheckBoxTreeItem<>(file.getName());
            parent.getChildren().add(treeItem);
            for(File f : file.listFiles()) {
                createTree(f, treeItem);
            }
        } else {
            parent.getChildren().add(new CheckBoxTreeItem<>(file.getName()));
        }
    }
}
