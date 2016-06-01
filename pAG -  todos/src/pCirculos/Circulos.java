/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pCirculos;

import genetica.*;
/**
 *
 * @author ealonso
 */
public class Circulos implements GeneticAlgorithm {
    public static Circulo circulos[];
    public static int N;
    public static int minr, maxr, maxxy;
    public static int initPop;


    public Circulos(int initPop, int N, int minr, int maxr, int maxxy  ) {
        this.initPop = initPop;
        this.minr = minr;
        this.maxr = maxr;
        this.maxxy = maxxy;

        int i, nN, r, x, y;

        Circulo c;
        boolean isoverlapped;

//        this.N = 0;
//        nN = N;
//        circulos = new Circulo[N];
//        for ( i = 0 ; i < nN; i++ ) {
//            r = randInt(minr,maxr);
//            do {
//                x = randInt(r,maxxy-r);
//                y = randInt(r,maxxy-r);
//                c = new Circulo(x,y,r);
//                isoverlapped = overlapped(c);
//            }
//            while ( isoverlapped );
//            circulos[i] = c;
//            this.N++;
//        }

        this.N = 15;
        circulos = new Circulo[N];
        x = 101;
        y = 152;
        r = 9;
        circulos[0] = new Circulo(x,y,r);
        x = 171;
        y = 128;
        r = 10;
        circulos[1] = new Circulo(x,y,r);
        x = 138;
        y = 61;
        r = 20;
        circulos[2] = new Circulo(x,y,r);
        x = 194;
        y = 234;
        r = 8;
        circulos[3] = new Circulo(x,y,r);
        x = 21;
        y = 122;
        r = 9;
        circulos[4] = new Circulo(x,y,r);
        x = 221;
        y = 139;
        r = 12;
        circulos[5] = new Circulo(x,y,r);
        x = 47;
        y = 35;
        r = 22;
        circulos[6] = new Circulo(x,y,r);
        x = 188;
        y = 22;
        r = 20;
        circulos[7] = new Circulo(x,y,r);
        x = 122;
        y = 241;
        r = 10;
        circulos[8] = new Circulo(x,y,r);
        x = 228;
        y = 58;
        r = 20;
        circulos[9] = new Circulo(x,y,r);
        x = 159;
        y = 93;
        r = 18;
        circulos[10] = new Circulo(x,y,r);
        x = 101;
        y = 59;
        r = 11;
        circulos[11] = new Circulo(x,y,r);
        x = 146;
        y = 207;
        r = 24;
        circulos[12] = new Circulo(x,y,r);
        x = 42;
        y = 207;
        r = 10;
        circulos[13] = new Circulo(x,y,r);
        x = 87;
        y = 190;
        r = 19;
        circulos[14] = new Circulo(x,y,r);

    }

    public Population populate() {
        Circulo c;
        Population p;
        Individuo L;
        int x, y, r;
        p = new Population();
        c = new Circulo(0,0,0);
        for ( int i = 0; i < initPop; i++ ) {
            L = null;
            do {
                r = minr; //randInt(minr,maxr);
                x = randInt(r,maxxy-r);
                y = randInt(r,maxxy-r);
                c.x = x; c.y = y; c.r = r;
                if ( valid(c) ) {
                   L = new Individuo(x, y, r);
                }
            }
            while ( L == null );
            p.addChromosome(L);
        }
        return p;
    }
    
    public static boolean valid( Circulo c ) {
        return c.x+c.r < Circulos.maxxy &&
               c.y+c.r < Circulos.maxxy &&
               c.x-c.r > 0 &&
               c.y-c.r > 0 && 
               !overlapped(c);
    }
    
    public static boolean overlapped( Circulo c ) {
        for ( int i = 0; i < N; i++ )
            if ( twoCirclesOverlapped( c, circulos[i]) )
                return true;
        return false;
    }

    public static boolean twoCirclesOverlapped( Circulo c1, Circulo c2 ) {
        float DistBetweenCenters = (float)Math.sqrt( 
                                    (c1.x-c2.x) * (c1.x-c2.x) +
                                    (c1.y-c2.y) * (c1.y-c2.y));

        return DistBetweenCenters < c1.r+c2.r ||
               DistBetweenCenters < Math.abs(c1.r-c2.r);
    }

    int randInt( int min, int max ) {
        return min + (int)Math.floor( Math.random() * (max-min+1) );
    }

}

/*
x = 99;
y = 32;
r = 23;
circulos[0] = new Circulo(x,y,r);
x = 221;
y = 39;
r = 8;
circulos[1] = new Circulo(x,y,r);
x = 101;
y = 130;
r = 20;
circulos[2] = new Circulo(x,y,r);
x = 93;
y = 203;
r = 23;
circulos[3] = new Circulo(x,y,r);
x = 142;
y = 111;
r = 8;
circulos[4] = new Circulo(x,y,r);
x = 116;
y = 72;
r = 14;
circulos[5] = new Circulo(x,y,r);
x = 200;
y = 117;
r = 18;
circulos[6] = new Circulo(x,y,r);
x = 64;
y = 231;
r = 13;
circulos[7] = new Circulo(x,y,r);
x = 180;
y = 49;
r = 22;
circulos[8] = new Circulo(x,y,r);
x = 76;
y = 63;
r = 11;
circulos[9] = new Circulo(x,y,r);
x = 220;
y = 224;
r = 17;
circulos[10] = new Circulo(x,y,r);
x = 168;
y = 171;
r = 14;
circulos[11] = new Circulo(x,y,r);
x = 140;
y = 161;
r = 9;
circulos[12] = new Circulo(x,y,r);
x = 30;
y = 45;
r = 14;
circulos[13] = new Circulo(x,y,r);
x = 148;
y = 215;
r = 24;
circulos[14] = new Circulo(x,y,r);
 */