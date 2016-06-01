/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pMochila;
import genetica.*;

/**
 *
 * @author laboratorio_computo
 */
public class Individuo extends BinaryChromosome {
    
    public Individuo( int n ) {
        super(n);
    }
    
    //siempre se agrega con este codigo
    public Individuo(byte bits[]) {
        super( bits );
    }    

    //siempre se agrega con este codigo
    public Chromosome duplicate() {
        return new Individuo(bits);
    }    
    
    public float calcFitness() {
        if ( Mochila.valido(this) )
            fitness = - Mochila.calcValor(this);
        else
            fitness = 0;
        return fitness;
    }
    
    public String toString() {
        int peso = Mochila.peso;
        int pesoo = Mochila.calcPeso(this);
        String res;
        res = "";
        res += "fit = " + fitness + " ";
        res += "peso mochila = " + peso + " ";
        res += "pesoobjetos = " + pesoo + " ";
        res += "objetos = ";
        for ( int i = 0; i < Mochila.objetos.size(); i++ )
            if ( getBit(i) != 0 ) {
                Objeto o = Mochila.objetos.get(i);
                res += "("+o.nombre+","+o.peso+","+o.valor+") ";
            }
        return res;
    }
}

