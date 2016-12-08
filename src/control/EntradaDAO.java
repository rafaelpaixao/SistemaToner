package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Entrada;

public class EntradaDAO extends AtributosDAO implements MetodosDAO< Entrada> {

    public EntradaDAO(Connection conexao) {
        this.conexao = conexao;
        this.nomeTabela = "entradas";
    }

    @Override
    public boolean cadastrar(Entrada novo) throws SQLException {
        String comandoSql = ""
                + "insert into "
                + this.nomeTabela
                + "("
                + "idToner,"
                + "idUsuario,"
                + "data,"
                + "quantidade,"
                + "tipo)"
                + "values (?,?,?,?,?)";
        PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
        cadastro.setInt(1, novo.getIdToner());
        cadastro.setInt(2, novo.getIdUsuario());
        cadastro.setString(3, novo.getData());
        cadastro.setInt(4, novo.getQuantidade());
        cadastro.setString(5, novo.getTipoDeEntrada());
        return cadastro.executeUpdate() != 0;
    }

    @Override
    public boolean excluir(Entrada excluido) throws SQLException {
        String comandoSql = ""
                + "delete from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
        atualizacao.setInt(1, excluido.getId());
        return atualizacao.executeUpdate() != 0;
    }

    @Override
    public ArrayList< Entrada> getTodos() throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela;
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        ResultSet resultado = selecao.executeQuery();

        ArrayList< Entrada> lista = new ArrayList<>();
        while (resultado.next()) {
            Entrada r = new Entrada();
            r.setId(resultado.getInt("id"));
            r.setIdToner(resultado.getInt("idToner"));
            r.setIdUsuario(resultado.getInt("idUsuario"));
            r.setData(resultado.getString("data"));
            r.setQuantidade(resultado.getInt("quantidade"));
            r.setTipoDeEntrada(resultado.getString("tipo"));
            lista.add(r);
        }

        selecao.close();
        return lista;
    }

    @Override
    public Entrada getPorId(int idProcurado) throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        selecao.setInt(1, idProcurado);
        ResultSet resultado = selecao.executeQuery();

        Entrada r = null;
        if (resultado.next()) {
            r = new Entrada();
            r.setId(resultado.getInt("id"));
            r.setIdToner(resultado.getInt("idToner"));
            r.setIdUsuario(resultado.getInt("idUsuario"));
            r.setData(resultado.getString("data"));
            r.setQuantidade(resultado.getInt("quantidade"));
            r.setTipoDeEntrada(resultado.getString("tipo"));
        }

        selecao.close();
        return r;
    }

    public ResultSet getResultSetEntradas() throws SQLException {
        String comandoSql = "select "
                + "entradas.data as Data, "
                + "entradas.tipo as Tipo, "
                + "usuarios.login as Usuário, "
                + "modelosImpressoras.modeloToner as Toner, "
                + "modelosImpressoras.modeloImpressora as Impressora, "
                + "entradas.quantidade "
                + "from entradas "
                + "join usuarios on entradas.idUsuario=usuarios.id "
                + "join toners on entradas.idToner=toners.id "
                + "join modelosImpressoras on toners.idModeloImpressora = modelosImpressoras.id "
                + "order by entradas.data desc";
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        return selecao.executeQuery();
    }

    boolean isUtilizadoUsuario(int id) throws SQLException {
        String comandoSql = "select * from " + this.nomeTabela + " where idUsuario=?";
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        selecao.setInt(1, id);
        return selecao.executeQuery().next();
    }

    boolean isUtilizadoToner(int id) throws SQLException {
        String comandoSql = "select * from " + this.nomeTabela + " where idToner=?";
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        selecao.setInt(1, id);
        return selecao.executeQuery().next();
    }
}
