package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
                + "qtdCheio,"
                + "qtdVazio,"
                + "idSetor)"
                + "values (?,?,?,?,?,?)";
        try {
            PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
            cadastro.setInt(1, saida.getIdToner());
            cadastro.setInt(2, saida.getIdUsuario());
            cadastro.setString(3, saida.getData());
            cadastro.setInt(4, saida.getQtdCheio());
            cadastro.setInt(5, saida.getQtdVazio());
            cadastro.setInt(6, saida.getIdSetor());
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
                + "qtdCheio=?,"
                + "qtdVazio=?, "
                + "idSetor=? "
                + "where idSaida=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setInt(1, saida.getIdToner());
            atualizacao.setInt(2, saida.getIdUsuario());
            atualizacao.setString(3, saida.getData());
            atualizacao.setInt(4, saida.getQtdCheio());
            atualizacao.setInt(5, saida.getQtdVazio());
            atualizacao.setInt(6, saida.getIdSetor());
            atualizacao.setInt(7, saida.getId());
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
                e.setQtdCheio(resultado.getInt("qtdCheio"));
                e.setQtdVazio(resultado.getInt("qtdVazio"));
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
                e.setQtdCheio(resultado.getInt("qtdCheio"));
                e.setQtdVazio(resultado.getInt("qtdVazio"));
                e.setIdSetor(resultado.getInt("idSetor"));
            }
            
            selecao.close();
        } catch (Exception er) {
            throw new RuntimeException(er);
        }

        return e;
    }

}
