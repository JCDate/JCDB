/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrdenesSolicitadas;

import Modelos.AnalisisAtrasosM;
import Modelos.Usuarios;
import Servicios.AnalisisAtrasos_servicio;
import Servicios.Conexion;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AtrasosGUI extends javax.swing.JFrame {

    private final AnalisisAtrasos_servicio analisisAtrasos_servicio = new AnalisisAtrasos_servicio();
    private List<AnalisisAtrasosM> analisisatrasos;

    private final toExcel excel = new toExcel(); //Generar Excel

    TableRowSorter trs;
    Connection conexion;
    Usuarios mod;

    public AtrasosGUI() throws SQLException, ClassNotFoundException {
        initComponents();
        this.setResizable(false);
        this.setDefaultCloseOperation(0);
        this.conexion = Conexion.getInstance().getConnection();
        this.analisisAtrasos();

    }

    public AtrasosGUI(Usuarios mod) throws SQLException, ClassNotFoundException {
        this.conexion = Conexion.getInstance().getConnection();
        initComponents();
        this.mod = mod;
        this.setResizable(false);
        this.setDefaultCloseOperation(0);
        this.analisisAtrasos();

    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("jc/img/jc.png"));
        return retValue;
    }

    private void analisisAtrasos() throws SQLException {

        //Tabla analisis de atrasos
        this.analisisatrasos = this.analisisAtrasos_servicio.recuperarTodasAnalisis(conexion);
        DefaultTableModel dtm = (DefaultTableModel) jTableA.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < this.analisisatrasos.size(); i++) {
            
            dtm.addRow(new Object[]{
                this.analisisatrasos.get(i).getFechaRecibida(),
                this.analisisatrasos.get(i).getFechaVencida(),
                this.analisisatrasos.get(i).getOrden(),
                this.analisisatrasos.get(i).getCantidad_reque(),
                this.analisisatrasos.get(i).getCantidadEntregada(),
                this.analisisatrasos.get(i).getPiezasEntregar(),
                this.analisisatrasos.get(i).getConsignatario(),
                this.analisisatrasos.get(i).getItem_cliente(),
                this.analisisatrasos.get(i).getComentario(),
                this.analisisatrasos.get(i).getFechaEmbarque(),
                this.analisisatrasos.get(i).getFactura(),
                this.analisisatrasos.get(i).getEmbarques()
            });
        }
    }

    public void Filtro(int num0, int num1) {
        trs.setRowFilter(RowFilter.regexFilter(jtxtfiltro.getText(), num0, num1));
    }

    /**
     * to This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jtxtfiltro = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableA = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Wide Latin", 1, 25)); // NOI18N
        jLabel2.setText("ANALISIS DE ATRASOS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 610, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/buscar.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 80, -1, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/cancelar.png"))); // NOI18N
        jButton3.setText("CERRAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 130, 40));

        jtxtfiltro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtxtfiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtfiltroActionPerformed(evt);
            }
        });
        jtxtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtfiltroKeyTyped(evt);
            }
        });
        getContentPane().add(jtxtfiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 80, 240, 30));

        jTableA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "<html><center>FECHA<br> RECIBIDA</center></html>", "<html><center>FECHA<br> VENCIDA</center></html>", "ORDEN", "<html><center>CANTIDAD<br> SOLICITADA</center></html>", "<html><center>CANTIDAD<br> ENTREGADA</center></html>", "<html><center>PIEZAS A<br> ENTREGAR</center></html>", "CONSIGNATARIO", "COMPONENTE", "COMENTARIO", "<html><center>FECHA<br> EMBARQUE</center></html>", "FACTURA", "EMBARQUES"
            }
        ));
        jTableA.setName(""); // NOI18N
        jScrollPane2.setViewportView(jTableA);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 1240, 500));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/jcLogo.png"))); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/excel.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 60, 60));

        jPanel1.setBackground(new java.awt.Color(135, 206, 235));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jtxtfiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtfiltroActionPerformed
    }//GEN-LAST:event_jtxtfiltroActionPerformed

    private void jtxtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtfiltroKeyTyped
        jtxtfiltro.addKeyListener(new KeyAdapter() {
            //@Override
            public void keyReleased(final KeyEvent ke) {
                jtxtfiltro.setText(jtxtfiltro.getText().toUpperCase());
                String cadena = (jtxtfiltro.getText());
                jtxtfiltro.setText(cadena);

                Filtro(2, 7);

            }
        });
        trs = new TableRowSorter(jTableA.getModel());
        jTableA.setRowSorter(trs);

    }//GEN-LAST:event_jtxtfiltroKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        excel.WriteExcelAtrasos();
        JOptionPane.showMessageDialog(this, "LISTA ANALISIS DE ATRASOS DESCARGADA.");
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AtrasosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtrasosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtrasosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtrasosGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AtrasosGUI().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AtrasosGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AtrasosGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableA;
    private javax.swing.JTextField jtxtfiltro;
    // End of variables declaration//GEN-END:variables

}
