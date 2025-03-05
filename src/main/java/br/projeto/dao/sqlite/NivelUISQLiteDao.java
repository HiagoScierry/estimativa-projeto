/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.sqlite;

import br.projeto.config.database.SQLiteConnection;
import br.projeto.dao.interfaces.INivelUIDAO;
import br.projeto.model.NivelUI;
import java.util.List;

/**
 *
 * @author Cauã
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NivelUISQLiteDao implements INivelUIDAO {
    
    private Connection connection;

    public NivelUISQLiteDao() throws Exception {
        this.connection = SQLiteConnection.getConexao();
    }

    @Override
    public void inserir(NivelUI nivelUI) {
        String sql = "INSERT INTO NivelUI (nome, percentual) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nivelUI.getNome());
            stmt.setDouble(2, nivelUI.getPercentual());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir NivelUI: " + e.getMessage());
        }
    }

    @Override
    public NivelUI buscarPorId(int id) {
        String sql = "SELECT * FROM NivelUI WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new NivelUI(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("percentual")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar NivelUI por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<NivelUI> listarTodos() {
        String sql = "SELECT * FROM NivelUI";
        List<NivelUI> niveis = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                niveis.add(new NivelUI(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("percentual")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar todos os NivelUI: " + e.getMessage());
        }
        return niveis;
    }

    @Override
    public void atualizar(NivelUI nivelUI) {
        String sql = "UPDATE NivelUI SET nome = ?, percentual = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nivelUI.getNome());
            stmt.setDouble(2, nivelUI.getPercentual());
            stmt.setInt(3, nivelUI.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar NivelUI: " + e.getMessage());
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM NivelUI WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir NivelUI: " + e.getMessage());
        }
    }
}
