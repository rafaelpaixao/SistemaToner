
package model;

public class Toner {
    private int id;
    private int idModeloImpressora;
    private String tipo;
    private int estoque;
    private int fora;
    private int desabilitado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdModeloImpressora() {
        return idModeloImpressora;
    }

    public void setIdModeloImpressora(int idModeloImpressora) {
        this.idModeloImpressora = idModeloImpressora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getFora() {
        return fora;
    }

    public void setFora(int fora) {
        this.fora = fora;
    }

    public int getDesabilitado() {
        return desabilitado;
    }

    public void setDesabilitado(int desabilitado) {
        this.desabilitado = desabilitado;
    }
    
}
