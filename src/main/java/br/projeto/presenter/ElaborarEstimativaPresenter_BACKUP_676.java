/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.Projeto;
<<<<<<< HEAD
=======
import br.projeto.model.ProjetoClayton;
>>>>>>> 5b1bfcf62375dd7afcf47f6232c1e014e98f4861
import br.projeto.repository.ProjetoRepositoryMock;
import br.projeto.view.ElaborarEstimativaView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        view.getBtnCriarEstimativa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarEstimativa();
            }
        });

        view.getChcWeb().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarEstimativa();
            }
        });

        view.getChcIOS().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarEstimativa();
            }
        });

        view.getChcAndroid().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarEstimativa();
            }
        });
    }

    private void gerarEstimativa() {
        String nomeProjeto = view.getTxtNomeProjeto().getText();
        if (nomeProjeto.isEmpty()) {
            JOptionPane.showMessageDialog(view, "O nome do projeto é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //repository.adicionarEstimativa(nomeProjeto); não sei como vai ser no repositório, então deixei comentado

        atualizarTabelaEstimativaProjeto();
        atualizarTabelaPrecosPorDiaTrabalho();
        atualizarTabelaValoresFinais();

        JOptionPane.showMessageDialog(view, "Estimativa gerada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void atualizarTabelaEstimativaProjeto() {
        //implementar
    }

    private void atualizarTabelaPrecosPorDiaTrabalho() {
        //implementar
    }

    private void atualizarTabelaValoresFinais() {
        //implementar
    }

    private void atualizarEstimativa() {
        // Aqui atualizaria as tabelas dependendo da plataforma escolida nas checkbox
        if (view.getChcWeb().isSelected()) {
            atualizarTabelaEstimativaProjeto();
            atualizarTabelaPrecosPorDiaTrabalho();
            atualizarTabelaValoresFinais();
        }

        if (view.getChcIOS().isSelected()) {
            atualizarTabelaEstimativaProjeto();
            atualizarTabelaPrecosPorDiaTrabalho();
            atualizarTabelaValoresFinais();
        }

        if (view.getChcAndroid().isSelected()) {
            atualizarTabelaEstimativaProjeto();
            atualizarTabelaPrecosPorDiaTrabalho();
            atualizarTabelaValoresFinais();
        }
    }

    @Override
    public void update(List<Projeto> projetos) {
        //implementar
    }

    public ElaborarEstimativaView getView() {
        return view;
    }

    public ProjetoRepositoryMock getRepository() {
        return repository;
    }
}

