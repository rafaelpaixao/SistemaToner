
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Impressora;

public class ImpressoraDAO {
    Connection conexao;
    
    public ImpressoraDAO(Connection conexao){
        this.conexao = conexao;
    }
    
    public void cadastrar(Impressora impressora){
        String comandoSql = ""
                + "insert into impressoras ("
                + "idSetor,"
                + "modeloImpressora,"
                + "modeloToner,"
                + "precoToner)"
                + "values (?,?,?,?);";
        try {
            PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
            cadastro.setInt(1, impressora.getIdSetor());
            cadastro.setString(2, impressora.getModelo());
            cadastro.setString(3, impressora.getModeloToner());
            cadastro.setDouble(4, impressora.getPrecoToner());
            cadastro.executeUpdate();
            cadastro.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void atualizar(Impressora impressora){
        String comandoSql = ""
                + "update impressoras "
                + "set "
                + "idSetor=?, "
                + "modeloImpressora=?, "
                + "modeloToner=?, "
                + "precoToner=?, "
                + "where idImpressora=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setInt(1, impressora.getIdSetor());
            atualizacao.setString(2, impressora.getModelo());
            atualizacao.setString(3, impressora.getModeloToner());
            atualizacao.setDouble(4, impressora.getPrecoToner());
            atualizacao.setInt(5, impressora.getId());
            atualizacao.executeUpdate();
            atualizacao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void deletar(Impressora impressora){
        String comandoSql = ""
                + "delete from impressoras "
                + "where idImpressora=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setInt(1, impressora.getId());
            atualizacao.executeUpdate();
            atualizacao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public ArrayList<Impressora> getTodosImpressoras(Impressora impressora){
        
        ArrayList<Impressora> lista = new ArrayList<>();
        
        String comandoSql = ""
                + "select * from impressoras "
                + "inner join setores "
                + "on impressoras.idSetor = setores.idSetor";

        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            ResultSet resultado = selecao.executeQuery();
            
            while(resultado.next()){
                Impressora i = new Impressora();
                i.setId(resultado.getInt("idImpressora"));
                i.setModelo(resultado.getString("modeloImpressora"));
                i.setModeloToner(resultado.getString("modeloToner"));
                i.setPrecoToner(resultado.getDouble("precoToner"));
                i.setIdSetor(resultado.getInt("idSetor"));
                lista.add(i);
            }
            
            selecao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return lista;
    }
    
    public Impressora getImpressoraPorId(int idProcurado){
        
        Impressora i = null;
        
        String comandoSql = ""
                + "select * from impressoras "
                + "inner join setores "
                + "on impressoras.idSetor = setores.idSetor "
                + "where idImpressora=?";

        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setInt(1, idProcurado);
            ResultSet resultado = selecao.executeQuery();
                       
            if(resultado.next()){
                i = new Impressora();
                i.setId(resultado.getInt("idImpressora"));
                i.setModelo(resultado.getString("modeloImpressora"));
                i.setModeloToner(resultado.getString("modeloToner"));
                i.setPrecoToner(resultado.getDouble("precoToner"));
                i.setIdSetor(resultado.getInt("idSetor"));
            }
            
            selecao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return i;
    }
}
