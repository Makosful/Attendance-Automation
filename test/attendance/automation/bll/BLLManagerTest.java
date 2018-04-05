package attendance.automation.bll;

import attendance.automation.be.Clazz;
import attendance.automation.be.NotificationMessage;
import attendance.automation.be.Student;
import attendance.automation.be.User;
import attendance.automation.be.Wifi;
import attendance.automation.dal.DALException;
import attendance.automation.dal.IDAL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Axl
 */
public class BLLManagerTest
{

    private static IDAL dal;
    private BLLManager bll;

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        dal = new IDALImpl();
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
        dal = null;
    }

    @Before
    public void setUp() throws Exception
    {
        bll = new BLLManager(dal);
    }

    @After
    public void tearDown() throws Exception
    {
        bll = null;
    }

    public void testGetAttendanceTimeFrame()
    {
    }

    public void testAttendancePieChartDates()
    {
        bll.attendancePieChartDates(LocalDate.now(), LocalDate.now(), 1);
    }

    private static class IDALImpl implements IDAL
    {

        public IDALImpl()
        {
        }

        @Override
        public ArrayList<Boolean> attendanceTimeFrame(LocalDate from, LocalDate to, int id) throws DALException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void changePassword(User user, String newPass)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void createNewUser(User student)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public ArrayList<Integer> getStudentAttendance(User user) throws DALException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public List<Wifi> getWifi() throws DALException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public ArrayList<Student> loadStudents() throws DALException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void registerAttendance(User user) throws DALException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean validEmail(String email)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean validUsername(String username)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public User userLogIn(String username, String password) throws DALException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public ArrayList<Clazz> fillClassesListCombo() throws DALException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void fillStudentsList(ListView<String> lstStudents)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public LocalDate setStartDate()
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public LocalDate getFirstDayOfMonth()
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void fillClassesChart(PieChart chrtClasses)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void fillStudentsChart(PieChart chrtStudents)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setNewPassword(String newRandomEncryptedPassword, String email)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public double GetAttendancePercentage(int UserID) throws DALException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public List<NotificationMessage> allNotifications() throws DALException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void getUser(User user) throws DALException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void requestAttendaceChange(int studentId, List<String> chosenCalsses, String message, LocalDate date) throws DALException
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
