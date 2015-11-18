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

import pt.milkyway.logic.CartaPirata;
import pt.milkyway.logic.CartaPlaneta;
import pt.milkyway.logic.Cubo;
import pt.milkyway.logic.CuboAmarelo;
import pt.milkyway.logic.CuboAzul;
import pt.milkyway.logic.CuboVermelho;
import pt.milkyway.logic.Jogo;

public class AguardaCompras extends EstadosAdapter  // Classe correpondente ao estado em que é possível comprar recursos e efectuar upgrades
{
    public AguardaCompras(Jogo jogo)    // Construtor
    {
        super(jogo);
    }
    
    @Override
    public IEstados compraCargo(Cubo c,int n)   // Método que valida a compra de recursos (recebe recurso e local do stock)
    { 
        int pos=getJogo().getPosicao(); // Guarda a posição actual da nave
        
        if(getJogo().getMapa().get(pos) instanceof CartaPirata) // Se for um Planeta Pirata
        {
            CartaPirata cp=(CartaPirata)getJogo().getMapa().get(pos);   // Downcast
            
            cp.removeStock();   // Remove o stock de contrabando do Planeta Pirata
            
            if(getJogo().getNave().getCargo1()==null)   // Se o porão 1 estiver livre
                getJogo().getNave().addCargo1(c);
            else if(getJogo().getNave().getCargo2()==null)  // Se o porão 2 estiver livre
                getJogo().getNave().addCargo2(c);
            else if(getJogo().getNave().getCargo3()==null&&getJogo().getNave().getCapacidade()==3)  //Se o porão 3 estiver livre e disponível
                getJogo().getNave().addCargo3(c);
            
            getJogo().setDinheiro(getJogo().getDinheiro()-3);   // Desconta 3 moedas do dinheiro após a compra            
        }
        else    // Se for um Planeta Normal
        {
            CartaPlaneta cp=(CartaPlaneta)getJogo().getMapa().get(pos);   // Downcast
            
            if(n==1)
                cp.removeStock1();   // Remove o stock1 do Planeta Normal
            else
                cp.removeStock2();   // Remove o stock2 do Planeta Normal
            
            if(getJogo().getNave().getCargo1()==null)   // Se o porão 1 estiver livre
                getJogo().getNave().addCargo1(c);
            else if(getJogo().getNave().getCargo2()==null)  // Se o porão 2 estiver livre
                getJogo().getNave().addCargo2(c);
            else if(getJogo().getNave().getCargo3()==null&&getJogo().getNave().getCapacidade()==3)  //Se o porão 3 estiver livre e disponível
                getJogo().getNave().addCargo3(c);
            
            if(c instanceof CuboAzul)   // Água
            {
                int compra=cp.getAgua();    // Mercado local para o recurso
                
                getJogo().setDinheiro(getJogo().getDinheiro()-compra);   // Desconta o valor da compra
            }
            else if(c instanceof CuboAmarelo)   // Comida
            {
                int compra=cp.getComida();    // Mercado local para o recurso
                
                getJogo().setDinheiro(getJogo().getDinheiro()-compra);   // Desconta o valor da compra
            }
            else if(c instanceof CuboVermelho)  // Medicamentos
            {
                int compra=cp.getMedicamentos();    // Mercado local para o recurso
                
                getJogo().setDinheiro(getJogo().getDinheiro()-compra);   // Desconta o valor da compra
            }
        }
        
        return this; 
    }

    @Override
    public IEstados efectuaUpgrade(int f)    // Método que valida a compra de upgrades da nave (f - flag de equipamento)
    { 
        if(f==1)
        {
            getJogo().getNave().setCapacidade(3);   // Flag 1 - Upgrade de porão
            getJogo().setDinheiro(getJogo().getDinheiro()-3);   // Desconta o valor do upgrade
        }
        else if(f==2)
        {
            getJogo().getNave().setEnergia(4);  // Flag 2 - 1º Upgrade de energia de combate
            getJogo().setDinheiro(getJogo().getDinheiro()-4);   // Desconta o valor do upgrade
        }
        else if(f==3)
        {
            getJogo().getNave().setEnergia(5);  // Flag 3 - 2º Upgrade de energia de combate
            getJogo().setDinheiro(getJogo().getDinheiro()-5);   // Desconta o valor do upgrade
        }
        
        return this; 
    }
    
    @Override
    public IEstados terminaCompras() // Método que devolve um objecto correspondente ao estado de movimento
    { 
        return new AguardaMovimento(getJogo());
    }
}