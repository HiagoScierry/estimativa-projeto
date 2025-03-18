package br.projeto.presenter;

import br.projeto.model.Projeto;
import br.projeto.service.EstimaProjetoService;
import br.projeto.singleton.LogSingleton;
import br.projeto.singleton.ProjetoSingleton;
import br.projeto.singleton.UsuarioSingleton;
import br.projeto.view.DashBoardProjetoView;
import org.jfree.data.general.DefaultPieDataset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, Long> tipos = new HashMap<String, Long>();

        for (Projeto projeto : projetos) {
            StringBuilder tipo = new StringBuilder();

            if (!projeto.getFuncionalidadesAndroid().isEmpty()) {
                System.out.println("Adicionando Android");
                tipo.append("Android");
            }

            if (!projeto.getFuncionalidadesIOS().isEmpty()) {
                System.out.println("Adicionando iOS");
                if (tipo.length() > 0) {
                    tipo.append(" e ");
                }
                tipo.append("iOS");
            }

            if (!projeto.getFuncionalidadesWebBackend().isEmpty()) {
                System.out.println("Adicionando Web");
                if (tipo.length() > 0) {
                    tipo.append(" e ");
                }
                tipo.append("Web");
            }

            tipos.put(tipo.toString(), tipos.getOrDefault(tipo.toString(), 0L) + 1);
        }



       for (Map.Entry<String, Long> entry : tipos.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());

           dataset.setValue(entry.getKey(), entry.getValue());
       }
        return dataset;
    }

    @Override
    public void update(List<Projeto> projetos) {
        carregarDashboard();
    }
}
