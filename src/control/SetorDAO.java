
package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Setor;

public class SetorDAO {
    
    Connection conexao;
    
    public SetorDAO(Connection conexao){
        this.conexao = conexao;
    }
    
    public void cadastrar(Setor setor){
        String comandoSql = ""
                + "insert into setores ("
                + "nomeSetor,"
                + "nomeEmpresa)"
                + "values (?,?);";
        try {
            PreparedStatement cadastro = conexao.prepareStatement(comandoSql);
            cadastro.setString(1, setor.getNome());
            cadastro.setString(2, setor.getEmpresa());
            cadastro.executeUpdate();
            cadastro.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void atualizar(Setor setor){
        String comandoSql = ""
                + "update setores "
                + "set "
                + "nomeSetor=?, "
                + "nomeEmpresa=? "
                + "where idSetor=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setString(1, setor.getNome());
            atualizacao.setString(2, setor.getEmpresa());
            atualizacao.setInt(3, setor.getId());
            atualizacao.executeUpdate();
            atualizacao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void deletar(Setor setor){
        String comandoSql = ""
                + "delete from setores "
                + "where idSetor=?";
        try {
            PreparedStatement atualizacao = conexao.prepareStatement(comandoSql);
            atualizacao.setInt(1, setor.getId());
            atualizacao.executeUpdate();
            atualizacao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public ArrayList<Setor> getTodosSetores(Setor setor){
        
        ArrayList<Setor> lista = new ArrayList<>();
        
        String comandoSql = "select * from setores";

        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            ResultSet resultado = selecao.executeQuery();
                    
            while(resultado.next()){
                Setor s = new Setor();
                s.setId(resultado.getInt("idSetor"));
                s.setNome(resultado.getString("nomeSetor"));
                s.setEmpresa(resultado.getString("nomeEmpresa"));
                lista.add(s);
            }
            
            selecao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return lista;
    }
    
    public Setor getSetorPorId(int idProcurado){
        
        Setor s = null;
        
        String comandoSql = "select * from setores where idSetor=?";

        try {
            PreparedStatement selecao = conexao.prepareStatement(comandoSql);
            selecao.setInt(1, idProcurado);
            ResultSet resultado = selecao.executeQuery();
            
            if(resultado.next()){
                s = new Setor();
                s.setId(resultado.getInt("idSetor"));
                s.setNome(resultado.getString("nomeSetor"));
                s.setEmpresa(resultado.getString("nomeEmpresa"));
            }
            
            selecao.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return s;
    }
}
