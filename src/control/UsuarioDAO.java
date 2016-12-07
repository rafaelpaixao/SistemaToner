
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Usuario;

public class UsuarioDAO extends atributosDAO implements metodosDAO<Usuario>{
    
    public UsuarioDAO(Connection conexao){
        this.conexao = conexao;
        this.nomeTabela = "usuarios";
    }

    @Override
    public void cadastrar(Usuario novo) throws SQLException {
        String comandoSql = ""
                + "insert into "
                + this.nomeTabela
                + "("
                + "login,"
                + "senha,"
                + "tipoDeUsuario)"
                + "values (?,?,?);";
            PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
            cadastro.setString(1, novo.getLogin());
            cadastro.setString(2, novo.getSenha());
            cadastro.setString(3, novo.getTipo());
        cadastro.executeUpdate();
        cadastro.close();
    }

    @Override
    public void atualizar(Usuario atualizado) throws SQLException {
        String comandoSql = ""
                + "update "
                + this.nomeTabela
                + " set "
                + "login=?, "
                + "senha=?,"
                + "tipo=? "
                + "where id=?";

            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setString(1, atualizado.getLogin());
            atualizacao.setString(2, atualizado.getSenha());
            atualizacao.setString(3, atualizado.getTipo());
            atualizacao.setInt(4, atualizado.getId());
        atualizacao.setInt(4, atualizado.getId());
        atualizacao.executeUpdate();
        atualizacao.close();
    }

    @Override
    public void excluir(Usuario excluido) throws SQLException {
        String comandoSql = ""
                + "delete from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
        atualizacao.setInt(1, excluido.getId());
        atualizacao.executeUpdate();
        atualizacao.close();
    }

    @Override
    public ArrayList<Usuario> getTodos() throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela;
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        ResultSet resultado = selecao.executeQuery();

        ArrayList<Usuario> lista = new ArrayList<>();
        while (resultado.next()) {
            Usuario r = new Usuario();
            r.setId(resultado.getInt("id"));
            r.setLogin(resultado.getString("login"));
                r.setSenha(resultado.getString("senha"));
                r.setTipo(resultado.getString("tipo"));
            lista.add(r);
        }

        selecao.close();
        return lista;
    }

    @Override
    public Usuario getPorId(int idProcurado) throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        selecao.setInt(1, idProcurado);
        ResultSet resultado = selecao.executeQuery();

        Usuario r = null;
        if (resultado.next()) {
            r = new Usuario();
            r.setId(resultado.getInt("id"));
            r.setLogin(resultado.getString("login"));
                r.setSenha(resultado.getString("senha"));
                r.setTipo(resultado.getString("tipo"));
        }

        selecao.close();
        return r;
    }
    
    public Usuario logar(String login,String senha) throws SQLException{
        
        Usuario u = null;
        
        String comandoSql = "select * from "+this.nomeTabela+" where login=? and senha=?";

            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setString(1, login);
            selecao.setString(2, senha);
            ResultSet resultado = selecao.executeQuery();

            if(resultado.next()){
                u = new Usuario();
                u.setId(resultado.getInt("id"));
                u.setLogin(resultado.getString("login"));
                u.setSenha(resultado.getString("senha"));
                u.setTipo(resultado.getString("tipo"));
            }
            
            selecao.close();
        
        return u;
    }

    boolean existeLogin(String login) throws SQLException {
        String comandoSql = "select * from "+this.nomeTabela+" where login=?";

            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setString(1, login);
            ResultSet resultado = selecao.executeQuery();
            boolean retorno = resultado.next();
            selecao.close();
            return retorno;
    }
}
