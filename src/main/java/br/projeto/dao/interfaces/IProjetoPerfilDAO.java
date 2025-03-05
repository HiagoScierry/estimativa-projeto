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
public interface IProjetoPerfilDAO {
    void associarPerfilaProjeto(int projetoId, int perfilId);
    List<Perfil> listarPerfisPorProjeto(int projetoId);
    void atualizarPerfisProjeto(int projetoId, List<Perfil> perfis);
}
