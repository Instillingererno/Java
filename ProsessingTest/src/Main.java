import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main extends PApplet {
    public static void main(String[] args) {
        String[] processingArgs = {"MySketch"};
        Main sketch = new Main();
        PApplet.runSketch(processingArgs, sketch);
    }

    ArrayList<Float> position = arrayListFromStuff(1.34f,1f,34f); // x, y, z

    float a = 10f;
    float b = 28f;
    float c = 8f/3f;

    ArrayList<Delta> delta = arrayListFromStuff(
            (x,y,z) -> a * (y - x), //dx
            (x,y,z) -> x * (b - z) - y, //dy
            (x,y,z) -> x * y - c * z //dz
    );

    public void settings() {
        size(800, 600, P3D);
        //background(0);
    }

    public void draw() {
        float dt = 0.01f;
        float x = position.get(0), y = position.get(1), z = position.get(2);

        IntStream.range(0, 3).forEachOrdered(
                i -> {
                    float temp = position.get(i);
                    position.set(i, (temp + delta.get(i).calculate(x,y,z)) * dt);
                }
        );
        System.out.printf("x: %f\ty: %f\tz: %f\n", x, y, z);
        stroke(0);
        translate(width/2, height/2);
        point(x,y,z);
    }




    interface Delta {
        float calculate(float x, float y, float z);
    }

    @SafeVarargs
    private static <T> ArrayList<T> arrayListFromStuff(T... stuff) {
        return new ArrayList<>(Arrays.asList(stuff));
    }
    @SafeVarargs
    private static <T> void referenceFromList(List<T> list, T... stuff) {
        for(int i = 0; i < (list.size() < stuff.length ? list.size() : stuff.length); i++) {
            stuff[i] = list.get(i);
        }
    }
}
