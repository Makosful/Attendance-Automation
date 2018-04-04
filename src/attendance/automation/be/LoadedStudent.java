package attendance.automation.be;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Axl
 */
public class LoadedStudent
{

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty attendance;

    public LoadedStudent(int id, String fName, String lName, String att)
    {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.attendance = new SimpleStringProperty(att);
    }

    public int getId()
    {
        return id.get();
    }

    public String getFirstName()
    {
        return firstName.get();
    }

    public String getLastName()
    {
        return lastName.get();
    }

    public String getAttendance()
    {
        return attendance.get();
    }

    public void setAttendance(double att)
    {
        double s = att * 100;
        this.attendance.setValue(s + "%");
    }
}
