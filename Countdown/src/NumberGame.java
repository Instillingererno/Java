import java.util.Arrays;

public class NumberGame {
    double[] numbers;

    public NumberGame(double[] numbers) {
        this.numbers = numbers;
    }

    public static void main(String[] args) {
        NumberGame test = new NumberGame(new double[] {75,100,1,5,2,4});
        Svar resultat = test.answer(686);
        System.out.println(resultat.getMethod());
    }

    public Svar answer(int input) {
        // (int) ((Math.random() * 4) + 1)
        long startTime = System.currentTimeMillis();
        double[] tall = new double[numbers.length];
        String method;
        double result;
        int iteration = 0;
        do {
            iteration++;
            System.arraycopy(numbers, 0, tall, 0, numbers.length);
            method = "";
            boolean fortsett = true;
            int index = (int) (Math.random() * (tall.length));
            int teller = 1;
            result = tall[index];
            tall[index] = 0;
            method += result + " ";
            while(fortsett) {
                index = (int) (Math.random() * (tall.length));
                double nesteTall = tall[index];
                //System.out.println(index + " " + nesteTall + " " + Arrays.toString(tall));
                tall[index] = 0;
                if(nesteTall != 0) {
                    teller ++;
                    switch ((int) (Math.random() * 4) + 1) {
                        case 1:
                            result += nesteTall;
                            method += "+ " + nesteTall + " = " + result + ";\n";
                            break;
                        case 2:
                            result -= nesteTall;
                            method += "- " + nesteTall + " = " + result + ";\n";
                            break;
                        case 3:
                            result = result * nesteTall;
                            method += "* " + nesteTall + " = " + result + ";\n";
                            break;
                        case 4:
                            result = result / nesteTall;
                            method += "/ " + nesteTall + " = " + result + ";\n";
                            break;
                    }
                }
                if(teller == tall.length || result == input) fortsett = false;
            }
        } while(result != input);
        long endTime = System.currentTimeMillis();
        method += iteration + " iterasjons over " + (endTime-startTime) + " ms;";
        return new Svar(method, result);
    }
}

class Svar {
    String method;
    double result;
    public Svar(String method, double result) {
        this.method = method;
        this.result = result;
    }
    public double getResult() {
        return result;
    }
    public String getMethod() {
        return method;
    }
}
