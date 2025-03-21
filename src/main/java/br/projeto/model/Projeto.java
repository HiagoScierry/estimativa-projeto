package br.projeto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Projeto {
    private int id;
    private String nome;
    private String dataCriacao;
    private String status;
    private boolean compartilhado;
    private List<Perfil> perfis;
    private List<Funcionalidade> funcionalidadesWebBackend; 
    private List<Funcionalidade> funcionalidadesIOS;
    private List<Funcionalidade> funcionalidadesAndroid;
    private List<CustoAdicional> custosAdicionais;
    private NivelUI nivelUI;
    private Estimativa estimativa; 
    private double percentualImpostos;
    private double percentualLucro;

    public Projeto() {}

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(String dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean isCompartilhado() { return compartilhado; }
    public void setCompartilhado(boolean compartilhado) { this.compartilhado = compartilhado; }

    public List<Perfil> getPerfis() { return perfis; }
    public void setPerfis(List<Perfil> perfis) { this.perfis = perfis; }

    public List<Funcionalidade> getFuncionalidadesWebBackend() { return funcionalidadesWebBackend; }
    public void setFuncionalidadesWebBackend(List<Funcionalidade> funcionalidadesWebBackend) { this.funcionalidadesWebBackend = funcionalidadesWebBackend; }

    public List<Funcionalidade> getFuncionalidadesIOS() { return funcionalidadesIOS; }
    public void setFuncionalidadesIOS(List<Funcionalidade> funcionalidadesIOS) { this.funcionalidadesIOS = funcionalidadesIOS; }

    public List<Funcionalidade> getFuncionalidadesAndroid() { return funcionalidadesAndroid;}
    public void setFuncionalidadesAndroid(List<Funcionalidade> funcionalidadesAndroid) {this.funcionalidadesAndroid = funcionalidadesAndroid;}
    
    public List<CustoAdicional> getCustosAdicionais() { return custosAdicionais; }
    public void setCustosAdicionais(List<CustoAdicional> custosAdicionais) { this.custosAdicionais = custosAdicionais; }

    public NivelUI getNivelUI() { return nivelUI; }
    public void setNivelUI(NivelUI nivelUI) { this.nivelUI = nivelUI; }
    
    public Estimativa getEstimativa() { return estimativa; }
    public void setEstimativa(Estimativa estimativa) { this.estimativa = estimativa; }

    public double getPercentualImpostos() { return percentualImpostos; }
    public void setPercentualImpostos(double percentualImpostos) { this.percentualImpostos = percentualImpostos; }

    public double getPercentualLucro() { return percentualLucro; }
    public void setPercentualLucro(double percentualLucro) { this.percentualLucro = percentualLucro; }

    // Métodos
    public double calcularCustoTotal(double taxaDesenvolvedor) {
        int horasWebBackend = funcionalidadesWebBackend.stream().mapToInt(Funcionalidade::getHorasEstimadas).sum();
        int horasIOS = funcionalidadesIOS.stream().mapToInt(Funcionalidade::getHorasEstimadas).sum();
        int horasAndroid = funcionalidadesAndroid.stream().mapToInt(Funcionalidade::getHorasEstimadas).sum();
        
        double custoDesenvolvimento = (horasWebBackend + horasIOS + horasAndroid ) * taxaDesenvolvedor;
        double custoAdicionalTotal = custosAdicionais.stream().mapToDouble(CustoAdicional::getValor).sum();
        return custoDesenvolvimento + custoAdicionalTotal;
    }

    public double calcularPrecoFinal(double taxaDesenvolvedor) {
        double custoTotal = calcularCustoTotal(taxaDesenvolvedor);
        double valorComImpostos = custoTotal * (1 + percentualImpostos);
        return valorComImpostos * (1 + percentualLucro);
    }
    
    public Map<String, Integer> getFuncionalidadesEscolhidas() {
        Map<String, Integer> funcionalidadesEscolhidas = new HashMap<String, Integer>();

        for (Funcionalidade funcionalidade : funcionalidadesWebBackend) {
            funcionalidadesEscolhidas.put("WEB/BACKEND : " + funcionalidade.getNome(), funcionalidade.getHorasEstimadas());
        }

        for (Funcionalidade funcionalidade : funcionalidadesIOS) {
            funcionalidadesEscolhidas.put("IOS : " + funcionalidade.getNome(), funcionalidade.getHorasEstimadas());
        }
        
        for (Funcionalidade funcionalidade : funcionalidadesAndroid) {
            funcionalidadesEscolhidas.put("ANDROID : " + funcionalidade.getNome(), funcionalidade.getHorasEstimadas());
        }

        return funcionalidadesEscolhidas;
    }
    
}