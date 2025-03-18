
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.h2;

import br.projeto.config.database.h2.H2Connection;
import br.projeto.dao.interfaces.IProjetoPerfilDAO;
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
public class ProjetoPerfilH2Dao implements IProjetoPerfilDAO {

    private Connection connection;

    public ProjetoPerfilH2Dao() throws Exception {
        this.connection = H2Connection.getConexao();
    }

    @Override
    public void associarPerfilaProjeto(int projetoId, int perfilId) {
        String sql = "INSERT INTO ProjetoPerfil (projetoId, perfilId) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projetoId);
            stmt.setInt(2, perfilId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao associar perfil ao projeto: " + e.getMessage());
        }
    }

    @Override
    public List<Perfil> listarPerfisPorProjeto(int projetoId) {
        String sql = "SELECT p.id AS perfilId, p.nome AS perfilNome, p.nivelUI AS perfilNivelUI, "
                   + "f.id AS funcId, f.nome AS funcNome, f.horasEstimadas AS funcHorasEstimadas, f.plataforma AS funcPlataforma "
                   + "FROM Perfil p "
                   + "LEFT JOIN Funcionalidade f ON f.perfilId = p.id "
                   + "LEFT JOIN ProjetoPerfil pp ON pp.perfilId = p.id "
                   + "WHERE pp.projetoId = ?";

        List<Perfil> perfis = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projetoId);
            ResultSet rs = stmt.executeQuery();

            List<Funcionalidade> funcionalidades = new ArrayList<>();
            while (rs.next()) {
                int perfilId = rs.getInt("perfilId");
                String perfilNome = rs.getString("perfilNome");
                String perfilNivelUI = rs.getString("perfilNivelUI");

                int funcionalidadeId = rs.getInt("funcId");
                if (funcionalidadeId > 0) {
                    String funcionalidadeNome = rs.getString("funcNome");
                    int horasEstimadas = rs.getInt("funcHorasEstimadas");
                    String plataforma = rs.getString("funcPlataforma");

                    Funcionalidade funcionalidade = new Funcionalidade(funcionalidadeId, funcionalidadeNome, horasEstimadas, plataforma);
                    funcionalidades.add(funcionalidade);
                }

                Perfil perfilExistente = null;
                for (Perfil p : perfis) {
                    if (p.getId() == perfilId) {
                        perfilExistente = p;
                        break;
                    }
                }

                if (perfilExistente == null) {
                    Perfil perfil = new Perfil(perfilId, perfilNome, funcionalidades, perfilNivelUI);
                    perfis.add(perfil);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar perfis do projeto: " + e.getMessage());
        }
        return perfis;
    }

    @Override
    public void atualizarPerfisProjeto(int projetoId, List<Perfil> perfis) {
        String deleteSql = "DELETE FROM ProjetoPerfil WHERE projetoId = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, projetoId);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir perfis antigos do projeto: " + e.getMessage());
        }

        String insertSql = "INSERT INTO ProjetoPerfil (projetoId, perfilId) VALUES (?, ?)";
        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
            for (Perfil perfil : perfis) {
                insertStmt.setInt(1, projetoId);
                insertStmt.setInt(2, perfil.getId());
                insertStmt.addBatch();
            }
            insertStmt.executeBatch();
        } catch (SQLException e) {
            System.out.println("Erro ao associar novos perfis ao projeto: " + e.getMessage());
        }
    }
}
