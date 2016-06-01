/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pKnapsack;

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
                 // poblacion, capacidad, nobjetos, maxpeso, maxvalor
                new KnapSack( 16, 30, 100, 5, 10 ) );

        GA.setMaxIter(1000000);
        GA.setCrossOverRate(1f);
        GA.setMutationRate(0.1f);
        GA.setEliteRate(8/16.0f);
        GA.setMinFitness(-1130);

        System.out.println("iterations = " +  GA.getMaxIter() );

        System.out.println("crossover rate = " + GA.getCrossOverRate());
        System.out.println("mutation rate = " + GA.getMutationRate());
        System.out.println("elite = " +
                (int)(GA.getEliteRate() *  KnapSack.initPop ) );

        GA.solveInNewThread();
        
/*        JavaRenderer.GA = PAG.GA;

        JavaRenderer apl=new JavaRenderer();
        apl.setVisible(true);*/
        
    }
    
}
