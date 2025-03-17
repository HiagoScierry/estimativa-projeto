package br.projeto.command;

import br.projeto.model.Projeto;
import br.projeto.repository.ProjetoRepository;
import br.projeto.singleton.ProjetoSingleton;

import javax.swing.*;

public class ExcluirProjetoProjetoCommand implements ProjetoCommand {
    private ProjetoSingleton projetoSingleton;
    private Projeto projeto;
    private ProjetoRepository projetoRepository;

    public ExcluirProjetoProjetoCommand(ProjetoSingleton projetoSingleton) {
        this.projetoSingleton = projetoSingleton;
        this.projetoRepository = new ProjetoRepository();
    }

    public ExcluirProjetoProjetoCommand(ProjetoSingleton projetoSingleton, Projeto projeto) {
        this.projetoSingleton = projetoSingleton;
        this.projeto = projeto;
        this.projetoRepository = new ProjetoRepository();
    }


    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @Override
    public void execute() {
        if (projeto == null) {
            new MostrarMensagemProjetoCommand("Projeto não definido.").execute();
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(
                null,
                "Deseja realmente excluir o projeto \"" + projeto.getNome() + "\"?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacao == JOptionPane.YES_OPTION) {
            boolean removido = projetoSingleton.removerProjetoPorId(projeto.getId());
            projetoRepository.removerProjeto(projeto.getId());
            if (removido) {

                new MostrarMensagemProjetoCommand("Projeto \"" + projeto.getNome() + "\" removido com sucesso!").execute();
            } else {
                new MostrarMensagemProjetoCommand("Erro ao remover o projeto \"" + projeto.getNome() + "\".").execute();
            }
        }
    }
}
