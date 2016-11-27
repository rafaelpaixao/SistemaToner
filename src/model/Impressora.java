
package model;

public class Impressora {
    private int id;
    private int idSetor;
    private String modelo;
    private String modeloToner;
    private double precoToner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
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

    public double getPrecoToner() {
        return precoToner;
    }

    public void setPrecoToner(double precoToner) {
        this.precoToner = precoToner;
    }
    
    
}
