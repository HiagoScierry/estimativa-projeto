/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.dao.sqlite;

import br.projeto.config.database.SQLiteConnection;
import br.projeto.dao.interfaces.IEstimativaDAO;
import br.projeto.model.Estimativa;

import java.sql.*;


/**
 *
 * @author Cauã
 */
public class EstimativaSQLiteDao implements IEstimativaDAO {

    private Connection connection;

    public EstimativaSQLiteDao() throws Exception {
        this.connection = SQLiteConnection.getConexao();
    }

    @Override
    public void inserir(Estimativa estimativa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void atualizar(Estimativa estimativa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void excluir(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Estimativa buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}