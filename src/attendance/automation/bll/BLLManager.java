package attendance.automation.bll;

import attendance.automation.be.LoadedStudent;
import attendance.automation.be.Student;
import attendance.automation.be.User;
import attendance.automation.be.Wifi;
import attendance.automation.bll.Hashing.Hash;
import attendance.automation.dal.DALException;
import attendance.automation.dal.DALManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javax.mail.MessagingException;

/**
 *
 * @author Axl
 */
public class BLLManager {

    private final DALManager dal;

    public BLLManager() {
        dal = new DALManager();
    }

    public void changePassword(User user, String newPass, String curPass) throws BLLException {
        System.out.println(user.getUserName());
        newPass = Hash.passwordHashing(newPass);
        curPass = Hash.passwordHashing(curPass);
        user = userLogIn(user.getUserName(), curPass);
        dal.changePassword(user, newPass);

    }

    public void createStudent(String fName, String lName, String uName,
            String email, String password) {
        String pass = Hash.passwordHashing(password);
        User student = new Student(true, fName, lName, uName, email, pass);
        dal.createNewUser(student);
    }

    public ObservableList<PieChart.Data> getStudentAttendance(User user) throws BLLException
    {
        try
        {
            ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
            int attendance = 0;
            int absence = 0;

            ArrayList<Integer> studentAttendance = dal.getStudentAttendance(user);
            for (Integer i : studentAttendance)
            {
                switch (i)
                {
                    case 0:
                        absence++;
                        break;
                    case 1:
                        attendance++;
                        break;
                }
            }

            data.add(new PieChart.Data("Attendance", attendance));
            data.add(new PieChart.Data("Absence", absence));

            return data;
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    /**
     * Checks if machine is connected to a certain wifi network
     *
     * @param wifi
     *
     * @return
     *
     * @throws BLLException
     */
    public boolean isConnectedToWifi(String wifi) throws BLLException {
        try {
            List<Wifi> wifiList = dal.getWifi();
            return wifiList.stream().anyMatch((w)
                    -> (w.getSsid().equalsIgnoreCase(wifi)));
        } catch (DALException ex) {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    public ObservableList<LoadedStudent> loadStudent() throws BLLException {
        try {
            ObservableList<LoadedStudent> students
                    = FXCollections.observableArrayList();
            ArrayList<Student> list = dal.loadStudents();

            for (Student s : list) {
                double p = getAveragePercentage(s.getId()) * 100;
                DecimalFormat df = new DecimalFormat("##.##");
                String ps = df.format(p);
                students.add(new LoadedStudent(s.getFirstName(),
                        s.getLastName(),
                        ps + "%"));
            }

            return students;
        } catch (DALException ex) {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    public void registerAttendance(User user) throws BLLException {
        try {
            dal.registerAttendance(user);
        } catch (DALException ex) {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    public boolean validEmail(String email) {
        return dal.validEmail(email);
    }

    public boolean validUsername(String username) {
        return dal.validUsername(username);
    }

    public User userLogIn(String username, String password) throws BLLException {
        try {
            return dal.userLogIn(username, password);
        } catch (DALException ex) {
            throw new BLLException(ex.getMessage(), ex);
        }
    }

    public void fillClassesList(ListView<String> lstClasses) {
        dal.fillClassesList(lstClasses);
    }

    public void fillClassesListCombo(ComboBox<String> comboClasses) {
        dal.fillClassesListCombo(comboClasses);
    }

    public void fillStudentsList(ListView<String> lstStudents) {
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

    public void fillClassesChart(PieChart chrtClasses) {
        dal.fillClassesChart(chrtClasses);
    }

    public void fillStudentsChart(PieChart chrtStudents) {
        dal.fillStudentsChart(chrtStudents);
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
            NoSuchAlgorithmException {
        if (checkBoxSelected) {
            StoreLocalLogin.setLoginInfo(txtUserName, txtPassword);
        } else {
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
    public String[] getLoginInfo() throws FileNotFoundException {

        String[] rememberLogin = StoreLocalLogin.getLoginInfo();
        return rememberLogin;

    }

    /**
     * Send a email to the user containing a new password
     *
     * @param email
     *
     * @return
     *
     * @throws javax.mail.MessagingException
     */
    public boolean forgottenPassEmail(String email) throws MessagingException {

        boolean emailInDB = dal.validEmail(email);
        if (!emailInDB) {

            String newRandomPassword = RandomPassword.generateRandomPassword();
            Email mail = new Email(email, "New password for attendance automation",
                    "<p style='font-size:19px'>Hi, here is the new password"
                    + " for your account: </p><span style='font-size:12px; "
                    + "border: 1px solid green; padding:4px;'>"
                    + newRandomPassword + "</span>"
                    + "<p style='font-size:13px'>Please remember to "
                    + "change it after the first login</p>");
            mail.sendMail();

            String newRandomEncryptedPassword = Hash.passwordHashing(newRandomPassword);
            dal.setNewPassword(newRandomEncryptedPassword, email);

            return true;
        } else {
            return false;
        }

    }

    public double getAveragePercentage(int UserID) throws BLLException {
        try {
            return dal.GetAttendancePercentage(UserID);
        } catch (SQLException ex) {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }
}
