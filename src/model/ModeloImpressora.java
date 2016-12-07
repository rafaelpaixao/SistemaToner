package model;

public class ModeloImpressora {
    private int id;
    private String modeloImpressora;
    private String modeloToner;
    private double precoToner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModeloImpressora() {
        return modeloImpressora;
    }

    public void setModeloImpressora(String modeloImpressora) {
        this.modeloImpressora = modeloImpressora;
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
