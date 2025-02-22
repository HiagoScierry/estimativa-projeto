package br.projeto.presenter;

import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.view.CadastrarView;
import javax.swing.JOptionPane;

/**
 *
 * @author Cauã
 */
public class CadastrarPresenter {
    private final CadastrarView view;
    private final ProjetoRepositoryMock repository;

    public CadastrarPresenter(ProjetoRepositoryMock repository) {
        this.view = new CadastrarView();
        this.repository = repository;

        configurarEventos();
        view.setVisible(true);
    }

    private void configurarEventos() {
        view.getBtnCadastrar().addActionListener(e -> cadastrarUsuario());

        view.getBtnCancelar().addActionListener(e -> cancelarCadastro());
    }

    //só uma simulação, temos que aplicar a lógica aqui
    public void cadastrarUsuario() {
        String nomeUsuario = view.getTxtNomeUsuario().getText();
        String email = view.getTxtEmail().getText();
        String senha = new String(view.getPswSenha().getPassword()); // Pegando a senha de forma segura

        // Verifica se os campos não estão vazios
        if (nomeUsuario.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            // Aqui você pode adicionar a lógica de cadastro no seu repositório
            if (cadastrarNoSistema(nomeUsuario, email, senha)) {
                JOptionPane.showMessageDialog(view, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                view.dispose();  // Fecha a tela de cadastro após o sucesso
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao cadastrar usuário. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //só uma simulação, temos que aplicar a lógica aqui
    private boolean cadastrarNoSistema(String nomeUsuario, String email, String senha) {
        return nomeUsuario.equals("novo_usuario") && senha.length() > 3;
    }

    private void cancelarCadastro() {
        view.dispose();
    }

    public CadastrarView getView() {
        return view;
    }

    public ProjetoRepositoryMock getRepository() {
        return repository;
    }
}
