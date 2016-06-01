/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pKnapsack;

/**
 *
 * @author ealonso
 */
public class Objeto {
    public String nombre;
    public int peso;
    public int valor;

    public Objeto(String nombre, int peso, int valor) {
        this.nombre = nombre;
        this.peso = peso;
        this.valor = valor;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public String toString() {
        return nombre + "["+peso+","+valor+"]";
    }
    
}
