/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package nhom3.huce.ttnt_nhom3;

import ParserFile.ParserFile;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MAI NGOC DOAN
 */
public class App extends javax.swing.JFrame implements Subject{

    /**
     * Creates new form App
     */
    public App() {
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

        javax.swing.JPanel jPanel7 = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        jResultArea = new javax.swing.JTextArea();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        jStartBox = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        jDesBox = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        jDepthText = new javax.swing.JTextField();
        jSearchingBtn = new javax.swing.JButton();
        javax.swing.JPanel jPanel6 = new javax.swing.JPanel();
        jImportGraphBtn = new javax.swing.JButton();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        jProcessTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jResultArea.setColumns(20);
        jResultArea.setRows(5);
        jScrollPane2.setViewportView(jResultArea);

        jPanel7.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Result/Path");
        jPanel7.add(jLabel6, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel7, java.awt.BorderLayout.LINE_END);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Iterative Deepening Search");
        jPanel2.add(jLabel3);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        jPanel4.setMinimumSize(new java.awt.Dimension(447, 74));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel2.setText("Start");
        jPanel4.add(jLabel2);

        jStartBox.setMinimumSize(new java.awt.Dimension(100, 27));
        jPanel4.add(jStartBox);

        jLabel1.setText("Destination");
        jPanel4.add(jLabel1);

        jPanel4.add(jDesBox);

        jLabel4.setText("Depth");
        jPanel4.add(jLabel4);

        jDepthText.setColumns(5);
        jPanel4.add(jDepthText);

        jSearchingBtn.setText("Go");
        jSearchingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchingBtnActionPerformed(evt);
            }
        });
        jPanel4.add(jSearchingBtn);

        jPanel1.add(jPanel4);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        jImportGraphBtn.setText("Import");
        jImportGraphBtn.setActionCommand("");
        jImportGraphBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jImportGraphBtnActionPerformed(evt);
            }
        });
        jPanel6.add(jImportGraphBtn);

        jPanel1.add(jPanel6);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Process");
        jPanel5.add(jLabel5, java.awt.BorderLayout.PAGE_START);

        jProcessTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Step", "Current Node", "Depth", "Children", "Open", "Close"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jProcessTable);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(878, 493));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jSearchingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchingBtnActionPerformed
        
        if (graph == null) {
            return;
        }
        
        String start = (String) this.jStartBox.getSelectedItem();
        String end = (String) this.jDesBox.getSelectedItem();
        Integer depth = Integer.parseInt( this.jDepthText.getText() );
        
        graph.setMaxDepth(depth);
        this.jResultArea.setText(graph.travel(start, end));

        this.insertDataToTable(graph.getTable(), jProcessTable);
        graph.reset();
        
    }//GEN-LAST:event_jSearchingBtnActionPerformed

    private void jImportGraphBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jImportGraphBtnActionPerformed
        
        ImportFile fileChooser = new ImportFile();
        fileChooser.observe(this);
        fileChooser.setLocationRelativeTo(null);
        fileChooser.setVisible(true);
        
    }//GEN-LAST:event_jImportGraphBtnActionPerformed

    private void insertDataToTable(ArrayList<ArrayList<String>> data, JTable toTable) {

        int stt = 1;
       
        var tableModel = (DefaultTableModel) toTable.getModel();
        tableModel.setRowCount(0);
        for (var row : data) {
            String[] rowData = new String[]{
                stt + "",
                row.get(0),
                row.get(1),
                row.get(2),
                row.get(3),
                row.get(4),};
            stt++;
            tableModel.addRow(rowData);
        }
    }

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
            java.util.logging.Logger.getLogger(App.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jDepthText;
    private javax.swing.JComboBox<String> jDesBox;
    private javax.swing.JButton jImportGraphBtn;
    private javax.swing.JTable jProcessTable;
    private javax.swing.JTextArea jResultArea;
    private javax.swing.JButton jSearchingBtn;
    private javax.swing.JComboBox<String> jStartBox;
    // End of variables declaration//GEN-END:variables

    private IDS graph;
    @Override
    public void update(File file) {
        
        ParserFile parserFile = new ParserFile(file);
       
        this.graph =  parserFile.getGraph();
        
        // co danh sach cac nut:
        var listNode = this.graph.get().keySet();
        this.jStartBox.setModel( new DefaultComboBoxModel(listNode.toArray()) );        
        this.jDesBox.setModel( new DefaultComboBoxModel(listNode.toArray()) );
        
    }
}
