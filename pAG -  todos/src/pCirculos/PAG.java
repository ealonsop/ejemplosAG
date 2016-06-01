/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pCirculos;

import genetica.*;

/**
 *
 * @author ealonso
 */
public class PAG {

     public static GeneticAlgorithmSolver GA;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here


       GA = new GeneticAlgorithmSolver( 
                 // poblacion, circulos fijos, min radio, maxradio, maxXY
                new Circulos(20, 15, 8, 25, 255 ) );

        GA.setMaxIter(1000000);
        GA.setCrossOverRate(0.7f);
        GA.setMutationRate(0.1f);
        GA.setEliteRate(2/20.0f);
        GA.setMinFitness(-44);

        System.out.println("iterations = " +  GA.getMaxIter() );

        System.out.println("crossover rate = " + GA.getCrossOverRate());
        System.out.println("mutation rate = " + GA.getMutationRate());

        System.out.println("elite = " +
                (int)(GA.getEliteRate() *  Circulos.initPop ) );

        GA.solveInNewThread();
        
        JavaRenderer.GA = PAG.GA;

        JavaRenderer apl=new JavaRenderer();
        apl.setVisible(true);
        
    }
    
}
