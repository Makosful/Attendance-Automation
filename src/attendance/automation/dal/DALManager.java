package attendance.automation.dal;

import java.time.LocalDate;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Axl
 */
public class DALManager
{

    private final String sub1 = "Jan";
    private final String sub2 = "Feb";
    private final String sub3 = "Mar";
    
    public DALManager()
    {
    }

    public LocalDate setStartDate() {
        return LocalDate.of(2018, 1, 1);
    }

    public XYChart.Series getScoData() {
        XYChart.Series series = new XYChart.Series();
        series.setName("SCO");
        series.getData().addAll(
                new XYChart.Data<>(sub1, 95),
                new XYChart.Data<>(sub2, 86),
                new XYChart.Data<>(sub3, 91)
        );
        return series;
    }

    
    public XYChart.Series getSdeData()
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

    public XYChart.Series getItoData() {
        XYChart.Series series = new XYChart.Series();
        series.setName("ITO");
        series.getData().addAll(
                new XYChart.Data<>(sub1, 85),
                new XYChart.Data<>(sub2, 76),
                new XYChart.Data<>(sub3, 70)
        );

        return series;
    }

    public LocalDate getFirstDayOfMonth() {
        LocalDate initial = LocalDate.now();
        return initial.withDayOfMonth(1);
    }
        
}
