
package model;

public class Impressora {
    private int id;
    private Setor setor;
    private String modelo;
    private String modeloToner;
    private double preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getModeloToner() {
        return modeloToner;
    }

    public void setModeloToner(String modeloToner) {
        this.modeloToner = modeloToner;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    
}
