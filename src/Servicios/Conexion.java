/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private String SERVER = "192.168.1.75";
    private static Conexion instancia; // Instancia única de la clase
    private Connection connection;

    protected Conexion() { // Constructor privado para evitar la creación de instancias desde fuera de la clase
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+SERVER+":3306/jc_mysql?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true", "root", ""); // Datos de la BD
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized Conexion getInstance() { // Método estático para obtener la instancia única
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getConnection() {
        return connection;
    }

    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}