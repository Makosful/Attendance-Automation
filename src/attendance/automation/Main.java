package attendance.automation;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Axl
 */
public class Main extends Application
{

    public static Image icon;

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Automatic Attendance");
        
        Parent root = FXMLLoader.load(getClass().getResource("gui/view/LoginScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException
    {
        int i = 0;
        System.out.println(i++);
        System.out.println(i);
        launch(args);

        
    }

}
