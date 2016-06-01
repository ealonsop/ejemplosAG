/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pFuncion;

import genetica.*;
/**
 *
 * @author ealonso
 */
public class Funcion implements GeneticAlgorithm {
    public static int initPop;

    public Funcion(int initPop  ) {
        this.initPop = initPop;
    }

    public Population populate() {
        Population p;
        Individuo L;
        int x;
        p = new Population();
        for ( int i = 0; i < initPop; i++ ) {
            x = randInt(0,5000);
            L = new Individuo(x);
            p.addChromosome(L);
        }
        return p;
    }
    
    int randInt( int min, int max ) {
        return min + (int)Math.floor( Math.random() * (max-min+1) );
    }

}
