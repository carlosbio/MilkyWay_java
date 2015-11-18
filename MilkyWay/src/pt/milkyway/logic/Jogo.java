// ------------------------------------------------------------------------------------ //
//                                                                                      //
//          Programa: Milky Way - Jogo de tabuleiro de comércio espacial                //
//                                                                                      //
//                          Autor: Carlos da Silva                                      //
//                                                                                      //
//                              Data: 04/05/2015                                        //
//                                                                                      //
// ------------------------------------------------------------------------------------ //

package pt.milkyway.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import pt.milkyway.states.AguardaInicio;
import pt.milkyway.states.IEstados;
import pt.milkyway.states.EstadoListener;

public final class Jogo extends Base implements Serializable  // Classe Jogo
{
    private final List<Carta> baralho=new ArrayList<>();   // Baralho
    private final List<Carta> mapa=new ArrayList<>();      // Mapa
    private IEstados estadoActual;      // Estado actual
    private IEstados estadoAnterior;    // Estado anterior (para os casos de abordagens piratas)
    private boolean vitoria=false;      // Flag de condição de vitória
    private boolean resgate=false;      // Flag de resgate pirata
    private boolean confiscado=false;   // Flag de contrabando confiscado
    private int dadopreto;              // Dado preto
    private int dadocores;              // Dado a cores (Branco(1)(2), Preto(3), Azul(4), Amarelo(5), Vermelho(6))
    private int posicao=24;             // Posição da nave no mapa (0 - 24)
    private String jogador;             // Nome do jogador
    private int dinheiro=10;            // Dinheiro (max 30)
    private final Nave nave=new Nave();       // Nave espacial
    
    // Eventos
    private transient List<EstadoListener> listeners = null;
    
    public Jogo(){this.gerarBaralho();}    // Construtor
    
    public void setEstado(IEstados estado)  // Define o estado
    {
        this.setEstadoAnterior(this.estadoActual);  // Substitui o estado anterior pelo estado actual
        this.setEstadoActual(estado);               // Define o novo estado actual
        
        if(debugMode)   // Mostra info da data e hora do sistema e do estado actual
            System.out.println((new Date())+ " : setEstado - " + estado.getClass().getSimpleName());
    }
    
    public void gerarBaralho()  // Método que gera o baralho que vai corresponder ao mapa de navegação
    {           
        for(int i=0;i<12;i++)
            baralho.add(new CartaVazio());  // Adiciona as 12 cartas de espaço vazio
        
        for(int i=0;i<2;i++)
            baralho.add(new CartaWormhole());  // Adiciona apenas 2 cartas wormhole
        
        baralho.add(new CartaPlaneta("Gethen",1,3,2,3));    // Adiciona as cartas Planetas Normais
        baralho.add(new CartaPlaneta("Kiber",3,1,2,3));
        baralho.add(new CartaPlaneta("Reverie",1,2,3,3));
        baralho.add(new CartaPlaneta("Tiamat",3,2,1,3));
        baralho.add(new CartaPlaneta("Lamarckia",2,3,1,3));
        baralho.add(new CartaPlaneta("Arrakis",2,1,3,3));
        
        baralho.add(new CartaPirata("Whirl","comida"));        // Adiciona as cartas Planetas Piratas
        baralho.add(new CartaPirata("Striterax","medicamentos"));
        baralho.add(new CartaPirata("Asperta","agua"));
        
        Collections.shuffle(baralho);   // Baralha as cartas
        
        baralho.add(0,new CartaWormhole());     // Coloca uma carta wormhole no topo do baralho
        baralho.add(24,new CartaWormhole());    // Coloca uma carta wormhole no fundo do baralho
        
        for(int i=0;i<23;i++)
            mapa.add(new CartaVirada());    // Prepara o mapa com as cartas todas viradas para baixo
        
        mapa.add(0,new CartaWormhole());    // Coloca uma carta wormhole no topo do mapa
        mapa.add(24,new CartaWormhole());   // Coloca uma carta wormhole no fundo do mapa
    }

    public List<Carta> getBaralho(){return baralho;}    // Método que permite obter o baralho

    public List<Carta> getMapa(){return mapa;}  // Método que permite obter o mapa
    
    public void comecaExploracao()  // Método que muda o estado actual para o estado de exploração
    {
        setEstado(estadoActual.comecarJogo());  // Método que permite definir o estado, enviando um objecto que representa esse estado
    }
    
    public void opcoesJogo()    // Primeiro estado da máquina - estado em que é possível carregar um jogo ou começar um novo
    {        
        setEstado(new AguardaInicio(this)); // Método que permite definir o estado, enviando um objecto que representa esse estado
    }
    
    public void terminaJogo()   // Método que termina um jogo
    {
        setEstado(null);    // Ao definir o estado a null, vai forçar a saída do ciclo iterador principal da main
    }

    public Nave getNave(){return nave;} // Obtem a nave    

    public int getDadopreto(){return dadopreto;}    // Obtem o valor do dado preto

    public void setDadopreto(int dadopreto){this.dadopreto = dadopreto;}    // Define o valor do dado preto

    public int getDadocores(){return dadocores;}    // Obtem o valor do dado a cores

    public void setDadocores(int dadocores){this.dadocores = dadocores;}    // Define o valor do dado a cores

    public int getPosicao(){return posicao;}    // Obtem a posição da nave no jogo

    public void setPosicao(int posicao){this.posicao = posicao;}    // Define a posição da nave no jogo

    public int getDinheiro(){return dinheiro;}  // Obtem o valor do dinheiro acumulado

    public void setDinheiro(int dinheiro){this.dinheiro = dinheiro;}    // Define o valor do dinheiro

    public String getJogador(){return jogador;} // Método que permite obter o nome do jogador

    public void setJogador(String jogador){ this.jogador = jogador;}    // Setter jogador

    public boolean isVitoria(){return vitoria;} // Getter vitoria

    public void setVitoria(boolean vitoria){this.vitoria = vitoria;}    // Setter vitoria

    public boolean isResgate(){return resgate;} // Getter resgate

    public void setResgate(boolean resgate){this.resgate = resgate;}    // Setter resgate

    public boolean isConfiscado(){return confiscado;}   // Getter confiscado

    public void setConfiscado(boolean confiscado){this.confiscado = confiscado;}    // Setter confiscado
    
    public void nomeJogador(String j) // Método que permite definir o nome do jogador
    {
        setEstado(estadoActual.nomeJogador(j));
    }
    
    public void virarCarta()    // Método que permite virar as cartas adjacentes à posição actual
    {
        setEstado(estadoActual.virarCarta());
    }
    
    public void reporStocks()   // Método que permite transitar para o estado de reposição de stocks
    {
        setEstado(estadoActual.reporStocks());
    }
    
    public void reporContrabando()  // Método que permite repor o stock de contrabando nos Planetas Piratas conhecidos
    {
        setEstado(estadoActual.reporPirata());
    }
    
    public void removerBrancos()    // Método que permite remover os cubos brancos dos Planetas Normais conhecidos
    {
        setEstado(estadoActual.removerBrancos());
    }
    
    public void reporPlaneta()  // Método que permite repor os stocks dos Planetas Normais conhecidos
    {
        setEstado(estadoActual.reporPlaneta());
    }
    
    public void transporteContrabando() // Método que verifica a existência de contrabando e trata a atracção de piratas e alfândega
    {
        setEstado(estadoActual.transporteContrabando());
    }
    
    public void terminaStocks() // Método que conclui o estado de reposição de stocks
    {
        setEstado(estadoActual.terminaStocks());
    }
    
    public void vendeCargo(Cubo c,int n)    // Método que efectua venda de recurso (envia o recurso e o local)
    {
        setEstado(estadoActual.vendeCargo(c,n));
    }
         
    public void terminaVendas() // Método que conclui o estado de vendas
    {
        setEstado(estadoActual.terminaVendas());
    }
    
    public void compraCargo(Cubo c,int n)   // Método que efectua compra de recurso (envia o recurso e o local)
    {
        setEstado(estadoActual.compraCargo(c,n));
    }
    
    public void efectuaUpgrade(int f)    // Método que efectua upgrade (1-cargo,2-energia,3-energia)
    {
        setEstado(estadoActual.efectuaUpgrade(f));
    }
    
    public void terminaCompras()    // Método que conclui o estado de compras
    {
        setEstado(estadoActual.terminaCompras());
    }
    
    public void moveNave(int p)     // Método que actualiza a posição da nave
    {
        setEstado(estadoActual.moveNave(p));
    }
    
    public void terminaMover()      // Método que conclui o estado de movimento
    {
        setEstado(estadoActual.terminaMover());
    }
    
    public void validaFim()     // Método que valida a condição final de jogo
    {
        setEstado(estadoActual.terminaJogo());
    }
    
    public IEstados getEstadoActual()   // Método que permite obter o estado actual
    {
        return estadoActual;
    }
    
    public void setEstadoActual(IEstados estadoActual)  // Método que permite definir o estado actual
    {
        this.estadoActual = estadoActual;
    }
    
    public IEstados getEstadoAnterior() // Método que permite obter o estado anterior
    {
        return estadoAnterior;
    }
    
    public void setEstadoAnterior(IEstados estadoAnterior)  // Método que permite definir o estado anterior
    {
        this.estadoAnterior = estadoAnterior;
    }
    
    public boolean isEstadoAnterior(Class estado)   // Método que devolve um booleano em verificações do estado anterior
    {
        return estadoAnterior != null && estadoAnterior.getClass() == estado;
    }
    
    public void novoJogo()      // Método que muda o estado actual para o estado de preparação em que pede o nome do jogador
    {
        setEstado(estadoActual.novoJogo()); // Método que permite definir o estado, enviando um objecto que representa esse estado
    }
    
    public Jogo loadJogo() throws FileNotFoundException     // Método que carrega um jogo gravado em ficheiro
    {
        try 
        {
            IEstados proximoEstado = estadoActual.loadJogo();
            setEstado(proximoEstado);

            return proximoEstado.getJogo(); // Nova instancia do jogo
        }
        catch (FileNotFoundException e) 
        {
            throw e;
        }
        catch (IOException e) 
        {
            // Ignora
        }
        return null;
    }
    
    public void gravaJogo() // Método que permite a gravação de um jogo para continuar mais tarde
    {
        setEstado(estadoActual.gravaJogo());    // Método que permite definir o estado, enviando um objecto que representa esse estado
    }
    
    public void gravaInstanciaJogo(String fileName) throws IOException  // Método que grava a instância actual do jogo
    {
        FileOutputStream fo = new FileOutputStream(fileName);
        
        try (ObjectOutputStream oo = new ObjectOutputStream(fo)) 
        {
            oo.writeObject(this);
        }
    }
    
    public Jogo carregaInstanciaJogo(String fileName) throws FileNotFoundException  // Método que carrega a instância actual do jogo
    {
        Jogo jogoAux= this;
        
        try 
        {         
            FileInputStream fi = new FileInputStream(fileName);
            
            try (ObjectInputStream oi = new ObjectInputStream(fi)) 
            {
                jogoAux = ((Jogo) oi.readObject());
            }
        }
        catch (FileNotFoundException e) 
        {
            throw e;
        }
        catch (IOException | ClassNotFoundException e)
        {
            // Ignora
        }
        
        return jogoAux;
    }
    
    public void addListener(EstadoListener toAdd)
    {
        getListeners().add(toAdd);
    }
    
    public List<EstadoListener> getListeners()
    {
        if (listeners == null)
            listeners = new ArrayList<>();
        
        return listeners;
    }
}