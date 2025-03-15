/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.sqlite;

import br.projeto.config.database.SQLiteConnection;
import br.projeto.dao.interfaces.IProjetoUsuarioCompartilhadoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cauã
 */
public class ProjetoUsuarioCompartilhadoSQLiteDao implements IProjetoUsuarioCompartilhadoDAO {

    private Connection connection;

    public ProjetoUsuarioCompartilhadoSQLiteDao() throws Exception {
        this.connection = SQLiteConnection.getConexao();
    }

    @Override
    public void compartilharProjetoComUsuario(int projetoId, int usuarioId, boolean isCriador) {
        String sql = "INSERT INTO ProjetoUsuarioCompartilhado (projetoId, usuarioId, isCriador) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projetoId);
            stmt.setInt(2, usuarioId);
            stmt.setBoolean(3, isCriador);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao compartilhar projeto com usuário: " + e.getMessage());
        }
    }

    @Override
    public List<Integer> listarUsuariosPorProjeto(int projetoId) {
        List<Integer> usuarios = new ArrayList<>();
        String sql = "SELECT usuarioId FROM ProjetoUsuarioCompartilhado WHERE projetoId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projetoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                usuarios.add(rs.getInt("usuarioId"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários do projeto: " + e.getMessage());
        }

        return usuarios;
    }

    @Override
    public List<Integer> listarProjetosPorUsuario(int usuarioId) {
        List<Integer> projetos = new ArrayList<>();
        String sql = "SELECT projetoId FROM ProjetoUsuarioCompartilhado WHERE usuarioId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                projetos.add(rs.getInt("projetoId"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar projetos do usuário: " + e.getMessage());
        }

        return projetos;
    }

    @Override
    public void removerCompartilhamento(int projetoId, int usuarioId) {
        String sql = "DELETE FROM ProjetoUsuarioCompartilhado WHERE projetoId = ? AND usuarioId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projetoId);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao remover compartilhamento do projeto: " + e.getMessage());
        }
    }

    @Override
    public boolean isCriador(int projetoId, int usuarioId) {
        String sql = "SELECT isCriador FROM ProjetoUsuarioCompartilhado WHERE projetoId = ? AND usuarioId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projetoId);
            stmt.setInt(2, usuarioId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("isCriador");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar se usuário é criador do projeto: " + e.getMessage());
        }
        return false;
    }
    
    @Override
    public boolean verificarSeProjetoJaCompartilhado(int projetoId, int usuarioId) {
        String sql = "SELECT COUNT(*) FROM ProjetoUsuarioCompartilhado WHERE projetoId = ? AND usuarioId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projetoId);
            stmt.setInt(2, usuarioId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar se projeto já foi compartilhado: " + e.getMessage());
        }
        return false;
    }
}
