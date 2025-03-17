package br.projeto.config.database.h2;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {
    public static Connection getConexao() {
        try {

            Dotenv dotenv = Dotenv.load();
            String url = dotenv.get("H2_URL");
            if (url == null || url.isEmpty()) {
                url = "jdbc:h2:~/test";
            }
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(url, "sa", "");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Driver H2 não encontrado");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados H2", e);
        }
    }
}
