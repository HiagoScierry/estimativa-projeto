package br.projeto.command;

import br.projeto.presenter.DashBoardProjetoPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.singleton.ProjetoSingleton;
import br.projeto.view.DashBoardProjetoView;

import javax.swing.*;

public class AbrirDashboardProjetoCommand implements ProjetoCommand {
    private final JDesktopPane desktop;
    private final ProjetoSingleton projetoSingleton;

    public AbrirDashboardProjetoCommand(JDesktopPane desktop, ProjetoSingleton projetoSingleton) {
        this.desktop = desktop;
        this.projetoSingleton = projetoSingleton;
    }

    @Override
    public void execute() {
        String tituloJanela = "Dashboard de Projetos";
        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(tituloJanela)) {
            windowManager.bringToFront(tituloJanela);
        } else {
            DashBoardProjetoView dashboardView = new DashBoardProjetoView();
            new DashBoardProjetoPresenter(dashboardView, projetoSingleton);
            dashboardView.setTitle(tituloJanela);
            desktop.add(dashboardView);
            dashboardView.setVisible(true);
            try {
                dashboardView.setMaximum(true);
            } catch (Exception ignored) {
            }
        }
    }
}
