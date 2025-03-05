/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.interfaces;

import br.projeto.model.Funcionalidade;
import java.util.List;

/**
 *
 * @author hiago
 */
public interface IFuncionalidadeDAO {
    void inserir(Funcionalidade funcionalidade);
    Funcionalidade buscarPorId(int id);
    void atualizar(Funcionalidade funcionalidade);
    void excluir(int id);
}
