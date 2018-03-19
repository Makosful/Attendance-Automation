package attendance.automation.be;

/**
 *
 * @author Axl
 */
public class PasswordValidation
{

    private final boolean valid;
    private final String message;

    public PasswordValidation(boolean valid)
    {
        this(valid, "");
    }

    public PasswordValidation(boolean valid, String message)
    {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid()
    {
        return valid;
    }

    public String getMessage()
    {
        return message;
    }
}
