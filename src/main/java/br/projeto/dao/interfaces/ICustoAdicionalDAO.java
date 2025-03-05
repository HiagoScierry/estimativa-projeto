/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.interfaces;

import br.projeto.model.CustoAdicional;
import java.util.List;

/**
 *
 * @author hiago
 */
public interface ICustoAdicionalDAO {
    void inserir(CustoAdicional custo);
    CustoAdicional buscarPorId(int id);
    void atualizar(CustoAdicional custo);
    void excluir(int id);
}
