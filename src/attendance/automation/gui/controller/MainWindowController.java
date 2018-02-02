package attendance.automation.gui.controller;

import attendance.automation.gui.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author Axl
 */
public class MainWindowController implements Initializable
{

    private Model model;

    @FXML
    private Button btnButton;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = Model.getInstance();
    }

    @FXML
    private void handleButtonTest(ActionEvent event)
    {
        model.saveXML();
    }

}
