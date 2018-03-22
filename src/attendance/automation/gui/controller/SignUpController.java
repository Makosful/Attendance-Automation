package attendance.automation.gui.controller;

import attendance.automation.Main;
import attendance.automation.be.PasswordValidation;
import attendance.automation.gui.model.Model;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
        System.out.println("You've been signed up");
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
        txtPass.textProperty().addListener((observable, oldValue, newValue) ->
        {
            PasswordValidation pv = model.checkPasswordstrength(newValue.trim());
            validPass = pv.isValid();
            if (validPass)
            {
                lblPassError.setVisible(!validPass);
            }
            else
            {
                lblPassError.setText(pv.getMessage());
                lblPassError.setVisible(!validPass);
            }
        });

        txtPassConfirm.textProperty().addListener((observable,
                                                   oldValue, newValue) ->
        {
            matchingPass = false;
            if (txtPass.getText().equals(txtPassConfirm.getText()))
            {
                matchingPass = true;
                lblPassConfError.setVisible(!matchingPass);
            }
            else
            {
                matchingPass = false;
                lblPassConfError.setText("The two passwords are not identical");
                lblPassConfError.setVisible(!matchingPass);
            }
        });
        
        emailCheck();
        usernameCheck();
        
    }
    /**
     * Checks if the email is valid in database
     * Checks if emailConfirm textfield contains same as above, txtemail.
     */
    public void emailCheck()
    {
        txtEmail.focusedProperty().addListener(new ChangeListener<Boolean>() 
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(!newValue)
                {
                    if(model.validEmail(txtEmail.getText()))
                    {
                        System.out.println("Email is valid");
                    }
                    else
                    {
                        System.out.println("Email not valid");
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
                        System.out.println("Email is same");
                    } 
                    else
                    {
                        System.out.println("Email not same");
                    }
                }
            }
        });

    }
    /**
     * Checks if the username is valid.
     */
    public void usernameCheck()
    {
        txtUsername.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) 
            {
                if(!newValue)
                {
                    if (model.validUsername(txtUsername.getText()))
                    {
                        System.out.println("valid");
                    }
                    else
                    {
                        System.out.println("username is not valid");
                    }
                }
            }
        });
    }

}
