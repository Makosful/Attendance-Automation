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
    private final String className;
    private final String studentName;
    
    public NotificationMessage(int studentID, String className, String studentName, String message)
    {
        this.studentID = studentID;
        this.className = className;
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

    public String getClassName() 
    {
        return className;
    }
    
    public String getStudentName()
    {
        return studentName;
    }
    
}
