/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.projeto.repository;

import br.projeto.dao.DaoUtil;
import br.projeto.dao.interfaces.IUsuarioDAO;
import br.projeto.model.Usuario;
import br.projeto.repository.interfaces.IUsuarioRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author hiago
 */
public class UsuarioRepository implements IUsuarioRepository{

    private DaoUtil daoUtil;
    private IUsuarioDAO usuarioDAO;
    
    public UsuarioRepository(){
        daoUtil = DaoUtil.getInstance();
        usuarioDAO = daoUtil.getUsuarioDao();
    }
    
    @Override
    public Optional<Usuario> autenticar(String email, String senha) {
        return usuarioDAO.autenticar(email, senha);
    }
    
    public void cadatrarUsuario(Usuario usuario){
        usuarioDAO.inserir(usuario);
    }

    @Override
    public  Optional<Usuario> buscarPorEmail(String email) {
        return usuarioDAO.buscarPorEmail(email);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioDAO.listarTodos();
    }

}
