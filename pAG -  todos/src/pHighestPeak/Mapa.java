/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pHighestPeak;

import genetica.*;

/**
 *
 * @author ealonso
 */
public class Mapa implements GeneticAlgorithm {
    
    public static Imagen I;
    
    public Mapa() {
        I = new Imagen( "mapa.bmp" );
    }
    
    public static boolean valido( Individuo c )
    {
        int x, y;
        x = c.decodeX();
        y = c.decodeY();
        return x >= 0 && x < I.getWidth() &&
               y >= 0 && y < I.getHeight();
    }
    
    public static int altura( Individuo c ) {
        int x, y;
        x = c.decodeX();
        y = c.decodeY();
        return I.get(y, x); //fila,columna
    }

    public Population populate() {
        Population p;
        Individuo c;
        
        int tamPob, i;
        int x, y;
        
        p = new Population();
        tamPob = 10;
        
        for ( i = 0; i < tamPob; i++ ) {
            x = randInt( 0, I.getWidth()-1 );
            y = randInt( 0, I.getHeight()-1 );
            c = new Individuo( x, y  );
            p.addChromosome(c);
        }
        return p;
    }
    
    public int randInt( int min, int max ) {
        return min + (int)Math.floor(Math.random()*(max-min+1));
    }    
    
    
}
