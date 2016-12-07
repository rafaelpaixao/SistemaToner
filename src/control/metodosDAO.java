package control;

import java.sql.SQLException;
import java.util.ArrayList;

public interface metodosDAO <R>{
    
    public void cadastrar(R novo) throws SQLException;
    
    public void atualizar(R atualizado) throws SQLException;
    
    public void excluir(R excluido) throws SQLException;
    
    public ArrayList<R> getTodos() throws SQLException;
    
    public R getPorId(int idProcurado) throws SQLException;
    
}
