/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

/**
 *
 * @author Hussain
 */
public class NotificationMessage 
{
    private final String message;
    private final int studentID;
    private final int classID;
    private final String studentName;
    
    public NotificationMessage(int studentID, int classID, String studentName, String message)
    {
        this.studentID = studentID;
        this.classID = classID;
        this.message = message;
        this.studentName = studentName;
    }
    /**
     * Get notification message
     * @return 
     */
    public String getMessage() 
    {
        return message;
    }

    public int getStudentID() 
    {
        return studentID;
    }

    public int getClassID() 
    {
        return classID;
    }
    
    public String getStudentName()
    {
        return studentName;
    }
    
}
