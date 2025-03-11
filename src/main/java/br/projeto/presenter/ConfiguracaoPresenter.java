/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.singleton.LogSingleton;
import br.projeto.view.ConfiguracaoView;

/**
 *
 * @author Cauã
 */
public class ConfiguracaoPresenter{
    private final ConfiguracaoView view;
     
    public ConfiguracaoPresenter(){
        this.view = new ConfiguracaoView();
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
