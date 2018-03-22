package attendance.automation.gui.controller;

import attendance.automation.Main;
import attendance.automation.be.PasswordValidation;
import attendance.automation.gui.model.Model;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    //</editor-fold>

    private Model model;
    @FXML
    private TextField txtUsername;
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

    }

    /**
     * Checks if the email is valid in database
     * Checks if emailConfirm textfield contains same as above, txtemail.
     */
    public void emailCheck()
    {
        txtEmail.textProperty().addListener((observable, oldValue, newValue)
                ->
        {
            if (!model.validEmail(newValue))
            {
                System.out.println("Email is not valid");
            }
        });

        txtEmailConfirm.textProperty().addListener((observable, oldValue, newValue)
                ->
        {
            if (txtEmailConfirm.getText().equals(txtEmail.getText()))
            {
                System.out.println("OK");
            }
            else
            {
                System.out.println("Same email");
            }
        });
    }

}
