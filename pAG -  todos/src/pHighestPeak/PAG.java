/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pHighestPeak;

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

       GA = new GeneticAlgorithmSolver( new Mapa() );
       
       GA.setMaxIter(500000);
       GA.setCrossOverRate(0.8f);
       GA.setMutationRate(0.1f);
       GA.setEliteRate(2/10f);
       GA.setMinFitness(-255f);
       
       GA.solve();
       
       System.out.println("Mejor solucion: " + 
       GA.getBestChromosome() );

    }
    
}
