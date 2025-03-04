/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hiago
 */
public class Projeto {
    private int id;
    private String nomeProjeto;
    private LocalDate date;
    private Usuario usuario;
    private int usuarioId;
    private Map<String, Integer> funcionalidades;
    private List<Projeto> projetos;
    private boolean foiCompartilhado;
    private List<ValorBase> valorBase;
    private List<Usuario> usuariosCompartilhados;

    public Projeto(int id, String nomeProjeto, LocalDate date) {
        this.id = id;
        this.nomeProjeto = nomeProjeto;
        this.date = date;
    }

    public List<ValorBase> getValorBase() {
        return valorBase;
    }

    /**
     *
     * @param valorBase
     */
    public void setValorBase(List<ValorBase> valorBase) {
        this.valorBase = valorBase;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<String, Integer> getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(Map<String, Integer> funcionalidades) {
       this.funcionalidades = funcionalidades;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public boolean isFoiCompartilhado() {
        return foiCompartilhado;
    }

    public void setFoiCompartilhado(boolean foiCompartilhado) {
        this.foiCompartilhado = foiCompartilhado;
    }

    public List<Usuario> getUsuariosCompartilhados() {
        return usuariosCompartilhados;
    }

    public void setUsuariosCompartilhados(List<Usuario> usuariosCompartilhados) {
        this.usuariosCompartilhados = usuariosCompartilhados;
    }
    
    public void addProjeto(Projeto projeto){
        this.projetos.add(projeto);
    }
    
    public void addFuncionalidade(String nomeFuncionalidade, int horas){
        this.funcionalidades.put(nomeFuncionalidade, horas);
    }
}
