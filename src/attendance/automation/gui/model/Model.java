package attendance.automation.gui.model;

import attendance.automation.be.PasswordValidation;
import attendance.automation.be.User;
import attendance.automation.bll.BLLException;
import attendance.automation.bll.BLLManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javax.mail.MessagingException;

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

    public void signUp(String fName, String lName, String uName,
                       String email, String password)
    {
        bll.createStudent(fName, lName, uName, email, password);
    }

    /**
     * Checks if valid in database.
     *
     * @param email - The email to check.
     *
     * @return
     */
    public boolean validEmail(String email)
    {
        return bll.validEmail(email);
    }

    /**
     * Checks if valid username in db.
     *
     * @param username - username to check.
     *
     * @return
     */
    public boolean validUsername(String username)
    {
        return bll.validUsername(username);
    }

    public User userLogIn(String username, String password) throws BLLException
    {
        return bll.userLogIn(username, password);
    }

    public PasswordValidation checkPasswordstrength(String password)
    {
        return bll.checkPasswordStrength(password);
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

    public LocalDate getStartDate()
    {
        LocalDate startDate = bll.setStartDate();
        return startDate;
    }

    public XYChart.Series getScoData()
    {
        XYChart.Series series = bll.getScoData();
        return series;
    }

    public XYChart.Series getSdeData()
    {
        XYChart.Series series = bll.getSdeData();
        return series;
    }

    public XYChart.Series getItoData()
    {
        XYChart.Series series = bll.getItoData();
        return series;
    }

    public LocalDate getFirstDayOfMonth()
    {
        LocalDate firstDay = bll.getFirstDayOfMonth();
        return firstDay;
    }

    public void storeLocalLogin(String txtUserName, String txtPassword, boolean selected)
            throws IOException,
                   NoSuchAlgorithmException
    {
        bll.storeLocalLogin(txtUserName, txtPassword, selected);
    }

    public String[] getLogInInfo() throws FileNotFoundException
    {
        String[] login = bll.getLoginInfo();
        return login;
    }

    public void forgottenPassEmail(String email) throws MessagingException
    {
        bll.forgottenPassEmail(email);
    }

}
