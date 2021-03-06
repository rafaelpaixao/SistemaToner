package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Saida;

public class SaidaDAO extends AtributosDAO implements MetodosDAO<Saida> {

    public SaidaDAO(Connection conexao) {
        this.conexao = conexao;
        this.nomeTabela = "saidas";
    }

    @Override
    public boolean cadastrar(Saida novo) throws SQLException {
        String comandoSql = ""
                + "insert into "
                + this.nomeTabela
                + "("
                + "idToner,"
                + "idUsuario,"
                + "data,"
                + "quantidade,"
                + "idSetor)"
                + "values (?,?,?,?,?)";
        PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
        cadastro.setInt(1, novo.getIdToner());
        cadastro.setInt(2, novo.getIdUsuario());
        cadastro.setString(3, novo.getData());
        cadastro.setInt(4, novo.getQuantidade());
        cadastro.setInt(5, novo.getIdSetor());
        return cadastro.executeUpdate() != 0;
    }

    @Override
    public boolean excluir(Saida excluido) throws SQLException {
        String comandoSql = ""
                + "delete from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
        atualizacao.setInt(1, excluido.getId());
        return atualizacao.executeUpdate() !=0;
    }

    @Override
    public ArrayList<Saida> getTodos() throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela;
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        ResultSet resultado = selecao.executeQuery();

        ArrayList<Saida> lista = new ArrayList<>();
        while (resultado.next()) {
            Saida r = new Saida();
            r.setId(resultado.getInt("id"));
            r.setIdToner(resultado.getInt("idToner"));
            r.setIdUsuario(resultado.getInt("idUsuario"));
            r.setData(resultado.getString("data"));
            r.setQuantidade(resultado.getInt("quantidade"));
            r.setIdSetor(resultado.getInt("idSetor"));
            lista.add(r);
        }

        selecao.close();
        return lista;
    }

    @Override
    public Saida getPorId(int idProcurado) throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        selecao.setInt(1, idProcurado);
        ResultSet resultado = selecao.executeQuery();

        Saida r = null;
        if (resultado.next()) {
            r = new Saida();
            r.setId(resultado.getInt("id"));
            r.setIdToner(resultado.getInt("idToner"));
            r.setIdUsuario(resultado.getInt("idUsuario"));
            r.setData(resultado.getString("data"));
            r.setQuantidade(resultado.getInt("quantidade"));
            r.setIdSetor(resultado.getInt("idSetor"));
        }

        selecao.close();
        return r;
    }
    
    public ResultSet getResultSetSaidas() throws SQLException {
        String comandoSql = "select "
                + "saidas.data as Data, "
                + "setores.nome as Setor, "
                + "setores.empresa as Empresa, "
                + "usuarios.login as Usuário, "
                + "modelosImpressoras.modeloToner as Toner, "
                + "modelosImpressoras.modeloImpressora as Impressora, "
                + "saidas.quantidade as Quantidade "
                + "from saidas "
                + "join usuarios on saidas.idUsuario=usuarios.id "
                + "join toners on saidas.idToner=toners.id "
                + "join modelosImpressoras on toners.idModeloImpressora = modelosImpressoras.id "
                + "join setores on saidas.idSetor = setores.id "
                + "order by saidas.data desc";
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        return selecao.executeQuery();
    }
    
    boolean isUtilizadoUsuario(int id)throws SQLException {
        String comandoSql = "select * from "+this.nomeTabela+" where idUsuario=?";
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setInt(1, id);
            return selecao.executeQuery().next();
    }
    
    boolean isUtilizadoToner(int id)throws SQLException {
        String comandoSql = "select * from "+this.nomeTabela+" where idToner=?";
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setInt(1, id);
            return selecao.executeQuery().next();
    }
    
    boolean isUtilizadoSetor(int id)throws SQLException {
        String comandoSql = "select * from "+this.nomeTabela+" where idSetor=?";
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setInt(1, id);
            return selecao.executeQuery().next();
    }
}
