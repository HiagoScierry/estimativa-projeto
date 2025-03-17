
package br.projeto.dao.sqlite;

import br.projeto.config.database.sqlite.SQLiteConnection;
import br.projeto.dao.interfaces.IPerfilDAO;
import br.projeto.model.Funcionalidade;
import br.projeto.model.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hiago
 */
public class PerfilSQLiteDao implements IPerfilDAO {

    private Connection connection;
    private FuncionalidadeSQLiteDao funcionalidadeDao;

    public PerfilSQLiteDao() throws Exception {
        this.connection = SQLiteConnection.getConexao();
        this.funcionalidadeDao = new FuncionalidadeSQLiteDao();
    }

    @Override
    public void inserir(Perfil perfil) {
        String sql = "INSERT INTO Perfil (nome, nivelUI) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, perfil.getNome());
            stmt.setString(2, perfil.getNivelUI());
            stmt.executeUpdate();

            // Obtendo o ID do perfil recém-criado
            int perfilId = getPerfilIdByNome(perfil.getNome());

            // Inserindo todas as funcionalidades associadas ao perfil
            for (Funcionalidade func : perfil.getFuncionalidades()) {
                funcionalidadeDao.inserir(func);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir perfil: " + e.getMessage(), e);
        }
    }

    @Override
    public Perfil buscarPorId(int id) {
        String sql = "SELECT * FROM Perfil WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                List<Funcionalidade> funcionalidades = (List<Funcionalidade>) funcionalidadeDao.buscarPorId(id);
                return new Perfil(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    funcionalidades,
                    rs.getString("nivelUI")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar perfil por ID: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Perfil> listarTodos() {
        String sql = "SELECT * FROM Perfil";
        List<Perfil> perfis = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                List<Funcionalidade> funcionalidades = new ArrayList<>();
                perfis.add(new Perfil(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    funcionalidades,
                    rs.getString("nivelUI")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar perfis: " + e.getMessage(), e);
        }
        return perfis;
    }

    @Override
    public void atualizar(Perfil perfil) {
        String sql = "UPDATE Perfil SET nome = ?, nivelUI = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, perfil.getNome());
            stmt.setString(2, perfil.getNivelUI());
            stmt.setInt(3, perfil.getId());
            stmt.executeUpdate();

//            funcionalidadeDao.atualizarFuncionalidades(perfil.getFuncionalidades(), perfil.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar perfil: " + e.getMessage(), e);
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM Perfil WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

//            funcionalidadeDao.excluirFuncionalidadesPorPerfilId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir perfil: " + e.getMessage(), e);
        }
    }

    private int getPerfilIdByNome(String nome) {
        String sql = "SELECT id FROM Perfil WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter ID do perfil pelo nome: " + e.getMessage(), e);
        }
        return -1;
    }
}