
package control;

import java.util.ArrayList;
import model.Usuario;

public class Sistema {
    private AcessoAoBanco acessoAoBanco;
    
    private UsuarioDAO usuarioDao;
    private SetorDAO setorDao;
    private ImpressoraDAO impressoraDao;
    private TonerDAO tonerDao;
    private EntradaDAO entradaDao;
    private SaidaDAO saidaDao;
    
    private Usuario usuarioAtivo;
    
    public Sistema(){
        this.acessoAoBanco = new AcessoAoBanco();
        this.usuarioDao = new UsuarioDAO(this.acessoAoBanco.getConexao());
        this.setorDao = new SetorDAO(this.acessoAoBanco.getConexao());
        this.impressoraDao = new ImpressoraDAO(this.acessoAoBanco.getConexao());
        this.tonerDao = new TonerDAO(this.acessoAoBanco.getConexao());
        this.entradaDao = new EntradaDAO(this.acessoAoBanco.getConexao());
        this.saidaDao = new SaidaDAO(this.acessoAoBanco.getConexao());
    }
    
    public void encerrar(){
        this.acessoAoBanco.encerrar();
        System.exit(0);
    }
    
    public boolean logar(Usuario u){
        usuarioAtivo = this.usuarioDao.logar(u.getLogin(), u.getSenha());
        if(usuarioAtivo!=null){
            return true;
        }else{
            return false;
        }
    }
    
    public String getTipoUsuarioAtivo(){
        return this.usuarioAtivo.getTipo();
    }
    
    public String getNomeUsuarioAtivo(){
        return this.usuarioAtivo.getLogin();
    }
    
    public boolean cadastrarUsuario(Usuario u){
        if(usuarioDao.existeLogin(u.getLogin()))
            return false;
        else{
            this.usuarioDao.cadastrar(u);
            return true;
        }  
    }
    
    public ArrayList<Usuario> getListaDeUsuarios(){
        return this.usuarioDao.getTudo();
    }
    
    public boolean deletarUsuario(Usuario u){
        if(u.getLogin().equals(this.usuarioAtivo.getLogin()))
            return false;
        else{
            this.usuarioDao.deletar(u);
            return true;
        }
    }
    
    public boolean atualizarUsuario(Usuario u){
        this.usuarioDao.atualizar(u);
        return true;
    }
}
