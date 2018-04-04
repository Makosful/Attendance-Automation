package attendance.automation.gui.model;

import attendance.automation.Main;
import attendance.automation.be.LoadedStudent;
import attendance.automation.be.User;
import attendance.automation.bll.BLLException;
import attendance.automation.bll.BLLManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
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

    private final BLLManager bll;

    private final ObservableList<PieChart.Data> pieChartAttendance;
    private final ObservableList<LoadedStudent> students;
    private User user;

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

        students = FXCollections.observableArrayList();
        loadStudents(students);
    }

    /**
     * Opens the Change Password window
     */
    public void changePassword()
    {
        openWindow("ChangePassword");
    }

    /**
     * Changes the current user's password
     *
     * @param newPass
     * @param curPass
     *
     * @throws attendance.automation.bll.BLLException
     */
    public void changePassword(String newPass, String curPass) throws BLLException
    {
        bll.changePassword(user, newPass, curPass);
    }

    public ObservableList<LoadedStudent> getStudents()
    {
        return students;
    }

    public boolean isAtSchool(String wifi)
    {
        try
        {
            return bll.isConnectedToWifi(wifi);
        }
        catch (BLLException ex)
        {
            return false;
        }
    }

    public void registerPresent() throws BLLException
    {
        bll.registerAttendance(user);
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
        user = bll.userLogIn(username, password);
        return user;
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
        try
        {
            pieChartAttendance.addAll(bll.getStudentAttendance(user));
            return pieChartAttendance;
        }
        catch (BLLException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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

    public boolean forgottenPassEmail(String email) throws MessagingException
    {
        boolean emailExistsInDB = bll.forgottenPassEmail(email);
        return emailExistsInDB;
    }

    private void loadStudents(ObservableList<LoadedStudent> list)
    {
        try
        {
            list.addAll(bll.loadStudent());
        }
        catch (BLLException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void openWindow(String fxml)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("gui/view/" + fxml + ".fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();
            stage.setResizable(false);
            stage.centerOnScreen();
        }
        catch (IOException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
