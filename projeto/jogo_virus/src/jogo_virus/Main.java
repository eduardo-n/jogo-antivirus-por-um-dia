/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo_virus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import static java.util.Spliterators.iterator;
import java.util.concurrent.TimeUnit;
import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;
import tabuleiro.Setor;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        // TODO code application logic here

        int comando; // comando digitado pelo usuario
        int jogador = 1; // numero do jogador desejado para executar as funções

        System.out.println("\n");
        System.out.println("-----------------------------------------");
        System.out.println("|         Antivírus por um dia          |");
        System.out.println("-----------------------------------------\n");
        
        // Inicializando o Tabuleiro
        tabuleiro.Tabuleiro tabu = new tabuleiro.Tabuleiro();
        tabu.initTabuleiro();
        tabu.setPosicaoAtualP1("5 10");
        tabu.setPosicaoAtualP2("5 10");
        
        // Inicializando o Setor do Centro e colocando no ArrayList de setores visitados
        tabuleiro.SetorNormal setorCentro = new tabuleiro.SetorNormal(5,10,true,true,true,true);
        tabu.inserirSetorVisitado(setorCentro);
        
        // Incrementando as coordenadas dos lados que não podem ser moficados na matriz
        int[] arrayCoordenada = new int[2];
        tabu.ladosBloqueadosIniciais(arrayCoordenada);

        // Inicializando os Jogadores
        jogadores.JogadorSimples jogSimples = new jogadores.JogadorSimples(2,6);
        jogadores.JogadorSuporte jogSuporte = new jogadores.JogadorSuporte(1,7);

        // Inicializando setorFonte do vírus e setando os dados
        tabuleiro.SetorNormal setorFonte = new tabuleiro.SetorNormal();
        setorFonte = setorFonte.gerarSetorFonte(setorFonte);

        // Scanner para receber os comandos das jogadas
        Scanner scanf = new Scanner(System.in);  
        int menu = 1;
        int countJogadas = 0;
        int quantidadeInimigos;
        int ciclo = 0;
        Setor setorTemporario = new tabuleiro.SetorNormal();
        while(menu != 0)
        { 
            quantidadeInimigos = (setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu).getInimigosDoSetor().size())+
                    (setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP2(), tabu).getInimigosDoSetor().size());
            if(countJogadas > 3+quantidadeInimigos)
            {
                countJogadas = 0;
                ciclo++;
            }
            //vez do jogador1
            if(countJogadas <2 && tabu.getPosicaoAtualP1()!=null)
            {
                jogador = 1;
            }
            //vez do jogador2
            else if(tabu.getPosicaoAtualP2()!=null)
            {
                jogador = 2;
            }
            //os 2 jogadores estão mortos
            else if(tabu.getPosicaoAtualP2()==null && tabu.getPosicaoAtualP1()==null)
            {
                System.out.println("• Fim do Jogo! - Voces perderam");
                break;
            }
            else if(ciclo>25)
            {
                System.out.println("• Fim do Jogo! - Voces perderam");
                break;
            }
            //ainda tem um jogador vivo mas não estava na vez dele ainda
            else
            {
                countJogadas++;
            }
        
            //vez de algum jogador
            if(countJogadas<4)
            {
                System.out.println("\n\tVez de P"+jogador+"\n");
                System.out.println("1 - Mover");
                System.out.println("2 - Atacar");
                System.out.println("3 - Procurar");
                if(countJogadas>1 && countJogadas<4)
                {
                    System.out.println("4 - Recuperar Defesa");
                }
                System.out.println("9 - Sair do jogo");
                System.out.print("\nDigite o numero: ");
                comando = scanf.nextInt();

                switch(comando){                
                    case 1:
                        System.out.println("\n1 - Cima");
                        System.out.println("2 - Direita");
                        System.out.println("3 - Baixo");
                        System.out.println("4 - Esquerda\n");
                        System.out.print("Digite o numero: ");
                        comando = scanf.nextInt();
                        // movendo jogador 1 se possível e somando 1 no countJogadas
                        if(jogador == 1)
                        {    
                            countJogadas = countJogadas + jogSimples.mover(comando, tabu,jogSimples, jogSuporte);
                        }
                        else
                        {
                            countJogadas = countJogadas + jogSuporte.mover(comando, tabu,jogSimples, jogSuporte);
                        }

                        // Verificando se algum dos jogadores chegaram no Setor da Fonte do Vírus
                        if(tabu.getPosicaoAtualP2().equals(setorFonte.getCoordenadaX()+" "+setorFonte.getCoordenadaY()) || 
                            tabu.getPosicaoAtualP1().equals(setorFonte.getCoordenadaX()+" "+setorFonte.getCoordenadaY()))
                        {
                            System.out.println("\n• Parabéns ! Você conseguiu combater o vírus\n");
                            menu = 0;
                        }

                        break;
                    case 2:
                        // ataque
                    case 3:
                        // procurar
                        if(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu).getClass().getName().equals("tabuleiro.SetorPrivado"))
                        {
                            System.out.println("• Este setor eh privado - nao pode executar a acao de procurar");
                            tabu.modificarTabuleiro(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu), 1, jogSimples, jogSuporte, tabu);
                        }
                        else
                        {
                            if(countJogadas<2)
                            {
                                countJogadas = countJogadas + jogSimples.procurar(jogSimples,jogSuporte, setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu), tabu);
                            }
                            else
                            {
                                countJogadas = countJogadas + jogSuporte.procurar(jogSimples,jogSuporte, setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP2(), tabu), tabu);
                            }
                        }
                        break;
                    case 4:
                        // somente o jogador2 consegue executar esta habilidade
                        if(countJogadas>1 && countJogadas<4)
                        {
                            //recuperar Defesa gasta as 2 jogadas
                            countJogadas = countJogadas + jogSuporte.recuperarDefesa(tabu, jogSimples, jogSuporte);
                            tabu.modificarTabuleiro(setorTemporario, jogador, jogSimples, jogSuporte, tabu);
                        }
                        // jogador e inimigo não podem recuperar defesa
                        else 
                        {
                            //System.out.println(""+countJogadas);
                            System.out.println("• Digite um comando valido.");
                        }
                        break;
                    case 9:
                        //sair do jogo    
                        menu=0;
                        break;
                    default:
                        
                        System.out.println(""+countJogadas);
                        System.out.println("• Digite um comando valido.");
                        break;
                }
            }
            else
            {
                System.out.println("vez do inimigo");
                //chamar a funcao de ataque inimigo
                countJogadas++;
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
9  | X |   |   |   |   |
10 |---|---|---|---|---|

*/