/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefectoInspeccion;

import Modelos.DefInsM;
import Modelos.Troqueles;
import Modelos.Usuarios;
import Servicios.Conexion;
import Servicios.DefIns_servicio;
import Servicios.troqueles_servicio;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AgregarDefIns extends javax.swing.JFrame {

    private final DefIns_servicio defIns_servicio = new DefIns_servicio();
    private final DefInsM defIns;

    private final troqueles_servicio troquel_servicio = new troqueles_servicio();
    private List<Troqueles> troquel;
    Usuarios mod;

    public AgregarDefIns() {
        initComponents();
        this.setResizable(false);
        this.defIns = new DefInsM();
        this.setDefaultCloseOperation(0);
        this.Defecto();
    }

    public AgregarDefIns(Usuarios mod) {
        initComponents();
        this.mod = mod;
        this.setResizable(false);
        this.defIns = new DefInsM();
        this.setDefaultCloseOperation(0);
        this.Defecto();
    }

    public void Defecto() {
        choice1.add("-------------------------------");
        choice1.add("A. GRIETAS - ROTAS");
        choice1.add("B. MARCA DE ESTRÉS");
        choice1.add("C. INCOMPLETAS");
        choice1.add("D. DOBLEZ CON DEFECTO");
        choice1.add("E. REBABA");
        choice1.add("F. GRABADO DEFECTUOSO");
        choice1.add("G. MARCAS DE REBABA");
        choice1.add("H. OVALAMIENTO");
        choice1.add("I. EMPALMADA");
        choice1.add("J. RAYADAS");
        choice1.add("K. CÓNCAVA - CONVEXA");
        choice1.add("L. BARRENO - ROTAS");
        choice1.add("M. GOLPE");
        choice1.add("N. CHUECAS");
        choice1.add("Ñ. FLANGE DEFORME");
        choice1.add("O. BARRENOS INCOMPLETOS (TAPADOS)");
        choice1.add("P. GRABADO INVERTIDO");
    }

    public void operacion(String componente) {
        try {
            this.troquel = this.troquel_servicio.recuperarTodasDef(Conexion.getInstance().getConnection(), componente);
            for (int i = 0; i < this.troquel.size(); i++) {
                choice2.add(String.valueOf(this.troquel.get(i).getNumOperaciones()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgregarDefIns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("jc/img/jc.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        choice1 = new java.awt.Choice();
        choice2 = new java.awt.Choice();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("COMPONENTE:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("DEFECTO:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/Guardar.png"))); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(391, 140, 140, 40));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/cancelar.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, -1, 40));

        jLabel6.setFont(new java.awt.Font("Wide Latin", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("AGREGAR   DEFECTO");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 400, 50));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/jcLogo.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("OPERACIÓN:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 230, -1));

        choice1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(choice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 230, -1));

        choice2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(choice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 60, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("*");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 20, -1));

        jPanel1.setBackground(new java.awt.Color(135, 206, 235));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 270));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String componente = jTextField1.getText();
        String def = choice1.getSelectedItem();
        String op = choice2.getSelectedItem();
        if (!componente.trim().equals("") && !def.trim().equals("")) {

            int opInt = Integer.parseInt(op);

            this.defIns.setComponente(componente);
            this.defIns.setDefecto(def);
            this.defIns.setOperacion(opInt);

            try {
                this.defIns_servicio.agregar(Conexion.getInstance().getConnection(), this.defIns);
                JOptionPane.showMessageDialog(this, "DATOS GUARDADOS.");
                AgregarDefIns.this.dispose();

                DefInsGUI defIn = new DefInsGUI(mod);
                defIn.setVisible(true);
                defIn.setLocationRelativeTo(null);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(this, "Ha surgido un error y no se ha podido guardar el registro.");
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(this, "Ha surgido un error y no se ha podido guardar el registro.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "HAY CAMPOS INCOMPLETOS.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            AgregarDefIns.this.dispose();
            DefInsGUI defIn = new DefInsGUI(mod);
            defIn.setVisible(true);
            defIn.setLocationRelativeTo(null);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AgregarDefIns.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        String componente = jTextField1.getText();
        this.operacion(componente);
    }//GEN-LAST:event_jTextField1KeyTyped

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
            java.util.logging.Logger.getLogger(AgregarDefIns.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarDefIns.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarDefIns.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarDefIns.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarDefIns().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private java.awt.Choice choice2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
