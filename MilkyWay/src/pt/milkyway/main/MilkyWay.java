// ------------------------------------------------------------------------------------ //
//                                                                                      //
//          Programa: Milky Way - Jogo de tabuleiro de comércio espacial                //
//                                                                                      //
//                          Autor: Carlos da Silva                                      //
//                                                                                      //
//                              Data: 04/05/2015                                        //
//                                                                                      //
// ------------------------------------------------------------------------------------ //

package pt.milkyway.main;

import java.io.IOException;

import pt.milkyway.logic.*;
import pt.milkyway.ui.text.IUTexto;

public class MilkyWay
{
    public static void main(String[] args) throws IOException, InterruptedException 
    {
        modoTexto();    // Chama o método que faz correr o jogo em modo de texto
    }
    
    public static void modoTexto()
    {
        // Fase de Desenvolvimento
        
        Jogo.debugMode = false; // Para efeitos de debug colocar o valor a true
        
        IUTexto iuTexto = new IUTexto(new Jogo());  // Cria um objecto da classe IUTexto com um novo objecto Jogo
        
        // Start
        
        iuTexto.iniciaInterface();  // Chama o método que inicia a interface de texto
        
        while (iuTexto.getJogo().getEstadoActual()!=null) // Ciclo principal de iteração 
        {
            try 
            {
                iuTexto.executaInterface(); // Chama o método de execução da interface de texto a cada iteração
            }
            catch (InterruptedException | IOException e) 
            {
                System.err.println(e.getMessage());
            }
            catch (NullPointerException e) 
            {
                if(iuTexto.getJogo().getDinheiro()<1)
                    System.err.println("\n\nBANCARROTA!! \n\nFIM DO JOGO!");
                else if(iuTexto.getJogo().isVitoria())
                    System.err.println("\n\nPARABÉNS!! FIM DO JOGO!");
            }
        }
    }
}