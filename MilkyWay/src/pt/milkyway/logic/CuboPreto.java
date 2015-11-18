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

public class CuboPreto extends Cubo // Classe Cubo Preto
{
    private String nome;  
    
    public CuboPreto(){this.nome = "contrabando";}    // Construtor

    public String getNome(){return nome;}   // Getter

    public void setNome(String nome){this.nome = nome;} // Setter
}