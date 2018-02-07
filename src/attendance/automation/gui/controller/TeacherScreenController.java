package attendance.automation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class TeacherScreenController implements Initializable
{

    @FXML
    private ListView<String> lstClasses;
    @FXML
    private PieChart chrtClasses;
    @FXML
    private ListView<String> lstStudents;
    @FXML
    private PieChart chrtStudents;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        fillClassesList();
        fillStudentsList();
        fillClassesChart();
        fillStudentsChart();
    }

    private void fillClassesList()
    {
        lstClasses.getItems().addAll(
                "SCO 1 A",
                "SCO 2 A",
                "SCO 1 B",
                "SCO 2 B",
                "SDE 1 A",
                "SDE 2 A",
                "SDE 1 B",
                "SDE 2 B",
                "ITO 1 A",
                "ITO 2 A",
                "ITO 1 B",
                "ITO 2 B"
        );
    }

    private void fillStudentsList()
    {
        lstStudents.getItems().addAll(
                "Massimiliano MacCallister",
                "Lita Sayre",
                "Pam Giovanizio",
                "Salmon Messruther",
                "Alyse Roscam",
                "Denna Shelley",
                "Clive Shilito",
                "Hendrik Kiezler",
                "Lillis Berkelay",
                "Magdalene Bielby",
                "Ezekiel Alderwick",
                "Kirbie Lamers",
                "Darrell Cordall",
                "Maggee Vorley",
                "Janina Antalffy",
                "Alys Breissan",
                "Deva Riggert",
                "Liam St. Pierre",
                "Leonidas Grover",
                "Viola Woodcraft",
                "Gail Simondson",
                "Pietro Dimitrijevic",
                "Patty Waterhouse",
                "Jilly Belliveau",
                "Janaya Hector",
                "Padraig Crehan",
                "Amabelle Farryn",
                "Bard Curtois",
                "Leese Clemensen",
                "Emmy Denekamp"
        );
    }

    private void fillClassesChart()
    {
        chrtClasses.setTitle("Overall Attendance in class");
        chrtClasses.getData().addAll(
                new PieChart.Data("Attendance", 86),
                new PieChart.Data("Absense", 14)
        );
    }

    private void fillStudentsChart()
    {
        chrtStudents.setTitle("Student's Overall Attendance");
        chrtStudents.getData().addAll(
                new PieChart.Data("Attendance", 98),
                new PieChart.Data("Absense", 2)
        );
    }

}
