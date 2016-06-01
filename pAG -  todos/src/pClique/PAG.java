/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pClique;

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
                 // poblacion, nro de vertices, probabilidad de arco
//                new Clique( 16, 40, 1f ) );
                new Clique( 16, 40, 0.7f ) );

        GA.setMaxIter(500000);
        GA.setCrossOverRate(0.8f);
        GA.setMutationRate(0.1f);
        GA.setEliteRate(8/16.0f);
        GA.setMinFitness(-40);


        GA.solveInNewThread();
        
        JavaRenderer.GA = PAG.GA;

        JavaRenderer apl=new JavaRenderer();
        apl.setVisible(true);
        
    }
    
}
