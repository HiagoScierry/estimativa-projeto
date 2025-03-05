package br.projeto.repository.interfaces;

import br.projeto.model.Projeto;
import java.util.List;

public interface IProjetoRepository {
    void adicionarProjeto(Projeto projeto);
    void removerProjeto(int id);
    Projeto buscarPorId(int id);
    void atualizarProjeto(Projeto projeto);
    List<Projeto> listarTodos();
    List<Projeto> listarPorUsuario(int usuarioId);
}
