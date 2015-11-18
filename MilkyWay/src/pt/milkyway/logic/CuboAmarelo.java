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

public class CuboAmarelo extends Cubo   // Classe Cubo Amarelo
{
    private String nome;   
    
    public CuboAmarelo(){this.nome = "comida";}  // Construtor

    public String getNome(){return nome;}   // Getter

    public void setNome(String nome){this.nome = nome;} // Setter
}