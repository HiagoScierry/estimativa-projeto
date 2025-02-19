/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.Projeto;
import br.projeto.view.CompartilharProjetoView;
import br.projeto.repository.ProjetoRepositoryMock;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Cauã
 */

public class CompartilharProjetoPresenter implements Observer{
    private final CompartilharProjetoView view;
    private final ProjetoRepositoryMock repository;

    public CompartilharProjetoPresenter() {
        this.view = new CompartilharProjetoView();
        this.repository = new ProjetoRepositoryMock();
        configurarEventos();
        
        this.repository.addObserver(this);
    }

    private void configurarEventos() {
        view.getBtnCompartilhar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compartilharProjeto();
            }
        });
    }

    private void compartilharProjeto() {
        JOptionPane.showMessageDialog(view, 
                "Projeto compartilhado com os usuários selecionados!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void update(List<Projeto> projetos) {
        StringBuilder projetosList = new StringBuilder("Projetos Disponíveis:\n");
        for (Projeto projeto : projetos) {
            projetosList.append(projeto.getNome()).append("\n");
        }
    }

    public CompartilharProjetoView getView() {
        return view;
    }

    public ProjetoRepositoryMock getRepository() {
        return repository;
    }
}

