/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.projeto.dao.interfaces;

import br.projeto.model.Estimativa;

/**
 *
 * @author Cauã
 */
public interface IEstimativaDAO {
    void inserir(Estimativa estimativa);
    void atualizar(Estimativa estimativa);
    void excluir(int id);
    Estimativa buscarPorId(int id);
}