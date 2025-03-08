package br.projeto.repository;

import br.projeto.model.Projeto;
import br.projeto.model.Subject;
import br.projeto.presenter.Observer;
import java.time.LocalDate;

import java.util.*;

public class ProjetoRepositoryMock implements Subject {
    private final List<Projeto> projetos;
    private final List<Observer> observers;

    public ProjetoRepositoryMock() {
        projetos = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public Projeto getProjetoPorNome(String nome) {
        return projetos.stream()
                .filter(projeto -> projeto.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }

    
    //AQUI TAMBÈM FOI MOCKADO PARA TESTAR O JTREE DE PROJETO
    public void adicionarProjeto(String nome, String criador, String dataCriacao, String status,
                                 boolean compartilhado, String compartilhadoPor,
                                 List<String> tipos, Map<String, Integer> funcionalidadesEscolhidas) {
                Projeto novoProjeto = new Projeto(
            1,               // ID único
            "a",                         // Nome do projeto
            null,                           // Criador (null por padrão)
            LocalDate.now().toString(),     // Data de criação (hoje)
            "Em andamento",                 // Status inicial genérico
            false,                          // Não compartilhado por padrão
            null,                           // Não compartilhado por ninguém
            new ArrayList<>(),              // Lista vazia de perfis
            new ArrayList<>(),              // Lista vazia de funcionalidades Web/Backend
            new ArrayList<>(),              // Lista vazia de funcionalidades iOS
            new ArrayList<>(),              // Lista vazia de funcionalidades Android
            new ArrayList<>(),              // Lista vazia de custos adicionais
            null,                           // Nível de UI indefinido
            0.0,                            // Percentual de impostos padrão (0%)
            0.0                             // Percentual de lucro padrão (0%)
        );
//        Projeto novoProjeto = new Projeto(nome, criador, dataCriacao, status, compartilhado,
//                compartilhadoPor, tipos, funcionalidadesEscolhidas);
        projetos.add(novoProjeto);
        notifyObservers();
    }

    public boolean removerProjetoPorNome(String nome) {
        boolean removido = projetos.removeIf(projeto -> projeto.getNome().equals(nome));
        if (removido) {
            notifyObservers();
        }
        return removido;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(projetos);
        }
    }

}
