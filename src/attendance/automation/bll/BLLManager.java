package attendance.automation.bll;

import attendance.automation.be.*;
import attendance.automation.bll.Hashing.Hash;
import attendance.automation.dal.DALException;
import attendance.automation.dal.DALManager;
import attendance.automation.dal.IDAL;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.ListView;
import javax.mail.MessagingException;

/**
 *
 * @author Axl
 */
public class BLLManager implements IBLL
{

    private final IDAL dal;

    public BLLManager()
    {
        dal = new DALManager();
    }

    /**
     * Contructor used for testing
     *
     * @param dal
     */
    public BLLManager(IDAL dal)
    {
        this.dal = dal;
    }

    /**
     * Gets a student's attendance from a specific period
     *
     * @param from The start date
     * @param to The end date
     * @param userId
     * @param user The user
     * @return Returns a List with with the PieChart Data for the student's
     * attendance
     * @throws BLLException
     */
    @Override
    public List<Data> attendancePieChartDates(LocalDate from, LocalDate to, int userId)
            throws BLLException
    {
        try
        {
            ArrayList<Data> data = new ArrayList<>();
            ArrayList<Boolean> att = dal.attendanceTimeFrame(from, to, userId);

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

    @Override
    public void changePassword(User user, String newPass, String curPass)
            throws BLLException
    {
        System.out.println(user.getUserName());
        newPass = Hash.passwordHashing(newPass);
        curPass = Hash.passwordHashing(curPass);
        user = userLogIn(user.getUserName(), curPass);
        dal.changePassword(user, newPass);

    }

    @Override
    public void createStudent(String fName, String lName, String uName,
                              String email, String password)
    {
        String pass = Hash.passwordHashing(password);
        User student = new Student(true, fName, lName, uName, email, pass);
        dal.createNewUser(student);
    }

    @Override
    public ArrayList<Data> getStudentAttendance(User user) throws BLLException
    {
        try
        {
            ArrayList<Data> data = new ArrayList<>();
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
    @Override
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

    @Override
    public ArrayList<LoadedStudent> loadStudent() throws BLLException
    {
        try
        {
            ArrayList<LoadedStudent> students = new ArrayList<>();
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

    @Override
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

    @Override
    public boolean validEmail(String email)
    {
        return dal.validEmail(email);
    }

    @Override
    public boolean validUsername(String username)
    {
        return dal.validUsername(username);
    }

    @Override
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

    @Override
    public ArrayList<String> fillClassesListCombo() throws BLLException
    {
        try
        {
            ArrayList<String> data = new ArrayList<>();
            ArrayList<Clazz> clazzes = dal.fillClassesListCombo();
            clazzes.forEach((clazz) ->
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

    @Override
    public void fillStudentsList(ListView<String> lstStudents)
    {
        dal.fillStudentsList(lstStudents);
    }

    @Override
    public LocalDate setStartDate()
    {
        LocalDate startDate = dal.setStartDate();
        return startDate;
    }

    @Override
    public LocalDate getFirstDayOfMonth()
    {
        LocalDate firstDay = dal.getFirstDayOfMonth();
        return firstDay;
    }

    @Override
    public void fillClassesChart(PieChart chrtClasses)
    {
        dal.fillClassesChart(chrtClasses);
    }

    @Override
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
     * @throws attendance.automation.bll.BLLException
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Override
    public void storeLocalLogin(String txtUserName, String txtPassword, boolean checkBoxSelected)
            throws BLLException
    {
        try
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
        catch (IOException | NoSuchAlgorithmException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    /**
     * Get the login credentials
     *
     * @return
     * @throws attendance.automation.bll.BLLException
     *
     * @throws FileNotFoundException
     */
    @Override
    public String[] getLoginInfo() throws BLLException
    {
        try
        {
            String[] rememberLogin = StoreLocalLogin.getLoginInfo();
            return rememberLogin;
        }
        catch (FileNotFoundException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    /**
     * Send a email to the user containing a new password
     *
     * @param email
     *
     * @return
     * @throws attendance.automation.bll.BLLException
     *
     * @throws javax.mail.MessagingException
     */
    @Override
    public boolean forgottenPassEmail(String email) throws BLLException
    {

        try
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
        catch (MessagingException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }

    }

    public double getAveragePercentage(int UserID) throws BLLException
    {
        try
        {
            return dal.GetAttendancePercentage(UserID);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
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

    @Override
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

    @Override
    public void studentTimeFrame(LocalDate fromDate, LocalDate toDate, ObservableList<LoadedStudent> students) throws BLLException
    {
        ArrayList<LoadedStudent> newStudents = new ArrayList<>();
        for (LoadedStudent s : students)
        {
            try
            {
                ArrayList<Boolean> bool = dal.attendanceTimeFrame(fromDate, toDate, s.getId());
                double d = calculateAverage(bool) * 100;
                newStudents.add(new LoadedStudent(s.getId(), s.getFirstName(), s.getLastName(), d + "%"));
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
        sum = bool.stream().map((_item) -> 1.0).reduce(sum, (accumulator, _item) -> accumulator + 1);
        return sum / size;
    }

    @Override
    public void requestAttendaceChange(int studentId,
                                       List<String> chosenCalsses,
                                       String message,
                                       LocalDate date)
            throws BLLException
    {
        try
        {
            dal.requestAttendaceChange(studentId, chosenCalsses, message, date);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }
}
