package attendance.automation.gui.controller;

import attendance.automation.be.PasswordValidation;
import attendance.automation.bll.BLLException;
import attendance.automation.bll.validation.IValidation;
import attendance.automation.bll.validation.ValidationFactory;
import attendance.automation.gui.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class ChangePasswordController implements Initializable
{

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnChangePass;
    @FXML
    private PasswordField txtPassCon;
    @FXML
    private PasswordField txtPassCur;
    @FXML
    private PasswordField txtPassNew;
    private IValidation passwordValidation;
    private Model model;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try {
            passwordValidation = ValidationFactory.createValidation(ValidationFactory.validationType.password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage()); 
        }
        
            model = Model.getInstance();
            
            txtPassNew.textProperty().addListener((observable, oldValue, newValue) ->
            {
               
                
                if (!passwordValidation.inputValidation(newValue))
                {
                    txtPassNew.setStyle("-fx-border-color: red; -fx-border-width:2px;");
                }
                else
                {
                    txtPassNew.setStyle("-fx-border-color: green; -fx-border-width:2px;");
                }
            });
            
            txtPassCon.textProperty().addListener((observable, oldValue, newValue) ->
            {
                if (txtPassNew.getText().equals(newValue))
                {
                    txtPassCon.setStyle("-fx-border-color: green; -fx-border-width:2px;");
                }
                else
                {
                    txtPassCon.setStyle("-fx-border-color: red; -fx-border-width:2px;");
                }
            });

    }

    @FXML
    private void handleCancel(ActionEvent event)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleChangePass(ActionEvent event)
    {
        if (passwordValidation.inputValidation(txtPassNew.getText())
        || txtPassNew.getText().equals(txtPassCon.getText()))
        {
            try
            {
                model.changePassword(txtPassNew.getText(), txtPassCur.getText());
                handleCancel(event);
            }
            catch (BLLException ex)
            {
                System.out.println("Try again, Hacker");
            }
        }
    }

}
