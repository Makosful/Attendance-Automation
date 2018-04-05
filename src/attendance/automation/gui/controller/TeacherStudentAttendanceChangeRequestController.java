/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.be.NotificationMessage;
import attendance.automation.be.User;
import attendance.automation.bll.BLLException;
import attendance.automation.gui.model.Model;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    
    private ContextMenu cm;
    @FXML
    private AnchorPane anchorPane;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        messageViewSetup();
        model = Model.getInstance();
        setupContextMenu();
    } 

    @FXML
    private void loadMessages(ActionEvent event) throws SQLException 
    {
        loadMessages();
    }
    /**
     * Custom objects in a listview.
     */
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
        clickEvents();
    }
   
    public void clickEvents()
    {
        messageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) 
            {
                
                NotificationMessage message = messageView.getSelectionModel().getSelectedItem();
                cm.hide();
                if(message !=  null)
                {
                lblClass.setText(message.getClassName());
                lblStudentName.setText(message.getStudentName());
                lblDate.setText("04-04-2018 - midlertidig");
                }
                openContextMenu(event, message);
            }
        });
    }
    /**
     * Open contextmenu only if a message has been clicked.
     * @param e
     * @param message 
     */
    public void openContextMenu(MouseEvent e, NotificationMessage message)
    {
        if(e.getButton() == MouseButton.SECONDARY)
        {
            if(message != null)
            {
                cm.hide();
                cm.show(anchorPane, e.getScreenX(), e.getScreenY());
            }
        }
    }
    /**
     * Action events  and contextmenu setup.
     */
    public void setupContextMenu()
    {
        cm = new ContextMenu();
        MenuItem accept = new MenuItem("Accept");
        MenuItem decline = new MenuItem("Decline");
        
        accept.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                System.out.println("Attendance has been changed.");
            }
        });
        decline.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) 
            {
                System.out.println("Attendance hasn't been changed");
            }
        });
        cm.getItems().addAll(accept, decline);
    }
    /**
     * Sets the current user.
     * @param user 
     */
    public void setUser(User user)
    {
        this.user = user;
    }
    /**
     * Gets all the messages for the teacher.
     */
    public void loadMessages()
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
    
}
