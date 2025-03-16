package br.projeto.generators;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import com.opencsv.CSVWriter;

public class GeneratorCSVAdapter extends AGeneratorAdapter {

    public GeneratorCSVAdapter(String caminho) {
        super(caminho);
    }

    @Override
    public void generator(String nome, ArrayList<String[]> conteudo) {
        try {
            FileWriter fw = new FileWriter(new File(caminho + nome + ".csv"));
            CSVWriter cw = new CSVWriter(fw);

            // Escrever o conteúdo
            cw.writeAll(conteudo);

            cw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}