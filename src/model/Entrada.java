
package model;

public class Entrada extends Movimentacao{
    private String tipoDeEntrada; //Cadastro ou recarga

    public String getTipoDeEntrada() {
        return tipoDeEntrada;
    }

    public void setTipoDeEntrada(String tipoDeEntrada) {
        this.tipoDeEntrada = tipoDeEntrada;
    }
}
