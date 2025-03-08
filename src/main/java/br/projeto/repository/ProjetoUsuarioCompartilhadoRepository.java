/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.repository;

import br.projeto.dao.DaoUtil;
import br.projeto.dao.interfaces.IProjetoUsuarioCompartilhadoDAO;
import java.util.List;

/**
 *
 * @author Cauã
 */
public class ProjetoUsuarioCompartilhadoRepository {

    private final DaoUtil daoUtil;
    private final IProjetoUsuarioCompartilhadoDAO projetoUsuarioCompartilhadoDAO;

    public ProjetoUsuarioCompartilhadoRepository() {
        this.daoUtil = DaoUtil.getInstance();
        this.projetoUsuarioCompartilhadoDAO = daoUtil.getProjetoUsuarioCompartilhadoDao();
    }

    public void compartilharProjeto(int projetoId, int usuarioId, boolean isCriador) {
        projetoUsuarioCompartilhadoDAO.compartilharProjetoComUsuario(projetoId, usuarioId, isCriador);
    }

    public List<Integer> obterUsuariosDoProjeto(int projetoId) {
        return projetoUsuarioCompartilhadoDAO.listarUsuariosPorProjeto(projetoId);
    }

    public List<Integer> obterProjetosDoUsuario(int usuarioId) {
        return projetoUsuarioCompartilhadoDAO.listarProjetosPorUsuario(usuarioId);
    }

    public void removerCompartilhamento(int projetoId, int usuarioId) {
        projetoUsuarioCompartilhadoDAO.removerCompartilhamento(projetoId, usuarioId);
    }

    public boolean verificarSeEhCriador(int projetoId, int usuarioId) {
        return projetoUsuarioCompartilhadoDAO.isCriador(projetoId, usuarioId);
    }
}

