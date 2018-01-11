import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JUnitTest {
    Idrettsgren instance1;
    Ovelse test;
    @Before
    public void setup() {
        instance1 = new Idrettsgren("TEST", 2);
        test = new Ovelse("200m hekk", "Damer");
    }
    @Test
    public void regOvelseTest1() {
        boolean expResult = true;
        boolean actualResult = (instance1.regOvelse(test));
        assertTrue(actualResult);
    }
    @Test
    public void regOvelseTest2() {
        boolean expResult = false;
        instance1.regOvelse(test);
        boolean actualResult = (instance1.regOvelse(test));
        assertEquals(expResult, actualResult);
    }
    @Test
    public void regOvelseTest3() {
        boolean expResult = true;
        instance1.regOvelse(test);
        boolean result = instance1.regOvelse(new Ovelse("asdf","1/2"));
        assertSame(expResult, result);
    }
}
