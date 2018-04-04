package attendance.automation.be;

/**
 *
 * @author Axl
 */
public class Clazz
{

    private final int id;
    private final String name;

    public Clazz(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
}
