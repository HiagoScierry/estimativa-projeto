package br.projeto.service;

import br.projeto.model.Projeto;
import br.projeto.repository.ProjetoRepositoryMock;

import java.util.*;

public class CriarProjetoMock {
    private final ProjetoRepositoryMock repository;

    public CriarProjetoMock(ProjetoRepositoryMock repository) {
        this.repository = repository;
    }

    public Optional<Projeto> criarProjetoAleatorio() {
        List<Projeto> projetosExistentes = repository.getProjetos();

        if (projetosExistentes.isEmpty()) {
            return Optional.empty();
        }

        Random random = new Random();
        Projeto projetoBase = projetosExistentes.get(random.nextInt(projetosExistentes.size()));

        List<String> tipos = combinarTipos(projetosExistentes, random);
        if (tipos.size() < 1 || tipos.size() > 2) {
            return Optional.empty();
        }

        String nome = gerarNomeDoProjeto(tipos);
        String criador = projetoBase.getCriador().getNome();
        String dataCriacao = gerarDataAleatoria();
        String status = random.nextBoolean() ? "Estimado" : "Em andamento";
        boolean compartilhado = random.nextBoolean();
        String compartilhadoPor = compartilhado ? projetoBase.getCriador().getNome() : null;
        Map<String, Integer> funcionalidades = combinarFuncionalidades(projetosExistentes, random);

        return Optional.empty();
    }

    private String gerarNomeDoProjeto(List<String> tipos) {
        StringBuilder nomeBuilder = new StringBuilder("Projeto ");

        for (int i = 0; i < tipos.size(); i++) {
            nomeBuilder.append(tipos.get(i));
            if (i < tipos.size() - 2) {
                nomeBuilder.append(", ");
            } else if (i == tipos.size() - 2) {
                nomeBuilder.append(" e ");
            }
        }

        nomeBuilder.append(" - ").append(UUID.randomUUID().toString().substring(0, 5));
        return nomeBuilder.toString();
    }

    private String gerarDataAleatoria() {
        Random random = new Random();
        int dia = random.nextInt(28) + 1;
        int mes = random.nextInt(12) + 1;
        int ano = 2023 + random.nextInt(3);
        return String.format("%02d/%02d/%d", dia, mes, ano);
    }

    private List<String> combinarTipos(List<Projeto> projetos, Random random) {
        return new ArrayList<>();
    }

    private Map<String, Integer> combinarFuncionalidades(List<Projeto> projetos, Random random) {
        Map<String, Integer> funcionalidadesCombinadas = new HashMap<>();
        int numProjetosParaCombinar = 1 + random.nextInt(projetos.size());

        for (int i = 0; i < numProjetosParaCombinar; i++) {
            Projeto projeto = projetos.get(random.nextInt(projetos.size()));
            Map<String, Integer> funcionalidades = projeto.getFuncionalidadesEscolhidas();

            int numFuncionalidades = 1 + random.nextInt(funcionalidades.size());
            List<String> chaves = new ArrayList<>(funcionalidades.keySet());
            Collections.shuffle(chaves, random);

            for (int j = 0; j < numFuncionalidades; j++) {
                String chave = chaves.get(j);
                funcionalidadesCombinadas.putIfAbsent(chave, funcionalidades.get(chave));
            }
        }

        return funcionalidadesCombinadas;
    }
}
