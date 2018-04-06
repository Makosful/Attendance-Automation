package attendance.automation.gui.model;

import attendance.automation.Main;
import attendance.automation.be.LoadedStudent;
import attendance.automation.be.NotificationMessage;
import attendance.automation.be.User;
import attendance.automation.bll.BLLException;
import attendance.automation.bll.BLLManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
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
    private final ObservableList<Series<String, Number>> barChartAttendance;
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

        // pie chart
        pieChartAttendance = FXCollections.observableArrayList();
        
        //Bar chart
        barChartAttendance = FXCollections.observableArrayList();

        students = FXCollections.observableArrayList();
        loadStudents(students);
    }

    public void attendanceTimeFrame(LocalDate from, LocalDate to)
    {
        try
        {
            
            ObservableList<PieChart.Data> data = bll.attendanceTimeFrame(from, to, user);
            for (int i = 0; i < pieChartAttendance.size(); i++)
            {
                pieChartAttendance.get(i).setPieValue(data.get(i).getPieValue());
            }
            
        }
        catch (BLLException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void loadAttendance()
    {
        try
        {
            pieChartAttendance.clear();
            pieChartAttendance.addAll(bll.getStudentAttendance(user));
        }
        catch (BLLException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
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
        bll.getUser(user);
        return user;
    }

    public ObservableList<String> fillClassesListCombo() throws BLLException
    {
        return bll.fillClassesListCombo();
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

    public ObservableList<PieChart.Data> getPieChartAttendance()
    {
    
        return pieChartAttendance;
    }

    public LocalDate getStartDate()
    {
        LocalDate startDate = bll.setStartDate();
        return startDate;
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

    /**
     * Get user
     *
     * @return
     */
    public User getUser()
    {
        return user;
    }

    public List<NotificationMessage> allNotifications() throws BLLException
    {
        return bll.allNotifications();
    }

    public void studentTimeFrame(LocalDate fromDate, LocalDate toDate, String clazz)
    {
        try
        {
            bll.studentTimeFrame(fromDate, toDate, this.students);
        }
        catch (BLLException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * To send a request to teachers for changing the attaendace status
     *
     * @param studentId
     * @param message
     * @param chosenCalsses
     * @param date
     */
    public void requestAttendanceChange(int studentId, List<String> chosenCalsses, String message, LocalDate date) throws SQLException
    {
        bll.requestAttendaceChange(studentId, chosenCalsses, message, date);
    }
    
    public void changeStudentAttendance(Date date, int classID, int userID) throws BLLException
    {
        bll.changeStudentAttendance(date, classID, userID);
    }

    public ObservableList<Series<String, Number>> getBarChartAttendance(LocalDate from, LocalDate to) {
        
        barChartAttendance.clear();
        try {
            ObservableList<Series<String, Number>> seriesData = bll.getBarChartAttendance(user, from, to);
            barChartAttendance.addAll(seriesData);
        } catch (BLLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return barChartAttendance;
    }
}
