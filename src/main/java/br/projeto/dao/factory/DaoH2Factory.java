package br.projeto.dao.factory;

import br.projeto.config.database.H2Connection;
import br.projeto.dao.interfaces.*;
import br.projeto.dao.h2.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoH2Factory implements IDAOFactory {
    public DaoH2Factory() throws Exception {
        try (Connection connection = H2Connection.getConexao(); Statement stmt = connection.createStatement()) {

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
        return new CustoAdicionalH2Dao();
    }

    @Override
    public IFuncionalidadeDAO getFuncionalidadeDao() throws SQLException, Exception {
        return new FuncionalidadeH2Dao();
    }

    @Override
    public IPerfilDAO getPerfilDao() throws SQLException, Exception {
        return new PerfilH2Dao();
    }

    @Override
    public IProjetoCustoAdicionalDAO getProjetoCustoAdicional() throws SQLException, Exception {
        return new ProjetoCustoAdicionalH2Dao();
    }

    @Override
    public IProjetoDAO getProjetoDao() throws SQLException, Exception {
        return new ProjetoH2Dao();
    }

    @Override
    public IProjetoFuncionalidadeDAO getProjetoFuncionalidadeDao() throws SQLException, Exception {
        return new ProjetoFuncionalidadeH2Dao();
    }

    @Override
    public IProjetoPerfilDAO getProjetoPerfil() throws SQLException, Exception {
        return new ProjetoPerfilH2Dao();
    }

    @Override
    public IUsuarioDAO getUsuarioDao() throws SQLException, Exception {
        return new UsuarioH2Dao();
    }

    @Override
    public INivelUIDAO getNivelUIDao() throws SQLException, Exception {
        return new NivelUIH2Dao();
    }

    @Override
    public IProjetoUsuarioCompartilhadoDAO getProjetoUsuarioCompartilhadoDao() throws SQLException, Exception {
        return new ProjetoUsuarioCompartilhadoH2Dao();
    }

    @Override
    public IEstimativaDAO getEstimativaDAO() throws SQLException, Exception {
        return new EstimativaH2Dao();
    }

}
