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
public class Individuo extends BinaryChromosome {
    public int peso;
    
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
        peso = 0;
        for ( i = 0; i < KnapSack.nobjetos; i++ )
            if ( bits[i] == 1 ) {
                fitness += KnapSack.objetos[i].valor;
                peso += KnapSack.objetos[i].peso;
            }
        //validar
        if ( peso > KnapSack.capacidad ) {
            fitness = 0;
            //setZero();
        }
        else
            fitness = - fitness;
        return fitness;
    }
    
    public void setZero() {
        for ( int i = 0; i < bits.length; i++ )
            bits[i] = 0;
    }
    
    //de Chromosome
    public Chromosome duplicate() {
        return new Individuo(bits);
    }

    public String toString() {
        String res;
        
        res = " fit= "+fitness + " peso= " + peso+ " ";
        for (int i = 0; i < bits.length; i++ )
            if ( bits[i] == 1 )
                res += KnapSack.objetos[i].nombre + " ";
     //   res += " " + Arrays.toString(bits);
       return res;
    }
    
}
