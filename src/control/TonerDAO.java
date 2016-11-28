package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Toner;

public class TonerDAO {

    Connection conexao;

    public TonerDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void cadastrar(Toner toner) {
        String comandoSql = ""
                + "insert into toners ("
                + "idImpressora,"
                + "tipoDeToner,"
                + "qtdEstoqueCheio,"
                + "qtdEstoqueVazio,"
                + "qtdForaCheio,"
                + "qtdForaVazio,"
                + "qtdDesabilitadoCheio,"
                + "qtdDesabilitadoVazio)"
                + "values (?,?,0,0,0,0,0,0);";
        try {
            PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
            cadastro.setInt(1, toner.getIdImpressora());
            cadastro.setString(2, toner.getTipo());
            cadastro.executeUpdate();
            cadastro.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Toner toner) {
        String comandoSql = ""
                + "update toners "
                + "set "
                + "idImpressora=?,"
                + "tipoDeToner=?,"
                + "qtdEstoqueCheio=?,"
                + "qtdEstoqueVazio=?,"
                + "qtdForaCheio=?,"
                + "qtdForaVazio=?,"
                + "qtdDesabilitadoCheio=?,"
                + "qtdDesabilitadoVazio=?"
                + "where idToner=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setInt(1, toner.getIdImpressora());
            atualizacao.setString(2, toner.getTipo());
            atualizacao.setInt(3, toner.getQtdEstoqueCheio());
            atualizacao.setInt(4, toner.getQtdEstoqueVazio());
            atualizacao.setInt(5, toner.getQtdForaCheio());
            atualizacao.setInt(6, toner.getQtdForaVazio());
            atualizacao.setInt(7, toner.getQtdDesabilitadoCheio());
            atualizacao.setInt(8, toner.getQtdDesabilitadoVazio());
            atualizacao.setInt(9, toner.getId());
            atualizacao.executeUpdate();
            atualizacao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Toner toner) {
        String comandoSql = ""
                + "delete from toners "
                + "where idToner=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setInt(1, toner.getId());
            atualizacao.executeUpdate();
            atualizacao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Toner> getTudo() {

        ArrayList<Toner> lista = new ArrayList<>();

        String comandoSql = "select * from toners";

        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            ResultSet resultado = selecao.executeQuery();

            while (resultado.next()) {
                Toner t = new Toner();
                t.setId(resultado.getInt("idToner"));
                t.setTipo(resultado.getString("tipoDeToner"));
                t.setQtdEstoqueCheio(resultado.getInt("qtdEstoqueCheio"));
                t.setQtdEstoqueVazio(resultado.getInt("qtdEstoqueVazio"));
                t.setQtdForaCheio(resultado.getInt("qtdForaCheio"));
                t.setQtdForaVazio(resultado.getInt("qtdForaVazio"));
                t.setQtdDesabilitadoCheio(resultado.getInt("qtdDesabilitadoCheio"));
                t.setQtdDesabilitadoVazio(resultado.getInt("qtdDesabilitadoVazio"));
                t.setIdImpressora(resultado.getInt("idImpressora"));
                lista.add(t);
            }

            selecao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public Toner getToner(int idToner) {

        Toner t = null;

        String comandoSql = "select * from toners where idToner=?";

        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setInt(1, idToner);
            ResultSet resultado = selecao.executeQuery();

            if (resultado.next()) {
                t = new Toner();
                t.setId(resultado.getInt("idToner"));
                t.setTipo(resultado.getString("tipoDeToner"));
                t.setQtdEstoqueCheio(resultado.getInt("qtdEstoqueCheio"));
                t.setQtdEstoqueVazio(resultado.getInt("qtdEstoqueVazio"));
                t.setQtdForaCheio(resultado.getInt("qtdForaCheio"));
                t.setQtdForaVazio(resultado.getInt("qtdForaVazio"));
                t.setQtdDesabilitadoCheio(resultado.getInt("qtdDesabilitadoCheio"));
                t.setQtdDesabilitadoVazio(resultado.getInt("qtdDesabilitadoVazio"));
                t.setIdImpressora(resultado.getInt("idImpressora"));
            }

            selecao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return t;
    }

    public ResultSet getResultSetSituacaoToner() {
        ResultSet retorno = null;
        String comandoSql = "select * from toners";
        
        try {
            PreparedStatement selecao;
            selecao = conexao.prepareStatement(comandoSql);
            retorno = selecao.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(TonerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return retorno;
    }
}
