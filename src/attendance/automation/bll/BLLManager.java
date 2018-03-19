package attendance.automation.bll;

import attendance.automation.be.PasswordValidation;
import attendance.automation.dal.DALManager;
import java.time.LocalDate;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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

    public PasswordValidation checkPasswordStrength(String pass)
    {
        if (pass.length() < 8)
        {
            return new PasswordValidation(false, "Password must be at least 8 characters long");
        }
        else if (!haveUpperCase(pass))
        {
            return new PasswordValidation(false, "Password must have at least 1 upper case");
        }
        else if (!haveLowerCase(pass))
        {
            return new PasswordValidation(false, "Password must have at least 1 lower case");
        }
        else if (!haveNumber(pass))
        {
            return new PasswordValidation(false, "Password must have at least 1 number");
        }
        return new PasswordValidation(true);
    }

    public void fillClassesList(ListView<String> lstClasses)
    {
        dal.fillClassesList(lstClasses);
    }

    public void fillStudentsList(ListView<String> lstStudents)
    {
        dal.fillStudentsList(lstStudents);
    }

    public LocalDate setStartDate()
    {
        LocalDate startDate = dal.setStartDate();
        return startDate;
    }

    public XYChart.Series getScoData()
    {
        XYChart.Series series = dal.getScoData();
        return series;
    }

    public XYChart.Series getSdeData()
    {
        XYChart.Series series = dal.getSdeData();
        return series;
    }

    public XYChart.Series getItoData()
    {
        XYChart.Series series = dal.getItoData();
        return series;
    }

    public LocalDate getFirstDayOfMonth()
    {
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

    /**
     * Checks of the given password contains any lowercase letters
     *
     * @param password
     *
     * @return
     */
    private boolean haveLowerCase(String password)
    {
        char[] pass = password.toCharArray();
        for (char p : pass)
        {
            if (Character.isLowerCase(p))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * checks is a password contains any numbers
     *
     * @param password
     *
     * @return
     */
    private boolean haveNumber(String password)
    {
        char[] pass = password.toCharArray();
        for (char p : pass)
        {
            if (Character.isDigit(p))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given oassword contains any uppercase letters
     *
     * @param password
     *
     * @return
     */
    private boolean haveUpperCase(String password)
    {
        char[] pass = password.toCharArray();
        for (char p : pass)
        {
            if (Character.isUpperCase(p))
            {
                return true;
            }
        }
        return false;
    }

}
