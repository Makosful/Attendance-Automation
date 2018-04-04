package attendance.automation.dal;

import attendance.automation.be.User;
import attendance.automation.be.Wifi;
import attendance.automation.dal.UserLogIn.UserLogIn;
import attendance.automation.dal.ValidationDatabase.IValidationDatabase;
import attendance.automation.dal.ValidationDatabase.ValidationDataBase;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;

/**
 *
 * @author Axl
 */
public class DALManager
{

    private final String sub1 = "Jan";
    private final String sub2 = "Feb";
    private final String sub3 = "Mar";
    private final UserDAO uDAO;
    private final IValidationDatabase vd;
    private final UserLogIn liEncryption;
    private final StudentDAO sDAO;

    public DALManager()
    {
        vd = new ValidationDataBase();
        liEncryption = new UserLogIn();
        uDAO = new UserDAO();
        sDAO = new StudentDAO();
    }

    public void changePassword(User user, String pass)
    {
        uDAO.setNewPassword(pass, user.getEmail());
    }

    public void createNewUser(User user)
    {
        uDAO.addNewUser(user);
    }

    public ArrayList<Integer> getStudentAttendance(User user) throws DALException
    {
        try
        {
            return sDAO.getStudentAttendance(user);
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    public ArrayList loadStudents() throws DALException
    {
        try
        {
            return sDAO.getAllStudents();
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    public void registerAttendance(User user) throws DALException
    {
        try
        {
            sDAO.registerAttendance(user.getId(), true);
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    public User userLogIn(String username, String password) throws DALException
    {
        try
        {
            return liEncryption.userLogIn(username, password);
        }
        catch (SQLException ex)
        {
            throw new DALException("Password or username is not correct", ex);
        }
    }

    public boolean validEmail(String email)
    {
        return vd.validEmail(email);
    }

    public boolean validUsername(String username)
    {
        return vd.validUsername(username);
    }

    public void fillClassesList(ListView<String> lstClasses)
    {
        lstClasses.getItems().addAll(
                "SCO 1 A",
                "SCO 2 A",
                "SCO 1 B",
                "SCO 2 B",
                "SDE 1 A",
                "SDE 2 A",
                "SDE 1 B",
                "SDE 2 B",
                "ITO 1 A",
                "ITO 2 A",
                "ITO 1 B",
                "ITO 2 B"
        );
    }

    public void fillStudentsList(ListView<String> lstStudents)
    {
        lstStudents.getItems().addAll(
                "Massimiliano MacCallister",
                "Lita Sayre",
                "Pam Giovanizio",
                "Salmon Messruther",
                "Alyse Roscam",
                "Denna Shelley",
                "Clive Shilito",
                "Hendrik Kiezler",
                "Lillis Berkelay",
                "Magdalene Bielby",
                "Ezekiel Alderwick",
                "Kirbie Lamers",
                "Darrell Cordall",
                "Maggee Vorley",
                "Janina Antalffy",
                "Alys Breissan",
                "Deva Riggert",
                "Liam St. Pierre",
                "Leonidas Grover",
                "Viola Woodcraft",
                "Gail Simondson",
                "Pietro Dimitrijevic",
                "Patty Waterhouse",
                "Jilly Belliveau",
                "Janaya Hector",
                "Padraig Crehan",
                "Amabelle Farryn",
                "Bard Curtois",
                "Leese Clemensen",
                "Emmy Denekamp"
        );
    }

    public void fillClassesChart(PieChart chrtClasses)
    {
        chrtClasses.setTitle("Overall Attendance in class");
        chrtClasses.getData().addAll(
                new PieChart.Data("Attendance", 86),
                new PieChart.Data("Absense", 14)
        );
    }

    public void fillStudentsChart(PieChart chrtStudents)
    {
        chrtStudents.setTitle("Student's Overall Attendance");
        chrtStudents.getData().addAll(
                new PieChart.Data("Attendance", 98),
                new PieChart.Data("Absense", 2)
        );
    }

    public LocalDate setStartDate()
    {
        return LocalDate.of(2018, 1, 1);
    }

    public XYChart.Series getScoData()
    {
        XYChart.Series series = new XYChart.Series();
        series.setName("SCO");
        series.getData().addAll(
                new XYChart.Data<>(sub1, 95),
                new XYChart.Data<>(sub2, 86),
                new XYChart.Data<>(sub3, 91)
        );
        return series;
    }

    public XYChart.Series getSdeData()
    {
        XYChart.Series series = new XYChart.Series();
        series.setName("SDE");
        series.getData().addAll(
                new XYChart.Data<>(sub1, 100),
                new XYChart.Data<>(sub2, 89),
                new XYChart.Data<>(sub3, 96)
        );

        return series;
    }

    public XYChart.Series getItoData()
    {
        XYChart.Series series = new XYChart.Series();
        series.setName("ITO");
        series.getData().addAll(
                new XYChart.Data<>(sub1, 85),
                new XYChart.Data<>(sub2, 76),
                new XYChart.Data<>(sub3, 70)
        );

        return series;
    }

    public LocalDate getFirstDayOfMonth()
    {
        LocalDate initial = LocalDate.now();
        return initial.withDayOfMonth(1);
    }

    /**
     * Pass the information needed for setting a new password in the db
     *
     * @param password
     * @param email
     */
    public void setNewPassword(String password, String email)
    {
        uDAO.setNewPassword(password, email);
    }

    /**
     * Gets a list of all visible WIFI signals within range
     *
     * @return
     *
     * @throws DALException
     */
    public List<Wifi> getWifi() throws DALException
    {

        try
        {
            List<Wifi> results = new ArrayList();
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("netsh wlan show networks");
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;

            br.readLine(); // Skips the first line
            br.readLine(); // Skips interface name
            br.readLine(); // Skips number of available wifis
            br.readLine(); // Skips the fourth line
            while ((line = br.readLine()) != null)
            {
                String ssid = line;
                ssid = ssid.substring(9);

                String type = br.readLine();
                type = type.substring(30);

                String auth = br.readLine();
                auth = auth.substring(30);

                String encr = br.readLine();
                encr = encr.substring(30);

                Wifi wifi = new Wifi(ssid, type, auth, encr);
                results.add(wifi);

                br.readLine(); // Clears the empty line between entries
            }

            return results;
        }
        catch (IOException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    public double GetAttendancePercentage(int UserID) throws SQLException
    {
        ArrayList<Boolean> n = sDAO.registerAverageAttendance(UserID);

        double totalDays = n.size();
        double attendedDays = 0;
        double avgAmount = 0;

        for (Boolean b : n)
        {
            if (b)
            {
                attendedDays++;
            }

            avgAmount = (attendedDays / totalDays);
        }
        return avgAmount;
    }
}
