/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pCirculos;

/**
 *
 * @author ealonso
 */

import javax.media.opengl.*;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.opengl.GLCanvas;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.Color;
import com.sun.opengl.util.*;

import java.util.List;

import genetica.*;

public class JavaRenderer extends JFrame implements
        GLEventListener, KeyListener

{
    Robot R;
    GLU glu;
    GL gl;
    GLCanvas canvas;
    private FPSAnimator animator;
    private GLCapabilities caps;

    float v_x, v_y, v_z;
    int gridX, gridY;
    

    public static GeneticAlgorithmSolver GA;
    public Circulo best;

    float minX, maxX, minY, maxY;
    float fac;

    public JavaRenderer()
    {
        super();
    
        this.setTitle("Aplicacion Base");
        this.setSize(500, 400);
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        caps = new GLCapabilities();
        caps.setDoubleBuffered(true);
        canvas = new GLCanvas(caps);
        canvas.addGLEventListener(this);
        canvas.addKeyListener(this);

        add(canvas);
        animator = new FPSAnimator(canvas, 120);
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        animator.start();

        gridX = 30;
        gridY = 30;
        v_x = -gridX/2;
        v_y = -gridY/2;
        v_z = -40;


        minX = minY = 0;
        maxX = maxY = Circulos.maxxy;
        float dx, dy;
        dx = (maxX - minX);
        dy = (maxY - minY);
        if ( dx > dy )
            fac = gridX / dx;
        else
            fac = gridX / dy;

        best = new Circulo(0,0,0);

    }

    public void display(GLAutoDrawable gLDrawable)
    {
        int i, j;

        gl = gLDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
        
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    	
        gl.glTranslatef( v_x, v_y, v_z );

        gl.glColor3f(0.2f,0.2f,0.2f);

        //lineas horizontales
        gl.glBegin( GL.GL_LINES );
        for ( i=0; i<= gridY; i++ ) {
            gl.glVertex3f(0,i,0);
            gl.glVertex3f(gridX,i,0);
        }
        gl.glEnd();

        //lineas verticales
        gl.glBegin( GL.GL_LINES );
        for ( i=0; i<= gridX; i++ ) {
            gl.glVertex3f(i,0,0);
            gl.glVertex3f(i,gridY,0);
        }
        gl.glEnd();

        gl.glClear(GL.GL_DEPTH_BUFFER_BIT );

        Circulo c;

        gl.glPointSize(8);
        gl.glColor3f(1,1,1);
        for ( i = 0; i < Circulos.N; i++ ) {
            c = Circulos.circulos[i];
            gl.glColor3f(1,1,1);
//            gl.glBegin( GL.GL_POINTS );
//                gl.glVertex3f( (c.x-minX)*fac, (c.y-minY)*fac,0);
//            gl.glEnd();
            gl.glColor3f(1,1,1);
            dibujaCirculo((c.x-minX)*fac,(c.y-minY)*fac,c.r*fac);
        }

        if ( GA.getGt() != null ) {
            Individuo L;
            L = (Individuo)GA.getBestChromosome();
            L.decode(best);
            c = best;
            gl.glColor3f(1,1,0);
            dibujaCirculo((c.x-minX)*fac,(c.y-minY)*fac,c.r*fac);
    
        }

        this.setTitle(GA.getCurIter()+"");
        
        gl.glFlush();
    }

    public void dibujaCirculo( float x, float y, float r ) {
        float px, py, ang, a;
        int nl;

        nl = 30;
        ang = (float)Math.PI*2 / nl;
        a = ang;
        gl.glBegin( GL.GL_LINE_LOOP );
        for ( int i = 0; i < nl; i++ ) {
            px = x + r * (float)Math.cos(a);
            py = y + r * (float)Math.sin(a);
            gl.glVertex3f(px,py,0);
            a += ang;
        }
        gl.glEnd();
    }

    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged)
    {
        display(gLDrawable);
    }

    public void init(GLAutoDrawable gLDrawable)
    {
       try {
        R = new Robot();
       }
       catch (Exception e)
       {}
       canvas.requestFocus();

       gl = gLDrawable.getGL();
       glu = new GLU();
       gl.glClearColor(0,0,0,0);
       gl.glEnable(GL.GL_DEPTH_TEST);
    }

    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height)
    {
        gl = gLDrawable.getGL();

        gl.glViewport(0,0,width,height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0,(double)width/height,  0.1, 1000);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

    }
    public void reshape(GLAutoDrawable gLDrawable,int width, int height)
    {
        reshape(gLDrawable,0,0,width,height);
    }

    public void keyTyped(KeyEvent key)
    {
    }

    public void keyPressed(KeyEvent key)
    {
        switch (key.getKeyCode()) {
          case KeyEvent.VK_ESCAPE:
              System.exit(0);
            break;
            case KeyEvent.VK_F1:
                GA.solveInNewThread();
              break;
            case KeyEvent.VK_PAGE_UP:
              v_z = v_z - 1;
              break;
            case KeyEvent.VK_F2:
                for ( int i = 0; i < Circulos.N; i++ ) {
                    System.out.println("x = " + Circulos.circulos[i].x + ";");
                    System.out.println("y = " + Circulos.circulos[i].y + ";");
                    System.out.println("r = " + Circulos.circulos[i].r + ";");
                    System.out.println("circulos["+i+"] = new Circulo(x,y,r);");
                }
                break;
            case KeyEvent.VK_PAGE_DOWN:
              v_z = v_z + 1;
              break;
            case KeyEvent.VK_RIGHT:
              break;
            case KeyEvent.VK_LEFT:
              break;
            case KeyEvent.VK_UP:
              break;
            case KeyEvent.VK_DOWN:
               break;
              
            case KeyEvent.VK_A:
              String ayuda =
              "F1: Cambia de punto"+"\n"+
              "ESC: Salir"+"\n";
              JOptionPane.showMessageDialog(null, ayuda, "Ayuda", JOptionPane.INFORMATION_MESSAGE);
              break;
          default:
            break;
        }
    }

    public void keyReleased(KeyEvent key)
    {
    }
   
}
