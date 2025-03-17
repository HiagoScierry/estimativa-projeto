/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.presenter.ElaborarEstimativaPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.singleton.ProjetoSingleton;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author Cauã
 */

//Command criada de maneira genérica seguindo o modelo da classe 'AbrirInternalFrameGenericoProjetoCommand', sendo feita alterações somente para mostrar a JFrame correta
//Acredito que a classe "MostrarMensagemCommand também possa ser excluida
public class CriarEstimativaProjetoCommand implements ProjetoCommand{
    private final JDesktopPane desktop;
    private final String titulo;
    private Integer projetoId;
    private ProjetoSingleton projetoSingleton;

    public CriarEstimativaProjetoCommand(JDesktopPane desktop, String titulo) {
        this.desktop = desktop;
        this.titulo = titulo;
        this.projetoSingleton = ProjetoSingleton.getInstance();
    }

    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
    }

    @Override
    public void execute() {
        if (projetoId == null) {
            throw new IllegalStateException("O Id do projeto não foi definido para este comando.");
        }

        projetoSingleton.setIdProjetoAtual(projetoId);

        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(titulo)) {
            windowManager.bringToFront(titulo);
        } else {
            ElaborarEstimativaPresenter presenter = new ElaborarEstimativaPresenter();
            presenter.setProjetoId(this.projetoId);
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
