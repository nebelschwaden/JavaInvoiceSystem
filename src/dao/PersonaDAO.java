/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import control.Coneccion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.*;
/**
 *
 * @author PC
 */
public class PersonaDAO {
    public int guardarPersona(Coneccion coneccion,Persona p){
        int done=0;
        coneccion.crearConeccion();
        try {
            PreparedStatement update1 = coneccion.getConeccion().prepareStatement("INSERT INTO PERSONA VALUES(0,'"+p.getUsuario()+"','"+p.getContraseña()+"','"+p.getNombre()+"','"+p.getApellidoP()+"','"+p.getApellidoM()+"','"+p.getCedula()+"','"+p.getPasaporte()+"','"+p.getTelefono()+"','"+p.getCorreo()+"','"+p.getNumTarjeta()+"')");
            PreparedStatement update2 = coneccion.getConeccion().prepareStatement("INSERT INTO CLIENTE VALUES(0,LAST_INSERT_ID())");
            update1.executeUpdate();
            done = update2.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error en sintaxis SQL (Transacción Cliente).");
        }
        coneccion.cerrar();
        return done;
    }
    public int guardarAdministrador(Coneccion coneccion, Persona p){
        int done=0; 
        coneccion.crearConeccion();
        try {
            PreparedStatement update1 = coneccion.getConeccion().prepareStatement("INSERT INTO PERSONA VALUES(0,'"+p.getUsuario()+"','"+p.getContraseña()+"','"+p.getNombre()+"','"+p.getApellidoP()+"','"+p.getApellidoM()+"','"+p.getCedula()+"','"+p.getPasaporte()+"','"+p.getTelefono()+"','"+p.getCorreo()+"','"+null+"')");
            PreparedStatement update2 = coneccion.getConeccion().prepareStatement("INSERT INTO ADMINISTRADOR VALUES(0,LAST_INSERT_ID())");
            update1.executeUpdate();
            done = update2.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error en sintaxis SQL (Transacción Administrador).");
        }
        coneccion.cerrar();
        return done;
     }
    public Cliente buscarCliente(Coneccion coneccion, String usuario, String pass){
        //coneccion.crearConeccion();
        Cliente c=null;
        try {
            PreparedStatement consultaPersona = coneccion.getConeccion().prepareStatement("SELECT * FROM PERSONA WHERE USUARIO= '"+usuario+"'AND CONTRASEÑA= '"+pass+"'");
            ResultSet resultadoPersona = consultaPersona.executeQuery();
            if(resultadoPersona.next()){
                int id = resultadoPersona.getInt("idPersona");              
                PreparedStatement consultaCliente = coneccion.getConeccion().prepareStatement("SELECT * FROM PERSONA, CLIENTE WHERE PERSONA.IDPERSONA="+id+" AND CLIENTE.PERSONA_IDPERSONA="+id);
                ResultSet resultadoCliente = consultaCliente.executeQuery();
                if(resultadoCliente.next()){
                    int idCliente = resultadoCliente.getInt("idCliente");
                    String[] datosCliente = new String[10];
                    datosCliente[0]=resultadoCliente.getString("usuario");
                    datosCliente[1]=resultadoCliente.getString("contraseña");
                    datosCliente[2]=resultadoCliente.getString("nombre");
                    datosCliente[3]=resultadoCliente.getString("apellidoP");
                    datosCliente[4]=resultadoCliente.getString("apellidoM");
                    datosCliente[5]=resultadoCliente.getString("cedula");
                    datosCliente[6]=resultadoCliente.getString("pasaporte");
                    datosCliente[7]=resultadoCliente.getString("telefono");
                    datosCliente[8]=resultadoCliente.getString("correo");
                    datosCliente[9]=resultadoCliente.getString("numeroTarjeta");
                c = new Cliente(idCliente,id,datosCliente[0],datosCliente[1],datosCliente[2],datosCliente[3],datosCliente[4],datosCliente[5],datosCliente[6],datosCliente[7],datosCliente[8],datosCliente[9]);    
                }else{
                    return c=null;
                }
            }           
        } catch (SQLException ex) {
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error de sintaxis SQL (Consulta cliente).");
        }
        //coneccion.cerrar();
        return c;
    }
    public Administrador buscarAdministrador(Coneccion coneccion, String usuario, String pass){
        //coneccion.crearConeccion();
        Administrador a=null;
        try {
            PreparedStatement consultaPersona = coneccion.getConeccion().prepareStatement("SELECT * FROM PERSONA WHERE USUARIO= '"+usuario+"'AND CONTRASEÑA= '"+pass+"'");
            ResultSet resultadoPersona = consultaPersona.executeQuery();
            if(resultadoPersona.next()){
                int id = resultadoPersona.getInt("idPersona");   
                PreparedStatement consultaAdministrador = coneccion.getConeccion().prepareStatement("SELECT * FROM PERSONA, ADMINISTRADOR WHERE PERSONA.IDPERSONA = "+id+" AND ADMINISTRADOR.PERSONA_IDPERSONA="+id);
                ResultSet resultadoAdministrador = consultaAdministrador.executeQuery();
                if(resultadoAdministrador.next()){
                    int idAdministrador = resultadoAdministrador.getInt("idAdministrador");
                    String[] datosCliente = new String[9];
                    datosCliente[0]=resultadoAdministrador.getString("usuario");
                    datosCliente[1]=resultadoAdministrador.getString("contraseña");
                    datosCliente[2]=resultadoAdministrador.getString("nombre");
                    datosCliente[3]=resultadoAdministrador.getString("apellidoP");
                    datosCliente[4]=resultadoAdministrador.getString("apellidoM");
                    datosCliente[5]=resultadoAdministrador.getString("cedula");
                    datosCliente[6]=resultadoAdministrador.getString("pasaporte");
                    datosCliente[7]=resultadoAdministrador.getString("telefono");
                    datosCliente[8]=resultadoAdministrador.getString("correo");
                    
                a = new Administrador(idAdministrador,id, datosCliente[0], datosCliente[1], datosCliente[2], datosCliente[3], datosCliente[4], datosCliente[5], datosCliente[6], datosCliente[7], datosCliente[8], null );
                }
            }           
        } catch (SQLException ex) {
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error de sintaxis SQL (Consulta Administrador).");
        }
        //coneccion.cerrar();
        return a;
    }
    public int modificarCliente(Coneccion coneccion, Cliente c){
        int done=0;
        coneccion.crearConeccion();
        try {
            PreparedStatement consultaBD = coneccion.getConeccion().prepareStatement("UPDATE PERSONA SET nombre='"+c.getNombre()+"', apellidoP='"+c.getApellidoP()+"',apellidoM='"+c.getApellidoM()+"',usuario='"+c.getUsuario()+"',contraseña='"+c.getContraseña()+"',cedula='"+c.getCedula()+"',pasaporte='"+c.getPasaporte()+"',telefono='"+c.getTelefono()+"',correo='"+c.getCorreo()+"',numeroTarjeta='"+c.getNumTarjeta()+"' WHERE idPersona = "+c.getIdPersona());
            done = consultaBD.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error modificar Cliente. (SQL)");
         
        }
        coneccion.cerrar();
        return done;
    }
    public int modificarAdministrador(Coneccion coneccion, Administrador a){
        int done=0;
        coneccion.crearConeccion();
        try {
            PreparedStatement consultaBD = coneccion.getConeccion().prepareStatement("UPDATE PERSONA SET nombre='"+a.getNombre()+"', apellidoP='"+a.getApellidoP()+"',apellidoM='"+a.getApellidoM()+"',usuario='"+a.getUsuario()+"',contraseña='"+a.getContraseña()+"',cedula='"+a.getCedula()+"',pasaporte='"+a.getPasaporte()+"',telefono='"+a.getTelefono()+"',correo='"+a.getCorreo()+"',numeroTarjeta='"+null+"' WHERE idPersona = "+a.getIdPersona());
            done = consultaBD.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al modificar administrador. (SQL)");
            
        }
        coneccion.cerrar();
        return done;
    }
     public int buscarUser(Coneccion coneccion, String usuario){
        coneccion.crearConeccion();
        int done=0;
        try {
            PreparedStatement consultaPersona = coneccion.getConeccion().prepareStatement("SELECT * FROM PERSONA WHERE USUARIO='"+usuario+"'");
            ResultSet resultadoPersona = consultaPersona.executeQuery();
            if(resultadoPersona.next()){
                return done=1;
            }           
        } catch (SQLException ex) {
            //Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error encontrando usuario.");
        }
        coneccion.cerrar();
        return done;
    }

}
