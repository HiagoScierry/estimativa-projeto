/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.repository.interfaces;

import br.projeto.model.Usuario;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author hiago
 */
public interface IUsuarioRepository {
    Optional<Usuario> autenticar(String email, String senha);
    void cadatrarUsuario(Usuario usuario);
    Optional<Usuario> buscarPorEmail(String email);
    List<Usuario> buscarTodos();
    Optional<Usuario> buscarPorId(int id);
}
