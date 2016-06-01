/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pClique;

import genetica.*;
import java.util.*;

/**
 *
 * @author ealonso
 */
public class Clique implements GeneticAlgorithm {
    
    public  static int initPop;
    public  static int N;
    public  static boolean grafo[][];
    public  static float p;
    
    public Clique(int initPop, int N, float p) {
        this.initPop = initPop;
        this.N = N;
        this.p = p;

        int i, j;
        grafo = new boolean[N][];
        for ( i = 0; i < N; i++ ) {
            grafo[i] = new boolean[N];
            for ( j = 0; j < N; j++ )
                grafo[i][j] = false;
        }
        
        for ( i  = 0; i < N; i++ ) {
            for ( j = i+1; j < N ; j++ ) 
                grafo[i][j] = grafo[j][i] = Math.random() <= p;
        }
        
        
    }

    //verficia si un conjunto de vértices forman un clique
    public static boolean isClique( byte vertices[] ) {
        int i, j;
        
        for ( i = 0; i < N; i++ ) {
            if ( vertices[i] == 1 ) {
                for ( j = i+1; j < N; j++ ) {
                    if ( vertices[j] == 1 )
                        if ( grafo[i][j] == false )
                            return false;
                }
            }
        }
        return true;
    }
    
    public int randInt( int min, int max ) {
        return min + (int)Math.floor( Math.random() * (max-min+1) );
    }
    
    //población inicial
    //cliques de 2 vértices
    public Population populate() {
        int i, b1, b2;
        Population poblacion;
        
        Individuo L;
        poblacion = new Population();
        for ( i = 0; i < initPop; i++  ) {
            L = new Individuo(N);
            do {
                b1 = randInt(0,N-1);
                b2 = randInt(0,N-1);
            }
            while ( !grafo[b1][b2] );
            L.setBit(b1, (byte)1);
            L.setBit(b2, (byte)1);
            poblacion.addChromosome(L);
        }
        return poblacion;
    }


}
