package br.projeto.repository.interfaces;

import br.projeto.model.CustoAdicional;
import java.util.List;

public interface ICustosAdicionaisRepository {
    void adicionarCusto(CustoAdicional custo);
    void removerCusto(int id);
    CustoAdicional buscarPorId(int id);
    void atualizarCusto(CustoAdicional custo);
}