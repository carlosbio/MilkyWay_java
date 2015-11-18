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

public class CartaPirata extends Carta  // Carta Planeta Pirata
{
    private final String nome;      // Nome do Planeta Pirata
    private final String recurso;   // Nome do único recurso que o planeta pirata compra
    private CuboPreto stock;        // Referência para o cubo preto em stock

    public CartaPirata(String n,String r)   // Construtor
    {
        this.nome=n;
        this.recurso=r;
    }  

    public String getNome(){return nome;}   // Obtem nome
    
    public String getRecurso(){return recurso;}   // Obtem nome do recurso que o planeta pirata compra

    public CuboPreto getStock(){return stock;}    // Obtem recurso (objecto)
    
    public String checkStock()    // Obtem recurso (info)
    {
        if(stock==null)
            return "indisponível";
        else
            return "contrabando";
    }

    public void addStock(CuboPreto s){this.stock = new CuboPreto();}    // Cria um cubo preto em stock
    
    public void removeStock(){this.stock = null;}    // Coloca a null o ponteiro para o objecto (o GC trata de o eliminar)
}