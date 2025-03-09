package br.projeto.presenter;

import br.projeto.command.ProjetoCommand;
import br.projeto.singleton.ProjetoSingleton;

import javax.swing.JOptionPane;

public class CriarProjetoProjetoCommand implements ProjetoCommand {

    private static int contadorProjetos = 1;
    private static final String PREFIXO_TITULO = "Novo Projeto ";
    private ProjetoSingleton projetoSingleton = ProjetoSingleton.getInstance();

    public CriarProjetoProjetoCommand(ProjetoSingleton projetoSingleton) {
        this.projetoSingleton = projetoSingleton;
    }

    @Override
    public void execute() {
        String titulo = PREFIXO_TITULO + contadorProjetos;
        
        JOptionPane.showMessageDialog(null, "O projeto '" + titulo + "' foi criado com sucesso!",
                                      "Projeto Criado", JOptionPane.INFORMATION_MESSAGE);

        projetoSingleton.notifyObservers();
        contadorProjetos++;
    }
}