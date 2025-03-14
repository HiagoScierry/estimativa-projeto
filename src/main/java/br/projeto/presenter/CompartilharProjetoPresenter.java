/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.Projeto;
import br.projeto.model.Usuario;
import br.projeto.repository.ProjetoUsuarioCompartilhadoRepository;
import br.projeto.repository.UsuarioRepository;
import br.projeto.repository.interfaces.IUsuarioRepository;
import br.projeto.singleton.ProjetoSingleton;
import br.projeto.singleton.UsuarioSingleton;
import br.projeto.view.CompartilharProjetoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cauã
 */
public class CompartilharProjetoPresenter {
    private final CompartilharProjetoView view;
    private final ProjetoSingleton projetoSingleton;
    private final UsuarioSingleton usuarioLogado;
    private final ProjetoUsuarioCompartilhadoRepository projetoCompartilhadoRepository;
    private final IUsuarioRepository usuarioRepository;
    final int projetoId;

    public CompartilharProjetoPresenter(ProjetoSingleton projetoSingleton, int projetoId) {
        this.view = new CompartilharProjetoView();
        this.projetoSingleton = projetoSingleton;
        this.usuarioLogado = UsuarioSingleton.getInstance();
        this.projetoCompartilhadoRepository = new ProjetoUsuarioCompartilhadoRepository();
        this.usuarioRepository = new UsuarioRepository();   
        this.projetoId = projetoId;
        
        projetoSingleton.setIdProjetoAtual(projetoId);
        configurarEventos();
        atualizarTabelaUsuarios();
    }

    private void configurarEventos() {
        view.getBtnCompartilhar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compartilharProjeto();
            }
        });
    }

    private void atualizarTabelaUsuarios() {
        List<Usuario> usuarios = usuarioRepository.buscarTodos();

        String emailUsuarioLogado = usuarioLogado.getUsuario().getEmail();
        List<Usuario> usuariosFiltrados = usuarios.stream()
                .filter(usuario -> !usuario.getEmail().equals(emailUsuarioLogado))
                .collect(Collectors.toList());

        DefaultTableModel tableModel = (DefaultTableModel) view.getTblUsuariosDisponiveis().getModel();
        tableModel.setRowCount(0);

        for (Usuario usuario : usuariosFiltrados) {
            Object[] row = {false, usuario.getNome(), usuario.getEmail()};
            tableModel.addRow(row);
        }
    }

    private void compartilharProjeto() {
        Projeto projetoAtual = projetoSingleton.getProjetoPorId(projetoId);
        System.out.println("Executando CompartilharProjetoPresenter para o projeto ID: " + projetoId); //debug

        if (projetoAtual == null) {
            JOptionPane.showMessageDialog(
                view, 
                "Nenhum projeto selecionado.", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        DefaultTableModel model = (DefaultTableModel) view.getTblUsuariosDisponiveis().getModel();
        int totalLinhas = model.getRowCount();
        boolean usuarioSelecionado = false;

        for (int i = 0; i < totalLinhas; i++) {
            boolean selecionado = (boolean) model.getValueAt(i, 0);
            if (selecionado) {
                usuarioSelecionado = true;
                String emailUsuario = (String) model.getValueAt(i, 2);
                
            if (!usuarioSelecionado) {
            JOptionPane.showMessageDialog(
                view, 
                "Selecione pelo menos um usuário para compartilhar!", 
                "Aviso", 
                JOptionPane.WARNING_MESSAGE
            );
            return;
            }
            
                usuarioRepository.buscarPorEmail(emailUsuario).ifPresent(usuario -> {
                    if (projetoCompartilhadoRepository.verificarSeProjetoJaCompartilhado(projetoAtual.getId(), usuario.getId())) {
                        JOptionPane.showMessageDialog(
                            view, 
                            "O projeto já foi compartilhado com o usuário: " + usuario.getNome(), 
                            "Aviso", 
                            JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    } else {
                        projetoCompartilhadoRepository.compartilharProjeto(projetoAtual.getId(), usuario.getId(), false);
                        JOptionPane.showMessageDialog(
                        view, 
                        "Projeto compartilhado com sucesso!", 
                        "Sucesso", 
                        JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                });
            }
        }

        for (int i = 0; i < totalLinhas; i++) {
            model.setValueAt(false, i, 0);
        }
    }

    public CompartilharProjetoView getView() {
        return view;
    }
}
