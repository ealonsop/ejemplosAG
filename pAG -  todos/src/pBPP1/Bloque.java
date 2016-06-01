/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pBPP1;

/**
 *
 * @author ealonso
 */
public class Bloque {
    private int id;
    private int tam;
    
    public Bloque( int id, int tam ) {
       this.id = id;
       this.tam = tam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }
    
    
    
    public String toString() {
        //return "["+id+","+tam+"]";
        return ""+(id+1);
    }
    
}
