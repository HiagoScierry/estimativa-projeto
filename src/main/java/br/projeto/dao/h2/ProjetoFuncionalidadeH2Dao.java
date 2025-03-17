/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.h2;

import br.projeto.config.database.H2Connection;
import br.projeto.dao.interfaces.IProjetoFuncionalidadeDAO;
import br.projeto.model.Funcionalidade;
import br.projeto.model.Projeto;
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
public class ProjetoFuncionalidadeH2Dao implements IProjetoFuncionalidadeDAO {

    private Connection connection;

    public ProjetoFuncionalidadeH2Dao() throws Exception {
        this.connection = H2Connection.getConexao();
    }

    @Override
    public void associarProjetoFuncionalidade(int projetoId, int funcionalidadeId) {
        String sql = "INSERT INTO ProjetoFuncionalidade (projetoId, funcionalidadeId) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projetoId);
            stmt.setInt(2, funcionalidadeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao associar funcionalidade ao projeto: " + e.getMessage());
        }
    }

    @Override
    public List<Funcionalidade> listarFuncionalidadesPorProjeto(int projetoId, String tipo) {
        List<Funcionalidade> funcionalidades = new ArrayList<>();
        String sql = "SELECT f.id, f.nome, f.horasEstimadas, f.plataforma "
                   + "FROM Funcionalidade f "
                   + "JOIN ProjetoFuncionalidade pf ON f.id = pf.funcionalidadeId "
                   + "WHERE pf.projetoId = ? AND f.tipo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projetoId);
            stmt.setString(2, tipo);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionalidade funcionalidade = new Funcionalidade(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("horasEstimadas"),
                    rs.getString("plataforma")
                );
                funcionalidades.add(funcionalidade);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionalidades do projeto: " + e.getMessage());
        }

        return funcionalidades;
    }

    public void atualizarFuncionalidadesProjeto(int projetoId, Projeto projeto) {
        String deleteSql = "DELETE FROM ProjetoFuncionalidade WHERE projetoId = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, projetoId);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionalidades antigas do projeto: " + e.getMessage());
        }

        String insertSql = "INSERT INTO ProjetoFuncionalidade (projetoId, funcionalidadeId) VALUES (?, ?)";
        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesWebBackend()) {
                insertStmt.setInt(1, projetoId);
                insertStmt.setInt(2, funcionalidade.getId());
                insertStmt.addBatch();
            }
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesIOS()) {
                insertStmt.setInt(1, projetoId);
                insertStmt.setInt(2, funcionalidade.getId());
                insertStmt.addBatch();
            }
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesAndroid()) {
                insertStmt.setInt(1, projetoId);
                insertStmt.setInt(2, funcionalidade.getId());
                insertStmt.addBatch();
            }
            insertStmt.executeBatch();
        } catch (SQLException e) {
            System.out.println("Erro ao associar funcionalidades novas ao projeto: " + e.getMessage());
        }
    }
}