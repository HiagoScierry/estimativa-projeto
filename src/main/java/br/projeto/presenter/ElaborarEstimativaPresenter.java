/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.Projeto;
import br.projeto.singleton.ProjetoSingleton;
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
    private final ProjetoSingleton projetoSingleton;

    public ElaborarEstimativaPresenter() {
        this.view = new ElaborarEstimativaView();
        this.projetoSingleton = ProjetoSingleton.getInstance();
        configurarEventos();
        this.projetoSingleton.addObserver(this);
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

}

