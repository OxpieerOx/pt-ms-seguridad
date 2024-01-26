package com.intech.msseguridad.dtos;

import java.io.Serializable;

public class WrapperResponse<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer estado;
    private String mensaje;

    private T resultado;


    public WrapperResponse() {

    }

    public WrapperResponse(Integer estado) {
        this.estado = estado;
    }

    public WrapperResponse(Integer estado, String message) {
        this.estado = estado;
        this.mensaje = message;
    }

    public WrapperResponse(Integer estado, String message, T body) {
        this.estado = estado;
        this.mensaje = message;
        this.resultado = body;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public T getResultado() {
        return resultado;
    }

    public void setResultado(T resultado) {
        this.resultado = resultado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


}
