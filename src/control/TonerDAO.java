/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                + "MODELO VARCHAR(50) NOT NULL, "
                + "NUM_SERIE VARCHAR(50) NOT NULL, "
                + "BP INT NOT NULL, "
                + "EMPRESA VARCHAR(50) NOT NULL, "
                + "PRECO DOUBLE NOT NULL, "
                + "TIPO_TONER VARCHAR(50) NOT NULL);";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

    }

    public void cadastrarToner(Toner toner) {

        connection = new ConnectionFactory().getConnection();
        
        sql = "INSERT INTO TONER (MODELO, NUM_SERIE, BP, EMPRESA, PRECO, TIPO_TONER)"
                    + " VALUES (?,?,?,?,?,?);";

        try {
            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, toner.getModelo());
            stmt.setString(2, toner.getNum_serie());
            stmt.setInt(3, toner.getBp());
            stmt.setString(4, toner.getEmpresa());
            stmt.setDouble(5, toner.getPreco());
            stmt.setString(6, toner.getTipoToner());
            
            
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
