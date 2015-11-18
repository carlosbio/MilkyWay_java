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

import pt.milkyway.logic.Jogo;

public class AguardaPreparacao extends EstadosAdapter   // Classe correpondente à fase de preparação de um novo jogo
{
    public AguardaPreparacao(Jogo jogo) // Construtor
    {
        super(jogo);
    }
    
    @Override
    public IEstados nomeJogador(String n)   // Método que define o nome de jogador no actual estado
    {
        getJogo().setJogador(n);
        
        return this;
    }
    
    @Override
    public IEstados comecarJogo()   // Método que devolve um objecto correspondente ao estado de exploração
    { 
        return new AguardaExploracao(getJogo()); 
    }
}