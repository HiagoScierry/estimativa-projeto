/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.factory;

import br.projeto.config.database.SQLiteConnection;
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
import br.projeto.dao.sqlite.CustoAdicionalSQLiteDao;
import br.projeto.dao.sqlite.EstimativaSQLiteDao;
import br.projeto.dao.sqlite.FuncionalidadeSQLiteDao;
import br.projeto.dao.sqlite.NivelUISQLiteDao;
import br.projeto.dao.sqlite.PerfilSQLiteDao;
import br.projeto.dao.sqlite.ProjetoCustoAdicionalSQLiteDao;
import br.projeto.dao.sqlite.ProjetoFuncionalidadeSQLiteDao;
import br.projeto.dao.sqlite.ProjetoPerfilSQLiteDao;
import br.projeto.dao.sqlite.ProjetoSQLiteDao;
import br.projeto.dao.sqlite.ProjetoUsuarioCompartilhadoSQLiteDao;
import br.projeto.dao.sqlite.UsuarioSQLiteDao;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hiago
 */
public class DaoSQLiteFactory implements IDAOFactory {

    public DaoSQLiteFactory() throws Exception {
        try (Connection connection = SQLiteConnection.getConexao(); Statement stmt = connection.createStatement()) {

            String sqlScript = new String(Files.readAllBytes(Paths.get("src/main/java/br/projeto/config/database/CREATE_DATABASE.sql")), StandardCharsets.UTF_8);
            String[] commands = sqlScript.split(";");

            connection.setAutoCommit(false); // Desativa o auto-commit

            for (String command : commands) {
                if (!command.trim().isEmpty()) {
                    stmt.execute(command);
                    System.out.println("Comando executado: " + command.trim());
                }
            }

            connection.commit(); // Commit da transação
            System.out.println("Banco de dados SQLite criado com sucesso!");
            
        } catch (SQLException e) {
            System.err.println("Erro ao criar o banco de dados: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public ICustoAdicionalDAO getCustoAdicionalDao() throws SQLException, Exception {
        return new CustoAdicionalSQLiteDao();
    }

    @Override
    public IFuncionalidadeDAO getFuncionalidadeDao() throws SQLException, Exception {
        return new FuncionalidadeSQLiteDao();
    }

    @Override
    public IPerfilDAO getPerfilDao() throws SQLException, Exception {
        return new PerfilSQLiteDao();
    }

    @Override
    public IProjetoCustoAdicionalDAO getProjetoCustoAdicional() throws SQLException, Exception {
        return new ProjetoCustoAdicionalSQLiteDao();
    }

    @Override
    public IProjetoDAO getProjetoDao() throws SQLException, Exception {
        return new ProjetoSQLiteDao();
    }

    @Override
    public IProjetoFuncionalidadeDAO getProjetoFuncionalidadeDao() throws SQLException, Exception {
        return new ProjetoFuncionalidadeSQLiteDao();
    }

    @Override
    public IProjetoPerfilDAO getProjetoPerfil() throws SQLException, Exception {
        return new ProjetoPerfilSQLiteDao();
    }

    @Override
    public IUsuarioDAO getUsuarioDao() throws SQLException, Exception {
        return new UsuarioSQLiteDao();
    }
    
    @Override
    public INivelUIDAO getNivelUIDao() throws SQLException, Exception {
        return new NivelUISQLiteDao();
    }

    @Override
    public IProjetoUsuarioCompartilhadoDAO getProjetoUsuarioCompartilhadoDao() throws SQLException, Exception {
        return new ProjetoUsuarioCompartilhadoSQLiteDao();
    }
    
    @Override
    public IEstimativaDAO getEstimativaDAO() throws SQLException, Exception {
        return new EstimativaSQLiteDao();
    }
}
