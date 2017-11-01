import java.util.stream.Stream;

interface Lambda {
    int run(int a, int b);
}
interface Random {
    void run(String a);
}

class wuuut {
    public static void test(Random a) {
        //gjør noe
        a.run("it wurked");
    }
    public static void main(String[] args) {

        System.out.println("Testing av 1");
        Lambda test1 = (int a, int b) -> (b != 0) ? a / b : 0;
        System.out.println(test1.run(4,2));

        System.out.println("\n\n\nTesting av 2");
        Random test2 = System.out::println;
        //            (String a) -> System.out.println(a);
        test2.run("dette er en test");

        System.out.println("\n\n\nTesting av 3");
        String[] test3 = {"Test1", "Test2", "Test3", "Test4"};
        Stream.of(test3).forEach(System.out::println);

        System.out.println("\n\n\nEkstra test av 3 med substring");
        Stream.of(test3).forEach((String a) -> test2.run(a.substring(0,4)));

        test(test2);
    }
}
