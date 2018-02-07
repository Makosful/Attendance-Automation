package attendance.automation.gui.model;

import attendance.automation.Main;
import attendance.automation.bll.BLLManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * This class will cache all data for the application while acting as the sole
 * point of connection to the BL Layer
 *
 * @author Axl
 */
public class Model
{

    //<editor-fold defaultstate="collapsed" desc="Singleton">
// Singleton instance of the model
    private static final Model INSTANCE = new Model();

    /**
     * Gets the single instance of the Model class
     *
     * @return The Model class
     */
    public static Model getInstance()
    {
        return INSTANCE;
    }
    //</editor-fold>

    BLLManager bll;

    private Stage stage;

    /**
     * Singleton constructor. Prevents new instances of this class being made
     * outside of this class
     */
    private Model()
    {
        // Object initiation
        bll = new BLLManager();
    }

    /**
     * Sets the central stage
     *
     * @param stage The Stage
     */
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    /**
     * Gets the central Stage
     *
     * @return Returns the Application's Stage
     */
    public Stage getStage()
    {
        return this.stage;
    }

    /**
     * Changes the Stage to the login screen
     */
    public void changeStageLogin()
    {
        try
        {
            Parent root = FXMLLoader.load(Main.class.getResource("gui/view/LoginScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            this.centerStage(stage);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Changes the stage to the student view screen
     */
    public void changeStageStudentView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/StudentScreen.fxml"));
            Parent parent = loader.load();
            this.stage.setScene(new Scene(parent));
            this.centerStage(stage);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Centers the window on the screen
     *
     * @param stage
     */
    private void centerStage(Stage stage)
    {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
