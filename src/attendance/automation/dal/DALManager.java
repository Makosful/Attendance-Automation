package attendance.automation.dal;

import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;

/**
 *
 * @author Axl
 */
public class DALManager
{

    public DALManager()
    {
    }
    
    public void fillClassesList(ListView<String> lstClasses)
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

    public void fillStudentsList(ListView<String> lstStudents)
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

    public void fillClassesChart(PieChart chrtClasses)
    {
        chrtClasses.setTitle("Overall Attendance in class");
        chrtClasses.getData().addAll(
                new PieChart.Data("Attendance", 86),
                new PieChart.Data("Absense", 14)
        );
    }

    public void fillStudentsChart(PieChart chrtStudents)
    {
        chrtStudents.setTitle("Student's Overall Attendance");
        chrtStudents.getData().addAll(
                new PieChart.Data("Attendance", 98),
                new PieChart.Data("Absense", 2)
        );
    }

}
