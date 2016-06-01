/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pKnapsack;

import genetica.*;
import java.util.*;

/**
 *
 * @author ealonso
 */
public class KnapSack implements GeneticAlgorithm {
    
    public  static int initPop;
    public  static int capacidad;
    public  static int maxPeso;
    public  static int maxValor;
    public  static int nobjetos;
    public static Objeto objetos[];
    
    public KnapSack(int initPop, int cap, int nobjetos, int maxPeso, int maxValor) {
        this.initPop = initPop;
        this.capacidad = cap;
        this.nobjetos = nobjetos;
        this.maxPeso = maxPeso;
        this.maxValor = maxValor;
        Objeto B;
        int i;

        this.nobjetos = 24;
        this.capacidad = 500;

        objetos = new Objeto[this.nobjetos];
//        for ( i = 0; i < this.nobjetos; i++ ) {
//            objetos[i] = new Objeto( String.valueOf(i), randInt(1,maxPeso), randInt(1,maxValor) );
  //          objetos[i] = new Objeto( 2, 3 );
    //    }

//datos de:
// www.nils-haldenwang.de/computer-science/computational-intelligence/genetic-algorithm-vs-0-1-knapsack
// solucion: apple, banana, camera, cheese, compass, glucose, map, note-case, sandwich,
// socks, sunglasses, suntan cream, water, waterproof overclothes, waterproof trousers
// With value: 1130

objetos[	0	] = new Objeto("apple",	39	,	40	);
objetos[	1	] = new Objeto("banana",	27	,	60	);
objetos[	2	] = new Objeto("beer",	52	,	10	);
objetos[	3	] = new Objeto("book",	30	,	10	);
objetos[	4	] = new Objeto("camera",	32	,	30	);
objetos[	5	] = new Objeto("cheese",	23	,	30	);
objetos[	6	] = new Objeto("compass",	13	,	35	);
objetos[	7	] = new Objeto("glucose",	15	,	60	);
objetos[	8	] = new Objeto("map",	9	,	150	);
objetos[	9	] = new Objeto("notebook",	90	,	1	);
objetos[	10	] = new Objeto("note-case",	22	,	80	);
objetos[	11	] = new Objeto("sandwich",	50	,	160	);
objetos[	12	] = new Objeto("socks",	4	,	50	);
objetos[	13	] = new Objeto("sunglasses",	7	,	20	);
objetos[	14	] = new Objeto("suntan cream",	11	,	70	);
objetos[	15	] = new Objeto("tent",	200	,	150	);
objetos[	16	] = new Objeto("tin",	68	,	45	);
objetos[	17	] = new Objeto("towel",	18	,	12	);
objetos[	18	] = new Objeto("trousers",	48	,	10	);
objetos[	19	] = new Objeto("T-shirt",	24	,	15	);
objetos[	20	] = new Objeto("umbrella",	73	,	40	);
objetos[	21	] = new Objeto("water",	153	,	200	);
objetos[	22	] = new Objeto("waterproof overclothes",	43	,	75	);
objetos[	23	] = new Objeto("waterproof trousers",	42	,	70	);



    }
    
    public int randInt( int min, int max ) {
        return min + (int)Math.floor( Math.random() * (max-min+1) );
    }
    
    public Population populate() {
        int i, b;
        Population poblacion;
        int peso;
        
        Individuo L;
        poblacion = new Population();
        for ( i = 0; i < initPop; i++  ) {
            peso = 0;
            L = new Individuo(nobjetos);
            do {
                b = randInt(0,nobjetos-1);
                if ( L.getBit(b) == 0 &&
                    peso + objetos[b].peso <= capacidad ) {
                    peso += objetos[b].peso;
                    L.setBit(b, (byte)1);
                }
            }
            while ( peso < capacidad/2 );
            poblacion.addChromosome(L);
        }
        return poblacion;
    }


}
