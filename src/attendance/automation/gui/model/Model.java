package attendance.automation.gui.model;

import attendance.automation.bll.BLLManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;

/**
 * This class will cache all data for the application while acting as the sole
 * point of connection to the BL Layer
 *
 * @author Axl
 */
public class Model
{

    //<editor-fold defaultstate="collapsed" desc="Singleton">
// Singleton instance of the model
    private static final Model INSTANCE = new Model();

    /**
     * Gets the single instance of the Model class
     *
     * @return The Model class
     */
    public static Model getInstance()
    {
        return INSTANCE;
    }
    //</editor-fold>

    BLLManager bll;

    private final ObservableList<PieChart.Data> pieChartAttendance;

    /**
     * Singleton constructor. Prevents new instances of this class being made
     * outside of this class
     */
    private Model()
    {
        // Object initiation
        bll = new BLLManager();

        // Adding mock data to the pie chart
        pieChartAttendance = FXCollections.observableArrayList();
        pieChartAttendance.addAll(
                new PieChart.Data("Present", 80),
                new PieChart.Data("Absent", 20)
        );
    }
    
        public void fillClassesList(ListView<String> lstClasses)
    {
        bll.fillClassesList(lstClasses);
    }

    public void fillStudentsList(ListView<String> lstStudents)
    {
        bll.fillStudentsList(lstStudents);
    }

    public void fillClassesChart(PieChart chrtClasses)
    {
        bll.fillClassesChart(chrtClasses);
    }

    public void fillStudentsChart(PieChart chrtStudents)
    {
        bll.fillStudentsChart(chrtStudents);
    }

    //<editor-fold defaultstate="collapsed" desc="Observables">
    public ObservableList<PieChart.Data> getPieChartAttendance()
    {
        return pieChartAttendance;
    }
    //</editor-fold>
}
