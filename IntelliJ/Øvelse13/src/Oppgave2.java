import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import javax.swing.*;
import java.awt.*;

public class Oppgave2 extends GLCanvas implements GLEventListener {
    private final static String TITTEL = "Ikke les dette, sphee";
    private final static int CANVAS_WIDTH = 2000;
    private final static int CANVAS_HEIGHT = 1500;
    private GL2 gl;
    private GLU glu;
    private GLUT glut;

    private Oppgave2() {
        this.addGLEventListener(this);
    }

    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        glu = new GLU();
        gl.glClearColor(0f,0f,0f,0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glPointSize(10);
        glut = new GLUT();

    }

    public void dispose(GLAutoDrawable drawable) {

    }

    private final static double[][] cube = {
            {0,0,0},
            {1,0,0},
            {1,1,0},
            {0,1,0},
            {0,1,1},
            {1,1,1},
            {1,0,1},
            {0,0,1}
    };
    private void tegnKube2a(double[][] input) {
        gl.glBegin(GL.GL_LINE_LOOP);
        for(double[] i : input) {
            gl.glVertex3dv(i,0);
        }
        gl.glEnd();
    }
    private void tegnKube2b() {
        glut.glutWireCube(1f);
    }

    public void display(GLAutoDrawable drawable) {
        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_DEPTH_BUFFER_BIT | GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glTranslated (0.0, 0.0, -10.0);
        gl.glColor3f(1.0f, 1.0f, 1.0f);

        //Kube med LINE_LOOP
        gl.glTranslated(-2.5,-0.5,-0.5);
        tegnKube2a(cube);

        //Kube med glutWireCube
        gl.glTranslated(5.0,0.0,-1.0);
        tegnKube2b();
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


        GLCanvas canvas = new Oppgave2();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        final JFrame frame = new JFrame();
        frame.getContentPane().add(canvas);
        frame.setTitle(TITTEL);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
