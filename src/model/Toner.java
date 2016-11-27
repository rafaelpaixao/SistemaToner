
package model;

public class Toner {
    private int id;
    private int idImpressora;
    private String tipo;
    private int qtdEstoqueCheio;
    private int qtdEstoqueVazio;
    private int qtdForaCheio;
    private int qtdForaVazio;
    private int qtdDesabilitadoCheio;
    private int qtdDesabilitadoVazio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdImpressora() {
        return idImpressora;
    }

    public void setIdImpressora(int idImpressora) {
        this.idImpressora = idImpressora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQtdEstoqueCheio() {
        return qtdEstoqueCheio;
    }

    public void setQtdEstoqueCheio(int qtdEstoqueCheio) {
        this.qtdEstoqueCheio = qtdEstoqueCheio;
    }

    public int getQtdEstoqueVazio() {
        return qtdEstoqueVazio;
    }

    public void setQtdEstoqueVazio(int qtdEstoqueVazio) {
        this.qtdEstoqueVazio = qtdEstoqueVazio;
    }

    public int getQtdForaCheio() {
        return qtdForaCheio;
    }

    public void setQtdForaCheio(int qtdForaCheio) {
        this.qtdForaCheio = qtdForaCheio;
    }

    public int getQtdForaVazio() {
        return qtdForaVazio;
    }

    public void setQtdForaVazio(int qtdForaVazio) {
        this.qtdForaVazio = qtdForaVazio;
    }

    public int getQtdDesabilitadoCheio() {
        return qtdDesabilitadoCheio;
    }

    public void setQtdDesabilitadoCheio(int qtdDesabilitadoCheio) {
        this.qtdDesabilitadoCheio = qtdDesabilitadoCheio;
    }

    public int getQtdDesabilitadoVazio() {
        return qtdDesabilitadoVazio;
    }

    public void setQtdDesabilitadoVazio(int qtdDesabilitadoVazio) {
        this.qtdDesabilitadoVazio = qtdDesabilitadoVazio;
    }
    
}
