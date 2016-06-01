/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pTSP;

import genetica.*;
/**
 *
 * @author ealonso
 */
public class Individuo implements Chromosome {
    private int ciudades[];
    private float fitness;
    
    public Individuo(int nc) {
        ciudades = new int[nc];
        fitness = 0;
    }
    
    public int getNciudades() {
        return ciudades.length;
    }
    
    public int getCiudad(int p) {
        return ciudades[p];
    }

    public void setCiudad( int p, int C ) {
        ciudades[p] = C;
    }

    //de Chromosome
    public float getFitness() {
        return fitness;
    }
    
    //de Chromosome
    public float calcFitness() {
        int N;
        int i;
        N = getNciudades();
        fitness = 0;
        for ( i = 0; i < N; i++ ) {
            fitness += TSP.getDistancia( getCiudad(i), getCiudad( (i+1)% N ) );
        }
        return getFitness();
    }
    
    //de Chromosome
    public Chromosome mutate(float rate) {
        int c1, c2, mc1, mc2, tmp;
        int nintentos;
        float nf, mnf;
        Individuo M;
        
        mnf = 10000000000f;
        mc1 = mc2 = 0;
        M = (Individuo)duplicate();
        nintentos = (int)rate;
        while ( nintentos-- > 0 ) {
            c1 = (int)Math.floor( Math.random() * getNciudades() );
            do {
                  c2 = (int)Math.floor( Math.random() * getNciudades() );
                  if ( c1 != c2 ) {
                     tmp = M.ciudades[c1];
                     M.ciudades[c1] = M.ciudades[c2];
                     M.ciudades[c2] = tmp;
                     nf = M.calcFitness();
                     if ( nf < mnf ) {
                         mnf = nf;
                         mc1 = c1;
                         mc2 = c2;
                     }
                     M.ciudades[c2] = M.ciudades[c1];
                     M.ciudades[c1] = tmp;
                 }
            }
            while ( c1 == c2 );
        }
        tmp = M.ciudades[mc1];
        M.ciudades[mc1] = M.ciudades[mc2];
        M.ciudades[mc2] = tmp;
        return M;
    }
    
/*
    //de Chromosome
    public Chromosome mutate(float rate) {
        int c1, mc1, mc2;
        int c2;
        int i, ngenes;
        int intentos, intentos2;
        int tmp;
        float fa, nf, mnf;
      
        Individuo M;
        
        fa = getFitness();
        mnf = 10000000000f;
        mc1 = mc2 = 0;
        intentos2 = 0;
        c1 = c2 = 0;
        M = (Individuo)duplicate();
        ngenes = (int)rate;
        for ( i = 0; i < ngenes && mnf > fa; i++ ) {
            do {
                c1 = (int)Math.floor( Math.random() * getNciudades() );
                intentos = 1000;
                do {
                      c2 = (int)Math.floor( Math.random() * getNciudades() );
                      if ( c1 != c2 ) {
                         tmp = M.ciudades[c1];
                         M.ciudades[c1] = M.ciudades[c2];
                         M.ciudades[c2] = tmp;
                         nf = M.calcFitness();
                         if ( nf < mnf ) {
                             mnf = nf;
                             mc1 = c1;
                             mc2 = c2;
                         }
                         M.ciudades[c2] = M.ciudades[c1];
                         M.ciudades[c1] = tmp;
                     }
                     intentos--;
                }
                while ( intentos > 0 && c1 == c2 );
                intentos2++;
            }
            while ( intentos2 < 50 && mnf > fa );
            tmp = M.ciudades[mc1];
            M.ciudades[mc1] = M.ciudades[mc2];
            M.ciudades[mc2] = tmp;
        }
        
        return M;
        
    }
*/    
    //de Chromosome
    public void crossOver( Chromosome c2, Chromosome offsprings[] ) {
        if  (offsprings != null ) {
            offsprings[0] = this;
            offsprings[1] = c2;
        }
    }
    
    //de Chromosome
    public Chromosome duplicate() {
        Individuo L;
        int i;
        L = new Individuo(ciudades.length);
        for ( i = 0; i < ciudades.length; i++ ) {
            L.ciudades[i] = ciudades[i];
        }
        return L;
    }

    
    public String toString() {
        String res;
        int i;
        float u, d;
        
        res = "";
        
        u  = d =0;
        res += "fit= "+fitness;
/*        res += "{"+": " ;
        for ( i = 0; i < getNciudades(); i++ )
            res += " " + getCiudad(i);
        res += "}";*/
        return res;
    }
    
}
