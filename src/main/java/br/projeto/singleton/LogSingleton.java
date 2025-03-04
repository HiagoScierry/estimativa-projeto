/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.singleton;

import adapter.CSVLogAdapter;
import adapter.JSONLogAdapter;
import interfaces.ILogAdapter;
import model.log;

/**
 *
 * @author Cauã
 */
public class LogSingleton {
    private static LogSingleton instancia = null;
    private ILogAdapter tipoLog;
    
    private LogSingleton(){};
    
    public static LogSingleton getInstancia(){
        if (instancia == null) {
            instancia = new LogSingleton();
        }
        return instancia;
    }
    
    public void setTipoLog(String tipo){
        if(tipo.equalsIgnoreCase("JSON")){
            tipoLog = new JSONLogAdapter();
        } else if(tipo.equalsIgnoreCase("CSV")){
            tipoLog = new CSVLogAdapter();
        }
        throw new IllegalArgumentException("Formato inválido: " + tipo);
    }
    
    public void criarLog(String tipoLog, String operacao, String nome, String usuario, Exception exception) {
        if (tipoLog == null || this.tipoLog == null) {
            throw new IllegalStateException("Tipo de log não configurado.");
        }
        
        log logEntry = new log(tipoLog, operacao, nome, usuario);
        
        if (exception == null) {
            this.tipoLog.escreverMensagemLogCorreto(logEntry);
        } else {
            this.tipoLog.escreverMensagemLogErro(logEntry, exception);
        }
    }
}
