import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main {
    static List<Integer> ids = List.of(0,123,123,123,12,3,123,123,12,312,3,124,1,24,1243,124,124,412,456,546,56,56,5,56,56,5656,321,56,56,56,56,3,45,4,35,345,34,54,312,54,1,2,3,4,5,6,7,8,9,1,2,2,3,34,45,24,3,4,4,5,5,5,56,6,6,67,7,78,78,8,8,89,5,3,2,34,5,6,7,78,89,76,34,3,23,23,4,5,7,7,2,4,6,4,4,67,7,5,45,7,23);
    static int randomNumber(int thing) {
        sleep(10);
        return thing^2;
    }
    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println("Concurrency test, utilizing streams and concurrent lib, tested with simulated medium load i/o operation");
        System.out.println("list size " + ids.size());
        System.out.println();
        System.out.println("parallelism with completable future and forkjoinpool");
        long current = System.currentTimeMillis();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() ->
                new ForkJoinPool(ids.size()).submit(() ->
                        ids.stream()
                                .parallel()
                                .mapToInt(Main::randomNumber)
                                .sum()
                ).join()
        );
        /*IntStream.rangeClosed(1,4).forEach(i -> {
            sleep(1000);
            System.out.println(i + " second(s) have passed");
        });*/
        System.out.println("sum of squared ids " + future.get());
        System.out.println("time spent to calculate " + (System.currentTimeMillis() - current) + "ms");
        System.out.println();
        System.out.println("imperativ standard method with for loop, no concurrency");
        current = System.currentTimeMillis();
        int sum = 0;
        for(int i = 0; i < ids.size(); i++) {
            sum += randomNumber(ids.get(i));
        }
        System.out.println("sum of squared ids " + sum);
        System.out.println("time spent to calculate " + (System.currentTimeMillis() - current) + "ms");
        System.out.println();
        System.out.println("standard, easy, stream parallelism, uses (n of cores - 1) threads; best for heavy calc operations");
        current = System.currentTimeMillis();
        sum = ids.parallelStream().mapToInt(Main::randomNumber).sum();
        System.out.println("sum of squared ids " + sum);
        System.out.println("time spent to calculate " + (System.currentTimeMillis() - current) + "ms");
        System.out.println();
        System.out.println("uses standard stream, no parallelism");
        current = System.currentTimeMillis();
        sum = ids.stream().mapToInt(Main::randomNumber).sum();
        System.out.println("sum of squared ids " + sum);
        System.out.println("time spent to calculate " + (System.currentTimeMillis() - current) + "ms");
    }


    static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
