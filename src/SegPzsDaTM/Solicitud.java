/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SegPzsDaTM;

import Modelos.SolicitudTMPDM;
import Modelos.Usuarios;
import Servicios.Conexion;
import Servicios.SolicitudTM_servicio;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class Solicitud extends javax.swing.JFrame {

    private final SolicitudTM_servicio op_servicio = new SolicitudTM_servicio();
    private final SolicitudTMPDM op;

    Usuarios mod;
    Connection conexion;

    public Solicitud() throws SQLException, ClassNotFoundException {
        this.conexion = Conexion.getInstance().getConnection();
        initComponents();
        this.setResizable(false);
        this.op = new SolicitudTMPDM();
        this.setDefaultCloseOperation(0);
    }

    public Solicitud(SolicitudTMPDM p_op, Usuarios mod) throws SQLException, ClassNotFoundException, ParseException {
        this.conexion = Conexion.getInstance().getConnection();
        initComponents();
        this.op = p_op;
        this.setDefaultCloseOperation(0);
        this.mod = mod;

        jTextField1.setText(this.op.getCotizacion());
        jTextField2.setText(this.op.getComponente());

        String sDate1 = this.op.getFechaSol();
        if (!sDate1.equals("")) {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            dchFechaSolicitud.setDate(date1);
        }

        String sDate2 = this.op.getEnvioCot();
        if (!sDate2.equals("")) {
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
            dchEnvioCotizacion.setDate(date2);
        }

        txtPrecioCotizacion.setText(this.op.getPrecioC());

        String sDate3 = this.op.getEnviadoSKF();
        if (!sDate3.equals("")) {
            Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate3);
            dchEnviadoSKF.setDate(date3);
        }

        String sDate4 = this.op.getAprobadoSKF();
        if (!sDate4.equals("")) {
            Date date4 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate4);
            dchAprobadoSKF.setDate(date4);
        }

        String sDate5 = this.op.getSolicitudM();
        if (!sDate5.equals("")) {
            Date date5 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate5);
            dchSolicitudMaterial.setDate(date5);
        }

        txtCostoMaterial.setText(this.op.getCostoM());

        String sDate6 = this.op.getFechaMaq();
        if (!sDate6.equals("")) {
            Date date6 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate6);
            dchFechaMaquinado.setDate(date6);
        }

        String sDate7 = this.op.getTemple();
        if (!sDate7.equals("")) {
            Date date7 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate7);
            dchTemple.setDate(date7);
        }

        String sDate8 = this.op.getAjuste();
        if (!sDate8.equals("")) {
            Date date8 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate8);
            dchAjuste.setDate(date8);
        }

        String sDate9 = this.op.getProduccion();
        if (!sDate9.equals("")) {
            Date date9 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate9);
            dchProduccion.setDate(date9);
        }

        txtPiezaFacturadaSKF.setText(this.op.getPzFacturadaSKF());

        chkAprobado.setSelected(this.op.isAprobacion());
        habilitarCampos(this.op.isAprobacion());

    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("jc/img/jc.png"));

        return retValue;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPrecioCotizacion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        dchFechaSolicitud = new com.toedter.calendar.JDateChooser();
        dchEnvioCotizacion = new com.toedter.calendar.JDateChooser();
        dchEnviadoSKF = new com.toedter.calendar.JDateChooser();
        dchAprobadoSKF = new com.toedter.calendar.JDateChooser();
        dchSolicitudMaterial = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        chkAprobado = new javax.swing.JCheckBox();
        dchFechaMaquinado = new com.toedter.calendar.JDateChooser();
        txtCostoMaterial = new javax.swing.JTextField();
        dchTemple = new com.toedter.calendar.JDateChooser();
        dchAjuste = new com.toedter.calendar.JDateChooser();
        dchProduccion = new com.toedter.calendar.JDateChooser();
        txtPiezaFacturadaSKF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("COTIZACIÓN:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setEnabled(false);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 190, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("COMPONENTE:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, -1, -1));

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/Guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 220, 140, 40));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/cancelar.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 320, -1, 40));

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        jLabel6.setText("SOLICITUD");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 300, 50));

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField2.setEnabled(false);
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 190, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("<HTML>ENVIO DE <BR>COTIZACIÓN (J.C):</HTML>");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("FECHA DE SOLICITUD:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("<HTML>PRECIO DE<BR> COTIZACIÓN:</HTML>");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        txtPrecioCotizacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPrecioCotizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioCotizacionKeyTyped(evt);
            }
        });
        getContentPane().add(txtPrecioCotizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 190, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("ENVIADO A SKF:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, 20));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("APROBADO POR SKF:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("<HTML>SOLICITUD DE<BR> MATERIAL:</HTML>");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, -1, 40));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("COSTO DE MATERIAL:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, -1, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("FECHA DE MAQUINADO:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, -1, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("TEMPLE :");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, -1, 40));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("AJUSTE:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, -1, 40));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("PRODUCCIÓN:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 380, -1, 40));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("<HTML>PIEZA FACTURADA A <BR>SKF:</HTML>");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 440, -1, 40));

        dchFechaSolicitud.setDateFormatString("dd/MM/yyyy");
        getContentPane().add(dchFechaSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 190, -1));

        dchEnvioCotizacion.setDateFormatString("dd/MM/yyyy");
        getContentPane().add(dchEnvioCotizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 190, -1));

        dchEnviadoSKF.setDateFormatString("dd/MM/yyyy");
        getContentPane().add(dchEnviadoSKF, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 190, -1));

        dchAprobadoSKF.setDateFormatString("dd/MM/yyyy");
        getContentPane().add(dchAprobadoSKF, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 190, -1));

        dchSolicitudMaterial.setDateFormatString("dd/MM/yyyy");
        getContentPane().add(dchSolicitudMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 450, 190, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/jcLogo.png"))); // NOI18N
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.setBackground(new java.awt.Color(135, 206, 235));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkAprobado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chkAprobado.setText("APROBADO");
        chkAprobado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAprobadoActionPerformed(evt);
            }
        });
        jPanel1.add(chkAprobado, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, -1, -1));

        dchFechaMaquinado.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(dchFechaMaquinado, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, 190, -1));

        txtCostoMaterial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCostoMaterial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoMaterialKeyTyped(evt);
            }
        });
        jPanel1.add(txtCostoMaterial, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, 190, -1));

        dchTemple.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(dchTemple, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, 190, -1));

        dchAjuste.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(dchAjuste, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 340, 190, -1));

        dchProduccion.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(dchProduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 390, 190, -1));

        txtPiezaFacturadaSKF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPiezaFacturadaSKF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPiezaFacturadaSKFKeyTyped(evt);
            }
        });
        jPanel1.add(txtPiezaFacturadaSKF, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 440, 190, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        String cotizacion = jTextField1.getText();
        String componente = jTextField2.getText();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date sol = dchFechaSolicitud.getDate();
        String strSol = null;
        if (sol != null) {
            strSol = dateFormat.format(sol);
        } else {
            strSol = "";
        }

        Date enC = dchEnvioCotizacion.getDate();
        String strEnC = null;
        if (enC != null) {
            strEnC = dateFormat.format(enC);
        } else {
            strEnC = "";
        }

        String preC = txtPrecioCotizacion.getText();

        Date envSKF = dchEnviadoSKF.getDate();
        String strenvSKF = null;
        if (envSKF != null) {
            strenvSKF = dateFormat.format(envSKF);
        } else {
            strenvSKF = "";
        }

        Date aprSKF = dchAprobadoSKF.getDate();
        String straprSKF = null;
        if (aprSKF != null) {
            straprSKF = dateFormat.format(aprSKF);
        } else {
            straprSKF = "";
        }

        Date solM = dchSolicitudMaterial.getDate();
        String strsolM = null;
        if (solM != null) {
            strsolM = dateFormat.format(solM);
        } else {
            strsolM = "";
        }

        String cosM = txtCostoMaterial.getText();

        Date FMaqui = dchFechaMaquinado.getDate();
        String strFMaqui = null;
        if (FMaqui != null) {
            strFMaqui = dateFormat.format(FMaqui);
        } else {
            strFMaqui = "";
        }

        Date temple = dchTemple.getDate();
        String strtemple = null;
        if (temple != null) {
            strtemple = dateFormat.format(temple);
        } else {
            strtemple = "";
        }

        Date ajuste = dchAjuste.getDate();
        String strajuste = null;
        if (ajuste != null) {
            strajuste = dateFormat.format(ajuste);
        } else {
            strajuste = "";
        }

        Date prod = dchProduccion.getDate();
        String strprod = null;
        if (prod != null) {
            strprod = dateFormat.format(prod);
        } else {
            strprod = "";
        }

        String pfac = txtPiezaFacturadaSKF.getText();

        try {

            this.op.setCotizacion(cotizacion);
            this.op.setComponente(componente);
            this.op.setFechaSol(strSol);
            this.op.setEnvioCot(strEnC);
            this.op.setPrecioC(preC);
            this.op.setEnviadoSKF(strenvSKF);
            this.op.setAprobadoSKF(straprSKF);
            this.op.setSolicitudM(strsolM);
            this.op.setCostoM(cosM);
            this.op.setFechaMaq(strFMaqui);
            this.op.setTemple(strtemple);
            this.op.setAjuste(strajuste);
            this.op.setProduccion(strprod);
            this.op.setPzFacturadaSKF(pfac);
            this.op.setAprobacion(chkAprobado.isSelected());
            this.op_servicio.modificar(conexion, this.op);
            Solicitud.this.dispose();
            SegPzsDaTMGUI ant = new SegPzsDaTMGUI(mod);
            ant.setVisible(true);
            ant.setLocationRelativeTo(null);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(this, "Ha surgido un error y no se ha podido guardar el registro.");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Ha surgido un error y no se ha podido guardar el registro.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Solicitud.this.dispose();
            SegPzsDaTMGUI tr = new SegPzsDaTMGUI(mod);
            tr.setVisible(true);
            tr.setLocationRelativeTo(null);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Solicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtPiezaFacturadaSKFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPiezaFacturadaSKFKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPiezaFacturadaSKFKeyTyped

    private void txtCostoMaterialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoMaterialKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoMaterialKeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped

    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped

    }//GEN-LAST:event_jTextField1KeyTyped

    private void txtPrecioCotizacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCotizacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioCotizacionKeyTyped

    private void chkAprobadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAprobadoActionPerformed
        habilitarCampos(chkAprobado.isSelected());
    }//GEN-LAST:event_chkAprobadoActionPerformed

    private void habilitarCampos(boolean aprobado) {
        if (!aprobado) {
            dchFechaSolicitud.setEnabled(false);
            dchEnvioCotizacion.setEnabled(false);
            txtPrecioCotizacion.setEnabled(false);
            dchEnviadoSKF.setEnabled(false);
            dchAprobadoSKF.setEnabled(false);
            dchSolicitudMaterial.setEnabled(false);
            txtCostoMaterial.setEnabled(false);
            dchFechaMaquinado.setEnabled(false);
            dchTemple.setEnabled(false);
            dchAjuste.setEnabled(false);
            dchProduccion.setEnabled(false);
            txtPiezaFacturadaSKF.setEnabled(false);
        } else {
            dchFechaSolicitud.setEnabled(true);
            dchEnvioCotizacion.setEnabled(true);
            txtPrecioCotizacion.setEnabled(true);
            dchEnviadoSKF.setEnabled(true);
            dchAprobadoSKF.setEnabled(true);
            dchSolicitudMaterial.setEnabled(true);
            txtCostoMaterial.setEnabled(true);
            dchFechaMaquinado.setEnabled(true);
            dchTemple.setEnabled(true);
            dchAjuste.setEnabled(true);
            dchProduccion.setEnabled(true);
            txtPiezaFacturadaSKF.setEnabled(true);
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
            java.util.logging.Logger.getLogger(Solicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Solicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Solicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Solicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Solicitud().setVisible(true);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Solicitud.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox chkAprobado;
    private com.toedter.calendar.JDateChooser dchAjuste;
    private com.toedter.calendar.JDateChooser dchAprobadoSKF;
    private com.toedter.calendar.JDateChooser dchEnviadoSKF;
    private com.toedter.calendar.JDateChooser dchEnvioCotizacion;
    private com.toedter.calendar.JDateChooser dchFechaMaquinado;
    private com.toedter.calendar.JDateChooser dchFechaSolicitud;
    private com.toedter.calendar.JDateChooser dchProduccion;
    private com.toedter.calendar.JDateChooser dchSolicitudMaterial;
    private com.toedter.calendar.JDateChooser dchTemple;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JTextField txtCostoMaterial;
    private javax.swing.JTextField txtPiezaFacturadaSKF;
    private javax.swing.JTextField txtPrecioCotizacion;
    // End of variables declaration//GEN-END:variables
}
