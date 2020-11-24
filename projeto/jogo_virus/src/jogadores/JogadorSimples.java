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
    
    public void mover(int comando, tabuleiro.Tabuleiro tabu)
    {
        String posicaoAtualJogador;
        
        posicaoAtualJogador = tabu.getPosicaoAtualP1();
        
        // Convertendo e separando a String de Coordenadas do jogador
        StringTokenizer coordenada = new StringTokenizer(posicaoAtualJogador);
        Integer x = Integer.parseInt((String)coordenada.nextElement());
        Integer y = Integer.parseInt((String)coordenada.nextElement());
        
        Iterator i;
        Setor setorAtual = new Setor();
        // Pegando o setor atual do jogador
        i = tabu.setoresVisitados.iterator();
        while(i.hasNext())
        {
            Setor PesqSetorAtual = (Setor) i.next();
            if((PesqSetorAtual.getCoordenadaX()+" "+PesqSetorAtual.getCoordenadaY()).equals(posicaoAtualJogador))
            {  
                // Setando o lado da esquerda do novo setor igual ao lado da direita do setor da esquerda
                setorAtual = setorAtual.getSetorPorCoordenada(posicaoAtualJogador, tabu);
            }            
        }
        
        // mover para cima
        if(comando == 1 && setorAtual.isLadoCima())
        {
            Setor setor = new Setor();
            if(setor.getSetorPorCoordenada(posicaoAtualJogador,tabu) != null)
            {
                // Verificando se existe porta em cima
                if(setor.getSetorPorCoordenada(posicaoAtualJogador,tabu).isLadoCima())
                {
                    // atualizando a posição do jogador
                    tabu.setPosicaoAtualP1((x-2)+" "+y);
                    
                    // verificar se o novo setor já existe
                    String coordenadaNovoSetor = (x-2)+" "+y;
                    if(setor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
                    {
                        Setor setorInit = new Setor();
                        setorInit.initSetor(tabu.getPosicaoAtualP1(), tabu, comando);
                    }
                }
            }
            else
            {
                // quando null ( ERRO )
            }
            
            tabu.modificarTabuleiro(setorAtual, 1, tabu);
        }
        else if(comando == 2 && setorAtual.isLadoDir())
        {
            Setor setor = new Setor();
            if(setor.getSetorPorCoordenada(posicaoAtualJogador,tabu) != null)
            {
                // Verificando se existe porta pra direita
                if(setor.getSetorPorCoordenada(posicaoAtualJogador,tabu).isLadoDir())
                {
                    // atualizando a posição do jogador
                    tabu.setPosicaoAtualP1(x+" "+(y+4));
                    
                    // verificar se o novo setor já existe
                    String coordenadaNovoSetor = x+" "+(y+4);
                    if(setor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
                    {
                        Setor setorInit = new Setor();
                        setorInit.initSetor(tabu.getPosicaoAtualP1(), tabu, comando);
                    }
                }
            }
            else
            {
                // quando null ( ERRO )
            }
        }
        else if(comando == 3 && setorAtual.isLadoBaixo())
        {
            Setor setor = new Setor();
            if(setor.getSetorPorCoordenada(posicaoAtualJogador,tabu) != null)
            {
                // Verificando se existe porta em baixo
                if(setor.getSetorPorCoordenada(posicaoAtualJogador,tabu).isLadoDir())
                {
                    // atualizando a posição do jogador
                    tabu.setPosicaoAtualP1((x+2)+" "+y);
                    
                    // verificar se o novo setor já existe
                    String coordenadaNovoSetor = (x+2)+" "+y;
                    if(setor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
                    {
                        Setor setorInit = new Setor();
                        setorInit.initSetor(tabu.getPosicaoAtualP1(), tabu, comando);
                    }
                    else
                    {
                        
                    }
                }
            }
            else
            {
                // quando null ( ERRO )
            }
            
            tabu.modificarTabuleiro(setorAtual, 1, tabu);
        }
        else if(comando == 4 && setorAtual.isLadoEsq())
        {
            Setor setor = new Setor();
            if(setor.getSetorPorCoordenada(posicaoAtualJogador,tabu) != null)
            {
                // Verificando se existe porta pra esquerda
                if(setor.getSetorPorCoordenada(posicaoAtualJogador,tabu).isLadoDir())
                {
                    // atualizando a posição do jogador
                    tabu.setPosicaoAtualP1(x+" "+(y-4));
                    
                    // verificar se o novo setor já existe
                    String coordenadaNovoSetor = x+" "+(y-4);
                    if(setor.getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
                    {
                        Setor setorInit = new Setor();
                        setorInit.initSetor(tabu.getPosicaoAtualP1(), tabu, comando);
                    }
                }
            }
            else
            {
                // quando null ( ERRO )
            }
            
            tabu.modificarTabuleiro(setorAtual, 1, tabu);
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