package attendance.automation.dal;

import attendance.automation.be.Clazz;
import attendance.automation.be.NotificationMessage;
import attendance.automation.be.Student;
import attendance.automation.be.User;
import attendance.automation.be.Wifi;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;

/**
 *
 * @author Axl
 */
public interface IDAL
{

    public ArrayList<Boolean> attendanceTimeFrame(LocalDate from, LocalDate to, int id) throws DALException;

    public void changePassword(User user, String newPass);

    public void createNewUser(User student);

    public ArrayList<Integer> getStudentAttendance(User user) throws DALException;

    public List<Wifi> getWifi() throws DALException;

    public ArrayList<Student> loadStudents() throws DALException;

    public void registerAttendance(User user) throws DALException;

    public boolean validEmail(String email);

    public boolean validUsername(String username);

    public User userLogIn(String username, String password) throws DALException;

    public ArrayList<Clazz> fillClassesListCombo() throws DALException;

    public void fillStudentsList(ListView<String> lstStudents);

    public LocalDate setStartDate();

    public LocalDate getFirstDayOfMonth();

    public void fillClassesChart(PieChart chrtClasses);

    public void fillStudentsChart(PieChart chrtStudents);

    public void setNewPassword(String newRandomEncryptedPassword, String email);

    public double GetAttendancePercentage(int UserID) throws DALException;

    public List<NotificationMessage> allNotifications() throws DALException;

    public void getUser(User user) throws DALException;

    public void requestAttendaceChange(int studentId, List<String> chosenCalsses, String message, LocalDate date) throws DALException;

}
