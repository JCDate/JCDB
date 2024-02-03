/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Modelos.PrecioMP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PrecioMP_servicio {

    private final String tabla = "preciomp";

    public void agregar(Connection conexion, PrecioMP precioMP) throws SQLException {
        try {
            int id_prov = 0, id_calibre = 0;
            String selectProveedores = "SELECT id_prov FROM proveedores WHERE nombre='" + precioMP.getProveedor() + "' AND calibre='" + precioMP.getCalibre() + "'";
            PreparedStatement stmt1 = conexion.prepareStatement(selectProveedores);
            ResultSet rs = stmt1.executeQuery();
            if (rs.next()) {
                id_prov = rs.getInt("id_prov");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el proveedor");
            }

            String selectCalibre = "SELECT id_calibre FROM inventario WHERE id_prov='" + id_prov + "' ";
            PreparedStatement stmt2 = conexion.prepareStatement(selectCalibre);
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                id_calibre = rs2.getInt("id_calibre");
            }

            PreparedStatement consulta;
            consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(calibre,pesoUnitario,moneda,id_prov,id_calibre) VALUES( ?,?,?,?,?)");
            consulta.setString(1, precioMP.getCalibre());
            consulta.setFloat(2, precioMP.getPesoUnitario());
            consulta.setString(3, precioMP.getMoneda());
            consulta.setInt(4, id_prov);
            consulta.setInt(5, id_calibre);

            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public void modificar(Connection conexion, PrecioMP precioMP, PrecioMP precioMP2) throws SQLException {
        try {
            PreparedStatement consulta;
            String sqlId = "SELECT idCalibre, id_calibre, id_prov FROM preciomp WHERE calibre=? AND pesoUnitario BETWEEN ? AND ? AND moneda=?";
            PreparedStatement statement = conexion.prepareStatement(sqlId);
            statement.setString(1, precioMP2.getCalibre());
            statement.setFloat(2, precioMP2.getPesoUnitario() - 10);
            statement.setFloat(3, precioMP2.getPesoUnitario() + 10);
            statement.setString(4, precioMP2.getMoneda());
            ResultSet rs = statement.executeQuery();
            int id = 0;
            int cal = 0;
            int prov = 0;
            if (rs.next()) {
                id = rs.getInt("idCalibre");
                cal = rs.getInt("id_calibre");
                prov = rs.getInt("id_prov");

                consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET pesoUnitario=?, moneda=? WHERE calibre=? AND idCalibre=? AND id_prov=? AND id_calibre=?");
                consulta.setFloat(1, precioMP.getPesoUnitario());
                consulta.setString(2, precioMP.getMoneda());
                consulta.setString(3, precioMP.getCalibre());
                consulta.setInt(4, id);
                consulta.setInt(5, prov);
                consulta.setInt(6, cal);
                consulta.executeUpdate();
            } else {
                System.out.println("id =" + id);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

    }

    public int existeCalibre(String usuarios) throws ClassNotFoundException {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection conexion = Conexion.getInstance().getConnection();

            String sql = "SELECT COUNT(calibre) FROM preciomp WHERE calibre=?";

            ps = conexion.prepareStatement(sql);
            ps.setString(1, usuarios);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
    }

    public List<PrecioMP> recuperarTodas(Connection conexion) throws SQLException {
        List<PrecioMP> precioMP = new ArrayList<>();
        String nomProv = "";
        try {
            PreparedStatement consulta = conexion.prepareStatement("SELECT  calibre, pesoUnitario,moneda,id_prov FROM " + this.tabla);
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                PreparedStatement consulta2 = conexion.prepareStatement("SELECT * FROM  proveedores WHERE id_prov='" + resultado.getInt("id_prov") + "'");
                ResultSet res = consulta2.executeQuery();
                if (res.next()) {
                    nomProv = res.getString("nombre");
                }
                precioMP.add(new PrecioMP(resultado.getString("calibre"), resultado.getFloat("pesoUnitario"), resultado.getString("moneda"), nomProv));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return precioMP;
    }
}
