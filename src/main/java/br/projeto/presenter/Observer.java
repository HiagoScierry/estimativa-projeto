package br.projeto.presenter;

import br.projeto.model.ProjetoClayton;

import java.util.List;

public interface Observer {
    void update(List<ProjetoClayton> projetos);
}
