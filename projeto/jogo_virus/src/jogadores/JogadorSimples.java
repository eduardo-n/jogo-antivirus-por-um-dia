package jogadores;

import java.util.Iterator;
import java.util.StringTokenizer;
import tabuleiro.Setor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class JogadorSimples extends Participantes {
   
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
    
    public int mover(int comando, tabuleiro.Tabuleiro tabu, Participantes jogadorP, Participantes jogadorPP)
    {
        String posicaoAntigaJogador;        
        posicaoAntigaJogador = tabu.getPosicaoAtualP1();
        
        // Convertendo e separando a String de Coordenada do jogador
        StringTokenizer coordenada = new StringTokenizer(posicaoAntigaJogador);
        Integer x = Integer.parseInt((String)coordenada.nextElement());
        Integer y = Integer.parseInt((String)coordenada.nextElement());
        
        Iterator i;
        Setor setorAntigo = new Setor();
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
        
        // mover para cima se existir porta
        if(comando == 1 && setorAntigo.isLadoCima())
        {
            Setor setor = new Setor();
            // atualizando a posição do jogador
            tabu.setPosicaoAtualP1((x-2)+" "+y);

            // verificar se o novo setor já existe
            String coordenadaNovoSetor = (x-2)+" "+y;
            if(setor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
            {
                Setor setorInit = new Setor();
                setorInit.initSetor(tabu.getPosicaoAtualP1(), tabu, comando);
            }
 
            tabu.modificarTabuleiro(setorAntigo, 1, jogadorP, jogadorPP,tabu);
            return 1; // moveu
        }
        // mover para direita se existir porta
        else if(comando == 2 && setorAntigo.isLadoDir())
        {
            Setor setor = new Setor();
            // atualizando a posição do jogador
            tabu.setPosicaoAtualP1(x+" "+(y+4));

            // verificar se o novo setor já existe
            String coordenadaNovoSetor = x+" "+(y+4);
            if(setor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
            {
                Setor setorInit = new Setor();
                setorInit.initSetor(tabu.getPosicaoAtualP1(), tabu, comando);
            }
            
            tabu.modificarTabuleiro(setorAntigo, 1, jogadorP, jogadorPP,tabu);
            return 1; // moveu
        }
        // mover para baixo se existir porta
        else if(comando == 3 && setorAntigo.isLadoBaixo())
        {
            Setor setor = new Setor();
            // atualizando a posição do jogador
            tabu.setPosicaoAtualP1((x+2)+" "+y);

            // verificar se o novo setor já existe
            String coordenadaNovoSetor = (x+2)+" "+y;
            if(setor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
            {
                Setor setorInit = new Setor();
                setorInit.initSetor(tabu.getPosicaoAtualP1(), tabu, comando);
            }
            
            tabu.modificarTabuleiro(setorAntigo, 1, jogadorP, jogadorPP,tabu);
            return 1; // moveu
        }
        // mover para esquerda se existir porta
        else if(comando == 4 && setorAntigo.isLadoEsq())
        {
            Setor setor = new Setor();
            // atualizando a posição do jogador
            tabu.setPosicaoAtualP1(x+" "+(y-4));

            // verificar se o novo setor já existe
            String coordenadaNovoSetor = x+" "+(y-4);
            if(setor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
            {
                Setor setorInit = new Setor();
                setorInit.initSetor(tabu.getPosicaoAtualP1(), tabu, comando);
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