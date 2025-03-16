package br.projeto.generators;

import java.util.ArrayList;

public abstract class AGeneratorAdapter {
    protected String caminho;

    public AGeneratorAdapter(String caminho) {
        this.caminho = caminho;
    }

    public abstract void generator(String nome, ArrayList<String[]> conteudo);
}