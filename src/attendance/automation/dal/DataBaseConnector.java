package attendance.automation.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author Hussain
 */
public class DataBaseConnector
{

    private final SQLServerDataSource dataSource;

    /**
     * Constructor
     * Setting the relevant info to the database connection.
     */
    public DataBaseConnector()
    {
        
        dataSource = new SQLServerDataSource();

        dataSource.setServerName("EASV-DB2");
        dataSource.setPortNumber(1433);
        dataSource.setDatabaseName("AttendanceAutomationNull");
        dataSource.setUser("CS2017A_8");
        dataSource.setPassword("CS2017A_8");
    }

    /**
     * Getting the connection from the database.
     *
     * @return Returns a Connection
     *
     * @throws SQLServerException Throws an exception if it fails to connect to
     *                            the database
     */
    public Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }

}
