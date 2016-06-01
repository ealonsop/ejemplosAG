package pWordGuess;

//
// A simple Java Console for your application (Swing version)
// Requires Java 1.1.5 or higher
//
// Disclaimer the use of this source is at your own risk. 
//
// Permision to use and distribute into your own applications
//
// RJHM van den Bergh , rvdb@comweb.nl

import genetica.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class consola extends WindowAdapter implements WindowListener, ActionListener
{
	private static JFrame frame;
	private static JLabel word,dif;
        private static Timer timer;
        private static GeneticAlgorithmSolver GA;
        
        
	public consola(GeneticAlgorithmSolver GA)
	{
                this.GA = GA;
		// create all components and add them
		frame=new JFrame("WordGuess");
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize=new Dimension((int)((screenSize.width/3)*2),(int)(screenSize.height/6));
		int x=(int)((screenSize.width-frameSize.width)/2);
		int y=(int)((screenSize.height-frameSize.height)/2);
		frame.setBounds(x,y,frameSize.width,frameSize.height);
		
		word=new JLabel();
                word.setFont(new Font("Monospaced", Font.BOLD, 40 ));
		dif=new JLabel();
                dif.setFont(new Font("Monospaced", Font.BOLD, 40 ));
	
                frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(word,BorderLayout.NORTH);
		frame.getContentPane().add(dif,BorderLayout.CENTER);

                timer = new Timer(50,this);
                timer.start();
                
		frame.setVisible(true);		
		
		frame.addWindowListener(this);	
                
	}
        
	public synchronized void windowClosed(WindowEvent evt)
	{
		this.notifyAll(); // stop all threads
		System.exit(0);
	}		
        
		
	public synchronized void windowClosing(WindowEvent evt)
	{
		frame.setVisible(false); // default behaviour of JFrame	
		frame.dispose();
	}      

	public synchronized void actionPerformed(ActionEvent evt)
	{
            if ( GA.getGt() != null ) {
                String w = ((Individuo)GA.getBestChromosome()).decode();
                String d, wg;
                word.setText( w );
                d = "";
                wg = ((WordGuess)(GA.getGeneticAlgorithm())).word;
                for ( int i = 0; i < wg.length(); i++ )
                   if ( w.charAt(i) != wg.charAt(i) )
                       d += "*";
                   else
                       d += " ";
                dif.setText( d );
                frame.setTitle(GA.getCurIter()+"");
            }
        }
        

}