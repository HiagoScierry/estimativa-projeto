/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.CustoAdicional;
import br.projeto.model.Estimativa;
import br.projeto.model.Funcionalidade;
import br.projeto.model.NivelUI;
import br.projeto.model.Projeto;
import br.projeto.repository.ProjetoRepository;
import br.projeto.service.EstimaProjetoService;
import br.projeto.singleton.ProjetoSingleton;
import br.projeto.view.ElaborarEstimativaView;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Cauã
 */

public class ElaborarEstimativaPresenter implements Observer {
    private final ElaborarEstimativaView view;
    private final ProjetoSingleton projetoSingleton;
    private EstimaProjetoService estimaProjetoService;
    private ProjetoRepository projetoRepository;
    private int projetoId;

    public ElaborarEstimativaPresenter() {
        this.view = new ElaborarEstimativaView();
        this.projetoSingleton = ProjetoSingleton.getInstance();
        this.estimaProjetoService = new EstimaProjetoService();
        this.projetoRepository = new ProjetoRepository();
        configurarEventos();
        this.projetoSingleton.addObserver(this);
    }

    private void configurarEventos() {
        view.getBtnCriarEstimativa().addActionListener((ActionEvent e) -> {
            criarProjeto();
        });
        view.getTblEstimativaProjeto().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                calcularLinhas(e);
            }
        });

        view.getTblPrecosPorDiaTrabalho().setValueAt(this.estimaProjetoService.getValorDiariaUiUx(), 0, 2);
        view.getTblPrecosPorDiaTrabalho().setValueAt(this.estimaProjetoService.getValorDiariaUiUx(), 0, 3);
        view.getTblPrecosPorDiaTrabalho().setValueAt(this.estimaProjetoService.getValorDiariaUiUx(), 0, 4);

        view.getTblPrecosPorDiaTrabalho().setValueAt(this.estimaProjetoService.getValorDiariaGerenciamento(), 1, 2);
        view.getTblPrecosPorDiaTrabalho().setValueAt(this.estimaProjetoService.getValorDiariaGerenciamento(), 1, 3);
        view.getTblPrecosPorDiaTrabalho().setValueAt(this.estimaProjetoService.getValorDiariaGerenciamento(), 1, 4);
        
        view.getTblPrecosPorDiaTrabalho().setValueAt(this.estimaProjetoService.getValorDiariaDesenvolvimento(), 2, 2);
        view.getTblPrecosPorDiaTrabalho().setValueAt(this.estimaProjetoService.getValorDiariaDesenvolvimento(), 2, 3);
        view.getTblPrecosPorDiaTrabalho().setValueAt(this.estimaProjetoService.getValorDiariaDesenvolvimento(), 2, 4);


        view.getTblPrecosPorDiaTrabalho().setValueAt(1500.00, 3, 2);
        view.getTblPrecosPorDiaTrabalho().setValueAt(6000.00, 4, 2);
        view.getTblPrecosPorDiaTrabalho().setValueAt(1500.00, 5, 2);
        view.getTblPrecosPorDiaTrabalho().setValueAt(0.00, 6, 2);
        view.getTblPrecosPorDiaTrabalho().setValueAt(2000.00, 7, 2);
        view.getTblPrecosPorDiaTrabalho().setValueAt(2000.00, 8, 2);
        view.getTblPrecosPorDiaTrabalho().setValueAt("15%", 9, 2);
        view.getTblPrecosPorDiaTrabalho().setValueAt("15%", 10, 2);


    }

    public void setProjetoId(int projetoId) {
        this.projetoId = projetoId;
    }

    private void condicaoLinhasParentes(MouseEvent e, int linhaAlterada) {
        JTable table = (JTable) e.getSource();
        int totalColunas = table.getColumnCount(); // Número total de colunas

        if (linhaAlterada < 6) {
            int start = (linhaAlterada < 3) ? 0 : 3;
            int end = (linhaAlterada < 3) ? 2 : 5;

            for (int i = start; i <= end; i++) {
                // Marca a linha alterada no checkbox (coluna 0)
                table.setValueAt(linhaAlterada == i, i, 0);

                if (linhaAlterada == i) {
                    // Obtém o valor da coluna 5 e garante que seja um número ou null
                    Object valorCelula = table.getValueAt(i, 5);
                    Number valor = (valorCelula instanceof Number) ? (Number) valorCelula : null;
                    table.setValueAt(valor, i, 5); // Mantém o valor na linha alterada

                    // se a linha alterada for menor que 3 , devo buscar a soma de dias das linhas 0 a 2 e multiplicar pela porcentagem que recebi da linha alterada

                    if (linhaAlterada >= 3 && linhaAlterada < 6) {
                        int soma = 0;
                        for (int j = 0; j < 3; j++) {
                            soma += table.getValueAt(j, 5) != null ? Integer.parseInt(table.getValueAt(j, 5).toString().trim()) : 0;
                        }

                        double percentual = Double.parseDouble(table.getValueAt(linhaAlterada, 5).toString().replace("%", "")) / 100.0;
                        soma = (int) (soma * percentual);
                        table.setValueAt(soma, linhaAlterada, 5);
                        ((AbstractTableModel) table.getModel()).fireTableCellUpdated(i, 5);
                    }
                } else {
                    // Limpa a coluna 5 das linhas não alteradas usando null em vez de ""
                    table.setValueAt(null, i, 5);

                    // Se a coluna 6 existir, também limpa
                    if (totalColunas > 6) {
                        table.setValueAt(null, i, 6);
                    }
                }
            }
        }
    }


    private void calculoTaxaExtraImpostosDias() {
        JTable tabelaPrecoPorDia = view.getTblPrecosPorDiaTrabalho(); // Substitua pelo nome real da sua JTable
        JTable tabelaResultados = view.getTblValoresFinais(); // Substitua pelo nome real da segunda JTable
        JTable tabelaEstimativa = view.getTblEstimativaProjeto(); // Substitua pelo nome real da terceira JTable

        double valorTotalFuncionalidades = 0;
        int dias = 0;

        for (int i = 0; i < tabelaEstimativa.getRowCount(); i++) {
            valorTotalFuncionalidades += tabelaEstimativa.getValueAt(i, 6) != null ? Double.parseDouble(tabelaEstimativa.getValueAt(i, 6).toString().trim()) : 0;
            dias += tabelaEstimativa.getValueAt(i, 5) != null ? Integer.parseInt(tabelaEstimativa.getValueAt(i, 5).toString().trim()) : 0;
        }

        double custoHardware = Double.parseDouble(tabelaPrecoPorDia.getValueAt(3, 2).toString().trim());
        double custoSoftware = Double.parseDouble(tabelaPrecoPorDia.getValueAt(4, 2).toString().trim());
        double custoRiscos = Double.parseDouble(tabelaPrecoPorDia.getValueAt(5, 2).toString().trim());
        double custoGarantia = Double.parseDouble(tabelaPrecoPorDia.getValueAt(6, 2).toString().trim());
        double fundoReserva = Double.parseDouble(tabelaPrecoPorDia.getValueAt(7, 2).toString().trim());
        double outrosCustos = Double.parseDouble(tabelaPrecoPorDia.getValueAt(8, 2).toString().trim());
        double percentualImposto = Double.parseDouble(tabelaPrecoPorDia.getValueAt(9, 2).toString().replace("%", "")) / 100.0;
        double percentualLucro = Double.parseDouble(tabelaPrecoPorDia.getValueAt(10, 2).toString().replace("%", "")) / 100.0;

        // Cálculo do valor base


        double taxaExtras = custoHardware + custoSoftware + custoRiscos + custoGarantia + fundoReserva + outrosCustos;

        double valorImposto = (valorTotalFuncionalidades) * percentualImposto;
        double valorMaisTaxas = valorTotalFuncionalidades + taxaExtras;
        double valorMiasImposto = valorTotalFuncionalidades + valorImposto;

        double lucro = valorTotalFuncionalidades * percentualLucro;

        double precoFinalCliente = valorImposto + lucro + valorMaisTaxas;

        int meses = dias / 30;

        double mediaPorMes = precoFinalCliente / meses;

        // Inserindo os resultados na segunda JTable
        tabelaResultados.setValueAt(valorTotalFuncionalidades, 0, 1);
        tabelaResultados.setValueAt(valorMaisTaxas, 1, 1);
        tabelaResultados.setValueAt(valorImposto, 2, 1);
        tabelaResultados.setValueAt(valorMiasImposto, 3, 1);
        tabelaResultados.setValueAt(lucro, 4, 1);
        tabelaResultados.setValueAt(dias, 5, 1);
        tabelaResultados.setValueAt(meses, 6, 1);
        tabelaResultados.setValueAt(precoFinalCliente, 7, 1);
        tabelaResultados.setValueAt(mediaPorMes, 8, 1);

        for (int i = 0; i < tabelaResultados.getRowCount(); i++) {
            ((AbstractTableModel) tabelaResultados.getModel()).fireTableCellUpdated(i, 1);

        }
    }

    private void calculoTotal() {
        JTable table = view.getTblEstimativaProjeto();
        double valorTotal = 0;

        for (int i = 0; i < table.getRowCount(); i++) {
            valorTotal += table.getValueAt(i, 6) != null ? Double.parseDouble(table.getValueAt(i, 6).toString().trim()) : 0 ;
        }

        view.getTblValoresFinais().setValueAt(valorTotal, 0, 1);

        ((AbstractTableModel) view.getTblValoresFinais().getModel()).fireTableCellUpdated(0,1);

        calculoTaxaExtraImpostosDias();

    }

    private void calcularLinhas(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        int row = table.rowAtPoint(e.getPoint());
        int column = table.columnAtPoint(e.getPoint());

        if (column == 0) { // Se clicar na coluna do checkbox
            Object cellValue = table.getValueAt(row, column);
            Boolean checked = (cellValue instanceof Boolean) ? (Boolean) cellValue : false;

            if (checked != null && checked) {
                boolean webBackend = view.getChcWeb().isSelected();
                boolean ios = view.getChcIOS().isSelected();
                boolean android = view.getChcAndroid().isSelected();

                // Verificar se o valor é percentual
                String valorColuna2 = table.getValueAt(row, 2).toString();
                boolean isPercentual = valorColuna2.contains("%");

                int soma = 0;
                for (int i = 2; i <= 4; i++) {
                    String valorStr = table.getValueAt(row, i).toString().trim();
                    if (valorStr.contains("-")) valorStr = "0"; // Substituir valores negativos

                    try {
                        valorStr = isPercentual ? valorStr.replace("%", "") : valorStr;

                        if(webBackend && i == 2){
                            soma += Integer.parseInt(valorStr);
                        }

                        if (ios && i == 3){
                            soma += Integer.parseInt(valorStr);
                        }

                        if(android & i == 4){
                            soma += Integer.parseInt(valorStr);
                        }

                    } catch (NumberFormatException ex) {
                        System.err.println("Erro ao converter para inteiro: " + valorStr);
                    }
                }

                table.setValueAt(soma, row, 5);
                condicaoLinhasParentes(e, row);


                //CALCULAR FUNCIONALIDADE COM AS HORAS
                double valorFuncionalidade = 0.0;
                int dias = table.getValueAt(row, 5).toString().trim() == "" ? 0 : Integer.parseInt(table.getValueAt(row, 5).toString().trim());

                if(webBackend || android){
                    valorFuncionalidade += estimaProjetoService.calcularValorUnitario("WEB/BACKEND", dias);
                }

                if(ios){
                    valorFuncionalidade += estimaProjetoService.calcularValorUnitario("IOS", dias);
                }


                table.setValueAt(Math.ceil(valorFuncionalidade), row, 6);


                ((AbstractTableModel) table.getModel()).fireTableCellUpdated(row, 5);
                ((AbstractTableModel) table.getModel()).fireTableCellUpdated(row, 6);

                calculoTotal();
            }
        }
    }

    private void criarProjeto() {
        Projeto projeto = projetoRepository.buscarPorId(projetoId);

        boolean webBackend = view.getChcWeb().isSelected();
        boolean ios = view.getChcIOS().isSelected();
        boolean android = view.getChcAndroid().isSelected();

        List<Funcionalidade> funcionalidadesWebBackend = new ArrayList<Funcionalidade>();
        List<Funcionalidade> funcionalidadesIOS = new ArrayList<Funcionalidade>();
        List<Funcionalidade> funcionalidadesAndroid = new ArrayList<Funcionalidade>();
        List<CustoAdicional> custoAdicionals = new ArrayList<CustoAdicional>();
        NivelUI nivelUI = new NivelUI(0, "", 0.0);
        Estimativa estimativa = new Estimativa(0, 0, 0 ,0);

        int rowCount = view.getTblEstimativaProjeto().getRowCount();
        int columnCount = view.getTblEstimativaProjeto().getColumnCount();

        for (int i = 0; i < rowCount; i++) {
            String funcionalidadeNome = "";
            if (columnCount > 1) {
                funcionalidadeNome = view.getTblEstimativaProjeto().getValueAt(i, 1).toString();
            }
            boolean rowMarked = view.getTblEstimativaProjeto().getValueAt(i, 0) != null ? true : false;
            Funcionalidade funcionalidade = new Funcionalidade(0, funcionalidadeNome, 0, "");

            if (i > 2 && i < 6 && view.getTblEstimativaProjeto().getValueAt(i, 5) != null) {
                nivelUI.setNome(view.getTblEstimativaProjeto().getValueAt(i, 1).toString());
                System.out.println(view.getTblEstimativaProjeto().getValueAt(i, 5).toString());
                nivelUI.setPercentual(Double.parseDouble(view.getTblEstimativaProjeto().getValueAt(i, 5).toString().trim()));
            }

            if (i > 5) {
                // Web Backend
                if (rowMarked && webBackend && view.getTblEstimativaProjeto().getValueAt(i, 2) != null) {
                    String valorWeb = view.getTblEstimativaProjeto().getValueAt(i, 2).toString().trim();
                    if (valorWeb.contains("-")) {
                        funcionalidade.setHorasEstimadas(0); // Ou outro valor padrão
                    } else {
                        try {
                            funcionalidade.setHorasEstimadas(Integer.parseInt(valorWeb));
                        } catch (NumberFormatException e) {
                            funcionalidade.setHorasEstimadas(0); // Ou outro valor padrão
                        }
                    }
                    funcionalidade.setPlataforma("WEB/BACKEND");
                    funcionalidadesWebBackend.add(funcionalidade);
                }

                // iOS
                if (rowMarked && ios && view.getTblEstimativaProjeto().getValueAt(i, 3) != null) {
                    String valorIOS = view.getTblEstimativaProjeto().getValueAt(i, 3).toString().trim();
                    if (valorIOS.contains("-")) {
                        funcionalidade.setHorasEstimadas(0); // Ou outro valor padrão
                    } else {
                        try {
                            funcionalidade.setHorasEstimadas(Integer.parseInt(valorIOS));
                        } catch (NumberFormatException e) {
                            funcionalidade.setHorasEstimadas(0); // Ou outro valor padrão
                        }
                    }
                    funcionalidade.setPlataforma("IOS");
                    funcionalidadesIOS.add(funcionalidade);
                }

                // Android
                if (rowMarked && android && view.getTblEstimativaProjeto().getValueAt(i, 5) != null) {
                    String valorAndroid = view.getTblEstimativaProjeto().getValueAt(i, 4).toString().trim();
                    if (valorAndroid.contains("-")) {
                        funcionalidade.setHorasEstimadas(0); // Ou outro valor padrão
                    } else {
                        try {
                            funcionalidade.setHorasEstimadas(Integer.parseInt(valorAndroid));
                        } catch (NumberFormatException e) {
                            funcionalidade.setHorasEstimadas(0); // Ou outro valor padrão
                        }
                    }
                    funcionalidade.setPlataforma("ANDROID");
                    funcionalidadesAndroid.add(funcionalidade);
                }
            }
        }

        // Processando custos adicionais
        for (int i = 3; i < view.getTblPrecosPorDiaTrabalho().getRowCount() - 2; i++) {
            System.out.println(view.getTblPrecosPorDiaTrabalho().getValueAt(i, 1));
            CustoAdicional custoAdicional = new CustoAdicional(
                    0,
                    view.getTblPrecosPorDiaTrabalho().getValueAt(i, 1).toString().trim(),
                    Double.parseDouble(view.getTblPrecosPorDiaTrabalho().getValueAt(i, 2).toString().trim())
            );
            custoAdicionals.add(custoAdicional);
        }


        estimativa.setPrecoFinal(Double.parseDouble(view.getTblValoresFinais().getValueAt(7, 1).toString().trim()));
        estimativa.setCustoTotal(Double.parseDouble(view.getTblValoresFinais().getValueAt(0, 1).toString().trim()));
        estimativa.setTempoTotal(Integer.parseInt(view.getTblValoresFinais().getValueAt(5, 1).toString().trim()));

        // Processando valores de impostos e lucro
        double imposto = Double.parseDouble(view.getTblPrecosPorDiaTrabalho().getValueAt(9, 2).toString().replace("%", ""));
        double lucro = Double.parseDouble(view.getTblPrecosPorDiaTrabalho().getValueAt(10, 2).toString().replace("%", ""));

        projeto.setPercentualImpostos(imposto / 100);
        projeto.setPercentualLucro(lucro / 100);
        projeto.setNivelUI(nivelUI);
        projeto.setFuncionalidadesWebBackend(funcionalidadesWebBackend);
        projeto.setFuncionalidadesIOS(funcionalidadesIOS);
        projeto.setFuncionalidadesAndroid(funcionalidadesAndroid);
        projeto.setCustosAdicionais(custoAdicionals);
        projeto.setEstimativa(estimativa);

        projetoRepository.atualizarProjeto(projeto);
    }


    @Override
    public void update(List<Projeto> projetos){}
    
    public ElaborarEstimativaView getView() {
        return view;
    }

}

