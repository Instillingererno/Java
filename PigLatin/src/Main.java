import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<String> consonants = List.of("b", "c", "d", "f", "g", "h", "j", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "x", "z", "w", "y");

    public static void main(String[] args) {
        System.out.println(Main.translate("This is a test string to check if it works as expected"));
    }

    // This is a test string to check if it works as expected. It should work with several sentences as well!
    public static String translate(String input) {
        return Arrays.stream(input.split(" "))
                .map(word -> {
                    var cluster = Arrays.stream(word.toLowerCase().split(""))
                            .takeWhile(letter -> consonants.contains(letter))
                            .collect(Collectors.joining());
                    return word.substring(cluster.length()) + cluster + "ay";
                })
                .collect(Collectors.joining(" "));
    }
}
