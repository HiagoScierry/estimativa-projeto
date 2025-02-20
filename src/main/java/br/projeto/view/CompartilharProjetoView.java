/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.projeto.view;

import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author Cauã
 */
public class CompartilharProjetoView extends javax.swing.JFrame {

    /**
     * Creates new form CompartilharProjetoViewa
     */
    public CompartilharProjetoView() {
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

        lblCompartilharProjeto = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsuariosDisponiveis = new javax.swing.JTable();
        lblDescricao = new javax.swing.JLabel();
        btnCompartilhar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCompartilharProjeto.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCompartilharProjeto.setText("COMPARTILHAR PROJETO");
        getContentPane().add(lblCompartilharProjeto, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 34, -1, -1));

        tblUsuariosDisponiveis.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblUsuariosDisponiveis.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblUsuariosDisponiveis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Nome", "E-mail"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuariosDisponiveis.setRowHeight(35);
        tblUsuariosDisponiveis.setShowHorizontalLines(true);
        tblUsuariosDisponiveis.setShowVerticalLines(true);
        tblUsuariosDisponiveis.getTableHeader().setResizingAllowed(false);
        tblUsuariosDisponiveis.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblUsuariosDisponiveis);
        if (tblUsuariosDisponiveis.getColumnModel().getColumnCount() > 0) {
            tblUsuariosDisponiveis.getColumnModel().getColumn(0).setResizable(false);
            tblUsuariosDisponiveis.getColumnModel().getColumn(0).setPreferredWidth(1);
            tblUsuariosDisponiveis.getColumnModel().getColumn(1).setResizable(false);
            tblUsuariosDisponiveis.getColumnModel().getColumn(2).setResizable(false);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 118, 818, -1));

        lblDescricao.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDescricao.setText("A seguir, selecione o(s) usuário(s) com quem compartilhará o projeto.");
        getContentPane().add(lblDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 78, -1, -1));

        btnCompartilhar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCompartilhar.setText("Compartilhar");
        getContentPane().add(btnCompartilhar, new org.netbeans.lib.awtextra.AbsoluteConstraints(433, 563, 128, -1));

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
            java.util.logging.Logger.getLogger(CompartilharProjetoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompartilharProjetoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompartilharProjetoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompartilharProjetoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompartilharProjetoView().setVisible(true);
            }
        });
    }

    public JButton getBtnCompartilhar() {
        return btnCompartilhar;
    }

    public JTable getTblUsuariosDisponiveis() {
        return tblUsuariosDisponiveis;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompartilhar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCompartilharProjeto;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JTable tblUsuariosDisponiveis;
    // End of variables declaration//GEN-END:variables
}
