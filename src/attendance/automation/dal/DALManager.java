package attendance.automation.dal;

/**
 *
 * @author Axl
 */
public class DALManager
{

    XMLDataAccessObject xmlDAO;

    public DALManager()
    {
        xmlDAO = new XMLDataAccessObject();
    }

    public void saveXML()
    {
        xmlDAO.saveXML();
    }
}
