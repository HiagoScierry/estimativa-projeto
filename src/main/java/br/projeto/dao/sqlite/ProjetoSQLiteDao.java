/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.sqlite;

import br.projeto.config.database.SQLiteConnection;
import br.projeto.dao.DaoUtil;
import br.projeto.dao.interfaces.IProjetoDAO;
import br.projeto.model.CustoAdicional;
import br.projeto.model.Funcionalidade;
import br.projeto.model.NivelUI;
import br.projeto.model.Perfil;
import br.projeto.model.Projeto;
import br.projeto.model.Usuario;

/**
 *
 * @author hiago
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjetoSQLiteDao implements IProjetoDAO {
    private Connection connection;
    private DaoUtil daoUtil;

    public ProjetoSQLiteDao() throws Exception {
        this.connection = SQLiteConnection.getConexao();
        this.daoUtil = DaoUtil.getInstance();
    }

    @Override
    public void inserir(Projeto projeto) {
        String sql = "INSERT INTO Projeto (nome, dataCriacao, status, compartilhado, nivelUIId, percentualImpostos, percentualLucro) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDataCriacao());
            stmt.setString(3, projeto.getStatus());
            stmt.setBoolean(4, projeto.isCompartilhado());
            stmt.setInt(5, projeto.getNivelUI() != null ? projeto.getNivelUI().getId() : 0);
            stmt.setDouble(6, projeto.getPercentualImpostos());
            stmt.setDouble(7, projeto.getPercentualLucro());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                projeto.setId(rs.getInt(1));
            }

            for (Perfil perfil : projeto.getPerfis()) {
                new ProjetoPerfilSQLiteDao().associarPerfilaProjeto(projeto.getId(), perfil.getId());
            }

            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesWebBackend()) {
                new ProjetoFuncionalidadeSQLiteDao().associarProjetoFuncionalidade(projeto.getId(), funcionalidade.getId());
            }
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesIOS()) {
                new ProjetoFuncionalidadeSQLiteDao().associarProjetoFuncionalidade(projeto.getId(), funcionalidade.getId());
            }
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesAndroid()) {
                new ProjetoFuncionalidadeSQLiteDao().associarProjetoFuncionalidade(projeto.getId(), funcionalidade.getId());
            }

            for (CustoAdicional custo : projeto.getCustosAdicionais()) {
                new ProjetoCustoAdicionalSQLiteDao().associarProjetoCustoAdicional(projeto.getId(), custo.getId());
            }

        } catch (Exception e) {
            System.out.println("Erro ao inserir o projeto: " + e.getMessage());
        }
    }

    @Override
    public Projeto buscarPorId(int id) {
        String sql = "SELECT * FROM Projeto WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario criador = new UsuarioSQLiteDao().buscarPorId(rs.getInt("criadorId"));
                Usuario compartilhadoPor = new UsuarioSQLiteDao().buscarPorId(rs.getInt("compartilhadoPorId"));
                NivelUI nivelUI = new NivelUISQLiteDao().buscarPorId(rs.getInt("nivelUIId"));

                List<Perfil> perfis = new ProjetoPerfilSQLiteDao().listarPerfisPorProjeto(id);

                List<Funcionalidade> funcionalidadesWebBackend = new ProjetoFuncionalidadeSQLiteDao().listarFuncionalidadesPorProjeto(id, "WEB/BACKEND");
                List<Funcionalidade> funcionalidadesIOS = new ProjetoFuncionalidadeSQLiteDao().listarFuncionalidadesPorProjeto(id, "IOS");
                List<Funcionalidade> funcionalidadesAndroid = new ProjetoFuncionalidadeSQLiteDao().listarFuncionalidadesPorProjeto(id, "ANDROID");

                List<CustoAdicional> custosAdicionais = new ProjetoCustoAdicionalSQLiteDao().listarCustosAdicionaisPorProjeto(id);

                Projeto projeto = new Projeto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("dataCriacao"),
                    rs.getString("status"),
                    rs.getBoolean("compartilhado"),
                    perfis,
                    funcionalidadesWebBackend,
                    funcionalidadesIOS,
                    funcionalidadesAndroid,
                    custosAdicionais,
                    nivelUI,
                    rs.getDouble("percentualImpostos"),
                    rs.getDouble("percentualLucro")
                );
                return projeto;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o projeto por ID: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(ProjetoSQLiteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Projeto> listarTodos() {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM Projeto";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NivelUI nivelUI = daoUtil.getNivelUIDao().buscarPorId(rs.getInt("nivelUIId"));

                List<Perfil> perfis = daoUtil.getProjetoPerfilDao().listarPerfisPorProjeto(rs.getInt("id"));

                List<Funcionalidade> funcionalidadesWebBackend = daoUtil.getProjetoFuncionalidadeDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "WEB/BACKEND");
                List<Funcionalidade> funcionalidadesIOS =  daoUtil.getProjetoFuncionalidadeDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "IOS");
                List<Funcionalidade> funcionalidadesAndroid =  daoUtil.getProjetoFuncionalidadeDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "ANDROID");

                List<CustoAdicional> custosAdicionais = daoUtil.getProjetoCustoAdicionalDao().listarCustosAdicionaisPorProjeto(rs.getInt("id"));

                Projeto projeto = new Projeto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("dataCriacao"),
                    rs.getString("status"),
                    rs.getBoolean("compartilhado"),
                    perfis,
                    funcionalidadesWebBackend,
                    funcionalidadesIOS,
                    funcionalidadesAndroid,
                    custosAdicionais,
                    nivelUI,
                    rs.getDouble("percentualImpostos"),
                    rs.getDouble("percentualLucro")
                );
                projetos.add(projeto);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar todos os projetos: " + e.getMessage());
        }
        return projetos;
    }

    @Override
    public List<Projeto> listarPorUsuario(int usuarioId) {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT *\n" +
                "FROM Projeto p\n" +
                "JOIN ProjetoUsuarioCompartilhado puc ON p.id = puc.projetoId\n" +
                "JOIN Usuario u ON puc.usuarioId = u.id\n" +
                "WHERE u.id = ?;";


        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NivelUI nivelUI = new NivelUISQLiteDao().buscarPorId(rs.getInt("nivelUIId"));

                List<Perfil> perfis = new ProjetoPerfilSQLiteDao().listarPerfisPorProjeto(rs.getInt("id"));

                List<Funcionalidade> funcionalidadesWebBackend = new ProjetoFuncionalidadeSQLiteDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "WEB/BACKEND");
                List<Funcionalidade> funcionalidadesIOS = new ProjetoFuncionalidadeSQLiteDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "IOS");
                List<Funcionalidade> funcionalidadesAndroid = new ProjetoFuncionalidadeSQLiteDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "ANDROID");

                List<CustoAdicional> custosAdicionais = new ProjetoCustoAdicionalSQLiteDao().listarCustosAdicionaisPorProjeto(rs.getInt("id"));

                Projeto projeto = new Projeto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("dataCriacao"),
                    rs.getString("status"),
                    rs.getBoolean("compartilhado"),
                    perfis,
                    funcionalidadesWebBackend,
                    funcionalidadesIOS,
                    funcionalidadesAndroid,
                    custosAdicionais,
                    nivelUI,
                    rs.getDouble("percentualImpostos"),
                    rs.getDouble("percentualLucro")
                );
                projetos.add(projeto);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar projetos por usuário: " + e.getMessage());
        }
        return projetos;
    }

    @Override
    public void atualizar(Projeto projeto) {
        String sql = "UPDATE Projeto SET nome = ?, dataCriacao = ?, status = ?, compartilhado = ?, nivelUIId = ?, percentualImpostos = ?, percentualLucro = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDataCriacao());
            stmt.setString(3, projeto.getStatus());
            stmt.setBoolean(4, projeto.isCompartilhado());
            stmt.setInt(5, projeto.getNivelUI() != null ? projeto.getNivelUI().getId() : 0);
            stmt.setDouble(6, projeto.getPercentualImpostos());
            stmt.setDouble(7, projeto.getPercentualLucro());
            stmt.setInt(8, projeto.getId());
            stmt.executeUpdate();

            new ProjetoPerfilSQLiteDao().atualizarPerfisProjeto(projeto.getId(), projeto.getPerfis());
            new ProjetoFuncionalidadeSQLiteDao().atualizarFuncionalidadesProjeto(projeto.getId(), projeto);
            new ProjetoCustoAdicionalSQLiteDao().atualizarCustosAdicionaisProjeto(projeto.getId(), projeto.getCustosAdicionais());

        } catch (Exception e) {
            System.out.println("Erro ao atualizar projeto: " + e.getMessage());
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM Projeto WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir projeto: " + e.getMessage());
        }
    }
}