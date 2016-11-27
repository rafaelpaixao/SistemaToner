
package control;

import model.Usuario;

public class Sistema {
    AcessoAoBanco acessoAoBanco;
    
    UsuarioDAO usuarioDao;
    SetorDAO setorDao;
    ImpressoraDAO impressoraDao;
    TonerDAO tonerDao;
    EntradaDAO entradaDao;
    SaidaDAO saidaDao;
    
    Usuario usuarioAtivo;
    
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
    
    public void logar(String login, String senha){
        usuarioAtivo = this.usuarioDao.logar(login, senha);
        if(usuarioAtivo!=null){
            System.out.println("Usuario logado!");
        }else{
            System.out.println("Login inválido!");
        }
    }
}
