/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.gui.controller;

import attendance.automation.Main;
import attendance.automation.bll.validation.IValidation;
import attendance.automation.bll.validation.ValidationFactory;
import attendance.automation.gui.model.Model;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author B
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField txtFieldEmail;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label lblEmailStatus;
    
    private Model model;
    private Stage currentStage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            model = Model.getInstance();
            
        try {    
            IValidation emailValidation = ValidationFactory.createValidation(ValidationFactory.validationType.email);
            addTxtFieldValidationListener(txtFieldEmail, lblEmailStatus, emailValidation);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void handleSendNewPassword(ActionEvent event) {
        try {
            model.forgottenPassEmail(txtFieldEmail.getText());
            lblEmailStatus.setText("An email is now sent to you containing a "
                                 + "new temporary password");
        } catch (MessagingException ex) {
            lblEmailStatus.setText("An error occurred while sending the email");
        }
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        changeStageLoginView();
    }
    
    /**
     * Changes the currentStage to the login view screen
     */
    public void changeStageLoginView()
    {
        try
        {
            this.currentStage = (Stage) anchorPane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/LoginScreen.fxml"));
            Parent parent = loader.load();
            this.currentStage.setScene(new Scene(parent));
            this.currentStage.show();
            this.centerStage();
        }
        catch (IOException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Centers the window on the screen
     *
     * @param currentStage
     */
    private void centerStage()
    {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        currentStage.setX((primScreenBounds.getWidth() - currentStage.getWidth()) / 2);
        currentStage.setY((primScreenBounds.getHeight() - currentStage.getHeight()) / 2);
    }

    
    /**
     * 
     * @param txtField
     * @param label
     * @param validation 
     */
    private void addTxtFieldValidationListener(TextField txtField, Label label, IValidation validation) {
        
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
                validation.inputValidation(newValue);
                label.setText(validation.getValidationMessage());

        });
    }
}
