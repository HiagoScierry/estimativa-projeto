/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.projeto.view;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Cauã
 */
public class VisualizarEstimativaView extends JInternalFrame {

    /**
     * Creates new form VisualizarEstimativaView
     */
    public VisualizarEstimativaView() {
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
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane4 = new javax.swing.JScrollPane();
        tblValoresFinais = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTaxasExtras = new javax.swing.JTable();
        lblVisualizarEstimativa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFuncionalidadesProjeto = new javax.swing.JTable();
        lblPlataformas = new javax.swing.JLabel();
        txtPlataformas = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        tblValoresFinais.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblValoresFinais.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblValoresFinais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Valor", null},
                {"+ Taxas Extras", null},
                {"Imposto  Calculado", null},
                {"+ Impostos", null},
                {"Lucro Calculado", null},
                {"Dias", null},
                {"Meses", null},
                {"Preço Final ao Cliente", null},
                {"Média por Mês", null}
            },
            new String [] {
                "Descrição", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblValoresFinais.setRowHeight(50);
        tblValoresFinais.setShowHorizontalLines(true);
        tblValoresFinais.setShowVerticalLines(true);
        tblValoresFinais.getTableHeader().setResizingAllowed(false);
        tblValoresFinais.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblValoresFinais);
        if (tblValoresFinais.getColumnModel().getColumnCount() > 0) {
            tblValoresFinais.getColumnModel().getColumn(0).setResizable(false);
            tblValoresFinais.getColumnModel().getColumn(1).setResizable(false);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 542;
        gridBagConstraints.ipady = 156;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(18, 10, 40, 48);
        getContentPane().add(jScrollPane4, gridBagConstraints);

        tblTaxasExtras.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblTaxasExtras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblTaxasExtras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Preço por dia de traballho  -Designer UI/UX", null},
                {"Preço por dia de traballho - Gerência de Projeto", null},
                {"Preço por dia de traballho  - Desenvolvimento", null},
                {"Custo com hardware e instalações físicas.", null},
                {"Custo com softwares.", null},
                {"Custo com riscos.", null},
                {"Custo com garantia.", null},
                {"Fundo de reserva", null},
                {"Outros custos.", null},
                {"Percentual com impostos(%)", null},
                {"Percentual de Lucro desejado (%)", null}
            },
            new String [] {
                "Taxas extras", "Valor total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTaxasExtras.setRowHeight(50);
        tblTaxasExtras.setShowHorizontalLines(true);
        tblTaxasExtras.setShowVerticalLines(true);
        tblTaxasExtras.getTableHeader().setResizingAllowed(false);
        tblTaxasExtras.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTaxasExtras);
        if (tblTaxasExtras.getColumnModel().getColumnCount() > 0) {
            tblTaxasExtras.getColumnModel().getColumn(0).setResizable(false);
            tblTaxasExtras.getColumnModel().getColumn(0).setPreferredWidth(200);
            tblTaxasExtras.getColumnModel().getColumn(1).setResizable(false);
            tblTaxasExtras.getColumnModel().getColumn(1).setPreferredWidth(20);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 542;
        gridBagConstraints.ipady = 300;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 10, 0, 48);
        getContentPane().add(jScrollPane3, gridBagConstraints);

        lblVisualizarEstimativa.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblVisualizarEstimativa.setText("VISUALIZAR ESTIMATIVA DE PROJETO");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(31, 228, 0, 0);
        getContentPane().add(lblVisualizarEstimativa, gridBagConstraints);

        tblFuncionalidadesProjeto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblFuncionalidadesProjeto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblFuncionalidadesProjeto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Funcionalidade que o aplicativo terá", "Total de dias", "Valor total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFuncionalidadesProjeto.setToolTipText("");
        tblFuncionalidadesProjeto.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tblFuncionalidadesProjeto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblFuncionalidadesProjeto.setRowHeight(50);
        tblFuncionalidadesProjeto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblFuncionalidadesProjeto.setShowHorizontalLines(true);
        tblFuncionalidadesProjeto.setShowVerticalLines(true);
        tblFuncionalidadesProjeto.setSurrendersFocusOnKeystroke(true);
        tblFuncionalidadesProjeto.getTableHeader().setResizingAllowed(false);
        tblFuncionalidadesProjeto.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblFuncionalidadesProjeto);
        if (tblFuncionalidadesProjeto.getColumnModel().getColumnCount() > 0) {
            tblFuncionalidadesProjeto.getColumnModel().getColumn(0).setResizable(false);
            tblFuncionalidadesProjeto.getColumnModel().getColumn(0).setPreferredWidth(500);
            tblFuncionalidadesProjeto.getColumnModel().getColumn(1).setResizable(false);
            tblFuncionalidadesProjeto.getColumnModel().getColumn(1).setPreferredWidth(20);
            tblFuncionalidadesProjeto.getColumnModel().getColumn(2).setResizable(false);
            tblFuncionalidadesProjeto.getColumnModel().getColumn(2).setPreferredWidth(20);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 955;
        gridBagConstraints.ipady = 500;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 50, 40, 0);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        lblPlataformas.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPlataformas.setText("Plataformas:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 50, 0, 0);
        getContentPane().add(lblPlataformas, gridBagConstraints);

        txtPlataformas.setEditable(false);
        txtPlataformas.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 170;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(32, 13, 0, 0);
        getContentPane().add(txtPlataformas, gridBagConstraints);

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
            java.util.logging.Logger.getLogger(VisualizarEstimativaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualizarEstimativaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualizarEstimativaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualizarEstimativaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualizarEstimativaView().setVisible(true);
            }
        });
    }

    public JTable getTblFuncionalidadesProjeto() {
        return tblFuncionalidadesProjeto;
    }

    public JTable getTblTaxasExtras() {
        return tblTaxasExtras;
    }

    public JTable getTblValoresFinais() {
        return tblValoresFinais;
    }

    public JTextField getTxtPlataformas() {
        return txtPlataformas;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblPlataformas;
    private javax.swing.JLabel lblVisualizarEstimativa;
    private javax.swing.JTable tblFuncionalidadesProjeto;
    private javax.swing.JTable tblTaxasExtras;
    private javax.swing.JTable tblValoresFinais;
    private javax.swing.JTextField txtPlataformas;
    // End of variables declaration//GEN-END:variables
}
