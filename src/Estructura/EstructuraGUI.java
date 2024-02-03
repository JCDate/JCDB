/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructura;

import CRs.CRsGUI;
import Modelos.EstructuraM;
import Modelos.Usuarios;
import Servicios.Conexion;
import Servicios.Estructura_servicio;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class EstructuraGUI extends javax.swing.JFrame {

    private final Estructura_servicio estructura_servicio = new Estructura_servicio();
    private List<EstructuraM> estructura;

    private final toExcel excel = new toExcel();
    TableRowSorter trs;
    Usuarios mod;
    Connection conexion;

    public EstructuraGUI() throws SQLException, ClassNotFoundException {
        this.conexion = Conexion.getInstance().getConnection();
        initComponents();
        this.setResizable(false);
        this.EstructuraGUI();
        this.setDefaultCloseOperation(0);
        jButton4.setVisible(false);

        //Boton Exportar Estructura
        jButton5.setFocusPainted(false);
        jButton5.setBorderPainted(false);
        //jButton5.setContentAreaFilled(false);

    }

    public EstructuraGUI(Usuarios mod) throws SQLException, ClassNotFoundException {
        this.conexion = Conexion.getInstance().getConnection();
        initComponents();
        this.mod = mod;
        this.setResizable(false);
        this.setDefaultCloseOperation(0);
        this.EstructuraGUI();
        jButton4.setVisible(false);

        //Boton Exportar Estructura
        jButton5.setFocusPainted(false);
        jButton5.setBorderPainted(false);
        //jButton5.setContentAreaFilled(false);

        try {
            //componente, cr, calibre de datos lista de precios
            PreparedStatement pst = conexion.prepareStatement("INSERT INTO datoslistaprecios (datoslistaprecios.componente, datoslistaprecios.cr, datoslistaprecios.calibre, datoslistaprecios.blank_kg) SELECT estructura.componente, estructura.CR, estructura.calibre, consumoyantecedentes.consumo_uni FROM estructura, consumoyantecedentes WHERE consumoyantecedentes.componente_CA=estructura.componente AND NOT EXISTS ( SELECT datoslistaprecios.componente FROM datoslistaprecios WHERE datoslistaprecios.componente = estructura.componente AND datoslistaprecios.componente=consumoyantecedentes.componente_CA)");
            pst.executeUpdate();

            //Modificar calibre de datos lista de precios
            PreparedStatement pst2 = conexion.prepareStatement("UPDATE datoslistaprecios SET datoslistaprecios.calibre=(SELECT estructura.calibre FROM estructura WHERE estructura.componente= datoslistaprecios.componente )");
            pst2.executeUpdate();

            //Modificar kg/back de datos lista de precios
            PreparedStatement pst5 = conexion.prepareStatement("UPDATE datoslistaprecios SET datoslistaprecios.blank_kg=(SELECT estructura.peso_estamp FROM estructura WHERE estructura.componente= datoslistaprecios.componente )");
            pst5.executeUpdate();

            //componente datos tecnicos mp
            PreparedStatement pst3 = conexion.prepareStatement("INSERT INTO datostecnicosmp (datostecnicosmp.componente) SELECT datoslistaprecios.componente FROM datoslistaprecios WHERE NOT EXISTS ( SELECT datostecnicosmp.componente FROM datostecnicosmp WHERE datostecnicosmp.componente = datoslistaprecios.componente)");
            pst3.executeUpdate();

            //componente calculos teoricos
            PreparedStatement pst4 = conexion.prepareStatement("INSERT INTO calculosteoricos(calculosteoricos.componente) SELECT datoslistaprecios.componente FROM datoslistaprecios WHERE NOT EXISTS ( SELECT calculosteoricos.componente FROM calculosteoricos WHERE calculosteoricos.componente = datoslistaprecios.componente)");
            pst4.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRsGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (mod.getId_tipo() == 4) {
            jButton2.setEnabled(false);
            jButton4.setEnabled(false);
            jButton1.setEnabled(false);
        }
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("jc/img/jc.png"));

        return retValue;
    }

    private void EstructuraGUI() {
        try {

            this.estructura = this.estructura_servicio.recuperarTodas(Conexion.getInstance().getConnection());

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            for (int i = 0; i < this.estructura.size(); i++) {
                dtm.addRow(new Object[]{
                    this.estructura.get(i).getComponente(),
                    this.estructura.get(i).getUm(),
                    this.estructura.get(i).getOp(),
                    this.estructura.get(i).getCR(),
                    this.estructura.get(i).getCalibre(),
                    this.estructura.get(i).getPeso_estamp()
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los registros");
        }
    }

    public void Filtro() {
        int ColumnaTabla = 0;
        trs.setRowFilter(RowFilter.regexFilter(jtxtfiltro.getText(), ColumnaTabla));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jtxtfiltro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "COMPONENTE", "UM", "OP ", "C/R", "CALIBRE", "PESO DE ESTAMPA"
            }
        ));
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 1250, 510));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/1004733.png"))); // NOI18N
        jButton1.setText("AGREGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 150, 40));

        jLabel3.setFont(new java.awt.Font("Wide Latin", 1, 36)); // NOI18N
        jLabel3.setText("ESTRUCTURA");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));

        jtxtfiltro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtxtfiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtfiltroActionPerformed(evt);
            }
        });
        jtxtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtfiltroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtfiltroKeyTyped(evt);
            }
        });
        getContentPane().add(jtxtfiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 120, 230, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/buscar.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 120, -1, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/cancelar.png"))); // NOI18N
        jButton3.setText("CERRAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 680, 130, 40));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/modificar.png"))); // NOI18N
        jButton2.setText("MODIFICAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 150, 40));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/modificar.png"))); // NOI18N
        jButton4.setText("MODIFICAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 150, 40));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/excel.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 10, 60, 60));

        jLabel4.setText("EXPORTAR");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 70, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/jcLogo.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.setBackground(new java.awt.Color(135, 206, 235));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            EstructuraGUI.this.dispose();
            AgregarEstructura vista = new AgregarEstructura(mod);
            vista.setVisible(true);
            vista.setLocationRelativeTo(null);
        } catch (SQLException ex) {
            Logger.getLogger(EstructuraGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EstructuraGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtxtfiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtfiltroActionPerformed
    }//GEN-LAST:event_jtxtfiltroActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jtxtfiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtfiltroKeyReleased
    }//GEN-LAST:event_jtxtfiltroKeyReleased

    private void jtxtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtfiltroKeyTyped
        jtxtfiltro.addKeyListener(new KeyAdapter() {
            //@Override
            public void keyReleased(final KeyEvent ke) {
                String cadena = (jtxtfiltro.getText());
                jtxtfiltro.setText(cadena);
                Filtro();
            }
        });
        trs = new TableRowSorter(jTable1.getModel());
        jTable1.setRowSorter(trs);
        jButton2.setVisible(false);
        jButton4.setVisible(true);
    }//GEN-LAST:event_jtxtfiltroKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int fila_seleccionada = jTable1.getSelectedRow();
        if (fila_seleccionada >= 0) {
            EstructuraGUI.this.dispose();
            ModificarEstructura modificar = new ModificarEstructura(this.estructura.get(fila_seleccionada), mod);
            modificar.setVisible(true);
            modificar.setLocationRelativeTo(null);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una fila.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int fila_seleccionada = trs.convertRowIndexToModel(jTable1.getSelectedRow());
        if (fila_seleccionada >= 0) {
            EstructuraGUI.this.dispose();
            ModificarEstructura modificar = new ModificarEstructura(this.estructura.get(fila_seleccionada), mod);
            modificar.setVisible(true);
            modificar.setLocationRelativeTo(null);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una fila.");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        excel.WriteExcelEstructura();
        JOptionPane.showMessageDialog(this, "Datos Exportados.");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if (evt.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
            try {
                String componente = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
                String um = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
                String OP = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
                String calibre = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();

                PreparedStatement consulta;
                consulta = conexion.prepareStatement("UPDATE estructura SET um='" + um + "',OP='" + OP + "', calibre='" + calibre + "' WHERE componente='" + componente + "'");
                consulta.executeUpdate();
                JOptionPane.showMessageDialog(this, "MODIFICACIÓN EXITOSA");
            } catch (SQLException ex) {
                Logger.getLogger(EstructuraGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTable1KeyReleased

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
            java.util.logging.Logger.getLogger(EstructuraGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EstructuraGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EstructuraGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EstructuraGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new EstructuraGUI().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(EstructuraGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(EstructuraGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtxtfiltro;
    // End of variables declaration//GEN-END:variables
}
