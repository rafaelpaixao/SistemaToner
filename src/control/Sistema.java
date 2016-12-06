package control;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.*;

public class Sistema {

    private AcessoAoBanco acessoAoBanco;
    private UsuarioDAO usuarioDao;
    private SetorDAO setorDao;
    private ImpressoraDAO impressoraDao;
    private TonerDAO tonerDao;
    private EntradaDAO entradaDao;
    private SaidaDAO saidaDao;
    private ArquivoCSV arquivoCsv;

    private Usuario usuarioAtivo;

    public Sistema() {
        this.acessoAoBanco = new AcessoAoBanco();
        this.usuarioDao = new UsuarioDAO(this.acessoAoBanco.getConexao());
        this.setorDao = new SetorDAO(this.acessoAoBanco.getConexao());
        this.impressoraDao = new ImpressoraDAO(this.acessoAoBanco.getConexao());
        this.tonerDao = new TonerDAO(this.acessoAoBanco.getConexao());
        this.entradaDao = new EntradaDAO(this.acessoAoBanco.getConexao());
        this.saidaDao = new SaidaDAO(this.acessoAoBanco.getConexao());
        this.arquivoCsv = new ArquivoCSV();
    }

    public void encerrar() {
        this.acessoAoBanco.encerrar();
        System.exit(0);
    }

    public boolean logar(Usuario u) {
        usuarioAtivo = this.usuarioDao.logar(u.getLogin(), u.getSenha());
        if (usuarioAtivo != null) {
            return true;
        } else {
            return false;
        }
    }

    public String getTipoUsuarioAtivo() {
        return this.usuarioAtivo.getTipo();
    }

    public String getNomeUsuarioAtivo() {
        return this.usuarioAtivo.getLogin();
    }

    public int getIdUsuarioAtivo() {
        return this.usuarioAtivo.getId();
    }

    public boolean cadastrarUsuario(Usuario u) {
        if (usuarioDao.existeLogin(u.getLogin())) {
            return false;
        } else {
            this.usuarioDao.cadastrar(u);
            return true;
        }
    }

    public ArrayList<Usuario> getListaDeUsuarios() {
        return this.usuarioDao.getTudo();
    }

    public boolean deletarUsuario(Usuario u) {
        if (u.getLogin().equals(this.usuarioAtivo.getLogin())) {
            return false;
        } else {
            this.usuarioDao.deletar(u);
            return true;
        }
    }

    public boolean atualizarUsuario(Usuario u) {
        this.usuarioDao.atualizar(u);
        return true;
    }

    public boolean cadastrarSetor(Setor s) {
        this.setorDao.cadastrar(s);
        return true;
    }

    public ArrayList<Setor> getListaDeSetores() {
        return this.setorDao.getTudo();
    }

    public Setor getSetor(int id) {
        return this.setorDao.getSetorPorId(id);
    }

    public boolean deletarSetor(Setor s) {
        this.setorDao.deletar(s);
        return true;
    }

    public boolean atualizarSetor(Setor s) {
        this.setorDao.atualizar(s);
        return true;
    }

    public boolean cadastrarImpressora(Impressora x) {
        this.impressoraDao.cadastrar(x);
        return true;
    }

    public ArrayList<Impressora> getListaDeImpressoras() {
        return this.impressoraDao.getTudo();
    }

    public Impressora getImpressora(int id) {
        return this.impressoraDao.getImpressoraPorId(id);
    }

    public boolean deletarImpressora(Impressora x) {
        this.impressoraDao.deletar(x);
        return true;
    }

    public boolean atualizarImpressora(Impressora x) {
        this.impressoraDao.atualizar(x);
        return true;
    }

    public boolean cadastrarToner(Toner x) {
        this.tonerDao.cadastrar(x);
        return true;
    }

    public ArrayList<Toner> getListaDeToner() {
        return this.tonerDao.getTudo();
    }

    public boolean deletarToner(Toner x) {
        if (temMovimentacaoToner(x)) {
            return false;
        }
        this.tonerDao.deletar(x);
        return true;
    }

    public boolean atualizarToner(Toner x) {
        this.tonerDao.atualizar(x);
        return true;
    }

    public boolean habilitarToner(Toner x, int qtd) {
        x = this.tonerDao.getToner(x.getId());
        if (qtd > 0 && qtd <= x.getDesabilitado()) {
            x.setDesabilitado(x.getDesabilitado() - qtd);
            x.setEstoque(x.getEstoque() + qtd);
            this.tonerDao.atualizar(x);
            return true;
        } else {
            return false;
        }
    }

    public boolean desabilitarToner(Toner x, int qtd) {
        x = this.tonerDao.getToner(x.getId());
        if (qtd > 0 && qtd <= x.getEstoque()) {
            x.setEstoque(x.getEstoque() - qtd);
            x.setDesabilitado(x.getDesabilitado() + qtd);
            this.tonerDao.atualizar(x);
            return true;
        } else {
            return false;
        }
    }

    private boolean temMovimentacaoToner(Toner x) {
        return this.tonerDao.tonerTemMovimentacao(x);
    }

    public boolean cadastrarEntrada(Entrada x) {
        Toner t;
        if (x.getQuantidade() <= 0) {
            return false;
        } else {
            t = this.tonerDao.getToner(x.getIdToner());
            
            if (x.getTipoDeEntrada().equals("Recarga")) {
                if (x.getQuantidade() > t.getFora()) {
                    return false;
                } else {
                    t.setFora(t.getFora() - x.getQuantidade());
                }
            }
            
            t.setEstoque(t.getEstoque() + x.getQuantidade());
            
            this.entradaDao.cadastrar(x);
            this.tonerDao.atualizar(t);
            return true;
        }

    }

    public boolean cadastrarSaida(Saida x) {
        if (x.getQuantidade() <= 0) {
            return false;
        } else {
            Toner t = this.tonerDao.getToner(x.getIdToner());
            String modeloImpressora = this.impressoraDao.getImpressoraPorId(t.getIdImpressora()).getModeloImpressora();
            
            if(this.impressoraDao.temImpressoraNoSetor(modeloImpressora, x.getIdSetor())){
                if (x.getQuantidade() > t.getEstoque()) {
                    return false;
                } else {
                    t.setEstoque(t.getEstoque() - x.getQuantidade());
                    t.setFora(t.getFora() + x.getQuantidade());
                }

                this.saidaDao.cadastrar(x);
                this.tonerDao.atualizar(t);
                return true;
            }
            else return false;
        }

    }

    public DefaultTableModel getTableModelSituacaoToner() {
        DefaultTableModel retorno = null;
        try {
            retorno = buildTableModel(this.tonerDao.getResultSetSituacaoToner());
            Vector<String> colunas = new Vector<String>();
            colunas.add("Toner");
            colunas.add("Impressora");
            colunas.add("Tipo");
            colunas.add("Preço (R$)");
            colunas.add("Estoque");
            colunas.add("Fora");
            colunas.add("Desabilitado");
            retorno = new DefaultTableModel(retorno.getDataVector(), colunas);

        } catch (SQLException ex) {
            Logger.getLogger(Sistema.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public DefaultTableModel getTableModelEntradas() {
        DefaultTableModel retorno = null;

        try {
            retorno = buildTableModel(this.entradaDao.getResultSetEntradas());
            Vector<String> colunas = new Vector<String>();
            colunas.add("Data");
            colunas.add("Tipo");
            colunas.add("Usuário");
            colunas.add("Toner");
            colunas.add("Impressora");
            colunas.add("Quantidade");
            retorno = new DefaultTableModel(retorno.getDataVector(), colunas);

        } catch (SQLException ex) {
            Logger.getLogger(Sistema.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    public DefaultTableModel getTableModelSaidas() {
        DefaultTableModel retorno = null;

        try {
            retorno = buildTableModel(this.saidaDao.getResultSetSaidas());
            Vector<String> colunas = new Vector<String>();
            colunas.add("Data");
            colunas.add("Setor");
            colunas.add("Empresa");
            colunas.add("Usuário");
            colunas.add("Toner");
            colunas.add("Impressora");
            colunas.add("Quantidade");
            retorno = new DefaultTableModel(retorno.getDataVector(), colunas);

        } catch (SQLException ex) {
            Logger.getLogger(Sistema.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    private DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }
    
    public boolean exportarCSV(JTable tabela, File arquivo){
        if(arquivo!=null)
            return this.arquivoCsv.exportarTabela(tabela, arquivo);
        else
            return false;
    }
}
