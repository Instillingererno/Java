import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

interface Testing {
    void run(double[] a);
}

public class Test1 extends GLCanvas implements GLEventListener {
    // constants
    private static String TITLE = "Dette er en test";
    private static final int CANVAS_WIDTH = 2000;
    private static final int CANVAS_HEIGHT = 1500;

    // Setup OpenGL Graphics Renderer
    private GLU glu;

    /** Constructor to setup the GUI for this Component */
    public Test1() {
        this.addGLEventListener(this);
    }

// ------- Implement methods declared in GLEventListener (init, reshape, display, dispose)

    /**
     * Called immediately after the OpenGL context is initialized. Can be used
     * to perform one-time initializations. Run only once.
     */
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2(); // get the OpenGL graphics context
        glu = new GLU();
        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
    }

    /**
     * Handler for window re-size event. Also called when the glAutoDrawable is
     * first set to visible
     */
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        if(height == 0) height = 1; //prevent divide by zero
        float aspect = (float)width / height;

        //Set the view port (display area) to cover the entire window
        //gl.glViewport(0,0, width, height);

        //Setup perspective projection, with the aspect ratio matches viewport
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0, aspect, 0.1, 100.0); //fov y, aspect, z near, z far

        //Enable the model-view transform
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //reset
    }

    /**
     * Called by OpenGL for drawing
     */
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        // ------ Render stuff ---------
        //triangle
        gl.glTranslated(0.0,0.0,-20.0);
        gl.glColor3f(1.0f,0.0f,0.0f);

        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3d(1.0, 1.0, 0.0);
        gl.glVertex3d(-1.0, 1.0, 0.0);
        gl.glVertex3d(0.0, 0.0, 0.0);
        gl.glEnd();
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public static void main(String[] args) {
        GLCanvas canvas = new Test1();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        final JFrame frame = new JFrame();
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(TITLE);
        frame.pack();
        frame.setVisible(true);
    }
}
