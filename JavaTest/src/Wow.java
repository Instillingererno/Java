import java.util.Arrays;

public class Wow {
    public static void main(String[] args) {
        var kek = new String[]{"Dette", "er", "en", "test"};
        var tasVekk = new String[]{"er", "en"};

        var res = Arrays.stream(kek).filter(e -> {for(String i : tasVekk) if (e.equals(i)) return false; return true;}).toArray();

        System.out.println(Arrays.toString(res));
    }
}