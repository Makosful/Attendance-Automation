/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Hussain
 */
public class TeacherStudentAttendanceChangeRequestController implements Initializable 
{

    @FXML
    private GridPane lblDate;
    @FXML
    private Label lblStudentName;
    @FXML
    private Label lblClass;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    } 
    
    public void getAllMessages()
    {
        
    }
    
}
