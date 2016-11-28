
package control;

import java.util.ArrayList;
import model.*;

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
    
    public int getIdUsuarioAtivo(){
        return this.usuarioAtivo.getId();
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
    
    public boolean cadastrarSetor(Setor s){
        this.setorDao.cadastrar(s);
        return true;
    }
    
    public ArrayList<Setor> getListaDeSetores(){
        return this.setorDao.getTudo();
    }
    
    public Setor getSetor(int id){
        return this.setorDao.getSetorPorId(id);
    }
    
    public boolean deletarSetor(Setor s){
        this.setorDao.deletar(s);
        return true;
    }
    
    public boolean atualizarSetor(Setor s){
        this.setorDao.atualizar(s);
        return true;
    }
    
    public boolean cadastrarImpressora(Impressora x){
        this.impressoraDao.cadastrar(x);
        return true;
    }
    
    public ArrayList<Impressora> getListaDeImpressoras(){
        return this.impressoraDao.getTudo();
    }
    
    public Impressora getImpressora(int id){
        return this.impressoraDao.getImpressoraPorId(id);
    }
    
    public boolean deletarImpressora(Impressora x){
        this.impressoraDao.deletar(x);
        return true;
    }
    
    public boolean atualizarImpressora(Impressora x){
        this.impressoraDao.atualizar(x);
        return true;
    }
    
    public boolean cadastrarToner(Toner x){
        this.tonerDao.cadastrar(x);
        return true;
    }
    
    public ArrayList<Toner> getListaDeToner(){
        return this.tonerDao.getTudo();
    }
    
    public boolean deletarToner(Toner x){
        if(temMovimentacaoToner(x))
            return false;
        this.tonerDao.deletar(x);
        return true;
    }
    
    public boolean atualizarToner(Toner x){
        this.tonerDao.atualizar(x);
        return true;
    }
    
    private boolean temMovimentacaoToner(Toner x){
        //FAZER
        return true;
    }
    
    public boolean cadastrarEntrada(Entrada x){
        this.entradaDao.cadastrar(x);
        return true;
    }
    
    public boolean cadastrarSaida(Saida x){
        this.saidaDao.cadastrar(x);
        return true;
    }
}
