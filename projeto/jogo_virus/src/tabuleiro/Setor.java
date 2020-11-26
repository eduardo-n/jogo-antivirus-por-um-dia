/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabuleiro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import jogadores.Inimigo;
import jogadores.Personagens;

public class Setor {
    
    int coordenadaX;
    int coordenadaY;
    boolean ladoEsq;
    boolean ladoBaixo;
    boolean ladoDir;
    boolean ladoCima;
    public ArrayList<Inimigo> inimigosDoSetor = new ArrayList<Inimigo>();
    
    public Setor(){}

    public Setor(int coordenadaX, int coordenadaY, boolean ladoEsq, boolean ladoBaixo, boolean ladoDir, boolean ladoCima)
    {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.ladoEsq = ladoEsq;
        this.ladoBaixo = ladoBaixo;
        this.ladoDir = ladoDir;
        this.ladoCima = ladoCima;
        
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public boolean isLadoEsq() {
        return ladoEsq;
    }

    public void setLadoEsq(boolean ladoEsq) {
        this.ladoEsq = ladoEsq;
    }

    public boolean isLadoBaixo() {
        return ladoBaixo;
    }

    public void setLadoBaixo(boolean ladoBaixo) {
        this.ladoBaixo = ladoBaixo;
    }

    public boolean isLadoDir() {
        return ladoDir;
    }

    public void setLadoDir(boolean ladoDir) {
        this.ladoDir = ladoDir;
    }

    public boolean isLadoCima() {
        return ladoCima;
    }

    public void setLadoCima(boolean ladoCima) {
        this.ladoCima = ladoCima;
    }

    public ArrayList<Inimigo> getInimigosDoSetor() {
        return inimigosDoSetor;
    }

    public void setInimigosDoSetor(ArrayList<Inimigo> inimigosDoSetor) {
        this.inimigosDoSetor = inimigosDoSetor;
    }

    public Setor getSetorPorCoordenada(String coordenada, tabuleiro.Tabuleiro tabu)
    {
        Iterator i = tabu.setoresVisitados.iterator();
        while(i.hasNext())
        {
            Setor setor = (Setor) i.next();            
            if((setor.getCoordenadaX()+" "+setor.getCoordenadaY()).equals(coordenada))
            {
                return setor;
            }            
        }
        return null;
    }
    
    public void initSetor(String posicaoNova, tabuleiro.Tabuleiro tabu, int comando, Setor novoSetor)
    {
        // Numero aleatorio para decidir o tipo do setor
        Random random = new Random();
        int numeroRandom = random.nextInt(3);
        
        // Convertendo e separando a String da nova Coordenada
        StringTokenizer coordenada = new StringTokenizer(posicaoNova);
        Integer x = Integer.parseInt((String)coordenada.nextElement());
        Integer y = Integer.parseInt((String)coordenada.nextElement());
        
        // Setando a coordenada do novo setor
        novoSetor.setCoordenadaX(x);
        novoSetor.setCoordenadaY(y);

        // Verificando os 4 lados para saber onde deve gerar porta ou parede      
        int naoPodeMudarLado = 0;
        Iterator i;
        
        // BLOCO - Cima
        i = tabu.ladosBloqueados.iterator();
        while(i.hasNext())
        {
            String coordenadaLista = (String) i.next();
            if(coordenadaLista.equals((x-1)+" "+y))
            {
                naoPodeMudarLado = 1;
            }            
        }       
        if(naoPodeMudarLado == 0)
        {
            // Verificar se o setor de cima já foi criado
            i = tabu.setoresVisitados.iterator();
            while(i.hasNext())
            {
                Setor setorVisitado = (Setor) i.next();
                if((setorVisitado.getCoordenadaX()+" "+setorVisitado.getCoordenadaY()).equals((x-2)+" "+y))
                {
                    naoPodeMudarLado = 1;  
                    // Setando o lado de cima do novo setor igual ao lado de baixo do setor de cima
                    novoSetor.setLadoCima(getSetorPorCoordenada((setorVisitado.getCoordenadaX()+" "+setorVisitado.getCoordenadaY()), tabu).isLadoBaixo());
                }            
            }
        }
        if(naoPodeMudarLado == 0) // Se puder mudar o lado, sera sorteado porta ou parede
        {
            numeroRandom = random.nextInt(10);
            if(numeroRandom <= 2)
            {
                novoSetor.setLadoCima(false);
            }
            else
            {
                novoSetor.setLadoCima(true);
            }
        }
        
        naoPodeMudarLado = 0; // zerando a variavel para os proximos lados
        // Fim BLOCO - Cima
        
        // BLOCO - Direita
        i = tabu.ladosBloqueados.iterator();
        while(i.hasNext())
        {
            String coordenadaLista = (String) i.next();
            if(coordenadaLista.equals((x+" "+(y+2))))
            {
                naoPodeMudarLado = 1;
            }            
        }       
        if(naoPodeMudarLado == 0)
        {
            // Verificar se o setor da direita já foi criado
            i = tabu.setoresVisitados.iterator();
            while(i.hasNext())
            {
                Setor setorVisitado = (Setor) i.next();
                if((setorVisitado.getCoordenadaX()+" "+setorVisitado.getCoordenadaY()).equals(x+" "+(y+4)))
                {
                    naoPodeMudarLado = 1;   
                    // Setando o lado da direita do novo setor igual ao lado da esquerda do setor da direita
                    novoSetor.setLadoDir(getSetorPorCoordenada((setorVisitado.getCoordenadaX()+" "+setorVisitado.getCoordenadaY()), tabu).isLadoEsq());
                }            
            }
        }
        if(naoPodeMudarLado == 0) // Se puder mudar o lado, sera sorteado porta ou parede
        {
            numeroRandom = random.nextInt(10);
            if(numeroRandom <= 2)
            {
                novoSetor.setLadoDir(false);
            }
            else
            {
                novoSetor.setLadoDir(true);
            }
        }
        
        naoPodeMudarLado = 0; // zerando a variavel para os proximos lados
        // Fim BLOCO - Direita
        
        // BLOCO - Baixo
        i = tabu.ladosBloqueados.iterator();
        while(i.hasNext())
        {
            String coordenadaLista = (String) i.next();
            if(coordenadaLista.equals(((x+1)+" "+y)))
            {
                naoPodeMudarLado = 1;
                //System.out.print(naoPodeMudarLado+" Teste");
            }            
        }       
        if(naoPodeMudarLado == 0)
        {
            // Verificar se o setor de baixo já foi criado
            i = tabu.setoresVisitados.iterator();
            while(i.hasNext())
            {
                Setor setorVisitado = (Setor) i.next();
                if((setorVisitado.getCoordenadaX()+" "+setorVisitado.getCoordenadaY()).equals((x+2)+" "+y))
                {
                    naoPodeMudarLado = 1;   
                    // Setando o lado de baixo do novo setor igual ao lado de cima do setor de baixo
                    novoSetor.setLadoBaixo(getSetorPorCoordenada((setorVisitado.getCoordenadaX()+" "+setorVisitado.getCoordenadaY()), tabu).isLadoCima());
                }            
            }
        }
        if(naoPodeMudarLado == 0) // Se puder mudar o lado, sera sorteado porta ou parede
        {
            numeroRandom = random.nextInt(10);
            if(numeroRandom <= 2)
            {
                novoSetor.setLadoBaixo(false);
            }
            else
            {
                novoSetor.setLadoBaixo(true);
            }
        }
        
        naoPodeMudarLado = 0; // zerando a variavel para os proximos lados
        // Fim BLOCO - Baixo
        
        // BLOCO - Esquerda
        i = tabu.ladosBloqueados.iterator();
        while(i.hasNext())
        {
            String coordenadaLista = (String) i.next();
            if(coordenadaLista.equals((x+" "+(y-2))))
            {
                naoPodeMudarLado = 1;
            }            
        }       
        if(naoPodeMudarLado == 0)
        {
            // Verificar se o setor da esquerda já foi criado
            i = tabu.setoresVisitados.iterator();
            while(i.hasNext())
            {
                Setor setorVisitado = (Setor) i.next();
                if((setorVisitado.getCoordenadaX()+" "+setorVisitado.getCoordenadaY()).equals(x+" "+(y-4)))
                {
                    naoPodeMudarLado = 1;   
                    // Setando o lado da esquerda do novo setor igual ao lado da direita do setor da esquerda
                    novoSetor.setLadoEsq(getSetorPorCoordenada((setorVisitado.getCoordenadaX()+" "+setorVisitado.getCoordenadaY()), tabu).isLadoDir());
                }            
            }
        }
        
        if(naoPodeMudarLado == 0) // Se puder mudar o lado, sera sorteado porta ou parede
        {
            numeroRandom = random.nextInt(10);
            if(numeroRandom <= 2)
            {
                novoSetor.setLadoEsq(false);
            }
            else
            {
                novoSetor.setLadoEsq(true);
            }
        }
        // Fim BLOCO - Esquerda

        // Gerando e armazenando os inimigos do Setor
        numeroRandom = random.nextInt((5 - 1)+1) + 1;
        int numeroRandomAtkDef;
        for(int j=0; j<numeroRandom; j++)
        {
            numeroRandomAtkDef = random.nextInt((3 - 1)+1) + 1;
            Inimigo inimigo = new Inimigo(numeroRandomAtkDef,numeroRandomAtkDef);
            // Adicionando inimigo na lista do Setor
            novoSetor.inimigosDoSetor.add(inimigo);
        }
        
        // Adicionando o novo setor na lista
        tabu.setoresVisitados.add(novoSetor);
    }
}
   
/*
   012345678901234567890
0  |---|---|---|---|---|
1  |   |   |   |   |   |
2  |---|---|---|---|---|
3  |   |   |   |   |   |
4  |---|---|-*-|---|---|
5  |   |   *   *   |   |
6  |---|---|-*-|-*-|---|
7  |   |   |   | 1 |   |
8  |---|---|---|---|---|
9  |   |   |   |   |   |
10 |---|---|---|---|---|

*/
