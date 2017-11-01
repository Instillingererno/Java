import static javax.swing.JOptionPane.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*interface divide {
    int findDivide(int a, int b);
}
interface passString {
    void foo(String a);
}

interface test123{
    void foo();
}
class test {
    public test(test123 input) {
        input.foo();
    }
}*/

class oisfg extends Thread {
    run() {

    }
}

interface Type {
    int areal(int a, int b);
}
interface Test {
    void dewit();
}


class Figur {
    int lengde;
    int bredde;
    Type hvafaen;
    public Figur(int lengde, int bredde, Type hvafaen) {
        this.lengde = lengde;
        this.bredde = bredde;
        this.hvafaen = hvafaen;
    }
    public int getAreal() {
        return this.hvafaen.areal(this.lengde, this.bredde);
    }
}
class Ting {
    public static void doStuff(Test input) {
        input.dewit();
    }
}

class lambda {
    public static void main(String[] args) {



        new Thread(()-> )

        String[] test = {"Test1", "Test2", "Test3", "Test4"};

        Stream.of(test).forEach((index) -> System.out.println(index));
        Stream.of(test).forEach(System.out::println);

        /*Ting.doStuff(() -> {
            String output = "Dette skal vise hva denoeridhnstlgk"
                        +"\nDfrjkndhdtnjfsjhngøhjlnfsghfgh"
                        +"\nDfrjkndhdtnjfsjhngøhjlnfsghfgh"
                        +"\nDfrjkndhdtnjfsjhngøhjlnfsghfgh"
                        +"\nDfrjkndhdtnjfsjhngøhjlnfsghfgh"
                        +"\nDfrjkndhdtnjfsjhngøhjlnfsghfgh"
                        +"\nDfrjkndhdtnjfsjhngøhjlnfsghfgh"
                        +"\nDfrjkndhdtnjfsjhngøhjlnfsghfgh"
                        +"\nDfrjkndhdtnjfsjhngøhjlnfsghfgh"
                        +"\nDfrjkndhdtnjfsjhngøhjlnfsghfgh"
                        +"\nDfrjkndhdtnjfsjhngøhjlnfsghfgh"
                        +"\nDfrjkndhdtnjfsjhngøhjlnfsghfgh"
                        +"\nDfrjkndhdtnjfsjhngøhjlnfsghfgh";
            System.out.println(output);
        });*/

        /*Type sirkel = (int a, int b) -> b * 3;
        Type kvadrat = (int a, int b) -> a * b;

        Figur[] wut = new Figur[] {new Figur(2,2,kvadrat), new Figur(0,4,sirkel)};

        for(int i = 0; i < wut.length; i++) {
            System.out.println(wut[i].getAreal());
        }*/

        //List stuff = Arrays.asList("Test1", "Test2", "Test3");
        //stuff.forEach(System.out::println);

        // ------------- Gammel test
        /*divide test1 = (int a, int b) -> (b != 0) ? a / b : 0;

        System.out.println(test1.findDivide(4,2));

        System.out.println("------------------------");


        // \/ kunne vært passString test2 = (String a) -> test.skrivTilCmd(a);
        passString test2 = test::skrivTilCmd;

        test2.foo("DIOASgjnhgkfhj");

        int sum;



        //while(() != CLOSED_OPTION)
        */

        //test test12345 = new test(() -> System.out.println("Hello world"));

    }
}
