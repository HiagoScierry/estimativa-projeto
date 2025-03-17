package br.projeto.config.database.h2;

import org.h2.tools.Server;
import java.sql.SQLException;

public class H2Server {
    private static Server server;

    public static void start() {
        try {
            server = Server.createTcpServer("-tcpAllowOthers", "-tcpPort", "9092").start();
            System.out.println("Servidor H2 iniciado em: " + server.getURL());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao iniciar o servidor H2", e);
        }
    }

    public static void stop() {
        if (server != null) {
            server.stop();
            System.out.println("Servidor H2 parado.");
        }
    }

    public static void main(String[] args) {
        start(); // Inicia o servidor H2
    }
}