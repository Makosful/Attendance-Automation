package attendance.automation.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Axl
 */
public class StudentScreenController
        extends Controller
        implements Initializable
{

    @FXML
    private PieChart chrtStatistics;
    @FXML
    private AnchorPane root;
    private SubScene innerScene;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    @Override
    public double getWidth()
    {
        return root.getPrefWidth();
    }

    @Override
    public double getHeight()
    {
        return root.getPrefHeight();
    }

    @Override
    public void setInnerScene(SubScene scene)
    {
        this.innerScene = scene;
    }

}
