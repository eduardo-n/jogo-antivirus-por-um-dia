package jogadores;

import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;
import tabuleiro.Setor;
import tabuleiro.SetorNormal;
import tabuleiro.SetorOculto;
import tabuleiro.SetorPrivado;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class JogadorSimples extends Personagens {
   
    public JogadorSimples(int ataque, int defesa)
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
        posicaoAntigaJogador = tabu.getPosicaoAtualP1();
        
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
            tabu.setPosicaoAtualP1((x-2)+" "+y);

            // verificar se o novo setor já existe
            String coordenadaNovoSetor = (x-2)+" "+y;
            if(novoSetor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
            {
                novoSetor.initSetor(tabu.getPosicaoAtualP1(), tabu, comando, novoSetor);
            }
 
            tabu.modificarTabuleiro(setorAntigo, 1, jogadorP, jogadorPP,tabu);
            return 1; // moveu
        }
        // mover para direita se existir porta
        else if(comando == 2 && setorAntigo.isLadoDir())
        {
            // atualizando a posição do jogador
            tabu.setPosicaoAtualP1(x+" "+(y+4));

            // verificar se o novo setor já existe
            String coordenadaNovoSetor = x+" "+(y+4);
            if(novoSetor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
            {
                novoSetor.initSetor(tabu.getPosicaoAtualP1(), tabu, comando, novoSetor);
            }
            
            tabu.modificarTabuleiro(setorAntigo, 1, jogadorP, jogadorPP,tabu);
            return 1; // moveu
        }
        // mover para baixo se existir porta
        else if(comando == 3 && setorAntigo.isLadoBaixo())
        {
            // atualizando a posição do jogador
            tabu.setPosicaoAtualP1((x+2)+" "+y);

            // verificar se o novo setor já existe
            String coordenadaNovoSetor = (x+2)+" "+y;
            if(novoSetor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
            {
                novoSetor.initSetor(tabu.getPosicaoAtualP1(), tabu, comando, novoSetor);
            }
            
            tabu.modificarTabuleiro(setorAntigo, 1, jogadorP, jogadorPP,tabu);
            return 1; // moveu
        }
        // mover para esquerda se existir porta
        else if(comando == 4 && setorAntigo.isLadoEsq())
        {
            // atualizando a posição do jogador
            tabu.setPosicaoAtualP1(x+" "+(y-4));

            // verificar se o novo setor já existe
            String coordenadaNovoSetor = x+" "+(y-4);
            if(novoSetor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
            {
                novoSetor.initSetor(tabu.getPosicaoAtualP1(), tabu, comando, novoSetor);
            }
            
            tabu.modificarTabuleiro(setorAntigo, 1, jogadorP, jogadorPP,tabu);
            return 1; // moveu
        }
        else if(comando != 1 && comando != 2 && comando != 3 && comando != 4 )
        {
            System.out.print("\n• Comando invalido.");            
            tabu.modificarTabuleiro(setorAntigo, 1, jogadorP, jogadorPP,tabu);
            return 0; // não moveu
        }
        else
        {
            System.out.print("\n• Voce nao pode se movimentar atraves de paredes, tente outra direcao.");            
            tabu.modificarTabuleiro(setorAntigo, 1, jogadorP, jogadorPP,tabu);
            return 0; // não moveu
        }
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
            jogadorP.setDefesa(jogadorP.getDefesa()+1);
            System.out.println("• Voce ganhou +1 ponto de defesa");
        }
        else if(numeroRandom == 5)
        {
            jogadorP.setDefesa(jogadorP.getDefesa()+2);
            System.out.println("• Voce ganhou +2 pontos de defesa");
        }
        else
        {
            for(int i=0;i<setor.getInimigosDoSetor().size();i++)
            {
                setor.getInimigosDoSetor().get(i).setDefesa( setor.getInimigosDoSetor().get(i).getDefesa()-1);
            }
            if(setor.getInimigosDoSetor().size()!=0)       
            {
                System.out.println("• Todos os inimigos neste setor perderam -1 ponto de defesa");
            }
            for(int i=0;i<setor.getInimigosDoSetor().size();i++)
            {
                if(setor.getInimigosDoSetor().get(i).getDefesa()==0)
                {
                    setor.getInimigosDoSetor().remove(i);
                }
            }
        }
        
        tabu.modificarTabuleiro(setor, 1, jogadorP, jogadorPP, tabu);
        return 1;
    }
    
    public void atacar()
    {
        
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