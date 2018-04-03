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
    String message;
    int studentID;
    int classID;
    
    public NotificationMessage(int studentID, int classID, String message)
    {
        this.studentID = studentID;
        this.classID = classID;
        this.message = message;
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
    
}
