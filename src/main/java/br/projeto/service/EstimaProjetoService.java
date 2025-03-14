package br.projeto.service;

import br.projeto.model.Funcionalidade;
import br.projeto.model.Projeto;

import java.util.Map;

public class EstimaProjetoService {
    private static final int HORAS_TRABALHADAS_POR_DIA = 8;
    private static final double VALOR_DIARIA_DESENVOLVIMENTO = 450.0;
    private static final double VALOR_DIARIA_GERENCIA = 300.0;
    private static final double VALOR_DIARIA_UI_UX = 550.0;

    public int calcularDiasTotais(Projeto projeto) {
        return calcularDiasFuncionalidades(projeto.getFuncionalidadesEscolhidas());
    }

    public double calcularCusto(Projeto projeto) {
        int diasTotais = calcularDiasTotais(projeto);
        return diasTotais * VALOR_DIARIA_DESENVOLVIMENTO;
    }

    public double calcularValorUnitario(String tipoProjeto, int dias) {
        switch (tipoProjeto) {
            case "Web/Back-end":
            case "Android":
                return dias * VALOR_DIARIA_DESENVOLVIMENTO;
            case "iOS":
                return dias * VALOR_DIARIA_UI_UX;
            default:
                throw new IllegalArgumentException("Tipo de projeto desconhecido: " + tipoProjeto);
        }
    }

    public int calcularDiasFuncionalidades(Map<String, Integer> funcionalidadesEscolhidas) {
        int totalHoras = 0;
        for (Integer horas : funcionalidadesEscolhidas.values()) {
            totalHoras += horas;
        }
        return (int) Math.ceil((double) totalHoras / HORAS_TRABALHADAS_POR_DIA);
    }

    public double calcularCustosAdicionais(double custoHardware, double custoSoftware, double custoRiscos, double custoGarantia, double fundoReserva, double outrosCustos) {
        return custoHardware + custoSoftware + custoRiscos + custoGarantia + fundoReserva + outrosCustos;
    }

    public double calcularImpostos(double subtotal, double percentualImpostos) {
        return subtotal * (percentualImpostos / 100);
    }

    public double calcularLucro(double subtotalComImpostos, double percentualLucro) {
        return subtotalComImpostos * (percentualLucro / 100);
    }

    public double calcularPrecoFinal(double subtotalComImpostos, double lucro) {
        return subtotalComImpostos + lucro;
    }

    public double calcularMediaPorMes(double precoFinal, double meses) {
        return precoFinal / meses;
    }
}