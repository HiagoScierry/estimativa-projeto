/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.repository;

import br.projeto.dao.DaoUtil;
import br.projeto.dao.interfaces.IEstimativaDAO;
import br.projeto.model.Estimativa;
import br.projeto.repository.interfaces.IEstimativaRepository;

/**
 *
 * @author Cauã
 */
public class EstimativaRepository implements IEstimativaRepository {

    private DaoUtil daoUtil;
    private IEstimativaDAO estimativaDAO;

    public EstimativaRepository() {
        daoUtil = DaoUtil.getInstance();
        estimativaDAO = daoUtil.getEstimativaDao();
    }

    @Override
    public void adicionarEstimativa(Estimativa estimativa) {
        estimativaDAO.inserir(estimativa);
    }

    @Override
    public void removerEstimativa(int id) {
        estimativaDAO.excluir(id);
    }

    @Override
    public Estimativa buscarPorId(int id) {
        return estimativaDAO.buscarPorId(id);
    }

    @Override
    public void atualizarEstimativa(Estimativa estimativa) {
        estimativaDAO.atualizar(estimativa);
    }
}
