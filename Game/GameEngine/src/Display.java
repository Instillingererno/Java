import org.lwjgl.opengl.GLContext;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Display {

    private static long windowID;

    public static void main(String[] args) {

        boolean result = glfwInit();
        System.out.println("Initializing screen: " + result);

        windowID = glfwCreateWindow(640, 480, "Display 1", MemoryUtil.NULL, MemoryUtil.NULL);
        System.out.println("Creating window: " + (windowID == MemoryUtil.NULL));

        glfwMakeContextCurrent(windowID);
        glfwSwapInterval(1);
        glfwShowWindow(windowID);

        ;
    }
}
