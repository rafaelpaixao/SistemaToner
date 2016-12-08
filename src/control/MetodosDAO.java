package control;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MetodosDAO <R>{
    
    public boolean cadastrar(R novo) throws SQLException;
       
    public boolean excluir(R excluido) throws SQLException;
    
    public ArrayList<R> getTodos() throws SQLException;
    
    public R getPorId(int idProcurado) throws SQLException;
    
}
