package br.projeto.presenter;

import br.projeto.presenter.helpers.WindowManager;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.repository.UsuarioRepository;
import br.projeto.view.LoginView;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Cauã
 */
public class LoginPresenter {
    private final LoginView view;

    public LoginPresenter() {
        this.view = new LoginView();
        configurarEventos();
        view.setVisible(true);
    }

    private void configurarEventos() {
        view.getBtnEntrar().addActionListener(e -> fazerLogin());

        view.getBtnCadastrar().addActionListener(e -> redirecionarCadastro());
    }

    public void fazerLogin() {
        String nomeUsuario = view.getTxtNomeUsuario().getText();
        String senha = new String(view.getPswSenha().getPassword());

        // Verifica se os campos não estão vazios
        if (nomeUsuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, preencha ambos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            // Aqui você pode adicionar a lógica de autenticação real, com base no repositório ou um sistema de autenticação.
            if (autenticarUsuario(nomeUsuario, senha)) {
                // Lógica de redirecionamento após login bem-sucedido
                SwingUtilities.invokeLater(() -> {
                    PrincipalPresenter presenter = new PrincipalPresenter(new ProjetoRepositoryMock());
                    WindowManager.getInstance().initialize(presenter);
                });
                view.dispose(); // Fecha a tela de login
            } else {
                JOptionPane.showMessageDialog(view, "Usuário ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean autenticarUsuario(String nomeUsuario, String senha) {
        UsuarioRepository usuarioRepository = new UsuarioRepository();

        return usuarioRepository.autenticar(nomeUsuario, senha);
    }

    private void redirecionarCadastro() {
        SwingUtilities.invokeLater(() -> {
            CadastrarPresenter cadastrarPresenter = new CadastrarPresenter();
            cadastrarPresenter.getView().setVisible(true);
        });
    }
}
