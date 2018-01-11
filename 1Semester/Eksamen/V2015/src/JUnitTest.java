import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class JUnitTest {
    Mobil instance;

    @Before
    public void setup() {
        instance = new Mobil("Ifony 5", 3, 123456);
    }

    @Test
    public void test1() {
        boolean expResult = true;
        boolean result = instance.regVisittkort(new Visittkort(new Navn("Sveinung", "Overland"), 12345, 4321, "sveinungskole@gmail.com"));
        System.out.println(Arrays.toString(instance.sorter()));
        assertEquals(expResult, result);
    }
    @Test
    public void testRegnyvisittkort() {
        double delta = 1;
        Visittkort[] expResult = {null,null,new Visittkort(new Navn("Sveinung", "Overland"), 12345, 4321, "sveinungskole@gmail.com")};
        instance.regVisittkort(new Visittkort(new Navn("Sveinung", "Overland"), 12345, 4321, "sveinungskole@gmail.com"));
        Visittkort[] result = instance.sorter();
        assertArrayEquals(expResult,result);
    }
}

