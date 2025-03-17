package br.projeto.presenter;

import br.projeto.command.*;
import br.projeto.model.Projeto;
import br.projeto.model.Usuario;
import br.projeto.presenter.helpers.WindowManager;
import br.projeto.presenter.window_command.*;
import br.projeto.repository.ProjetoUsuarioCompartilhadoRepository;
import br.projeto.repository.UsuarioRepository;
import br.projeto.service.ConstrutorDeArvoreNavegacaoService;
import br.projeto.service.NoArvoreComposite;
import br.projeto.singleton.ProjetoSingleton;
import br.projeto.singleton.UsuarioSingleton;
import br.projeto.view.GlobalWindowManager;
import br.projeto.view.PrincipalView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;

public final class PrincipalPresenter implements Observer {
    private final PrincipalView view;
    private final ProjetoSingleton projetoSingleton;
    private final ConstrutorDeArvoreNavegacaoService construtorDeArvoreNavegacaoService;
    private final Map<String, ProjetoCommand> comandos;
    private final ProjetoUsuarioCompartilhadoRepository projetoCompartilhadoRepo;
    private final UsuarioRepository usuarioRepo;
    private final List<WindowCommand> windowCommands = new ArrayList<>();
    private UsuarioSingleton usuarioLogado;

    public PrincipalPresenter() {
        this.view = new PrincipalView();
        this.projetoSingleton = ProjetoSingleton.getInstance();
        this.projetoSingleton.addObserver(this);
        this.usuarioLogado = UsuarioSingleton.getInstance();
        this.projetoCompartilhadoRepo = new ProjetoUsuarioCompartilhadoRepository();
        this.usuarioRepo = new UsuarioRepository();
        
        view.getLblNomeUsuario().setText(usuarioLogado.getUsuario().getNome());

        this.construtorDeArvoreNavegacaoService = new ConstrutorDeArvoreNavegacaoService();

        GlobalWindowManager.initialize(view);

        this.comandos = inicializarComandos();

        inicializarEExecutarWindowCommands();
        view.setVisible(true);
    }

    private void inicializarEExecutarWindowCommands() {
        Arrays.asList(
                new ConfigurarViewCommand(this),
                new ConfigurarMenuJanelaCommand(this),
                new SetLookAndFeelCommand()
        ).forEach(WindowCommand::execute);
    }


    private Map<String, ProjetoCommand> inicializarComandos() {
        
        Map<String, ProjetoCommand> comandos = new HashMap<>();
        comandos.put("Principal", new AbrirDashboardProjetoCommand(view.getDesktop(), projetoSingleton));
        comandos.put("Usuário", new AbrirGerenciadorUsuarioCommand(view.getDesktop(), "Usuário"));
        comandos.put("Ver perfis de projeto", new AbrirPerfisDeProjetoCommand(view.getDesktop(), "Ver Perfis de Projetos"));
        comandos.put("Elaborar estimativa", new CriarEstimativaProjetoCommand(view.getDesktop(), "Elaborar estimativa"));
        comandos.put("Visualizar estimativa", new VisualizarEstimativaProjetoCommand(view.getDesktop(), "Visualizar estimativa"));
        comandos.put("Compartilhar projeto de estimativa", new CompartilharProjetoCommand(projetoSingleton, view.getDesktop()));
        comandos.put("Exportar projeto de estimativa", new ExportarProjetoCommand(projetoSingleton));
        comandos.put("Novo projeto", new CriarProjetoProjetoCommand(projetoSingleton));
        comandos.put("Configurações", new AbrirConfiguracaoProjetoCommand(view.getDesktop(), "Configurações"));
        comandos.put("Excluir projeto", new ExcluirProjetoProjetoCommand(projetoSingleton));
        comandos.put("Abrir detalhes", new AbrirDetalhesProjetoProjetoCommand(projetoSingleton, view.getDesktop()));
        return comandos;
    }

    public void configurarArvore() {
        NoArvoreComposite raiz = construtorDeArvoreNavegacaoService.criarNo("Principal", "principal", comandos.get("Principal"));
        NoArvoreComposite noUsuario = construtorDeArvoreNavegacaoService.criarNo("Usuário", "usuario", comandos.get("Usuário"));
        NoArvoreComposite noPerfis = construtorDeArvoreNavegacaoService.criarNo("Ver perfis de projeto", "perfil", comandos.get("Ver perfis de projeto"));
        NoArvoreComposite noProjetos = construtorDeArvoreNavegacaoService.criarNo("Projetos", "projeto", null);

        noProjetos.setMenuContextual(() -> {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem novoProjetoItem = new JMenuItem("Novo Projeto");
            novoProjetoItem.addActionListener(e -> {
                ProjetoCommand cmd = comandos.get("Novo projeto");
                if (cmd != null) {
                    cmd.execute();
                }
            });
            menu.add(novoProjetoItem);
            return menu;
        });

        raiz.adicionarFilho(noUsuario);
        raiz.adicionarFilho(noPerfis);
        raiz.adicionarFilho(noProjetos);

        List<Projeto> listaProjetos = projetoSingleton.getProjetos();
        int totalProjetos = 1;

        for (final Projeto projeto : listaProjetos) {
            CriarEstimativaProjetoCommand cmdElaborarEstimativa = new CriarEstimativaProjetoCommand(view.getDesktop(), "Elaborar estimativa");
            VisualizarEstimativaProjetoCommand cmdVisualizar = new VisualizarEstimativaProjetoCommand(view.getDesktop(), "Visualizar estimativa");
            ExportarProjetoCommand cmdExportar = new ExportarProjetoCommand(projetoSingleton);
            CompartilharProjetoCommand cmdCompartilhar = new CompartilharProjetoCommand(projetoSingleton, view.getDesktop());
            AbrirDetalhesProjetoProjetoCommand cmdDetalhes = new AbrirDetalhesProjetoProjetoCommand(projetoSingleton, view.getDesktop()) {
                @Override
                public void execute() {
                    String tituloJanela = "Detalhes do Projeto: " + projeto.getNome();
                    WindowManager windowManager = WindowManager.getInstance();

                    if (!windowManager.isFrameAberto(tituloJanela)) {
                        super.execute();
                        bloquearMinimizacao(tituloJanela);
                    } else {
                        windowManager.bringToFront(tituloJanela);
                    }
                }
            };

            cmdElaborarEstimativa.setProjetoId(projeto.getId());
            cmdExportar.setProjetoId(projeto.getId());
            cmdDetalhes.setProjetoId(projeto.getId());
            cmdDetalhes.setProjetoNome(projeto.getNome());
            cmdCompartilhar.setProjetoId(projeto.getId());
            cmdVisualizar.setProjetoId(projeto.getId());
            
           boolean ehCriador = projetoCompartilhadoRepo.verificarSeEhCriador(projeto.getId(), usuarioLogado.getUsuario().getId());
           String nomeProjeto = projeto.getNome();

           if (!ehCriador) {
               List<Integer> usuariosDoProjeto = projetoCompartilhadoRepo.obterUsuariosDoProjeto(projeto.getId());

               if (!usuariosDoProjeto.isEmpty()) {
                   String nomeDono = "Desconhecido";

                   for (int idUsuario : usuariosDoProjeto) {
                       if (projetoCompartilhadoRepo.verificarSeEhCriador(projeto.getId(), idUsuario)) {
                           Optional<Usuario> usuarioDonoOpt = usuarioRepo.buscarPorId(idUsuario);
                           if (usuarioDonoOpt.isPresent()) {
                               nomeDono = usuarioDonoOpt.get().getNome();
                               break;
                           }
                       }
                   }

                   nomeProjeto = "Compartilhado por " + nomeDono;
               }
           }
            
            NoArvoreComposite noProjeto = construtorDeArvoreNavegacaoService.criarNo(nomeProjeto, "projeto", cmdDetalhes);
            adicionarMenuContextual(projeto, noProjeto);

            if (ehCriador) {

                if(projeto.getEstimativa() == null){
                    noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Elaborar estimativa", "action", cmdElaborarEstimativa));
                }


                noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Visualizar estimativa", "action", cmdVisualizar));
                noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Compartilhar projeto de estimativa", "action", cmdCompartilhar));
                noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Exportar projeto de estimativa", "action", cmdExportar));
            } else {
                noProjeto.adicionarFilho(construtorDeArvoreNavegacaoService.criarNo("Visualizar estimativa", "action", comandos.get("Visualizar estimativa")));
            }
            noProjetos.adicionarFilho(noProjeto);
            totalProjetos++;
        }
        
        DefaultMutableTreeNode modeloArvore = construtorDeArvoreNavegacaoService.converterParaNoMutavel(raiz);
        JTree arvore = construtorDeArvoreNavegacaoService.criarJTreeDoModelo(modeloArvore);
        view.setTree(arvore);
    }

    private void adicionarMenuContextual(Projeto projeto, NoArvoreComposite noProjeto) {
        noProjeto.setMenuContextual(() -> {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem excluirProjetoItem = new JMenuItem("Excluir Projeto");
            excluirProjetoItem.addActionListener(e -> {
                ProjetoCommand cmdExcluir = new ExcluirProjetoProjetoCommand(projetoSingleton, projeto);
                cmdExcluir.execute();
            });
            menu.add(excluirProjetoItem);
            return menu;
        });
    }

    @Override
    public void update(final List<Projeto> listaProjetos) {
        SwingUtilities.invokeLater(() -> {
            WindowCommand fecharJanelasCommand = new FecharJanelasRelacionadasCommand(view.getDesktop(), listaProjetos);
            fecharJanelasCommand.execute();
            configurarArvore();
        });
    }

    private void bloquearMinimizacao(String titulo) {
        JInternalFrame[] frames = view.getDesktop().getAllFrames();
        for (JInternalFrame frame : frames) {
            if (frame.getTitle().equals(titulo)) {
                frame.setIconifiable(false);
            }
        }
    }

    public void restaurarJanelas() {
        DesktopMemento memento = Zelador.getInstance().restaurarEstado();
        if (memento != null) {
            memento.restore(getView().getDesktop());
            getView().revalidate();
            getView().repaint();
        } else {
            new MostrarMensagemProjetoCommand("Nenhum estado anterior salvo para restaurar.").execute();
        }
    }

    public List<Projeto> carregarProjetosDatabase() {
        return projetoSingleton.getProjetos();
    }

    public Map<String, ProjetoCommand> getComandos() {
        return comandos;
    }

    public PrincipalView getView() {
        return view;
    }
}
