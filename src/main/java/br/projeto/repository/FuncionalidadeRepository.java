package br.projeto.repository;

import br.projeto.dao.DaoUtil;
import br.projeto.dao.interfaces.IFuncionalidadeDAO;
import br.projeto.model.Funcionalidade;
import br.projeto.repository.interfaces.IFuncionalidadeRepository;

public class FuncionalidadeRepository implements IFuncionalidadeRepository {

    private DaoUtil daoUtil;
    private IFuncionalidadeDAO funcionalidadeDAO;

    public FuncionalidadeRepository() {
        daoUtil = DaoUtil.getInstance();
        funcionalidadeDAO = daoUtil.getFuncionalidadeDao();
    }

    @Override
    public void adicionarFuncionalidade(Funcionalidade funcionalidade) {
        funcionalidadeDAO.inserir(funcionalidade);
    }

    @Override
    public void removerFuncionalidade(int id) {
        funcionalidadeDAO.excluir(id);
    }

    @Override
    public Funcionalidade buscarPorId(int id) {
        return funcionalidadeDAO.buscarPorId(id);
    }

    @Override
    public void atualizarFuncionalidade(Funcionalidade funcionalidade) {
        funcionalidadeDAO.atualizar(funcionalidade);
    }
}
