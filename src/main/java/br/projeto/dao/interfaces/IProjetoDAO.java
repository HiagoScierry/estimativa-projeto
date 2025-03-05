/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.interfaces;

import br.projeto.model.Projeto;
import java.util.List;

/**
 *
 * @author hiago
 */
public interface IProjetoDAO {
    void inserir(Projeto usuario);
    Projeto buscarPorId(int id);
    List<Projeto> listarTodos();    
    List<Projeto> listarPorUsuario(int usuarioId);
    void atualizar(Projeto projeto);
    void excluir(int id);
}
