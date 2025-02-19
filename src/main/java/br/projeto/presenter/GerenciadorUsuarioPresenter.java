/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.Projeto;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.view.GerenciadorUsuarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Cauã
 */

public class GerenciadorUsuarioPresenter implements Observer {
    private final GerenciadorUsuarioView view;
    private final ProjetoRepositoryMock repository;

    public GerenciadorUsuarioPresenter() {
        this.view = new GerenciadorUsuarioView();
        this.repository = new ProjetoRepositoryMock();
        configurarEventos();

        this.repository.addObserver(this);
    }

    private void configurarEventos() {
        view.getBtnCadastrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });
    }

    private void cadastrarUsuario() {
        String usuario = view.getTxtUsuario().getText();
        String email = view.getTxtEmail().getText();
        String senha = new String(view.getPswSenha().getPassword());

        if (usuario.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            // implementar lógica real
            JOptionPane.showMessageDialog(view, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            atualizarTabelaUsuarios();
        }
    }

    private void limparCampos() {
        view.getTxtUsuario().setText("");
        view.getTxtEmail().setText("");
        view.getPswSenha().setText("");
    }

    private void atualizarTabelaUsuarios() {
       /* Aqui é só um exemplo, precisa ser implementado quando tivermos o repository correto
        List<Usuario> usuarios = repository.getUsuarios(); 
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblUsuariosCadastrados().getModel();
        tableModel.setRowCount(0); 

        for (Usuario usuario : usuarios) {
            Object[] row = {usuario.getNome(), usuario.getEmail()};
            tableModel.addRow(row);
        }*/
    }


    @Override
    public void update(List<Projeto> projetos) {
        atualizarTabelaUsuarios();
    }

    public GerenciadorUsuarioView getView() {
        return view;
    }

    public ProjetoRepositoryMock getRepository() {
        return repository;
    }
}
