/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pBPP1;

import genetica.*;
import java.util.*;

/**
 *
 * @author ealonso
 */
public class BPP1 implements GeneticAlgorithm {
    
    public  static int initPop;
    public  static int cap;
    public  static int nbloques;
    
    private List<Bloque> bloques;
    
    public BPP1(int initPop,int cap, int nbloques) {
        this.initPop = initPop;
        this.cap = cap;
        this.nbloques = nbloques;
        Bloque B;
        int i;
        bloques = new ArrayList<Bloque>();
//        for ( i = 0; i < nbloques; i++ ) {
//           // B = new Bloque( i, (int)Math.floor( Math.random() * cap ) + 1 );
//            B = new Bloque( i*3, 3 );
//            bloques.add(B);
//            B = new Bloque( i*3+1, 2 );
//            bloques.add(B);
//            B = new Bloque( i*3+2, 4 );
//            bloques.add(B);
//        }

//        this.cap = 12;
//        B = new Bloque(0, 8);
//        bloques.add(B);
//        B = new Bloque(1, 6);
//        bloques.add(B);
//        B = new Bloque(2, 4);
//        bloques.add(B);
//        B = new Bloque(3, 4);
//        bloques.add(B);
//        B = new Bloque(4, 4);
//        bloques.add(B);
//        B = new Bloque(5, 4);
//        bloques.add(B);
//        B = new Bloque(6, 3);
//        bloques.add(B);
//        B = new Bloque(7, 3);
//        bloques.add(B);

/*
Width	Rolls
1380	22
1520	25
1560	12
1710	14
1820	18
1880	18
1930	20
2000	10
2050	12
2100	14
2140	16
2150	18
2200	20
*/
genera(1380,	22);
genera(1520,	25);
genera(1560,	12);
genera(1710,	14);
genera(1820,	18);
genera(1880,	18);
genera(1930,	20);
genera(2000,	10);
genera(2050,	12);
genera(2100,	14);
genera(2140,	16);
genera(2150,	18);
genera(2200,	20);
this.nbloques = bloques.size();
this.cap = 5600;


//genera(90,	1);//1
//genera(30,	8);
//genera(20,	2);
//genera(10,	1);//1
//this.nbloques = bloques.size();
//this.cap = 120;
    }

    public void genera( int tam, int cant ) {
        int i;
        Bloque B;
        for ( i = 0; i < cant; i++ ) {
            B = new Bloque( bloques.size(), tam );
            bloques.add(B);
        }
    }

    public Population populate() {
        int i;
        Population poblacion;
        
        Individuo L;
        poblacion = new Population();
        for ( i = 0; i < initPop; i++  ) {
            reordena( bloques );
            L = distribuye2( bloques );
            poblacion.addChromosome(L);
        }
        return poblacion;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }
    
    private void reordena( List L ) {
        int j;
        Object tmp;
        int n;
        n = L.size();
        while( n > 0 ){
            j= (int)Math.floor( Math.random() * n );
            n--;
            tmp=L.get(n);
            L.set(n, L.get(j));
            L.set(j, tmp);
        }
    }    
    
    private Individuo distribuye( List<Bloque> L ) {
        Individuo R;
        Contenedor C;
        int i, n;
        boolean pudo;
        
        R = new Individuo();
        
        i = 0;
        n = L.size();
        while ( i < n ) {
            C = new Contenedor(cap);
            pudo = false;
            do {
                pudo = C.addBloque(L.get(i));
                if ( pudo )
                    i++;
            }
            while ( i < n && pudo );
            R.addContenedor(C);
        }
        return R;
    }
    

    private Individuo distribuye2( List<Bloque> L ) {
        Individuo R;
        Contenedor C;
        int i, n;
        boolean pudo;

        R = new Individuo();

        i = 0;
        n = L.size();
        while ( i < n ) {
            C = new Contenedor(cap);
            pudo = false;
            do {
                pudo = C.addBloque(L.get(i));
                if ( pudo )
                    i++;
            }
            while ( i < n && pudo && C.getUsado() <= cap/2);
            R.addContenedor(C);
        }
        return R;
    }

}
