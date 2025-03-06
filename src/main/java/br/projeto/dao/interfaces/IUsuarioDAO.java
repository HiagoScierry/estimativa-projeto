/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.interfaces;

import br.projeto.model.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author hiago
 */
public interface IUsuarioDAO {
    void inserir(Usuario usuario);
    Usuario buscarPorId(int id);
    Optional<Usuario> buscarPorEmail(String email);
    List<Usuario> listarTodos();
    Optional<Usuario> autenticar(String email, String senha);
}
