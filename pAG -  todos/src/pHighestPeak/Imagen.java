/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pHighestPeak;

/**
 *
 * @author ealonso
 */

import java.io.*;
import javax.imageio.ImageIO;

import java.awt.image.*;

public class Imagen {
    public byte data[];
    public int W, H;
    public boolean ok;
    
    public Imagen( String archivo  )
    {
        BufferedImage I;
        try {
            I = ImageIO.read(new File(archivo));
            ok = true;
            W = I.getWidth();
            H = I.getHeight();
            data = Region( I );
        } catch (IOException e) {
            System.out.println("Error.."+archivo);
            ok = false;
        }
        
    }

    public int get(int f, int c)
    {
        return (int)(data[f*W+c] & 0xFF);
    }
    
    public void set(int f, int c, int v)
    {
        data[f*W+c] = (byte)v;
    }
    
    public boolean isOk() {
        return ok;
    }
    
    public void clear()
    {
        for(int i=0; i<W*H;i++)
            data[i] = 0;
    }
    
    public int getWidth() {
        return W;
    }

    public int getHeight() {
        return H;
    }
    
    //usado solo una vez; para la imagen completa
    private byte[] Region( BufferedImage I )
    {
       byte reg[] = new byte[W*H];

       int P, N, Lsize, i, j;
       int R, G, B;
       int Gray;

       Raster r = I.getData();
       DataBufferByte d = (DataBufferByte)r.getDataBuffer();
       Lsize = I.getWidth();
       for ( P = i = 0 ; i < H; i++ )
       {
           int inibuffer = i* Lsize*3;
           for ( N = j = 0; j < W; N+=3,P++,j++)
           {
                B = d.getElem(inibuffer+N);
                G = d.getElem(inibuffer+N+1);
                R = d.getElem(inibuffer+N+2);
                Gray = (R*299+G*587+B*114)/1000;
                reg[P] = (byte)Gray;
           }
       }
       return reg;
    }
    
}
