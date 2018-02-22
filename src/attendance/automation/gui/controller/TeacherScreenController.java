package attendance.automation.gui.controller;

import attendance.automation.Main;
import attendance.automation.gui.model.Model;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class TeacherScreenController implements Initializable
{

    private Model model;

    @FXML
    private ListView<String> lstClasses;
    @FXML
    private PieChart chrtClasses;
    @FXML
    private ListView<String> lstStudents;
    @FXML
    private PieChart chrtStudents;
    @FXML
    private Button btnStudentStatistics;

    
    private Stage currentStage;
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

        fillClassesList();
        fillStudentsList();
        fillClassesChart();
        fillStudentsChart();
    }

    private void fillClassesList()
    {
        model.fillClassesList(lstClasses);
    }

    private void fillStudentsList()
    {
       model.fillStudentsList(lstStudents);
    }

    private void fillClassesChart()
    {
        model.fillClassesChart(chrtClasses);
    }

    private void fillStudentsChart()
    {
        model.fillStudentsChart(chrtStudents);
    }

    @FXML
    private void handleStudentStatictics(ActionEvent event) throws IOException
    {  
        currentStage = (Stage) btnStudentStatistics.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/TeacherStudentView.fxml"));
        Parent parent = loader.load();
        currentStage.setScene(new Scene(parent)); 
        centerStage();
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
}
