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
    private Label lblPassConfirm;
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

}
