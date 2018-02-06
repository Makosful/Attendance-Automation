package attendance.automation.gui.model;

import attendance.automation.bll.BLLManager;

/**
 * This class will cache all data for the application while acting as the sole
 * point of connection to the BL Layer
 *
 * @author Axl
 */
public class Model
{

    // Singleton instance of the model
    private static final Model INSTANCE = new Model();

    BLLManager bll;

    /**
     * Singleton constructor. Prevents new instances of this class being made
     * outside of this class
     */
    private Model()
    {
        // Acts as a normal constructor otherwise

        bll = new BLLManager();
    }

    /**
     * Gets the single instance of the Model class
     *
     * @return The Model class
     */
    public static Model getInstance()
    {
        return INSTANCE;
    }

}
