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

import java.io.Serializable;

public class Nave extends Base implements Serializable  // Classe Nave
{
    private int capacidade=2;   // Capacidade do porão de carga (min 2 - max 3)
    private int energia=3;      // Energia de combate (min 3 - max 5)
    private Cubo cargo1;        // Porão 1
    private Cubo cargo2;        // Porão 2
    private Cubo cargo3;        // Porão 3 (disponível após upgrade)

    public Nave(){} // Construtor

    public int getCapacidade(){return capacidade;}  // Obtem capacidade

    public void setCapacidade(int capacidade){this.capacidade = capacidade;}    // Define capacidade

    public int getEnergia(){return energia;}    // Obtem energia de combate

    public void setEnergia(int energia){this.energia = energia;}    // Define energia de combate

    public Cubo getCargo1(){return cargo1;} // Obtem cargo 1
    
    public Cubo getCargo2(){return cargo2;} // Obtem cargo 2
    
    public Cubo getCargo3(){return cargo3;} // Obtem cargo 3
    
    public void removeCargo1(){this.cargo1 = null;} // Coloca a null o ponteiro para o objecto (o GC trata de o eliminar)
    
    public void removeCargo2(){this.cargo2 = null;} // Coloca a null o ponteiro para o objecto (o GC trata de o eliminar)
    
    public void removeCargo3(){this.cargo3 = null;} // Coloca a null o ponteiro para o objecto (o GC trata de o eliminar)
    
    public String checkCargo1() // Obtem cargo1 (info)
    {
        if(cargo1 instanceof CuboAzul)
            return "agua";
        else if(cargo1 instanceof CuboAmarelo)
            return "comida";
        else if(cargo1 instanceof CuboVermelho)
            return "medicamentos";
        else if(cargo1 instanceof CuboPreto)
            return "contrabando";
        else
            return "vazio";
    }
    
    public String checkCargo2() // Obtem cargo2 (info)
    {
        if(cargo2 instanceof CuboAzul)
            return "agua";
        else if(cargo2 instanceof CuboAmarelo)
            return "comida";
        else if(cargo2 instanceof CuboVermelho)
            return "medicamentos";
        else if(cargo2 instanceof CuboPreto)
            return "contrabando";
        else
            return "vazio";
    }
    
    public String checkCargo3() // Obtem cargo3 (info)
    {
        if(cargo3 instanceof CuboAzul)
            return "agua";
        else if(cargo3 instanceof CuboAmarelo)
            return "comida";
        else if(cargo3 instanceof CuboVermelho)
            return "medicamentos";
        else if(cargo3 instanceof CuboPreto)
            return "contrabando";
        else
            return "vazio";
    }
    
    public void addCargo1(Cubo s)   // Cria um cubo em cargo 1 de acordo com a classe que recebe
    {
        if(s instanceof CuboAmarelo)
            this.cargo1 = new CuboAmarelo();
        else if(s instanceof CuboAzul)
            this.cargo1 = new CuboAzul();
        else if(s instanceof CuboVermelho)
            this.cargo1 = new CuboVermelho();
        else if(s instanceof CuboPreto)
            this.cargo1 = new CuboPreto();
    }
    
    public void addCargo2(Cubo s)   // Cria um cubo em cargo 2 de acordo com a classe que recebe
    {
        if(s instanceof CuboAmarelo)
            this.cargo2 = new CuboAmarelo();
        else if(s instanceof CuboAzul)
            this.cargo2 = new CuboAzul();
        else if(s instanceof CuboVermelho)
            this.cargo2 = new CuboVermelho();
        else if(s instanceof CuboPreto)
            this.cargo2 = new CuboPreto();
    }
    
    public void addCargo3(Cubo s)   // Cria um cubo em cargo 3 de acordo com a classe que recebe
    {
        if(s instanceof CuboAmarelo)
            this.cargo3 = new CuboAmarelo();
        else if(s instanceof CuboAzul)
            this.cargo3 = new CuboAzul();
        else if(s instanceof CuboVermelho)
            this.cargo3 = new CuboVermelho();
        else if(s instanceof CuboPreto)
            this.cargo3 = new CuboPreto();
    }
}