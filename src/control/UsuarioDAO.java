
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Usuario;


public class UsuarioDAO {
    Connection conexao;
    
    public UsuarioDAO(Connection conexao){
        this.conexao = conexao;
    }
    
    public void cadastrar(Usuario usuario){
        String comandoSql = ""
                + "insert into usuarios ("
                + "login,"
                + "senha,"
                + "tipoDeUsuario)"
                + "values (?,?,?);";
        try {
            PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
            cadastro.setString(1, usuario.getLogin());
            cadastro.setString(2, usuario.getSenha());
            cadastro.setString(3, usuario.getTipo());
            cadastro.executeUpdate();
            cadastro.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void atualizar(Usuario usuario){
        String comandoSql = ""
                + "update usuarios "
                + "set "
                + "login=?, "
                + "senha=?,"
                + "tipo=? "
                + "where idUsuario=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setString(1, usuario.getLogin());
            atualizacao.setString(2, usuario.getSenha());
            atualizacao.setString(3, usuario.getTipo());
            atualizacao.setInt(4, usuario.getId());
            atualizacao.executeUpdate();
            atualizacao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void deletar(Usuario usuario){
        String comandoSql = ""
                + "delete from usuarios "
                + "where idUsuario=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setInt(1, usuario.getId());
            atualizacao.executeUpdate();
            atualizacao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public ArrayList<Usuario> getTodosUsuarios(Usuario usuario){
        
        ArrayList<Usuario> lista = new ArrayList<>();
        
        String comandoSql = "select * from usuarios";

        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            ResultSet resultado = selecao.executeQuery();
            
            while(resultado.next()){
                Usuario u = new Usuario();
                u.setId(resultado.getInt("idUsuario"));
                u.setLogin(resultado.getString("login"));
                u.setSenha(resultado.getString("senha"));
                u.setTipo(resultado.getString("tipoDeUsuario"));
                lista.add(u);
            }
            
            selecao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return lista;
    }
    
    public Usuario getUsuarioPorId(int idProcurado){
        
        Usuario u = null;
        
        String comandoSql = "select * from usuarios where idUsuario=?";

        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setInt(1, idProcurado);
            ResultSet resultado = selecao.executeQuery();
            
            if(resultado.next()){
                u = new Usuario();
                u.setId(resultado.getInt("idUsuario"));
                u.setLogin(resultado.getString("login"));
                u.setSenha(resultado.getString("senha"));
                u.setTipo(resultado.getString("tipoDeUsuario"));
            }
            
            selecao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return u;
    }
    
    public Usuario logar(String login,String senha){
        
        Usuario u = null;
        
        String comandoSql = "select * from usuarios where login=? and senha=?";

        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setString(1, login);
            selecao.setString(2, senha);
            ResultSet resultado = selecao.executeQuery();

            if(resultado.next()){
                u = new Usuario();
                u.setId(resultado.getInt("idUsuario"));
                u.setLogin(resultado.getString("login"));
                u.setSenha(resultado.getString("senha"));
                u.setTipo(resultado.getString("tipoDeUsuario"));
            }
            
            selecao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return u;
    }
}
