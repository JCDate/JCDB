/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Modelos.CRsM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRs_servicio {

    private final String tabla = "crs";

    public void agregar(Connection conexion, CRsM crs) throws SQLException {
        try {
            PreparedStatement consulta;

            consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(componente, cr) VALUES(?, ?)");
            consulta.setString(1, crs.getComponente());
            consulta.setString(2, crs.getCr());

            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public void modificar(Connection conexion, CRsM crs) throws SQLException {
        try {
            PreparedStatement consulta;

            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET  cr = ? WHERE componente = ?");

            consulta.setString(1, crs.getCr());
            consulta.setString(2, crs.getComponente());

            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }

    }

    public int existeComponente(String usuarios) throws ClassNotFoundException {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            Connection conexion = Conexion.getInstance().getConnection();

            String sql = "SELECT COUNT(componente) FROM crs WHERE componente=?";

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

    public List<CRsM> recuperarTodas(Connection conexion) throws SQLException {
        List<CRsM> crs = new ArrayList<>();
        try {

            PreparedStatement consulta = conexion.prepareStatement("SELECT  componente, cr FROM " + this.tabla + " ORDER BY componente ASC");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                crs.add(new CRsM(resultado.getString("componente"), resultado.getString("cr")));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return crs;
    }

}
