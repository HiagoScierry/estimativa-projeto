/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.h2;

import br.projeto.config.database.h2.H2Connection;
import br.projeto.dao.interfaces.IEstimativaDAO;
import br.projeto.model.Estimativa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;


/**
 *
 * @author Cauã
 */
public class EstimativaH2Dao implements IEstimativaDAO {

    private Connection connection;

    public EstimativaH2Dao() throws Exception {
        this.connection = H2Connection.getConexao();
    }

    @Override
    public void inserir(Estimativa estimativa) {
        String sql = "INSERT INTO Estimativa (custoTotal, tempoTotal, precoFinal) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, estimativa.getCustoTotal());
            stmt.setInt(2, estimativa.getTempoTotal());
            stmt.setDouble(3, estimativa.getPrecoFinal());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                estimativa.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Estimativa estimativa) {
        String sql = "UPDATE Estimativa SET custoTotal = ?, tempoTotal = ?, precoFinal = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, estimativa.getCustoTotal());
            stmt.setInt(2, estimativa.getTempoTotal());
            stmt.setDouble(3, estimativa.getPrecoFinal());
            stmt.setInt(4, estimativa.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM Estimativa WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Estimativa buscarPorId(int id) {
        String sql = "SELECT * FROM Estimativa WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Estimativa(
                    rs.getInt("id"),
                    rs.getDouble("custoTotal"),
                    rs.getInt("tempoTotal"),
                    rs.getDouble("precoFinal")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar Estimativa por ID: " + e.getMessage());
        }
        return null;
    }
}