/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.presenter.ConfiguracaoPresenter;
import br.projeto.presenter.helpers.WindowManager;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Cauã
 */
//Command criada de maneira genérica seguindo o modelo da classe 'AbrirInternalFrameGenericoProjetoCommand', sendo feita alterações somente para mostrar a JFrame correta
//Acredito que a classe "MostrarMensagemCommand também possa ser excluida
public class AbrirConfiguracaoProjetoCommand implements ProjetoCommand {
    private final JDesktopPane desktop;
    private final String titulo;

    public AbrirConfiguracaoProjetoCommand(JDesktopPane desktop, String titulo) {
        this.desktop = desktop;
        this.titulo = titulo;
    }

    @Override
    public void execute() {
        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(titulo)) {
            windowManager.bringToFront(titulo);
        } else {
            ConfiguracaoPresenter presenter = new ConfiguracaoPresenter();
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
