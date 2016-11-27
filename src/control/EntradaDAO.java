package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
                + "qtdCheio,"
                + "qtdVazio,"
                + "tipoDeEntrada)"
                + "values (?,?,?,?,?,?)";
        try {
            PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
            cadastro.setInt(1, entrada.getIdToner());
            cadastro.setInt(2, entrada.getIdUsuario());
            cadastro.setDate(3, entrada.getData());
            cadastro.setInt(4, entrada.getQtdCheio());
            cadastro.setInt(5, entrada.getQtdVazio());
            cadastro.setString(6, entrada.getTipoDeEntrada());
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
                + "qtdCheio=?,"
                + "qtdVazio=?, "
                + "tipoDeEntrada=? "
                + "where idEntrada=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setInt(1, entrada.getIdToner());
            atualizacao.setInt(2, entrada.getIdUsuario());
            atualizacao.setDate(3, entrada.getData());
            atualizacao.setInt(4, entrada.getQtdCheio());
            atualizacao.setInt(5, entrada.getQtdVazio());
            atualizacao.setString(6, entrada.getTipoDeEntrada());
            atualizacao.setInt(7, entrada.getId());
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

    public ArrayList<Entrada> getTodosEntradas(Entrada entrada) {

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
                e.setData(resultado.getDate("dataEntrada"));
                e.setQtdCheio(resultado.getInt("qtdCheio"));
                e.setQtdVazio(resultado.getInt("qtdVazio"));
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
                e.setData(resultado.getDate("dataEntrada"));
                e.setQtdCheio(resultado.getInt("qtdCheio"));
                e.setQtdVazio(resultado.getInt("qtdVazio"));
                e.setTipoDeEntrada(resultado.getString("tipoDeEntrada"));
            }
            
            selecao.close();
        } catch (Exception er) {
            throw new RuntimeException(er);
        }

        return e;
    }

}
