/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pBPP1;

import java.util.*;

/**
 *
 * @author ealonso
 */
public class Contenedor {
    public int cap;
    public int usado;
    public List<Bloque> bloques;

    public Contenedor(int cap) {
        this.cap = cap;
        this.usado = 0;
        this.bloques = new ArrayList<Bloque>();
    }
    
    public Contenedor clonar() {
        int i;
        Contenedor C;
        C = new Contenedor(cap);
        for ( i = 0; i < getNbloques(); i++ )
            C.addBloque(getBloque(i));
        return C;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public int getUsado() {
        return usado;
    }
    
    public int getLibre() {
        return getCap() - getUsado();
    }
    
    public boolean addBloque( Bloque B ) {
        if ( getLibre() >= B.getTam() ) {
            bloques.add(B);
            usado += B.getTam();
            return true;
        }
        else
            return false;
    }
    
    public int getNbloques() {
        return bloques.size();
    }
    
    public Bloque getBloque(int p) {
        return bloques.get(p);
    }
    
    public void remBloque(int p) {
        Bloque B;
        B = getBloque(p);
        usado -= B.getTam();
        bloques.remove(p);
    }
  
    public String toString() {
        String res;
        int i;
        res = "";
        
        res += "( "+ getLibre() + " ";
        for ( i = 0; i < getNbloques(); i++ )
            res += ", " + getBloque(i);
        res += ")";
        return res;
    }
    
    
}
