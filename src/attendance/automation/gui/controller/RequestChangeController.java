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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private ListView<String> listViewSelectedClasses;
    @FXML
    private JFXDatePicker datePicker;
    
    private ObservableList<String> classes;
    private LocalDate date;
    Model model;
    private User user;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        model = Model.getInstance();
        user = model.getUser();
        
        classes = FXCollections.observableArrayList();
        classes.addAll(user.getClasses());
        
        listViewAllClasses.setItems(classes);
        
        addClassItem();
        removeClassItem();
        setDate();
       
    }    
    /**
     * Adda a class/subject to the listview, which will be used in the request 
     * for change in attendance status
     */
    private void addClassItem() {
        
        listViewAllClasses.setOnMouseClicked(event -> {
            int SelectedIndex = listViewAllClasses.getSelectionModel().getSelectedIndex();
            if(listViewAllClasses.getSelectionModel().isSelected(SelectedIndex)){
                String SelectedItem = listViewAllClasses.getSelectionModel().getSelectedItem();
                listViewSelectedClasses.getItems().add(SelectedItem);
            }
            
        });
    }

    /**
     * Remove a selected class
     */
    private void removeClassItem() {
        listViewSelectedClasses.setOnMouseClicked(event -> {
            int SelectedIndex = listViewSelectedClasses.getSelectionModel().getSelectedIndex();
            if(listViewSelectedClasses.getSelectionModel().isSelected(SelectedIndex)){
                
                listViewSelectedClasses.getItems().remove(SelectedIndex);
            }
            
        });
     
    }
    
    
    
    /**
     * Set todays date as default and eventhandling for when choosing a date
     */
    private void setDate() {

        datePicker.setValue(LocalDate.now());

        datePicker.setOnAction(event -> {
            date = datePicker.getValue();
        });
    }
     
        

    @FXML
    private void handleSaveRequest(ActionEvent event) {
        try {
            List<String> chosenCalsses = classes;
            model.requestAttendanceChange(user.getId(), chosenCalsses, txtFieldMessage.getText(), date);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    @FXML
    private void handleCancelRequest(ActionEvent event) {
         Stage stage = (Stage)txtFieldMessage.getScene().getWindow();
         stage.close();
    
    }



    
}
