/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pBPP1;

import genetica.*;


/**
 *
 * @author ealonso
 */
public class PAG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int i;
        GeneticAlgorithmSolver GA;
        
        GA = new GeneticAlgorithmSolver( new BPP1(5, 10, 50) );
        
//        GA.setMaxIter(1000000);
        GA.setMaxIter(500000);
        GA.setCrossOverRate(0);

        //sin usar.
        //siempre intercambia 1 bloque de dos contenedores
        GA.setMutationRate(1);
        
        GA.setEliteRate(1/5.0f);
//        GA.setMinFitness(12840.0f);
        GA.setMinFitness(0);

        System.out.println("iterations = " +  GA.getMaxIter() );

        System.out.println("elite = " + 
                (int)(GA.getEliteRate() *  BPP1.initPop ) );
        
        GA.solve();
        
    }
    
}
