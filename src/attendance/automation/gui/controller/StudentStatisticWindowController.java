package attendance.automation.gui.controller;

import attendance.automation.Main;
import attendance.automation.gui.model.Model;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class StudentStatisticWindowController implements Initializable
{

    private Model model;


    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private Button btnSetDateStart;
    @FXML
    private Button btnSetDateToday;
    @FXML
    private Button btnBack;
    @FXML
    private PieChart chrtTotalAttendance;
    @FXML
    private BarChart<String, Number> chrtClassAttendance;
    @FXML
    private CheckBox cbSCO;
    @FXML
    private CheckBox cbSDE;
    @FXML
    private CheckBox cbITO;
    Stage currentStage;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button requestChange;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = Model.getInstance();

        chrtTotalAttendance.setData(model.getPieChartAttendance());

        //<editor-fold defaultstate="collapsed" desc="Bar Chart">
        chrtClassAttendance.getData().addAll(model.getScoData(), model.getSdeData(), model.getItoData());
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Date and Time">
        LocalDate monthStart = model.getFirstDayOfMonth();
        dateFrom.setValue(monthStart);

        LocalDate today = LocalDate.now();
        dateTo.setValue(today);
        //</editor-fold>
    }

    @FXML
    private void handleBackButton(ActionEvent event)
    {
       changeStageStudentView();
    }

    @FXML
    private void handleSetDateStart(ActionEvent event)
    {
        dateFrom.setValue(model.getStartDate());
        
    }

    @FXML
    private void handleSetDateToday(ActionEvent event)
    {
        dateTo.setValue(LocalDate.now());
    }
    
    /**
     * Centers the window on the screen
     *
     * @param currentStage
     */
    private void centerStage()
    {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        currentStage.setX((primScreenBounds.getWidth() - currentStage.getWidth()) / 2);
        currentStage.setY((primScreenBounds.getHeight() - currentStage.getHeight()) / 2);
    }
    
    /**
     * Changes the currentStage to the student view screen
     */
    public void changeStageStudentView()
    {
        try
        {
            this.currentStage = (Stage) anchorPane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/StudentScreen.fxml"));
            Parent parent = loader.load();
            this.currentStage.setScene(new Scene(parent));
            this.currentStage.show();
            this.centerStage();
        }
        catch (IOException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRequestChange(ActionEvent event) {
        try {           
            
            Stage primeStage = (Stage)requestChange.getScene().getWindow();
            FXMLLoader fxLoader = new FXMLLoader(Main.class.getResource("gui/view/RequestChange.fxml"));
            Parent root = fxLoader.load();
            
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initOwner(primeStage);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();
           
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
