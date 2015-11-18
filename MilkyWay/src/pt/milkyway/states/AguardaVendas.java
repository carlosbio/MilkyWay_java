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

import pt.milkyway.logic.CartaPlaneta;
import pt.milkyway.logic.CartaPirata;
import pt.milkyway.logic.Cubo;
import pt.milkyway.logic.CuboAmarelo;
import pt.milkyway.logic.CuboAzul;
import pt.milkyway.logic.CuboPreto;
import pt.milkyway.logic.CuboVermelho;
import pt.milkyway.logic.Jogo;

public class AguardaVendas extends EstadosAdapter   // Classe correspondente ao estado em que é possível vender recursos
{
    public AguardaVendas(Jogo jogo) // Construtor
    {
        super(jogo);
    }
    
    @Override
    public IEstados vendeCargo(Cubo c,int n)  // Método que valida a venda de material
    {
        int pos=getJogo().getPosicao(); // Guarda a posição actual da nave
        
        if(getJogo().getMapa().get(pos) instanceof CartaPirata) // Se for um Planeta Pirata, só pode comprar um tipo de recurso
        {
            CartaPirata cp=(CartaPirata)getJogo().getMapa().get(pos);   // Downcast
            
            if(c instanceof CuboAzul&&"agua".equals(cp.getRecurso()))  // Se a água for comercializável neste planeta
            {
                getJogo().setDinheiro(getJogo().getDinheiro()+4);   // Soma 4 moedas ao dinheiro
                
                if(n==1)
                    getJogo().getNave().removeCargo1(); // Remove o recurso do porão, dependendo de onde estiver
                else if(n==2)
                    getJogo().getNave().removeCargo2();
                else if(n==3)
                    getJogo().getNave().removeCargo3();
            }
            else if(c instanceof CuboAmarelo&&"comida".equals(cp.getRecurso()))  // Se a comida for comercializável neste planeta
            {
                getJogo().setDinheiro(getJogo().getDinheiro()+4);   // Soma 4 moedas ao dinheiro
                
                if(n==1)
                    getJogo().getNave().removeCargo1(); // Remove o recurso do porão, dependendo de onde estiver
                else if(n==2)
                    getJogo().getNave().removeCargo2();
                else if(n==3)
                    getJogo().getNave().removeCargo3();
            }
            else if(c instanceof CuboVermelho&&"medicamentos".equals(cp.getRecurso()))  // Se medicamentos forem comercializáveis neste planeta
            {
                getJogo().setDinheiro(getJogo().getDinheiro()+4);   // Soma 4 moedas ao dinheiro
                
                if(n==1)
                    getJogo().getNave().removeCargo1(); // Remove o recurso do porão, dependendo de onde estiver
                else if(n==2)
                    getJogo().getNave().removeCargo2();
                else if(n==3)
                    getJogo().getNave().removeCargo3();
            }  
        }
        else if(getJogo().getMapa().get(pos) instanceof CartaPlaneta) // Se for um Planeta Normal
        {
            CartaPlaneta cp=(CartaPlaneta)getJogo().getMapa().get(pos);   // Downcast
            
            int venda;
            
            if(c instanceof CuboAzul)   // Se for água
            {
                venda=cp.getAgua();     // Valor de referência
                
                if(!"agua".equals(cp.checkStock1()))    // Caso o stock1 seja diferente
                    venda++;
                
                if(!"agua".equals(cp.checkStock2()))    // Caso o stock2 seja diferente
                    venda++;
                
                getJogo().setDinheiro(getJogo().getDinheiro()+venda);   // Soma o valor da venda ao dinheiro
                
                if(n==1)
                    getJogo().getNave().removeCargo1(); // Remove o recurso do porão, dependendo de onde estiver
                else if(n==2)
                    getJogo().getNave().removeCargo2();
                else if(n==3)
                    getJogo().getNave().removeCargo3();                
            }
            else if(c instanceof CuboAmarelo)   // Se for comida
            {
                venda=cp.getComida();     // Valor de referência
                
                if(!"comida".equals(cp.checkStock1()))    // Caso o stock1 seja diferente
                    venda++;
                
                if(!"comida".equals(cp.checkStock2()))    // Caso o stock2 seja diferente
                    venda++;
                
                getJogo().setDinheiro(getJogo().getDinheiro()+venda);   // Soma o valor da venda ao dinheiro
                
                if(n==1)
                    getJogo().getNave().removeCargo1(); // Remove o recurso do porão, dependendo de onde estiver
                else if(n==2)
                    getJogo().getNave().removeCargo2();
                else if(n==3)
                    getJogo().getNave().removeCargo3();                
            }
            else if(c instanceof CuboVermelho)   // Se for medicamentos
            {
                venda=cp.getMedicamentos();     // Valor de referência
                
                if(!"medicamentos".equals(cp.checkStock1()))    // Caso o stock1 seja diferente
                    venda++;
                
                if(!"medicamentos".equals(cp.checkStock2()))    // Caso o stock2 seja diferente
                    venda++;
                
                getJogo().setDinheiro(getJogo().getDinheiro()+venda);   // Soma o valor da venda ao dinheiro
                
                if(n==1)
                    getJogo().getNave().removeCargo1(); // Remove o recurso do porão, dependendo de onde estiver
                else if(n==2)
                    getJogo().getNave().removeCargo2();
                else if(n==3)
                    getJogo().getNave().removeCargo3();                
            }
            else if(c instanceof CuboPreto)   // Se for contrabando
            {
                venda=cp.getContrabando();     // Valor de referência
                
                if(!"contrabando".equals(cp.checkStock1()))    // Caso o stock1 seja diferente
                    venda++;
                
                if(!"contrabando".equals(cp.checkStock2()))    // Caso o stock2 seja diferente
                    venda++;
                
                getJogo().setDinheiro(getJogo().getDinheiro()+venda);   // Soma o valor da venda ao dinheiro
                
                if(n==1)
                    getJogo().getNave().removeCargo1(); // Remove o recurso do porão, dependendo de onde estiver
                else if(n==2)
                    getJogo().getNave().removeCargo2();
                else if(n==3)
                    getJogo().getNave().removeCargo3();                
            }
        }
        
        return this; 
    }

    @Override
    public IEstados terminaVendas() // Método que devolve um objecto correspondente ao estado de compras
    { 
        return new AguardaCompras(getJogo());
    }
}