
package model;

import java.sql.Date;

public class Entrada {
    private int id;
    private Toner toner;
    private Usuario usuario;
    private Date data;
    private String descricao;
    private int qtdCheio;
    private int qtdVazio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Toner getToner() {
        return toner;
    }

    public void setToner(Toner toner) {
        this.toner = toner;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdCheio() {
        return qtdCheio;
    }

    public void setQtdCheio(int qtdCheio) {
        this.qtdCheio = qtdCheio;
    }

    public int getQtdVazio() {
        return qtdVazio;
    }

    public void setQtdVazio(int qtdVazio) {
        this.qtdVazio = qtdVazio;
    }
    
    
}
