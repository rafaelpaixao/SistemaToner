
package model;

import java.sql.Date;

public class Movimentacao {
    private int id;
    private int idToner;
    private int idUsuario;
    private String data;
    private int qtdCheio;
    private int qtdVazio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdToner() {
        return idToner;
    }

    public void setIdToner(int idToner) {
        this.idToner = idToner;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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