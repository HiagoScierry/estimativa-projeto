package br.projeto.repository;

import br.projeto.dao.DaoUtil;
import br.projeto.dao.interfaces.IProjetoDAO;
import br.projeto.model.Projeto;
import br.projeto.repository.interfaces.IProjetoRepository;
import java.util.List;

public class ProjetoRepository implements IProjetoRepository {

    private DaoUtil daoUtil;
    private IProjetoDAO projetoDAO;

    public ProjetoRepository() {
        daoUtil = DaoUtil.getInstance();
        projetoDAO = daoUtil.getProjetoDao();
    }

    @Override
    public void adicionarProjeto(Projeto projeto) {
        projetoDAO.inserir(projeto);
    }

    @Override
    public void removerProjeto(int id) {
        projetoDAO.excluir(id);
    }

    @Override
    public Projeto buscarPorId(int id) {
        return projetoDAO.buscarPorId(id);
    }

    @Override
    public void atualizarProjeto(Projeto projeto) {
        projetoDAO.atualizar(projeto);
    }

    @Override
    public List<Projeto> listarTodos() {
        return projetoDAO.listarTodos();
    }

    @Override
    public List<Projeto> listarPorUsuario(int usuarioId) {
        return projetoDAO.listarPorUsuario(usuarioId);
    }
}
