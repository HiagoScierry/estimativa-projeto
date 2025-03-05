/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.interfaces;

import br.projeto.model.CustoAdicional;
import java.util.List;

/**
 *
 * @author hiago
 */
public interface IProjetoCustoAdicionalDAO {
    void associarProjetoCustoAdicional(int projetoId, int custoAdicionalId);
    List<CustoAdicional> listarCustosAdicionaisPorProjeto(int projetoId);
    void atualizarCustosAdicionaisProjeto(int projetoId, List<CustoAdicional> custosAdicionais);
}
