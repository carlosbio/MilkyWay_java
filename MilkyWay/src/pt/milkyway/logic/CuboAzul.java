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

public class CuboAzul extends Cubo  // Classe Cubo Azul
{
    private String nome; 
    
    public CuboAzul(){this.nome = "agua";}  // Construtor

    public String getNome(){return nome;}   // Getter

    public void setNome(String nome){this.nome = nome;} // Setter
}