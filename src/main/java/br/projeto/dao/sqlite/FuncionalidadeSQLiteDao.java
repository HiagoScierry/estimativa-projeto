/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.sqlite;

import br.projeto.config.database.sqlite.SQLiteConnection;
import br.projeto.dao.interfaces.IFuncionalidadeDAO;
import br.projeto.model.Funcionalidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hiago
 */
public class FuncionalidadeSQLiteDao implements IFuncionalidadeDAO {

    private Connection connection;

    public FuncionalidadeSQLiteDao() throws Exception {
        this.connection = SQLiteConnection.getConexao();
    }

    @Override
    public void inserir(Funcionalidade funcionalidade) {
        String sql = "INSERT INTO Funcionalidade (nome, horasEstimadas, plataforma) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, funcionalidade.getNome());
            stmt.setInt(2, funcionalidade.getHorasEstimadas());
            stmt.setString(3, funcionalidade.getPlataforma());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                funcionalidade.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir funcionalidade: " + e.getMessage());
        }
    }

    @Override
    public Funcionalidade buscarPorId(int id) {
        String sql = "SELECT * FROM Funcionalidade WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Funcionalidade(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("horasEstimadas"),
                    rs.getString("plataforma")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionalidade por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void atualizar(Funcionalidade funcionalidade) {
        String sql = "UPDATE Funcionalidade SET nome = ?, horasEstimadas = ?, plataforma = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, funcionalidade.getNome());
            stmt.setInt(2, funcionalidade.getHorasEstimadas());
            stmt.setString(3, funcionalidade.getPlataforma());
            stmt.setInt(4, funcionalidade.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionalidade: " + e.getMessage());
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM Funcionalidade WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionalidade: " + e.getMessage());
        }
    }
}
