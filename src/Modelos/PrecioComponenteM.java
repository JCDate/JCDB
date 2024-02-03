/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;


public class PrecioComponenteM {
    private String componente;
    private Float precioUnitario;
    private String fechaActualizacion;


    public PrecioComponenteM() {
        this.componente = null;
        this.precioUnitario = null;
        this.fechaActualizacion = null;
      
    }

    public PrecioComponenteM(String componente, Float precioUnitario, String fechaActualizacion) {
        this.componente = componente;
        this.precioUnitario = precioUnitario;
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }


    @Override
    public String toString() {
        return "PrecioComponente{" + "componente=" + componente + ", precioUnitario=" + precioUnitario + '}';
    }

   
}
