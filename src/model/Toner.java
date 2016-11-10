/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Rafael
 */
public class Toner {
    int id;
    String descricao;
    String tipo;
    Empresa empresaAbastecedora;
    double preco;
    int qtdDisponivel;
    
    public String toSQL(){
        //Escrever método que serializa as variaveis desse objeto para inserção no banco
        return null;
    }
}
