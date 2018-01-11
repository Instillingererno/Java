import org.junit.*;
import static org.junit.Assert.*;

public class JUnitTest {
    Bokstaveringsalfabet instance;
    @Before
    public void setup() {
        instance = new Bokstaveringsalfabet("TestNavn", "Alfa-Delta-Beta", "-");
    }
    @Test
    public void sorterAlfabet() {
        String[] expResult = {"Alfa","Beta","Delta"};
        String[] result = instance.sorter();
        assertArrayEquals(expResult, result);
    }
    @Test
    public void konvertering() {
        String input = "ABBAK";
        String expResult = "Alfa Beta Beta Alfa Ukjent ";
        String result = instance.bokstavering(input);
        assertEquals(expResult, result);
    }
}
