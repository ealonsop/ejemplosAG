/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pWordGuessDigit;
import genetica.*;

/**
 *
 * @author laboratorio_computo
 */
public class Individuo extends BinaryChromosome {
    
    public Individuo( String w ) {
        super(w.length()*4);
        encode(w);
    }
    
    //siempre se agrega con este codigo
    public Individuo(byte bits[]) {
        super( bits );
    }    

    //siempre se agrega con este codigo
    public Chromosome duplicate() {
        return new Individuo(bits);
    }    
    
    public void encode(String w) {
        int i;
        for ( i = 0; i < w.length(); i++ ) {
            setNbits( i, 4, w.codePointAt(i)-48 );
        }
    }
    
    public String decode() {
        int i;
        int c;
        String res;
        res = "";
        for ( i = 0; i < bits.length/4; i++ ) {
            c = getNint(i,4);
            res = res + Character.toString((char)(c+48));
        }
        return res;
    }

    //distancia Hamming
    public float calcFitness() {
        int i;
        int sum, dif;
        sum = 0;
        String word = decode();
        for ( i = 0; i < WordGuess.word.length(); i++ ) {
            dif = word.codePointAt(i)-WordGuess.word.codePointAt(i);
            if ( dif != 0 )
               sum = sum + 1;
        }
        fitness = sum;
        return fitness;
    }

//    public float calcFitness() {
//        int i;
//        int sum, dif;
//        sum = 0;
//        String word = decode();
//        for ( i = 0; i < WordGuess.word.length(); i++ ) {
//            dif = word.codePointAt(i)-WordGuess.word.codePointAt(i);
//            sum = sum + Math.abs(dif);
//        }
//        fitness = sum;
//        return fitness;
//    }
        
    public String toString() {
        String w = decode();
        return fitness  + " : " + w;
    }
}

