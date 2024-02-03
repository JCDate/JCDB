/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrdenesSolicitadas;

import Modelos.PlanProduccionM;
import Modelos.TroqueladoresM;
import Modelos.Usuarios;
import Servicios.Conexion;
import Servicios.Troqueladores_servicio;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author JC
 */
public class PlanProduccion extends javax.swing.JFrame {

    Usuarios mod;
    Connection conexion;
    final String SELECT_CATEGORIA_SQL = "SELECT categoria FROM maquinariacategoria";
    final String SELECT_COMPONENTE_ATRASOS_PRODUCCION_SQL = "SELECT componente FROM atrasosproduccion WHERE orden=?";
    final String SELECT_CANTIDAD_ATRASOS_PRODUCCION_SQL = "SELECT cantidad FROM atrasosproduccion WHERE orden=?";
    final String SELECT_ATRASOS_PRODUCCION_SQL = "SELECT orden FROM atrasosproduccion";
    final String SELECT_OPERACION_TROQUELES_SQL = "SELECT DISTINCT numOperaciones FROM troqueles WHERE componente=?";
    final String SELECT_MAX_OPERACION_TROQUELES_SQL = "SELECT MAX(numOperaciones) FROM troqueles WHERE componente=?";
    final String SELECT_TROQUEL_SQL = "SELECT troquel FROM troqueles WHERE componente=? AND numOperaciones=?";
    final String SELECT_VOLUMEN_SQL = " SELECT tipo FROM consumoyantecedentes WHERE componente_CA=?";
    private String componente;
    DefaultTableModel tm;
    private Troqueladores_servicio ts = new Troqueladores_servicio();
    List<PlanProduccionM> listaPlan = new ArrayList<>();
    int maximo = -1;

    /**
     * Creates new form PlanProduccion
     */
    public PlanProduccion() {
        initComponents();
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("jc/img/jcLogo.png"));

        return retValue;
    }

    public PlanProduccion(Usuarios mod) throws SQLException, ClassNotFoundException {
        initComponents();

        tm = (DefaultTableModel) tblPlanProduccion.getModel();
        this.setResizable(false);
        this.setDefaultCloseOperation(0);
        this.conexion = Conexion.getInstance().getConnection();
        this.mod = mod;
        List<String> maquinas = obtenerMaquinaria();
        List<String> ordenes = obtenerOrdenes();
        List<TroqueladoresM> operadores = ts.recuperarTodas(conexion);

        // Maquinaria
        maquinas.forEach((maquina) -> {
            cbxMaquina.addItem(maquina);
        });

        // Ordenes
        cbxOrdenes.addItem("");
        ordenes.forEach((orden) -> {
            cbxOrdenes.addItem(orden);
        });

        // Ordenes
        cbxOperador.addItem("");
        operadores.forEach((operador) -> {
            cbxOperador.addItem(operador.getNombre());
        });

        cbxOrdenes.addActionListener((ActionEvent ae) -> {
            try {
                // Obtener el componente seleccionado
                String componenteSeleccionado = cbxOrdenes.getSelectedItem().toString();
                componente = obtenerComponente(componenteSeleccionado);
                txtComponente.setText(componente);

                // Obtener y establecer las operaciones en el JComboBox
                List<String> operaciones = obtenerNumOperaciones(componente);
                actualizarOperacionesComboBox(operaciones);

                // Obtener la operación seleccionada
                String operacionSeleccionada = cbxOperacion.getSelectedItem().toString();
                String troqueles = obtenerTroquel(operacionSeleccionada, componente);
                txtTroquel.setText(troqueles);

                // Obtener el tamaño correspondiente al troquel seleccionado
                String tamanio = obtenerTamanio(componenteSeleccionado);
                txtTamanioOrden.setText(tamanio);
            } catch (SQLException ex) {
                // Manejar adecuadamente la excepción
                Logger.getLogger(PlanProduccion.class.getName()).log(Level.SEVERE, null, ex);
                mostrarMensajeDeError("Error al obtener los datos.");
            }
        });

        cbxOperacion.addActionListener((ActionEvent ae) -> {
            try {
                String operacionSeleccionada = cbxOperacion.getSelectedItem().toString();
                String troqueles = obtenerTroquel(operacionSeleccionada, componente);
                txtTroquel.setText(troqueles);
            } catch (SQLException ex) {
                Logger.getLogger(PlanProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    // Método para actualizar los elementos del JComboBox
    private void actualizarOperacionesComboBox(List<String> operaciones) {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        operaciones.forEach(comboBoxModel::addElement);
        cbxOperacion.setModel(comboBoxModel);
    }

// Método para mostrar un mensaje de error al usuario
    private void mostrarMensajeDeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private List<String> obtenerMaquinaria() throws SQLException {
        List<String> listaMaquinaria = new ArrayList<>();
        try (PreparedStatement consulta = conexion.prepareStatement(SELECT_CATEGORIA_SQL);
                ResultSet resultado = consulta.executeQuery();) {
            while (resultado.next()) {
                listaMaquinaria.add(resultado.getString("categoria"));
            }
        }
        return listaMaquinaria;
    }

    private List<String> obtenerOrdenes() throws SQLException {
        List<String> listaMaquinaria = new ArrayList<>();
        try (PreparedStatement consulta = conexion.prepareStatement(SELECT_ATRASOS_PRODUCCION_SQL);
                ResultSet resultado = consulta.executeQuery();) {
            while (resultado.next()) {
                listaMaquinaria.add(resultado.getString("orden"));
            }
        }
        return listaMaquinaria;
    }

    private String obtenerComponente(String orden) throws SQLException {
        String cmpt = new String();
        try (PreparedStatement consulta = conexion.prepareStatement(SELECT_COMPONENTE_ATRASOS_PRODUCCION_SQL)) {
            consulta.setString(1, orden);
            try (ResultSet resultado = consulta.executeQuery()) {
                while (resultado.next()) {
                    cmpt = resultado.getString("componente");
                }
            }
        }
        return cmpt;
    }

    private String obtenerTamanio(String orden) throws SQLException {
        int cantidad = 0;
        try (PreparedStatement consulta = conexion.prepareStatement(SELECT_CANTIDAD_ATRASOS_PRODUCCION_SQL)) {
            consulta.setString(1, orden);
            try (ResultSet resultado = consulta.executeQuery()) {
                while (resultado.next()) {
                    cantidad = resultado.getInt("cantidad");
                }
            }
        }

        return String.valueOf(cantidad);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxMaquina = new javax.swing.JComboBox<>();
        lblJCIcono = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbxOrdenes = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbxOperacion = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtProgramacion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbxOperador = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPlanProduccion = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        txtComponente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTroquel = new javax.swing.JTextField();
        txtComentarios = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTamanioOrden = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(135, 206, 235));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Wide Latin", 1, 25)); // NOI18N
        jLabel1.setText("Plan de Producción");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Máquina:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jPanel1.add(cbxMaquina, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 170, -1));

        lblJCIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/jcLogo.png"))); // NOI18N
        jPanel1.add(lblJCIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("No. De Orden:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jPanel1.add(cbxOrdenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 130, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Operación:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jPanel1.add(cbxOperacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 150, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Programación:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Componente:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Comentarios:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));
        jPanel1.add(txtProgramacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 120, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Operador:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        jPanel1.add(cbxOperador, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 160, -1));

        tblPlanProduccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Categoría/Maquina", "No. de Orden", "Troquel", "Componente", "Volumen Alto", "Volumen Bajo", "Tamaño de Orden", "Operación", "Programación", "Comentario", "Operador"
            }
        ));
        jScrollPane2.setViewportView(tblPlanProduccion);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 111, 840, 320));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/excel.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 10, 70, 60));

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/1004733.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 260, 50));

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/boton_regresar.png"))); // NOI18N
        btnRegresar.setBorderPainted(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 140, 50));

        txtComponente.setEditable(false);
        jPanel1.add(txtComponente, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 130, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setText("Troquel:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));
        jPanel1.add(txtTroquel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 180, -1));
        jPanel1.add(txtComentarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 130, -1));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Tamaño orden:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));
        jPanel1.add(txtTamanioOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 120, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jc/img/Eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 440, 240, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        PlanProduccion.this.dispose(); // Se liberan los recursos de la interfaz
        try {
            OrdenesSolicitadasGUI osGUI = new OrdenesSolicitadasGUI(mod); // Se crea una instancia de la interfaz gráfica
            osGUI.setVisible(true); // Se hace visible la ventana
            osGUI.setLocationRelativeTo(null); // Indica que la ventana actual se abrirá al centro de la pantalla principal del sistema 
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PlanProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String maquina = getValueOrDefault(cbxMaquina.getSelectedItem().toString(), "");
        String orden = getValueOrDefault(cbxOrdenes.getSelectedItem().toString(), "");
        String cmpt = getValueOrDefault(txtComponente.getText(), "");
        String operacion = getValueOrDefault(cbxOperacion.getSelectedItem().toString(), "");
        String troquel = getValueOrDefault(txtTroquel.getText(), "");
        String tamanio = getValueOrDefault(txtTamanioOrden.getText(), "");
        String comentario = getValueOrDefault(txtComentarios.getText(), "");
        String programacion = getValueOrDefault(txtProgramacion.getText(), "");
        String operador = getValueOrDefault(cbxOperador.getSelectedItem().toString(), "");

        String tipo = "";
        String va;
        String vb;
        try (PreparedStatement consulta = conexion.prepareStatement(SELECT_VOLUMEN_SQL)) {
            consulta.setString(1, cmpt);
            try (ResultSet resultado = consulta.executeQuery()) {
                while (resultado.next()) {
                    tipo = resultado.getString("tipo");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (tipo.equals("BAJO VOLUMEN")) {
            va = "";
            vb = "X";
        } else {
            va = "X";
            vb = "";
        }

        Object[] rowData = {maquina, orden, troquel, cmpt, va, vb, tamanio, operacion, programacion, comentario, operador};
        listaPlan.add(new PlanProduccionM(maquina, orden, troquel, cmpt, va, vb, tamanio, operacion, programacion, comentario, operador));
// Agrega la fila al modelo de tabla
        tm.addRow(rowData);

    }//GEN-LAST:event_btnAgregarActionPerformed

    public String getValueOrDefault(String value, String defaultValue) {
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            generarPlan(listaPlan);
        } catch (IOException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PlanProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tblPlanProduccion.getSelectedRow(); // Se obtiene la fila seleccionada
        if (filaSeleccionada >= 0) { // Si la fila seleccionada es mayor o igual a 0
            if (filaSeleccionada != -1) { // Si el indice de la fila es diferente de -1
                // Se obtienen los valores de las celdas
                String maquina = (String) tblPlanProduccion.getValueAt(filaSeleccionada, 0);
                String orden = (String) tblPlanProduccion.getValueAt(filaSeleccionada, 1);
                //Mensaje de confirmación de eliminación
                int resp = JOptionPane.showConfirmDialog(null, "LA INFORMACIÓN SELECCIONADA SE ELIMINARÁ,¿ESTÁS DE ACUERDO?", "ALERTA", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if (resp == JOptionPane.YES_NO_OPTION) { // Si el usuario acepta eliminar...
                    int indice = 0;
                    for (PlanProduccionM plan : listaPlan) {
                        if ((plan.getMaquina() == null ? maquina == null : plan.getMaquina().equals(maquina)) && (plan.getOrden() == null ? orden == null : plan.getOrden().equals(orden))) {
                            listaPlan.remove(indice);
                            DefaultTableModel modelo = (DefaultTableModel) tblPlanProduccion.getModel();
                            modelo.removeRow(indice);
                            JOptionPane.showMessageDialog(null, "Datos Eliminados Correctamente");
                            break;
                        }
                        indice++;
                    }
                }
            }
        } else{
            JOptionPane.showMessageDialog(null, "Por favor seleccione una fila");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlanProduccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PlanProduccion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbxMaquina;
    private javax.swing.JComboBox<String> cbxOperacion;
    private javax.swing.JComboBox<String> cbxOperador;
    private javax.swing.JComboBox<String> cbxOrdenes;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblJCIcono;
    private javax.swing.JTable tblPlanProduccion;
    private javax.swing.JTextField txtComentarios;
    private javax.swing.JTextField txtComponente;
    private javax.swing.JTextField txtProgramacion;
    private javax.swing.JTextField txtTamanioOrden;
    private javax.swing.JTextField txtTroquel;
    // End of variables declaration//GEN-END:variables

    private List<String> obtenerNumOperaciones(String componente) throws SQLException {
        List<String> listaOperaciones = new ArrayList<>();
        maximo = -1;
        try (PreparedStatement consulta = conexion.prepareStatement(SELECT_OPERACION_TROQUELES_SQL)) {
            consulta.setString(1, componente);
            try (ResultSet resultado = consulta.executeQuery()) {
                while (resultado.next()) {
                    String numOperaciones = resultado.getString("numOperaciones");
                    listaOperaciones.add(numOperaciones);
                    int operacion = Integer.parseInt(numOperaciones);
                    if (operacion > maximo) {
                        maximo = operacion;
                    }
                }
            }
        }
        return listaOperaciones;
    }

    private String obtenerTroquel(String operacion, String componente) throws SQLException {
        String troquel = new String();

        try (PreparedStatement consulta = conexion.prepareStatement(SELECT_TROQUEL_SQL)) {
            consulta.setString(1, componente);
            consulta.setString(2, operacion);
            try (ResultSet resultado = consulta.executeQuery()) {
                while (resultado.next()) {
                    troquel = resultado.getString("troquel");
                }
            }
        }

        return troquel;
    }

    private void generarPlan(List<PlanProduccionM> planes) throws IOException, SQLException, ClassNotFoundException {
        String filePath = "PlanProduccion.xlsx";
        XSSFWorkbook workbook = null;
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0); // Obtén la primera hoja del libro
// Obtener la fecha actual
            Date fechaActual = new Date();

            // Definir el formato de salida
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            // Formatear la fecha actual según el formato
            String fechaFormateada = formato.format(fechaActual);

            // Fecha Actual
            Row rowFecha = sheet.getRow(4); // Obtén la fila
            Cell cellFecha = rowFecha.getCell(22); // Obtén la celda en la fila
            // Modifica el valor de la celda
            cellFecha.setCellValue(fechaFormateada);
            int i = 8;
            for (PlanProduccionM plan : planes) {
                // Máquina / Categoría
                Row rowMaquina = sheet.getRow(i); // Obtén la fila
                Cell cellMaquina = rowMaquina.getCell(20); // Obtén la celda en la fila
                // Modifica el valor de la celda
                cellMaquina.setCellValue(plan.getMaquina());

                // No de Orden
                Row rowNoOrden = sheet.getRow(i); // Obtén la fila
                Cell cellNoOrden = rowNoOrden.getCell(21); // Obtén la celda en la fila
                // Modifica el valor de la celda
                cellNoOrden.setCellValue(plan.getOrden());

                // Troquel
                Row rowTroquel = sheet.getRow(i); // Obtén la fila
                Cell cellTroquel = rowTroquel.getCell(22); // Obtén la celda en la fila
                // Modifica el valor de la celda
                cellTroquel.setCellValue(plan.getTroquel());

                // Componente
                Row rowComponente = sheet.getRow(i); // Obtén la fila
                Cell cellComponente = rowComponente.getCell(23); // Obtén la celda en la fila
                // Modifica el valor de la celda
                cellComponente.setCellValue(plan.getCmpt());

                // Volumen alto
                Row rowVA = sheet.getRow(i); // Obtén la fila
                Cell cellVA = rowVA.getCell(24); // Obtén la celda en la fila
                // Modifica el valor de la celda
                cellVA.setCellValue(plan.getVa());

                // Volumen alto
                Row rowVB = sheet.getRow(i); // Obtén la fila
                Cell cellVB = rowVB.getCell(25); // Obtén la celda en la fila
                // Modifica el valor de la celda
                cellVB.setCellValue(plan.getVb());

                // Tamaño orden
                Row rowTamanio = sheet.getRow(i); // Obtén la fila
                Cell cellTamanio = rowTamanio.getCell(26); // Obtén la celda en la fila
                // Modifica el valor de la celda
                cellTamanio.setCellValue(plan.getTamanioOrden());

                // Tamaño operación
                Row rowOperacion = sheet.getRow(i); // Obtén la fila
                Cell cellOperacion = rowOperacion.getCell(27); // Obtén la celda en la fila
                // Modifica el valor de la celda
                cellOperacion.setCellValue(plan.getOperacion() + "/" + maximo);

                // Tamaño programación
                Row rowProgramacion = sheet.getRow(i); // Obtén la fila
                Cell cellProgramacion = rowProgramacion.getCell(31); // Obtén la celda en la fila
                // Modifica el valor de la celda
                cellProgramacion.setCellValue(plan.getProgramacion());

                // Comentarios
                Row rowComentarios = sheet.getRow(i); // Obtén la fila
                Cell cellComentarios = rowComentarios.getCell(33); // Obtén la celda en la fila
                // Modifica el valor de la celda
                cellComentarios.setCellValue(plan.getComentario());

                // Operador
                Row rowOperador = sheet.getRow(i); // Obtén la fila
                Cell cellOperador = rowOperador.getCell(34); // Obtén la celda en la fila
                // Modifica el valor de la celda
                cellOperador.setCellValue(plan.getOperador());
                i++;
            }
            // Definir el formato de salida
            SimpleDateFormat formato2 = new SimpleDateFormat("dd MM yyyy");
            // Formatear la fecha actual según el formato
            String fechaFormateada2 = formato2.format(fechaActual);
            // Crear un nuevo archivo de Excel para guardar los cambios
            String newFilePath = "Plan de Producción " + fechaFormateada2 + ".xlsx ";  // Ruta del nuevo archivo de Excel
            FileOutputStream fos = new FileOutputStream(newFilePath);
            workbook.write(fos);
            fos.close();
            JOptionPane.showMessageDialog(null, "Datos Exportados Correctamente");
        } catch (IOException e) {
        }
    }
}
