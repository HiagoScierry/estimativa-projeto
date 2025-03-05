package br.projeto.repository.interfaces;

import br.projeto.model.Funcionalidade;

public interface IFuncionalidadeRepository {
    void adicionarFuncionalidade(Funcionalidade funcionalidade);
    void removerFuncionalidade(int id);
    Funcionalidade buscarPorId(int id);
    void atualizarFuncionalidade(Funcionalidade funcionalidade);
}