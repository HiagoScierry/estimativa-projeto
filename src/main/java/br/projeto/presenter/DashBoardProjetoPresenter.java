package br.projeto.presenter;

import br.projeto.model.Projeto;
import br.projeto.service.EstimaProjetoService;
import br.projeto.singleton.LogSingleton;
import br.projeto.singleton.ProjetoSingleton;
import br.projeto.singleton.UsuarioSingleton;
import br.projeto.view.DashBoardProjetoView;
import org.jfree.data.general.DefaultPieDataset;

import java.util.List;

public class DashBoardProjetoPresenter implements Observer {
    private final DashBoardProjetoView view;
    private final EstimaProjetoService estimaService;
    private final ProjetoSingleton projetoSingleton;

    public DashBoardProjetoPresenter(DashBoardProjetoView view, ProjetoSingleton projetoSingleton) {
        this.view = view;
        this.projetoSingleton = projetoSingleton;
        this.estimaService = new EstimaProjetoService();

        this.projetoSingleton.addObserver(this);
        carregarDashboard();

         LogSingleton.getInstancia().criarLog(
            "INFO",
            "ABRIR TELA DASHBOARD PROJETOS",
            UsuarioSingleton.getInstance().getUsuario().getNome() + "/" + UsuarioSingleton.getInstance().getUsuario().getEmail(),
                 String.valueOf(UsuarioSingleton.getInstance().getUsuario().getId()),
            null
        );
    }

    private void carregarDashboard() {
        List<Projeto> projetos = projetoSingleton.getProjetos();

        int totalProjetos = projetos.size();
        int diasTotais = projetos.stream()
                .mapToInt(estimaService::calcularDiasTotais)
                .sum();
        double custoTotal = projetos.stream()
                .mapToDouble(estimaService::calcularCusto)
                .sum();

        view.exibirDadosConsolidados(totalProjetos, diasTotais, custoTotal);

        DefaultPieDataset datasetCustos = gerarDatasetCustos(projetos);
        DefaultPieDataset datasetProjetos = gerarDatasetProjetos(projetos);

        view.atualizarGraficos(datasetCustos, datasetProjetos);
    }

    private DefaultPieDataset gerarDatasetCustos(List<Projeto> projetos) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Projeto projeto : projetos) {
            double custo = estimaService.calcularCusto(projeto);
            dataset.setValue(projeto.getNome(), custo);
        }
        return dataset;
    }

    private DefaultPieDataset gerarDatasetProjetos(List<Projeto> projetos) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        // A FAZER !!!
//        Map<String, Long> tipos = projetos.stream()
//                .flatMap(projeto -> projeto.getPerfis().stream())
//                .collect(Collectors.groupingBy(tipo -> tipo, Collectors.counting()));
//
//        for (Map.Entry<String, Long> entry : tipos.entrySet()) {
//            dataset.setValue(entry.getKey(), entry.getValue());
//        }
        return dataset;
    }

    @Override
    public void update(List<Projeto> projetos) {
        carregarDashboard();
    }
}
