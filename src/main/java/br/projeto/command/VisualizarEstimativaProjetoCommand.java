/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.command;

import br.projeto.presenter.VisualizarEstimativaPresenter;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.singleton.ProjetoSingleton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Cauã
 */

//Command criada de maneira genérica seguindo o modelo da classe 'AbrirInternalFrameGenericoProjetoCommand', sendo feita alterações somente para mostrar a JFrame correta
public class VisualizarEstimativaProjetoCommand implements ProjetoCommand{
    private final JDesktopPane desktop;
    private final String titulo;
    private final ProjetoSingleton projetoSingleton;
    private int projetoId;

    public VisualizarEstimativaProjetoCommand(JDesktopPane desktop, String titulo) {
        this.desktop = desktop;
        this.titulo = titulo;
        this.projetoSingleton = ProjetoSingleton.getInstance();
    }
    
    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
    }

    @Override
    public void execute() {      
        WindowManager windowManager = WindowManager.getInstance();

        if (windowManager.isFrameAberto(titulo)) {
            windowManager.bringToFront(titulo);
        } else {
                 
            if (projetoSingleton.getProjetoPorId(projetoId) == null ||
                projetoSingleton.getProjetoPorId(projetoId).getEstimativa() == null) {
                System.out.println(projetoSingleton.getProjetoPorId(projetoId).getEstimativa());
                JOptionPane.showMessageDialog(null, "Estimativa indisponível! Não é possível acessar esta página.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            VisualizarEstimativaPresenter presenter = new VisualizarEstimativaPresenter(projetoId);
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
