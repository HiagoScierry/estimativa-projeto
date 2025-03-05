/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.factory;

import br.projeto.dao.interfaces.ICustoAdicionalDAO;
import br.projeto.dao.interfaces.IFuncionalidadeDAO;
import br.projeto.dao.interfaces.INivelUIDAO;
import br.projeto.dao.interfaces.IPerfilDAO;
import br.projeto.dao.interfaces.IProjetoCustoAdicionalDAO;
import br.projeto.dao.interfaces.IProjetoDAO;
import br.projeto.dao.interfaces.IProjetoFuncionalidadeDAO;
import br.projeto.dao.interfaces.IProjetoPerfilDAO;
import br.projeto.dao.interfaces.IUsuarioDAO;
import java.sql.SQLException;

/**
 *
 * @author hiago
 */
public interface IDAOFactory {

    public ICustoAdicionalDAO getCustoAdicionalDao() throws SQLException, Exception;

    public IFuncionalidadeDAO getFuncionalidadeDao() throws SQLException, Exception;

    public IPerfilDAO getPerfilDao() throws SQLException, Exception;

    public IProjetoCustoAdicionalDAO getProjetoCustoAdicional() throws SQLException, Exception;

    public IProjetoDAO getProjetoDao() throws SQLException, Exception;

    public IProjetoFuncionalidadeDAO getProjetoFuncionalidadeDao() throws SQLException, Exception;

    public IProjetoPerfilDAO getProjetoPerfil() throws SQLException, Exception;

    public IUsuarioDAO getUsuarioDao() throws SQLException, Exception;
    
    public INivelUIDAO getNivelUIDao() throws SQLException, Exception;
}
