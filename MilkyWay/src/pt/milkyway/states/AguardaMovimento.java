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

import static pt.milkyway.logic.Base.debugMode;
import pt.milkyway.logic.Carta;
import pt.milkyway.logic.CartaPirata;
import pt.milkyway.logic.CartaVirada;
import pt.milkyway.logic.CartaWormhole;
import pt.milkyway.logic.Jogo;
import pt.milkyway.utils.utils;

public class AguardaMovimento extends EstadosAdapter    // Classe correspondente ao estado de movimento da nave no mapa
{
    public AguardaMovimento(Jogo jogo)  // Construtor
    {
        super(jogo);
    }
    
    @Override
    public IEstados terminaJogo()
    { 
        if(getJogo().getDinheiro()<1)
            return null;
        
        for(Carta c : getJogo().getMapa())
        {
            if(c instanceof CartaVirada)
                return this;
        }
        
        getJogo().setVitoria(true);
        
        return null; 
    }

    @Override
    public IEstados moveNave(int p) // Método que actualiza a posição actual da nave
    { 
        getJogo().setPosicao(p);    // Define a nova posição
        
        if(!(getJogo().getMapa().get(p) instanceof CartaWormhole))    // Se não for Wormhole    
            getJogo().setDinheiro(getJogo().getDinheiro()-1);   // Desconta uma moeda por posição
        
        if(getJogo().getMapa().get(p) instanceof CartaPirata)   // Caso a posição actual corresponda a uma Planeta Pirata
            sentinelasPiratas();
        
        return this;
    }
    
    public void sentinelasPiratas() // Método que correponde aos sentinelas piratas estacionados em órbita dos Planetas Piratas
    {
        for(int i=0;i<2;i++)
        {
            getJogo().setDadopreto(utils.randInt(1,6)); // Lança o dado preto para determinar a energia dos piratas

            if(getJogo().getDadopreto()>getJogo().getNave().getEnergia())   // Se for superior à energia da nave
            {
                int resgate=getJogo().getDadopreto()-getJogo().getNave().getEnergia();  // Calcula resgate

                getJogo().setDinheiro(getJogo().getDinheiro()-resgate); // Desconta valor do resgate
                
                getJogo().setResgate(true);

                if(debugMode)
                    System.out.println("\nRESGATE PIRATA!!");   // Debug only
            }
        }        
    }
    
    @Override
    public IEstados terminaMover() // Método que devolve um objecto correspondente ao estado de exploração
    { 
        return new AguardaExploracao(getJogo());
    }
}