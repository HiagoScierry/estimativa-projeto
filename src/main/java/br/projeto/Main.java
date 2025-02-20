package br.projeto;

import br.projeto.presenter.LoginPresenter;
import br.projeto.repository.ProjetoRepositoryMock;

public class Main {

    public static void main(String[] args) {
        LoginPresenter tela =  new LoginPresenter(new ProjetoRepositoryMock());
    }
}


