package br.projeto.repository.interfaces;

import br.projeto.model.Perfil;
import java.util.List;

public interface IPerfilRepository {
    void adicionarPerfil(Perfil perfil);
    Perfil buscarPorId(int id);
    List<Perfil> listarTodos();
}