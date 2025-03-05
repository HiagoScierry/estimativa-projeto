/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.projeto.dao.interfaces;

import br.projeto.model.NivelUI;
import java.util.List;

/**
 *
 * @author Cauã
 */
public interface INivelUIDAO {
    void inserir(NivelUI nivelUI);
    NivelUI buscarPorId(int id);
    List<NivelUI> listarTodos();
    void atualizar(NivelUI nivelUI);
    void excluir(int id);
    
    //utilizar novos métodos conforme a necessidade do projeto
    /*NivelUI buscarPorNome(String nome);
    List<NivelUI> buscarPorPercentual(double percentual);*/
}
