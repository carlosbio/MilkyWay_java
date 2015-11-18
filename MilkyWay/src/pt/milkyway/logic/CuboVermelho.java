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

public class CuboVermelho extends Cubo  // Classe Cubo Vermelho
{
    private String nome; 
    
    public CuboVermelho(){this.nome = "medicamentos";} // Construtor

    public String getNome(){return nome;}   // Getter

    public void setNome(String nome){this.nome = nome;} // Setter
}