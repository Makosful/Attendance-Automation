package attendance.automation.gui.controller;

import attendance.automation.Main;
import attendance.automation.gui.model.Model;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

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

    @FXML
    private void handleLogin(ActionEvent event) throws IOException
    {
        String text = txtUserName.getText().toLowerCase();
        if (text.startsWith("t"))
            changeStageTeacherView();
        else if (text.startsWith("s"))
            changeStageStudentView();
    }
    
    
    
    private void changeStageTeacherView() throws IOException
    {
        Stage currentStage = (Stage) btnLogin.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/TeacherScreen.fxml"));
        Parent parent = loader.load();
        currentStage.setScene(new Scene(parent));  
    }
    
    private void changeStageStudentView() throws IOException
    {
        Stage currentStage = (Stage) btnLogin.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/StudentScreen.fxml"));
        Parent parent = loader.load();
        currentStage.setScene(new Scene(parent));  
    }
    
    

}
