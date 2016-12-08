package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Toner;

public class TonerDAO extends AtributosDAO implements MetodosDAO<Toner> {

    public TonerDAO(Connection conexao) {
        this.conexao = conexao;
        this.nomeTabela = "toners";
    }

    @Override
    public boolean cadastrar(Toner novo) throws SQLException {
        String comandoSql = ""
                + "insert into "
                + this.nomeTabela
                + "("
                + "idModeloImpressora,"
                + "tipo,"
                + "estoque,"
                + "fora,"
                + "desabilitado)"
                + "values (?,?,0,0,0);";
        PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
        cadastro.setInt(1, novo.getIdModeloImpressora());
        cadastro.setString(2, novo.getTipo());
        return cadastro.executeUpdate() !=0;
    }

    public boolean atualizar(Toner atualizado) throws SQLException {
        String comandoSql = ""
                + "update "
                + this.nomeTabela
                + " set "
                + "idModeloImpressora=?, "
                + "tipo=?, "
                + "estoque=?, "
                + "fora=?, "
                + "desabilitado=? "
                + " where id=?";
        PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
        atualizacao.setInt(1, atualizado.getIdModeloImpressora());
        atualizacao.setString(2, atualizado.getTipo());
        atualizacao.setInt(3, atualizado.getEstoque());
        atualizacao.setInt(4, atualizado.getFora());
        atualizacao.setInt(5, atualizado.getDesabilitado());
        atualizacao.setInt(6, atualizado.getId());
        return atualizacao.executeUpdate()!=0;
    }

    @Override
    public boolean excluir(Toner excluido) throws SQLException {
        String comandoSql = ""
                + "delete from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
        atualizacao.setInt(1, excluido.getId());
        return atualizacao.executeUpdate() !=0;
    }
    
    @Override
    public ArrayList<Toner> getTodos() throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela;
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        ResultSet resultado = selecao.executeQuery();

        ArrayList<Toner> lista = new ArrayList<>();
        while (resultado.next()) {
            Toner r = new Toner();
            r.setId(resultado.getInt("id"));
            r.setTipo(resultado.getString("tipo"));
            r.setEstoque(resultado.getInt("estoque"));
            r.setFora(resultado.getInt("fora"));
            r.setDesabilitado(resultado.getInt("desabilitado"));
            r.setIdModeloImpressora(resultado.getInt("idModeloImpressora"));
            lista.add(r);
        }

        selecao.close();
        return lista;
    }

    @Override
    public Toner getPorId(int idProcurado) throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        selecao.setInt(1, idProcurado);
        ResultSet resultado = selecao.executeQuery();

        Toner r = null;
        if (resultado.next()) {
            r = new Toner();
            r.setId(resultado.getInt("id"));
            r.setTipo(resultado.getString("tipo"));
            r.setEstoque(resultado.getInt("estoque"));
            r.setFora(resultado.getInt("fora"));
            r.setDesabilitado(resultado.getInt("desabilitado"));
            r.setIdModeloImpressora(resultado.getInt("idModeloImpressora"));
        }

        selecao.close();
        return r;
    }

    public ResultSet getResultSetSituacaoToner() throws SQLException {
        ResultSet retorno = null;
        String comandoSql = "select "
                + "modelosImpressoras.modeloToner as Toner, "
                + "modelosImpressoras.modeloImpressora as Impressora, "
                + "toners.tipo as Tipo, "
                + "modelosImpressoras.precoToner as Preço, "
                + "toners.estoque as Estoque, "
                + "toners.fora as Fora, "
                + "toners.desabilitado as Desabilitado "
                + "from toners join modelosImpressoras on toners.idModeloImpressora=modelosImpressoras.id "
                + "order by modelosImpressoras.modeloToner";

        PreparedStatement selecao;
        selecao = conexao.prepareStatement(comandoSql);
        retorno = selecao.executeQuery();

        return retorno;
    }
    
    boolean isUtilizadoModeloImpressora(int id)throws SQLException {
        String comandoSql = "select * from "+this.nomeTabela+" where idModeloImpressora=?";
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setInt(1, id);
            return selecao.executeQuery().next();
    }
}
