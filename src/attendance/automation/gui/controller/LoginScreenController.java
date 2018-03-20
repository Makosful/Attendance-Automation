package attendance.automation.gui.controller;

import attendance.automation.Main;
import attendance.automation.gui.model.Model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

    private Model model;

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private CheckBox checkBoxRememberMe;
    
    Stage currentStage;



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
        checkRememberMe();
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException
    {
        String text = txtUserName.getText().toLowerCase();
        if (text.startsWith("t"))
            changeStageTeacherView();
        else if (text.startsWith("s"))
            changeStageStudentView();
        
        try {
            model.storeLocalLogin(txtUserName.getText(), txtPassword.getText(), checkBoxRememberMe.isSelected());
        } catch (IOException ex) {
            System.out.println("Could not store the login credentials");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Could not store the login credentials");
        }
    }

    private void changeStageTeacherView() throws IOException
    {
        currentStage = (Stage) btnLogin.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/TeacherScreen.fxml"));
        Parent parent = loader.load();
        currentStage.setScene(new Scene(parent));
        centerStage();
    }

    private void changeStageStudentView() throws IOException
    {
        currentStage = (Stage) btnLogin.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/StudentScreen.fxml"));
        Parent parent = loader.load();
        currentStage.setScene(new Scene(parent));
        centerStage();
    }

    /**
     * Centers the window on the screen
     *
     * @param stage
     */
    private void centerStage()
    {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        currentStage.setX((primScreenBounds.getWidth() - currentStage.getWidth()) / 2);
        currentStage.setY((primScreenBounds.getHeight() - currentStage.getHeight()) / 2);
    }


    private void checkRememberMe() {
        try {
            String[] login = model.getLogInInfo();
            if(login[0] != null)
            {
                txtUserName.setText(login[0]);
                txtPassword.setText(login[1]);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Could not retrieve the login credentials");
        }
        
    }

    @FXML
    private void handleForgottenPassword(ActionEvent event) {
    }


}
