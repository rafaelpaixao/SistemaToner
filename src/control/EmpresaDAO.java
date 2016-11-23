/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Empresa;

/**
 *
 * @author diegocruzalves
 */
public class EmpresaDAO {
    
    Connection connection;
    PreparedStatement stmt;
    String sql;
    
    public void criarTabela() {

        connection = new ConnectionFactory().getConnection();

        sql = "CREATE TABLE if not exists EMPRESA ("
                + "ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, "
                + "NOME VARCHAR(50) NOT NULL, "
                + "CNPJ VARCHAR(50) NOT NULL);";
                

        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void cadastrarEmpresa(Empresa empresa) {
        connection = new ConnectionFactory().getConnection();
        sql = "INSERT INTO EMPRESA (NOME, CNPJ)"
                + " VALUES (?,?);";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, empresa.getNome());
            stmt.setString(2, empresa.getCnpj());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void editarEmpresa(Empresa empresa) {
        connection = new ConnectionFactory().getConnection();
        sql = "UPDATE EMPRESA SET NOME = ?, CNPJ = ?";
        try {
            stmt = connection.prepareStatement(sql);

            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Empresa> exibirToner(Empresa empresa) {
        connection = new ConnectionFactory().getConnection();
        ArrayList<Empresa> lista = new ArrayList<>();
        sql = "SELECT * FROM EMPRESA ORDER BY ID ASC";
        try {
            stmt = connection.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                empresa = new Empresa();
                empresa.setId(rs.getInt("ID"));
                empresa.setNome(rs.getString("NOME"));
                empresa.setCnpj(rs.getString("CNPJ"));
                lista.add(empresa);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    
}
