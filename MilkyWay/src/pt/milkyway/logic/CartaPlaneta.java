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

public class CartaPlaneta extends Carta // Carta Planeta Normal
{
    private final String nome;                                // Nome do Planeta Normal
    private final int agua,comida,medicamentos,contrabando;   // Valores dos recursos
    private Cubo stock1;                                // Referência para o primeiro Cubo
    private Cubo stock2;                                // Referência para o segundo Cubo
    
    public CartaPlaneta(String n,int a,int com,int m,int c)  // Construtor
    {
        this.nome=n;
        this.agua=a;
        this.comida=com;
        this.medicamentos=m;
        this.contrabando=c;
    }    

    public String getNome(){return nome;}       // Obtem nome

    public int getAgua(){ return agua;}         // Obtem valor do recurso azul (agua)

    public int getComida(){return comida;}    // Obtem valor do recurso amarelo (comida)

    public int getMedicamentos(){return medicamentos;}  // Obtem valor do recurso vermelho (medicamentos)

    public int getContrabando(){return contrabando;}        // Obtem valor do recurso preto (contrabando)

    public Cubo getStock1(){return stock1;}   // Obtem stock1 (objecto)
    
    public Cubo getStock2(){return stock2;}   // Obtem stock2 (objecto)
    
    public String checkStock1() // Obtem stock 1 (info)
    {
        if(stock1 instanceof CuboAzul)
            return "agua";
        else if(stock1 instanceof CuboAmarelo)
            return "comida";
        else if(stock1 instanceof CuboVermelho)
            return "medicamentos";
        else if(stock1 instanceof CuboBranco)
            return "confiscado";
        else
            return "indisponível";
    }
    
    public String checkStock2() // Obtem stock 2 (info)
    {
        if(stock2 instanceof CuboAzul)
            return "agua";
        else if(stock2 instanceof CuboAmarelo)
            return "comida";
        else if(stock2 instanceof CuboVermelho)
            return "medicamentos";
        else if(stock2 instanceof CuboBranco)
            return "confiscado";
        else
            return "indisponível";
    } 
    
    public void removeStock1(){this.stock1 = null;} // Coloca a null o ponteiro para o objecto (o GC trata de o eliminar)
    
    public void removeStock2(){this.stock2 = null;} // Coloca a null o ponteiro para o objecto (o GC trata de o eliminar)

    public void addStock1(Cubo s)   // Cria um cubo em stock 1 de acordo com a classe que recebe
    {
        if(s instanceof CuboAmarelo)
            this.stock1 = new CuboAmarelo();
        else if(s instanceof CuboAzul)
            this.stock1 = new CuboAzul();
        else if(s instanceof CuboBranco)
            this.stock1 = new CuboBranco();
        else if(s instanceof CuboVermelho)
            this.stock1 = new CuboVermelho();
    }
    
    public void addStock2(Cubo s)   // Cria um cubo em stock 2 de acordo com a classe que recebe
    {
        if(s instanceof CuboAmarelo)
            this.stock2 = new CuboAmarelo();
        else if(s instanceof CuboAzul)
            this.stock2 = new CuboAzul();
        else if(s instanceof CuboBranco)
            this.stock2 = new CuboBranco();
        else if(s instanceof CuboVermelho)
            this.stock2 = new CuboVermelho();
    }
}