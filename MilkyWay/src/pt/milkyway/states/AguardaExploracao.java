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

public class AguardaExploracao extends EstadosAdapter   // Classe correspondente ao estado de exploração do getJogo().getMapa()
{
    public AguardaExploracao(Jogo jogo) // Construtor
    {
        super(jogo);
    }
    
    @Override
    public IEstados virarCarta()    // Método que vira as cartas adjacentes à carta actual
    {        
        if(getJogo().getPosicao()==0)
        {
            getJogo().getMapa().set(1,getJogo().getBaralho().get(1));
        }
        else if(getJogo().getPosicao()==1)
        {
            getJogo().getMapa().set(0,getJogo().getBaralho().get(0));
            getJogo().getMapa().set(2,getJogo().getBaralho().get(2));
            getJogo().getMapa().set(3,getJogo().getBaralho().get(3));
        }
        else if(getJogo().getPosicao()==2)
        {
            getJogo().getMapa().set(1,getJogo().getBaralho().get(1));
            getJogo().getMapa().set(3,getJogo().getBaralho().get(3));
            getJogo().getMapa().set(4,getJogo().getBaralho().get(4));
        }
        else if(getJogo().getPosicao()==3)
        {
            getJogo().getMapa().set(1,getJogo().getBaralho().get(1));
            getJogo().getMapa().set(2,getJogo().getBaralho().get(2));
            getJogo().getMapa().set(4,getJogo().getBaralho().get(4));
            getJogo().getMapa().set(5,getJogo().getBaralho().get(5));
            getJogo().getMapa().set(6,getJogo().getBaralho().get(6));
        }
        else if(getJogo().getPosicao()==4)
        {
            getJogo().getMapa().set(2,getJogo().getBaralho().get(2));
            getJogo().getMapa().set(3,getJogo().getBaralho().get(3));
            getJogo().getMapa().set(5,getJogo().getBaralho().get(5));
            getJogo().getMapa().set(6,getJogo().getBaralho().get(6));
            getJogo().getMapa().set(7,getJogo().getBaralho().get(7));
        }
        else if(getJogo().getPosicao()==5)
        {
            getJogo().getMapa().set(3,getJogo().getBaralho().get(3));
            getJogo().getMapa().set(4,getJogo().getBaralho().get(4));
            getJogo().getMapa().set(6,getJogo().getBaralho().get(6));
            getJogo().getMapa().set(8,getJogo().getBaralho().get(8));
        }
        else if(getJogo().getPosicao()==6)
        {
            getJogo().getMapa().set(3,getJogo().getBaralho().get(3));
            getJogo().getMapa().set(4,getJogo().getBaralho().get(4));
            getJogo().getMapa().set(5,getJogo().getBaralho().get(5));
            getJogo().getMapa().set(7,getJogo().getBaralho().get(7));
            getJogo().getMapa().set(8,getJogo().getBaralho().get(8));
            getJogo().getMapa().set(9,getJogo().getBaralho().get(9));
        }
        else if(getJogo().getPosicao()==7)
        {
            getJogo().getMapa().set(4,getJogo().getBaralho().get(4));
            getJogo().getMapa().set(6,getJogo().getBaralho().get(6));
            getJogo().getMapa().set(8,getJogo().getBaralho().get(8));
            getJogo().getMapa().set(9,getJogo().getBaralho().get(9));
            getJogo().getMapa().set(10,getJogo().getBaralho().get(10));
        }
        else if(getJogo().getPosicao()==8)
        {
            getJogo().getMapa().set(5,getJogo().getBaralho().get(5));
            getJogo().getMapa().set(6,getJogo().getBaralho().get(6));
            getJogo().getMapa().set(7,getJogo().getBaralho().get(7));
            getJogo().getMapa().set(9,getJogo().getBaralho().get(9));
            getJogo().getMapa().set(11,getJogo().getBaralho().get(11));
            getJogo().getMapa().set(12,getJogo().getBaralho().get(12));
            getJogo().getMapa().set(13,getJogo().getBaralho().get(13));
        }
        else if(getJogo().getPosicao()==9)
        {
            getJogo().getMapa().set(6,getJogo().getBaralho().get(6));
            getJogo().getMapa().set(7,getJogo().getBaralho().get(7));
            getJogo().getMapa().set(8,getJogo().getBaralho().get(8));
            getJogo().getMapa().set(10,getJogo().getBaralho().get(10));
            getJogo().getMapa().set(12,getJogo().getBaralho().get(12));
            getJogo().getMapa().set(13,getJogo().getBaralho().get(13));
        }
        else if(getJogo().getPosicao()==10)
        {
            getJogo().getMapa().set(7,getJogo().getBaralho().get(7));
            getJogo().getMapa().set(9,getJogo().getBaralho().get(9));
            getJogo().getMapa().set(13,getJogo().getBaralho().get(13));
        }
        else if(getJogo().getPosicao()==11)
        {
            getJogo().getMapa().set(8,getJogo().getBaralho().get(8));
            getJogo().getMapa().set(12,getJogo().getBaralho().get(12));
            getJogo().getMapa().set(14,getJogo().getBaralho().get(14));
            getJogo().getMapa().set(15,getJogo().getBaralho().get(15));
            getJogo().getMapa().set(16,getJogo().getBaralho().get(16));
        }
        else if(getJogo().getPosicao()==12)
        {
            getJogo().getMapa().set(8,getJogo().getBaralho().get(8));
            getJogo().getMapa().set(9,getJogo().getBaralho().get(9));
            getJogo().getMapa().set(11,getJogo().getBaralho().get(11));
            getJogo().getMapa().set(13,getJogo().getBaralho().get(13));
            getJogo().getMapa().set(15,getJogo().getBaralho().get(15));
            getJogo().getMapa().set(16,getJogo().getBaralho().get(16));
        }
        else if(getJogo().getPosicao()==13)
        {
            getJogo().getMapa().set(8,getJogo().getBaralho().get(8));
            getJogo().getMapa().set(9,getJogo().getBaralho().get(9));
            getJogo().getMapa().set(10,getJogo().getBaralho().get(10));
            getJogo().getMapa().set(12,getJogo().getBaralho().get(12));
            getJogo().getMapa().set(16,getJogo().getBaralho().get(16));
        }
        else if(getJogo().getPosicao()==14)
        {
            getJogo().getMapa().set(11,getJogo().getBaralho().get(11));
            getJogo().getMapa().set(15,getJogo().getBaralho().get(15));
            getJogo().getMapa().set(17,getJogo().getBaralho().get(17));
        }
        else if(getJogo().getPosicao()==15)
        {
            getJogo().getMapa().set(11,getJogo().getBaralho().get(11));
            getJogo().getMapa().set(12,getJogo().getBaralho().get(12));
            getJogo().getMapa().set(14,getJogo().getBaralho().get(14));
            getJogo().getMapa().set(16,getJogo().getBaralho().get(16));
            getJogo().getMapa().set(17,getJogo().getBaralho().get(17));
            getJogo().getMapa().set(18,getJogo().getBaralho().get(18));
        }
        else if(getJogo().getPosicao()==16)
        {
            getJogo().getMapa().set(11,getJogo().getBaralho().get(11));
            getJogo().getMapa().set(12,getJogo().getBaralho().get(12));
            getJogo().getMapa().set(13,getJogo().getBaralho().get(13));
            getJogo().getMapa().set(15,getJogo().getBaralho().get(15));
            getJogo().getMapa().set(17,getJogo().getBaralho().get(17));
            getJogo().getMapa().set(18,getJogo().getBaralho().get(18));
            getJogo().getMapa().set(19,getJogo().getBaralho().get(19));
        }
        else if(getJogo().getPosicao()==17)
        {
            getJogo().getMapa().set(14,getJogo().getBaralho().get(14));
            getJogo().getMapa().set(15,getJogo().getBaralho().get(15));
            getJogo().getMapa().set(16,getJogo().getBaralho().get(16));
            getJogo().getMapa().set(18,getJogo().getBaralho().get(18));
            getJogo().getMapa().set(20,getJogo().getBaralho().get(20));
        }
        else if(getJogo().getPosicao()==18)
        {
            getJogo().getMapa().set(15,getJogo().getBaralho().get(15));
            getJogo().getMapa().set(16,getJogo().getBaralho().get(16));
            getJogo().getMapa().set(17,getJogo().getBaralho().get(17));
            getJogo().getMapa().set(19,getJogo().getBaralho().get(19));
            getJogo().getMapa().set(20,getJogo().getBaralho().get(20));
            getJogo().getMapa().set(21,getJogo().getBaralho().get(21));
        }
        else if(getJogo().getPosicao()==19)
        {
            getJogo().getMapa().set(16,getJogo().getBaralho().get(16));
            getJogo().getMapa().set(18,getJogo().getBaralho().get(18));
            getJogo().getMapa().set(20,getJogo().getBaralho().get(20));
            getJogo().getMapa().set(21,getJogo().getBaralho().get(21));
        }
        else if(getJogo().getPosicao()==20)
        {
            getJogo().getMapa().set(17,getJogo().getBaralho().get(17));
            getJogo().getMapa().set(18,getJogo().getBaralho().get(18));
            getJogo().getMapa().set(19,getJogo().getBaralho().get(19));
            getJogo().getMapa().set(21,getJogo().getBaralho().get(21));
            getJogo().getMapa().set(22,getJogo().getBaralho().get(22));
        }
        else if(getJogo().getPosicao()==21)
        {
            getJogo().getMapa().set(18,getJogo().getBaralho().get(18));
            getJogo().getMapa().set(19,getJogo().getBaralho().get(19));
            getJogo().getMapa().set(20,getJogo().getBaralho().get(20));
            getJogo().getMapa().set(22,getJogo().getBaralho().get(22));
            getJogo().getMapa().set(23,getJogo().getBaralho().get(23));
        }
        else if(getJogo().getPosicao()==22)
        {
            getJogo().getMapa().set(20,getJogo().getBaralho().get(20));
            getJogo().getMapa().set(21,getJogo().getBaralho().get(21));
            getJogo().getMapa().set(23,getJogo().getBaralho().get(23));
        }
        else if(getJogo().getPosicao()==23)
        {
            getJogo().getMapa().set(21,getJogo().getBaralho().get(21));
            getJogo().getMapa().set(22,getJogo().getBaralho().get(22));
            getJogo().getMapa().set(24,getJogo().getBaralho().get(24));
        }
        else if(getJogo().getPosicao()==24)
        {
            getJogo().getMapa().set(23,getJogo().getBaralho().get(23));
        }
        
        return this; 
    }
    
    @Override
    public IEstados gravaJogo()
    {
        return new AguardaInicio(getJogo());
    }
    
    @Override
    public IEstados reporStocks()   // Método que devolve um objecto correspondente ao estado de reposição de stocks
    { 
        return new AguardaStocks(getJogo()); 
    }
}