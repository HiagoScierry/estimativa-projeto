
package br.projeto.dao.h2;

import br.projeto.config.database.H2Connection;
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
public class PerfilH2Dao implements IPerfilDAO {

    private Connection connection;
    private FuncinamentoH2Dao funcionalidadeDao;

    public PerfilH2Dao() throws Exception {
        this.connection = H2Connection.getConexao();
        this.funcionalidadeDao = new FuncinamentoH2Dao();
    }

    @Override
    public void inserir(Perfil perfil) {
        String sql = "INSERT INTO Perfil (nome, nivelUI) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, perfil.getNome());
            stmt.setString(2, perfil.getNivelUI());
            stmt.executeUpdate();

            int perfilId = getPerfilIdByNome(perfil.getNome());
            funcionalidadeDao.inserirFuncionalidades(perfil.getFuncionalidades(), perfilId);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir perfil: " + e.getMessage());
        }
    }

    @Override
    public Perfil buscarPorId(int id) {
        String sql = "SELECT * FROM Perfil WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                List<Funcionalidade> funcionalidades = funcionalidadeDao.buscarFuncionalidadesPorPerfilId(id);
                return new Perfil(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    funcionalidades,
                    rs.getString("nivelUI")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar perfil por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Perfil> listarTodos() {
        String sql = "SELECT * FROM Perfil";
        List<Perfil> perfis = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                List<Funcionalidade> funcionalidades = funcionalidadeDao.buscarFuncionalidadesPorPerfilId(rs.getInt("id"));
                perfis.add(new Perfil(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    funcionalidades,
                    rs.getString("nivelUI")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar todos os perfis: " + e.getMessage());
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

            funcionalidadeDao.atualizarFuncionalidades(perfil.getFuncionalidades(), perfil.getId());
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar perfil: " + e.getMessage());
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM Perfil WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            funcionalidadeDao.excluirFuncionalidadesPorPerfilId(id);
        } catch (SQLException e) {
            System.out.println("Erro ao excluir perfil: " + e.getMessage());
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
            System.out.println("Erro ao obter ID do perfil pelo nome: " + e.getMessage());
        }
        return -1;
    }
}