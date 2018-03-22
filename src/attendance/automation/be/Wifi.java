package attendance.automation.be;

/**
 *
 * @author Axl
 */
public class Wifi
{

    String ssid, type, authentication, encryption;

    public Wifi(String ssid, String type, String authentication, String encryption)
    {
        this.ssid = ssid;
        this.type = type;
        this.authentication = authentication;
        this.encryption = encryption;
    }

    public String getSsid()
    {
        return ssid;
    }

    public void setSsid(String ssid)
    {
        this.ssid = ssid;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getAuthentication()
    {
        return authentication;
    }

    public void setAuthentication(String authentication)
    {
        this.authentication = authentication;
    }

    public String getEncryption()
    {
        return encryption;
    }

    public void setEncryption(String encryption)
    {
        this.encryption = encryption;
    }

    @Override
    public String toString()
    {
        return this.ssid + " "
               + this.type + " "
               + this.authentication + " "
               + this.encryption;
    }
}
