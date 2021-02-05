/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author PC
 */
public class Cliente extends Persona{
    public int idCliente;

    public Cliente(int idCliente, int idPersona, String usuario, String contraseña, String nombre, String apellidoP, String apellidoM, String cedula, String pasaporte, String telefono, String correo, String numTarjeta) {
        super(idPersona, usuario, contraseña, nombre, apellidoP, apellidoM, cedula, pasaporte, telefono, correo, numTarjeta);
        this.idCliente = idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }
    
    
   public void registrarse(){
       
   }
   public void modificarDatosCliente(){
       
   }
    @Override
    public String toString() {
        return "USCLI "+getUsuario()+"CONCLI "+getContraseña();
    }
}
