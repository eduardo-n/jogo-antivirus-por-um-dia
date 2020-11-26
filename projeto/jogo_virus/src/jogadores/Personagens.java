package jogadores;

import java.util.Random;
import java.util.Scanner;
import tabuleiro.Setor;
import tabuleiro.SetorNormal;
import tabuleiro.Tabuleiro;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Personagens {
    
    protected int ataque;
    protected int defesa;
    
    public Personagens(){}
    
    public Personagens(int ataque, int defesa)
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
    
    public int atacar(int jogador, Personagens jogadorP, Personagens jogadorPP, Tabuleiro tabu)
    {
        Scanner scanf = new Scanner(System.in);
        
        int posicaoInimigoEscolhido = 0;
        Setor setor = new Setor();

        if(jogador == 1)
        {
            setor = setor.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu);
            
            if(setor.getClass().getName().equals("tabuleiro.SetorOculto"))
            {
                // Numero aleatorio para decidir se o ataque pode ser feito ou nao, devido ao setor ser oculto
                Random random = new Random();
                int numeroRandom = random.nextInt((2 - 1)+1) + 1;
                
                if(numeroRandom == 1)
                {
                    System.out.println("\n• Este setor eh oculto e nao foi encontrado nenhum inimigo.");
                    return 1;
                }
            }
            
            // Se existir inimigos no setor
            if(setor.getInimigosDoSetor().size() > 0)
            {
                System.out.println("\nQual inimigo voce deseja atacar ?\n");
                for(int i=0;i<setor.getInimigosDoSetor().size();i++)
                {
                    System.out.println("Inimigo - "+(i+1));
                }

                try{
                    
                    System.out.print("Digite o numero: ");
                    posicaoInimigoEscolhido = scanf.nextInt()-1;
                   
                    if(posicaoInimigoEscolhido < 0 || posicaoInimigoEscolhido > setor.getInimigosDoSetor().size()+1)
                    {
                        System.out.print("\n• Digite um comando valido.");
                        return 0;
                    }

                    // Realzando o ataque ao inimigo
                    setor.getInimigosDoSetor().get(posicaoInimigoEscolhido).setDefesa(setor.getInimigosDoSetor().get(posicaoInimigoEscolhido).getDefesa() - jogadorP.getAtaque());

                    // Matando o inimigo caso ele perca toda sua defesa
                    if(setor.getInimigosDoSetor().get(posicaoInimigoEscolhido).getDefesa() <= 0)
                    {
                        setor.getInimigosDoSetor().remove(setor.getInimigosDoSetor().get(posicaoInimigoEscolhido));
                        System.out.println("\n• Inimigo "+(posicaoInimigoEscolhido+1)+" morreu");
                    }
                    
                    return 1;
                    
                }catch(Exception e){
                    
                    System.out.println("\n• Digite um comando valido.");
                    // scanf apenas para resetar o scanf anterior ( comando )
                    scanf.next();
                    return 0;
                }

            }
            else
            {
                System.out.println("\n• Nao ha inimigos neste setor, nao foi possivel executar o ataque.");
                return 0;
            }
        }
        else if(jogador == 2)
        {
            setor = setor.getSetorPorCoordenada(tabu.getPosicaoAtualP2(), tabu);

            if(setor.getClass().getName().equals("tabuleiro.SetorOculto"))
            {
                // Numero aleatorio para decidir se o ataque pode ser feito ou nao, devido ao setor ser oculto
                Random random = new Random();
                int numeroRandom = random.nextInt((2 - 1)+1) + 1;
                
                if(numeroRandom == 1)
                {
                    System.out.println("\n• Este setor eh oculto e nao foi encontrado nenhum inimigo.");
                    return 1;
                }
            }
            
            // Se existir inimigos no setor
            if(setor.getInimigosDoSetor().size() > 0)
            {
                System.out.println("\nQual inimigo voce deseja atacar ?");
                for(int i=0;i<setor.getInimigosDoSetor().size();i++)
                {
                    System.out.println("Inimigo - "+(i+1));
                }

                System.out.print("Digite o numero: ");
                posicaoInimigoEscolhido = scanf.nextInt()-1;

                // Realzando o ataque ao inimigo
                setor.getInimigosDoSetor().get(posicaoInimigoEscolhido).setDefesa(setor.getInimigosDoSetor().get(posicaoInimigoEscolhido).getDefesa() - jogadorPP.getAtaque());

                // Matando o inimigo caso ele perca toda sua defesa
                if(setor.getInimigosDoSetor().get(posicaoInimigoEscolhido).getDefesa() <= 0)
                {
                    setor.getInimigosDoSetor().remove(setor.getInimigosDoSetor().get(posicaoInimigoEscolhido));
                    System.out.println("• Inimigo "+(posicaoInimigoEscolhido+1)+" morreu");
                }

                return 1;
            }
            else
            {
                System.out.println("• Nao ha inimigos neste setor, nao foi possivel executar o ataque.");
                return 0;
            }
        }
        else // ataque do inimigo
        {
            Setor setorP1 = setor.getSetorPorCoordenada(tabu.getPosicaoAtualP1(), tabu);
            Setor setorP2 = setor.getSetorPorCoordenada(tabu.getPosicaoAtualP2(), tabu);
            
            // Verificando se P1 está morto ou não
            if(!tabu.getPosicaoAtualP1().equals(""))
            {
                // Ataque dos inimigos ao P1;
                for(int i=0;i<setorP1.getInimigosDoSetor().size();i++)
                {
                    // Numero aleatorio para decidir se o ataque pode ser feito ou nao, devido ao setor ser oculto
                    Random random = new Random();
                    int numeroRandom = random.nextInt((2 - 1)+1) + 1;

                    if(numeroRandom == 1)
                    {
                        System.out.println("• Inimigo "+(i+1)+" nao conseguiu atacar P1");
                    }
                    else
                    {
                        System.out.println("• Inimigo "+(i+1)+" atacou P1.");
                        jogadorP.setDefesa(jogadorP.getDefesa() - setorP1.getInimigosDoSetor().get(i).getAtaque());
                    }
                }
                
                // matando o jogador caso acabe suas defesas
                if(jogadorP.getDefesa() <= 0)
                {
                    tabu.setPosicaoAtualP1("");
                }
            }

            // Verificando se P2 está morto ou não
            if(!tabu.getPosicaoAtualP2().equals(""))
            {    
                // Ataque dos inimigos ao P2; 
                for(int i=0;i<setorP2.getInimigosDoSetor().size();i++)
                {
                    // Numero aleatorio para decidir se o ataque pode ser feito ou nao, devido ao setor ser oculto
                    Random random = new Random();
                    int numeroRandom = random.nextInt((2 - 1)+1) + 1;

                    if(numeroRandom == 1)
                    {
                        System.out.println("• Inimigo "+(i+1)+" nao conseguiu atacar P2");
                    }
                    else
                    {
                        System.out.println("• Inimigo "+(i+1)+" atacou P2.");
                        jogadorPP.setDefesa(jogadorPP.getDefesa() - setorP2.getInimigosDoSetor().get(i).getAtaque());
                    }
                }
                
                // matando o jogador caso acabe suas defesas
                if(jogadorPP.getDefesa() <= 0)
                {
                    tabu.setPosicaoAtualP2("");
                    System.out.println("• Jogador P2 morreu.");
                }
            }

            return 1;
        }
    }    
}