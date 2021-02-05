/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.PersonaDAO;
import modelo.*;

/**
 *
 * @author PC
 */
public class ControlPersonas {
    PersonaDAO personaDAO = new PersonaDAO();
    public int guardarUsuario(Coneccion coneccion,String[] datosPersona){
        Persona p = new Persona(0, datosPersona[0], datosPersona[1], datosPersona[2], datosPersona[3], datosPersona[4], datosPersona[5], datosPersona[6], datosPersona[7], datosPersona[8], datosPersona[9]);
        personaDAO = new PersonaDAO();
        return personaDAO.guardarPersona(coneccion, p);
    }
    public int guardarAdministrador(Coneccion coneccion, String[] datosAdministrador){
        Persona p = new Persona(0, datosAdministrador[0], datosAdministrador[1], datosAdministrador[2], datosAdministrador[3], datosAdministrador[4], datosAdministrador[5], datosAdministrador[6], datosAdministrador[7], datosAdministrador[8], null);
        personaDAO = new PersonaDAO();
        return personaDAO.guardarAdministrador(coneccion, p);
    }
    public String[] buscarCliente(Coneccion coneccion, String usuario, String pass){
        personaDAO = new PersonaDAO(/*coneccion*/);
        Cliente ce = personaDAO.buscarCliente(coneccion, usuario, pass);
        String[] datosCliente = new String[12];
        if(ce!=null){
        datosCliente[0]= ce.getUsuario();
        datosCliente[1]= ce.getContraseña();
        datosCliente[2]= ce.getNombre();
        datosCliente[3]= ce.getApellidoP();
        datosCliente[4]= ce.getApellidoM();
        datosCliente[5]= ce.getCedula();
        datosCliente[6]= ce.getPasaporte();
        datosCliente[7]= ce.getTelefono();
        datosCliente[8]= ce.getCorreo();
        datosCliente[9]= ce.getNumTarjeta();
        datosCliente[10]=Integer.toString(ce.getIdPersona());
        datosCliente[11]=Integer.toString(ce.getIdCliente());
        }else{
            return datosCliente=null;
        }
        return datosCliente;
    }
    public String[] buscarAdministrador(Coneccion coneccion, String usuario, String pass){
        personaDAO = new PersonaDAO(/*coneccion*/);
        Administrador ad = personaDAO.buscarAdministrador(coneccion, usuario, pass);
        String[] datosAdmin = new String[10];
        if(ad!=null){
        datosAdmin[0] = ad.getUsuario();
        datosAdmin[1] = ad.getContraseña();
        datosAdmin[2] = ad.getNombre();
        datosAdmin[3] = ad.getApellidoP();
        datosAdmin[4] = ad.getApellidoM();
        datosAdmin[5] = ad.getCedula();
        datosAdmin[6] = ad.getPasaporte();
        datosAdmin[7] = ad.getTelefono();
        datosAdmin[8] = ad.getCorreo();
        datosAdmin[9] = Integer.toString(ad.getIdPersona());
        }else{
            return datosAdmin=null;
        }
         return datosAdmin;
    }
    public int modificarCliente(Coneccion coneccion,int idPersona, String[] datosCliente){
        Cliente ce = new Cliente(0,idPersona,datosCliente[0],datosCliente[1],datosCliente[2],datosCliente[3],datosCliente[4],datosCliente[5],datosCliente[6],datosCliente[7],datosCliente[8],datosCliente[9]);
        personaDAO = new PersonaDAO();
        return personaDAO.modificarCliente(coneccion, ce);
    }
    
    public int modificarAdministrador(Coneccion coneccion,int idPersona, String[] datosAdministrador){
        Administrador ad = new Administrador(0,idPersona,datosAdministrador[0],datosAdministrador[1],datosAdministrador[2],datosAdministrador[3],datosAdministrador[4],datosAdministrador[5],datosAdministrador[6],datosAdministrador[7],datosAdministrador[8],null);
        personaDAO = new PersonaDAO();
        return personaDAO.modificarAdministrador(coneccion, ad);
    }
}
