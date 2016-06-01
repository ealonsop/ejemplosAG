/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pHighestPeak;
import pFuncion.*;
import genetica.*;

/**
 *
 * @author laboratorio_computo
 */
public class Individuo extends BinaryChromosome {
    
    //max imagenes de 1024x1024 (10 bits para x y 10 para y)
    public Individuo( int x, int y ) {
        super(20);
        encode(x,y);
    }
    
    //siempre se agrega con este codigo
    public Individuo(byte bits[]) {
        super( bits );
    }    

    //siempre se agrega con este codigo
    public Chromosome duplicate() {
        return new Individuo(bits);
    }    
    
    public void encode(int x, int y) {
        setNbits( 0, 10, x );
        setNbits( 1, 10, y );
    }
    
    public int decodeX() {
        return getNint( 0, 10 );
    }

    public int decodeY() {
        return getNint( 1, 10 );
    }
    
    public float calcFitness() {
        if ( Mapa.valido(this) )
            fitness = - Mapa.altura(this);
        else
            fitness = 0;
        return fitness;
    }
    
    public String toString() {
        int x = decodeX();
        int y = decodeY();
        return fitness  + " : " + x + " , " + y;
    }
}

