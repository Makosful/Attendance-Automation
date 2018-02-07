package attendance.automation;

import attendance.automation.gui.model.Model;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Axl
 */
public class Main extends Application
{

    public static Image icon;

    private Model model;

    @Override
    public void start(Stage stage) throws Exception
    {
        model = Model.getInstance();

        stage.setTitle("Automatic Attendance");

        model.setStage(stage);
        model.changeStageLogin();
        stage = model.getStage();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
