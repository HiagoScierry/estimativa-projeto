    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.h2;

import br.projeto.config.database.h2.H2Connection;
import br.projeto.dao.interfaces.IUsuarioDAO;
import br.projeto.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author hiago
 */
public class UsuarioH2Dao implements IUsuarioDAO{

    private Connection connection;

    public UsuarioH2Dao() throws Exception {
        this.connection = H2Connection.getConexao();
    }

    @Override
    public void inserir(Usuario usuario){
        String sql = "INSERT INTO Usuario (nome, email, senha) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            ResultSet usuarioID = stmt.executeQuery(sql);

            System.out.println("Valor retornado da criaçao : " + usuarioID);
        } catch (Exception e) {
            System.out.println("Erro ao criar usuario");
        }
    }

    @Override
    public Optional<Usuario> buscarPorId(int id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
                return Optional.of(usuario);
            }
        } catch (Exception e) {
            System.out.println("Erro ao encontrar usuario: " + e.getMessage());
        }
        return Optional.empty();
    }


    @Override
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM Usuario";
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                ));
            }
        } catch (Exception e){
            System.out.println("Erro ao listar usuarios");
        }

        return usuarios;
    }

    @Override
    public Optional<Usuario> autenticar(String email, String senha) {
        String sql = "SELECT * FROM Usuario WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                ));
            }

        } catch (Exception e){
            System.out.println("Erro ao autenticar usuario");
        }

        return Optional.of(null);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        String sql = "SELECT * FROM Usuario WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                ));
            }
        } catch (Exception e){
            System.out.println("Erro ao encontrar usuario");
        }
        return null;
    }
}
