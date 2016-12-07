
package model;

public class Impressora {
    private int id;
    private int idModeloImpressora;
    private int idSetor;
    private String tipo;
    private String codigo;

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

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
  
}
