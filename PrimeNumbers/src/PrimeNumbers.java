public class PrimeNumbers {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long[] primeArray = new long[500000];
        int iterator = 0;
        long number = 1;

        while(iterator < 500000) {
            boolean prime = true;
            number++;
            for(int i = 2; i < number-1; i++) {
                if(number % i == 0) {
                    prime = false;
                    break;
                }
            }
            if(prime) {
                primeArray[iterator] = number;
                iterator++;
            }
        }
        /*for(int i : primeArray) {
            System.out.println(i);
        }*/
        long endTime = System.currentTimeMillis();
        System.out.println((endTime-startTime) + " ms");
    }
}
