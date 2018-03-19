package attendance.automation.bll;

import attendance.automation.dal.DALManager;
import java.time.LocalDate;
import javafx.scene.chart.XYChart;
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
    
    public boolean validEmail(String email)
    {
        return dal.validEmail(email);
    }
    
    public boolean validUsername(String username)
    {
        return dal.validUsername(username);
    }
    
    public void fillClassesList(ListView<String> lstClasses)
    {
        dal.fillClassesList(lstClasses);
    }

    public void fillStudentsList(ListView<String> lstStudents)
    {
        dal.fillStudentsList(lstStudents);
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

    public void fillClassesChart(PieChart chrtClasses)
    {
        dal.fillClassesChart(chrtClasses);
    }

    public void fillStudentsChart(PieChart chrtStudents)
    {
        dal.fillStudentsChart(chrtStudents);
    }

}
