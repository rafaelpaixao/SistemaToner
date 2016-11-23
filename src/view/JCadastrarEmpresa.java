/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.EmpresaDAO;
import javax.swing.JOptionPane;
import model.Empresa;

/**
 *
 * @author diegocruzalves
 */
public class JCadastrarEmpresa extends javax.swing.JInternalFrame {

    /**
     * Creates new form JCadastrarEmpresa
     */
    public JCadastrarEmpresa() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idLabel = new javax.swing.JLabel();
        textFieldCadEmpresaId = new javax.swing.JTextField();
        nomeLabel = new javax.swing.JLabel();
        textFieldCadEmpresaNome = new javax.swing.JTextField();
        cnpjLabel = new javax.swing.JLabel();
        textFieldCadEmpresaCnpj = new javax.swing.JTextField();
        btnGravarEmpresa = new javax.swing.JButton();
        btnCancelarCadEmpresa = new javax.swing.JButton();

        setTitle("Cadastro de Empresa");
        setToolTipText("");

        idLabel.setText("ID");

        textFieldCadEmpresaId.setEditable(false);
        textFieldCadEmpresaId.setEnabled(false);

        nomeLabel.setText("Nome");

        cnpjLabel.setText("CNPJ");

        btnGravarEmpresa.setText("Gravar");
        btnGravarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarEmpresaActionPerformed(evt);
            }
        });

        btnCancelarCadEmpresa.setText("Cancelar");
        btnCancelarCadEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCadEmpresaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idLabel)
                            .addComponent(nomeLabel)
                            .addComponent(cnpjLabel))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldCadEmpresaNome, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(textFieldCadEmpresaCnpj, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(textFieldCadEmpresaId)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGravarEmpresa)
                        .addGap(28, 28, 28)
                        .addComponent(btnCancelarCadEmpresa)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLabel)
                    .addComponent(textFieldCadEmpresaId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeLabel)
                    .addComponent(textFieldCadEmpresaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cnpjLabel)
                    .addComponent(textFieldCadEmpresaCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravarEmpresa)
                    .addComponent(btnCancelarCadEmpresa))
                .addGap(17, 17, 17))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGravarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarEmpresaActionPerformed
        Empresa empresa = new Empresa();

        empresa.setNome(textFieldCadEmpresaNome.getText());
        empresa.setCnpj(textFieldCadEmpresaCnpj.getText());
        

        // Validação de dados
        if ((textFieldCadEmpresaNome.getText().isEmpty()) || (textFieldCadEmpresaCnpj.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "O preenchimento de todos os campos é obrigatório!");
        } else { // instanciando a classe*/
            EmpresaDAO dao = new EmpresaDAO();
            dao.cadastrarEmpresa(empresa);
            JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso!");
        }

        textFieldCadEmpresaNome.setText("");
        textFieldCadEmpresaCnpj.setText("");
                                           
    }//GEN-LAST:event_btnGravarEmpresaActionPerformed

    private void btnCancelarCadEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCadEmpresaActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarCadEmpresaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarCadEmpresa;
    private javax.swing.JButton btnGravarEmpresa;
    private javax.swing.JLabel cnpjLabel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField textFieldCadEmpresaCnpj;
    private javax.swing.JTextField textFieldCadEmpresaId;
    private javax.swing.JTextField textFieldCadEmpresaNome;
    // End of variables declaration//GEN-END:variables
}
