/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
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
}
