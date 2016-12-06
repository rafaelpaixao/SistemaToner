package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                + "estoque,"
                + "fora,"
                + "desabilitado)"
                + "values (?,?,0,0,0);";
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
                + "estoque=?,"
                + "fora=?,"
                + "desabilitado=? "
                + "where idToner=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setInt(1, toner.getIdImpressora());
            atualizacao.setString(2, toner.getTipo());
            atualizacao.setInt(3, toner.getEstoque());
            atualizacao.setInt(4, toner.getFora());
            atualizacao.setInt(5, toner.getDesabilitado());
            atualizacao.setInt(6, toner.getId());
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
                t.setEstoque(resultado.getInt("estoque"));
                t.setFora(resultado.getInt("fora"));
                t.setDesabilitado(resultado.getInt("desabilitado"));
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
                t.setEstoque(resultado.getInt("estoque"));
                t.setFora(resultado.getInt("fora"));
                t.setDesabilitado(resultado.getInt("desabilitado"));
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
        String comandoSql = "select "
                + "impressoras.modeloToner as Toner, "
                + "impressoras.modeloImpressora as Impressora, "
                + "toners.tipoDeToner as Tipo, "
                + "impressoras.precoToner as Preço, "
                + "toners.estoque, "
                + "toners.fora, "
                + "toners.desabilitado "
                + "from toners join impressoras on toners.idImpressora=impressoras.idImpressora "
                + "order by impressoras.modeloToner";

        try {
            PreparedStatement selecao;
            selecao = conexao.prepareStatement(comandoSql);
            retorno = selecao.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(TonerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }
    
    public boolean tonerTemMovimentacao(Toner x){
        String comandoSql1 = "select * from entradas where idToner=?";
        String comandoSql2 = "select * from saidas where idToner=?";

        try {
            PreparedStatement selecao1 = conexao.prepareStatement(comandoSql1);
            selecao1.setInt(1, x.getId());
            ResultSet resultado1 = selecao1.executeQuery();
            
            PreparedStatement selecao2 = conexao.prepareStatement(comandoSql2);
            selecao2.setInt(1, x.getId());
            ResultSet resultado2 = selecao2.executeQuery();

            return resultado1.next() || resultado2.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
