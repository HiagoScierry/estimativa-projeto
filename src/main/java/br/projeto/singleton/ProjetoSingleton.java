package br.projeto.singleton;

import br.projeto.model.Projeto;
import br.projeto.model.Subject;
import br.projeto.presenter.Observer;
import br.projeto.repository.ProjetoRepository;
import br.projeto.repository.ProjetoUsuarioCompartilhadoRepository;

import java.util.ArrayList;
import java.util.List;

public class ProjetoSingleton implements Subject {
    private UsuarioSingleton usuarioSingleton;
    private ProjetoRepository projetoRepository;
    private ProjetoUsuarioCompartilhadoRepository projetoUsuarioCompartilhadoRepository;
    private int idProjetoAtual;
    private static ProjetoSingleton instance;
    private final List<Projeto> projetos;
    private final List<Observer> observers;

    private ProjetoSingleton() {
        usuarioSingleton = UsuarioSingleton.getInstance();
        projetoRepository = new ProjetoRepository();
        projetoUsuarioCompartilhadoRepository = new ProjetoUsuarioCompartilhadoRepository();
        projetos = carregarProjetosRepository();
        observers = new ArrayList<>();

    }

    public static ProjetoSingleton getInstance() {
        if (instance == null){
            instance = new ProjetoSingleton();
        }

        return instance;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos.clear();
        this.projetos.addAll(projetos);
        notifyObservers();
    }

    public boolean removerProjetoPorId(int id) {
        boolean removido = projetos.removeIf(projeto -> projeto.getId() == id);
        if (removido) {
            notifyObservers();
        }
        return removido;
    }

    public void adicionarProjeto(Projeto projeto){
        projetos.add(projeto);
        projetoRepository.adicionarProjeto(projeto);
        notifyObservers();
    }

    public void atualizarProjeto(Projeto projeto){
        projetoRepository.atualizarProjeto(projeto);
        setProjetos(carregarProjetosRepository());
        notifyObservers();
    }

    private List<Projeto> carregarProjetosRepository(){
        int usuarioId = usuarioSingleton.getUsuario().getId();
        List<Projeto> lista = this.projetoRepository.listarPorUsuario(usuarioId);

        for (Projeto projeto : lista){
            System.out.println("ID: " + projeto.getId() + " NOME: " + projeto.getNome());

        }

        return lista;

    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public Projeto getProjetoPorId(int id) {
        return projetos.stream()
                .filter(projeto -> projeto.getId() == id)
                .findFirst()
                .orElse(null);
    }
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        // A FAZER : REMOVER DO DATABASE
    }

    @Override
    public void notifyObservers() {


        for (Observer observer : observers) {
            observer.update(projetos);
        }
    }
    
    public void setIdProjetoAtual(int idProjeto){
        this.idProjetoAtual = idProjeto;
    }
    
    public int getIdProjetoAtual(){
        return idProjetoAtual;
    }
}
