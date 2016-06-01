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
public class Individuo extends BinaryChromosome {
   
    public Individuo(int x) {
        super(16);
        setNbits(0, 16, x);
    }

    public Individuo( byte bits[] )  {
        super(bits);
    }

    //de Chromosome
    public float calcFitness() {
        float x;
        x = decode();
        fitness = x*x - 2*x + 3;
        return fitness;
    }
    
    //de Chromosome
    public Chromosome duplicate() {
        return new Individuo(bits);
    }

    float decode() {
        return getNint(0,16) / 100.0f;
    }

    public String toString() {
        String res;
        
        res = " fit= "+fitness;
        res += "  x = " + decode();
       return res;
    }
    
}
