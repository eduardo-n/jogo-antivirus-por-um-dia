/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabuleiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Tabuleiro {
    
    private String posicaoAtualP1;
    private String posicaoAtualP2;
    private ArrayList<String> ladosBloqueados = new ArrayList<String>();
    public ArrayList<Setor> setoresVisitados = new ArrayList<Setor>();
    
    private String [][] matrizTabuleiro = new String [11][21];
    
    public Tabuleiro(){}

    public String getPosicaoAtualP1() {
        return posicaoAtualP1;
    }

    public void setPosicaoAtualP1(String posicaoAtualP1) {
        this.posicaoAtualP1 = posicaoAtualP1;
    }

    public String getPosicaoAtualP2() {
        return posicaoAtualP2;
    }

    public ArrayList<String> getLadosBloqueados() {
        return ladosBloqueados;
    }

    public void setLadosBloqueados(ArrayList<String> ladosBloqueados) {
        this.ladosBloqueados = ladosBloqueados;
    }

    public void setPosicaoAtualP2(String posicaoAtualP2) {
        this.posicaoAtualP2 = posicaoAtualP2;
    }

    public String[][] getMatrizTabuleiro() {
        return matrizTabuleiro;
    }

    public void setMatrizTabuleiro(String[][] matrizTabuleiro) {
        this.matrizTabuleiro = matrizTabuleiro;
    }

    public ArrayList<Setor> getSetoresVisitados() {
        return setoresVisitados;
    }

    public void setSetoresVisitados(ArrayList<Setor> setoresVisitados) {
        this.setoresVisitados = setoresVisitados;
    }
    
    public void initTabuleiro()
    {
        for(int i=0;i<11;i++) // linha
        {
            for(int j=0;j<21;j++) // coluna
            {
                if(j==0 || j==4 || j==8 || j==12 || j==16 || j==20)
                {
                    matrizTabuleiro[i][j]="|";
                }
                else if(i%2!=0)
                {
                    matrizTabuleiro[i][j]=" ";
                }
                else
                {
                    matrizTabuleiro[i][j]="-";
                }
            }
        }
        
        matrizTabuleiro[4][10]="*";
        matrizTabuleiro[6][10]="*";
        matrizTabuleiro[5][12]="*";
        matrizTabuleiro[5][8]="*";
        matrizTabuleiro[5][10]="P";
        
        System.out.println("    1   2   3   4   5"); // numerando as colunas do Tabuleiro
        int k = 1; // Variável para numerar as linhas do Tabuleiro
        
        for(int i=0; i<11;i++)
        {
            if(i%2!=0)
            {
              System.out.print(k);
              System.out.print(" ");
              k++;
            }
            else
            {
                System.out.print("  ");
            }

            for(int j=0;j<21;j++)
            {                
                System.out.print(matrizTabuleiro[i][j]);
            }
            System.out.print("\n");
        }
    }
    
    public void modificarTabuleiro()
    {
        
    }
    
    public void inserirSetorVisitado(Setor setor)
    {
        setoresVisitados.add(setor);
    }
    
    // Função para armazenar no ArrayList todos os lados de setores que já iniciam bloqueados (sem poder alterar para porta ou parede)
    public void ladosBloqueadosIniciais(int[] arrayCoordenada)
    {
        String coordenadas;
        for(int i=0; i<=10; i++)
        {
            for(int j=0; j<=20; j++)
            {
                if( (i==0 || i==10) && (j==2 || j==6 || j==10 || j==14 || j==18) )
                {
                    coordenadas = i+" "+j;
                    ladosBloqueados.add(coordenadas);
                }
                else if ( i%2!=0 && (j==0 || j==20) )
                {
                    coordenadas = i+" "+j;
                    ladosBloqueados.add(coordenadas); 
                }
                else if((i==4 && j==10) || (i==5 && j==8) || (i==6 && j==10) || (i==5 && j==12) )
                {
                    coordenadas = i+" "+j;
                    ladosBloqueados.add(coordenadas); 
                }
            }
        }
    }
}

/*
   012345678901234567890
0  |---|---|---|---|---|
1  |   |   |   |   |   |
2  |---|---|---|---|---|
3  |   |   |   |   |   |
4  |---|---|-*-|---|---|
5  |   |   * C *   |   |
6  |---|---|-*-|---|---|
7  |   |   |   |   |   |
8  |---|---|---|---|---|
9  |   |   |   |   |   |
10 |---|---|---|---|---|

*/