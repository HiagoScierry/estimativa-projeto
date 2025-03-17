package br.projeto.presenter;

import br.projeto.model.Estimativa;
import br.projeto.model.Funcionalidade;
import br.projeto.model.Projeto;
import br.projeto.model.Usuario;
import br.projeto.view.DetalheProjetoView;
import br.projeto.service.EstimaProjetoService;
import br.projeto.repository.ProjetoUsuarioCompartilhadoRepository;
import br.projeto.repository.UsuarioRepository;
import br.projeto.singleton.LogSingleton;
import br.projeto.singleton.ProjetoSingleton;
import br.projeto.singleton.UsuarioSingleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DetalheProjetoPresenter implements Observer {
    private final DetalheProjetoView view;
    private final EstimaProjetoService estimaService;
    private final ProjetoSingleton projetoSingleton;
    private final int projetoId;
    private final UsuarioSingleton usuarioSingleton;
    private final ProjetoUsuarioCompartilhadoRepository projetoUsuarioCompartilhadoRepository;
    private final UsuarioRepository usuarioRepository;

    public DetalheProjetoPresenter(DetalheProjetoView view, ProjetoSingleton projetoSingleton, int projetoId) {
        this.view = view;
        this.projetoSingleton = projetoSingleton;
        this.projetoId = projetoId;
        this.estimaService = new EstimaProjetoService();
        this.usuarioSingleton = UsuarioSingleton.getInstance();
        this.projetoUsuarioCompartilhadoRepository = new ProjetoUsuarioCompartilhadoRepository();
        this.usuarioRepository = new UsuarioRepository();

        this.projetoSingleton.addObserver(this);
        carregarDetalhesProjeto();
    }

    private void carregarDetalhesProjeto() {
        Projeto projeto = projetoSingleton.getProjetoPorId(projetoId);

        if (projeto != null) {
            carregarCabecalho(projeto);
            carregarDetalhes(projeto);
        }

        LogSingleton.getInstancia().criarLog(
            "INFO",
            "ABRIR TELA DETALHE PROJETO",
            UsuarioSingleton.getInstance().getUsuario().getNome() + "/" + UsuarioSingleton.getInstance().getUsuario().getEmail(),
            String.valueOf(UsuarioSingleton.getInstance().getUsuario().getId()),
            null
        );
    }

    private void carregarCabecalho(Projeto projeto) {
        String nomeCriador = usuarioSingleton.getUsuario().getNome();
        if (projetoUsuarioCompartilhadoRepository.verificarSeEhCriador(usuarioSingleton.getUsuario().getId(), projeto.getId())) {
            nomeCriador = usuarioSingleton.getUsuario().getNome();
        } else {
            List<Integer> usuariosDoProjeto = projetoUsuarioCompartilhadoRepository.obterUsuariosDoProjeto(projeto.getId());

            if (!usuariosDoProjeto.isEmpty()) {
                int idDono = usuariosDoProjeto.get(0);
                Optional<Usuario> usuarioDonoOpt = usuarioRepository.buscarPorId(idDono);
                nomeCriador = usuarioDonoOpt.isPresent() ? usuarioDonoOpt.get().getNome() : "Desconhecido";
            } else {
                nomeCriador = "Desconhecido";
            }
        }

        StringBuilder plataformasBuilder = new StringBuilder();

        if (!projeto.getFuncionalidadesAndroid().isEmpty()) {
            plataformasBuilder.append("Android ");
        }

        if (!projeto.getFuncionalidadesWebBackend().isEmpty()) {
            plataformasBuilder.append("Web/Backend ");
        }

        if (!projeto.getFuncionalidadesIOS().isEmpty()) {
            plataformasBuilder.append("iOS");
        }

        String plataformasFinal = plataformasBuilder.toString();

        view.atualizarCabecalho(
            projeto.getNome(),
            nomeCriador,
            projeto.getDataCriacao(),
            plataformasFinal,
            projeto.getStatus()
        );
    }

    private void carregarDetalhes(Projeto projeto) {
        Map<Integer, Object[]> funcionalidades = new HashMap<>();

        // Adicionar funcionalidade na lista com o prefixo da plataforma antes do nome

        for (Funcionalidade funcionalidade : projeto.getFuncionalidadesWebBackend()) {
            funcionalidades.put(funcionalidade.getId(), new Object[]{"WEB/BACKEND", funcionalidade.getPlataforma()+ " : " + funcionalidade.getNome(), funcionalidade.getHorasEstimadas()});
        }
        for (Funcionalidade funcionalidade : projeto.getFuncionalidadesIOS()) {
            funcionalidades.put(funcionalidade.getId(), new Object[]{"IOS", funcionalidade.getPlataforma()+ " : " + funcionalidade.getNome(), funcionalidade.getHorasEstimadas()});
        }
        for (Funcionalidade funcionalidade : projeto.getFuncionalidadesAndroid()) {
            funcionalidades.put(funcionalidade.getId(), new Object[]{"ANDROID", funcionalidade.getPlataforma()+ " : " + funcionalidade.getNome(), funcionalidade.getHorasEstimadas()});
        }

        Object[][] dadosTabela = new Object[funcionalidades.size()][3];
        int i = 0;

        for (Map.Entry<Integer, Object[]> entry : funcionalidades.entrySet()) {
            int id = entry.getKey();
            String tipoProjeto = (String) entry.getValue()[0];
            String nome = (String) entry.getValue()[1];
            int horasEstimadas = (Integer) entry.getValue()[2];

            double valorUnitario = estimaService.calcularValorUnitario(tipoProjeto, horasEstimadas);

            dadosTabela[i][0] = nome;
            dadosTabela[i][1] = horasEstimadas;
            dadosTabela[i][2] = valorUnitario;
            i++;
        }

        Estimativa estimativa = projeto.getEstimativa();
        view.atualizarTabela(dadosTabela, estimativa.getPrecoFinal());
    }

    @Override
    public void update(List<Projeto> projetos) {
         carregarDetalhesProjeto();
    }
}
