/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.projeto.view;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JTable;

/**
 *
 * @author Cauã
 */
public class PerfisDeProjetoView extends JInternalFrame {

    /**
     * Creates new form PerfisDeProjetoViewa
     */
    public PerfisDeProjetoView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPerfisDeProjeto = new javax.swing.JTable();
        lblDescricao1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblDescricao2 = new javax.swing.JLabel();
        btnCriarProjeto = new javax.swing.JButton();
        btnCriarNovoPerfilProjeto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setText("PERFIS DE PROJETO");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 23, -1, -1));

        tblPerfisDeProjeto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblPerfisDeProjeto.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblPerfisDeProjeto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Nome do projeto", "Tipo de aplicação", "Tempo gasto", "Custo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPerfisDeProjeto.setRowHeight(35);
        tblPerfisDeProjeto.setShowHorizontalLines(true);
        tblPerfisDeProjeto.setShowVerticalLines(true);
        tblPerfisDeProjeto.getTableHeader().setResizingAllowed(false);
        tblPerfisDeProjeto.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblPerfisDeProjeto);
        if (tblPerfisDeProjeto.getColumnModel().getColumnCount() > 0) {
            tblPerfisDeProjeto.getColumnModel().getColumn(0).setResizable(false);
            tblPerfisDeProjeto.getColumnModel().getColumn(0).setPreferredWidth(1);
            tblPerfisDeProjeto.getColumnModel().getColumn(1).setResizable(false);
            tblPerfisDeProjeto.getColumnModel().getColumn(2).setResizable(false);
            tblPerfisDeProjeto.getColumnModel().getColumn(3).setResizable(false);
            tblPerfisDeProjeto.getColumnModel().getColumn(4).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 154, 812, -1));

        lblDescricao1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDescricao1.setText("1. A seguir, selecione qual(is) perfil(is) de projeto você deseja inserir em seu novo projeto. Após isso, clique no botão \"Criar projeto\".");
        lblDescricao1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(lblDescricao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 73, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 128, -1, -1));

        lblDescricao2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDescricao2.setText("2. Caso queira criar um novo perfil de projeto, clique no botão \"Criar novo perfil de projeto\".");
        getContentPane().add(lblDescricao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 101, -1, -1));

        btnCriarProjeto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCriarProjeto.setText("Criar projeto");
        getContentPane().add(btnCriarProjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(678, 609, -1, -1));

        btnCriarNovoPerfilProjeto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCriarNovoPerfilProjeto.setText("Criar novo perfil de projeto");
        getContentPane().add(btnCriarNovoPerfilProjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 609, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PerfisDeProjetoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PerfisDeProjetoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PerfisDeProjetoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PerfisDeProjetoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PerfisDeProjetoView().setVisible(true);
            }
        });
    }

    public JButton getBtnCriarNovoPerfilProjeto() {
        return btnCriarNovoPerfilProjeto;
    }

    public JButton getBtnCriarProjeto() {
        return btnCriarProjeto;
    }

    public JTable getTblPerfisDeProjeto() {
        return tblPerfisDeProjeto;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriarNovoPerfilProjeto;
    private javax.swing.JButton btnCriarProjeto;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescricao1;
    private javax.swing.JLabel lblDescricao2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblPerfisDeProjeto;
    // End of variables declaration//GEN-END:variables
}
