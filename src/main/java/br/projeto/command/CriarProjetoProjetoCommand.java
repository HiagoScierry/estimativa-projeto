package br.projeto.command;

import br.projeto.presenter.ElaborarEstimativaPresenter;
import br.projeto.presenter.helpers.WindowManager;
import javax.swing.*;

public class CriarProjetoProjetoCommand implements ProjetoCommand {
    private final JDesktopPane desktop;
    private final String titulo;

    public CriarProjetoProjetoCommand(JDesktopPane desktop, String titulo) {
        this.desktop = desktop;
        this.titulo = titulo;
    }

    @Override
    public void execute() {
        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(titulo)) {
            windowManager.bringToFront(titulo);
        } else {
            ElaborarEstimativaPresenter presenter = new ElaborarEstimativaPresenter();
            JInternalFrame frame = new JInternalFrame(titulo, true, true, true, true);
            frame.setContentPane(presenter.getView().getContentPane());
            frame.pack();
            frame.setSize(desktop.getWidth(), desktop.getHeight());
            frame.setIconifiable(false);
            frame.setVisible(true);
            desktop.add(frame);
            try {
                frame.setMaximum(true);
            } catch (Exception ignored) {
            }
        }
    }
}
