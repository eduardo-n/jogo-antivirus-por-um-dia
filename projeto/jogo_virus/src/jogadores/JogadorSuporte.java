package jogadores;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import tabuleiro.Setor;
import tabuleiro.SetorNormal;
import tabuleiro.SetorOculto;
import tabuleiro.SetorPrivado;
import tabuleiro.Tabuleiro;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class JogadorSuporte extends Personagens {

    public JogadorSuporte(){}
    
    public JogadorSuporte(int ataque, int defesa)
    {
        super(ataque, defesa);
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
    
    public int mover(int comando, tabuleiro.Tabuleiro tabu, Personagens jogadorP, Personagens jogadorPP)
    {
        String posicaoAntigaJogador; 
        posicaoAntigaJogador = tabu.getPosicaoAtualP2();
        
        // Convertendo e separando a String de Coordenada do jogador
        StringTokenizer coordenada = new StringTokenizer(posicaoAntigaJogador);
        Integer x = Integer.parseInt((String)coordenada.nextElement());
        Integer y = Integer.parseInt((String)coordenada.nextElement());
        
        Iterator i;
        Setor setorAntigo = new SetorNormal();
        // Pegando o setor antigo do jogador
        i = tabu.setoresVisitados.iterator();
        while(i.hasNext())
        {
            Setor PesqSetorAntigo = (Setor) i.next();
            if((PesqSetorAntigo.getCoordenadaX()+" "+PesqSetorAntigo.getCoordenadaY()).equals(posicaoAntigaJogador))
            {  
                // Armazenando o setor Antigo
                setorAntigo = setorAntigo.getSetorPorCoordenada(posicaoAntigaJogador, tabu);
            }            
        }
        
        // Numero aleatorio para decidir o tipo do setor
        Random random = new Random();
        int numeroRandom = random.nextInt(3);
        
        Setor novoSetor;      
        if(numeroRandom == 0)
        {
            novoSetor = new SetorNormal();
        }
        else if(numeroRandom == 1)
        {
            novoSetor = new SetorOculto();
        }
        else
        {
            novoSetor = new SetorPrivado();
        }
        
        // mover para cima se existir porta
        if(comando == 1 && setorAntigo.isLadoCima())
        {
            // atualizando a posição do jogador
            tabu.setPosicaoAtualP2((x-2)+" "+y);

            // verificar se o novo setor já existe
            String coordenadaNovoSetor = (x-2)+" "+y;
            if(novoSetor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
            {
                novoSetor.initSetor(tabu.getPosicaoAtualP2(), tabu, comando, novoSetor);
            }
 
            tabu.modificarTabuleiro(setorAntigo, 2, jogadorP, jogadorPP,tabu);        
            return 1; // moveu
        }
        // mover para direita se existir porta
        else if(comando == 2 && setorAntigo.isLadoDir())
        {
            // atualizando a posição do jogador
            tabu.setPosicaoAtualP2(x+" "+(y+4));

            // verificar se o novo setor já existe
            String coordenadaNovoSetor = x+" "+(y+4);
            if(novoSetor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
            {
                novoSetor.initSetor(tabu.getPosicaoAtualP2(), tabu, comando, novoSetor);
            }
            
            tabu.modificarTabuleiro(setorAntigo, 2, jogadorP, jogadorPP,tabu);         
            return 1; // moveu
        }
        // mover para baixo se existir porta
        else if(comando == 3 && setorAntigo.isLadoBaixo())
        {
            // atualizando a posição do jogador
            tabu.setPosicaoAtualP2((x+2)+" "+y);

            // verificar se o novo setor já existe
            String coordenadaNovoSetor = (x+2)+" "+y;
            if(novoSetor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
            {
                novoSetor.initSetor(tabu.getPosicaoAtualP2(), tabu, comando, novoSetor);
            }
            
            tabu.modificarTabuleiro(setorAntigo, 2, jogadorP, jogadorPP,tabu);
            return 1; // moveu
        }
        // mover para esquerda se existir porta
        else if(comando == 4 && setorAntigo.isLadoEsq())
        {
            // atualizando a posição do jogador
            tabu.setPosicaoAtualP2(x+" "+(y-4));

            // verificar se o novo setor já existe
            String coordenadaNovoSetor = x+" "+(y-4);
            if(novoSetor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
            {
                novoSetor.initSetor(tabu.getPosicaoAtualP2(), tabu, comando, novoSetor);
            }
            
            tabu.modificarTabuleiro(setorAntigo, 2, jogadorP, jogadorPP,tabu);
            return 1; // moveu
        }
        else if(comando != 1 && comando != 2 && comando != 3 && comando != 4 )
        {
            System.out.print("\n• Comando invalido.");            
            tabu.modificarTabuleiro(setorAntigo, 2, jogadorP, jogadorPP,tabu);
            return 0; // não moveu
        }
        else
        {
            System.out.print("\n• Voce nao pode se movimentar atraves de paredes, tente outra direcao.");            
            tabu.modificarTabuleiro(setorAntigo, 2, jogadorP, jogadorPP,tabu);
            return 0; // não moveu
        }
    }
    
    @Override
    public int atacar(int jogador, Personagens jogadorP, Personagens jogadorPP, Tabuleiro tabu) {
        return super.atacar(jogador, jogadorP, jogadorPP, tabu); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int procurar(Personagens jogadorP, Personagens jogadorPP, Setor setor, tabuleiro.Tabuleiro tabu)
    {
        Random random = new Random();
        int numeroRandom;
        numeroRandom = random.nextInt((6 - 1)+1) + 1;
        
        if(numeroRandom<4)
        {
            System.out.println("• Nada foi encontrado desta vez!");
        }
        else if(numeroRandom == 4)
        {
            jogadorPP.setDefesa(jogadorPP.getDefesa()+1);
            System.out.println("• Voce ganhou +1 ponto de defesa");
        }
        else if(numeroRandom == 5)
        {
            jogadorPP.setDefesa(jogadorPP.getDefesa()+2);
            System.out.println("• Voce ganhou +2 ponto de defesa");
        }
        else
        {
            for(int i=0;i<setor.getInimigosDoSetor().size();i++)
            {
                setor.getInimigosDoSetor().get(i).setDefesa( setor.getInimigosDoSetor().get(i).getDefesa()-1);
            }
            if(setor.getInimigosDoSetor().size()!= 0)       
            {
                System.out.println("• Todos os inimigos neste setor perderam -1 ponto de defesa");
            }
            else
            {
                System.out.println("• Esse setor nao possui nenhum inimigo para perder pontos, porem sua jogada foi contabilizada");
            }
            
            int quantInimigos = setor.getInimigosDoSetor().size();
            
            for(int i=0;i<quantInimigos;i++)
            {
                if(setor.getInimigosDoSetor().get(i).getDefesa()==0)
                {
                    setor.getInimigosDoSetor().remove(i);
                }
            }
            
        }
        
        tabu.modificarTabuleiro(setor, 2, jogadorP, jogadorPP, tabu);
        return 1;
    }
    
    public int recuperarDefesa(tabuleiro.Tabuleiro tabu, Personagens jogadorP, Personagens jogadorPP)
    {
        Scanner scanf = new Scanner(System.in);
        int resposta;
        if(tabu.getPosicaoAtualP1().equals(tabu.getPosicaoAtualP2()))
        {
            System.out.println("Qual jogador voce deseja curar ?");
            System.out.print("Digite 1 ou 2: ");
            resposta = scanf.nextInt();
            if(resposta == 1)
            {
                jogadorP.setDefesa(jogadorP.getDefesa()+2);
            }
            else
            {
                jogadorPP.setDefesa(jogadorPP.getDefesa()+2);
            }
            return 1;
        }
        else
        {
            jogadorPP.setDefesa(jogadorPP.getDefesa()+2);
            return 1;
        }
    }
}