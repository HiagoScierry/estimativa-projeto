/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.presenter;

import br.projeto.model.CustoAdicional;
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


    private void calculoTaxaExtraImpostosDias(){
        double taxasExtras = 0;
        double valorImporto = 0;
        double lucro = 0;
        int dias = 0;
        double preçoFinal = 0;



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


        for (int i = 0; i < view.getTblEstimativaProjeto().getRowCount(); i++) {
            String funcionalidadeNome = view.getTblEstimativaProjeto().getValueAt(i, 1).toString();
            boolean rowMarked = view.getTblEstimativaProjeto().getValueAt(i, 0).equals(true);
            Funcionalidade funcionalidade = new Funcionalidade(0, funcionalidadeNome, 0, "");

            if(i > 2 && i < 6){
                nivelUI.setNome(view.getTblEstimativaProjeto().getValueAt(i, 1).toString());
                nivelUI.setPercentual((Double) view.getTblEstimativaProjeto().getValueAt(i, 5));
            }


            if(i > 5){
                if(rowMarked && webBackend) {
                    funcionalidade.setHorasEstimadas((Integer) view.getTblEstimativaProjeto().getValueAt(i, 2));
                    funcionalidade.setPlataforma("Web Backend");
                    funcionalidadesWebBackend.add(funcionalidade);
                }

                if (rowMarked && ios) {
                    funcionalidade.setHorasEstimadas((Integer) view.getTblEstimativaProjeto().getValueAt(i, 3));
                    funcionalidade.setPlataforma("IOS");
                    funcionalidadesIOS.add(funcionalidade);

                }

                if (rowMarked && android) {
                    funcionalidade.setHorasEstimadas((Integer) view.getTblEstimativaProjeto().getValueAt(i, 4));
                    funcionalidade.setPlataforma("Android");
                    funcionalidadesAndroid.add(funcionalidade);
                }
            }
        }


        for (int i = 3; i < view.getTblPrecosPorDiaTrabalho().getRowCount() - 2; i++) {
            CustoAdicional custoAdicional = new CustoAdicional(0, (String) view.getTblPrecosPorDiaTrabalho().getValueAt(i, 1), (Double) view.getTblPrecosPorDiaTrabalho().getValueAt(i, 2));
            custoAdicionals.add(custoAdicional);
        }

        double imposto = Double.parseDouble(view.getTblValoresFinais().getValueAt(9, 2).toString().replace("%",""));
        double lucro = Double.parseDouble(view.getTblValoresFinais().getValueAt(10, 2).toString().replace("%", ""));

        projeto.setPercentualImpostos(imposto/100);
        projeto.setPercentualLucro(lucro/100);

        projeto.setNivelUI(nivelUI);
        projeto.setFuncionalidadesWebBackend(funcionalidadesWebBackend);
        projeto.setFuncionalidadesIOS(funcionalidadesIOS);
        projeto.setFuncionalidadesAndroid(funcionalidadesAndroid);



        projetoRepository.atualizarProjeto(projeto);

    }


    
    @Override
    public void update(List<Projeto> projetos){}
    
    public ElaborarEstimativaView getView() {
        return view;
    }

}

