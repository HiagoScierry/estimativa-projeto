package br.projeto.presenter.window_command;

import br.projeto.command.ProjetoCommand;
import br.projeto.model.Projeto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FecharJanelasRelacionadasCommand implements WindowCommand {
    private final JDesktopPane desktop;
    private final List<Projeto> listaProjetos;

    public FecharJanelasRelacionadasCommand(JDesktopPane desktop, List<Projeto> listaProjetos) {
        this.desktop = desktop;
        this.listaProjetos = listaProjetos;
    }

    @Override
    public void execute() {
        List<String> nomesProjetos = new ArrayList<>();
        for (Projeto projeto : listaProjetos) {
            System.out.println(projeto.getNome());
            nomesProjetos.add(projeto.getNome());
        }

        JInternalFrame[] quadrosInternos = desktop.getAllFrames();
        for (JInternalFrame quadroInterno : quadrosInternos) {
            if (quadroInterno.getTitle().startsWith("Detalhes do Projeto: ")) {
                String nomeProjeto = quadroInterno.getTitle().replace("Detalhes do Projeto: ", "");
                System.out.println(nomeProjeto);
                if (!nomesProjetos.contains(nomeProjeto)) {
                    quadroInterno.dispose();
                }
            }
        }
    }
}
