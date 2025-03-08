package br.projeto.presenter;

import br.projeto.command.ProjetoCommand;
import br.projeto.repository.ProjetoRepositoryMock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class CriarProjetoProjetoCommand implements ProjetoCommand {

    private static int contadorProjetos = 1;
    private static final String PREFIXO_TITULO = "Novo Projeto ";
    private final ProjetoRepositoryMock repository;

    public CriarProjetoProjetoCommand(ProjetoRepositoryMock repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        String titulo = PREFIXO_TITULO + contadorProjetos;
        
        
        //AQUI FOI MOCKADO UM PROJETO PARA APARECER A JTREE(VER ESTIMATIVA, REALIZAR ESTIMATIVA...)
        String criador = "Sistema"; // Criador indefinido
        String dataCriacao = LocalDate.now().toString(); // Data de hoje
        String status = "Em andamento"; // Status inicial genérico
        boolean compartilhado = false; // Não compartilhado por padrão
        String compartilhadoPor = ""; // Nenhum usuário específico
        List<String> tipos = new ArrayList<>(); // Lista vazia de tipos
        Map<String, Integer> funcionalidadesEscolhidas = new HashMap<>(); // Nenhuma funcionalidade escolhida
        repository.adicionarProjeto(titulo, criador, dataCriacao, status, compartilhado, compartilhadoPor, tipos, funcionalidadesEscolhidas);
        //FIM DO MOCK
       

        JOptionPane.showMessageDialog(null, "O projeto '" + titulo + "' foi criado com sucesso!", 
                                      "Projeto Criado", JOptionPane.INFORMATION_MESSAGE);

        repository.notifyObservers();
        contadorProjetos++;
    }
}