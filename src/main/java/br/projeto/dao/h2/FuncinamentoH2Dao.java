package br.projeto.dao.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.projeto.model.Funcionalidade;

public class FuncinamentoH2Dao {
    private Connection connection;

    public void inserirFuncionalidades(List<Funcionalidade> funcionalidades, int perfilId) {
        String sql = "INSERT INTO Funcionalidade (perfil_id, nome) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Funcionalidade funcionalidade : funcionalidades) {
                stmt.setInt(1, perfilId);
                stmt.setString(2, funcionalidade.getNome());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir funcionalidades: " + e.getMessage());
        }
    }

    public List<Funcionalidade> buscarFuncionalidadesPorPerfilId(int perfilId) {
        String sql = "SELECT * FROM Funcionalidade WHERE perfil_id = ?";
        List<Funcionalidade> funcionalidades = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, perfilId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                funcionalidades.add(new Funcionalidade(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("horasEstimadas"),
                    rs.getString("plataforma")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionalidades por perfil ID: " + e.getMessage());
        }
        return funcionalidades;
    }

    public void atualizarFuncionalidades(List<Funcionalidade> funcionalidades, int perfilId) {
        excluirFuncionalidadesPorPerfilId(perfilId);
        inserirFuncionalidades(funcionalidades, perfilId);
    }

    public void excluirFuncionalidadesPorPerfilId(int perfilId) {
        String sql = "DELETE FROM Funcionalidade WHERE perfil_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, perfilId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionalidades por perfil ID: " + e.getMessage());
        }
    }
}