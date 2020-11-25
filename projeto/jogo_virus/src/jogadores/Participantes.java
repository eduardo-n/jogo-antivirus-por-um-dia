package jogadores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Participantes {
    
    protected int ataque;
    protected int defesa;
    
    public Participantes(){}
    
    public Participantes(int ataque, int defesa)
    {
        this.ataque = ataque;
        this.defesa = defesa;  
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
    
    
}
