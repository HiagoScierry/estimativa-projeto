/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import com.aventstack.extentreports.model.Log;

import br.projeto.singleton.LogSingleton;
import br.projeto.singleton.UsuarioSingleton;
import br.projeto.view.ConfiguracaoView;
import model.log;

/**
 *
 * @author Cauã
 */
public class ConfiguracaoPresenter{
    private final ConfiguracaoView view;
    public ConfiguracaoPresenter(){
        this.view = new ConfiguracaoView();

        LogSingleton.getInstancia().criarLog(
            "INFO",
            "ABRIR TELA CONFIGURACAO PROJETO",
            UsuarioSingleton.getInstance().getUsuario().getNome() + "/" + UsuarioSingleton.getInstance().getUsuario().getEmail(),
                 String.valueOf(UsuarioSingleton.getInstance().getUsuario().getId()),
            null
        );

        conifurarTela();

    }

    public void conifurarTela(){
        view.getCmbFormatoLog().setSelectedItem(LogSingleton.getInstancia().getTipoLogName().equalsIgnoreCase("JSON") ? "JSON" : "CSV");

        view.getCmbFormatoLog().addActionListener((e) -> {
            String formatoSelecionado = (String) view.getCmbFormatoLog().getSelectedItem();
            configuraLogAdapter(formatoSelecionado);
        });
    }
    
    public void selecionarLogTipoLog(String tipoLog, String operacao, String nome, String usuario, Exception exception) {
        String formatoSelecionado = (String) view.getCmbFormatoLog().getSelectedItem();
        configuraLogAdapter(formatoSelecionado);
        
        LogSingleton.getInstancia().criarLog(tipoLog, operacao, nome, usuario, exception);
    }
    
    private void configuraLogAdapter(String formato) {
        LogSingleton.getInstancia().setTipoLog(formato);
    }
    
    public ConfiguracaoView getView(){
        return view;
    }
}    
