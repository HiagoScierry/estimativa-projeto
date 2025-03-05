/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.config.database;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hiago
 */
public class SQLiteConnection {
    private static String url;

    public static Connection getConexao() throws Exception {
        Dotenv dotenv = Dotenv.load();
        url = dotenv.get("DATABASE_PATH");
        if(url == null){
            url = ("DATABASE_PATH");
        }

        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new Exception("Problema ao abrir banco de dados SQLite");
        }
    }
}