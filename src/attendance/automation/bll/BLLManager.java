package attendance.automation.bll;

import attendance.automation.dal.DALManager;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;

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
    public void fillClassesList(ListView<String> lstClasses)
    {
        dal.fillClassesList(lstClasses);
    }

    public void fillStudentsList(ListView<String> lstStudents)
    {
        dal.fillStudentsList(lstStudents);
    }

    public void fillClassesChart(PieChart chrtClasses)
    {
        dal.fillClassesChart(chrtClasses);
    }

    public void fillStudentsChart(PieChart chrtStudents)
    {
        dal.fillStudentsChart(chrtStudents);
    }
}
