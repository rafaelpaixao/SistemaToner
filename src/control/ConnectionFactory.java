package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionFactory {

    public Connection getConnection() {
        try {
            //estabele conexão com o banco de dados
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/controledetoner?autoReconnect=true&useSSL=false", "root", "");
            
        } catch (SQLException excecao) {
            throw new RuntimeException(excecao);
        }
    }

    
} //end main
