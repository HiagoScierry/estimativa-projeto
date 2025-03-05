/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.interfaces;

import br.projeto.model.Funcionalidade;
import br.projeto.model.Projeto;
import java.util.List;

/**
 *
 * @author hiago
 */
public interface IProjetoFuncionalidadeDAO {
    void associarProjetoFuncionalidade(int projetoId, int funcionalidadeId);
    List<Funcionalidade> listarFuncionalidadesPorProjeto(int projetoId, String tipo);
    void atualizarFuncionalidadesProjeto(int projetoId, Projeto projeto);
}
