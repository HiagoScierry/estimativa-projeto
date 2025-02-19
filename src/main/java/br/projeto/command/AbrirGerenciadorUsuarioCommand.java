package br.projeto.command;

import br.projeto.presenter.GerenciadorUsuarioPresenter;
import br.projeto.presenter.helpers.WindowManager;

import javax.swing.*;

/**
 *
 * @author Cauã
 */

//Command criada de maneira genérica seguindo o modelo da classe 'AbrirInternalFrameGenericoProjetoCommand', sendo feita alterações somente para mostrar a JFrame correta
//Acredito que a classe "MostrarMensagemCommand também possa ser excluida
public class AbrirGerenciadorUsuarioCommand implements ProjetoCommand {
    private final JDesktopPane desktop;
    private final String titulo;

    public AbrirGerenciadorUsuarioCommand(JDesktopPane desktop, String titulo) {
        this.desktop = desktop;
        this.titulo = titulo;
    }

    @Override
    public void execute() {
        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(titulo)) {
            windowManager.bringToFront(titulo);
        } else {
            GerenciadorUsuarioPresenter presenter = new GerenciadorUsuarioPresenter();
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
