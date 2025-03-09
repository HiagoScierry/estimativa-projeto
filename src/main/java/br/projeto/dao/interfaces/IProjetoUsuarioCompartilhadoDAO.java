/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.projeto.dao.interfaces;

import java.util.List;

/**
 *
 * @author Cauã
 */
public interface IProjetoUsuarioCompartilhadoDAO {
    void compartilharProjetoComUsuario(int projetoId, int usuarioId, boolean isCriador);
    List<Integer> listarUsuariosPorProjeto(int projetoId);
    List<Integer> listarProjetosPorUsuario(int usuarioId);
    void removerCompartilhamento(int projetoId, int usuarioId);
    boolean isCriador(int projetoId, int usuarioId);
}
