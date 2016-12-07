package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Impressora;

public class ImpressoraDAO extends atributosDAO implements metodosDAO<Impressora> {

    public ImpressoraDAO(Connection conexao) {
        this.conexao = conexao;
        this.nomeTabela = "impressoras";
    }

    @Override
    public void cadastrar(Impressora novo) throws SQLException {
        String comandoSql = ""
                + "insert into "
                + this.nomeTabela
                + "("
                + "idModeloImpressora,"
                + "idSetor,"
                + "tipo,"
                + "codigo"
                + ")"
                + "values (?,?,?,?);";
        PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
        cadastro.setInt(1, novo.getIdModeloImpressora());
        cadastro.setInt(2, novo.getIdSetor());
        cadastro.setString(3, novo.getTipo());
        cadastro.setString(4, novo.getCodigo());
        cadastro.executeUpdate();
        cadastro.close();
    }

    @Override
    public void atualizar(Impressora atualizado) throws SQLException {
        String comandoSql = ""
                + "update "
                + this.nomeTabela
                + " set "
                + "idModeloImpressora=?, "
                + "idSetor=?, "
                + "tipo=?, "
                + "codigo=? "
                + "where id=?";
        PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
        atualizacao.setInt(1, atualizado.getIdModeloImpressora());
        atualizacao.setInt(2, atualizado.getIdSetor());
        atualizacao.setString(3, atualizado.getTipo());
        atualizacao.setString(4, atualizado.getCodigo());
        atualizacao.setInt(5, atualizado.getId());
        atualizacao.executeUpdate();
        atualizacao.close();
    }

    @Override
    public void excluir(Impressora excluido) throws SQLException {
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
    public ArrayList<Impressora> getTodos() throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela;
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        ResultSet resultado = selecao.executeQuery();

        ArrayList<Impressora> lista = new ArrayList<>();
        while (resultado.next()) {
            Impressora r = new Impressora();
            r.setId(resultado.getInt("id"));
            r.setIdModeloImpressora(resultado.getInt("idModeloImpressora"));
            r.setIdSetor(resultado.getInt("idSetor"));
            r.setTipo(resultado.getString("tipo"));
            r.setTipo(resultado.getString("codigo"));
            lista.add(r);
        }

        selecao.close();
        return lista;
    }

    @Override
    public Impressora getPorId(int idProcurado) throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        selecao.setInt(1, idProcurado);
        ResultSet resultado = selecao.executeQuery();

        Impressora r = null;
        if (resultado.next()) {
            r = new Impressora();
            r.setId(resultado.getInt("id"));
            r.setIdModeloImpressora(resultado.getInt("idModeloImpressora"));
            r.setIdSetor(resultado.getInt("idSetor"));
            r.setTipo(resultado.getString("tipo"));
            r.setTipo(resultado.getString("codigo"));
        }

        selecao.close();
        return r;
    }

    public boolean temImpressoraNoSetor(String modeloImpressora, int idSetor) throws SQLException {
        String comandoSql = ""
                + "select * from impressoras "
                + "join modelosImpressoras "
                + "on impressoras.idModeloImpressora = modelosImpressoras.id "
                + "where impressoras.idSetor=? AND modelosImpressoras.modeloImpressora=?";
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setInt(1, idSetor);
            selecao.setString(2, modeloImpressora);
            ResultSet resultado = selecao.executeQuery();
            return resultado.next();
    }

}
