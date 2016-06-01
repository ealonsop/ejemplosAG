/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pClique;

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
    

    float v0[];
    float v1[];
    float angulo, distancia;
    
    boolean mostrarSolucion;
    
    public static GeneticAlgorithmSolver GA;

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


        v0 = new float[2];
        v1 = new float[2];
        angulo = (float)(Math.PI*2 / Clique.N);
        distancia = gridX/2.0f;

        mostrarSolucion = true;
        
    }
    
    public void calc_pto( int v, float p[]  ) {
        p[0] = gridX/2.0f + (float)(distancia * Math.cos(v*angulo));
        p[1] = gridX/2.0f + (float)(distancia * Math.sin(v*angulo));
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


        gl.glPointSize(8);
        gl.glColor3f(1,1,1);
        for ( i = 0; i < Clique.N; i++ ) {
            calc_pto( i, v0);
            gl.glBegin( GL.GL_POINTS );
                gl.glVertex3f(v0[0],v0[1],0);
            gl.glEnd();
        }

        gl.glColor3f(0,0,1);
        for ( i = 0; i < Clique.N; i++ ) {
           calc_pto( i, v0);
           for ( j = i+1; j < Clique.N; j++ ) {
               if ( Clique.grafo[i][j] ) {
                   calc_pto( j, v1 );
                   gl.glBegin( GL.GL_LINES );
                      gl.glVertex3f(v0[0],v0[1],0);
                      gl.glVertex3f(v1[0],v1[1],0);
                   gl.glEnd();
               }
           }
        }

        gl.glClear(GL.GL_DEPTH_BUFFER_BIT );
        gl.glPointSize(11);
        
        if ( GA.getGt() != null && mostrarSolucion ) {
            gl.glColor3f(1,1,0);
            Individuo L;
            L = (Individuo)GA.getBestChromosome();
            for ( i = 0; i < Clique.N; i++ ) {
                if ( L.getBit(i) == 1 ) {
                    calc_pto( i, v0);
                    for ( j = i+1; j < Clique.N; j++ ) {
                        if ( L.getBit(j) == 1 )
                            if ( Clique.grafo[i][j] == true ) {
                               calc_pto( j, v1 );
                               gl.glBegin( GL.GL_POINTS );
                                    gl.glVertex3f(v0[0],v0[1],0);
                                    gl.glVertex3f(v1[0],v1[1],0);
                               gl.glEnd();
                               gl.glBegin( GL.GL_LINES );
                                    gl.glVertex3f(v0[0],v0[1],0);
                                    gl.glVertex3f(v1[0],v1[1],0);
                               gl.glEnd();
                            }
                    }
                }   
            }
            this.setTitle(GA.getCurIter()+"");
        }
        gl.glFlush();
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
            case KeyEvent.VK_F2:
                mostrarSolucion = !mostrarSolucion;
              break;
                
            case KeyEvent.VK_PAGE_UP:
              v_z = v_z - 1;
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
