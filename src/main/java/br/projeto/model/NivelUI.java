/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.model;

/**
 *
 * @author hiago
 */
public class NivelUI {
    private int id;
    private String nome;
    private double percentual;

    public NivelUI(int id, String nome, double percentual) {
        this.id = id;
        this.nome = nome;
        this.percentual = percentual;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getPercentual() { return percentual; }
    public void setPercentual(double percentual) { this.percentual = percentual; }
}