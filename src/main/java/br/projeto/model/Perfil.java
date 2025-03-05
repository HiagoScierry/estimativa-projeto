/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.model;

import java.util.List;

/**
 *
 * @author hiago
 */
public class Perfil {
    private int id;
    private String nome;
    private List<Funcionalidade> funcionalidades; // Referência à classe Funcionalidade
    private String nivelUI;

    public Perfil(int id, String nome, List<Funcionalidade> funcionalidades, String nivelUI) {
        this.id = id;
        this.nome = nome;
        this.funcionalidades = funcionalidades;
        this.nivelUI = nivelUI;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Funcionalidade> getFuncionalidades() { return funcionalidades; }
    public void setFuncionalidades(List<Funcionalidade> funcionalidades) { this.funcionalidades = funcionalidades; }

    public String getNivelUI() { return nivelUI; }
    public void setNivelUI(String nivelUI) { this.nivelUI = nivelUI; }
}