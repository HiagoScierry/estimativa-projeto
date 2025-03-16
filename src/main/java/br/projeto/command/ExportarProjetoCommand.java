/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.generators.AGeneratorAdapter;
import br.projeto.generators.GeneratorCSVAdapter;
import br.projeto.generators.GeneratorPDFAdapter;
import br.projeto.singleton.ProjetoSingleton;

/**
 *
 * @author Cauã
 */
import javax.swing.JOptionPane;

public class ExportarProjetoCommand implements ProjetoCommand {
    private String caminho;
    private ProjetoSingleton projetoSingleton;
    private Integer projetoId;

    public ExportarProjetoCommand(ProjetoSingleton projetoSingleton) {
        this.projetoSingleton = projetoSingleton;
        this.caminho = "tmp/";
    }

    public void setProjetoId(Integer projetoId) {
        this.projetoId = projetoId;
    }
        
    @Override
    public void execute() {
        String[] opcoes = {"PDF", "CSV"};
        int escolha = JOptionPane.showOptionDialog(
            null, 
            "Escolha o formato de exportação:", 
            "Exportar Projeto", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opcoes, 
            opcoes[0]
        );

        AGeneratorAdapter gerador;
        if (escolha == 0) { // PDF
            gerador = new GeneratorPDFAdapter(caminho);
        } else if (escolha == 1) { // CSV
            gerador = new GeneratorCSVAdapter(caminho);

        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma opção selecionada.");
            return;
        }

        projetoSingleton.setIdProjetoAtual(projetoId);
        gerador.generator(projetoSingleton.getProjetoPorId(projetoSingleton.getIdProjetoAtual()).getNome(), projetoSingleton.getProjetoPorId(projetoId));
        JOptionPane.showMessageDialog(null, "Exportação concluída!");
    }
}
