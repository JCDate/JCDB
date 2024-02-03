/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrecioMP;

import Servicios.Conexion;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author JC
 */
public class toExcel {

    /* DATOS PARA LA CONEXION */
    File file = new File("PrecioMP.xls");

    Connection conexion;

    /**
     * Constructor de clase
     */
    public toExcel() {

        this.conexion = Conexion.getInstance().getConnection(); // obtenemos la conexión
    }

    /**
     * Metodo para obtener los registros de la base de datos y crear el archivo
     * excel
     */
    public void WriteExcelPrecioMP() {
        int row = 1;
        //formato fuente para el contenido contenido
        WritableFont wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);
        WritableCellFormat cf = new WritableCellFormat(wf);

        //Interfaz para una hoja de cálculo
        WritableSheet excelSheet = null;
        WritableWorkbook workbook = null;

        //Establece la configuración regional para generar la hoja de cálculo
        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));

        try {
            workbook = Workbook.createWorkbook(file, wbSettings);
            //hoja con nombre de la tabla
            workbook.createSheet("PrecioMP", 0);
            excelSheet = workbook.getSheet(0);
            System.out.println("creando hoja excel.....Listo");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        //Consulta SQL 
        String sql = "SELECT calibre,pesoUnitario,moneda,id_prov FROM preciomp";
        try {
            PreparedStatement pstm = conexion.prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            System.out.println("obteniendo registros.....Listo");

            String nProv = "";
            while (res.next()) {
                String sqlProv = "SELECT nombre FROM proveedores WHERE id_prov='" + res.getInt("id_prov") + "' ";
                PreparedStatement pstm2 = conexion.prepareStatement(sqlProv);
                ResultSet res2 = pstm2.executeQuery();

                Label prov = new Label(0, 0, "PROVEEDOR", cf);
                Label cal = new Label(1, 0, "CALIBRE", cf);
                Label pre = new Label(2, 0, "PESO U.", cf);
                Label mon = new Label(3, 0, "MONEDA", cf);

                if (res2.next()) {

                    nProv = res2.getString("nombre");
                }
                Label proveedor = new Label(0, row, nProv, cf);
                Label calibre = new Label(1, row, res.getString("calibre"), cf);
                Label precio = new Label(2, row, res.getString("pesoUnitario"), cf);
                Label moneda = new Label(3, row, res.getString("moneda"), cf);
                row++;
                try {
                    excelSheet.addCell(prov);
                    excelSheet.addCell(cal);
                    excelSheet.addCell(pre);
                    excelSheet.addCell(mon);

                    excelSheet.addCell(proveedor);
                    excelSheet.addCell(calibre);
                    excelSheet.addCell(precio);
                    excelSheet.addCell(moneda);

                } catch (WriteException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //Escribe el archivo excel en disco
        try {
            workbook.write();
            workbook.close();
            System.out.println("Escribiendo en disco....Listo");
        } catch (IOException | WriteException ex) {
            System.err.println(ex.getMessage());
        }

        System.out.println("Proceso completado....");
    }

}
