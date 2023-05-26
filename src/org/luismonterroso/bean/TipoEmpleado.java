
package org.luismonterroso.bean;

public class TipoEmpleado {
    private int codigoTipoEmpleado;
    private String descripcionTipoEmpleado;
    
    public TipoEmpleado(){
    }
    
    public TipoEmpleado(int codigoTipoEmpleado, String descripcionTipoEmpleado) {
        this.codigoTipoEmpleado = codigoTipoEmpleado;
        this.descripcionTipoEmpleado = descripcionTipoEmpleado;
    }

    public int getCodigoTipoEmpleado() {
        return codigoTipoEmpleado;
    }

    public void setCodigoTipoEmpleado(int codigoTipoEmpleado) {
        this.codigoTipoEmpleado = codigoTipoEmpleado;
    }

    public String getDescripcionTipoEmpleado() {
        return descripcionTipoEmpleado;
    }

    public void setDescripcionTipoEmpleado(String descripcionTipoEmpleado) {
        this.descripcionTipoEmpleado = descripcionTipoEmpleado;
    }
    
    
}
