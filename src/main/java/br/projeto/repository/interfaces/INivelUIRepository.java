package br.projeto.repository.interfaces;

import br.projeto.model.NivelUI;
import java.util.List;

public interface INivelUIRepository {
    void adicionarNivelUI(NivelUI nivelUI);
    void removerNivelUI(int id);
    NivelUI buscarPorId(int id);
    void atualizarNivelUI(NivelUI nivelUI);
    List<NivelUI> listarTodos();
}