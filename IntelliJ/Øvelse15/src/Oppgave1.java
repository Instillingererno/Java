
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.awt.*;

public class Oppgave1 extends GLCanvas implements GLEventListener {
    private final static String TITTEL = "Øvelse 15... Nyt utsikten og opplev en spennende og innholdsrik verden";
    private final static int CANVAS_WIDTH = 2000;
    private final static int CANVAS_HEIGHT = 1500;
    private GL2 gl;
    private GLU glu;

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // BODY PARTS
    private double[][] head = {
            {-1,0,-1},
            {1,0,-1},
            {1,0,1},
            {-1,0,1},
            {-1,2,-1},
            {1,2,-1},
            {1,2,1},
            {-1,2,1}
    };

    private double[][] body = {
            {-2,0,-1},
            {2,0,-1},
            {2,0,1},
            {-2,0,1},
            {-2,4,-1},
            {2,4,-1},
            {2,4,1},
            {-2,4,1}
    };

    private double[][] armleft = {
            {-1.5,-4.5,-1},
            {0,-4.5,-1},
            {0,-4.5,1},
            {-1.5,-4.5,1},
            {-1.5,-0.2,-1},
            {0,-0.2,-1},
            {0,-0.2,1},
            {-1.5,-0.2,1}
    };

    private double[][] armright = {
            {1.5,-4.5,-1},
            {0,-4.5,-1},
            {0,-4.5,1},
            {1.5,-4.5,1},
            {1.5,-0.2,-1},
            {0,-0.2,-1},
            {0,-0.2,1},
            {1.5,-0.2,1}
    };

    private double[][] legleft = {
            {-2,-4.5,-1},
            {0,-4.5,-1},
            {0,-4.5,1},
            {-2,-4.5,1},
            {-2,0,-1},
            {0,0,-1},
            {0,0,1},
            {-2,0,1}
    };

    private double[][] legright = {
            {2,-4,-1},
            {0,-4,-1},
            {0,-4,1},
            {2,-4,1},
            {2,0,-1},
            {0,0,-1},
            {0,0,1},
            {2,0,1}
    };


    //CAMERA

    private double[] position = {0,0,0};
    private float direction = 0;
    private boolean follow = false;
    private long startTime;
    private float time;

    private void setFollow() {
        follow = true;
    }
    private void setOrbit() {
        follow = false;
    }
    private void cameraDraw() {
        int distance = 25;
        time = (time = System.currentTimeMillis() - startTime) / 1000;
        if(follow) {
            glu.gluLookAt(position[0] + Math.cos(direction*3.1415/180 + 1.5708)*distance, 10, (position[2] - Math.sin(direction*3.1415/180+1.5708)*distance),
                            position[0], 4, position[2],
                            0,1,0);
        } else {
            glu.gluLookAt(position[0] + distance * Math.cos(time/2), 10, position[2] + distance * Math.sin(time/2),
                            position[0],4,position[2],
                            0,1,0);
        }
    }

    private Oppgave1() {
        this.addGLEventListener(this);
        this.addKeyListener(new keyPressedAction());
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
    private void drawSide(int a, int b, int c, int d, double[][] i) {
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex3dv(i[a],0);
        gl.glVertex3dv(i[b],0);
        gl.glVertex3dv(i[c],0);
        gl.glVertex3dv(i[d],0);
        gl.glEnd();
    }
    private void drawRect(double[][] i) {
        drawSide(0,1,2,3 , i); //Side 1
        drawSide(1,2,6,5 , i); //Side 4
        drawSide(2,3,7,6 , i); //Side 5
        drawSide(3,0,4,7 , i); //Side 6
        drawSide(4,5,1,0 , i); //Side 3
        drawSide(5,6,7,4 , i); //Side 2
    }


    private float multiplier = 1;
    private boolean animate = false;
    private boolean isRotating = false;
    private float rotateDirection = 1;
    public void display(GLAutoDrawable drawable) {
        float rotSpeed = 2;
        double walkSpeed = 0.2;
        float maxRot = 60;
        float rotateAngle;
        gl.glClearColor(0.0f,0.0f,0.0f,0.0f);
        gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_DEPTH_BUFFER_BIT | GL2.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
         cameraDraw();
         if(animate) {
             rotateAngle = (time * rotSpeed) * multiplier;
             position[2] += (Math.cos(direction*3.1415/180) * walkSpeed) * multiplier;
             position[0] += (Math.sin(direction*3.1415/180) * walkSpeed) * multiplier;
         } else {
             rotateAngle = 0;
         }
         if(isRotating) {
             direction += rotSpeed * rotateDirection;
         }


         //Tegn gulv
         gl.glColor3f(0,0.7f,0);
         gl.glBegin(GL2.GL_POLYGON);
         gl.glVertex3d(50,-4,-50);
         gl.glVertex3d(-50,-4,-50);
         gl.glVertex3d(-50,-4,50);
         gl.glVertex3d(50,-4,50);
         gl.glEnd();


         gl.glTranslated(position[0], position[1], position[2]);
         gl.glRotated(direction,0,1,0);
         gl.glColor3f(0,0,0.6f);
         //Tegn kroppen
         drawRect(body);

         //Tegn hode
         gl.glPushMatrix();
            gl.glTranslated(0,4,0);
            gl.glColor3f(0.8f,0.8f,0.8f);
            drawRect(head);
            //Tegn venstre armen
        gl.glColor3f(0.8f,0.8f,0.8f);
        gl.glPushMatrix();
        gl.glTranslated(-2,0,0);
                gl.glRotated(-(Math.sin(rotateAngle)*maxRot),1,0,0);
                drawRect(armleft);
            gl.glPopMatrix();
            //Tegn høyre armen
            gl.glPushMatrix();
                gl.glTranslated(2,0,0);
                gl.glRotated(Math.sin(rotateAngle)*maxRot,1,0,0);
                drawRect(armright);
            gl.glPopMatrix();
        gl.glPopMatrix();

        //Tegn venstre foten
        gl.glColor3f(0.2f,0.2f,0.7f);
        gl.glPushMatrix();
            gl.glRotated(Math.sin(rotateAngle)*maxRot,1,0,0);
            drawRect(legleft);
        gl.glPopMatrix();

        //Tegn høyre foten
        gl.glColor3f(0.2f,0.2f,0.5f);
        gl.glPushMatrix();
            gl.glRotated(-(Math.sin(rotateAngle)*maxRot),1,0,0);
            drawRect(legright);
        gl.glPopMatrix();

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        gl = drawable.getGL().getGL2();
        height = (height == 0) ? 1 : height;
        float aspect = (float) width / height;

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45, aspect, 0.1, 500);

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

    private class keyPressedAction extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case 38: //Up
                    animate = true;
                    multiplier = 1;
                    break;
                case 37: //Left
                    isRotating = true;
                    rotateDirection = 1;
                    break;
                case 40: //Down
                    animate = true;
                    multiplier = -1;
                    break;
                case 39: //Right
                    isRotating = true;
                    rotateDirection = -1;
                    break;
            }
        }
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()) {
                case 38: //Up
                    animate = false;
                    break;
                case 37: //Left
                    isRotating = false;
                    break;
                case 40: //Down
                    animate = false;
                    break;
                case 39: //Right
                    isRotating = false;
                    break;
            }
        }
        public void keyTyped(KeyEvent e) {
            switch(e.getKeyChar()) {
                case 'v':
                    if(follow) {
                        setOrbit();
                    } else {
                        setFollow();
                    }
                    break;
            }
        }
    }
}
