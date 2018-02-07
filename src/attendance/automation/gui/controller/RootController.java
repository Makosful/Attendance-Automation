package attendance.automation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class RootController implements Initializable
{

    private Stage primaryStage;

    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    public void setStage(Stage stage)
    {
        this.primaryStage = stage;
    }

}
