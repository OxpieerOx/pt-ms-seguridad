package com.intech.msseguridad.util;

public class UtilEnum {
    private UtilEnum() {

    }

    public  enum ESTADO_OPERACION{
        EXITO(0,"EXITO"),
        ERROR(1,"ERROR"),
        DEMORA_SERVIDOR(500,"ESPERE UNOS SEGUNDOS Y VUELVA A INTENTAR");

        private final int codigo;
        private final String texto;
        private ESTADO_OPERACION(int estado,String texto){
            this.codigo = estado;
            this.texto = texto;
        }
        public int getCodigo() {
            return codigo;
        }

        public String getTexto() {
            return texto;
        }
    }
}
