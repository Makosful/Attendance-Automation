/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.NotificationMessage;
import attendance.automation.be.User;
import attendance.automation.bll.BLLException;
import attendance.automation.dal.TeacherDAO;
import attendance.automation.gui.model.Model;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Hussain
 */
public class TeacherStudentAttendanceChangeRequestController implements Initializable 
{

    @FXML
    private Label lblDate;
    @FXML
    private Label lblStudentName;
    @FXML
    private Label lblClass;
    @FXML
    private ListView<NotificationMessage> messageView;
    
    private User user;
    
    private Model model;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        messageViewSetup();
        model = Model.getInstance();
        // TODO
    } 

    @FXML
    private void loadMessages(ActionEvent event) throws SQLException 
    {
        messageView.getItems().clear();
        try 
        {
           for(NotificationMessage message : model.allNotifications())
           {             
               messageView.getItems().add(message);
           }
        } 
        catch (BLLException ex) 
        {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText(ex.getMessage());
           alert.show();
        }
    }
    
    public void messageViewSetup() 
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
        labelUpdate();
    }
   
    public void labelUpdate()
    {
        messageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                NotificationMessage message = messageView.getSelectionModel().getSelectedItem();
                if(message !=  null)
                {
                lblClass.setText(message.getClassID() + "");
                lblStudentName.setText(message.getStudentName());
                lblDate.setText("04-04-2018 - midlertidig");
                }

            }
        });
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
    
}
