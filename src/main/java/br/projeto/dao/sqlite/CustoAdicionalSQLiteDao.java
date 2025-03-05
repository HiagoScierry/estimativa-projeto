/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.sqlite;

import br.projeto.config.database.SQLiteConnection;
import br.projeto.dao.interfaces.ICustoAdicionalDAO;
import br.projeto.model.CustoAdicional;

/**
 *
 * @author hiago
 */
import java.sql.*;

public class CustoAdicionalSQLiteDao implements ICustoAdicionalDAO {

    private Connection connection;

    public CustoAdicionalSQLiteDao() throws Exception {
        this.connection = SQLiteConnection.getConexao();
    }

    @Override
    public void inserir(CustoAdicional custo) {
        String sql = "INSERT INTO CustoAdicional (descricao, valor) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, custo.getDescricao());
            stmt.setDouble(2, custo.getValor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir custo adicional: " + e.getMessage());
        }
    }

    @Override
    public CustoAdicional buscarPorId(int id) {
        String sql = "SELECT * FROM CustoAdicional WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CustoAdicional(
                    rs.getInt("id"),
                    rs.getString("descricao"),
                    rs.getDouble("valor")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar custo adicional por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void atualizar(CustoAdicional custo) {
        String sql = "UPDATE CustoAdicional SET descricao = ?, valor = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, custo.getDescricao());
            stmt.setDouble(2, custo.getValor());
            stmt.setInt(3, custo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar custo adicional: " + e.getMessage());
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM CustoAdicional WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir custo adicional: " + e.getMessage());
        }
    }
}
