package attendance.automation.gui.controller;

import attendance.automation.gui.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class StudentScreenController implements Initializable
{

    private Model model;

    @FXML
    private PieChart chrtStatistics;
    @FXML
    private Label lblAttendance;
    @FXML
    private Button btnRegisterPresent;

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
        chrtStatistics.setData(model.getPieChartAttendance());
    }

    @FXML
    private void handleRegisterPresent(ActionEvent event)
    {
        lblAttendance.setText("Present");
    }
}
