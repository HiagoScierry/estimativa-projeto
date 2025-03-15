package br.projeto.generators;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class GeneratorPDFAdapter extends AGeneratorAdapter {

    public GeneratorPDFAdapter(String caminho) {
        super(caminho);
    }

    @Override
    public void generator(String nome, ArrayList<String[]> conteudo) {
        Document documentPDF = new Document();

        try {
            PdfWriter.getInstance(documentPDF, new FileOutputStream(caminho + nome + ".pdf"));

            documentPDF.open();

            // Adicionar o conteúdo
            for (String[] linha : conteudo) {
                StringBuilder linhaFormatada = new StringBuilder();
                for (int i = 0; i < linha.length; i++) {
                    linhaFormatada.append(linha[i]);
                    if (i < linha.length - 1) {
                        linhaFormatada.append(" ");
                    }
                }
                documentPDF.add(new Paragraph(linhaFormatada.toString()));
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        documentPDF.close();
    }
}