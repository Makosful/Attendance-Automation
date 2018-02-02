package attendance.automation.dal;

import attendance.automation.be.Student;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Axl
 */
public class XMLDataAccessObject
{

    /**
     *
     */
    public void saveXML()
    {
        JAXBContext jabx;
        Marshaller marshaller;

        try
        {
            jabx = JAXBContext.newInstance(Student.class);
            marshaller = jabx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Student student = new Student("Noble", "Maibigbut");

            marshaller.marshal(student, new FileOutputStream("test.xml"));
        }
        catch (JAXBException ex)
        {
            Logger.getLogger(XMLDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(XMLDataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
