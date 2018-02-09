package attendance.automation.gui.model;

import attendance.automation.Main;
import attendance.automation.bll.BLLManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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
    private final ObservableList<PieChart.Data> pieChartAttendance;

    /**
     * Singleton constructor. Prevents new instances of this class being made
     * outside of this class
     */
    private Model()
    {
        // Object initiation
        bll = new BLLManager();

        // Adding mock data to the pie chart
        pieChartAttendance = FXCollections.observableArrayList();
        pieChartAttendance.addAll(
                new PieChart.Data("Present", 80),
                new PieChart.Data("Absent", 20)
        );
    }

    //<editor-fold defaultstate="collapsed" desc="Stage">
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Change Stage">
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
     */
    /**
     * Changes the stage to the Student Attendance screen
     */
    public void changeStageStudentAttendance() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/StudentStatisticWindow.fxml"));
            Parent parent = loader.load();
            this.stage.setScene(new Scene(parent));
            this.centerStage(stage);
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Changes the stage to the Teacher view
     
    public void changeStageTeacherView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/TeacherScreen.fxml"));
            Parent parent = loader.load();
            this.stage.setScene(new Scene(parent));
            this.centerStage(stage);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     */
    /**
     * Changes the stage to the Teacher's view of the student
     */
    public void changeStageTeacherStudentView()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/TeacherStudentView.fxml"));
            Parent parent = loader.load();
            this.stage.setScene(new Scene(parent));
            this.centerStage(stage);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Observables">
    public ObservableList<PieChart.Data> getPieChartAttendance()
    {
        return pieChartAttendance;
    }
    //</editor-fold>

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
