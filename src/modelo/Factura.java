/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.Date;

/**
 *
 * @author PC
 */
public class Factura extends Cliente{
    public int codigoFactura;
    public Date fecha;

    public Factura(int codigoFactura, Date fecha, int idCliente, int idPersona, String usuario, String contraseña, String nombre, String apellidoP, String apellidoM, String cedula, String pasaporte, String telefono, String correo, String numTarjeta) {
        super(idCliente, idPersona, usuario, contraseña, nombre, apellidoP, apellidoM, cedula, pasaporte, telefono, correo, numTarjeta);
        this.codigoFactura = codigoFactura;
        
        this.fecha = fecha;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

   
    
     
    

    public void crearFactura(){
        
    }
    public void mostrarFactura(){
        
    }
}
