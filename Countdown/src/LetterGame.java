import java.io.BufferedReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class LetterGame {
    private ArrayList<String> letters9 = new ArrayList<>(1000);
    private ArrayList<String> letters8 = new ArrayList<>(1000);
    private ArrayList<String> letters7 = new ArrayList<>(1000);
    private ArrayList<String> letters6 = new ArrayList<>(1000);
    private ArrayList<String> letters5 = new ArrayList<>(1000);
    private ArrayList<String> letters4 = new ArrayList<>(1000);
    private String[] files = {"center.dic", "centre.dic", "colour.dic", "eng_com.dic", "ise.dic", "ize.dic", "labelled.dic", "yse.dic"};
    //private String[] files = {"test.dic"};

    public LetterGame() {
        init();
    }

    public static void main(String[] args) {
        LetterGame test = new LetterGame();
        test.init();
        System.out.println(test.answer("eprdztwea", 20));
    }

    public ArrayList<String> getLetters(int nummer) {
        switch (nummer) {
            case 9:
                return letters9;
            case 8:
                return letters8;
            case 7:
                return letters7;
            case 6:
                return letters6;
            case 5:
                return letters5;
            case 4:
                return letters4;
            default:
                return null;
        }
    }

    public void init() {
        for(String i : files) {
            Path path = Paths.get("src/Dictionary/"+i);
            boolean fortsett = true;
            try {
                BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.ISO_8859_1);
                String line = null;
                while((line = reader.readLine()) != null && fortsett) {
                    switch(line.length()) {
                        case 4:
                            letters4.add(line);
                            break;
                        case 5:
                            letters5.add(line);
                            break;
                        case 6:
                            letters6.add(line);
                            break;
                        case 7:
                            letters7.add(line);
                            break;
                        case 8:
                            letters8.add(line);
                            break;
                        case 9:
                            letters9.add(line);
                            break;
                        default:
                            if(line.length() > 9) fortsett = false;
                            break;
                    }
                }
                reader.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String answer(String input, int words) {
        long startTime = System.currentTimeMillis();
        ArrayList<String> output = new ArrayList<>();
        for(int i = 9; i > 3; i--) {
            output.add(i+" letter words: \n");
            for(String j : getLetters(i)) {
                String[] enkelt = input.split("");
                String[] ord = j.split("");
                boolean fortsett = true;
                for(int k = 0; k < ord.length; k++) if(ord[k] != null) {
                    for(int m = 0; m < enkelt.length; m++) if(enkelt[m] != null) {
                        if(ord[k].equals(enkelt[m])) {
                            enkelt[m] = "";
                            ord[k] = "";
                            break;
                        }
                    }
                }
                for(String k : ord) if(!k.equals("")) fortsett = false;
                if(fortsett) output.add(j + "\n");
            }
            if(output.size() > words) break;
        }
        long endTime = System.currentTimeMillis();
        output.add((endTime-startTime) + " ms");
        return output.toString();
    }
}
