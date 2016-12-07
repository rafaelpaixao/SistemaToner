package control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ArquivoCSV {

    public boolean exportarTabela(JTable tabela, File arquivo) throws IOException {
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

        return true;
    }
}
