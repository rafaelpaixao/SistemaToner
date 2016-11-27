
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AcessoAoBanco {
    
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String banco = "controledetoner";
    private final String usuario = "root";
    private final String senha = "";
    
    private Connection conexao = null;
    
    public Connection getConexao(){
        if(this.conexao==null)
            setConexao();
        return this.conexao;
    }
    
    public void encerrar(){
        try {
            this.conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(AcessoAoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setConexao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conexao = DriverManager.getConnection(this.url+this.banco, this.usuario, this.senha);
            this.conexao.setAutoCommit(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AcessoAoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Conexão com o banco bem sucedida!");
    }
}
