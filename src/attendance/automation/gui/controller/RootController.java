package attendance.automation.gui.controller;

import attendance.automation.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class RootController implements Initializable
{

    @FXML
    private AnchorPane root;
    @FXML
    private SubScene innerScene;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("gui/view/LoginScreen.fxml"));
            Parent parent = loader.load();

            Controller ctrl = loader.getController();
            double height = ctrl.getHeight();
            double width = ctrl.getWidth();
            ctrl.setInnerScene(innerScene);

            innerScene.setRoot(parent);
            innerScene.setWidth(width);
            innerScene.setHeight(height);
        }
        catch (IOException ex)
        {
            Logger.getLogger(RootController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
