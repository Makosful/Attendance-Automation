package attendance.automation.bll;

import attendance.automation.be.*;
import attendance.automation.bll.Hashing.Hash;
import attendance.automation.dal.DALException;
import attendance.automation.dal.DALManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private final DALManager dal;

    public BLLManager()
    {
        dal = new DALManager();
    }

    public ObservableList<PieChart.Data> attendanceTimeFrame(LocalDate from, LocalDate to, User user) throws BLLException
    {
        try
        {
            ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
            ArrayList<Boolean> att = dal.attendanceTimeFrame(from, to, user.getId());

            int attend = 0;
            int abs = 0;

            for (Boolean bool : att)
            {
                if (bool)
                {
                    attend++;
                }
                else
                {
                    abs++;
                }
            }

            data.add(new PieChart.Data("Attendance", attend));
            data.add(new PieChart.Data("Absence", abs));

            return data;
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    public void changePassword(User user, String newPass, String curPass)
            throws BLLException
    {
        System.out.println(user.getUserName());
        newPass = Hash.passwordHashing(newPass);
        curPass = Hash.passwordHashing(curPass);
        user = userLogIn(user.getUserName(), curPass);
        dal.changePassword(user, newPass);

    }

    public void createStudent(String fName, String lName, String uName,
                              String email, String password)
    {
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
    public boolean isConnectedToWifi(String wifi) throws BLLException
    {
        try
        {
            List<Wifi> wifiList = dal.getWifi();
            return wifiList.stream().anyMatch((w)
                    -> (w.getSsid().equalsIgnoreCase(wifi)));
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    public ObservableList<LoadedStudent> loadStudent() throws BLLException
    {
        try
        {
            ObservableList<LoadedStudent> students
                                          = FXCollections.observableArrayList();
            ArrayList<Student> list = dal.loadStudents();

            for (Student s : list)
            {
                double p = getAveragePercentage(s.getId()) * 100;
                DecimalFormat df = new DecimalFormat("##.##");
                String ps = df.format(p);
                students.add(new LoadedStudent(s.getId(),
                                               s.getFirstName(),
                                               s.getLastName(),
                                               ps + "%"));
            }

            return students;
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    public void registerAttendance(User user) throws BLLException
    {
        try
        {
            dal.registerAttendance(user);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
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

    public ObservableList<String> fillClassesListCombo() throws BLLException
    {
        try
        {
            ObservableList<String> data = FXCollections.observableArrayList();
            ArrayList<Clazz> clazzes = dal.fillClassesListCombo();
            clazzes.forEach((clazz)
                    ->
            {
                data.add(clazz.getName());
            });
            return data;
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
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
     * Store or remove the login credentials
     *
     * @param txtUserName
     * @param txtPassword
     * @param checkBoxSelected
     * @param isHashed
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void storeLocalLogin(String txtUserName, String txtPassword, boolean checkBoxSelected, boolean isHashed)
            throws IOException,
                   NoSuchAlgorithmException
    {
        if (checkBoxSelected)
        {
            if(isHashed == false)
            {
                StoreLocalLogin.setLoginInfo(txtUserName, txtPassword);
            }
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
     *
     * @return
     *
     * @throws javax.mail.MessagingException
     */
    public boolean forgottenPassEmail(String email) throws MessagingException
    {

        boolean emailInDB = dal.validEmail(email);
        if (!emailInDB)
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

            String newRandomEncryptedPassword = Hash.passwordHashing(newRandomPassword);
            dal.setNewPassword(newRandomEncryptedPassword, email);

            return true;
        }
        else
        {
            return false;
        }

    }

    public double getAveragePercentage(int UserID) throws BLLException
    {
        try
        {
            return dal.GetAttendancePercentage(UserID);
        }
        catch (SQLException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    public List<NotificationMessage> allNotifications() throws BLLException
    {
        try
        {
            return dal.allNotifications();
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getMessage(), ex);
        }
    }

    public void getUser(User user) throws BLLException
    {
        try
        {
            dal.getUser(user);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getMessage(), ex);
        }
    }

    public void studentTimeFrame(LocalDate fromDate, LocalDate toDate, ObservableList<LoadedStudent> students) throws BLLException
    {
        ArrayList<LoadedStudent> newStudents = new ArrayList<>();
        for (LoadedStudent s : students)
        {
            try
            {
                ArrayList<Boolean> bool = dal.attendanceTimeFrame(fromDate, toDate, s.getId());
                double d = calculateAverage(bool) * 100;
                DecimalFormat df = new DecimalFormat("##.##");
                newStudents.add(new LoadedStudent(s.getId(), s.getFirstName(), s.getLastName(), df.format(d) + "%"));
            }
            catch (DALException ex)
            {
                throw new BLLException(ex.getLocalizedMessage(), ex);
            }
        }
        students.setAll(newStudents);
    }

    private double calculateAverage(ArrayList<Boolean> bool)
    {
        double size = bool.size();
        double sum = 0;

        for (Boolean b : bool)
        {
            if (b)
            {
                sum++;
            }
        }
//        sum = bool.stream().map((_item) -> 1.0).reduce(sum, (accumulator, _item) -> accumulator + 1);
        return sum / size;
    }

    public void requestAttendaceChange(int studentId, List<String> chosenCalsses, String message, LocalDate date) throws SQLException
    {
        dal.requestAttendaceChange(studentId, chosenCalsses, message, date);
    }

    public void changeStudentAttendance(Date date, int classID, int userID) throws BLLException
    {
        try
        {
            dal.changeStudentAttendance(date, classID, userID);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getMessage(), ex);
        }
    }

    public ObservableList<XYChart.Series<String, Number>> getBarChartAttendance(User user, LocalDate from, LocalDate to) throws BLLException
    {

        ObservableList<XYChart.Series<String, Number>> bars = FXCollections.observableArrayList();
        HashMap map;

        try
        {
            map = dal.attendanceClassStatistics(user.getId(), user.getClasses(), from, to);
            System.out.println();
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getMessage(), ex);
        }

        map.forEach((className, percentage) ->
        {
            System.out.println("Key : " + className + " Value : " + percentage);
            XYChart.Series bar = new XYChart.Series();
            bar.setName(className.toString());
            bar.getData().add(new XYChart.Data(className.toString(), percentage));
            bars.add(bar);
        });

        return bars;

    }
}
