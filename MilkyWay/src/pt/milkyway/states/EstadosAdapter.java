// ------------------------------------------------------------------------------------ //
//                                                                                      //
//          Programa: Milky Way - Jogo de tabuleiro de comércio espacial                //
//                                                                                      //
//                          Autor: Carlos da Silva                                      //
//                                                                                      //
//                              Data: 04/05/2015                                        //
//                                                                                      //
// ------------------------------------------------------------------------------------ //

package pt.milkyway.states;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import pt.milkyway.logic.*;

public class EstadosAdapter implements IEstados, Serializable   // Classe que implementa a interface e é base dos diversos estados
{
    private Jogo jogo;  // Inclusão de uma referência para o objecto jogo
    
    public EstadosAdapter(Jogo jogo)    // Construtor
    {
        this.jogo = jogo;
    }
    
    @Override
    public Jogo getJogo()   // Método que permite obter o jogo
    {
        return jogo;
    }
    
    public void setJogo(Jogo jogo)  // Método que permite definir o jogo
    {
        if (jogo == null) 
            return;
        if (jogo == this.jogo) 
            return;
        
        this.jogo = jogo;
    }
    
    @Override
    public IEstados comecarJogo() { return this; }
    
    @Override
    public IEstados novoJogo() { return this; };
    
    @Override
    public IEstados loadJogo() throws FileNotFoundException, IOException { return this; }
    
    @Override
    public IEstados gravaJogo() { return this; }

    @Override
    public IEstados nomeJogador(String n) { return this; }
    
    @Override
    public IEstados terminaMover() { return this; }

    @Override
    public IEstados virarCarta() { return this; }

    @Override
    public IEstados reporStocks() { return this; }

    @Override
    public IEstados reporPirata() { return this; }

    @Override
    public IEstados removerBrancos() { return this; }

    @Override
    public IEstados reporPlaneta() { return this; }

    @Override
    public IEstados transporteContrabando() { return this; }

    @Override
    public IEstados terminaStocks() { return this; }

    @Override
    public IEstados vendeCargo(Cubo c,int n) { return this; }

    @Override
    public IEstados terminaVendas() { return this; }

    @Override
    public IEstados compraCargo(Cubo c, int n) { return this; }

    @Override
    public IEstados efectuaUpgrade(int f) { return this; }

    @Override
    public IEstados terminaCompras() { return this; }

    @Override
    public IEstados terminaJogo() { return this; }

    @Override
    public IEstados moveNave(int p) { return this; }
}