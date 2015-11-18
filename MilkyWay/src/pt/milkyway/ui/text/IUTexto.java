// ------------------------------------------------------------------------------------ //
//                                                                                      //
//          Programa: Milky Way - Jogo de tabuleiro de comércio espacial                //
//                                                                                      //
//                          Autor: Carlos da Silva                                      //
//                                                                                      //
//                              Data: 04/05/2015                                        //
//                                                                                      //
// ------------------------------------------------------------------------------------ //

package pt.milkyway.ui.text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import pt.milkyway.logic.*;
import pt.milkyway.states.*;

public class IUTexto    // Classe responsável pela interface de comunicação por texto com o utilizador
{
    private final ArrayList<Integer> parametros=new ArrayList<>(); // Para validação de input de utilizador
    
    Jogo jogo;  // Inclusão de uma referência para o objecto jogo
    
    public IUTexto(Jogo jogo)   // Construtor
    {
        this.jogo = jogo;
    }
    
    public Jogo getJogo()   // Getter do jogo
    {
        return jogo;
    }
    
    public void iniciaInterface()   // Método que inicia a interface de texto
    {        
        if (Jogo.debugMode) // Avisa se o jogo estiver em modo debug
            System.out.println("DEBUG MODE");
        
        jogo.opcoesJogo();  // Chama o método do jogo que permite a transição para o estado de preparação
    }
    
    public void executaInterface() throws IOException, InterruptedException // Método principal de execução da interface de texto
    {
        if(jogo.getEstadoActual().getClass() == AguardaInicio.class)
            menuInicio();
        else if(jogo.getEstadoActual().getClass() == AguardaPreparacao.class)
            menuPreparacao();
        else if(jogo.getEstadoActual().getClass() == AguardaCompras.class)
            menuCompras();        
        else if(jogo.getEstadoActual().getClass() == AguardaExploracao.class)
            menuExploracao();
        else if(jogo.getEstadoActual().getClass() == AguardaMovimento.class)
            menuMovimento();        
        else if(jogo.getEstadoActual().getClass() == AguardaStocks.class)
            menuStocks();        
        else if(jogo.getEstadoActual().getClass() == AguardaVendas.class)
            menuVendas();        
    }

    public void menuInicio()    // Método que efectua a comunicação do menu inicial ao utilizador
    {
        int opInt;
   
        System.out.println("\n####################################");
        System.out.println("## Bem vindo ao Milky Way Express ##");
        System.out.println("####################################");
        System.out.println("\n1. Novo jogo");
        System.out.println("2. Retomar jogo");
        
        if (jogo.getEstadoAnterior()!=null)
            System.out.println("3. Gravar jogo");   // Permite a gravação apenas se estado anterior não for null
        
        System.out.println("0. Terminar");
        System.out.print("\nOpção:\n[> ");
        
        if (jogo.getEstadoAnterior()!=null) // Validação de parâmetros
            opInt = obterNumero(0,3);
        else
            opInt = obterNumero(0,2);
        
        switch (opInt) 
        {
            case 0:
                jogo.terminaJogo(); // Termina jogo
                break;
                
            case 1:
                jogo.novoJogo();    // Cria novo jogo
                break;
                
            case 2:
                try
                {
                    jogo = jogo.loadJogo(); // Retoma o jogo (carrega do ficheiro)
                }
                catch (FileNotFoundException e) 
                {
                    System.out.println("Não existe ficheiro gravado\n");
                }
                
                if (Jogo.debugMode)
                    System.out.println("DEBUG MODE: Jogo carregado");   // Debug mode                
                
                break;
                
            case 3:                
                if(jogo.getEstadoAnterior()!=null)
                {
                    jogo.gravaJogo();   // Grava jogo
                    System.out.println("\nJogo gravado");
                }
                break;
                
            default:
                break;
        }
    }
    
    public void menuPreparacao()    // Menu de preparação de um novo jogo
    {
        String opStr;  
        
        do
        {        
            System.out.print("\nNome do piloto:\n[> ");                      
            opStr = obterString();

            if(!opStr.isEmpty())
                jogo.nomeJogador(opStr); // Define o nome do jogador        
        }
        while(opStr.isEmpty());
        
        jogo.comecaExploracao();    // Chama o método que efectua a transição para o estado de exploração
    }
    
    public void mostrarInfo()
    {
        System.out.println("\n------");
        System.out.println(" INFO");
        System.out.println("------");
        System.out.print("\nNAVE");
        System.out.println("\t\t\tPILOTO");
        System.out.print("|- Energia: " + jogo.getNave().getEnergia());
        System.out.println("\t\t|- Nome: " + jogo.getJogador());
        System.out.print("|- Capacidade: " + jogo.getNave().getCapacidade());
        System.out.println("\t|- Dinheiro: " + jogo.getDinheiro());
        System.out.println("|- Porão 1: " + jogo.getNave().checkCargo1());
        System.out.println("|- Porão 2: " + jogo.getNave().checkCargo2());
        
        if(jogo.getNave().getCapacidade()==3)
            System.out.println("|- Porão 3: " + jogo.getNave().checkCargo3());
    }
    
    public void mostrarBaralho()    // Método que mostra as cartas do baralho (debugging only)
    {
        for(Carta c : jogo.getBaralho())
        {
            if(c instanceof CartaVazio)
                System.out.println("[Vazio]");
            else if(c instanceof CartaWormhole)
                System.out.println("[Wormhole]");
            else if(c instanceof CartaPlaneta)
            {
                CartaPlaneta cp = (CartaPlaneta)c;
                System.out.print("[Planeta Normal \"" + cp.getNome() + "\"");
                System.out.print(", VENDE: " + cp.checkStock1() + " / " + cp.checkStock2());
                System.out.println(", PREÇOS: agua(" + cp.getAgua() + ") comida(" + cp.getComida() + 
                        ") medicamentos(" + cp.getMedicamentos() + ") contrabando(" + cp.getContrabando() + ")" + "]");
            }
            else if(c instanceof CartaPirata)
            {
                CartaPirata cp = (CartaPirata)c;
                System.out.print("[Planeta Pirata \"" + cp.getNome() + "\"");
                System.out.print(", VENDE: " + cp.checkStock());
                System.out.println(", PREÇOS: " + cp.getRecurso() + "(3)" + "]");
            }
        } 
    }
    
    public void mostrarPosicao(int pos) // Método que mostra a posição actual
    {
        parametros.add(pos);    // Adiciona a posição ao array de validação de parâmetros
        
        Carta c=jogo.getMapa().get(pos);
        
        if(c instanceof CartaVazio)
        {
            if(pos<10)
                System.out.print(" " + pos + ". ");   // Mostra a posição (valor < 10)
            else
                System.out.print(pos + ". ");   // Mostra a posição (valor > 10)
            
            System.out.println("[Vazio]");  // Mostra o tipo
        }
        else if(c instanceof CartaWormhole)
        {
            if(pos<10)
                System.out.print(" " + pos + ". ");   // Mostra a posição (valor < 10)
            else
                System.out.print(pos + ". ");   // Mostra a posição (valor > 10)
            
            System.out.println("[Wormhole]");   // Mostra o tipo
        }
        else if(c instanceof CartaPlaneta)
        {
            if(pos<10)
                System.out.print(" " + pos + ". ");   // Mostra a posição (valor < 10)
            else
                System.out.print(pos + ". ");   // Mostra a posição (valor > 10)
            
            CartaPlaneta cp = (CartaPlaneta)c;
            
            System.out.print("[Planeta Normal \"" + cp.getNome() + "\"");   // Mostra o tipo
            System.out.print(", VENDE: " + cp.checkStock1() + " / " + cp.checkStock2());
            System.out.println(", PREÇOS: agua(" + cp.getAgua() + ") comida(" + cp.getComida() + 
                    ") medicamentos(" + cp.getMedicamentos() + ") contrabando(" + cp.getContrabando() + ")" + "]");
        }
        else if(c instanceof CartaPirata)
        {
            if(pos<10)
                System.out.print(" " + pos + ". ");   // Mostra a posição (valor < 10)
            else
                System.out.print(pos + ". ");   // Mostra a posição (valor > 10)
            
            CartaPirata cp = (CartaPirata)c;
            
            System.out.print("[Planeta Pirata \"" + cp.getNome() + "\"");   // Mostra o tipo
            System.out.print(", VENDE: " + cp.checkStock());
            System.out.println(", PREÇOS: " + cp.getRecurso() + "(3)" + "]");
        }
    }
    
    public void mostrarMapa() // Método que mostra o mapa e a posição da nave
    {
        System.out.println("---------------------");
        System.out.println(" SECTORES CONHECIDOS");
        System.out.println("---------------------\n");
        
        int pos=0;  // Contador de iterações
        
        for(Carta c : jogo.getMapa())
        {
            if(pos<10)
            {
                if(pos==jogo.getPosicao())
                    System.out.print("-->  " + pos + ". ");   // Posição da nave no mapa
                else
                    System.out.print("     " + pos + ". ");   // Espaços de formatação 
            }
            else
            {
                if(pos==jogo.getPosicao())
                    System.out.print("--> " + pos + ". ");   // Posição da nave no mapa
                else
                    System.out.print("    " + pos + ". ");   // Espaços de formatação 
            } 
            
            if(c instanceof CartaVazio)
                System.out.println("[Vazio] ");
            else if(c instanceof CartaWormhole)
                System.out.println("[Wormhole] ");
            else if(c instanceof CartaPlaneta)
            {
                CartaPlaneta cp = (CartaPlaneta)c;
                System.out.print("[Planeta Normal \"" + cp.getNome() + "\"");
                System.out.print(", VENDE: " + cp.checkStock1() + " / " + cp.checkStock2());
                System.out.println(", PREÇOS: agua(" + cp.getAgua() + ") comida(" + cp.getComida() + 
                        ") medicamentos(" + cp.getMedicamentos() + ") contrabando(" + cp.getContrabando() + ")" + "]");
            }
            else if(c instanceof CartaPirata)
            {
                CartaPirata cp = (CartaPirata)c;
                System.out.print("[Planeta Pirata \"" + cp.getNome() + "\"");
                System.out.print(", VENDE: " + cp.checkStock());
                System.out.println(", PREÇOS: " + cp.getRecurso() + "(3)" + "]");
            }
            else
                System.out.println("[Desconhecido] ");
            
            pos++;  // Incrementa o iterador
        } 
    }
    
    public void mostrarEspaco() // Método que mostra a localização da nave na área de exploração
    {
        System.out.println("-------------------------------------------");
        System.out.println(" LOCALIZAÇÃO DA NAVE NA ÁREA DE EXPLORAÇÃO");
        System.out.println("-------------------------------------------");
        System.out.println("                                                         _____");
        System.out.println("                                                        |0    |");
        
        if(jogo.getPosicao()==0)
            System.out.println("                                                        |  X  |");
        else
            System.out.println("                                                        |     |");
        
        System.out.println("                                                        |_____|");
        System.out.println("                      _____                              _____");
        System.out.println("                     |14   |                            |1    |");
        
        if(jogo.getPosicao()==14)
            System.out.println("                     |  X  |                            |     |");
        else if(jogo.getPosicao()==1)
            System.out.println("                     |     |                            |  X  |");
        else
            System.out.println("                     |     |                            |     |");
        
        System.out.println("                     |_____|                            |_____|");
        System.out.println("               _____  _____  _____         _____  _____  _____");
        System.out.println("              |17   ||15   ||11   |       |5    ||3    ||2    |");
        
        if(jogo.getPosicao()==17)
            System.out.println("              |  X  ||     ||     |       |     ||     ||     |");
        else if(jogo.getPosicao()==15)
            System.out.println("              |     ||  X  ||     |       |     ||     ||     |");
        else if(jogo.getPosicao()==11)
            System.out.println("              |     ||     ||  X  |       |     ||     ||     |");   
        else if(jogo.getPosicao()==5)
            System.out.println("              |     ||     ||     |       |  X  ||     ||     |");    
        else if(jogo.getPosicao()==3)
            System.out.println("              |     ||     ||     |       |     ||  X  ||     |");    
        else if(jogo.getPosicao()==2)
            System.out.println("              |     ||     ||     |       |     ||     ||  X  |");    
        else
            System.out.println("              |     ||     ||     |       |     ||     ||     |");    
                
        System.out.println("              |_____||_____||_____|       |_____||_____||_____|");
        System.out.println("        _____  _____  _____  _____  _____  _____  _____");
        System.out.println("       |20   ||18   ||16   ||12   ||8    ||6    ||4    |");
        
        if(jogo.getPosicao()==20)
            System.out.println("       |  X  ||     ||     ||     ||     ||     ||     |");
        else if(jogo.getPosicao()==18)
            System.out.println("       |     ||  X  ||     ||     ||     ||     ||     |");
        else if(jogo.getPosicao()==16)
            System.out.println("       |     ||     ||  X  ||     ||     ||     ||     |");   
        else if(jogo.getPosicao()==12)
            System.out.println("       |     ||     ||     ||  X  ||     ||     ||     |");    
        else if(jogo.getPosicao()==8)
            System.out.println("       |     ||     ||     ||     ||  X  ||     ||     |");    
        else if(jogo.getPosicao()==6)
            System.out.println("       |     ||     ||     ||     ||     ||  X  ||     |");    
        else if(jogo.getPosicao()==4)
            System.out.println("       |     ||     ||     ||     ||     ||     ||  X  |");    
        else
            System.out.println("       |     ||     ||     ||     ||     ||     ||     |");
        
        System.out.println("       |_____||_____||_____||_____||_____||_____||_____|");
        System.out.println(" _____  _____  _____         _____  _____  _____");
        System.out.println("|22   ||21   ||19   |       |13   ||9    ||7    |");
        
        if(jogo.getPosicao()==22)
            System.out.println("|  X  ||     ||     |       |     ||     ||     |");
        else if(jogo.getPosicao()==21)
            System.out.println("|     ||  X  ||     |       |     ||     ||     |");
        else if(jogo.getPosicao()==19)
            System.out.println("|     ||     ||  X  |       |     ||     ||     |");    
        else if(jogo.getPosicao()==13)
            System.out.println("|     ||     ||     |       |  X  ||     ||     |");    
        else if(jogo.getPosicao()==9)
            System.out.println("|     ||     ||     |       |     ||  X  ||     |");    
        else if(jogo.getPosicao()==7)
            System.out.println("|     ||     ||     |       |     ||     ||  X  |");    
        else
            System.out.println("|     ||     ||     |       |     ||     ||     |");        
        
        System.out.println("|_____||_____||_____|       |_____||_____||_____|");
        System.out.println(" _____                              _____");
        System.out.println("|23   |                            |10   |");
        
        if(jogo.getPosicao()==23)
            System.out.println("|  X  |                            |     |");
        else if(jogo.getPosicao()==10)
            System.out.println("|     |                            |  X  |");
        else
            System.out.println("|     |                            |     |");
        
        System.out.println("|_____|                            |_____|");
        System.out.println(" _____");
        System.out.println("|24   |");
        
        if(jogo.getPosicao()==24)
            System.out.println("|  X  |");
        else
            System.out.println("|     |");
        
        System.out.println("|_____|\n\n");
    }
    
    public void menuExploracao()    // Método exploração
    {
        String opStr;
        
        System.out.println("\n - INICIO DO TURNO DE EXPLORAÇÃO - \n(Premir qualquer tecla para continuar)\n");                      
        opStr = obterString();
        
        jogo.virarCarta();  // Chama o método que vira as cartas adjacentes à posição actual

        mostrarEspaco();        
        mostrarMapa();      // Mostra o mapa        
        mostrarInfo();
        
        System.out.print("\n--> A nave encontra-se neste momento em: ");
        mostrarPosicao(jogo.getPosicao());
        
        System.out.println("\n\n - FIM DO TURNO DE EXPLORAÇÃO - \n(Premir \"M\" para Menu de Opções ou outra tecla para continuar)\n");                      
        opStr = obterString();  // Força uma pausa
        
        if("m".equals(opStr)||"M".equals(opStr))    // Permite o escape para o menu principal
            jogo.gravaJogo();
        else        
            jogo.reporStocks(); // Chama o método que efectua a transição para o estado de reposição de stocks
    }
    
    public void menuStocks()    // Método stocks (a implementar)
    {
        String opStr;  
        
        System.out.println("\n - INICIO DO TURNO DE REPOSIÇÃO DE STOCKS - \n(Premir qualquer tecla para continuar)\n");                      
        opStr = obterString();
        
        mostrarMapa();
        mostrarInfo();
        
        System.out.print("\n--> A nave encontra-se neste momento em: ");
        mostrarPosicao(jogo.getPosicao());
        
        jogo.reporContrabando();      

        jogo.removerBrancos();

        jogo.reporPlaneta();
 
        jogo.transporteContrabando();

        if(jogo.isConfiscado())
        {
            System.out.println("\n--> A alfândega cobrou multa e confiscou o contrabando encontrado no porão de carga!\n");
            jogo.setConfiscado(false);
        }
        
        if(jogo.isResgate())
        {
            System.out.println("\n--> Ocorreu uma abordagem de piratas e foi necessário efectuar o pagamento de resgate!\n");
            jogo.setResgate(false);
        }
        
        System.out.println("\n\n - FIM DO TURNO DE REPOSIÇÃO DE STOCKS - \n(Premir qualquer tecla para continuar)\n");
        opStr = obterString();

        jogo.terminaStocks();
    }
    
    public void menuVendas()    // Método vendas
    {
        String opStr;  
        
        System.out.println("\n - INICIO DO TURNO DE VENDAS - \n(Premir qualquer tecla para continuar)");                      
        opStr = obterString();
        
        mostrarInfo();
        
        System.out.print("\n--> A nave encontra-se neste momento em: "); 
        mostrarPosicao(jogo.getPosicao());
        
        if(jogo.getMapa().get(jogo.getPosicao()) instanceof CartaPlaneta
                ||jogo.getMapa().get(jogo.getPosicao()) instanceof CartaPirata) // Verifica se a nave está num planeta
        {        
            if(jogo.getNave().getCargo1()!=null
                    ||jogo.getNave().getCargo2()!=null
                    ||jogo.getNave().getCargo3()!=null) // Verifica se a nave possui material possível de ser vendido
            {
                System.out.print("\nVender stock? (S/N)\n[> ");
                opStr = obterString();

                if("s".equals(opStr)||"S".equals(opStr))
                {
                    if(jogo.getNave().getCargo1()!=null)    // Verifica o material no porão 1
                    {
                        System.out.print("\nVender " + jogo.getNave().checkCargo1() + "? (S/N)\n[> ");
                        opStr = obterString();

                        if("s".equals(opStr)||"S".equals(opStr))
                            jogo.vendeCargo(jogo.getNave().getCargo1(),1);   
                    }

                    if(jogo.getNave().getCargo2()!=null)    // Verifica o material no porão 2
                    {
                        System.out.print("\nVender " + jogo.getNave().checkCargo2() + "? (S/N)\n[> ");
                        opStr = obterString();

                        if("s".equals(opStr)||"S".equals(opStr))
                            jogo.vendeCargo(jogo.getNave().getCargo2(),2);   
                    }

                    if(jogo.getNave().getCargo3()!=null)    // Verifica o material no porão 3
                    {
                        System.out.print("\nVender " + jogo.getNave().checkCargo3() + "? (S/N)\n[> ");
                        opStr = obterString();

                        if("s".equals(opStr)||"S".equals(opStr))
                            jogo.vendeCargo(jogo.getNave().getCargo3(),3);   
                    }
                }
            }
            else
                System.out.println("\nNão existe stock para vender!\n");
        }
        
        System.out.println("\n\n - FIM DO TURNO DE VENDAS - \n(Premir qualquer tecla para continuar)\n");                      
        opStr = obterString();
        
        jogo.terminaVendas();
    }
    
    public void menuCompras()   // Método compras
    {
        String opStr;  
        
        System.out.println("\n - INICIO DO TURNO DE COMPRAS - \n(Premir qualquer tecla para continuar)");                      
        opStr = obterString(); 
        
        mostrarInfo();
        
        System.out.print("\n--> A nave encontra-se neste momento em: "); 
        mostrarPosicao(jogo.getPosicao());
        
        if(jogo.getMapa().get(jogo.getPosicao()) instanceof CartaPlaneta
                ||jogo.getMapa().get(jogo.getPosicao()) instanceof CartaPirata) // Verifica se a nave está num planeta
        {
            if(jogo.getNave().getCargo1()==null // Verifica se a nave possui espaço no porão
                    ||jogo.getNave().getCargo2()==null
                    ||(jogo.getNave().getCargo3()==null&&jogo.getNave().getCapacidade()==3))
            {
                if(jogo.getMapa().get(jogo.getPosicao()) instanceof CartaPlaneta)   // Se fôr Planeta Normal
                {
                    CartaPlaneta cp=(CartaPlaneta)jogo.getMapa().get(jogo.getPosicao());    // Downcast
                    
                    if((cp.getStock1() instanceof CuboBranco)&&(cp.getStock2() instanceof CuboBranco))  // Recursos confiscados
                        System.out.println("\nOs recursos estão confiscados, pelo que não é possível adquirí-los!\n");
                    else
                    {
                        System.out.print("\nComprar recursos? (S/N)\n[> ");
                        opStr = obterString();
                        
                        if("s".equals(opStr)||"S".equals(opStr))    // Se for para comprar recursos
                        {
                            if((cp.getStock1() instanceof CuboAzul))  // Caso exista água a ser comercializada no stock1
                            {
                                System.out.print("\nComprar " + cp.checkStock1() + "? (S/N)\n[> ");  // Comprar actual recurso?
                                opStr = obterString();
                                
                                if("s".equals(opStr)||"S".equals(opStr))    // Se for para comprar este recurso
                                {
                                    if(jogo.getDinheiro()>=cp.getAgua())    // Caso tenha dinheiro para comprar água neste planeta
                                        jogo.compraCargo(cp.getStock1(),1);
                                    else
                                        System.out.println("\nNão possui dinheiro suficiente para comprar este recurso!\n");                                  
                                }
                            }
                            else if((cp.getStock1() instanceof CuboAmarelo))  // Caso exista comida a ser comercializada no stock1
                            {
                                System.out.print("\nComprar " + cp.checkStock1() + "? (S/N)\n[> ");  // Comprar actual recurso?
                                opStr = obterString();
                                
                                if("s".equals(opStr)||"S".equals(opStr))    // Se for para comprar este recurso
                                {
                                    if(jogo.getDinheiro()>=cp.getComida())    // Caso tenha dinheiro para comprar comida neste planeta
                                        jogo.compraCargo(cp.getStock1(),1);
                                    else
                                        System.out.println("\nNão possui dinheiro suficiente para comprar este recurso!\n");                                  
                                }
                            }
                            else if((cp.getStock1() instanceof CuboVermelho))  // Caso existam medicamentos a ser comercializados no stock1
                            {
                                System.out.print("\nComprar " + cp.checkStock1() + "? (S/N)\n[> ");  // Comprar actual recurso?
                                opStr = obterString();
                                
                                if("s".equals(opStr)||"S".equals(opStr))    // Se for para comprar este recurso
                                {
                                    if(jogo.getDinheiro()>=cp.getMedicamentos())    // Caso tenha dinheiro para comprar água neste planeta
                                        jogo.compraCargo(cp.getStock1(),1);
                                    else
                                        System.out.println("\nNão possui dinheiro suficiente para comprar este recurso!\n");                                  
                                }
                            }
                            
                            if((cp.getStock2() instanceof CuboAzul))  // Caso exista água a ser comercializada no stock2
                            {
                                System.out.print("\nComprar " + cp.checkStock2() + "? (S/N)\n[> ");  // Comprar actual recurso?
                                opStr = obterString();
                                
                                if("s".equals(opStr)||"S".equals(opStr))    // Se for para comprar este recurso
                                {
                                    if(jogo.getDinheiro()>=cp.getAgua())    // Caso tenha dinheiro para comprar água neste planeta
                                        jogo.compraCargo(cp.getStock2(),2);
                                    else
                                        System.out.println("\nNão possui dinheiro suficiente para comprar este recurso!\n");                                  
                                }
                            }
                            else if((cp.getStock2() instanceof CuboAmarelo))  // Caso exista comida a ser comercializada no stock2
                            {
                                System.out.print("\nComprar " + cp.checkStock2() + "? (S/N)\n[> ");  // Comprar actual recurso?
                                opStr = obterString();
                                
                                if("s".equals(opStr)||"S".equals(opStr))    // Se for para comprar este recurso
                                {
                                    if(jogo.getDinheiro()>=cp.getComida())    // Caso tenha dinheiro para comprar comida neste planeta
                                        jogo.compraCargo(cp.getStock2(),2);
                                    else
                                        System.out.println("\nNão possui dinheiro suficiente para comprar este recurso!\n");                                  
                                }
                            }
                            else if((cp.getStock2() instanceof CuboVermelho))  // Caso existam medicamentos a ser comercializados no stock2
                            {
                                System.out.print("\nComprar " + cp.checkStock2() + "? (S/N)\n[> ");  // Comprar actual recurso?
                                opStr = obterString();
                                
                                if("s".equals(opStr)||"S".equals(opStr))    // Se for para comprar este recurso
                                {
                                    if(jogo.getDinheiro()>=cp.getMedicamentos())    // Caso tenha dinheiro para comprar água neste planeta
                                        jogo.compraCargo(cp.getStock2(),2);
                                    else
                                        System.out.println("\nNão possui dinheiro suficiente para comprar este recurso!\n");                                  
                                }
                            }
                        }                          
                    }
                }
                else    // Se fôr um Planeta Pirata
                {
                    CartaPirata cp=(CartaPirata)jogo.getMapa().get(jogo.getPosicao());    // Downcast
                    
                    System.out.print("\nComprar contrabando? (S/N)\n[> ");
                    opStr = obterString();

                    if("s".equals(opStr)||"S".equals(opStr))    // Se for para comprar contrabando
                    {
                        if(jogo.getDinheiro()>=1)    // Caso tenha dinheiro para comprar contrabando
                            jogo.compraCargo(cp.getStock(),1);
                        else
                            System.out.println("\nNão possui dinheiro suficiente para comprar contrabando!\n");                                  
                    }
                }
            }
            
            System.out.print("\nEfectuar upgrades? (S/N)\n[> ");
            opStr = obterString();

            if("s".equals(opStr)||"S".equals(opStr))    // Se for para efectuar upgrades
            {
                if(jogo.getNave().getCapacidade()!=3)   // Se o porão de carga não tiver sido aumentado
                {
                    System.out.print("\nAumentar capacidade do porão de carga? (S/N)\n[> ");
                    opStr = obterString();

                    if("s".equals(opStr)||"S".equals(opStr))    // Se for para efectuar upgrade da capacidade do porão
                        if(jogo.getDinheiro()>=3)    // Caso tenha dinheiro para efectuar o upgrade
                            jogo.efectuaUpgrade(1); // Método que efectua o upgrade (flag 1 - porão);
                        else
                            System.out.println("\nNão possui dinheiro suficiente para efectuar o upgrade!\n"); 
                    
                }
                
                if(jogo.getNave().getEnergia()!=5)  // Se a energia de combate não tiver sido aumentada
                {
                    System.out.print("\nAumentar energia de combate da nave? (S/N)\n[> ");
                    opStr = obterString();

                    if("s".equals(opStr)||"S".equals(opStr))    // Se for para efectuar upgrade da energia de combate
                    {
                        if(jogo.getNave().getEnergia()==3)  // Caso seja para fazer o primeiro upgrade de energia de combate
                        {
                            if(jogo.getDinheiro()>=4)    // Caso tenha dinheiro para efectuar o upgrade
                                jogo.efectuaUpgrade(2); // Método que efectua o upgrade (flag 2 - 1º upgrade);
                            else
                                System.out.println("\nNão possui dinheiro suficiente para efectuar o upgrade!\n");
                            
                            if(jogo.getNave().getEnergia()==4&&jogo.getDinheiro()>=5)   // Permite realizar o upgrade seguinte
                            {
                                System.out.print("\nEfectuar outro upgrade de  energia de combate da nave? (S/N)\n[> ");
                                opStr = obterString();

                                if("s".equals(opStr)||"S".equals(opStr))    // Se for para efectuar upgrade da energia de combate
                                    if(jogo.getDinheiro()>=5)               // Caso tenha dinheiro para efectuar o upgrade
                                        jogo.efectuaUpgrade(3); // Método que efectua o upgrade (flag 3 - 2º upgrade);
                            }                            
                        }
                        else
                        {
                            if(jogo.getDinheiro()>=5)    // Caso tenha dinheiro para efectuar o upgrade
                                jogo.efectuaUpgrade(3); // Método que efectua o upgrade (flag 3 - 2º upgrade);
                            else
                                System.out.println("\nNão possui dinheiro suficiente para efectuar o upgrade!\n");
                        }
                    }                      
                }                
            }
        }
        
        System.out.println("\n\n - FIM DO TURNO DE COMPRAS - \n(Premir qualquer tecla para continuar)\n");                      
        opStr = obterString();
        
        jogo.terminaCompras();
    }
    
    public void menuMovimento() // Método movimento
    {
        int pos=jogo.getPosicao();
        int optInt;
        String opStr;
        
        System.out.println("\n - INICIO DO TURNO DE MOVIMENTO - \n(Premir qualquer tecla para continuar)\n");                      
        opStr = obterString();
        
        do
        {
            boolean valid=false;    // Flag de validação
            
            jogo.validaFim();   // Valida condição de fim de jogo
        
            if(jogo.getEstadoActual()==null)    // Caso não esteja em condição de fim de jogo
                break;
            
            while(valid==false)
            {
                mostrarEspaco();
                mostrarMapa();
                mostrarInfo();
                
                parametros.clear();     // Limpa o array de validação de parâmetros
                valid=false;            // Força o false
                
                System.out.print("\n--> A nave encontra-se neste momento em: ");
                
                mostrarPosicao(pos);

                System.out.println("\nMovimentos possíveis:\n");

                movimentoNave(pos); // Método que mostra os movimentos possíveis a partir da posição actual

                System.out.print("\nDeslocar a nave para que posição?\n[> ");

                optInt = obterNumero(0,24); // Pede posição de movimento

                for(Integer i : parametros) // Analisa o array de validação
                {
                    if(optInt==i&&optInt!=pos)
                        valid=true; // Devolve verdadeiro se posição introduzida é válida
                }

                if(jogo.getMapa().get(optInt) instanceof CartaVirada||valid==false) // Verifica validade 
                {
                    System.out.println("\nNão é possível movimentar a nave para essa posição!\n(Premir qualquer tecla para continuar)\n");                   
                    opStr = obterString();
                }
                else
                    jogo.moveNave(optInt);  // Chama o método de movimento
            }
            
            if(jogo.isResgate())
            {
                System.out.println("\n--> Ocorreu uma abordagem de piratas e foi necessário efectuar o pagamento de resgate!\n");
                jogo.setResgate(false);
            }

            System.out.print("\nEfectuar novo movimento? (S/N)\n[> ");
            opStr = obterString();

            pos=jogo.getPosicao();  // Actualiza a posição
        }
        while("s".equals(opStr)||"S".equals(opStr));
        
        System.out.println("\n\n - FIM DO TURNO DE MOVIMENTO - \n(Premir qualquer tecla para continuar)\n");                      
        opStr = obterString();
        
        jogo.terminaMover();
    }
    
    public void movimentoNave(int pos)  // Método que mostra os movimentos possíveis a partir da posição actual
    {        
        if(jogo.getPosicao()==0)
        {
            mostrarPosicao(1);
            mostrarPosicao(24);            
        }
        else if(jogo.getPosicao()==1)
        {
            mostrarPosicao(0);
            mostrarPosicao(2);
            mostrarPosicao(3);
        }
        else if(jogo.getPosicao()==2)
        {
            mostrarPosicao(1);
            mostrarPosicao(3);
            mostrarPosicao(4);
        }
        else if(jogo.getPosicao()==3)
        {
            mostrarPosicao(1);
            mostrarPosicao(2);
            mostrarPosicao(4);
            mostrarPosicao(5);
            mostrarPosicao(6);
        }
        else if(jogo.getPosicao()==4)
        {
            mostrarPosicao(2);
            mostrarPosicao(3);
            mostrarPosicao(5);
            mostrarPosicao(6);
            mostrarPosicao(7);
        }
        else if(jogo.getPosicao()==5)
        {
            mostrarPosicao(3);
            mostrarPosicao(4);
            mostrarPosicao(6);
            mostrarPosicao(8);
        }
        else if(jogo.getPosicao()==6)
        {
            mostrarPosicao(3);
            mostrarPosicao(4);
            mostrarPosicao(5);
            mostrarPosicao(7);
            mostrarPosicao(8);
            mostrarPosicao(9);
        }
        else if(jogo.getPosicao()==7)
        {
            mostrarPosicao(4);
            mostrarPosicao(6);
            mostrarPosicao(8);
            mostrarPosicao(9);
            mostrarPosicao(10);
        }
        else if(jogo.getPosicao()==8)
        {
            mostrarPosicao(5);
            mostrarPosicao(6);
            mostrarPosicao(7);
            mostrarPosicao(9);
            mostrarPosicao(11);
            mostrarPosicao(12);
            mostrarPosicao(13);
        }
        else if(jogo.getPosicao()==9)
        {
            mostrarPosicao(6);
            mostrarPosicao(7);
            mostrarPosicao(8);
            mostrarPosicao(10);
            mostrarPosicao(12);
            mostrarPosicao(13);
        }
        else if(jogo.getPosicao()==10)
        {
            mostrarPosicao(7);
            mostrarPosicao(9);
            mostrarPosicao(13);
        }
        else if(jogo.getPosicao()==11)
        {
            mostrarPosicao(8);
            mostrarPosicao(12);
            mostrarPosicao(14);
            mostrarPosicao(15);
            mostrarPosicao(16);
        }
        else if(jogo.getPosicao()==12)
        {
            mostrarPosicao(8);
            mostrarPosicao(9);
            mostrarPosicao(11);
            mostrarPosicao(13);
            mostrarPosicao(15);
            mostrarPosicao(16);
        }
        else if(jogo.getPosicao()==13)
        {
            mostrarPosicao(8);
            mostrarPosicao(9);
            mostrarPosicao(10);
            mostrarPosicao(12);
            mostrarPosicao(16);
        }
        else if(jogo.getPosicao()==14)
        {
            mostrarPosicao(11);
            mostrarPosicao(15);
            mostrarPosicao(17);
        }
        else if(jogo.getPosicao()==15)
        {
            mostrarPosicao(11);
            mostrarPosicao(12);
            mostrarPosicao(14);
            mostrarPosicao(16);
            mostrarPosicao(17);
            mostrarPosicao(18);
        }
        else if(jogo.getPosicao()==16)
        {
            mostrarPosicao(11);
            mostrarPosicao(12);
            mostrarPosicao(13);
            mostrarPosicao(15);
            mostrarPosicao(17);
            mostrarPosicao(18);
            mostrarPosicao(19);
        }
        else if(jogo.getPosicao()==17)
        {
            mostrarPosicao(14);
            mostrarPosicao(15);
            mostrarPosicao(16);
            mostrarPosicao(18);
            mostrarPosicao(20);
        }
        else if(jogo.getPosicao()==18)
        {
            mostrarPosicao(15);
            mostrarPosicao(16);
            mostrarPosicao(17);
            mostrarPosicao(19);
            mostrarPosicao(20);
            mostrarPosicao(21);
        }
        else if(jogo.getPosicao()==19)
        {
            mostrarPosicao(16);
            mostrarPosicao(18);
            mostrarPosicao(20);
            mostrarPosicao(21);
        }
        else if(jogo.getPosicao()==20)
        {
            mostrarPosicao(17);
            mostrarPosicao(18);
            mostrarPosicao(19);
            mostrarPosicao(21);
            mostrarPosicao(22);
        }
        else if(jogo.getPosicao()==21)
        {
            mostrarPosicao(18);
            mostrarPosicao(19);
            mostrarPosicao(20);
            mostrarPosicao(22);
            mostrarPosicao(23);
        }
        else if(jogo.getPosicao()==22)
        {
            mostrarPosicao(20);
            mostrarPosicao(21);
            mostrarPosicao(23);
        }
        else if(jogo.getPosicao()==23)
        {

            mostrarPosicao(21);
            mostrarPosicao(22);
            mostrarPosicao(24);
        }
        else if(jogo.getPosicao()==24)
        {
            mostrarPosicao(0);
            mostrarPosicao(23);
        }
        
        if(jogo.getMapa().get(pos) instanceof CartaWormhole)    // Se a carta actual é um Wormhole
        {
            System.out.println("\nNota: Através deste Wormhole é possível o teletransporte para:\n");
            
            int i=0;    // Iterador
            
            for(Carta c : jogo.getMapa())   // Ciclo que corre as cartas do mapa visível
            {
                if(c instanceof CartaWormhole&&i!=pos)  // Se a carta da actual iteração for um Wormhole e não for a posição actual               
                    mostrarPosicao(i);          // Mostra a carta
                
                i++;
            }
        } 
    }
    
    private String obterString() // Método auxiliar para pedido de strings
    {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    
    private int obterNumero(int de, int ate)    // Método auxiliar para pedidos válidos de inteiros
    {
        Scanner sc = new Scanner(System.in);
        int number;
        
        do 
        {
            while (!sc.hasNextInt()) 
            {
                System.out.println("Valor inválido!");
                sc.next();
            }
            
            number = sc.nextInt();
        } 
        while (number<de||number>ate);
        
        return number;
    }
}