/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.NotificationMessage;
import attendance.automation.be.User;
import attendance.automation.dal.TeacherDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        messageViewSetup();
        // TODO
    } 

    @FXML
    private void loadMessages(ActionEvent event) throws SQLException 
    {

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
                lblClass.setText(message.getClassID() + "");
                lblStudentName.setText(message.getStudentID() + "");
                lblDate.setText("04-04-2018 - midlertidig");

            }
        });
    }
    
    public void setUser(User user)
    {
        this.user = user;
    }
    
}
