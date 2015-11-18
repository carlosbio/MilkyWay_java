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

public class CuboBranco extends Cubo    // Classe Cubo Branco
{
    private String nome;   
    
    public CuboBranco(){this.nome = "confiscado";}   // Construtor

    public String getNome(){return nome;}   // Getter

    public void setNome(String nome){this.nome = nome;} // Setter
}