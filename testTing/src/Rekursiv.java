public class Rekursiv {
    private static long startTime;
    private static long pingTime = 0;
    private static long test2Time = 0;
    public static void main(String[] args) {
        int gjentakelse = 100000000;
        for(int i = 0; i < gjentakelse; i++) {
            startTime = System.nanoTime();
            ping(0);
            startTime = System.nanoTime();
            test2(0);
        }
        System.out.println("Ping brukte i gjennomsnitt " + pingTime/gjentakelse + " ns" +
        "\nTest2 brukte i gjennomsnitt " + test2Time/gjentakelse + " ns");
    }
    private static void ping(int tall) {
        while (tall < 1000000) {
            tall = pong(tall);
        }
        pingTime += System.nanoTime() - startTime;
    }
    private static int pong(int tall) {
        tall++;
        return tall;
    }

    private static void test2(int tall) {
        while (tall < 10000000) {
            tall++;
        }
        test2Time += System.nanoTime() - startTime;
    }
}
