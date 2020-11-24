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
        
        // mover para cima
        if(comando == 1)
        {
            if(getSetorPorCoordenada(posicaoAtualJogador,tabu) != null)
            {
                // Verificando se existe porta em cima
                if(getSetorPorCoordenada(posicaoAtualJogador,tabu).isLadoCima())
                {
                    // atualizando a posição do jogador
                    tabu.setPosicaoAtualP1((x+2)+" "+y);
                    
                    // verificar se o novo setor já existe
                    String coordenadaNovoSetor = (x+2)+" "+y;
                    if(getSetorPorCoordenada(coordenadaNovoSetor, tabu)== null)
                    {
                        
                    }
                }
            }
            else
            {
                // quando null
            }
        }
    }
    
    public Setor getSetorPorCoordenada(String coordenada, tabuleiro.Tabuleiro tabu)
    {
        Iterator i = tabu.setoresVisitados.iterator();
        while(i.hasNext())
        {
            Setor setor = (Setor) i.next();
            if((setor.getCoordenadaX()+" "+setor.getCoordenadaY()) == coordenada)
            {
                return setor;
            }            
        }
        return null;
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