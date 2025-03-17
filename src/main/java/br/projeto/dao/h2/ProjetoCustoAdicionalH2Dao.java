/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.h2;

import br.projeto.config.database.H2Connection;
import br.projeto.dao.interfaces.IProjetoCustoAdicionalDAO;
import br.projeto.model.CustoAdicional;
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
public class ProjetoCustoAdicionalH2Dao implements IProjetoCustoAdicionalDAO {

    private Connection connection;

    public ProjetoCustoAdicionalH2Dao() throws Exception {
        this.connection = H2Connection.getConexao();
    }

    @Override
    public void associarProjetoCustoAdicional(int projetoId, int custoAdicionalId) {
        String sql = "INSERT INTO ProjetoCustoAdicional (projetoId, custoAdicionalId) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projetoId);
            stmt.setInt(2, custoAdicionalId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao associar custo adicional ao projeto: " + e.getMessage());
        }
    }

    @Override
    public List<CustoAdicional> listarCustosAdicionaisPorProjeto(int projetoId) {
        List<CustoAdicional> custosAdicionais = new ArrayList<>();
        String sql = "SELECT c.id, c.descricao, c.valor "
                   + "FROM CustoAdicional c "
                   + "JOIN ProjetoCustoAdicional pca ON c.id = pca.custoAdicionalId "
                   + "WHERE pca.projetoId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, projetoId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CustoAdicional custoAdicional = new CustoAdicional(
                    rs.getInt("id"),
                    rs.getString("descricao"),
                    rs.getDouble("valor")
                );
                custosAdicionais.add(custoAdicional);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar custos adicionais do projeto: " + e.getMessage());
        }

        return custosAdicionais;
    }

    public void atualizarCustosAdicionaisProjeto(int projetoId, List<CustoAdicional> custosAdicionais) {
        String deleteSql = "DELETE FROM ProjetoCustoAdicional WHERE projetoId = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, projetoId);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir custos adicionais antigos do projeto: " + e.getMessage());
        }

        String insertSql = "INSERT INTO ProjetoCustoAdicional (projetoId, custoAdicionalId) VALUES (?, ?)";
        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
            for (CustoAdicional custo : custosAdicionais) {
                insertStmt.setInt(1, projetoId);
                insertStmt.setInt(2, custo.getId());
                insertStmt.addBatch();
            }
            insertStmt.executeBatch();
        } catch (SQLException e) {
            System.out.println("Erro ao associar novos custos adicionais ao projeto: " + e.getMessage());
        }
    }
}


