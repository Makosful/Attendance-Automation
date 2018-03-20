package attendance.automation.bll;

import attendance.automation.dal.DALManager;
import attendance.automation.gui.controller.LoginScreenController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


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

    public void storeLocalLogin(String txtUserName, String txtPassword, boolean checkBoxSelected) 
    throws IOException,
    NoSuchAlgorithmException 
    {
        if(checkBoxSelected)
        {
            StoreLocalLogin.setLoginInfo(txtUserName, txtPassword);
        }
        else
        {
            StoreLocalLogin.setLoginInfo("", "");
        }
    }

    public String[] getLoginInfo() throws FileNotFoundException {
        
        String[] rememberLogin = StoreLocalLogin.getLoginInfo();
        return rememberLogin;
    }

}
