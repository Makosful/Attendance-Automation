package attendance.automation.gui.controller;

import attendance.automation.Main;
import attendance.automation.be.Student;
import attendance.automation.be.User;
import attendance.automation.dal.DALManager;
import attendance.automation.dal.StudentDAO;
import attendance.automation.gui.model.Model;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class StudentScreenController implements Initializable
{

    @FXML
    private Button btnChangePassword;
    @FXML
    private Button btnLogout;
    @FXML
    private PieChart chrtStatistics;
    @FXML
    private Label lblAttendance;
    @FXML
    private Button btnRegisterPresent;
    @FXML
    private Button btnShowDetailStatistics;
    @FXML
    private AnchorPane anchorPane;

    private Model model;
    private Stage currentStage;
    private Student student;

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
        chrtStatistics.setData(model.getPieChartAttendance());

        try {
            checkWifi();
        } catch (SQLException ex) {
            Logger.getLogger(StudentScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void centerStage(Stage stage)
    {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    private void changeStage(String loginScreen)
    {
        try
        {
            Stage stage = (Stage) btnLogout.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/" + loginScreen + ".fxml"));
            Parent parent = loader.load();
            stage.setScene(new Scene(parent));
            centerStage(stage);
        }
        catch (IOException ex)
        {
            Logger.getLogger(StudentScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkWifi() throws SQLException
    {
        boolean present = model.isAtSchool("EASV");
        if (present)
        {
            System.out.println("You are present");
            lblAttendance.setText("Present");
        }
    }

    @FXML
    private void handleChangePassword(ActionEvent event)
    {
        model.changePassword();
    }

    @FXML
    private void handleLogOut(ActionEvent event)
    {
        changeStage("LoginScreen");
    }

    @FXML
    private void handleRegisterPresent(ActionEvent event)
    {
        lblAttendance.setText("Present");
    }

    @FXML
    private void handleOpenDetailedView(ActionEvent event)
    {
        changeStageStudentAttendance();
    }

    /**
     * Changes the currentStage to the Student Attendance screen
     */
    public void changeStageStudentAttendance()
    {
        try
        {
            currentStage = (Stage) anchorPane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/StudentStatisticWindow.fxml"));
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

    void setUser(User user) {
        student = (Student) user;
        System.out.println(student.getLastName());
    }
}
