package attendance.automation.bll;

import attendance.automation.be.PasswordValidation;
import attendance.automation.be.Student;
import attendance.automation.be.User;
import attendance.automation.bll.Encryption.Encryption;
import attendance.automation.dal.DALException;
import attendance.automation.dal.DALManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javax.mail.MessagingException;

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

    public void createStudent(String fName, String lName, String uName,
                              String email, String password)
    {
        User student = new Student(true, fName, lName, uName, email, password);
        dal.createNewUser(student);
    }

    public boolean validEmail(String email)
    {
        return dal.validEmail(email);
    }

    public boolean validUsername(String username)
    {
        return dal.validUsername(username);
    }

    public User userLogIn(String username, String password) throws BLLException
    {
        try
        {
            return dal.userLogIn(username, password);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getMessage(), ex);
        }
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

    /**
     * Store or remove the login credentials
     *
     * @param txtUserName
     * @param txtPassword
     * @param checkBoxSelected
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void storeLocalLogin(String txtUserName, String txtPassword, boolean checkBoxSelected)
            throws IOException,
                   NoSuchAlgorithmException
    {
        if (checkBoxSelected)
        {
            StoreLocalLogin.setLoginInfo(txtUserName, txtPassword);
        }
        else
        {
            StoreLocalLogin.setLoginInfo("", "");
        }
    }

    /**
     * Get the login credentials
     *
     * @return
     *
     * @throws FileNotFoundException
     */
    public String[] getLoginInfo() throws FileNotFoundException
    {

        String[] rememberLogin = StoreLocalLogin.getLoginInfo();
        return rememberLogin;

    }

    /**
     * Send a email to the user containing a new password
     *
     * @param email
     */
    public void forgottenPassEmail(String email) throws MessagingException
    {
        String newRandomPassword = RandomPassword.generateRandomPassword();

        Email mail = new Email(email, "New password for attendance automation",
                               "<p style='font-size:19px'>Hi, here is the new password"
                               + " for your account: </p><span style='font-size:12px; "
                               + "border: 1px solid green; padding:4px;'>"
                               + newRandomPassword + "</span>"
                               + "<p style='font-size:13px'>Please remember to "
                               + "change it after the first login</p>");
        mail.sendMail();

        String newRandomEncryptedPassword = Encryption.passwordEncryption(newRandomPassword);
        dal.setNewPassword(newRandomEncryptedPassword, email);
    }

}
