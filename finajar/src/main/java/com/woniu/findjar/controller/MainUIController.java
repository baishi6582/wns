package com.woniu.findjar.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.woniu.findjar.service.FindJar;
import com.woniu.findjar.vo.ClassPath;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainUIController  implements Initializable {

    @FXML
    private TextField dirText;

    @FXML
    private TextField classText;

    @FXML
    private TableView result;

    @FXML
    private TableColumn path;

    @FXML
    private TableColumn no;

    @FXML
    public void searchJar() {
        String dir = dirText.getText();
        if (dir == null || dir.length() == 0) {
            showWarnAlert("路径不能为空");
            return;
        }
        String className = classText.getText();
        if (className == null || className.length() == 0) {
            showWarnAlert("类名不能为空");
            return;
        }
        ObservableList<ClassPath> data = FXCollections.observableArrayList();
        List<ClassPath> list = new ArrayList<ClassPath>();
        FindJar.FindClassInLocalSystem(dir, className, list);
        data.addAll(list);
        path.setCellValueFactory(new PropertyValueFactory<ClassPath, String>("path"));
        no.setCellValueFactory(new PropertyValueFactory<ClassPath, Integer>("no"));
        result.setItems(data);
    }

    public void initialize(URL location, ResourceBundle resources) {

    }

    public static void showWarnAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.show();
    }
}
