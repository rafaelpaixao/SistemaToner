
package model;

import java.sql.Date;

public abstract class Movimentacao {
    private int id;
    private int idToner;
    private int idUsuario;
    private String data;
    private int quantidade;

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}