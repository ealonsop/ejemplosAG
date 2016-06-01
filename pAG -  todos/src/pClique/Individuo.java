/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pClique;

import genetica.*;
import java.util.Arrays;

/**
 *
 * @author ealonso
 */
public class Individuo extends BinaryChromosome {
    
    //nb: numero de vertices del grafo
    public Individuo(int nb) {
        super(nb);
    }

    public Individuo(byte bits[]) {
        super( bits );
    }

    //de Chromosome
    public float calcFitness() {
        int i;
        fitness = 0;
        //fitness: cantidad de vertices del sub-grafo
        for ( i = 0; i < bits.length; i++ )
            fitness += bits[i];
        
        //validar si realmente es un clique
        if ( Clique.isClique(bits) ) {
            fitness = -fitness;
        }
        else
            fitness = 0;
        return fitness;
    }
    
    //de Chromosome
    public Chromosome duplicate() {
        return new Individuo(bits);
    }

    public String toString() {
        String res;
        
        res = " fit= "+fitness + " ";
        res += " " + Arrays.toString(bits);
       return res;
    }
    
}
