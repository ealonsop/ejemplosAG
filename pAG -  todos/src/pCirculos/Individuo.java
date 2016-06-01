/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pCirculos;

import genetica.*;

/**
 *
 * @author ealonso
 */
public class Individuo extends BinaryChromosome {
    private static Circulo dum = new Circulo(0,0,0);
    
    public Individuo(int x, int y, int r) {
        super(24);
        encode(x,y,r);
        fitness = 0;
    }

    public Individuo(byte bits[]) {
        super( bits );
    }

    //de BinaryChromosome
    public void encode( Object oc ) {
        if ( oc != null ) {
            Circulo c = (Circulo)oc;
            encode( c.x, c.y, c.r );
        }
    }

    //de BinaryChromosome
    public void decode( Object oc ) {
        Circulo c = (Circulo)oc;
        c.x = getX();
        c.y = getY();
        c.r = getR();
    }

    //de Chromosome
    public float calcFitness() {
        decode(dum);
        if ( ! Circulos.valid(dum) ) {
            fitness = 0;
            setR(0);
        }
        else {
            fitness = -getR();
        }
        return fitness;
    }
    
    //de Chromosome
    public Chromosome duplicate() {
        return new Individuo(bits);
    }

    public void encode( int x, int y, int r ) {
        setX(x);
        setY(y);
        setR(r);
    }
    
    public void setX(int v) {
        set8bits(0,v);
    }

    public void setY(int v) {
        set8bits(1,v);
    }

    public void setR(int v) {
        set8bits(2,v);
    }
    
    public int getX() {
        return get8int(0);
    }

    public int getY() {
        return get8int(1);
    }

    public int getR() {
        return get8int(2);
    }

    public String toString() {
        String res;
        
        res = " fit= "+fitness;
        decode(dum);
        res += " " + dum;
       return res;
    }
    
}
