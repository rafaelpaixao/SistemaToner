package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Setor;

public class SetorDAO extends AtributosDAO implements MetodosDAO<Setor> {

    public SetorDAO(Connection conexao) {
        this.conexao = conexao;
        this.nomeTabela = "setores";
    }

    @Override
    public boolean cadastrar(Setor novo) throws SQLException {
        String comandoSql = ""
                + "insert into "
                + this.nomeTabela
                + "("
                + "nome,"
                + "empresa)"
                + "values (?,?);";
        PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
        cadastro.setString(1, novo.getNome());
        cadastro.setString(2, novo.getEmpresa());
        return cadastro.executeUpdate() !=0;
    }

    @Override
    public boolean excluir(Setor excluido) throws SQLException {
        String comandoSql = ""
                + "delete from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
        atualizacao.setInt(1, excluido.getId());
        return atualizacao.executeUpdate() !=0;
    }

    @Override
    public ArrayList<Setor> getTodos() throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela;
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        ResultSet resultado = selecao.executeQuery();

        ArrayList<Setor> lista = new ArrayList<>();
        while (resultado.next()) {
            Setor r = new Setor();
            r.setId(resultado.getInt("id"));
            r.setNome(resultado.getString("nome"));
            r.setEmpresa(resultado.getString("empresa"));
            lista.add(r);
        }

        selecao.close();
        return lista;
    }

    @Override
    public Setor getPorId(int idProcurado) throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        selecao.setInt(1, idProcurado);
        ResultSet resultado = selecao.executeQuery();

        Setor r = null;
        if (resultado.next()) {
            r = new Setor();
            r.setId(resultado.getInt("id"));
              r.setNome(resultado.getString("nome"));
            r.setEmpresa(resultado.getString("empresa"));
        }

        selecao.close();
        return r;
    }
    
    
}
