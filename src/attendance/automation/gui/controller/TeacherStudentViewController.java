package attendance.automation.gui.controller;

import attendance.automation.gui.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class TeacherStudentViewController implements Initializable
{

    private Model model;

    private SubScene innerScene;
    @FXML
    private ToggleGroup days;
    @FXML
    private Button btnBackButton;

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
    private void handleBackButton(ActionEvent event)
    {
        model.changeStageTeacherView();
    }

}
