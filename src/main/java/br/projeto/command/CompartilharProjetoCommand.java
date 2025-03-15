package br.projeto.command;

import br.projeto.presenter.CompartilharProjetoPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.singleton.ProjetoSingleton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class CompartilharProjetoCommand implements ProjetoCommand {
    private final JDesktopPane desktop;
    private final ProjetoSingleton projetoSingleton;
    private Integer projetoId;

    public CompartilharProjetoCommand(ProjetoSingleton projetoSingleton, JDesktopPane desktop) {
        this.projetoSingleton = projetoSingleton;
        this.desktop = desktop;
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
        projetoSingleton.setIdProjetoAtual(projetoId);

        String tituloJanela = "Compartilhar";
        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(tituloJanela)) {
            windowManager.bringToFront(tituloJanela);
        } else {
            CompartilharProjetoPresenter presenter = new CompartilharProjetoPresenter(projetoSingleton);

            JInternalFrame frame = new JInternalFrame(tituloJanela, true, true, true, true);
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
