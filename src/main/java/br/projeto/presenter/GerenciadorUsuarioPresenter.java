/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.ProjetoClayton;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.view.GerenciadorUsuarioView;
import java.util.List;

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
        this.repository.addObserver(this);
    }

    private void inserirDadosUsuarioLogado(){
        //criar logica aqui para inserir os dados
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
    public void update(List<ProjetoClayton> projetos) {
        inserirDadosUsuarioLogado();
        atualizarTabelaUsuarios();
    }

    public GerenciadorUsuarioView getView() {
        return view;
    }

    public ProjetoRepositoryMock getRepository() {
        return repository;
    }
}
