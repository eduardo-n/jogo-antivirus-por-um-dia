/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabuleiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Tabuleiro {
    
    private String posicaoAtualP1;
    private String posicaoAtualP2;
    public ArrayList<String> ladosBloqueados = new ArrayList<String>();
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
    
    public void modificarTabuleiro(Setor setorAtual, int jogador, Tabuleiro tabu)
    {
        System.out.println("\n");
        System.out.println("-----------------------------------------");
        System.out.println("|         Antivírus por um dia          |");
        System.out.println("-----------------------------------------\n");
        
        int e = 0;
        
        Iterator iterator;
        Setor setorNovo = new Setor();
        // Pegando o setor novo do jogador
        iterator = tabu.setoresVisitados.iterator();
       
        if(jogador == 1)
        {
            while(iterator.hasNext())
            {
                Setor PesqSetorNovo = (Setor) iterator.next(); 
                if((PesqSetorNovo.getCoordenadaX()+" "+PesqSetorNovo.getCoordenadaY()).equals(tabu.getPosicaoAtualP1()))
                {  
                    setorNovo = setorAtual.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu);
                }  
            }
 
            // Renomear o setor onde o P2 está posicionado
            if(tabu.getPosicaoAtualP1().equals(tabu.getPosicaoAtualP2()))
            {
                // Limpando o campo de P1 antigo quando ele mudar de posição
                matrizTabuleiro[setorAtual.getCoordenadaX()][setorAtual.getCoordenadaY()]=" ";
                matrizTabuleiro[setorNovo.coordenadaX][setorNovo.coordenadaY]="P";
            }
            else
            {
                // Limpando o campo de P1 antigo quando ele mudar de posição
                matrizTabuleiro[setorAtual.getCoordenadaX()][setorAtual.getCoordenadaY()]=" ";

                matrizTabuleiro[setorNovo.coordenadaX][setorNovo.coordenadaY]=""+jogador;

                // Convertendo e separando a String da Coordenada do jogador que não moveu
                StringTokenizer coordenada = new StringTokenizer(tabu.getPosicaoAtualP2());
                Integer x = Integer.parseInt((String)coordenada.nextElement());
                Integer y = Integer.parseInt((String)coordenada.nextElement());

                matrizTabuleiro[x][y]=""+(jogador+1);
            }
        }
        else if(jogador == 2)
        {
            while(iterator.hasNext())
            {
                Setor PesqSetorNovo = (Setor) iterator.next(); 
                if((PesqSetorNovo.getCoordenadaX()+" "+PesqSetorNovo.getCoordenadaY()).equals(tabu.getPosicaoAtualP2()))
                {  
                    setorNovo = setorAtual.getSetorPorCoordenada(tabu.getPosicaoAtualP2(), tabu);
                }  
            }

            // Renomear o setor onde o P1 está posicionado
            if(tabu.getPosicaoAtualP1().equals(tabu.getPosicaoAtualP2()))
            {
                matrizTabuleiro[setorNovo.coordenadaX][setorNovo.coordenadaY]="P";
            }
            else
            {
                // Limpando o campo de P2 antigo quando ele mudar de posição
                matrizTabuleiro[setorAtual.getCoordenadaX()][setorAtual.getCoordenadaY()]=" ";

                matrizTabuleiro[setorNovo.coordenadaX][setorNovo.coordenadaY]=""+jogador;

                // Convertendo e separando a String da Coordenada do jogador que não moveu
                StringTokenizer coordenada = new StringTokenizer(tabu.getPosicaoAtualP1());
                Integer x = Integer.parseInt((String)coordenada.nextElement());
                Integer y = Integer.parseInt((String)coordenada.nextElement());

                matrizTabuleiro[x][y]=""+(jogador-1);
            }
        }
        
        if(setorNovo.isLadoCima())
        {
            matrizTabuleiro[setorNovo.coordenadaX-1][setorNovo.coordenadaY]="*";
        }
        else
        {
            matrizTabuleiro[setorNovo.coordenadaX-1][setorNovo.coordenadaY]="-";
        }
        
        if(setorNovo.isLadoDir())
        {
            matrizTabuleiro[setorNovo.coordenadaX][setorNovo.coordenadaY+2]="*";
        }
        else
        {
            matrizTabuleiro[setorNovo.coordenadaX][setorNovo.coordenadaY+2]="|";
        }
        
        if(setorNovo.isLadoBaixo())
        {
            matrizTabuleiro[setorNovo.coordenadaX+1][setorNovo.coordenadaY]="*";
        }
        else
        {
            matrizTabuleiro[setorNovo.coordenadaX+1][setorNovo.coordenadaY]="-";
        }
        
        if(setorNovo.isLadoEsq())
        {
            matrizTabuleiro[setorNovo.coordenadaX][setorNovo.coordenadaY-2]="*";
        }
        else
        {
            matrizTabuleiro[setorNovo.coordenadaX][setorNovo.coordenadaY-2]="|";
        }

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
7  |   |   | P |   |   |
8  |---|---|---|---|---|
9  |   |   |   |   |   |
10 |---|---|---|---|---|

*/