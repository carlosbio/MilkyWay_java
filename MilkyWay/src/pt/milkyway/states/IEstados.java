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
import pt.milkyway.logic.*;

public interface IEstados   // Interface a ser implementada pelos estados da máquina
{
    Jogo getJogo();    
    
    IEstados loadJogo() throws FileNotFoundException, IOException;  // Override no estado AguardaInicio
    IEstados novoJogo();                // Override no estado AguardaInicio    
    IEstados gravaJogo();               // Override no estado AguardaInicio e AguardaExploracao
    IEstados virarCarta();              // Override no estado AguardaExploracao
    IEstados reporStocks();             // Override no estado AguardaExploracao
    IEstados comecarJogo();             // Override no estado AguardaPreparacao
    IEstados nomeJogador(String n);     // Override no estado AguardaPreparacao    
    IEstados reporPirata();             // Override no estado AguardaStocks
    IEstados removerBrancos();          // Override no estado AguardaStocks
    IEstados reporPlaneta();            // Override no estado AguardaStocks
    IEstados transporteContrabando();   // Override no estado AguardaStocks
    IEstados terminaStocks();           // Override no estado AguardaStocks
    IEstados vendeCargo(Cubo c,int n);  // Override no estado AguardaVendas
    IEstados terminaVendas();           // Override no estado AguardaVendas
    IEstados compraCargo(Cubo c,int n); // Override no estado AguardaCompras
    IEstados efectuaUpgrade(int f);     // Override no estado AguardaCompras
    IEstados terminaCompras();          // Override no estado AguardaCompras
    IEstados terminaJogo();             // Override no estado AguardaMovimento
    IEstados moveNave(int p);           // Override no estado AguardaMovimento
    IEstados terminaMover();            // Override no estado AguardaMovimento
}