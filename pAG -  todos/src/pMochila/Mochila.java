/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pMochila;

import genetica.*;
import java.util.*;

/**
 *
 * @author ealonso
 */

public class Mochila implements GeneticAlgorithm{
    public static List<Objeto> objetos;
    public static int peso;
    
    public Mochila() {
        
        peso = 2000;
        
        objetos = new ArrayList<Objeto>();
        objetos.add(new Objeto( "agua", 200, 20 ) );
        objetos.add(new Objeto( "linterna", 500, 30 ) );
        objetos.add(new Objeto( "atun", 240, 25 ) );
        objetos.add(new Objeto( "radio", 300, 15 ) );
        objetos.add(new Objeto( "celular", 150, 28 ) );
        objetos.add(new Objeto( "cuchillo", 230, 33 ) );
        objetos.add(new Objeto( "alcohol", 600, 22 ) );
        objetos.add(new Objeto( "vendas", 100, 24 ) );
        objetos.add(new Objeto( "libro", 700, 10 ) );
        
    }
    
    public static int calcPeso(Individuo c) {
        int i;
        int pesoo;
        pesoo = 0;
        for ( i = 0; i < objetos.size(); i++ ) 
            if ( c.getBit(i) != 0 ) {
                pesoo += objetos.get(i).peso;
            }
        return pesoo;
    }

    public static int calcValor(Individuo c) {
        int i;
        int valor;
        valor = 0;
        for ( i = 0; i < objetos.size(); i++ ) 
            if ( c.getBit(i) != 0 ) {
                valor += objetos.get(i).valor;
            }
        return valor;
    }
    
    public static boolean valido( Individuo c ) {
        return calcPeso(c) <= peso;
    }
    
    public Population populate() {
        Population p;
        Individuo c;
        
        int tamPob, i, o;
        
        p = new Population();
        tamPob = 10;
        
        for ( i = 0; i < tamPob; i++ ) {
            c = new Individuo( objetos.size()  );
            o = randInt(0,objetos.size()-1);
            c.setBit( o, (byte)1 ); 
            p.addChromosome(c);
        }
        return p;
    }
    
    public int randInt( int min, int max ) {
        return min + (int)Math.floor(Math.random()*(max-min+1));
    }

}
