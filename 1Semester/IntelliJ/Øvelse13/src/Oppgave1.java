import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import javax.swing.*;
import java.awt.*;

public class Oppgave1 extends GLCanvas implements GLEventListener {
    private final static String TITTEL = "Ikke les dette, sphee";
    private final static int CANVAS_WIDTH = 2000;
    private final static int CANVAS_HEIGHT = 1500;
    private GL2 gl;
    private GLU glu;
    private final static double[][] points = {
            {0.0,2.0,0.0}, //P0
            {1.5,1.5,0.0}, //P1
            {2.0,0.0,0.0}, //P2
            {1.5,-1.5,0.0}, //P3
            {0.0,-2.0,0.0}, //P4
            {-1.5,-1.5,0.0}, //P5
            {-2.0,0.0,0.0}, //P6
            {-1.5,1.5,0.0} //P7
    };

    private Oppgave1() {
        this.addGLEventListener(this);
    }

    public void init(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        glu = new GLU();
        gl.glClearColor(0f,0f,0f,0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glEnable(GL2.GL_POINT_SMOOTH);
        gl.glPointSize(20);
    }

    public void dispose(GLAutoDrawable drawable) {

    }

    public void display(GLAutoDrawable drawable) {
        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_DEPTH_BUFFER_BIT | GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glTranslated (0.0, 0.0, -30.0);
        gl.glColor3f(1.0f, 1.0f, 1.0f);

        // GL_POINT
        gl.glTranslated(-10.0,5.0,0.0);
        gl.glBegin(GL2.GL_POINTS);
        for(double[] i : points) {
            gl.glVertex3dv(i, 0);
        }
        gl.glEnd();

        // GL_LINES
        gl.glTranslated(5.0,0.0,0.0);
        gl.glBegin(GL2.GL_LINES);
        for(double[] i : points) {
            gl.glVertex3dv(i, 0);
        }
        gl.glEnd();

        // GL_LINE_STRIP
        gl.glTranslated(5.0,0.0,0.0);
        gl.glBegin(GL2.GL_LINE_STRIP);
        for(double[] i : points) {
            gl.glVertex3dv(i, 0);
        }
        gl.glEnd();

        // GL_LINE_LOOP
        gl.glTranslated(5.0,0.0,0.0);
        gl.glBegin(GL2.GL_LINE_LOOP);
        for(double[] i : points) {
            gl.glVertex3dv(i, 0);
        }
        gl.glEnd();

        // GL_TRIANGLES
        gl.glTranslated(5.0,0.0,0.0);
        gl.glBegin(GL2.GL_TRIANGLES);
        for(double[] i : points) {
            gl.glVertex3dv(i, 0);
        }
        gl.glEnd();

        // NEW LINE
        gl.glTranslated(-10.0,-10.0,0.0);

        // GL_TRIANGLE_STRIP
        gl.glTranslated(-10.0,0.0,0.0);
        gl.glBegin(GL2.GL_TRIANGLE_STRIP);
        for(double[] i : points) {
            gl.glVertex3dv(i, 0);
        }
        gl.glEnd();

        // GL_TRIANGLE_FAN
        gl.glTranslated(5.0,0.0,0.0);
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        for(double[] i : points) {
            gl.glVertex3dv(i, 0);
        }
        gl.glEnd();

        // GL_QUADS
        gl.glTranslated(5.0,0.0,0.0);
        gl.glBegin(GL2.GL_QUADS);
        for(double[] i : points) {
            gl.glVertex3dv(i, 0);
        }
        gl.glEnd();

        // GL_QUAD_STRIP
        gl.glTranslated(5.0,0.0,0.0);
        gl.glBegin(GL2.GL_QUAD_STRIP);
        for(double[] i : points) {
            gl.glVertex3dv(i, 0);
        }
        gl.glEnd();

        // GL_POLYGON
        gl.glTranslated(5.0,0.0,0.0);
        gl.glBegin(GL2.GL_POLYGON);
        for(double[] i : points) {
            gl.glVertex3dv(i, 0);
        }
        gl.glEnd();
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


        GLCanvas canvas = new Oppgave1();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        final JFrame frame = new JFrame();
        frame.getContentPane().add(canvas);
        frame.setTitle(TITTEL);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
