/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.ProjetoClayton;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.view.VisualizarEstimativaView;
import java.util.List;

/**
 *
 * @author Cauã
 */

public class VisualizarEstimativaPresenter implements Observer {
    private final VisualizarEstimativaView view;
    private final ProjetoRepositoryMock repository;

    public VisualizarEstimativaPresenter() {
        this.view = new VisualizarEstimativaView();
        this.repository = new ProjetoRepositoryMock();

        this.repository.addObserver(this);

        configurarEventos();
        atualizarTabelas();
    }

    private void configurarEventos() {
        //precisa de um recuperador no banco de dados para as plataformas do projeto (caso isso seja mantido)
    }

    private void atualizarTabelas() {
        // Atualiza as tabelas com os dados do repositório
        recuperarDadosTabelaFuncionalidades();
        recuperarDadosTabelaTaxasExtras();
        recuperarDadosTabelaValoresFinais();
    }

    private void recuperarDadosTabelaFuncionalidades() {
        //precisa de um recuperador no banco de dados para as funcionalidades do projeto
    }

    private void recuperarDadosTabelaTaxasExtras() {
        //precisa de um recuperador no banco de dados para as taxas do projeto
    }

    private void recuperarDadosTabelaValoresFinais() {
        //precisa de um recuperador no banco de dados para os valores finais do projeto
    }

    @Override
    public void update(List<ProjetoClayton> projetos) {
        atualizarTabelas();
    }

    public VisualizarEstimativaView getView() {
        return view;
    }

    public ProjetoRepositoryMock getRepository() {
        return repository;
    }
}

