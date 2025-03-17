/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.projeto.repository.interfaces;

import br.projeto.model.Estimativa;

/**
 *
 * @author Cauã
 */
public interface IEstimativaRepository {
    void adicionarEstimativa(Estimativa estimativa);
    void removerEstimativa(int id);
    Estimativa buscarPorId(int id);
    void atualizarEstimativa(Estimativa estimativa);
}