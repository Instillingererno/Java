public class EulersMethod {
    private final Function function;
    private final double knownX;
    private final double knownY;

    EulersMethod(Function function, double knownX, double knownY) {
        this.function = function;
        this.knownX = knownX;
        this.knownY = knownY;
    }

    double estimateValue(double xToEstimate, double stepLength) {
        int steps = (int) ((xToEstimate - knownX) / stepLength);
        double lastYValue = knownY;
        for(double i = 0; i < steps; i++) {
            lastYValue = lastYValue + stepLength * function.run((knownX + stepLength * i), lastYValue);
        }
        return lastYValue;
    }

    public static void main(String[] args) {
        EulersMethod test = new EulersMethod(((x, y) -> 6*Math.pow(x,2) - 3*Math.pow(x,2)*y), 0, 3);
        System.out.printf("Steplength = 1:\t\t %.4f\n", test.estimateValue(1, 1));
        System.out.printf("Steplength = 0.1:\t %.4f\n", test.estimateValue(1, 0.1));
        System.out.printf("Steplength = 0.01:\t %.4f\n", test.estimateValue(1, 0.01));
        System.out.printf("Steplength = 0.001:\t %.4f\n", test.estimateValue(1, 0.001));
        System.out.println();
        System.out.printf("Difference: %.4f\n", Math.abs(test.estimateValue(1, 1) - (2+Math.pow(Math.E,Math.pow(-1,3)))));
        System.out.printf("Difference: %.4f\n", Math.abs(test.estimateValue(1, 0.1) - (2+Math.pow(Math.E,Math.pow(-1,3)))));
        System.out.printf("Difference: %.4f\n", Math.abs(test.estimateValue(1, 0.01) - (2+Math.pow(Math.E,Math.pow(-1,3)))));
        System.out.printf("Difference: %.4f\n", Math.abs(test.estimateValue(1, 0.001) - (2+Math.pow(Math.E,Math.pow(-1,3)))));
    }

    interface Function {
        double run(double x, double y);
    }
}
