package view;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Alertas {
    public static void sucessoOuErro(Component c, boolean b){
        if(b)
            JOptionPane.showMessageDialog(c, "SUCESSO!");
        else
            JOptionPane.showMessageDialog(c, "ERRO!");
    }
    
    public static void mensagem(Component c, String s){
        JOptionPane.showMessageDialog(c, s);
    }
    
    public static File janelaSalvarComo(Component c){
        final JFileChooser janela = new JFileChooser();
        int returnVal = janela.showSaveDialog(c);
        if (returnVal == JFileChooser.APPROVE_OPTION)
            return janela.getSelectedFile();
        else
            return null;
    }
    
    public static void erroBanco(Component c, String msg){
        JOptionPane.showMessageDialog(c, "ERRO! Falha de comunicação com o banco de dados.\n"+msg);
    }
    
    public static void erroFormatoEntrada(Component c){
        JOptionPane.showMessageDialog(c, "ERRO! Formato de entrada inválido.");
    }
    
    public static void erroSalvarArquivo(Component c){
        JOptionPane.showMessageDialog(c, "ERRO! Não foi possível salvar o arquivo.");
    }
}
