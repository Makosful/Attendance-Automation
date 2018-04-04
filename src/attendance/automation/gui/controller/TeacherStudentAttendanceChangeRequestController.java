/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.NotificationMessage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

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
    @FXML
    private ListView<NotificationMessage> messageView;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    } 

    @FXML
    private void loadMessages(ActionEvent event) 
    {
    }
    
    public void listViewSetup() 
    {
        messageView.setCellFactory(new Callback<ListView<NotificationMessage>, ListCell<NotificationMessage>>() 
        {

            @Override
            public ListCell<NotificationMessage> call(ListView<NotificationMessage> p) {

                ListCell<NotificationMessage> cell = new ListCell<NotificationMessage>() {

                    @Override
                    protected void updateItem(NotificationMessage t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getMessage());
                        }
                    }
                };
                return cell;
            }
        });
    }
    
}
