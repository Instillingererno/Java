import org.junit.*;
import static org.junit.Assert.*;

public class JUnitTest {
    Bokstaveringsalfabet instance;
    double delta = 0.0001;

    @Before
    public void setUp() throws Exception {
        String tabell = "Alfa-Delta-Charlie-Bravo";
        instance = new Bokstaveringsalfabet("Nato", tabell, "-");
    }
    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testSorter() {
        System.out.println("testSorter");
        System.out.println(instance.toString());
        String[] expResult = {"Alfa", "Bravo", "Charlie", "Delta"};
        String[] result = instance.sorter();
        assertArrayEquals(expResult, result);
        System.out.println(instance.toString());
    }
    @Test
    public void testKonverter() {
        System.out.println("testKonverter");
        String ord = "ABBA";
        String expResult = "Alfa Bravo Bravo Alfa";
        String result = instance.konverter(ord);
        assertEquals(expResult, result);

    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main(JUnitTest.class.getName()); // tas med dersom textpad ikke er konfigurert
    }
}