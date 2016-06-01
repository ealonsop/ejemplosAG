/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pFuncion;

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
                 // poblacion
                new Funcion(10) );

        GA.setMaxIter(5000);
        GA.setCrossOverRate(0.8f);
        GA.setMutationRate(0.1f);
        GA.setEliteRate(2/10.0f);
        GA.setMinFitness(2);

        System.out.println("iterations = " +  GA.getMaxIter() );
        System.out.println("crossover rate = " + GA.getCrossOverRate());
        System.out.println("mutation rate = " + GA.getMutationRate());

        System.out.println("elite = " +
                (int)(GA.getEliteRate() *  Funcion.initPop ) );

        GA.solve();
             
    }
    
}
