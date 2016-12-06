/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Rafael
 */
public class ArquivoCSV {
     
    public boolean exportarTabela(JTable tabela, File arquivo) {
        try {
            TableModel model = tabela.getModel();
            File arquivoNovo = new File(arquivo.toString() + ".csv");
            FileWriter arquivoAberto = new FileWriter(arquivoNovo);

            for (int i = 0; i < model.getColumnCount(); i++) {
                arquivoAberto.write(model.getColumnName(i) + ";");
            }

            arquivoAberto.write("\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    arquivoAberto.write(model.getValueAt(i, j).toString() + ";");
                }
                arquivoAberto.write("\n");
            }

            arquivoAberto.close();

        } catch (IOException e) {
            System.out.println(e);
        }
        
        return true;
    }
}
