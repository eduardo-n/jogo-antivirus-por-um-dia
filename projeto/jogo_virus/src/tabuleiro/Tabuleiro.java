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
import jogadores.Personagens;

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
    
    public void modificarTabuleiro(Setor setorAntigo, int jogador, Personagens jogadorP, Personagens jogadorPP, Tabuleiro tabu)
    {
        
        System.out.println("-----------------------------------------");
        System.out.println("|         Antivírus por um dia          |");
        System.out.println("-----------------------------------------\n");
        
        int e = 0;
        
        Iterator iterator;
        Setor setorNovo = new SetorNormal();
        // Pegando o setor novo do jogador
        iterator = tabu.setoresVisitados.iterator();
       
        if(jogador == 1)
        {     
            while(iterator.hasNext())
            {
                Setor PesqSetorNovo = (Setor) iterator.next(); 
                if((PesqSetorNovo.getCoordenadaX()+" "+PesqSetorNovo.getCoordenadaY()).equals(tabu.getPosicaoAtualP1()))
                {  
                    setorNovo = setorAntigo.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu);
                }    
            }
            // Renomear o setor onde o P2 está posicionado
            if(tabu.getPosicaoAtualP1().equals(tabu.getPosicaoAtualP2()))
            {

                // Limpando o campo de P1 antigo quando ele mudar de posição
                matrizTabuleiro[setorAntigo.getCoordenadaX()][setorAntigo.getCoordenadaY()]=" ";
                matrizTabuleiro[setorNovo.coordenadaX][setorNovo.coordenadaY]="P";

            }
            else
            {
                // Limpando o campo de P1 antigo quando ele mudar de posição
                matrizTabuleiro[setorAntigo.getCoordenadaX()][setorAntigo.getCoordenadaY()]=" ";

                matrizTabuleiro[setorNovo.coordenadaX][setorNovo.coordenadaY]=""+jogador;
                
                if(!tabu.getPosicaoAtualP2().equals(""))
                {
                    // Convertendo e separando a String da Coordenada do jogador que não moveu
                    StringTokenizer coordenada = new StringTokenizer(tabu.getPosicaoAtualP2());
                    Integer x = Integer.parseInt((String)coordenada.nextElement());
                    Integer y = Integer.parseInt((String)coordenada.nextElement());

                    matrizTabuleiro[x][y]=""+(jogador+1);
                }
                
            }
        }
        else if(jogador == 2)
        {
            while(iterator.hasNext())
            {
                Setor PesqSetorNovo = (Setor) iterator.next(); 
                if((PesqSetorNovo.getCoordenadaX()+" "+PesqSetorNovo.getCoordenadaY()).equals(tabu.getPosicaoAtualP2()))
                {  
                    setorNovo = setorAntigo.getSetorPorCoordenada(tabu.getPosicaoAtualP2(), tabu);
                }  
            }
            
            // Renomear o setor onde o P1 está posicionado
            if(tabu.getPosicaoAtualP1().equals(tabu.getPosicaoAtualP2()))
            {
                // Limpando o campo de P2 antigo quando ele mudar de posição
                matrizTabuleiro[setorAntigo.getCoordenadaX()][setorAntigo.getCoordenadaY()]=" ";
                matrizTabuleiro[setorNovo.coordenadaX][setorNovo.coordenadaY]="P";
            }
            else
            {
                // Limpando o campo de P2 antigo quando ele mudar de posição
                matrizTabuleiro[setorAntigo.getCoordenadaX()][setorAntigo.getCoordenadaY()]=" ";

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
        
        // Imprimindo dados do(s) setor(es) em baixo do tabuleiro
        for(int u=0;u<2;u++)
        {
            Setor setorTemporario = new SetorNormal();
            int podeImprimir = 0;
            
            if(u==0) // P1
            {
                if(tabu.getPosicaoAtualP1().equals(""))
                {
                    // Passando a vez para o P2
                    u++;
                }
                else
                {
                    setorTemporario = setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu);
                    podeImprimir = 1;
                }
            }
            if(u==1) // P2
            {
                if(tabu.getPosicaoAtualP2().equals(""))
                {
                    // P2 está morto
                }
                else
                {
                    setorTemporario = setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP2(), tabu);
                    podeImprimir = 1;
                }
            }
            
            if(podeImprimir == 1)
            {
                int y;
                int x = (setorTemporario.coordenadaX+1)/2;
                if(setorTemporario.coordenadaY == 2)
                {
                    y = 1;
                }
                else if(setorTemporario.coordenadaY == 6)
                {
                    y = 2;
                }
                else if(setorTemporario.coordenadaY == 10)
                {
                    y = 3;
                }
                else if(setorTemporario.coordenadaY == 14)
                {
                    y = 4;
                }
                else
                {
                    y = 5;
                }

                char cima,direita,baixo,esquerda;
                if(setorTemporario.isLadoCima())
                {
                    cima = '*';
                }
                else
                {
                    cima = '-';
                }
                if(setorTemporario.isLadoDir())
                {
                    direita = '*';
                }
                else
                {
                    direita = '|';
                }
                if(setorTemporario.isLadoBaixo())
                {
                    baixo = '*';
                }
                else
                {
                    baixo = '-';
                }
                if(setorTemporario.isLadoEsq())
                {
                    esquerda = '*';
                }
                else
                {
                    esquerda = '|';
                }

                System.out.println("\n\tSetor ["+x+","+y+"]");
                System.out.println("|------------"+cima+"------------|");
                System.out.print("|   ");

                for(int i=0;i<setorTemporario.getInimigosDoSetor().size();i++)
                {
                    System.out.print(setorTemporario.getInimigosDoSetor().get(i).getAtaque()+"/"+setorTemporario.getInimigosDoSetor().get(i).getDefesa()+" ");
                }
                if(setorTemporario.getInimigosDoSetor().size() == 0)
                {
                    System.out.println("   Sem inimigos !     |");
                }
                else if(setorTemporario.getInimigosDoSetor().size() == 1)
                {
                    System.out.println("                  |");
                }
                else if(setorTemporario.getInimigosDoSetor().size() == 2)
                {
                    System.out.println("              |");
                }
                else if(setorTemporario.getInimigosDoSetor().size() == 3)
                {
                    System.out.println("          |");
                }
                else if(setorTemporario.getInimigosDoSetor().size() == 4)
                {
                    System.out.println("      |");
                }
                else if(setorTemporario.getInimigosDoSetor().size() == 5)
                {
                    System.out.println("  |");
                }

                System.out.println("|                         |");
                System.out.println(""+esquerda+"                         "+direita);
                System.out.print("|   ");

                if(tabu.getPosicaoAtualP1().equals(tabu.getPosicaoAtualP2()))
                {
                    System.out.println("\tP1     P2         |");
                    System.out.println("|\t"+jogadorP.getAtaque()+"/"+jogadorP.getDefesa()+"    "+jogadorPP.getAtaque()+"/"+jogadorPP.getDefesa()+"        |");
                    System.out.println("|------------"+baixo+"------------|");
                    break;
                }
                else
                {
                    if(u==0)
                    {
                        System.out.println("\tP1                |");
                        System.out.println("|\t"+jogadorP.getAtaque()+"/"+jogadorP.getDefesa()+"               |");
                        System.out.println("|------------"+baixo+"------------|");
                    }
                    else if(u==1)
                    {
                        System.out.println("\tP2                |");
                        System.out.println("|\t"+jogadorPP.getAtaque()+"/"+jogadorPP.getDefesa()+"               |");
                        System.out.println("|------------"+baixo+"------------|");
                    }
                }
            }
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