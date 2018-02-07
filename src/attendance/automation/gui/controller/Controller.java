package attendance.automation.gui.controller;

import javafx.scene.SubScene;

/**
 *
 * @author Axl
 */
public abstract class Controller
{

    public abstract double getHeight();

    public abstract double getWidth();

    public abstract void setInnerScene(SubScene scene);
}
