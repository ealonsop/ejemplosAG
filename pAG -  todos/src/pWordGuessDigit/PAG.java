/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pWordGuessDigit;

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

       GA = new GeneticAlgorithmSolver( new WordGuess("01234567899876543210") );

       GA.setMaxIter(1000000);
       GA.setCrossOverRate(1);
       GA.setMutationRate(0.1f);
       GA.setEliteRate(16/32f);
       GA.setMinFitness(0);
       
       new consola(GA);
       
       GA.solveInNewThread();
       
       
/*       System.out.println("Mejor solucion: " + 
       GA.getBestChromosome() );*/

    }
    
}
