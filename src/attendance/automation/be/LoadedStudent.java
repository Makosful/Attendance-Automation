package attendance.automation.be;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Axl
 */
public class LoadedStudent
{

    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty attendance;

    public LoadedStudent(String fName, String lName, String att)
    {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.attendance = new SimpleStringProperty(att);
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
}
