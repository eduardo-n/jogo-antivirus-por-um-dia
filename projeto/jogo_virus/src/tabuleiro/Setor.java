/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabuleiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Setor {
    
    int coordenadaX;
    int coordenadaY;
    boolean ladoEsq;
    boolean ladoBaixo;
    boolean ladoDir;
    boolean ladoCima;
    
    public Setor(){}

    public Setor(int coordenadaX, int coordenadaY, boolean ladoEsq, boolean ladoBaixo, boolean ladoDir, boolean ladoCima)
    {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.ladoEsq = ladoEsq;
        this.ladoBaixo = ladoBaixo;
        this.ladoDir = ladoDir;
        this.ladoCima = ladoCima;  
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public boolean isLadoEsq() {
        return ladoEsq;
    }

    public void setLadoEsq(boolean ladoEsq) {
        this.ladoEsq = ladoEsq;
    }

    public boolean isLadoBaixo() {
        return ladoBaixo;
    }

    public void setLadoBaixo(boolean ladoBaixo) {
        this.ladoBaixo = ladoBaixo;
    }

    public boolean isLadoDir() {
        return ladoDir;
    }

    public void setLadoDir(boolean ladoDir) {
        this.ladoDir = ladoDir;
    }

    public boolean isLadoCima() {
        return ladoCima;
    }

    public void setLadoCima(boolean ladoCima) {
        this.ladoCima = ladoCima;
    }
}

/*
   012345678901234567890
0  |---|---|---|---|---|
1  |   |   |   |   |   |
2  |---|---|---|---|---|
3  |   |   |   | P |   |
4  |---|---|-*-|---|-*-|
5  |   |   * C *   *   |
6  |---|---|-*-|---|-*-|
7  |   |   |   |   |   |
8  |---|---|---|---|---|
9  |   |   |   |   |   |
10 |---|---|---|---|---|

*/