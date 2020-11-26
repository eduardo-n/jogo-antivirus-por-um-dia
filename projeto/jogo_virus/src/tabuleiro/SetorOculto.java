/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabuleiro;

public class SetorOculto extends Setor{
    
    public SetorOculto(){}
    
    public SetorOculto(int coordenadaX, int coordenadaY, boolean ladoEsq, boolean ladoBaixo, boolean ladoDir, boolean ladoCima)
    {
        super(coordenadaX, coordenadaY, ladoEsq, ladoBaixo, ladoDir, ladoCima);
    }
    
    @Override
    public Setor getSetorPorCoordenada(String coordenada, Tabuleiro tabu) {
        return super.getSetorPorCoordenada(coordenada, tabu); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void initSetor(String posicaoNova, Tabuleiro tabu, int comando, Setor novoSetor) {
        super.initSetor(posicaoNova, tabu, comando, novoSetor); //To change body of generated methods, choose Tools | Templates.
    }
}