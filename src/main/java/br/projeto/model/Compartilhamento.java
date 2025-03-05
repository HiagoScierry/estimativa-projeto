/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.model;

/**
 *
 * @author hiago
 */
public class Compartilhamento {
    private int id;
    private Projeto projeto;
    private Usuario usuarioOrigem;
    private Usuario usuarioDestino;

    public Compartilhamento(int id, Projeto projeto, Usuario usuarioOrigem, Usuario usuarioDestino) {
        this.id = id;
        this.projeto = projeto;
        this.usuarioOrigem = usuarioOrigem;
        this.usuarioDestino = usuarioDestino;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Projeto getProjeto() { return projeto; }
    public void setProjeto(Projeto projeto) { this.projeto = projeto; }

    public Usuario getUsuarioOrigem() { return usuarioOrigem; }
    public void setUsuarioOrigem(Usuario usuarioOrigem) { this.usuarioOrigem = usuarioOrigem; }

    public Usuario getUsuarioDestino() { return usuarioDestino; }
    public void setUsuarioDestino(Usuario usuarioDestino) { this.usuarioDestino = usuarioDestino; }

    // Métodos
    public void permitirApenasLeitura() {
        // Implementar lógica para permitir apenas leitura
    }
}