package attendance.automation.bll;

import attendance.automation.dal.DALManager;
import java.time.LocalDate;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Axl
 */
public class BLLManager
{

    DALManager dal;

    public BLLManager()
    {
        dal = new DALManager();
    }

    public LocalDate setStartDate() {
        LocalDate startDate = dal.setStartDate();
        return startDate;
    }

    public XYChart.Series getScoData() {
        XYChart.Series series = dal.getScoData();
        return series;
    }

    public XYChart.Series getSdeData() {
        XYChart.Series series = dal.getSdeData();
        return series;
    }

    public XYChart.Series getItoData() {
        XYChart.Series series = dal.getItoData();
        return series;
    }

    public LocalDate getFirstDayOfMonth() {
        LocalDate firstDay = dal.getFirstDayOfMonth();
        return firstDay;
    }

}
