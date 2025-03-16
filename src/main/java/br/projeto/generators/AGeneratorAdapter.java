package br.projeto.generators;

import br.projeto.model.Projeto;

public abstract class AGeneratorAdapter {
    protected String caminho;

    public AGeneratorAdapter(String caminho) {
        this.caminho = caminho;
    }

    public abstract void generator(String nome, Projeto projeto);
}