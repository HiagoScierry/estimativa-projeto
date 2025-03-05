package br.projeto.repository;

import br.projeto.dao.DaoUtil;
import br.projeto.dao.interfaces.INivelUIDAO;
import br.projeto.model.NivelUI;
import br.projeto.repository.interfaces.INivelUIRepository;
import java.util.List;

public class NivelUIRepository implements INivelUIRepository {

    private DaoUtil daoUtil;
    private INivelUIDAO nivelUIDAO;

    public NivelUIRepository() {
        daoUtil = DaoUtil.getInstance();
        nivelUIDAO = daoUtil.getNivelUIDao();
    }

    @Override
    public void adicionarNivelUI(NivelUI nivelUI) {
        nivelUIDAO.inserir(nivelUI);
    }

    @Override
    public void removerNivelUI(int id) {
        nivelUIDAO.excluir(id);
    }

    @Override
    public NivelUI buscarPorId(int id) {
        return nivelUIDAO.buscarPorId(id);
    }

    @Override
    public void atualizarNivelUI(NivelUI nivelUI) {
        nivelUIDAO.atualizar(nivelUI);
    }

    @Override
    public List<NivelUI> listarTodos() {
        return nivelUIDAO.listarTodos();
    }
}