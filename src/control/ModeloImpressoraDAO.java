
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ModeloImpressora;

public class ModeloImpressoraDAO extends atributosDAO implements metodosDAO<ModeloImpressora>{
    
    public ModeloImpressoraDAO(Connection conexao){
        this.conexao = conexao;
        this.nomeTabela = "modelosImpressoras";
    }

    @Override
    public boolean cadastrar(ModeloImpressora novo) throws SQLException {
        String comandoSql = ""
                + "insert into "
                + this.nomeTabela
                + "("
                + "modeloImpressora,"
                + "modeloToner,"
                + "precoToner"
                + ")"
                + "values (?,?,?);";
        PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
        cadastro.setString(1, novo.getModeloImpressora());
        cadastro.setString(2, novo.getModeloToner());
        cadastro.setDouble(3, novo.getPrecoToner());
        return cadastro.executeUpdate() !=0;
    }

    @Override
    public boolean excluir(ModeloImpressora excluido) throws SQLException {
        String comandoSql = ""
                + "delete from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
        atualizacao.setInt(1, excluido.getId());
        return atualizacao.executeUpdate() !=0;
    }

    @Override
    public ArrayList<ModeloImpressora> getTodos() throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela;
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        ResultSet resultado = selecao.executeQuery();

        ArrayList<ModeloImpressora> lista = new ArrayList<>();
        while (resultado.next()) {
            ModeloImpressora r = new ModeloImpressora();
            r.setId(resultado.getInt("id"));
            r.setModeloImpressora(resultado.getString("modeloImpressora"));
            r.setModeloToner(resultado.getString("modeloToner"));
            r.setPrecoToner(resultado.getDouble("precoToner"));
            lista.add(r);
        }

        selecao.close();
        return lista;
    }

    @Override
    public ModeloImpressora getPorId(int idProcurado) throws SQLException {
        String comandoSql = ""
                + "select * from "
                + this.nomeTabela
                + " where id=?";
        PreparedStatement selecao = conexao.prepareStatement(comandoSql);
        selecao.setInt(1, idProcurado);
        ResultSet resultado = selecao.executeQuery();

        ModeloImpressora r = null;
        if (resultado.next()) {
            r = new ModeloImpressora();
            r.setId(resultado.getInt("id"));
            r.setModeloImpressora(resultado.getString("modeloImpressora"));
            r.setModeloToner(resultado.getString("modeloToner"));
            r.setPrecoToner(resultado.getDouble("precoToner"));
        }

        selecao.close();
        return r;
    }
}
