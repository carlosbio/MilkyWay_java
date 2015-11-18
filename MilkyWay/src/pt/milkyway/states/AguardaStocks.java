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
import pt.milkyway.logic.CartaPlaneta;
import pt.milkyway.logic.CuboAmarelo;
import pt.milkyway.logic.CuboAzul;
import pt.milkyway.logic.CuboBranco;
import pt.milkyway.logic.CuboPreto;
import pt.milkyway.logic.CuboVermelho;
import pt.milkyway.logic.Jogo;
import pt.milkyway.utils.utils;

public class AguardaStocks extends EstadosAdapter   // Classe correspondente ao estado de reposição de stocks dos planetas
{
    public AguardaStocks(Jogo jogo) // Construtor
    {
        super(jogo);
    }
    
    @Override
    public IEstados reporPirata()   // Método que repõe os stocks nos planetas piratas descobertos
    { 
        for(Carta c : getJogo().getMapa())  // Tem de iterar no arraylist mapa, pois trata-se do baralho visível
        {
            if(c instanceof CartaPirata)    // Se for um Planeta Pirata
            {
                CartaPirata cp = (CartaPirata)c;    // Downcast
                
                if(cp.getStock()==null)   // Se o stock estiver vazio
                    cp.addStock(new CuboPreto());   // Cria um cubo preto novo e adiciona
            }
        }
        
        return this; 
    }

    @Override
    public IEstados removerBrancos()    // Método que remove os cubos brancos aplicados no turno anterior
    { 
        for(Carta c : getJogo().getMapa())  // Tem de iterar no arraylist mapa, pois trata-se do baralho visível
        {
            if(c instanceof CartaPlaneta)   // Se for um Planeta Normal
            {
                CartaPlaneta cp = (CartaPlaneta)c;  // Downcast
                
                if(cp.getStock1() instanceof CuboBranco)    // Se estiver um cubo branco em stock1
                    cp.removeStock1();
                    
                if(cp.getStock2() instanceof CuboBranco)    // Se estiver um cubo branco em stock2
                    cp.removeStock2();
            }
        }
        
        return this;
    }

    @Override
    public IEstados reporPlaneta()
    {
        for(Carta c : getJogo().getMapa())  // Tem de iterar no arraylist mapa, pois trata-se do baralho visível
        {
            if(c instanceof CartaPlaneta)   // Se for um Planeta Normal
            {
                CartaPlaneta cp = (CartaPlaneta)c;  // Downcast
                
                if(cp.getStock1()==null)    // Se o stock1 estiver vazio
                {
                    getJogo().setDadocores(utils.randInt(1,6)); // Lança o dado de cores (Branco(1)(2), Preto(3), Azul(4), Amarelo(5), Vermelho(6))
                    
                    if(getJogo().getDadocores()==1||getJogo().getDadocores()==2)    // Se sair branco
                    {
                        cp.addStock1(new CuboBranco());   // Cria um cubo branco novo e adiciona
                        
                        corBranca();    // Chama o método que confisca contrabando e cobra multa a nível de alfândega
                    }
                    else if(getJogo().getDadocores()==3)    // Se sair preto (ataque pirata)
                    {
                        corPreta1(cp);   // Chama o método de combate pirata (stock1)
                    }
                    else if(getJogo().getDadocores()==4)    // Se sair azul
                    {
                        cp.addStock1(new CuboAzul());   // Cria um cubo azul novo e adiciona
                    }
                    else if(getJogo().getDadocores()==5)    // Se sair amarelo
                    {
                        cp.addStock1(new CuboAmarelo());   // Cria um cubo amarelo novo e adiciona
                    }
                    else if(getJogo().getDadocores()==6)    // Se sair vermelho
                    {
                        cp.addStock1(new CuboVermelho());   // Cria um cubo vermelho novo e adiciona
                    }
                }
                
                if(cp.getStock2()==null)    // Se o stock2 estiver vazio
                {
                    getJogo().setDadocores(utils.randInt(1,6)); // Lança o dado de cores (Branco(1)(2), Preto(3), Azul(4), Amarelo(5), Vermelho(6))
                    
                    if(getJogo().getDadocores()==1||getJogo().getDadocores()==2)    // Se sair branco
                    {
                        cp.addStock2(new CuboBranco());   // Cria um cubo branco novo e adiciona
                        
                        corBranca();    // Chama o método que confisca contrabando e cobra multa a nível de alfândega
                    }
                    else if(getJogo().getDadocores()==3)    // Se sair preto (ataque pirata)
                    {
                        corPreta2(cp);   // Chama o método de combate pirata (stock2)
                    }
                    else if(getJogo().getDadocores()==4)    // Se sair azul
                    {
                        cp.addStock2(new CuboAzul());   // Cria um cubo azul novo e adiciona
                    }
                    else if(getJogo().getDadocores()==5)    // Se sair amarelo
                    {
                        cp.addStock2(new CuboAmarelo());   // Cria um cubo amarelo novo e adiciona
                    }
                    else if(getJogo().getDadocores()==6)    // Se sair vermelho
                    {
                        cp.addStock2(new CuboVermelho());   // Cria um cubo vermelho novo e adiciona
                    }
                }
            }
        }        
        
        return this;
    }
    
    public void corBranca() // Método que confisca contrabando e cobra multa a nível de alfândega
    {
        int flagdebug=0;    // Para efeitos de debug

        if(getJogo().getNave().getCargo1() instanceof CuboPreto)    // Se houver contrabanco em cargo1
        {
            getJogo().getNave().removeCargo1();                 // O contrabando é confiscado
            getJogo().setDinheiro(getJogo().getDinheiro()-4);   // Pagamento de multa de 4 moedas
            getJogo().setConfiscado(true);
            flagdebug=1;
        }

        if(getJogo().getNave().getCargo2() instanceof CuboPreto)    // Se houver contrabanco em cargo2
        {
            getJogo().getNave().removeCargo2();                 // O contrabando é confiscado
            getJogo().setDinheiro(getJogo().getDinheiro()-4);   // Pagamento de multa de 4 moedas
            getJogo().setConfiscado(true);
            flagdebug=1;
        }

        if(getJogo().getNave().getCargo3() instanceof CuboPreto)    // Se houver contrabanco em cargo3
        {
            getJogo().getNave().removeCargo3();                 // O contrabando é confiscado
            getJogo().setDinheiro(getJogo().getDinheiro()-4);   // Pagamento de multa de 4 moedas
            getJogo().setConfiscado(true);
            flagdebug=1;
        }
        
        if(debugMode&&flagdebug==1)
            System.out.println("\nCONTRABANDO CONFISCADO!!");   // Debug only
    }
    
    public void combatePiratas()    // Método combate pirata
    {
        do
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

            getJogo().setDadocores(utils.randInt(1,6)); // Lança o dado de cores
        }
        while(getJogo().getDadocores()==3); // Enquanto for preto
    }
    
    public void corPreta1(CartaPlaneta cp)   // Método resultado combate pirata para o stock1
    {
        combatePiratas();
        
        if(getJogo().getDadocores()==1||getJogo().getDadocores()==2)    // Se sair branco
        {
            cp.addStock1(new CuboBranco());   // Cria um cubo branco novo e adiciona

            corBranca();    // Chama o método que confisca contrabando e cobra multa a nível de alfândega
        }
        else if(getJogo().getDadocores()==4)    // Se sair azul
        {
            cp.addStock1(new CuboAzul());   // Cria um cubo azul novo e adiciona
        }
        else if(getJogo().getDadocores()==5)    // Se sair amarelo
        {
            cp.addStock1(new CuboAmarelo());   // Cria um cubo amarelo novo e adiciona
        }
        else if(getJogo().getDadocores()==6)    // Se sair vermelho
        {
            cp.addStock1(new CuboVermelho());   // Cria um cubo vermelho novo e adiciona
        }
    }
    
    public void corPreta2(CartaPlaneta cp)   // Método resultado combate pirata para o stock2
    {
        combatePiratas();
        
        if(getJogo().getDadocores()==1||getJogo().getDadocores()==2)    // Se sair branco
        {
            cp.addStock2(new CuboBranco());   // Cria um cubo branco novo e adiciona

            corBranca();    // Chama o método que confisca contrabando e cobra multa a nível de alfândega
        }
        else if(getJogo().getDadocores()==4)    // Se sair azul
        {
            cp.addStock2(new CuboAzul());   // Cria um cubo azul novo e adiciona
        }
        else if(getJogo().getDadocores()==5)    // Se sair amarelo
        {
            cp.addStock2(new CuboAmarelo());   // Cria um cubo amarelo novo e adiciona
        }
        else if(getJogo().getDadocores()==6)    // Se sair vermelho
        {
            cp.addStock2(new CuboVermelho());   // Cria um cubo vermelho novo e adiciona
        }
    }

    @Override
    public IEstados transporteContrabando() // Método que verifica a existência de contrabando e trata a atracção de piratas e alfândega
    { 
        if((getJogo().getNave().getCargo1() instanceof CuboPreto)
                ||(getJogo().getNave().getCargo2() instanceof CuboPreto)
                ||(getJogo().getNave().getCargo3() instanceof CuboPreto))   // Caso se verifique o transporte de contrabando
        {
            for(int i=0;i<2;i++)    // Lança o dado duas vezes
            {
                getJogo().setDadocores(utils.randInt(1,6)); // Lança o dado de cores

                if(getJogo().getDadocores()==1||getJogo().getDadocores()==2)    // Se sair branco
                    corBranca();    // Chama o método que confisca contrabando e cobra multa a nível de alfândega
                else if(getJogo().getDadocores()==3)    // Se sair preto
                    combatePiratas();
            }
        }
        
        return this; 
    }
    
    @Override
    public IEstados terminaStocks()   // Método que devolve um objecto correspondente ao estado de vendas
    { 
        return new AguardaVendas(getJogo()); 
    }
}