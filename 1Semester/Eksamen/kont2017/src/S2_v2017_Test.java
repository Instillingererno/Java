import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class S2_v2017_Test{
	Bokstaveringsalfabet instance;
	double delta  = 0.0001;

	@BeforeClass
	public static void setUpClass() throws Exception {
		// database-innlogging
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	  // database.logut
	}

	@Before
	public void setUp() {
		/* oppretter nytt FonetiskAlafbet-objekt her, alternativt kan vi opprette et nytt objekt i den enkelte test-metoden */
		String tabell = "Alfa-Delta-Charlie-Bravo";
		instance = new Bokstaveringsalfabet("Nato", tabell, "-");
	}

	@After
	public void tearDown() {
		instance = null;
	}
   // lag testklasse for metodene sorterting og konverteringFraOrdTilBokstaveringsalfabet
	@Test
	public void testSorter() {
		System.out.println("testSorter");
		String[] expResult = {"Alfa", "Bravo","Charlie","Delta"};
		String[] result = instance.sorter();
		assertEquals(expResult, result);
    }

	@Test
	public void testKonverter(){
		System.out.println("testKonverter");
		String ord = "ABBA";
		String expResult = "Alfa Bravo Bravo Alfa";
		String result = instance.konverter(ord);
		assertEquals(expResult, result);
	}

    public static void main(String args[]) {
	   org.junit.runner.JUnitCore.main(S2_v2017_Test.class.getName()); // tas med dersom textpad ikke er konfigurert
    }
}