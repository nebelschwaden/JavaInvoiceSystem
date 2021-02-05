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
public class Administrador extends Persona{
    public int idAdministrador;

    public Administrador(int idAdministrador, int idPersona, String usuario, String contraseña, String nombre, String apellidoP, String apellidoM, String cedula, String pasaporte, String telefono, String correo, String numTarjeta) {
        super(idPersona, usuario, contraseña, nombre, apellidoP, apellidoM, cedula, pasaporte, telefono, correo, numTarjeta);
        this.idAdministrador = idAdministrador;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
    
    
    @Override
    public String toString() {
        return "US"+getUsuario()+"CON"+getContraseña();
    }
    public void modificarEvento(){
    }
    public void modificarDatosAdministrador(){
        
    }
                
}
