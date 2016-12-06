package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Entrada;

public class EntradaDAO {

    Connection conexao;

    public EntradaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void cadastrar(Entrada entrada) {
        String comandoSql = ""
                + "insert into entradas ("
                + "idToner,"
                + "idUsuario,"
                + "dataEntrada,"
                + "quantidade,"
                + "tipoDeEntrada)"
                + "values (?,?,?,?,?)";
        try {
            PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
            cadastro.setInt(1, entrada.getIdToner());
            cadastro.setInt(2, entrada.getIdUsuario());
            cadastro.setString(3, entrada.getData());
            cadastro.setInt(4, entrada.getQuantidade());
            cadastro.setString(5, entrada.getTipoDeEntrada());
            cadastro.executeUpdate();
            cadastro.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Entrada entrada) {
        String comandoSql = ""
                + "update entradas "
                + "set "
                + "idToner=?,"
                + "idUsuario=?,"
                + "dataEntrada=?,"
                + "quantidade=?,"
                + "tipoDeEntrada=? "
                + "where idEntrada=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setInt(1, entrada.getIdToner());
            atualizacao.setInt(2, entrada.getIdUsuario());
            atualizacao.setString(3, entrada.getData());
            atualizacao.setInt(4, entrada.getQuantidade());
            atualizacao.setString(5, entrada.getTipoDeEntrada());
            atualizacao.setInt(6, entrada.getId());
            atualizacao.executeUpdate();
            atualizacao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Entrada entrada) {
        String comandoSql = ""
                + "delete from entradas "
                + "where idEntrada=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setInt(1, entrada.getId());
            atualizacao.executeUpdate();
            atualizacao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Entrada> getTudo() {

        ArrayList<Entrada> lista = new ArrayList<>();

        String comandoSql = "select * from entradas";

        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            ResultSet resultado = selecao.executeQuery();

            while (resultado.next()) {
                Entrada e = new Entrada();
                e.setId(resultado.getInt("idEntrada"));
                e.setIdToner(resultado.getInt("idToner"));
                e.setIdUsuario(resultado.getInt("idUsuario"));
                e.setData(resultado.getString("dataEntrada"));
                e.setQuantidade(resultado.getInt("quantidade"));
                e.setTipoDeEntrada(resultado.getString("tipoDeEntrada"));
                lista.add(e);
            }

            selecao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public Entrada getEntrada(int idEntrada) {

        Entrada e = null;

        String comandoSql = "select * from entradas where idEntrada=?";

        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setInt(1, idEntrada);
            ResultSet resultado = selecao.executeQuery();

            if (resultado.next()) {
                e = new Entrada();
                e.setId(resultado.getInt("idEntrada"));
                e.setIdToner(resultado.getInt("idToner"));
                e.setIdUsuario(resultado.getInt("idUsuario"));
                e.setData(resultado.getString("dataEntrada"));
                e.setQuantidade(resultado.getInt("quantidade"));
                e.setTipoDeEntrada(resultado.getString("tipoDeEntrada"));
            }

            selecao.close();
        } catch (Exception er) {
            throw new RuntimeException(er);
        }

        return e;
    }

    public ResultSet getResultSetEntradas() {
        ResultSet retorno = null;
        String comandoSql = "select "
                + "entradas.dataEntrada as Data, "
                + "entradas.tipoDeEntrada as Tipo, "
                + "usuarios.login as Usuário, "
                + "impressoras.modeloToner as Toner, "
                + "impressoras.modeloImpressora as Impressora, "
                + "entradas.quantidade "
                + "from entradas "
                + "join usuarios on entradas.idUsuario=usuarios.idUsuario "
                + "join toners on entradas.idToner=toners.idToner "
                + "join impressoras on toners.idImpressora = impressoras.idImpressora "
                + "order by entradas.dataEntrada desc";
        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            retorno = selecao.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(TonerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
