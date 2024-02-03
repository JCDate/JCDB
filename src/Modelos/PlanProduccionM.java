/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author JC
 */
public class PlanProduccionM {
    String maquina;
    String orden;
    String troquel;
    String cmpt;
    String va;
    String vb;
    String operacion;
    String tamanioOrden;
    String volumen;
    String comentario;
    String programacion;
    String operador;

    public PlanProduccionM(String maquina, String orden, String troquel, String cmpt, String va, String vb, String tamanio, String operacion, String programacion, String comentario, String operador) {
        this.maquina = maquina;
        this.orden = orden;
        this.troquel = troquel;
        this.cmpt = cmpt;
        this.va = va;
        this.vb = vb;
        this.tamanioOrden = tamanio;
        this.operacion = operacion;
        this.programacion = programacion;
        this.comentario = comentario;
        this.operador = operador;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getTroquel() {
        return troquel;
    }

    public void setTroquel(String troquel) {
        this.troquel = troquel;
    }

    public String getCmpt() {
        return cmpt;
    }

    public void setCmpt(String cmpt) {
        this.cmpt = cmpt;
    }

    public String getVa() {
        return va;
    }

    public void setVa(String va) {
        this.va = va;
    }

    public String getVb() {
        return vb;
    }

    public void setVb(String vb) {
        this.vb = vb;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getTamanioOrden() {
        return tamanioOrden;
    }

    public void setTamanioOrden(String tamanioOrden) {
        this.tamanioOrden = tamanioOrden;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getProgramacion() {
        return programacion;
    }

    public void setProgramacion(String programacion) {
        this.programacion = programacion;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }


}
