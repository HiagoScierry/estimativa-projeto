package br.projeto.repository.interfaces;

import br.projeto.model.Perfil;
import java.util.List;

public interface IPerfilRepository {
    void adicionarPerfil(Perfil perfil);
    void removerPerfil(int id);
    Perfil buscarPorId(int id);
    void atualizarPerfil(Perfil perfil);
    List<Perfil> listarTodos();
}