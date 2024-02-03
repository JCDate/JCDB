/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MateriaPrimaRec;

import Modelos.InventarioM;
import Modelos.MateriaPrimaRecM;
import Modelos.ProveedoresM;
import Modelos.Usuarios;
import Servicios.Conexion;
import Servicios.Inventario_servicio;
import Servicios.MateriaPrimaRec_servicio;
import Servicios.Proveedores_servicio;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AgregarMateriaPrimaRec extends javax.swing.JFrame {

    private final MateriaPrimaRec_servicio materiaprima_servicio = new MateriaPrimaRec_servicio();
    private final MateriaPrimaRecM materiaprima;

    private final Inventario_servicio inventario_servicio = new Inventario_servicio();
    private List<InventarioM> inventario;
    private List<ProveedoresM> proveedores;

    private final Proveedores_servicio proveedores_servicio = new Proveedores_servicio();
    private List<ProveedoresM> prov;
    Usuarios mod;
    Connection conexion;
    
    public AgregarMateriaPrimaRec() throws SQLException, ClassNotFoundException {
        this.conexion = Conexion.getInstance().getConnection();
        initComponents();
        this.setResizable(false);
        this.materiaprima = new MateriaPrimaRecM();
        this.MPFiltro();
        this.setDefaultCloseOperation(0);
    }

    public AgregarMateriaPrimaRec(Usuarios mod) throws SQLException, ClassNotFoundException {
        this.conexion = Conexion.getInstance().getConnection();
        initComponents();
        this.setResizable(false);
        this.materiaprima = new MateriaPrimaRecM();
        this.mod = mod;
        this.MPFiltro();
        this.setDefaultCloseOperation(0);
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("jc/img/jc.png"));

        return retValue;
    }

    private void MPFiltro() {
        try {
            this.inventario = this.inventario_servicio.recuperarTodas(conexion);
            chcCalibre.add("----------------------");
            for (int i = 0; i < this.inventario.size(); i++) {
                chcCalibre.add(this.inventario.get(i).getCalibre());
            }

//            this.proveedores = this.proveedores_servicio.recuperarTodas(Conexion.obtener());
//            chcProveedor.add("----------------------");
//            for (int i = 0; i < this.proveedores.size(); i++) {
//                chcProveedor.add(string);
//            }
        } catch (SQLException ex) {
            Logger.getLogger(AgregarMateriaPrimaRec.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void guardar() {
        String ordenCompra = txtOrdenCompra.getText();
        String calibre = chcCalibre.getSelectedItem();
        String proveedor = chcProveedor.getSelectedItem();
        String kgSolicitados = txtKgSolicitados.getText();

        if (!calibre.trim().equals("----------------------")) {

            int a = Integer.parseInt(ordenCompra);
            float b = Float.parseFloat(kgSolicitados);

            this.materiaprima.setOrdenCompra(a);
            this.materiaprima.setCalibre(calibre);
            this.materiaprima.setProveedores(proveedor);
            this.materiaprima.setKgSolicitados(b);

            try {
                this.materiaprima_servicio.agregar(conexion, this.materiaprima);
                AgregarMateriaPrimaRec.this.dispose();
                MateriaPirmaRecGUI mpr = new MateriaPirmaRecGUI(mod);
                mpr.setVisible(true);
                mpr.setLocationRelativeTo(null);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(this, "Ha surgido un error y no se ha podido guardar el registro.");
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(this, "Ha surgido un error y no se ha podido guardar el registro.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "INGRESAR CALIBRE.");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblOrdenCompra = new javax.swing.JLabel();
        lblProveedor = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        lblAgregar = new javax.swing.JLabel();
        lblKgSolicitados = new javax.swing.JLabel();
        txtKgSolicitados = new javax.swing.JTextField();
        lblCalibre = new javax.swing.JLabel();
        txtOrdenCompra = new javax.swing.JTextField();
        lblJCIcono = new javax.swing.JLabel();
        chcCalibre = new java.awt.Choice();
        chcProveedor = new java.awt.Choice();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblOrdenCompra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblOrdenCompra.setText("ORDEN COMPRA:");
        getContentPane().add(lblOrdenCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 130, -1));

        lblProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblProveedor.setText("PROVEEDOR:");
        getContentPane().add(lblProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 110, -1));

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/Guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 140, -1));

        btnCerrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/cancelar.png"))); // NOI18N
        btnCerrar.setText("CANCELAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, -1, -1));

        lblAgregar.setFont(new java.awt.Font("Wide Latin", 1, 36)); // NOI18N
        lblAgregar.setText("AGREGAR");
        getContentPane().add(lblAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 400, 50));

        lblKgSolicitados.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblKgSolicitados.setText("KG. SOLICITADOS:");
        getContentPane().add(lblKgSolicitados, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 150, -1));

        txtKgSolicitados.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtKgSolicitados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKgSolicitadosActionPerformed(evt);
            }
        });
        getContentPane().add(txtKgSolicitados, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 280, -1));

        lblCalibre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCalibre.setText("CALIBRE:");
        getContentPane().add(lblCalibre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 80, -1));

        txtOrdenCompra.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtOrdenCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrdenCompraActionPerformed(evt);
            }
        });
        txtOrdenCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOrdenCompraKeyTyped(evt);
            }
        });
        getContentPane().add(txtOrdenCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 280, -1));

        lblJCIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/jcLogo.png"))); // NOI18N
        getContentPane().add(lblJCIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        chcCalibre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        chcCalibre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chcCalibreItemStateChanged(evt);
            }
        });
        getContentPane().add(chcCalibre, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 280, -1));

        chcProveedor.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        chcProveedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chcProveedorItemStateChanged(evt);
            }
        });
        getContentPane().add(chcProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 280, -1));

        jPanel1.setBackground(new java.awt.Color(135, 206, 235));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 390));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        try {
            AgregarMateriaPrimaRec.this.dispose();
            MateriaPirmaRecGUI mpr = new MateriaPirmaRecGUI(mod);
            mpr.setVisible(true);
            mpr.setLocationRelativeTo(null);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AgregarMateriaPrimaRec.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtKgSolicitadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKgSolicitadosActionPerformed
    }//GEN-LAST:event_txtKgSolicitadosActionPerformed

    private void txtOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrdenCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrdenCompraActionPerformed

    private void txtOrdenCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrdenCompraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrdenCompraKeyTyped

    private void chcCalibreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chcCalibreItemStateChanged
        try {
            Statement comando = conexion.createStatement();

            String calibre = chcCalibre.getSelectedItem();
            ResultSet registro = comando.executeQuery("SELECT nombre FROM proveedores WHERE calibre ='" + calibre + "'");
            while (registro.next()) {
                String columna = registro.getString("nombre");
                chcProveedor.addItem(columna);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgregarMateriaPrimaRec.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_chcCalibreItemStateChanged

    private void chcProveedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chcProveedorItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_chcProveedorItemStateChanged

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
            java.util.logging.Logger.getLogger(AgregarMateriaPrimaRec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarMateriaPrimaRec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarMateriaPrimaRec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarMateriaPrimaRec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new AgregarMateriaPrimaRec().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(AgregarMateriaPrimaRec.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgregarMateriaPrimaRec.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private java.awt.Choice chcCalibre;
    private java.awt.Choice chcProveedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAgregar;
    private javax.swing.JLabel lblCalibre;
    private javax.swing.JLabel lblJCIcono;
    private javax.swing.JLabel lblKgSolicitados;
    private javax.swing.JLabel lblOrdenCompra;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JTextField txtKgSolicitados;
    private javax.swing.JTextField txtOrdenCompra;
    // End of variables declaration//GEN-END:variables
}
