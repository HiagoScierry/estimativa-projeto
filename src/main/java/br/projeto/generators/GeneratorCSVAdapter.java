package br.projeto.generators;

import br.projeto.generators.AGeneratorAdapter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVWriter;
import br.projeto.model.*;

public class GeneratorCSVAdapter extends AGeneratorAdapter {

    public GeneratorCSVAdapter(String caminho) {
        super(caminho);
    }

    @Override
    public void generator(String nome, Projeto projeto) {
        try {
            
            File arquivoCSV = new File(caminho + nome + ".csv");
            FileWriter fw = new FileWriter(arquivoCSV);
            CSVWriter cw = new CSVWriter(fw);

            String[] cabecalho = {"Campo", "Valor"};
            cw.writeNext(cabecalho);
            
            cw.writeNext(new String[]{"Nome", projeto.getNome()});
            cw.writeNext(new String[]{"Data de Criação", projeto.getDataCriacao()});
            cw.writeNext(new String[]{"Percentual de Impostos", String.valueOf(projeto.getPercentualImpostos())});
            cw.writeNext(new String[]{"Percentual de Lucro", String.valueOf(projeto.getPercentualLucro())});

            cw.writeNext(new String[]{"Funcionalidades Web/Backend", "Nome", "Horas Estimadas"});
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesWebBackend()) {
                cw.writeNext(new String[]{"", funcionalidade.getNome(), String.valueOf(funcionalidade.getHorasEstimadas())});
            }

            cw.writeNext(new String[]{"Funcionalidades iOS", "Nome", "Horas Estimadas"});
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesIOS()) {
                cw.writeNext(new String[]{"", funcionalidade.getNome(), String.valueOf(funcionalidade.getHorasEstimadas())});
            }

            cw.writeNext(new String[]{"Funcionalidades Android", "Nome", "Horas Estimadas"});
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesAndroid()) {
                cw.writeNext(new String[]{"", funcionalidade.getNome(), String.valueOf(funcionalidade.getHorasEstimadas())});
            }

            cw.writeNext(new String[]{"Custos Adicionais", "Descrição", "Valor"});
            for (CustoAdicional custo : projeto.getCustosAdicionais()) {
                cw.writeNext(new String[]{"", custo.getDescricao(), String.valueOf(custo.getValor())});
            }

            cw.close();
            fw.close();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}