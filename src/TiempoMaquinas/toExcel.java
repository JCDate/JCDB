/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiempoMaquinas;

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
    File file = new File("TiempoMaquina.xls");

    Connection conexion;

    /**
     * Constructor de clase
     */
    public toExcel() {

        this.conexion = Conexion.getInstance().getConnection();
    }

    /**
     * Metodo para obtener los registros de la base de datos y crear el archivo
     * excel
     */
    public void WriteExcelTiempoMaquinas() {
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
            workbook.createSheet("TiempoMaquina", 0);
            excelSheet = workbook.getSheet(0);
            System.out.println("creando hoja excel.....Listo");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        //Consulta SQL 
        String sql = "SELECT maquina, horas FROM tiempomaquinas";
        try {
            PreparedStatement pstm = conexion.prepareStatement(sql);
            ResultSet res = pstm.executeQuery();
            System.out.println("obteniendo registros.....Listo");

            while (res.next()) {
                Label maq = new Label(0, 0, "MAQUINA", cf);
                Label hor = new Label(1, 0, "HORAS", cf);

                Label maquina = new Label(0, row, res.getString("maquina"), cf);
                Label horas = new Label(1, row, res.getString("horas"), cf);

                row++;
                try {
                    excelSheet.addCell(maq);
                    excelSheet.addCell(hor);

                    excelSheet.addCell(maquina);
                    excelSheet.addCell(horas);

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
