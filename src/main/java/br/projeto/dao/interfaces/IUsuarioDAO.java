/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.interfaces;

import br.projeto.model.Usuario;
import java.util.List;

/**
 *
 * @author hiago
 */
public interface IUsuarioDAO {
    void inserir(Usuario usuario);
    Usuario buscarPorId(int id);
    List<Usuario> listarTodos();
}
