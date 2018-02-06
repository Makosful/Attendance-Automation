package attendance.automation.bll;

import attendance.automation.dal.DALManager;

/**
 *
 * @author Axl
 */
public class BLLManager
{

    DALManager dal;

    public BLLManager()
    {
        dal = new DALManager();
    }

}
