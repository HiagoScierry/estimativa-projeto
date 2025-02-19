/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.presenter.helpers.WindowManager;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.view.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Cauã
 */
public class LoginPresenter {
    private final LoginView view;
    private final ProjetoRepositoryMock repository;

    public LoginPresenter(ProjetoRepositoryMock repository) {
        this.view = new LoginView();
        this.repository = repository;

        configurarEventos();
        view.setVisible(true);
    }

    //aqui tbm precisa ser colocado lógica de login de acordo com o módulo externo do Clayton
    private void configurarEventos() {
        view.getBtnEntrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fazerLogin();
            }
        });
    }

    public void fazerLogin() {
        SwingUtilities.invokeLater(() -> {
            PrincipalPresenter presenter = new PrincipalPresenter(new ProjetoRepositoryMock());
            WindowManager.getInstance().initialize(presenter);
        });
        view.dispose();
    }
}
