package attendance.automation.gui.controller;

import attendance.automation.Main;
import attendance.automation.be.LoadedStudent;
import attendance.automation.be.User;
import attendance.automation.gui.model.Model;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class TeacherScreenController implements Initializable {

    @FXML
    private Button btnChangePassword;
    @FXML
    private Button btnLogout;
    @FXML
    private TableView<LoadedStudent> tblStudents;
    @FXML
    private TableColumn<LoadedStudent, String> clmAtt;
    @FXML
    private TableColumn<LoadedStudent, String> clmFName;
    @FXML
    private TableColumn<LoadedStudent, String> clmLName;
    private ListView<String> lstStudents;
    @FXML
    private Button btnStudentStatistics;

    private Model model;

    private User user;

    private Stage currentStage;
    private ObservableList<LoadedStudent> students;
    @FXML
    private ComboBox<String> comboClasses;
    @FXML
    private JFXDatePicker fromDatepicker;
    @FXML
    private JFXDatePicker toDatepicker;
    @FXML
    private PieChart chrtStudents;
    @FXML
    private Button btnDatepickerSemesterStart;
    @FXML
    private Button btnDatepickerToday;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = Model.getInstance();

        students = FXCollections.observableArrayList();

        fillClassesList();
        setupStudentTable();
        fillStudentsTable();
        System.out.println(fromDatepicker.getValue());
        comboClasses.getSelectionModel().selectFirst();
    }

    private void centerStage(Stage stage) {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    private void changeStage(String loginScreen) {
        try {
            Stage stage = (Stage) btnStudentStatistics.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/" + loginScreen + ".fxml"));
            Parent parent = loader.load();
            stage.setScene(new Scene(parent));
            centerStage(stage);
        } catch (IOException ex) {
            Logger.getLogger(TeacherScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillClassesList() {
        model.fillClassesListCombo(comboClasses);
    }

    private void fillStudentsTable() {
        tblStudents.setItems(model.getStudents());
    }

    @FXML
    private void handleChangePassword(ActionEvent event) {
        model.changePassword();
    }

    @FXML
    private void handleLogOut(ActionEvent event) {
        changeStage("LoginScreen");
    }

    @FXML
    private void handleStudentStatictics(ActionEvent event) throws IOException {
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
    private void centerStage() {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        currentStage.setX((primScreenBounds.getWidth() - currentStage.getWidth()) / 2);
        currentStage.setY((primScreenBounds.getHeight() - currentStage.getHeight()) / 2);
    }

    private void setupStudentTable() {
        clmFName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        clmLName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        clmAtt.setCellValueFactory(new PropertyValueFactory<>("attendance"));
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void SetFromDatepicker(ActionEvent event) {
        CheckDatetimePickers();
    }

    @FXML
    private void SetToDatepicker(ActionEvent event) {
        CheckDatetimePickers();
    }

    private void CheckDatetimePickers() {
        if (fromDatepicker.getValue() != null && toDatepicker.getValue() != null) {
            LocalDate fromDate = fromDatepicker.getValue();
            LocalDate toDate = toDatepicker.getValue();
            String clazz = comboClasses.getSelectionModel().getSelectedItem();
            model.studentTimeFrame(fromDate, toDate, clazz);
        } else {
            return;
        }
    }

    @FXML
    private void MessageController(ActionEvent event) {
        try {
            currentStage = (Stage) btnStudentStatistics.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/view/TeacherStudentAttendanceChangeRequest.fxml"));
            Parent parent = loader.load();
            TeacherStudentAttendanceChangeRequestController attendanceRequest = loader.getController();
            attendanceRequest.setUser(user);
            currentStage.setScene(new Scene(parent));
            centerStage();
        } catch (IOException ex) {
            System.out.println("failed 2 open window");
            Logger.getLogger(TeacherScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSetDatepickerToSemesterStart(ActionEvent event) {
    }

    @FXML
    private void btnSetDatepickerToToday(ActionEvent event) {
    }
}
