/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.Estimativa;
import br.projeto.model.Funcionalidade;
import br.projeto.model.Projeto;
import br.projeto.repository.EstimativaRepository;
import br.projeto.service.EstimaProjetoService;
import br.projeto.singleton.ProjetoSingleton;
import br.projeto.view.VisualizarEstimativaView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cauã
 */

public class VisualizarEstimativaPresenter implements Observer {
    private final VisualizarEstimativaView view;
    private final ProjetoSingleton projetoSingleton;
    private final EstimaProjetoService estimaService;
    private final EstimativaRepository estimativaRepository;

    public VisualizarEstimativaPresenter(int projetoId) {
        this.view = new VisualizarEstimativaView();
        this.estimaService = new EstimaProjetoService();
        this.projetoSingleton = ProjetoSingleton.getInstance();
        this.estimativaRepository = new EstimativaRepository();

        this.projetoSingleton.addObserver(this);
        
        Projeto projeto = projetoSingleton.getProjetoPorId(projetoId);  
        atualizarTabelas(projeto);
    }


    private void atualizarTabelas(Projeto projeto) {
        recuperarPlataformasEstimativa(projeto);
        recuperarDadosTabelaFuncionalidades(projeto);
        recuperarDadosTabelaTaxasExtras(projeto);
        recuperarDadosTabelaValoresFinais(projeto);
    }

    private void recuperarPlataformasEstimativa(Projeto projeto){
        StringBuilder plataformasBuilder = new StringBuilder();

        if (!projeto.getFuncionalidadesAndroid().isEmpty()) {
            plataformasBuilder.append("Android ");
        }
        if (!projeto.getFuncionalidadesWebBackend().isEmpty()) {
            plataformasBuilder.append("Web/Backend ");
        }
        if (!projeto.getFuncionalidadesIOS().isEmpty()) {
            plataformasBuilder.append("iOS");
        }

        
        String PlataformasFinal = plataformasBuilder.toString().trim();
        System.out.println(PlataformasFinal);
        view.getTxtPlataformas().setText(PlataformasFinal);
    }
    private void recuperarDadosTabelaFuncionalidades(Projeto projeto) {
        DefaultTableModel model = (DefaultTableModel) view.getTblFuncionalidadesProjeto().getModel();
        model.setRowCount(0);

        Map<Integer, Object[]> funcionalidades = new HashMap<>();

        for (Funcionalidade funcionalidade : projeto.getFuncionalidadesWebBackend()) {
            funcionalidades.put(funcionalidade.getId(), new Object[]{"WEB/BACKEND", funcionalidade.getNome(), funcionalidade.getHorasEstimadas()});
        }
        for (Funcionalidade funcionalidade : projeto.getFuncionalidadesIOS()) {
            funcionalidades.put(funcionalidade.getId(), new Object[]{"IOS", funcionalidade.getNome(), funcionalidade.getHorasEstimadas()});
        }
        for (Funcionalidade funcionalidade : projeto.getFuncionalidadesAndroid()) {
            funcionalidades.put(funcionalidade.getId(), new Object[]{"ANDROID", funcionalidade.getNome(), funcionalidade.getHorasEstimadas()});
        }

        for (Map.Entry<Integer, Object[]> entry : funcionalidades.entrySet()) {
            String tipoProjeto = (String) entry.getValue()[0];
            String nome = (String) entry.getValue()[1];
            int horasEstimadas = (Integer) entry.getValue()[2];

            double valorUnitario = estimaService.calcularValorUnitario(tipoProjeto, horasEstimadas);

            model.addRow(new Object[]{nome, horasEstimadas, valorUnitario});
        }
    }

    private void recuperarDadosTabelaTaxasExtras(Projeto projeto) {
        //precisa de um recuperador no banco de dados para as taxas do projeto
    }

    private void recuperarDadosTabelaValoresFinais(Projeto projeto) {
        Estimativa estimativa = projeto.getEstimativa();
        DefaultTableModel model = (DefaultTableModel) view.getTblValoresFinais().getModel();

        while (model.getRowCount() < 6) {
            model.addRow(new Object[]{"", "", ""});
        }

        model.setValueAt(estimativa.getCustoTotal(), 0, 1);
        
        model.setValueAt(projeto.getPercentualImpostos(), 2, 1); 
        
        model.setValueAt(projeto.getPercentualLucro(), 4, 1);    
        model.setValueAt(estimativa.getTempoTotal(), 5, 1);
        model.setValueAt((estimativa.getTempoTotal() / 31), 6, 1);
        model.setValueAt(estimativa.getPrecoFinal(), 7, 1);
        model.setValueAt((estimativa.getPrecoFinal() / (estimativa.getTempoTotal() / 31)), 8, 1);
    }

    @Override
    public void update(List<Projeto> projetos) {
    }

    public VisualizarEstimativaView getView() {
        return view;
    }

}

