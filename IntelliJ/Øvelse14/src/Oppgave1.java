import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

import javax.swing.*;
import java.awt.*;

public class Oppgave1 extends GLCanvas implements GLEventListener {
    private final static String TITTEL = "Ikke les dette, sphee";
    private final static int CANVAS_WIDTH = 2000;
    private final static int CANVAS_HEIGHT = 1500;
    private GL2 gl;
    private GLU glu;

    long startTime;
    float time;

    static final float cornerPositions[][] = {
            {-1,-1,-1},
            {1,-1,-1},
            {1,-1,1},
            {-1,-1,1},
            {-1,1,-1},
            {1,1,-1},
            {1,1,1},
            {-1,1,1}
    };
    static final float colors[][] = {
            {1,0,0},
            {1,1,0},
            {0,1,0},
            {0,1,1},
            {0,0,1},
            {1,0,1}
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
        gl.glPointSize(20);
        startTime = System.currentTimeMillis();
    }

    public void dispose(GLAutoDrawable drawable) {

    }

    public void drawSide(int a, int b, int c, int d) {
        gl.glColor3fv(colors[a],0);
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3fv(cornerPositions[a],0);
        gl.glVertex3fv(cornerPositions[b],0);
        gl.glVertex3fv(cornerPositions[c],0);
        gl.glVertex3fv(cornerPositions[d],0);
        gl.glEnd();
    }
    public void drawCube() {
        drawSide(0,1,2,3); //Side 1
        drawSide(1,2,6,5); //Side 4
        drawSide(2,3,7,6); //Side 5
        drawSide(3,0,4,7); //Side 6
        drawSide(4,5,1,0); //Side 3
        drawSide(5,6,7,4); //Side 2
    }
    public void display(GLAutoDrawable drawable) {
        time = (time = System.currentTimeMillis() - startTime) / 1000;
        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_DEPTH_BUFFER_BIT | GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        int distance = 20;
        int angle;

        // Viewport1
        gl.glViewport(0,750,1000,750);
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45, (float) 4/3, 0.1, 100);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();
        angle = 10;
        glu.gluLookAt(20 * Math.cos(time),5,-(distance * Math.sin(time)),
                0,0,0,
                0,1,0);
        drawCube();

        // Viewport2
        gl.glViewport(1000,750,1000,750);
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45, (float) 4/3, 0.1, 100);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();
        angle = 20;
        glu.gluLookAt(20 * Math.cos(angle),-5,distance * Math.sin(angle),
                0,0,0,
                0,1,0);
        drawCube();

        // Viewport3
        gl.glViewport(0,0,1000,750);
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45, (float) 4/3, 0.1, 100);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();
        angle = 60;
        glu.gluLookAt(20 * Math.cos(angle),5,distance * Math.sin(angle),
                0,0,0,
                0,1,0);
        drawCube();

        // Viewport4
        gl.glViewport(1000,0,1000,750);
        gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45, (float) 4/3, 0.1, 100);
        gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
        gl.glLoadIdentity();
        angle = -30;
        glu.gluLookAt(20 * Math.cos(angle),5,distance * Math.sin(angle),
                0,0,0,
                0,1,0);
        drawCube();
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

        final FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();

        frame.setVisible(true);
    }
}
