/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.Projeto;
import br.projeto.view.PerfisDeProjetoView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author Cauã
 */

public class PerfisDeProjetoPresenter implements Observer {
    private final PerfisDeProjetoView view;

    public PerfisDeProjetoPresenter() {
        this.view = new PerfisDeProjetoView();


        configurarEventos();
        atualizarTabelaPerfisDeProjeto();
    }

    private void configurarEventos() {
        view.getBtnCriarProjeto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarProjeto();
            }
        });

        view.getBtnCriarNovoPerfilProjeto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarNovoPerfilProjeto();
            }
        });
    }

    private void criarProjeto() {
        // precisa da logica de criar o projeto a partir dos selecionados
        JOptionPane.showMessageDialog(view, 
                "Projeto criado com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void criarNovoPerfilProjeto() {
        //precisa da logica de criação de um novo perfil de projeto
    }

    private void atualizarTabelaPerfisDeProjeto() {
        //precisa ser criado a lógica
    }

    @Override
    public void update(List<Projeto> projetos) {
        // Atualiza a tabela sempre que o repositório for alterado
        atualizarTabelaPerfisDeProjeto();
    }

    public PerfisDeProjetoView getView() {
        return view;
    }

}

