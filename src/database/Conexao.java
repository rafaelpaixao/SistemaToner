package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Toner;

public class Conexao {

    Connection conexao = null;

    public Conexao() {
        try {
            Class.forName("org.sqlite.JDBC");
            conexao = DriverManager.getConnection("jdbc:sqlite:sistematoner.db");
            conexao.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erro em Banco() - "+e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Banco iniciado com sucesso!");
    }

    public void criarTabela() {
        Statement stmt = null;
        try {
            stmt = conexao.createStatement();
            String sql = "CREATE TABLE if not exists TONER ("
                    + "ID INT PRIMARY KEY NOT NULL, "
                    + "DESCRICAO TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            conexao.commit();
        } catch (Exception e) {
            System.err.println("Erro em criarTabela() - "+e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Tabela criada!");
    }

    public boolean existeTabela(){
        boolean retorno = false;
        Statement stmt = null;
        try {
            stmt = conexao.createStatement();
            String sql = "SELECT * FROM sqlite_master WHERE name ='TONER' and type='table';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                retorno = true;
            }
            rs.close();
            stmt.close();
            conexao.commit();
        } catch (Exception e) {
            System.err.println("Erro em existeTabela() - "+e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return retorno;
    }
    
    public void insertToner(Toner t) {
        Statement stmt = null;
        try {
            stmt = conexao.createStatement();
            String sql = "INSERT INTO TONER (ID, DESCRICAO)"
                    + " VALUES " + t.toSQL() + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            conexao.commit();
        } catch (Exception e) {
            System.err.println("Erro em insertRevendedora() - "+e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
