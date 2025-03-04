/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.model;

import java.time.LocalDate;

/**
 *
 * @author hiago
 */
public class Estimativa {
    private Projeto projeto;
    private LocalDate date;
    
    public Estimativa(Projeto projeto){
        this.projeto = projeto;
    }
    
    public double custoTotal(){
        return 0.0;
    }

    public double totalDias(){
        double total = 0.0;
        
        for (int value : this.projeto.getFuncionalidades().values()) {
            total += value;
        }
        
        return total/8; 
    }
    
}
