/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pWordGuess;

import genetica.GeneticAlgorithmSolver;

/**
 *
 * @author laboratorio_computo
 */
public class PAG {

    public static GeneticAlgorithmSolver GA;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

       GA = new GeneticAlgorithmSolver( new WordGuess("EDUARDOFRANCISCOALONSOPEREZ") );
//       GA = new GeneticAlgorithmSolver( new WordGuess("ALGORITMOGENETICO") );

       // 26^17 = 1133827315385150725554176
       GA.setMaxIter(1000000);
       GA.setCrossOverRate(1f);
       GA.setMutationRate(0.05f);
       GA.setEliteRate(16/32f);
       GA.setMinFitness(0);
       
       new consola(GA);
       
       GA.solveInNewThread();
       
       
/*       System.out.println("Mejor solucion: " + 
       GA.getBestChromosome() );*/
    }
    
}
