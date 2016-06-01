/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pBPP1;

import java.util.*;

import genetica.*;
/**
 *
 * @author ealonso
 */
public class Individuo implements Chromosome {
    private List<Contenedor> contenedores;
    private float fitness;
    
    public Individuo() {
        contenedores = new ArrayList<Contenedor>();
        fitness = 0;
    }
    
    public void addContenedor( Contenedor C ) {
        contenedores.add(C);
    }
    
    public int getNcontenedores() {
        return contenedores.size();
    }
    
    public Contenedor getContenedor(int p) {
        return contenedores.get(p);
    }
    
    public void remContenedor( int p ) {
        contenedores.remove(p);
    }

    public float getFitness() {
        return fitness;
    }
    
    public float calcFitness() {
        Contenedor C;
        int i, lib, nincomp;
        fitness = 0;
        nincomp = 0;
        for ( i = 0; i < getNcontenedores(); i++ ) {
            C = getContenedor(i);
            lib = C.getLibre();
            if ( lib != C.cap ) {
               fitness += lib;
                if ( lib > 0 )
                    nincomp++;
            }
        }
        fitness += nincomp;
        return fitness;
    }
    
    public Chromosome mutate(float rate) {
        int i;
        int c1, c2, bc1, bc2;
        int intentos;
        Contenedor C1, C2;
        Individuo M;
        
        intentos = 2000;
        do {
            M = null;
            c1 = (int)Math.floor( Math.random() * getNcontenedores() );
            c2 = (int)Math.floor( Math.random() * getNcontenedores() );
            if ( c1 != c2 ) {
                C1 = getContenedor(c1);
                C2 = getContenedor(c2);
                bc1 = (int)Math.floor( Math.random() * C1.getNbloques() );
                bc2 = (int)Math.floor( Math.random() * C2.getNbloques() );
                if ( C2.getLibre() != 0 && C1.getLibre() != 0 ) {
                    if ( intentos % 400 == 0 ) {
                       // System.out.println("  YEESSS");
                      M = cambiarBloques(c1,bc1,c2,bc2);
                      if ( M != null )
                          break;
                    }
                    M = moverBloque( c1, bc1, c2 );
                    if ( M != null )
                        break;
                    M = moverBloque( c2, bc2, c1 );
                    if ( M != null )
                       break;
                    //}
                }
                else {
                    if ( C2.getLibre() != 0 ) {
                        if ( intentos % 200 == 0 ) {
                        M = moverBloque( c1, bc1, c2 );
                        if ( M != null )
                            break;
                        }
                    }
                    else {
                        if ( C1.getLibre() != 0 ) {
                            if ( intentos % 200 == 0 ) {
                            M = moverBloque( c2, bc2, c1 );
                            if ( M != null )
                                break;
                            }
                        }

                    }

                }
                
            }
            intentos--;
        }
        while (  intentos > 0 && M == null   );
//        if ( intentos == 0 ) {
//           c1 = c2 = -1;
//           i = 0;
//           while ( i < getNcontenedores() ) {
//              if ( getContenedor(i).getLibre() != 0 ) {
//                  c1 = i;
//                  break;
//              }
//              else
//                 i++;
//           }
//           if ( c1 >= 0 ) {
//              i++;
//              while ( i < getNcontenedores() ) {
//                 if ( getContenedor(i).getLibre() != 0 ) {
//                      c2 = i;
//                      break;
//                  }
//                  else
//                     i++;
//              }
//              if ( c2 >= 0 ) {
//                    C1 = getContenedor(c1);
//                    C2 = getContenedor(c2);
//                    bc1 = (int)Math.floor( Math.random() * C1.getNbloques() );
//                    bc2 = (int)Math.floor( Math.random() * C2.getNbloques() );
//                    M = moverBloque( c1, bc1, c2 );
//                    if ( M== null )
//                        M = moverBloque( c2, bc2, c1 );
//                    if ( M == null )
//                       M = cambiarBloques(c1,bc1,c2,bc2);
//                 //System.out.println("sin intentos---encontrado");
//              }
//          }
//        }
        if ( M != null ) {
            for ( i = 0; i < M.getNcontenedores(); i++ )
                if ( M.getContenedor(i).getUsado() == 0 ) {
                    M.remContenedor(i);
                    i--;
                }
        }
        return M;
        
    }

    public void crossOver( Chromosome c2, Chromosome offsprings[] ) {
        if  (offsprings != null ) {
            offsprings[0] = this;
            offsprings[1] = c2;
        }
    }
    
    public Chromosome duplicate() {
        Individuo L;
        int i;
        Contenedor C;
        L = new Individuo();
        for ( i = 0; i < getNcontenedores(); i++ ) {
            C = getContenedor(i).clonar();
            L.addContenedor(C);
        }
        return L;
    }

    private Individuo cambiarBloques( int c1, int bc1, int c2, int bc2 ) {
        Individuo R;
        Contenedor C1, C2;
        Bloque B1, B2;
        int t1, t2;

        C1 = getContenedor(c1);
        C2 = getContenedor(c2);
        B1 = C1.getBloque(bc1);
        B2 = C2.getBloque(bc2);
        t1 = B1.getTam();
        t2 = B2.getTam();
        if ( t1 == t2 )
            return null;
        
        if ( C1.getLibre()+t1-t2 >= 0 &&
             C2.getLibre()+t2-t1 >= 0 )  {
            R = (Individuo)duplicate();
            C1 = R.getContenedor(c1);
            C2 = R.getContenedor(c2);
            B1 = C1.getBloque(bc1);
            B2 = C2.getBloque(bc2);
            C1.remBloque(bc1);
            C2.remBloque(bc2);
            C1.addBloque(B2);
            C2.addBloque(B1);
            return R;
        }
        else
            return null;
    }
    
    private Individuo moverBloque( int c1, int bc1, int c2 ) {
        Individuo R;
        Contenedor C1, C2;
        Bloque B1;
        int t1;

        C1 = getContenedor(c1);
        C2 = getContenedor(c2);
        B1 = C1.getBloque(bc1);
        t1 = B1.getTam();

        if ( C2.getLibre()-t1 >= 0 )  {
            R = (Individuo)duplicate();
            C1 = R.getContenedor(c1);
            C2 = R.getContenedor(c2);
            B1 = C1.getBloque(bc1);
            C1.remBloque(bc1);
            C2.addBloque(B1);
            return R;
        }
        else
            return null;
    }
    
    public String toString() {
        String res;
        int i, cc, ci;
        float u, d;
        
        res = "";
        
        u  = d =0;
        ci = cc = 0;
        res += "fit= "+fitness;
        res += "{ bins: "+getNcontenedores();
        for ( i = 0; i < getNcontenedores(); i++ ) {
            u += getContenedor(i).getUsado();
            d += getContenedor(i).getCap();
            if ( getContenedor(i).getLibre() != 0 )
                ci++;
            else
                cc++;
            //res += getContenedor(i)+ " ";
        }
        res += " u: " + u+ " c: " + d + " cc: "+ cc + " ci: "+ci;
        res += "}";
        return res;
    }
    
}
