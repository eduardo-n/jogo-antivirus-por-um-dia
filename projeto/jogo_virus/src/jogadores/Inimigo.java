/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogadores;

import tabuleiro.Tabuleiro;


public class Inimigo extends Personagens {
    
    public Inimigo(){}
    
    public Inimigo(int ataque, int defesa)
    {
        super(ataque, defesa);
    }

    @Override
    public int atacar(int jogador, Personagens jogadorP, Personagens jogadorPP, Tabuleiro tabu) {
        return super.atacar(jogador, jogadorP, jogadorPP, tabu); //To change body of generated methods, choose Tools | Templates.
    }
}