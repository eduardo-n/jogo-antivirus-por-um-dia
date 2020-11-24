/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo_virus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import static java.util.Spliterators.iterator;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        jogadores.JogadorSimples jogSuporte = new jogadores.JogadorSimples(1,7);

        // Inicializando setorFonte do vírus e setando os dados
        tabuleiro.SetorNormal setorFonte = new tabuleiro.SetorNormal();
        setorFonte = setorFonte.gerarSetorFonte(setorFonte);

        // Scanner para receber os comandos das jogadas
        Scanner scanf = new Scanner(System.in);  
        int menu = 1;
        while(menu != 0)
        {
            System.out.println("\n\tVez de P"+jogador+"\n");
        
            System.out.println("1 - Mover");
            System.out.println("2 - Atacar");
            System.out.println("3 - Procurar");
            System.out.println("4 - Sair do jogo");
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
                    // movendo jogador 1 se possível
                    jogSimples.mover(comando, tabu);
                    break;
                case 2:
                    // ataque
                case 3:
                    // procurar
                case 4:
                    //sair do while    
                    menu=0;
                default:
                    System.out.println("Digite um comando valido.");
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