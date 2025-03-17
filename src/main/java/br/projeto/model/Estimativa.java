/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.model;

/**
 *
 * @author hiago
 */

public class Estimativa {
    private int id;
    private double custoTotal;
    private int tempoTotal;
    private double precoFinal;

    public Estimativa(int id, double custoTotal, int tempoTotal, double precoFinal) {
        this.id = id;
        this.custoTotal = custoTotal;
        this.tempoTotal = tempoTotal;
        this.precoFinal = precoFinal;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getCustoTotal() { return custoTotal; }
    public void setCustoTotal(double custoTotal) { this.custoTotal = custoTotal; }

    public int getTempoTotal() { return tempoTotal; }
    public void setTempoTotal(int tempoTotal) { this.tempoTotal = tempoTotal; }

    public double getPrecoFinal() { return precoFinal; }
    public void setPrecoFinal(double precoFinal) { this.precoFinal = precoFinal; }
}