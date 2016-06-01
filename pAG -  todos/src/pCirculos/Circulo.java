/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pCirculos;

/**
 *
 * @author ealonso
 */
public class Circulo {
    public int x, y, r;

    public Circulo(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
   
    public String toString() {
        return "["+x+","+y+","+r+"]";
    }

}
