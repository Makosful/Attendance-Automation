package attendance.automation.gui.controller;

import attendance.automation.gui.model.Model;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class StudentStatisticWindowController implements Initializable
{

    private Model model;

    private final String sub1 = "Jan";
    private final String sub2 = "Feb";
    private final String sub3 = "Mar";

    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private Button btnSetDateStart;
    @FXML
    private Button btnSetDateToday;
    @FXML
    private Button btnBack;
    @FXML
    private PieChart chrtTotalAttendance;
    @FXML
    private BarChart<String, Number> chrtClassAttendance;
    @FXML
    private CheckBox cbSCO;
    @FXML
    private CheckBox cbSDE;
    @FXML
    private CheckBox cbITO;

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

        chrtTotalAttendance.setData(model.getPieChartAttendance());

        //<editor-fold defaultstate="collapsed" desc="Bar Chart">
        chrtClassAttendance.getData().addAll(getScoData(), getSdeData(), getItoData());
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Date and Time">
        LocalDate monthStart = getFirstDayOfMonth();
        dateFrom.setValue(monthStart);

        LocalDate today = LocalDate.now();
        dateTo.setValue(today);
        //</editor-fold>
    }

    @FXML
    private void handleBackButton(ActionEvent event)
    {
        model.changeStageStudentView();
    }

    @FXML
    private void handleSetDateStart(ActionEvent event)
    {
        dateFrom.setValue(LocalDate.of(2018, 1, 1));
    }

    @FXML
    private void handleSetDateToday(ActionEvent event)
    {
        dateTo.setValue(LocalDate.now());
    }

    private XYChart.Series getScoData()
    {
        XYChart.Series series = new XYChart.Series();
        series.setName("SCO");
        series.getData().addAll(
                new XYChart.Data<>(sub1, 95),
                new XYChart.Data<>(sub2, 86),
                new XYChart.Data<>(sub3, 91)
        );
        return series;
    }

    private XYChart.Series getSdeData()
    {
        XYChart.Series series = new XYChart.Series();
        series.setName("SDE");
        series.getData().addAll(
                new XYChart.Data<>(sub1, 100),
                new XYChart.Data<>(sub2, 89),
                new XYChart.Data<>(sub3, 96)
        );
        return series;
    }

    private XYChart.Series getItoData()
    {
        XYChart.Series series = new XYChart.Series();
        series.setName("ITO");
        series.getData().addAll(
                new XYChart.Data<>(sub1, 85),
                new XYChart.Data<>(sub2, 76),
                new XYChart.Data<>(sub3, 70)
        );
        return series;
    }

    private LocalDate getFirstDayOfMonth()
    {
        LocalDate initial = LocalDate.now();
        return initial.withDayOfMonth(1);
    }
}
