package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Saida;

public class SaidaDAO {

    Connection conexao;

    public SaidaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void cadastrar(Saida saida) {
        String comandoSql = ""
                + "insert into saidas ("
                + "idToner,"
                + "idUsuario,"
                + "dataSaida,"
                + "quantidade,"
                + "idSetor)"
                + "values (?,?,?,?,?)";
        try {
            PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
            cadastro.setInt(1, saida.getIdToner());
            cadastro.setInt(2, saida.getIdUsuario());
            cadastro.setString(3, saida.getData());
            cadastro.setInt(4, saida.getQuantidade());
            cadastro.setInt(5, saida.getIdSetor());
            cadastro.executeUpdate();
            cadastro.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Saida saida) {
        String comandoSql = ""
                + "update saidas "
                + "set "
                + "idToner=?,"
                + "idUsuario=?,"
                + "dataSaida=?,"
                + "quantidade=?, "
                + "idSetor=? "
                + "where idSaida=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setInt(1, saida.getIdToner());
            atualizacao.setInt(2, saida.getIdUsuario());
            atualizacao.setString(3, saida.getData());
            atualizacao.setInt(4, saida.getQuantidade());
            atualizacao.setInt(5, saida.getIdSetor());
            atualizacao.setInt(6, saida.getId());
            atualizacao.executeUpdate();
            atualizacao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Saida saida) {
        String comandoSql = ""
                + "delete from saidas "
                + "where idSaida=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setInt(1, saida.getId());
            atualizacao.executeUpdate();
            atualizacao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Saida> getTudo() {

        ArrayList<Saida> lista = new ArrayList<>();

        String comandoSql = "select * from saidas";

        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            ResultSet resultado = selecao.executeQuery();

            while (resultado.next()) {
                Saida e = new Saida();
                e.setId(resultado.getInt("idSaida"));
                e.setIdToner(resultado.getInt("idToner"));
                e.setIdUsuario(resultado.getInt("idUsuario"));
                e.setData(resultado.getString("dataSaida"));
                e.setQuantidade(resultado.getInt("quantidade"));
                e.setIdSetor(resultado.getInt("idSetor"));
                lista.add(e);
            }

            selecao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public Saida getSaida(int idSaida) {

        Saida e = null;

        String comandoSql = "select * from saidas where idSaida=?";

        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setInt(1, idSaida);
            ResultSet resultado = selecao.executeQuery();

            if (resultado.next()) {
                e = new Saida();
                e.setId(resultado.getInt("idSaida"));
                e.setIdToner(resultado.getInt("idToner"));
                e.setIdUsuario(resultado.getInt("idUsuario"));
                e.setData(resultado.getString("dataSaida"));
                e.setQuantidade(resultado.getInt("quantidade"));
                e.setIdSetor(resultado.getInt("idSetor"));
            }

            selecao.close();
        } catch (Exception er) {
            throw new RuntimeException(er);
        }

        return e;
    }

    public ResultSet getResultSetSaidas() {
        ResultSet retorno = null;
        String comandoSql = "select "
                + "saidas.dataSaida as Data, "
                + "setores.nomeSetor as Setor, "
                + "setores.nomeEmpresa as Empresa, "
                + "usuarios.login as Usuário, "
                + "impressoras.modeloToner as Toner, "
                + "impressoras.modeloImpressora as Impressora, "
                + "saidas.quantidade "
                + "from saidas "
                + "join usuarios on saidas.idUsuario=usuarios.idUsuario "
                + "join toners on saidas.idToner=toners.idToner "
                + "join impressoras on toners.idImpressora = impressoras.idImpressora "
                + "join setores on saidas.idSetor = setores.idSetor "
                + "order by saidas.dataSaida desc";
        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            retorno = selecao.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(TonerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
