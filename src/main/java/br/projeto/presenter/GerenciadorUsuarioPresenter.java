/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.Projeto;
import br.projeto.model.Usuario;
import br.projeto.repository.UsuarioRepository;
import br.projeto.repository.interfaces.IUsuarioRepository;
import br.projeto.singleton.UsuarioSingleton;
import br.projeto.view.GerenciadorUsuarioView;
import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cauã
 */

public class GerenciadorUsuarioPresenter {
    private final GerenciadorUsuarioView view;
    private UsuarioSingleton usuarioLogado;

    public GerenciadorUsuarioPresenter() {
        this.view = new GerenciadorUsuarioView();
        this.usuarioLogado = UsuarioSingleton.getInstance();
        configurarEventos();
    }

    private void configurarEventos(){
        inserirDadosUsuarioLogado();
        atualizarTabelaUsuarios();
    }

    private void inserirDadosUsuarioLogado(){
        view.getTxtEmail().setText(usuarioLogado.getUsuario().getEmail());
        view.getTxtUsuario().setText(usuarioLogado.getUsuario().getNome());
    }
    
    private void atualizarTabelaUsuarios() {
        IUsuarioRepository repository = new UsuarioRepository();

        List<Usuario> usuarios = repository.buscarTodos();
        DefaultTableModel tableModel = (DefaultTableModel) view.getTblUsuariosCadastrados().getModel();
        tableModel.setRowCount(0);

        for (Usuario usuario : usuarios) {
            Object[] row = {usuario.getNome(), usuario.getEmail()};
            tableModel.addRow(row);
        }

    }

    public GerenciadorUsuarioView getView() {
        return view;
    }

}
