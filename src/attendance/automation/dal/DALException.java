package attendance.automation.dal;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 *
 * @author Axl
 */
public class DALException extends Exception
{

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace)
    {
        super.setStackTrace(stackTrace); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StackTraceElement[] getStackTrace()
    {
        return super.getStackTrace(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized Throwable fillInStackTrace()
    {
        return super.fillInStackTrace(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printStackTrace(PrintWriter s)
    {
        super.printStackTrace(s); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printStackTrace(PrintStream s)
    {
        super.printStackTrace(s); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printStackTrace()
    {
        super.printStackTrace(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString()
    {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized Throwable initCause(Throwable cause)
    {
        return super.initCause(cause); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized Throwable getCause()
    {
        return super.getCause(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLocalizedMessage()
    {
        return super.getLocalizedMessage(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMessage()
    {
        return super.getMessage(); //To change body of generated methods, choose Tools | Templates.
    }
}
