/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.interfaces;

import br.projeto.model.Perfil;
import java.util.List;

/**
 *
 * @author hiago
 */
public interface IPerfilDAO {
    void inserir(Perfil perfil);
    Perfil buscarPorId(int id);
    List<Perfil> listarTodos();    
    void atualizar(Perfil perfil);
    void excluir(int id);
}