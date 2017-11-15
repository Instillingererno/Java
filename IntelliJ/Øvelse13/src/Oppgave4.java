import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

import javax.swing.*;
import java.awt.*;

public class Oppgave4 extends GLCanvas implements GLEventListener {
    private final static String TITTEL = "Ikke les dette, sphee";
    private final static int CANVAS_WIDTH = 2000;
    private final static int CANVAS_HEIGHT = 1500;
    private GL2 gl;
    private GLU glu;
    private GLUT glut;
    private double[] camera = {0,5,-15};
    private long timeAtStart = 0;
    private double time = 0;

    private Oppgave4() {
        this.addGLEventListener(this);
    }

    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        glu = new GLU();
        gl.glClearColor(0f,0f,0f,0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glPointSize(20);
        glut = new GLUT();
        timeAtStart = System.currentTimeMillis();
    }

    public void dispose(GLAutoDrawable drawable) {

    }
    private final static int distance = 10;
    public void display(GLAutoDrawable drawable) {
        time = (time = System.currentTimeMillis() - timeAtStart) / 1000;
        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_DEPTH_BUFFER_BIT | GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        glu.gluLookAt(distance * Math.cos(time),5,-(distance * Math.sin(time)),
                0,0,0,
                0,1,0);

        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glTranslated(0,0,0);
        glut.glutWireCube(2);

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        gl = drawable.getGL().getGL2();
        height = (height == 0) ? 1 : height;
        float aspect = (float) width / height;

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45, aspect, 0.1, 100);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public static void main(String[] args) {


        GLCanvas canvas = new Oppgave4();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        final JFrame frame = new JFrame();
        frame.getContentPane().add(canvas);
        frame.setTitle(TITTEL);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        final FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();

        frame.setVisible(true);
    }
}
