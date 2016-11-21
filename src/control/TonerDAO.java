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
import model.Toner;

/**
 *
 * @author diegocruzalves
 */
public class TonerDAO {

    Connection connection;
    PreparedStatement stmt;
    String sql;

    public void criarTabela() {

        connection = new ConnectionFactory().getConnection();

        sql = "CREATE TABLE if not exists TONER ("
                + "ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, "
                + "FABRICANTE VARCHAR(50) NOT NULL, "
                + "MODELO VARCHAR(50) NOT NULL, "
                + "NUM_SERIE VARCHAR(50) NOT NULL, "
                + "BP INT NOT NULL, "
                + "SETOR VARCHAR(50) NOT NULL, "
                + "PRECO DOUBLE NOT NULL, "
                + "EMPRESA VARCHAR(50) NOT NULL, "
                + "STATUS VARCHAR(50) NOT NULL, "
                + "TIPO_TONER VARCHAR(50) NOT NULL);";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void cadastrarToner(Toner toner) {
        connection = new ConnectionFactory().getConnection();
        sql = "INSERT INTO TONER (FABRICANTE, MODELO, NUM_SERIE, BP, SETOR, PRECO, EMPRESA, STATUS, TIPO_TONER)"
                + " VALUES (?,?,?,?,?,?,?,?,?);";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, toner.getFabricante());
            stmt.setString(2, toner.getModelo());
            stmt.setString(3, toner.getNum_serie());
            stmt.setInt(4, toner.getBp());
            stmt.setString(5, toner.getSetor());
            stmt.setDouble(6, toner.getPreco());
            stmt.setString(7, toner.getEmpresa());
            stmt.setString(8, toner.getStatus());
            stmt.setString(9, toner.getTipoToner());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void editarToner(Toner toner) {
        connection = new ConnectionFactory().getConnection();
        sql = "UPDATE TONER SET MODELO = ?, NUM_SERIE = ?, BP = ?, EMPRESA = ?, PRECO = ?, TIPO_TONER = ? WHERE ID = ?";
        try {
            stmt = connection.prepareStatement(sql);

            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Toner> exibirToner(Toner toner) {
        connection = new ConnectionFactory().getConnection();
        ArrayList<Toner> lista = new ArrayList<>();
        sql = "SELECT * FROM TONER ORDER BY ID ASC";
        try {
            stmt = connection.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                toner = new Toner();
                toner.setId(rs.getInt("ID"));
                toner.setFabricante(rs.getString("FABRICANTE"));
                toner.setModelo(rs.getString("MODELO"));
                toner.setNum_serie(rs.getString("NUM_SERIE"));
                toner.setBp(rs.getInt("BP"));
                toner.setSetor(rs.getString("SETOR"));
                toner.setPreco(rs.getDouble("PRECO"));
                toner.setEmpresa(rs.getString("EMPRESA"));
                toner.setStatus(rs.getString("STATUS"));
                toner.setTipoToner(rs.getString("TIPO_TONER"));
                lista.add(toner);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
