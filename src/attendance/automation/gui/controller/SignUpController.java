package attendance.automation.gui.controller;

import attendance.automation.Main;
import attendance.automation.be.PasswordValidation;
import attendance.automation.bll.validation.IValidation;
import attendance.automation.bll.validation.ValidationFactory;
import attendance.automation.gui.model.Model;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class SignUpController implements Initializable
{

    //<editor-fold defaultstate="collapsed" desc="FXML Variables">
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSignUp;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblEmailConfirm;
    @FXML
    private Label lblFName;
    @FXML
    private Label lblLName;
    @FXML
    private Label lblPass;
    @FXML
    private Label lblPassConfError;
    @FXML
    private Label lblPassConfirm;
    @FXML
    private Label lblPassError;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtEmailConfirm;
    @FXML
    private TextField txtFName;
    @FXML
    private TextField txtLName;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField txtPassConfirm;
    @FXML
    private TextField txtUsername;
    //</editor-fold>

    private Model model;
    private boolean validPass;
    private boolean matchingPass;
    @FXML
    private Label lblUsernameValidation;
    @FXML
    private Label lblFNameValidation;
    @FXML
    private Label lblLNameValidation;
    @FXML
    private Label lblEmailValidation;
    @FXML
    private Label lblEmailConfirmValid;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = Model.getInstance();

        setupValidationListeners();
        
        
    }

    private void changeStage(String file) throws IOException
    {

        Stage stage = (Stage) btnSignUp.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/" + file + ".fxml"));
        Parent parent = loader.load();
        stage.setScene(new Scene(parent));
        centerStage(stage);
    }

    /**
     * Centers the window on the screen
     *
     * @param stage
     */
    private void centerStage(Stage stage)
    {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    @FXML
    private void handleCancel(ActionEvent event)
    {
        try
        {
            changeStage("LoginScreen");
        }
        catch (IOException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            System.out.println(ex.getStackTrace());
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event)
    {
        model.signUp(txtFName.getText(),
                     txtLName.getText(),
                     txtUsername.getText(),
                     txtEmail.getText(),
                     txtPass.getText());
        try
        {
            changeStage("LoginScreen");
        }
        catch (IOException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            System.out.println(ex.getStackTrace());
        }
    }

    private void setupValidationListeners()
    {
        try {
            
            passwordCheck();
            usernameCheck();
            emailCheck();
            
            IValidation passwordValidation = ValidationFactory.createValidation(ValidationFactory.validationType.password);
            
            IValidation firstNameValidation = ValidationFactory.createValidation(ValidationFactory.validationType.firstAndLastName);
            
            IValidation lastNameValidation = ValidationFactory.createValidation(ValidationFactory.validationType.firstAndLastName);

            isValid(txtPass, lblPassError, passwordValidation);
            
            isValid(txtFName, lblFNameValidation, firstNameValidation);
            
            isValid(txtLName, lblLNameValidation, lastNameValidation);
            
          
        } catch (Exception ex) {
           ex.getMessage();
        }
    }

    private void passwordCheck() 
    {
        txtPass.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) 
            {
                if(!newValue)
                {

                    if (txtPass.getText().equals(txtPassConfirm.getText()))
                    {
                        lblPassConfError.setText("");
                    }
                    else
                    {
                        lblPassConfError.setText("The two passwords are not identical");
                    }
                }
            }
        });
        
    }

   
    private boolean isValid(TextField txtField, Label label, IValidation validation) {
        
        txtField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) 
            {
                if(!newValue)
                {
                    System.out.println(validation.getValidationMessage());
                    if(!validation.inputValidation(txtField.getText()))
                    {
                        label.setText(validation.getValidationMessage());
                        
                    }
                    else
                    {
                       label.setText("");
                       
                    }
           
                }
            }
        });
        
        return label.getText().isEmpty();
    }
    
    /**
     * Checks if the email is valid in database
     * Checks if emailConfirm textfield contains same as above, txtemail.
     */
    public boolean emailCheck()
    {
        try {
            txtEmail.focusedProperty().addListener(new ChangeListener<Boolean>()
            {

                
                IValidation emailValidation = ValidationFactory.createValidation(ValidationFactory.validationType.email);
   
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
                {
                    if(!newValue)
                    {
                        if(!model.validEmail(txtEmail.getText()))
                        {
                            lblEmailValidation.setText("Email is not valid");
                        }
                        else if(!emailValidation.inputValidation(txtEmail.getText()))
                        {
                            lblEmailValidation.setText(emailValidation.getValidationMessage()); 
                        }
                        else
                        {
                            lblEmailValidation.setText("");
                        }
                    }
                }
            });
            
            txtEmailConfirm.focusedProperty().addListener(new ChangeListener<Boolean>() 
            {
                @Override 
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
                {
                    if (!newValue)
                    {
                        if (txtEmailConfirm.getText().equals(txtEmail.getText()))
                        {
                            lblEmailConfirmValid.setText("");
                        }
                        else
                        {
                            lblEmailConfirmValid.setText("Email not same");
                        }
                    }
                }
            });
            
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        if(lblEmailValidation.getText().isEmpty()
        && lblEmailConfirmValid.getText().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Checks if the username is valid.
     */
    public boolean usernameCheck()
    {
        try {
            IValidation usernameValidation = ValidationFactory.createValidation(ValidationFactory.validationType.username);
            
            txtUsername.focusedProperty().addListener(new ChangeListener<Boolean>() 
            {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
                {
                    if(!newValue)
                    {
                        if (model.validUsername(txtUsername.getText()))
                        {
                            lblUsernameValidation.setText("");
                        }
                        else if(!usernameValidation.inputValidation(txtUsername.getText()))
                        {
                            lblUsernameValidation.setText(usernameValidation.getValidationMessage()); 
                        }
                        else
                        {
                            lblUsernameValidation.setText("username is not valid");
                        }
                    }
                }
            });
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return lblUsernameValidation.getText().isEmpty();
    }
    
    

}
