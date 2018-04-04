package attendance.automation.dal;

import attendance.automation.be.Clazz;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Axl
 */
public class ClassDAO
{

    private final DataBaseConnector db;

    public ClassDAO()
    {
        db = new DataBaseConnector();
    }

    public ArrayList<Clazz> getAllClasses() throws SQLException
    {
        ArrayList<Clazz> classes = new ArrayList();

        try (Connection con = db.getConnection())
        {
            String sql = "SELECT * FROM Classes";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                classes.add(new Clazz(rs.getInt("ClassId"), rs.getString("ClassName")));
            }
        }
        return classes;
    }
}
