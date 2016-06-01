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
public class TSP implements GeneticAlgorithm {
    
    public static float distancias [][];
    public static float puntos[][];
    public static int N;
    public static int initPop;
    
    float angulo;
    float distancia;
    
    public TSP(int initpop) {
        this.initPop = initpop;
        int i, j;
        
        
        N = 38;

        //N = 38;

        float x1, y1, x2, y2;

        puntos = new float[N][];
        distancias = new float[N][N];

        for ( i = 0; i < N; i++ ) {
            puntos[i] = new float[2];
            distancias[i] = new float[N];
        }

        // datos tomados de:
        // http://www.math.uwaterloo.ca/tsp/world/countries.html
        // Djibouti - 38 Cities
        // Optimal distance = 6659
        puntos[	0	][	0	]=	11003.6111f	;	puntos[	0	][	1	]=	42102.5f	;
        puntos[	1	][	0	]=	11108.6111f	;	puntos[	1	][	1	]=	42373.8889f	;
        puntos[	2	][	0	]=	11133.3333f	;	puntos[	2	][	1	]=	42885.8333f	;
        puntos[	3	][	0	]=	11155.8333f	;	puntos[	3	][	1	]=	42712.5f	;
        puntos[	4	][	0	]=	11183.3333f	;	puntos[	4	][	1	]=	42933.3333f	;
        puntos[	5	][	0	]=	11297.5f	;	puntos[	5	][	1	]=	42853.3333f	;
        puntos[	6	][	0	]=	11310.2778f	;	puntos[	6	][	1	]=	42929.4444f	;
        puntos[	7	][	0	]=	11416.6667f	;	puntos[	7	][	1	]=	42983.3333f	;
        puntos[	8	][	0	]=	11423.8889f	;	puntos[	8	][	1	]=	43000.2778f	;
        puntos[	9	][	0	]=	11438.3333f	;	puntos[	9	][	1	]=	42057.2222f	;
        puntos[	10	][	0	]=	11461.1111f	;	puntos[	10	][	1	]=	43252.7778f	;
        puntos[	11	][	0	]=	11485.5556f	;	puntos[	11	][	1	]=	43187.2222f	;
        puntos[	12	][	0	]=	11503.0556f	;	puntos[	12	][	1	]=	42855.2778f	;
        puntos[	13	][	0	]=	11511.3889f	;	puntos[	13	][	1	]=	42106.3889f	;
        puntos[	14	][	0	]=	11522.2222f	;	puntos[	14	][	1	]=	42841.9444f	;
        puntos[	15	][	0	]=	11569.4444f	;	puntos[	15	][	1	]=	43136.6667f	;
        puntos[	16	][	0	]=	11583.3333f	;	puntos[	16	][	1	]=	43150f	;
        puntos[	17	][	0	]=	11595f	;               puntos[	17	][	1	]=	43148.0556f	;
        puntos[	18	][	0	]=	11600f	;               puntos[	18	][	1	]=	43150f	;
        puntos[	19	][	0	]=	11690.5556f	;	puntos[	19	][	1	]=	42686.6667f	;
        puntos[	20	][	0	]=	11715.8333f	;	puntos[	20	][	1	]=	41836.1111f	;
        puntos[	21	][	0	]=	11751.1111f	;	puntos[	21	][	1	]=	42814.4444f	;
        puntos[	22	][	0	]=	11770.2778f	;	puntos[	22	][	1	]=	42651.9444f	;
        puntos[	23	][	0	]=	11785.2778f	;	puntos[	23	][	1	]=	42884.4444f	;
        puntos[	24	][	0	]=	11822.7778f	;	puntos[	24	][	1	]=	42673.6111f	;
        puntos[	25	][	0	]=	11846.9444f	;	puntos[	25	][	1	]=	42660.5556f	;
        puntos[	26	][	0	]=	11963.0556f	;	puntos[	26	][	1	]=	43290.5556f	;
        puntos[	27	][	0	]=	11973.0556f	;	puntos[	27	][	1	]=	43026.1111f	;
        puntos[	28	][	0	]=	12058.3333f	;	puntos[	28	][	1	]=	42195.5556f	;
        puntos[	29	][	0	]=	12149.4444f	;	puntos[	29	][	1	]=	42477.5f	;
        puntos[	30	][	0	]=	12286.9444f	;	puntos[	30	][	1	]=	43355.5556f	;
        puntos[	31	][	0	]=	12300f	;               puntos[	31	][	1	]=	42433.3333f	;
        puntos[	32	][	0	]=	12355.8333f	;	puntos[	32	][	1	]=	43156.3889f	;
        puntos[	33	][	0	]=	12363.3333f	;	puntos[	33	][	1	]=	43189.1667f	;
        puntos[	34	][	0	]=	12372.7778f	;	puntos[	34	][	1	]=	42711.3889f	;
        puntos[	35	][	0	]=	12386.6667f	;	puntos[	35	][	1	]=	43334.7222f	;
        puntos[	36	][	0	]=	12421.6667f	;	puntos[	36	][	1	]=	42895.5556f	;
        puntos[	37	][	0	]=	12645f	;               puntos[	37	][	1	]=	42973.3333f	;


/*      
        angulo = (float)(Math.PI*2 / N);
        distancia = 30/2.0f;
        for ( i = 0; i < N; i++ )
           calc_pto( i, puntos[i]);            
*/            
        
        
        for ( i = 0; i < N; i++ ) {
            x1 = puntos[i][0];
            y1 = puntos[i][1];
            for ( j = i+1; j < N; j++ ) {
                x2 = puntos[j][0];
                y2 = puntos[j][1];
                distancias[i][j] = (float) Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) );
              
            }
        }

    }

    public void calc_pto( int v, float p[]  ) {
        p[0] = 30/2.0f + (float)(distancia * Math.cos(v*angulo));
        p[1] = 30/2.0f + (float)(distancia * Math.sin(v*angulo));
    }

    public static int getInitPop() {
        return initPop;
    }
    
    public static float getDistancia( int i, int j ) {
        if ( i < j )
            return distancias[i][j];
        else
            return distancias[j][i];
    }

    public Population populate() {
        int ciudades[];
        int i, j;
        Population poblacion;

        ciudades = new int[N];
        Individuo L;
        poblacion = new Population();
        for ( i = 0; i < initPop; i++  ) {
            for ( j = 0; j < N; j++ )
                ciudades[j] = j;
            L = distribuye( ciudades );
            poblacion.addChromosome(L);
        }
        return poblacion;
    }

    private Individuo distribuye( int L[] ) {
        Individuo R;
        int i, n;

        reordena( L );
        R = new Individuo(L.length);
        n = L.length;
        for ( i = 0; i < n; i++ )
            R.setCiudad( i, L[i] );
        return R;
    }
    
    private void reordena( int L[] ) {
        int j;
        int tmp;
        int n;
        n = L.length;
        while( n > 1 ){
            j = (int)Math.floor( Math.random() * n );
            n--;
            tmp = L[n];
            L[n] = L[j];
            L[j] =tmp;
        }
    }    
    

}
