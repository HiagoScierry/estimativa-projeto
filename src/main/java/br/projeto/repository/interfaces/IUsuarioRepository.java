/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.repository.interfaces;

import br.projeto.model.Usuario;

/**
 *
 * @author hiago
 */
public interface IUsuarioRepository {
    boolean autenticar(String email, String senha);
     public void cadatrarUsuario(Usuario usuario);
        
}
