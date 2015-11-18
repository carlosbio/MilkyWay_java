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
import java.util.logging.Level;
import java.util.logging.Logger;

import pt.milkyway.logic.Jogo;

public class AguardaInicio extends EstadosAdapter   // Classe correspondente ao estado em que é possível iniciar, gravar e carregar um jogo
{
    public AguardaInicio(Jogo jogo) // Construtor
    {
        super(jogo);
    }
    
    @Override
    public IEstados novoJogo()  // Método que devolve um objecto correspondente ao estado de preparação de um novo jogo
    {        
        return new AguardaPreparacao(getJogo());
    }
    
    @Override
    public IEstados loadJogo() throws FileNotFoundException // Método que permite carregar um jogo gravado em ficheiro
    {
        setJogo(getJogo().carregaInstanciaJogo("jogo.db"));
        
        // Como o jogo foi gravado com o estado OpçõesJogo, é preciso forçar o novo estado
        getJogo().setEstadoActual(new AguardaExploracao(getJogo()));
        
        // Retorno não interessa porque o Jogo vai ser substituído
        return this;
    }
    
    @Override
    public IEstados gravaJogo() // Método que permite gravar um jogo em ficheiro
    {
        try {
            getJogo().gravaInstanciaJogo("jogo.db");
        } catch (IOException ex) {
            // ToDo: Deverá mostrar erros no interface
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);            
        }
        
        return this;    // Fica onde estava
    }
}