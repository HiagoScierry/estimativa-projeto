package br.projeto.repository;

import br.projeto.dao.DaoUtil;
import br.projeto.dao.interfaces.ICustoAdicionalDAO;
import br.projeto.model.CustoAdicional;
import br.projeto.repository.interfaces.ICustosAdicionaisRepository;

public class CustosAdicionaisRepository implements ICustosAdicionaisRepository {

    private DaoUtil daoUtil;
    private ICustoAdicionalDAO custoAdicionalDAO;

    public CustosAdicionaisRepository() {
        daoUtil = DaoUtil.getInstance();
        custoAdicionalDAO = daoUtil.getCustoAdicionalDao();
    }

    @Override
    public void adicionarCusto(CustoAdicional custo) {
        custoAdicionalDAO.inserir(custo);
    }

    @Override
    public void removerCusto(int id) {
        custoAdicionalDAO.excluir(id);
    }

    @Override
    public CustoAdicional buscarPorId(int id) {
        return custoAdicionalDAO.buscarPorId(id);
    }

    @Override
    public void atualizarCusto(CustoAdicional custo) {
        custoAdicionalDAO.atualizar(custo);
    }
}