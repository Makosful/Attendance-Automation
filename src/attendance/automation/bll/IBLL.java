package attendance.automation.bll;

import attendance.automation.be.LoadedStudent;
import attendance.automation.be.NotificationMessage;
import attendance.automation.be.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.ListView;

/**
 *
 * @author Axl
 */
public interface IBLL
{

    public ObservableList<Data> attendanceTimeFrame(LocalDate from, LocalDate to, User user) throws BLLException;

    public void changePassword(User user, String newPass, String curPass) throws BLLException;

    public boolean isConnectedToWifi(String wifi) throws BLLException;

    public ArrayList<Data> getStudentAttendance(User user) throws BLLException;

    public void registerAttendance(User user) throws BLLException;

    public void createStudent(String fName, String lName, String uName, String email, String password);

    public boolean validEmail(String email);

    public boolean validUsername(String username);

    public User userLogIn(String username, String password) throws BLLException;

    public void getUser(User user) throws BLLException;

    public ArrayList<String> fillClassesListCombo() throws BLLException;

    public void fillStudentsList(ListView<String> lstStudents);

    public void fillClassesChart(PieChart chrtClasses);

    public void fillStudentsChart(PieChart chrtStudents);

    public LocalDate setStartDate();

    public LocalDate getFirstDayOfMonth();

    public void storeLocalLogin(String txtUserName, String txtPassword, boolean selected) throws BLLException;

    public String[] getLoginInfo() throws BLLException;

    public boolean forgottenPassEmail(String email) throws BLLException;

    public ArrayList<LoadedStudent> loadStudent() throws BLLException;

    public List<NotificationMessage> allNotifications() throws BLLException;

    public void studentTimeFrame(LocalDate fromDate, LocalDate toDate, ObservableList<LoadedStudent> students) throws BLLException;

    public void requestAttendaceChange(int studentId, List<String> chosenCalsses, String message, LocalDate date) throws BLLException;

}
