package br.projeto.command;

import br.projeto.model.*;
import br.projeto.singleton.ProjetoSingleton;
import br.projeto.singleton.UsuarioSingleton;

import java.time.LocalDate;
import java.util.*;

import javax.swing.JOptionPane;

public class CriarProjetoProjetoCommand implements ProjetoCommand {

    private static final String PREFIXO_TITULO = "Novo Projeto ";
    private final ProjetoSingleton projetoSingleton;
    private final UsuarioSingleton usuarioSingleton;

    public CriarProjetoProjetoCommand(ProjetoSingleton projetoSingleton) {
        this.projetoSingleton = projetoSingleton;
        this.usuarioSingleton = UsuarioSingleton.getInstance();
    }

    @Override
    public void execute() {
        String titulo = PREFIXO_TITULO + projetoSingleton.getProjetos().size();

        List<Funcionalidade> funcionalidades = new ArrayList<Funcionalidade>();
        NivelUI nivelUI = new NivelUI(0, "UI Inicial", 0.0);
        List<Perfil> perfis = new ArrayList<Perfil>();
        List<CustoAdicional> custoAdicional = new ArrayList<CustoAdicional>();

        Projeto projeto = new Projeto(
                0,
                titulo,
                "",
                "EM ANDAMENTO",
                false,
                perfis,
                funcionalidades,
                funcionalidades,
                funcionalidades,
                custoAdicional,
                nivelUI,
                0.0,
                0.0
        );

        projetoSingleton.adicionarProjeto(projeto);


        JOptionPane.showMessageDialog(null, "O projeto '" + titulo + "' foi criado com sucesso!", 
                                      "Projeto Criado", JOptionPane.INFORMATION_MESSAGE);

        projetoSingleton.notifyObservers();
    }
}
