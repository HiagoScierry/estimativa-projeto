/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.h2;

import br.projeto.config.database.h2.H2Connection;
import br.projeto.dao.DaoUtil;
import br.projeto.dao.interfaces.IProjetoDAO;
import br.projeto.model.CustoAdicional;
import br.projeto.model.Estimativa;
import br.projeto.model.Funcionalidade;
import br.projeto.model.NivelUI;
import br.projeto.model.Perfil;
import br.projeto.model.Projeto;
import br.projeto.singleton.UsuarioSingleton;

/**
 *
 * @author hiago
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjetoH2Dao implements IProjetoDAO {
    private Connection connection;
    private DaoUtil daoUtil;

    public ProjetoH2Dao() throws Exception {
        this.connection = H2Connection.getConexao();
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

            daoUtil.getProjetoUsuarioCompartilhadoDao().compartilharProjetoComUsuario(
                    projeto.getId(),
                    UsuarioSingleton.getInstance().getUsuario().getId(),
                    true);

            // for (Perfil perfil : projeto.getPerfis()) {
            //     daoUtil.getProjetoPerfilDao().associarPerfilaProjeto(projeto.getId(), perfil.getId());
            // }

            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesWebBackend()) {
                daoUtil.getFuncionalidadeDao().inserir(funcionalidade);
                daoUtil.getProjetoFuncionalidadeDao().associarProjetoFuncionalidade(projeto.getId(), funcionalidade.getId());
            }
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesIOS()) {
                daoUtil.getFuncionalidadeDao().inserir(funcionalidade);
                daoUtil.getProjetoFuncionalidadeDao().associarProjetoFuncionalidade(projeto.getId(), funcionalidade.getId());
            }
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesAndroid()) {
                daoUtil.getFuncionalidadeDao().inserir(funcionalidade);
                daoUtil.getProjetoFuncionalidadeDao().associarProjetoFuncionalidade(projeto.getId(), funcionalidade.getId());
            }

            for (CustoAdicional custo : projeto.getCustosAdicionais()) {
                daoUtil.getCustoAdicionalDao().inserir(custo);
                daoUtil.getProjetoCustoAdicionalDao().associarProjetoCustoAdicional(projeto.getId(), custo.getId());
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
                Projeto projeto = new Projeto();

                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDataCriacao(rs.getString("dataCriacao"));
                projeto.setStatus(rs.getString("status"));
                projeto.setCompartilhado(rs.getBoolean("compartilhado"));

                NivelUI nivelUI = daoUtil.getNivelUIDao().buscarPorId(rs.getInt("nivelUIId"));
                projeto.setNivelUI(nivelUI);
                Estimativa estimativa = daoUtil.getEstimativaDao().buscarPorId(rs.getInt("estimativaId"));
                projeto.setEstimativa(estimativa);
                List<Perfil> perfis = daoUtil.getProjetoPerfilDao().listarPerfisPorProjeto(rs.getInt("id"));
                projeto.setPerfis(perfis);
                List<Funcionalidade> funcionalidadesWebBackend = daoUtil.getProjetoFuncionalidadeDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "WEB/BACKEND");
                List<Funcionalidade> funcionalidadesIOS =  daoUtil.getProjetoFuncionalidadeDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "IOS");
                List<Funcionalidade> funcionalidadesAndroid =  daoUtil.getProjetoFuncionalidadeDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "ANDROID");

                projeto.setFuncionalidadesWebBackend(funcionalidadesWebBackend);
                projeto.setFuncionalidadesIOS(funcionalidadesIOS);
                projeto.setFuncionalidadesAndroid(funcionalidadesAndroid);

                List<CustoAdicional> custosAdicionais = daoUtil.getProjetoCustoAdicionalDao().listarCustosAdicionaisPorProjeto(rs.getInt("id"));
                projeto.setCustosAdicionais(custosAdicionais);

                return projeto;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar o projeto por ID: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(ProjetoH2Dao.class.getName()).log(Level.SEVERE, null, ex);
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
                Projeto projeto = new Projeto();

                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDataCriacao(rs.getString("dataCriacao"));
                projeto.setStatus(rs.getString("status"));
                projeto.setCompartilhado(rs.getBoolean("compartilhado"));

                NivelUI nivelUI = daoUtil.getNivelUIDao().buscarPorId(rs.getInt("nivelUIId"));
                projeto.setNivelUI(nivelUI);
                Estimativa estimativa = daoUtil.getEstimativaDao().buscarPorId(rs.getInt("estimativaId"));
                projeto.setEstimativa(estimativa);
                List<Perfil> perfis = daoUtil.getProjetoPerfilDao().listarPerfisPorProjeto(rs.getInt("id"));
                projeto.setPerfis(perfis);
                List<Funcionalidade> funcionalidadesWebBackend = daoUtil.getProjetoFuncionalidadeDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "WEB/BACKEND");
                List<Funcionalidade> funcionalidadesIOS =  daoUtil.getProjetoFuncionalidadeDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "IOS");
                List<Funcionalidade> funcionalidadesAndroid =  daoUtil.getProjetoFuncionalidadeDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "ANDROID");

                projeto.setFuncionalidadesWebBackend(funcionalidadesWebBackend);
                projeto.setFuncionalidadesIOS(funcionalidadesIOS);
                projeto.setFuncionalidadesAndroid(funcionalidadesAndroid);

                List<CustoAdicional> custosAdicionais = daoUtil.getProjetoCustoAdicionalDao().listarCustosAdicionaisPorProjeto(rs.getInt("id"));
                projeto.setCustosAdicionais(custosAdicionais);


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
                Projeto projeto = new Projeto();

                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDataCriacao(rs.getString("dataCriacao"));
                projeto.setStatus(rs.getString("status"));
                projeto.setCompartilhado(rs.getBoolean("compartilhado"));
                projeto.setPercentualImpostos(rs.getDouble("percentualImpostos"));
                projeto.setPercentualLucro(rs.getDouble("percentualLucro"));

                NivelUI nivelUI = daoUtil.getNivelUIDao().buscarPorId(rs.getInt("nivelUIId"));
                projeto.setNivelUI(nivelUI);
                Estimativa estimativa = daoUtil.getEstimativaDao().buscarPorId(rs.getInt("estimativaId"));
                projeto.setEstimativa(estimativa);
                List<Perfil> perfis = daoUtil.getProjetoPerfilDao().listarPerfisPorProjeto(rs.getInt("id"));
                projeto.setPerfis(perfis);
                List<Funcionalidade> funcionalidadesWebBackend = daoUtil.getProjetoFuncionalidadeDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "WEB/BACKEND");
                List<Funcionalidade> funcionalidadesIOS =  daoUtil.getProjetoFuncionalidadeDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "IOS");
                List<Funcionalidade> funcionalidadesAndroid =  daoUtil.getProjetoFuncionalidadeDao().listarFuncionalidadesPorProjeto(rs.getInt("id"), "ANDROID");

                projeto.setFuncionalidadesWebBackend(funcionalidadesWebBackend);
                projeto.setFuncionalidadesIOS(funcionalidadesIOS);
                projeto.setFuncionalidadesAndroid(funcionalidadesAndroid);

                List<CustoAdicional> custosAdicionais = daoUtil.getProjetoCustoAdicionalDao().listarCustosAdicionaisPorProjeto(rs.getInt("id"));
                projeto.setCustosAdicionais(custosAdicionais);


                projetos.add(projeto);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar projetos por usuário: " + e.getMessage());
        }
        return projetos;
    }

    @Override
    public void atualizar(Projeto projeto) {
        String sql = "UPDATE Projeto SET nome = ?, dataCriacao = ?, status = ?, compartilhado = ?, nivelUIId = ?, percentualImpostos = ?, percentualLucro = ?, estimativaId = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            daoUtil.getNivelUIDao().inserir(projeto.getNivelUI());
            daoUtil.getEstimativaDao().inserir(projeto.getEstimativa());

            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDataCriacao());
            stmt.setString(3, projeto.getStatus());
            stmt.setBoolean(4, projeto.isCompartilhado());
            stmt.setInt(5, projeto.getNivelUI() != null ? projeto.getNivelUI().getId() : 0);
            stmt.setDouble(6, projeto.getPercentualImpostos());
            stmt.setDouble(7, projeto.getPercentualLucro());
            stmt.setInt(8, projeto.getEstimativa().getId());
            stmt.setInt(9, projeto.getId());
            stmt.executeUpdate();

            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesWebBackend()) {
                daoUtil.getFuncionalidadeDao().inserir(funcionalidade);
                daoUtil.getProjetoFuncionalidadeDao().associarProjetoFuncionalidade(projeto.getId(), funcionalidade.getId());
            }
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesIOS()) {
                daoUtil.getFuncionalidadeDao().inserir(funcionalidade);
                daoUtil.getProjetoFuncionalidadeDao().associarProjetoFuncionalidade(projeto.getId(), funcionalidade.getId());
            }
            for (Funcionalidade funcionalidade : projeto.getFuncionalidadesAndroid()) {
                daoUtil.getFuncionalidadeDao().inserir(funcionalidade);
                daoUtil.getProjetoFuncionalidadeDao().associarProjetoFuncionalidade(projeto.getId(), funcionalidade.getId());
            }

            for (CustoAdicional custo : projeto.getCustosAdicionais()) {
                daoUtil.getCustoAdicionalDao().inserir(custo);
                daoUtil.getProjetoCustoAdicionalDao().associarProjetoCustoAdicional(projeto.getId(), custo.getId());
            }




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