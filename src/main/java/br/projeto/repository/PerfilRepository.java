package br.projeto.repository;

import br.projeto.dao.DaoUtil;
import br.projeto.dao.interfaces.IPerfilDAO;
import br.projeto.model.Perfil;
import br.projeto.repository.interfaces.IPerfilRepository;
import java.util.List;

public class PerfilRepository implements IPerfilRepository {

    private DaoUtil daoUtil;
    private IPerfilDAO perfilDAO;

    public PerfilRepository() {
        daoUtil = DaoUtil.getInstance();
        perfilDAO = daoUtil.getPerfilDao();
    }

    @Override
    public void adicionarPerfil(Perfil perfil) {
        perfilDAO.inserir(perfil);
    }

    @Override
    public Perfil buscarPorId(int id) {
        return perfilDAO.buscarPorId(id);
    }

    @Override
    public List<Perfil> listarTodos() {
        return perfilDAO.listarTodos();
    }
}