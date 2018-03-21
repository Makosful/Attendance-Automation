package attendance.automation.gui.controller;

import attendance.automation.Main;
import attendance.automation.bll.BLLException;
import attendance.automation.bll.Encryption.Encryption;
import attendance.automation.dal.EncryptionDAL.LogInEncryption;
import attendance.automation.gui.model.Model;
import attendance.automation.be.User;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class LoginScreenController implements Initializable
{

    //<editor-fold defaultstate="collapsed" desc="FXML Variables">
    @FXML
    private Button btnSignup;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    //</editor-fold>

    // Objects
    private Model model;
    @FXML
    private Label lblInfoMessage;

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
    }

    private void changeStage(String file) throws IOException
    {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
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
    private void handleLogin(ActionEvent event)
    {
        try
        {
            String text = txtUserName.getText().toLowerCase();
            if (text.startsWith("t"))
                changeStage("TeacherScreen");
            else if (text.startsWith("s"))
                changeStage("StudentScreen");
        }
        catch (IOException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            System.out.println(ex.getStackTrace());
        }
        try 
        {
            User user = model.userLogIn(txtUserName.getText(), Encryption.passwordEncryption(txtPassword.getText()));          
        } 
        catch (BLLException ex) 
        {
            lblInfoMessage.setText("Wrong Password");
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event)
    {
        try
        {
            changeStage("SignUp");
        }
        catch (IOException ex)
        {
            System.out.println(ex.getLocalizedMessage());
            System.out.println(ex.getStackTrace());
        }
    }

}
