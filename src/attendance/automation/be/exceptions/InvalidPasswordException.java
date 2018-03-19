package attendance.automation.be.exceptions;

/**
 *
 * @author Axl
 */
public class InvalidPasswordException extends Exception
{

    public InvalidPasswordException(String message)
    {
        super(message);
    }

    public InvalidPasswordException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InvalidPasswordException(Throwable cause)
    {
        super(cause);
    }
}
