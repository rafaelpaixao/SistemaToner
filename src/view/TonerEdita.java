/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.Sistema;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import model.*;

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
        this.jTextModelo.setText(this.sistema.getImpressora(this.toner.getIdImpressora()).getModeloToner() + "(" + this.sistema.getImpressora(this.toner.getIdImpressora()).getModeloToner() + ")");
        if(this.habilitar){
            this.setTitle("Habilitar Toner");
            this.jLabelVerbo.setText("Habilitar:");
            this.jLabelFrase.setText("Desabilitados em estoque:");
            this.jTextCheio1.setText(""+this.toner.getQtdDesabilitadoCheio());
            this.jTextVazio1.setText(""+this.toner.getQtdDesabilitadoVazio());
        }
        else{
            this.setTitle("Desabilitar Toner");
            this.jLabelVerbo.setText("Desabilitar:");
            this.jLabelFrase.setText("Habilitados em estoque:");
            this.jTextCheio1.setText(""+this.toner.getQtdEstoqueCheio());
            this.jTextVazio1.setText(""+this.toner.getQtdEstoqueVazio());
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
        jLabel4 = new javax.swing.JLabel();
        jTextCheio1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextVazio1 = new javax.swing.JTextField();
        jLabelFrase = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextCheio2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextVazio2 = new javax.swing.JTextField();
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

        jLabel4.setText("Cheio:");

        jTextCheio1.setEditable(false);
        jTextCheio1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel6.setText("Vazio:");

        jTextVazio1.setEditable(false);
        jTextVazio1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabelFrase.setText("Desabilitados em estoque:");

        jLabel7.setText("Cheio:");

        jTextCheio2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextCheio2.setText("0");

        jLabel8.setText("Vazio:");

        jTextVazio2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextVazio2.setText("0");

        jLabelVerbo.setText("Habilitar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextModelo))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonConfirmar)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabelFrase, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextCheio1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextVazio1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextVazio2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextCheio2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabelVerbo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelFrase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextCheio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextVazio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelVerbo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextCheio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextVazio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonConfirmar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(300, 100, 296, 187);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        boolean sucesso = false;
        
        Toner x = new Toner();
        
        try{
        int cheio = Integer.parseInt(this.jTextCheio2.getText());
        int vazio = Integer.parseInt(this.jTextVazio2.getText());
        
        if(this.habilitar){
            sucesso = this.sistema.habilitarToner(x, cheio, vazio);
        }else{
            sucesso = this.sistema.desabilitarToner(x, cheio, vazio);
        }
        
        }catch(NumberFormatException e){
            sucesso = false;
        }finally{
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelFrase;
    private javax.swing.JLabel jLabelVerbo;
    private javax.swing.JTextField jTextCheio1;
    private javax.swing.JTextField jTextCheio2;
    private javax.swing.JTextField jTextModelo;
    private javax.swing.JTextField jTextVazio1;
    private javax.swing.JTextField jTextVazio2;
    // End of variables declaration//GEN-END:variables
}