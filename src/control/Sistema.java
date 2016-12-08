package control;

import java.io.File;
import java.io.IOException;
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
    private ModeloImpressoraDAO modeloImpressoraDao;
    private ImpressoraDAO impressoraDao;
    private TonerDAO tonerDao;
    private EntradaDAO entradaDao;
    private SaidaDAO saidaDao;
    private ArquivoCSV arquivoCsv;

    private Usuario usuarioAtivo;

    public Sistema() throws ClassNotFoundException, SQLException {
        this.acessoAoBanco = new AcessoAoBanco();
        this.usuarioDao = new UsuarioDAO(this.acessoAoBanco.getConexao());
        this.setorDao = new SetorDAO(this.acessoAoBanco.getConexao());
        this.modeloImpressoraDao = new ModeloImpressoraDAO(this.acessoAoBanco.getConexao());
        this.impressoraDao = new ImpressoraDAO(this.acessoAoBanco.getConexao());
        this.tonerDao = new TonerDAO(this.acessoAoBanco.getConexao());
        this.entradaDao = new EntradaDAO(this.acessoAoBanco.getConexao());
        this.saidaDao = new SaidaDAO(this.acessoAoBanco.getConexao());
        this.arquivoCsv = new ArquivoCSV();
    }

    public void encerrar() throws SQLException {
        this.acessoAoBanco.encerrar();
    }

    public boolean logar(Usuario u) throws SQLException {
        usuarioAtivo = this.usuarioDao.logar(u.getLogin(), u.getSenha());
        return usuarioAtivo != null;
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

    public boolean cadastrarUsuario(Usuario u) throws SQLException {
        if (usuarioDao.existeLogin(u.getLogin())) {
            return false;
        } else {
            return this.usuarioDao.cadastrar(u);
        }
    }

    public ArrayList<Usuario> getListaDeUsuarios() throws SQLException {
        return this.usuarioDao.getTodos();
    }

    public boolean excluirUsuario(Usuario u) throws SQLException {
        if (u.getLogin().equals(this.usuarioAtivo.getLogin())) {
            return false;
        } else if (!this.entradaDao.isUtilizadoUsuario(u.getId())
                && !this.saidaDao.isUtilizadoUsuario(u.getId())) {
            return this.usuarioDao.excluir(u);
        } else {
            return false;
        }
    }

    public boolean cadastrarSetor(Setor s) throws SQLException {
        return this.setorDao.cadastrar(s);
    }

    public ArrayList<Setor> getListaDeSetores() throws SQLException {
        return this.setorDao.getTodos();
    }

    public Setor getSetor(int id) throws SQLException {
        return this.setorDao.getPorId(id);
    }

    public boolean excluirSetor(Setor s) throws SQLException {
        if (!this.impressoraDao.isUtilizadoSetor(s.getId()) && !this.saidaDao.isUtilizadoSetor(s.getId())) {
            return this.setorDao.excluir(s);
        } else {
            return false;
        }
    }

    public boolean cadastrarModeloImpressora(ModeloImpressora x) throws SQLException {
        return this.modeloImpressoraDao.cadastrar(x);
    }

    public ArrayList<ModeloImpressora> getListaDeModelosImpressoras() throws SQLException {
        return this.modeloImpressoraDao.getTodos();
    }

    public ModeloImpressora getModeloImpressora(int id) throws SQLException {
        return this.modeloImpressoraDao.getPorId(id);
    }

    public boolean excluirModeloImpressora(ModeloImpressora x) throws SQLException {
        if (!this.impressoraDao.isUtilizadoModeloImpressora(x.getId()) && !this.tonerDao.isUtilizadoModeloImpressora(x.getId())) {
            return this.modeloImpressoraDao.excluir(x);
        } else {
            return false;
        }
    }

    public boolean cadastrarImpressora(Impressora x) throws SQLException {
        return this.impressoraDao.cadastrar(x);
    }

    public ArrayList<Impressora> getListaDeImpressoras() throws SQLException {
        return this.impressoraDao.getTodos();
    }

    public Impressora getImpressora(int id) throws SQLException {
        return this.impressoraDao.getPorId(id);
    }

    public boolean excluirImpressora(Impressora x) throws SQLException {
        if(!this.tonerDao.isUtilizadoModeloImpressora(x.getIdModeloImpressora()))
            return this.impressoraDao.excluir(x);
        else return false;
    }

    public boolean cadastrarToner(Toner x) throws SQLException {
        return this.tonerDao.cadastrar(x);
    }

    public ArrayList<Toner> getListaDeToner() throws SQLException {
        return this.tonerDao.getTodos();
    }

    public boolean excluirToner(Toner x) throws SQLException {
        if (!this.entradaDao.isUtilizadoToner(x.getId()) && !this.saidaDao.isUtilizadoToner(x.getId())) {
            return this.tonerDao.excluir(x);
        } else
        return false;
    }

    public boolean atualizarToner(Toner x) throws SQLException {
        this.tonerDao.atualizar(x);
        return true;
    }

    public boolean habilitarToner(Toner x, int qtd) throws SQLException {
        x = this.tonerDao.getPorId(x.getId());
        if (qtd > 0 && qtd <= x.getDesabilitado()) {
            x.setDesabilitado(x.getDesabilitado() - qtd);
            x.setEstoque(x.getEstoque() + qtd);
            this.tonerDao.atualizar(x);
            return true;
        } else {
            return false;
        }
    }

    public boolean desabilitarToner(Toner x, int qtd) throws SQLException {
        x = this.tonerDao.getPorId(x.getId());
        if (qtd > 0 && qtd <= x.getEstoque()) {
            x.setEstoque(x.getEstoque() - qtd);
            x.setDesabilitado(x.getDesabilitado() + qtd);
            this.tonerDao.atualizar(x);
            return true;
        } else {
            return false;
        }
    }

    public boolean cadastrarEntrada(Entrada x) throws SQLException {
        Toner t;
        if (x.getQuantidade() <= 0) {
            return false;
        } else {
            t = this.tonerDao.getPorId(x.getIdToner());

            if (x.getTipoDeEntrada().equals("Recarga")) {
                if (x.getQuantidade() > t.getFora()) {
                    return false;
                } else {
                    t.setFora(t.getFora() - x.getQuantidade());
                }
            }
            t.setEstoque(t.getEstoque() + x.getQuantidade());
            return (this.entradaDao.cadastrar(x) && this.tonerDao.atualizar(t));
        }
    }

    public boolean cadastrarSaida(Saida x) throws SQLException {
        if (x.getQuantidade() <= 0) {
            return false;
        } else {
            Toner t = this.tonerDao.getPorId(x.getIdToner());
            String modeloImpressora = this.modeloImpressoraDao.getPorId(t.getIdModeloImpressora()).getModeloImpressora();

            if (this.impressoraDao.temImpressoraNoSetor(modeloImpressora, x.getIdSetor())) {
                if (x.getQuantidade() > t.getEstoque()) {
                    return false;
                } else {
                    t.setEstoque(t.getEstoque() - x.getQuantidade());
                    t.setFora(t.getFora() + x.getQuantidade());
                }

                return (this.saidaDao.cadastrar(x) && this.tonerDao.atualizar(t));
            } else {
                return false;
            }
        }

    }

    public DefaultTableModel getTableModelSituacaoToner() throws SQLException {
        DefaultTableModel retorno = null;
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

        return retorno;
    }

    public DefaultTableModel getTableModelEntradas() throws SQLException {
        DefaultTableModel retorno = null;

        retorno = buildTableModel(this.entradaDao.getResultSetEntradas());
        Vector<String> colunas = new Vector<String>();
        colunas.add("Data");
        colunas.add("Tipo");
        colunas.add("Usuário");
        colunas.add("Toner");
        colunas.add("Impressora");
        colunas.add("Quantidade");
        retorno = new DefaultTableModel(retorno.getDataVector(), colunas);

        return retorno;
    }

    public DefaultTableModel getTableModelSaidas() throws SQLException {
        DefaultTableModel retorno = null;

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

    public boolean exportarCSV(JTable tabela, File arquivo) throws IOException {
        if (arquivo != null) {
            return this.arquivoCsv.exportarTabela(tabela, arquivo);
        } else {
            return false;
        }
    }
}
