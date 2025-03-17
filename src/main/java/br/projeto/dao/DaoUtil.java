package br.projeto.dao;

import br.projeto.dao.factory.IDAOFactory;
import br.projeto.dao.interfaces.ICustoAdicionalDAO;
import br.projeto.dao.interfaces.IEstimativaDAO;
import br.projeto.dao.interfaces.IFuncionalidadeDAO;
import br.projeto.dao.interfaces.INivelUIDAO;
import br.projeto.dao.interfaces.IPerfilDAO;
import br.projeto.dao.interfaces.IProjetoCustoAdicionalDAO;
import br.projeto.dao.interfaces.IProjetoDAO;
import br.projeto.dao.interfaces.IProjetoFuncionalidadeDAO;
import br.projeto.dao.interfaces.IProjetoPerfilDAO;
import br.projeto.dao.interfaces.IProjetoUsuarioCompartilhadoDAO;
import br.projeto.dao.interfaces.IUsuarioDAO;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author hiago
 */
public class DaoUtil {

    private static DaoUtil daoSingleton;

    private ICustoAdicionalDAO custoAdicionalDao;
    private IFuncionalidadeDAO funcionalidadeDao;
    private IPerfilDAO perfilDao;
    private IProjetoCustoAdicionalDAO projetoCustoAdicionalDao;
    private IProjetoDAO projetoDao;
    private IProjetoFuncionalidadeDAO projetoFuncionalidadeDao;
    private IProjetoPerfilDAO projetoPerfilDao;
    private IUsuarioDAO usuarioDao;
    private INivelUIDAO nivelUIDao;
    private IProjetoUsuarioCompartilhadoDAO projetoUsuarioCompartilhadoDao;
    private IEstimativaDAO estimativaDao;

    private DaoUtil() throws SQLException, Exception {
    }

    private void configurar(IDAOFactory daoFactory) throws SQLException, Exception {
        this.custoAdicionalDao = daoFactory.getCustoAdicionalDao();
        this.funcionalidadeDao = daoFactory.getFuncionalidadeDao();
        this.perfilDao = daoFactory.getPerfilDao();
        this.projetoCustoAdicionalDao = daoFactory.getProjetoCustoAdicional();
        this.projetoDao = daoFactory.getProjetoDao();
        this.projetoFuncionalidadeDao = daoFactory.getProjetoFuncionalidadeDao();
        this.projetoPerfilDao = daoFactory.getProjetoPerfil();
        this.usuarioDao = daoFactory.getUsuarioDao();
        this.nivelUIDao = daoFactory.getNivelUIDao();
        this.projetoUsuarioCompartilhadoDao = daoFactory.getProjetoUsuarioCompartilhadoDao();
        this.estimativaDao = daoFactory.getEstimativaDAO();
    }

    public static void configureInstance(IDAOFactory daoFactory) throws Exception {
        if (daoSingleton == null) {
            daoSingleton = new DaoUtil();
        }
        
        daoSingleton.configurar(daoFactory);
    }
    
    public static DaoUtil getInstance(){
        return daoSingleton;
    }

    public ICustoAdicionalDAO getCustoAdicionalDao() {
        return custoAdicionalDao;
    }

    public IFuncionalidadeDAO getFuncionalidadeDao() {
        return funcionalidadeDao;
    }

    public IPerfilDAO getPerfilDao() {
        return perfilDao;
    }

    public IProjetoCustoAdicionalDAO getProjetoCustoAdicionalDao() {
        return projetoCustoAdicionalDao;
    }

    public IProjetoDAO getProjetoDao() {
        return projetoDao;
    }

    public IProjetoFuncionalidadeDAO getProjetoFuncionalidadeDao() {
        return projetoFuncionalidadeDao;
    }

    public IProjetoPerfilDAO getProjetoPerfilDao() {
        return projetoPerfilDao;
    }

    public IUsuarioDAO getUsuarioDao() {
        return usuarioDao;
    }

    public INivelUIDAO getNivelUIDao() {
        return nivelUIDao;
    }
    
    public IProjetoUsuarioCompartilhadoDAO getProjetoUsuarioCompartilhadoDao(){
        return projetoUsuarioCompartilhadoDao;
    }
    
    public IEstimativaDAO getEstimativaDao(){
        return estimativaDao;
    }
}
