
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcessoAoBanco {
    
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String banco = "controledetoner";
    private final String usuario = "root";
    private final String senha = "";
    
    private Connection conexao = null;
    
    public Connection getConexao() throws ClassNotFoundException, SQLException{
        if(this.conexao==null)
            setConexao();
        return this.conexao;
    }
    
    public void encerrar() throws SQLException{
            this.conexao.close();
    }

    private void setConexao() throws ClassNotFoundException, SQLException{
            Class.forName("com.mysql.jdbc.Driver");
            this.conexao = DriverManager.getConnection(this.url+this.banco, this.usuario, this.senha);
            this.conexao.setAutoCommit(true);
    }
}
