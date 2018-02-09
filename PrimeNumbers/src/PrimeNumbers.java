import java.util.ArrayList;

public class PrimeNumbers {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int max = 20000;
        ArrayList<Integer> primeArray = new ArrayList(max);
        int iterator = 0;
        int number = 1;

        while(iterator < max) {
            boolean prime = true;
            number++;
            for(int i : primeArray) {
                if(number / i < 2) {
                    break;
                }
                if(number % i == 0) {
                    prime = false;
                    break;
                }
            }
            if(prime) {
                primeArray.add(number);
                iterator++;
            }
        }
        /*for(long i : primeArray) {
            System.out.println(i);
        }^*/
        System.out.println(primeArray.toString());
        //System.out.println(number);
        long endTime = System.currentTimeMillis();
        System.out.println((endTime-startTime) + " ms");
    }
}
