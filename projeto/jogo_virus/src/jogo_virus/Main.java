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
import jogadores.Inimigo;
import jogadores.Personagens;
import tabuleiro.Setor;
import tabuleiro.Tabuleiro;

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
        setorFonte = setorFonte.gerarSetorFonte(setorFonte, tabu);

        Scanner scanf = new Scanner(System.in);  // Scanner para receber os comandos das jogadas
        int menu = 1; // controle do menu de jogadas
        int countJogadas = 0; // controle das jogadas
        int quantidadeInimigos;
        int ciclo = 1;
        Setor setorTemporario = new tabuleiro.SetorNormal();
        Inimigo inimigoTemporario = new Inimigo();
        
        while(menu != 0)
        {
            if(!tabu.getPosicaoAtualP1().equals(""))
            {    
                if(ciclo > 25)
                {
                    System.out.println("• Fim do Jogo! - Voces perderam");
                    break;
                }
                else if(countJogadas > 4)
                {
                    countJogadas = 0;
                    ciclo++;
                }
                //vez do jogador 1
                if(countJogadas <2)
                {
                    jogador = 1;
                }
                //vez do jogador 2
                else if((countJogadas > 1 && countJogadas < 4) && !tabu.getPosicaoAtualP2().equals(""))
                {
                    jogador = 2;
                }
                else
                {
                    countJogadas = countJogadas+2;
                }
            }
            else
            {
                System.out.println("• Fim do Jogo! - Voces perderam");
                break;
            }

            if(countJogadas<4)
            {
                System.out.println("\nCiclo atual: "+ciclo);
                
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
                
                try
                {
                    comando = scanf.nextInt();
                    System.out.print("\n");
                    
                    switch(comando){ 
                        // mover
                        case 1:
                            System.out.println("\n1 - Cima");
                            System.out.println("2 - Direita");
                            System.out.println("3 - Baixo");
                            System.out.println("4 - Esquerda\n");
                            System.out.print("Digite o numero: ");
                            
                            try
                            {
                                comando = scanf.nextInt();
                                System.out.print("\n");
                                // movendo jogador 1 se possível e somando 1 no countJogadas
                                if(jogador == 1)
                                {    
                                    setorTemporario = setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu);
                                    if(setorTemporario.getInimigosDoSetor().size() > 0)
                                    {
                                        System.out.println("\n• Acabe com os inimigos para poder mover de setor");
                                        tabu.modificarTabuleiro(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu), 1, jogSimples, jogSuporte, tabu);
                                    }
                                    else
                                    {
                                        countJogadas = countJogadas + jogSimples.mover(comando, tabu,jogSimples, jogSuporte);
                                       
                                    }
                                }
                                else
                                {
                                    setorTemporario = setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP2(), tabu);
                                    if(setorTemporario.getInimigosDoSetor().size() > 0)
                                    {
                                        System.out.println("\n• Acabe com os inimigos para poder mover de setor");
                                        tabu.modificarTabuleiro(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP2(), tabu), 2, jogSimples, jogSuporte, tabu);
                                    }
                                    else
                                    {
                                        countJogadas = countJogadas + jogSuporte.mover(comando, tabu,jogSimples, jogSuporte);
                                    }
                                }

                                // Verificando se algum dos jogadores chegaram no Setor da Fonte do Vírus
                                if(tabu.getPosicaoAtualP2().equals(setorFonte.getCoordenadaX()+" "+setorFonte.getCoordenadaY()) || 
                                    tabu.getPosicaoAtualP1().equals(setorFonte.getCoordenadaX()+" "+setorFonte.getCoordenadaY()))
                                {
                                    System.out.println("\n• Parabéns ! Você encontrou a fonte do vírus\n");
                                    menu = 0;
                                }
 
                            }
                            catch(Exception e)
                            {
                                System.out.println("• Digite um comando valido.");
                                // scanf apenas para resetar o scanf anterior ( comando )
                                scanf.next();
                                tabu.modificarTabuleiro(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu), 1, jogSimples, jogSuporte, tabu);
                            }
                            
                            break;
                        case 2:
                            // ataque
                            if(countJogadas<2)
                            {
                                // Realizando ataque ao inimigo se puder
                                countJogadas = countJogadas + jogSimples.atacar(1, jogSimples, jogSuporte, tabu);
                            }
                            else
                            {
                                // Realizando ataque ao inimigo se puder
                                countJogadas = countJogadas + jogSuporte.atacar(2, jogSimples, jogSuporte, tabu);
                            }

                            tabu.modificarTabuleiro(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu), 1, jogSimples, jogSuporte, tabu);
                            break;
                        case 3:
                            // procurar
                            if(countJogadas<2)
                            {
                                if(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu).getClass().getName().equals("tabuleiro.SetorPrivado"))
                                {
                                    System.out.println("• Este setor eh privado - nao pode executar a acao de procurar");
                                    tabu.modificarTabuleiro(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu), 1, jogSimples, jogSuporte, tabu);
                                }
                                else
                                {
                                    countJogadas = countJogadas + jogSimples.procurar(jogSimples,jogSuporte, setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu), tabu);
                                }
                            }
                            else
                            {
                                if(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP2(), tabu).getClass().getName().equals("tabuleiro.SetorPrivado"))
                                {
                                    System.out.println("• Este setor eh privado - nao pode executar a acao de procurar");
                                    tabu.modificarTabuleiro(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP2(), tabu), 2, jogSimples, jogSuporte, tabu);
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
                                System.out.println("• Digite um comando valido.");
                                tabu.modificarTabuleiro(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu), 1, jogSimples, jogSuporte, tabu);
                            }

                            break;
                        case 9:
                            //sair do jogo    
                            menu=0;

                            break;
                        default:

                            System.out.println("• Digite um comando valido.");
                            tabu.modificarTabuleiro(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu), 1, jogSimples, jogSuporte, tabu);

                            break;
                    }   
                }
                catch(Exception e)
                {
                    System.out.println("• Digite um comando valido.");
                    // scanf apenas para resetar o scanf anterior ( comando )
                    scanf.next();
                    tabu.modificarTabuleiro(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu), 1, jogSimples, jogSuporte, tabu);
                }
            }
            else
            {

                System.out.println("\nCiclo atual: "+ciclo);
                System.out.println("\n\tJogada do Inimigo\n");
                
                // Realizando ataque do inimigo se puder
                countJogadas = countJogadas + inimigoTemporario.atacar(3, jogSimples, jogSuporte, tabu);
                
                if(tabu.getPosicaoAtualP1().equals(""))
                {
                    System.out.println("\n• P1 morreu, fim do Jogo! - Voces perderam\n");
                    menu = 0;
                }
                else
                {
                    // Pegando a quantidade de Inimigos para saber se é necessário exibir a jogada dele(s) ou não
                    if(!tabu.getPosicaoAtualP2().equals(""))
                    {
                        quantidadeInimigos = (setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu).getInimigosDoSetor().size())+
                            (setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP2(), tabu).getInimigosDoSetor().size());
                    }
                    else
                    {
                        quantidadeInimigos = (setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu).getInimigosDoSetor().size());
                    }
                    
                    if(quantidadeInimigos > 0)
                    {
                        tabu.modificarTabuleiro(setorTemporario.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu), 1, jogSimples, jogSuporte, tabu); 
                    }
                    else
                    {
                        System.out.println("• Não existe inimigo no(s) setore(s)\n");
                    }
                }
            }
        }
    }
}