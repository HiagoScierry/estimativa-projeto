package br.projeto.generators;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import br.projeto.model.*;
import br.projeto.singleton.ProjetoSingleton;

public class GeneratorPDFAdapter extends AGeneratorAdapter {

    public GeneratorPDFAdapter(String caminho) {
        super(caminho);
    }

    @Override
    public void generator(String nome, Projeto projeto) {
        Document documentPDF = new Document();

        try {
            PdfWriter.getInstance(documentPDF, new FileOutputStream(caminho + nome + ".pdf"));

            documentPDF.open();

            
            Projeto teste = ProjetoSingleton.getInstance().getProjetoPorId(projeto.getId());
            documentPDF.add(new Paragraph("Nome: " + projeto.getNome()));
            documentPDF.add(new Paragraph("Data de Criação: " + projeto.getDataCriacao()));
            documentPDF.add(new Paragraph("Percentual de Impostos: " + teste.getPercentualImpostos()));
            documentPDF.add(new Paragraph("Percentual de Lucro: " + teste.getPercentualLucro()));
            
            System.out.println("Percentual de Impostos: " + projeto.getPercentualImpostos());
            System.out.println("Percentual de Impostos: " + projeto.getPercentualLucro());
            
        documentPDF.add(new Paragraph("Funcionalidades Web/Backend:"));
        if (projeto.getFuncionalidadesWebBackend() != null && !projeto.getFuncionalidadesWebBackend().isEmpty()) {
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesWebBackend()) {
                System.out.println("Adicionando Web/Backend: " + funcionalidade.getNome());  // Log de execução
                documentPDF.add(new Paragraph("- " + funcionalidade.getNome() + " (Dias Estimados: " + funcionalidade.getHorasEstimadas() + ")"));
            }
        }

        documentPDF.add(new Paragraph("Funcionalidades iOS:"));
        if (projeto.getFuncionalidadesIOS() != null && !projeto.getFuncionalidadesIOS().isEmpty()) {
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesIOS()) {
                System.out.println("Adicionando iOS: " + funcionalidade.getNome());  // Log de execução
                documentPDF.add(new Paragraph("- " + funcionalidade.getNome() + " (Dias Estimados: " + funcionalidade.getHorasEstimadas() + ")"));
            }
        }

        documentPDF.add(new Paragraph("Funcionalidades Android:"));
        if (projeto.getFuncionalidadesAndroid() != null && !projeto.getFuncionalidadesAndroid().isEmpty()) {
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesAndroid()) {
                System.out.println("Adicionando Android: " + funcionalidade.getNome());  // Log de execução
                documentPDF.add(new Paragraph("- " + funcionalidade.getNome() + " (Dias Estimados: " + funcionalidade.getHorasEstimadas() + ")"));
            }
        }
   
            documentPDF.add(new Paragraph("Custos Adicionais:"));
            for (CustoAdicional custo : projeto.getCustosAdicionais()) {
                documentPDF.add(new Paragraph("- " + custo.getDescricao() + " (Valor: " + custo.getValor() + ")"));
            }
            
            Estimativa estimativa = projeto.getEstimativa();
            if (estimativa != null) {
                documentPDF.add(new Paragraph("Estimativa do Projeto:"));
                documentPDF.add(new Paragraph("Custo Total: " + estimativa.getCustoTotal()));
                documentPDF.add(new Paragraph("Tempo Total (em dias): " + estimativa.getTempoTotal()));
                documentPDF.add(new Paragraph("Preço Final: " + estimativa.getPrecoFinal()));
            }


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        documentPDF.close();
    }
}