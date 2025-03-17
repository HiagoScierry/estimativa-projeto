package br.projeto;

import br.projeto.config.database.h2.H2Server;
import br.projeto.dao.DaoUtil;
import br.projeto.dao.factory.DaoH2Factory;
import br.projeto.dao.factory.DaoSQLiteFactory;
import br.projeto.presenter.LoginPresenter;
import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.JOptionPane;


public class Main {

    public static void main(String[] args) {
        configuracaoInicial();
        new LoginPresenter();
    }

    private static void configuracaoInicial() {
        Dotenv dotenv = Dotenv.load();


        String bancoDados = dotenv.get("BANCO_DE_DADOS");
        if ("SQLite".equals(bancoDados)) {
            try {
                DaoUtil.configureInstance(new DaoSQLiteFactory());
            } catch (Exception ex) {
                
                System.out.println(ex.getMessage());
                 
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro no banco de dados",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if ("H2".equals(bancoDados)) {
            try {
                H2Server.start();
                DaoUtil.configureInstance(new DaoH2Factory());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());

                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Erro no banco de dados",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }

}
