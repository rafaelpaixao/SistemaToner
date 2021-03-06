package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Usuario;

public class UsuarioDAO extends AtributosDAO implements MetodosDAO<Usuario> {

    public UsuarioDAO(Connection conexao) {
        this.conexao = conexao;
        this.nomeTabela = "usuarios";
    }

    @Override
    public boolean cadastrar(Usuario novo) throws SQLException {
        String comandoSql = ""
                + "insert into "
                + this.nomeTabela
                + "("
                + "login,"
                + "senha,"
                + "tipo)"
                + "values (?,?,?);";
        PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
        cadastro.setString(1, novo.getLogin());
        cadastro.setString(2, novo.getSenha());
        cadastro.setString(3, novo.getTipo());
        return cadastro.executeUpdate() != 0;
    }

    @Override
    public boolean excluir(Usuario excluido) throws SQLException {
        String comandoSql = ""
                + "delete from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
        atualizacao.setInt(1, excluido.getId());
        return atualizacao.executeUpdate() != 0;
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

    public Usuario logar(String login, String senha) throws SQLException {

        Usuario u = null;

        String comandoSql = "select * from " + this.nomeTabela + " where login=? and senha=?";

        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        selecao.setString(1, login);
        selecao.setString(2, senha);
        ResultSet resultado = selecao.executeQuery();

        if (resultado.next()) {
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
        String comandoSql = "select * from " + this.nomeTabela + " where login=?";

        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        selecao.setString(1, login);
        ResultSet resultado = selecao.executeQuery();
        boolean retorno = resultado.next();
        selecao.close();
        return retorno;
    }

}
