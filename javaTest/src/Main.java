import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        /*var things = IntStream.rangeClosed(1, 10).mapToObj(i -> new Thing(String.valueOf(i), i)).toArray(Thing[]::new);

        System.out.println(oldestThing(things));*/

        //System.out.println(sum(1,2,3,4,5,5,678));

        System.out.println(numWays(4));


    }

    private static Thing oldestThing(Thing... things) {
        return Arrays.stream(things).max(Comparator.comparingInt(Thing::getAge)).orElseThrow();
    }

    private static double sum(Number... numbers) {
        return Arrays.stream(numbers).mapToDouble(Number::doubleValue).sum();
    }


    public static int numWays(int steps, int... stepSizesArray) {
        if(stepSizesArray.length == 0) stepSizesArray = new int[] {1,2};
        final int[] tempArray = stepSizesArray; // For lambda use
        return Arrays.stream(stepSizesArray)
                .map(e -> (steps - e) < 0 ? 0 : (steps - e) == 0 ? 1 : numWays(steps - e, tempArray))
                .sum();
    }


}

class Thing {
    private final String name;
    private final int age;

    Thing(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Name: " + name + ", age: " + age;
    }

    String getName() {
        return name;
    }
    
    int getAge() {
        return age;
    }
}