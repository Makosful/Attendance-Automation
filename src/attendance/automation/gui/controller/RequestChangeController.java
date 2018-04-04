/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.User;
import attendance.automation.gui.model.Model;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author B
 */
public class RequestChangeController implements Initializable {

    @FXML
    private TextField txtFieldMessage;
    @FXML
    private ListView<String> listViewAllClasses;
    @FXML
    private ListView<?> listViewSelectedClasses;
    @FXML
    private JFXDatePicker datePicker;
    
    private ObservableList<String> classes;

    Model model;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = Model.getInstance();
        User user = model.getUser();
        classes = FXCollections.observableArrayList();
        classes.addAll(user.getClasses());
        listViewAllClasses.setItems(classes);

    }    
    
}
