/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pWordGuessDigit;

import genetica.*;

/**
 *
 * @author ealonso
 */
public class WordGuess implements GeneticAlgorithm {
    public static String word;
    
    public WordGuess( String word ) {
        this.word = word;
    }
    
    public Population populate() {
        Population p;
        Individuo c;
        
        int tamPob, i, j;
        int ch;
        String w;
        
        p = new Population();

        tamPob = 32;
        
        for ( i = 0; i < tamPob; i++ ) {
            w = "";
            for ( j = 0; j < word.length(); j++ ) {
                ch = randInt(0,9);
                w = w + Character.toString((char)(ch+48));
            }
            c = new Individuo(w);
            p.addChromosome(c);
        }
        return p;
    }
    
    public int randInt( int min, int max ) {
        return min + (int)Math.floor(Math.random()*(max-min+1));
    }    
    
    
}
