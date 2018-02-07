package attendance.automation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class LoginScreenController
        extends Controller
        implements Initializable
{

    private SubScene innerScene;

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
        // TODO
    }

    @Override
    public double getHeight()
    {
        return root.getPrefHeight();
    }

    @Override
    public double getWidth()
    {
        return root.getPrefWidth();
    }

    @Override
    public void setInnerScene(SubScene scene)
    {
        this.innerScene = scene;
    }

    @FXML
    private void handleLogin(ActionEvent event)
    {
    }

}
