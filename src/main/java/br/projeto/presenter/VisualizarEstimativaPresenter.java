/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.CustoAdicional;
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
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
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
        DefaultTableModel model = (DefaultTableModel) view.getTblTaxasExtras().getModel();

        while (model.getRowCount() < 6) {
            model.addRow(new Object[]{"", "", ""});
        }

        double percentualComImpostos = projeto.getPercentualImpostos();
        double percentualLucroDesejado = projeto.getPercentualLucro();

        model.setValueAt(estimaService.getValorDiariaUiUx(), 0, 1);
        model.setValueAt(estimaService.getValorDiariaGerenciamento(), 1, 1);
        model.setValueAt(estimaService.getValorDiariaDesenvolvimento(), 2, 1);
        model.setValueAt(projeto.getCustosAdicionais().get(0).getValor(), 3, 1);
        model.setValueAt(projeto.getCustosAdicionais().get(1).getValor(), 4, 1);
        model.setValueAt(projeto.getCustosAdicionais().get(2).getValor(), 5, 1);
        model.setValueAt(projeto.getCustosAdicionais().get(3).getValor(), 6, 1);
        model.setValueAt(projeto.getCustosAdicionais().get(4).getValor(), 7, 1);
        model.setValueAt(projeto.getCustosAdicionais().get(5).getValor(), 8, 1);
        model.setValueAt(percentualComImpostos, 9, 1);
        model.setValueAt(percentualLucroDesejado, 10, 1);
    }

    private void recuperarDadosTabelaValoresFinais(Projeto projeto) {      
        DefaultTableModel model = (DefaultTableModel) view.getTblValoresFinais().getModel();
        DefaultTableModel tabelaFuncionalidades = (DefaultTableModel) view.getTblFuncionalidadesProjeto().getModel();

        // Garantindo que haja pelo menos 9 linhas na tabela
        while (model.getRowCount() < 9) {
            model.addRow(new Object[]{"", ""});
        }

        double valorTotalFuncionalidades = projeto.getEstimativa().getCustoTotal();
        int dias = 0;

        // Calculando valores a partir da tabela de funcionalidades
        for (int i = 0; i < tabelaFuncionalidades.getRowCount(); i++) {
            valorTotalFuncionalidades += tabelaFuncionalidades.getValueAt(i, 2) != null ? Double.parseDouble(tabelaFuncionalidades.getValueAt(i, 2).toString().trim()) : 0;
            dias += tabelaFuncionalidades.getValueAt(i, 1) != null ? Integer.parseInt(tabelaFuncionalidades.getValueAt(i, 1).toString().trim()) : 0;
        }

        // Somando os custos adicionais do projeto
        double taxaExtras = 0;
        for (int i = 0; i < Math.min(projeto.getCustosAdicionais().size(), 6); i++) {
            taxaExtras += projeto.getCustosAdicionais().get(i).getValor();
        }

        double valorImposto = valorTotalFuncionalidades * projeto.getPercentualImpostos();
        double valorMaisTaxas = valorTotalFuncionalidades + taxaExtras;
        double valorMaisImposto = valorTotalFuncionalidades + valorImposto;
        double lucro = valorTotalFuncionalidades * projeto.getPercentualLucro();

        model.setValueAt(valorTotalFuncionalidades, 0, 1);
        model.setValueAt(valorMaisTaxas, 1, 1);
        model.setValueAt(valorImposto, 2, 1);
        model.setValueAt(valorMaisImposto, 3, 1);
        model.setValueAt(lucro, 4, 1);
        model.setValueAt(estimaService.calcularDiasTotais(projeto), 5, 1);
        model.setValueAt(estimaService.calcularDiasTotais(projeto)/30, 6, 1);
        model.setValueAt(projeto.getEstimativa().getPrecoFinal(), 7, 1);
        model.setValueAt(projeto.getEstimativa().getPrecoFinal()/30, 8, 1);
    }
    
    @Override
    public void update(List<Projeto> projetos) {
    }

    public VisualizarEstimativaView getView() {
        return view;
    }

}


