package br.projeto.view;

import javax.swing.*;
import java.awt.*;

public final class PrincipalView extends JFrame {
    private JDesktopPane desktop;
    private JTree tree;
    private JScrollPane treeScrollPane;
    private JLabel lblNomeUsuario;
    

    public PrincipalView() {
        setTitle("Sistema de Estimativa de Projetos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        treeScrollPane = new JScrollPane();
        desktop = new JDesktopPane();

        JSplitPane divisoriaPainel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getTreeScrollPane(), getDesktop());
        divisoriaPainel.setDividerLocation(300);
        divisoriaPainel.setResizeWeight(0.0);
        divisoriaPainel.setContinuousLayout(true);
        divisoriaPainel.setOneTouchExpandable(true);

        add(divisoriaPainel, BorderLayout.CENTER);
        
        lblNomeUsuario = new JLabel("Nome do Usuário");
        lblNomeUsuario.setFont(new Font("Arial", Font.PLAIN, 18));
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(lblNomeUsuario);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setMainComponents(JToolBar toolBar) {
        add(toolBar, BorderLayout.NORTH);
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    public JTree getTree() {
        return tree;
    }

    public void setTree(JTree tree) {
        this.tree = tree;
        treeScrollPane.setViewportView(tree);
    }

    public JScrollPane getTreeScrollPane() {
        return treeScrollPane;
    }
    
    public JLabel getLblNomeUsuario(){
        return lblNomeUsuario;
    }
}
