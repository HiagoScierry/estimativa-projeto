package br.projeto.presenter;

import br.projeto.model.Usuario;
import br.projeto.repository.UsuarioRepository;
import br.projeto.repository.interfaces.IUsuarioRepository;
import br.projeto.singleton.UsuarioSingleton;
import br.projeto.view.CadastrarView;

import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

import com.pss.senha.validacao.ValidadorSenha;

/**
 *
 * @author Cauã
 */
public class CadastrarPresenter {
    private final CadastrarView view;

    public CadastrarPresenter() {
        this.view = new CadastrarView();

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

        ValidadorSenha validadorSenha = new ValidadorSenha();
        List<String> errosSenha =  validadorSenha.validar(senha);

        //LEMBRAR DE TIRAR DO COMENTÁRIO QUANDO ENTREGAR
        /*if (!errosSenha.isEmpty()) {
            String mensagemErro = "Erro ao cadastrar usuário!\n";
            for (String erro : errosSenha) {
                mensagemErro += erro + "\n";
            }
            JOptionPane.showMessageDialog(view, mensagemErro, "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }*/

        // Verifica se os campos não estão vazios
        if (nomeUsuario.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            // Aqui você pode adicionar a lógica de cadastro no seu repositório
            if (cadastrarNoSistema(nomeUsuario, email, senha)) {
                JOptionPane.showMessageDialog(view, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                view.dispose();  // Fecha a tela de cadastro após o sucesso
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao cadastrar usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //só uma simulação, temos que aplicar a lógica aqui
    private boolean cadastrarNoSistema(String nomeUsuario, String email, String senha) {
        IUsuarioRepository usuarioRepository = new UsuarioRepository();

        // Verifica se o email já está cadastrado
        Optional<Usuario> usuarioExistente = usuarioRepository.buscarPorEmail(email);
        if (!(usuarioExistente == null)) {
            JOptionPane.showMessageDialog(view, "Este email já está cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Cria um novo usuário
        Usuario usuario = new Usuario(0, nomeUsuario, email, senha);
        usuarioRepository.cadatrarUsuario(usuario);

        return true;

    }

    private void cancelarCadastro() {
        view.dispose();
    }

    public CadastrarView getView() {
        return view;
    }

}
