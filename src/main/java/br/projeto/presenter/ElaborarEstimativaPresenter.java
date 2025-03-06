/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.Projeto;
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.view.ElaborarEstimativaView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 *
 * @author Cauã
 */

public class ElaborarEstimativaPresenter implements Observer {
    private final ElaborarEstimativaView view;
    private final ProjetoRepositoryMock repository;

    public ElaborarEstimativaPresenter() {
        this.view = new ElaborarEstimativaView();
        this.repository = new ProjetoRepositoryMock();
        configurarEventos();
        this.repository.addObserver(this);
    }

    private void configurarEventos() {
        view.getBtnCriarEstimativa().addActionListener((ActionEvent e) -> {
            criarProjeto();
        });
    }

    private void criarProjeto() {
        String nomeProjeto = view.getTxtNomeProjeto().getText();
        if (nomeProjeto.isEmpty()) {
            JOptionPane.showMessageDialog(view, "O nome do projeto é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else{
            JOptionPane.showMessageDialog(view, "Projeto \"" + nomeProjeto + "\" criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    @Override
    public void update(List<Projeto> projetos){}
    
    public ElaborarEstimativaView getView() {
        return view;
    }

    public ProjetoRepositoryMock getRepository() {
        return repository;
    }
}

