/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pTSP;

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
        
                                        //poblacion
        GA = new GeneticAlgorithmSolver( new TSP(5) );
        GA.setMaxIter(500000);
        GA.setCrossOverRate(0);
        //interpretado como la cantidad de veces que
        //intercambiará un par de ciudades 
        GA.setMutationRate(50.0f);
        GA.setEliteRate(1.0f/TSP.initPop);
        GA.setMinFitness(6660);

        System.out.println("iterations = " +  GA.getMaxIter() );

        System.out.println("elite = " + 
                (int)(GA.getEliteRate() *  TSP.initPop ) );
        
        GA.solveInNewThread();


        JavaRenderer.GA = PAG.GA;

        JavaRenderer apl=new JavaRenderer();
        apl.setVisible(true);

    }
    
}
