/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

import java.sql.Date;

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
    private final Date date;
    
    public NotificationMessage(int studentID, String className, String studentName, String message, Date date)
    {
        this.studentID = studentID;
        this.className = className;
        this.message = message;
        this.studentName = studentName;
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
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
