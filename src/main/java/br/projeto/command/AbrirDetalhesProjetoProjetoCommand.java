package br.projeto.command;

import br.projeto.presenter.DetalheProjetoPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.singleton.ProjetoSingleton;
import br.projeto.view.DetalheProjetoView;

import javax.swing.*;

public class AbrirDetalhesProjetoProjetoCommand implements ProjetoCommand {
    private final ProjetoSingleton projetoSingleton;
    private final JDesktopPane desktop;
    private Integer projetoId;
    private String projetoNome;

    public AbrirDetalhesProjetoProjetoCommand(ProjetoSingleton projetoSingleton, JDesktopPane desktop) {
        this.projetoSingleton = projetoSingleton;
        this.desktop = desktop;
    }

    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
    }

    public void setProjetoNome(String projetoNome) {
        this.projetoNome = projetoNome;
    }

    @Override
    public void execute() {
        if (projetoId == null) {
            throw new IllegalStateException("O Id do projeto não foi definido para este comando.");
        }

        String tituloJanela = "Detalhes do Projeto: " + projetoNome;
        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(tituloJanela)) {
            windowManager.bringToFront(tituloJanela);
        } else {
            DetalheProjetoView detalheView = new DetalheProjetoView();
            detalheView.setTitle(tituloJanela);
            new DetalheProjetoPresenter(detalheView, projetoSingleton, projetoId);
            desktop.add(detalheView);
            detalheView.setVisible(true);
            try {
                detalheView.setMaximum(true);
            } catch (Exception ignored) {
            }
        }
    }
}
