
interface Test{
    int tester(int a, int b);
}

class lambdatest {
    public static void main(String[] args) {

        Test tilfeldigVariabel = (int a, int b) -> {
            return a - b;
        };

        System.out.println(tilfeldigVariabel.tester(4, 2));

    }
}
