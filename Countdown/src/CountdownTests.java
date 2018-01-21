import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountdownTests {
    @Test
    public void testNr1() {
        NumberGame test = new NumberGame(new double[] {75,100,1,5,2,4});
        Svar result = test.answer(686);
        assertEquals(result.getResult(), 686);
    }

    @Test
    public void testNr2() {
        for(int i = 0; i < 10; i++) {
            System.out.println((int) ((Math.random() * 4) + 1));

        }
    }
}
