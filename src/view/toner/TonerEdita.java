/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.toner;

import control.Sistema;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import model.*;
import view.Alertas;

/**
 *
 * @author Rafael
 */
public class TonerEdita extends javax.swing.JInternalFrame {

    Sistema sistema;
    Toner toner;
    boolean habilitar;

    public TonerEdita(Sistema sistema, Toner t, boolean h) {
        this.sistema = sistema;
        this.toner = t;
        this.habilitar = h;
        initComponents();
        try {
            this.jTextModelo.setText(this.sistema.getModeloImpressora(this.toner.getIdModeloImpressora()).getModeloToner());
        } catch (SQLException ex) {
            Logger.getLogger(TonerEdita.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (this.habilitar) {
            this.setTitle("Habilitar Toner");
            this.jLabelVerbo.setText("Habilitar:");
            this.jLabelFrase.setText("Desabilitados em estoque:");
            this.jTextAtual.setText("" + this.toner.getDesabilitado());
        } else {
            this.setTitle("Desabilitar Toner");
            this.jLabelVerbo.setText("Desabilitar:");
            this.jLabelFrase.setText("Habilitados em estoque:");
            this.jTextAtual.setText("" + this.toner.getEstoque());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButtonConfirmar = new javax.swing.JButton();
        jTextModelo = new javax.swing.JTextField();
        jTextAtual = new javax.swing.JTextField();
        jLabelFrase = new javax.swing.JLabel();
        jTextNovo = new javax.swing.JTextField();
        jLabelVerbo = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Habilitar Toner");
        setToolTipText("");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setText("Modelo do toner:");

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        jTextModelo.setEditable(false);

        jTextAtual.setEditable(false);
        jTextAtual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabelFrase.setText("Desabilitados:");

        jTextNovo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextNovo.setText("0");

        jLabelVerbo.setText("Habilitar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextModelo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelFrase, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 27, Short.MAX_VALUE)
                        .addComponent(jLabelVerbo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonConfirmar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFrase)
                    .addComponent(jTextAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelVerbo)
                    .addComponent(jTextNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonConfirmar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(300, 100, 338, 137);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        boolean sucesso = false;

        Toner x = new Toner();

        try {
            int qtd = Integer.parseInt(this.jTextNovo.getText());
            try {
                if (this.habilitar) {

                    sucesso = this.sistema.habilitarToner(x, qtd);

                } else {
                    sucesso = this.sistema.desabilitarToner(x, qtd);
                }
            } catch (SQLException ex) {
                Alertas.erroBanco(this,ex.toString());
            }

        } catch (NumberFormatException e) {
            Alertas.erroFormatoEntrada(this);
        } finally {
            Alertas.sucessoOuErro(this, sucesso);
            if (sucesso) {
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown

    }//GEN-LAST:event_formComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelFrase;
    private javax.swing.JLabel jLabelVerbo;
    private javax.swing.JTextField jTextAtual;
    private javax.swing.JTextField jTextModelo;
    private javax.swing.JTextField jTextNovo;
    // End of variables declaration//GEN-END:variables
}
