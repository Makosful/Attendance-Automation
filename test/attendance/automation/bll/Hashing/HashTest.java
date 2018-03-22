package attendance.automation.bll.Hashing;

import static org.junit.Assert.assertTrue;
import org.junit.*;

/**
 *
 * @author Axl
 */
public class HashTest
{

    public HashTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of passwordHashing method, of class Hash.
     */
    @Test
    public void testPasswordHashing()
    {
        System.out.println("passwordHashing");
        String password = "Passw0rd";
        String expResult = "ab38eadaeb746599f2c1ee90f8267f31f467347462764a24d71ac1843ee77fe3";
        String result = Hash.passwordHashing(password);
        assertTrue(expResult.equals(result));
    }

}
