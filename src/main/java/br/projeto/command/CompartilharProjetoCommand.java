package br.projeto.command;

import br.projeto.presenter.CompartilharProjetoPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.singleton.ProjetoSingleton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class CompartilharProjetoCommand implements ProjetoCommand {
    private final JDesktopPane desktop;
    private final String titulo;
    private final ProjetoSingleton projetoSingleton;
    private Integer projetoId;

    public CompartilharProjetoCommand(JDesktopPane desktop, String titulo) {
        this.desktop = desktop;
        this.titulo = titulo;
        this.projetoSingleton = ProjetoSingleton.getInstance();
    }
    
    public void setProjetoId(Integer projetoId) {
        this.projetoId = projetoId;
    }

    @Override
    public void execute() {
        if (projetoId == null) {
            throw new IllegalStateException("O Id do projeto não foi definido para este comando.");
        }

        System.out.println("Executando CompartilharProjetoCommand para o projeto ID: " + projetoId); // Debug
        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(titulo)) {
            windowManager.bringToFront(titulo);
        } else {
            CompartilharProjetoPresenter presenter = new CompartilharProjetoPresenter(projetoSingleton, projetoId);
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