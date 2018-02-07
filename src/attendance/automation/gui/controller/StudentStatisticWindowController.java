package attendance.automation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class StudentStatisticWindowController implements Initializable
{

    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private Button btnSetDateStart;
    @FXML
    private Button btnSetDateToday;
    @FXML
    private PieChart chrtTotalAttendance;
    @FXML
    private BarChart<?, ?> chrtClassAttendance;

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

}
