package br.projeto.config.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {
        private static String url;

        public static Connection getConexao() throws Exception {
            Dotenv dotenv = Dotenv.load();
            url = dotenv.get("H2_URL");
            if(url == null){
                url = ("jdbc:h2:~/test");
            }

            try {
                return DriverManager.getConnection(url);
            } catch (SQLException e) {
                throw new Exception("Problema ao abrir banco de dados SQLite");
            }
        }
}
