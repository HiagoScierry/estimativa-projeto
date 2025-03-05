/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.singleton;

import br.projeto.model.Usuario;

/**
 *
 * @author hiago
 */
public class UsuarioSingleton {
    private static UsuarioSingleton instance;

    private Usuario usuario;

    private UsuarioSingleton() {
    }

    public static UsuarioSingleton getInstance() {
        if (instance == null) {
            instance = new UsuarioSingleton();
        }
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}