/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import control.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.*;
/**
 *
 * @author PC
 */
public class EventoDAO {
    private List<Evento> listaEventos = new ArrayList<Evento>();
    private List<Localidad> listaLocalidad = new ArrayList<Localidad>();
    
    public int guardarEvento(Coneccion con, Evento ev,  List<Localidad> listaLocalidad ){
        int done=0;
        con.crearConeccion();
        try {
            PreparedStatement update1 = con.getConeccion().prepareStatement("INSERT INTO EVENTOS VALUES(0,'"+ev.getLugar()+"','"+ev.getEquipo1()+"','"+ev.getEquipo2()+"','"+ev.getFecha()+"','"+ev.getHoraInicio()+"')");
            update1.executeUpdate();
            for(int i=0; i<listaLocalidad.size() ; i++){
             PreparedStatement update2 = con.getConeccion().prepareStatement("INSERT INTO LOCALIDAD VALUES(0,'"+listaLocalidad.get(i).getTipo()+"','"+listaLocalidad.get(i).getPrecio()+"','"+listaLocalidad.get(i).getNumeroAsientos()+"',(SELECT MAX(idEventos) FROM EVENTOS))");
             update2.executeUpdate();    
                 for(int j=1; j<=listaLocalidad.get(i).numeroAsientos;j++){
                 PreparedStatement update3 = con.getConeccion().prepareStatement("INSERT INTO ASIENTOS VALUES(0,(SELECT MAX(idLocalidad) FROM LOCALIDAD),"+j+")");
                 done = update3.executeUpdate();
                       }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"Error en sintaxis SQL (Guardar Eventos).");
        }
        con.cerrar();
        return done;
    }
    public List<Evento> buscarEvento(Coneccion con, String equipo1){
        con.crearConeccion();
        try {
            PreparedStatement consultaBD = con.getConeccion().prepareStatement("SELECT * FROM EVENTOS WHERE equipo1 LIKE '"+equipo1+"%' OR equipo2 LIKE '"+equipo1+"%'");// a% -> todo lo que comienza con a; %a -> todo lo que termina con a
            ResultSet resultado = consultaBD.executeQuery();
            while(resultado.next()){
                int id = resultado.getInt("idEventos");
                String lugar = resultado.getString("lugar");
                String equipoA = resultado.getString("equipo1");
                String equipoB = resultado.getString("equipo2");
                Date fecha = resultado.getDate("fecha");
                Time hora = resultado.getTime("hora");
                listaEventos.add(new Evento(lugar, equipoA, equipoB, hora, fecha, id));
            }
            con.cerrar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en sintaxis SQL (Consulta Eventos).");
        }
        return listaEventos;
    }
    
    public List<Evento> botonesEvento(Coneccion con){
        con.crearConeccion();
        try {
            PreparedStatement consultaBD = con.getConeccion().prepareStatement("SELECT * FROM EVENTOS");// a% -> todo lo que comienza con a; %a -> todo lo que termina con a
            ResultSet resultado = consultaBD.executeQuery();
            while(resultado.next()){
                int id = resultado.getInt("idEventos");
                String lugar = resultado.getString("lugar");
                String equipoA = resultado.getString("equipo1");
                String equipoB = resultado.getString("equipo2");
                Date fecha = resultado.getDate("fecha");
                Time hora = resultado.getTime("hora");
                listaEventos.add(new Evento(lugar, equipoA, equipoB, hora, fecha, id));
            }
            con.cerrar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en sintaxis SQL (Consulta Botones Eventos).");
        }
        return listaEventos;
    }
    public Evento infoEvento(Coneccion con,int idEvento){
        con.crearConeccion();
        Evento ev=null;
        try {
            PreparedStatement consultaBD = con.getConeccion().prepareStatement("SELECT * FROM EVENTOS WHERE idEventos="+idEvento);// a% -> todo lo que comienza con a; %a -> todo lo que termina con a
            ResultSet resultado = consultaBD.executeQuery(); 
            if(resultado.next()){
                int id = resultado.getInt("idEventos");
                String lugar = resultado.getString("lugar");
                String equipoA = resultado.getString("equipo1");
                String equipoB = resultado.getString("equipo2");
                Date fecha = resultado.getDate("fecha");
                Time hora = resultado.getTime("hora");
                ev = new Evento(lugar, equipoA, equipoB, hora, fecha, id);
            }
            con.cerrar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en sintaxis SQL (Consulta Info Eventos).");
        }
        return ev;
    }
    public List<Localidad> infoLocalidad(Coneccion con,int idEvento){
        con.crearConeccion();
        try {
            PreparedStatement consultaBD = con.getConeccion().prepareStatement("SELECT * FROM LOCALIDAD WHERE Eventos_idEventos="+idEvento+"");
            ResultSet resultado = consultaBD.executeQuery(); 
            while(resultado.next()){
                int id = resultado.getInt("idLocalidad");
                String tipo = resultado.getString("tipo");
                double precio = resultado.getDouble("precio");
                int numeroAsientos = resultado.getInt("numeroAsientos");
                listaLocalidad.add(new Localidad(tipo, precio, id, numeroAsientos));
            }
            con.cerrar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en sintaxis SQL (Consulta info Localidad).");
        }
        return listaLocalidad;
    }
    public List<Integer> idesAsientos(Coneccion con, int idLocalidad, List<Integer> puestos){
        List<Integer> idesAsientos = new ArrayList<>();
        con.crearConeccion();
        try {
            for(int i=0; i<puestos.size();i++){
            PreparedStatement consultaBD = con.getConeccion().prepareStatement("SELECT * FROM ASIENTOS WHERE Localidad_idLocalidad="+idLocalidad+" AND puesto="+puestos.get(i)+"");
            ResultSet resultado = consultaBD.executeQuery(); 
            if(resultado.next()){
                idesAsientos.add(resultado.getInt("idAsientos"));
                }
            }
           con.cerrar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en sintaxis SQL (Obtención ID asiento).");
        }
         
        return idesAsientos;
    }
    public int registrarFactura(Coneccion con, int idCliente,Date fecha, int numBoletos, List<Integer> idAsientos){
        int done=0;
         con.crearConeccion();
        try {    
            PreparedStatement update1 = con.getConeccion().prepareStatement("INSERT INTO FACTURA VALUES(0,'"+fecha+"',"+idCliente+")");
            update1.executeUpdate(); 
            for(int i=0; i<numBoletos ; i++){
             PreparedStatement update2 = con.getConeccion().prepareStatement("INSERT INTO BOLETO VALUES(0,"+idCliente+",(SELECT MAX(idFactura) FROM FACTURA),'"+idAsientos.get(i)+"')");
             done=update2.executeUpdate();    
            }
            con.cerrar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error en sintaxis SQL (Registro Factura).");
        }
        
        return done;
    }
    public Factura buscarFactura(Coneccion con){
        Factura fa=null;
        con.crearConeccion();
        try {
            PreparedStatement update1 = con.getConeccion().prepareStatement("SELECT * FROM FACTURA, CLIENTE, PERSONA WHERE FACTURA.IDFACTURA = (SELECT MAX(idFactura) FROM FACTURA) AND FACTURA.CLIENTE_idCLIENTE = CLIENTE.idCliente AND CLIENTE.persona_idpersona = PERSONA.idPersona");
            ResultSet resultado = update1.executeQuery(); 
            if(resultado.next()){
                int idCliente = resultado.getInt("idCliente");
                int idFactura = resultado.getInt("idFactura");
                int idPersona = resultado.getInt("Persona_idPersona");
                Date fecha = resultado.getDate("fecha");
                String usuario = resultado.getString("usuario");
                String pass = resultado.getString("contraseña");
                String nombre = resultado.getString("nombre");
                String apellidoP = resultado.getString("apellidoP");
                String apellidoM = resultado.getString("apellidoM");
                String cedula = resultado.getString("cedula");
                String pasaporte = resultado.getString("pasaporte");
                String telefono = resultado.getString("telefono");
                String correo = resultado.getString("correo");
                String numTarjeta = resultado.getString("numeroTarjeta");
                fa = new Factura(idFactura, fecha, idCliente, idPersona, usuario, pass, nombre, apellidoP, apellidoM, cedula, pasaporte, telefono, correo, numTarjeta);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error en sintaxis SQL (Consulta Factura).");
        }
        return fa;
    }
    public int modificarEvento(Coneccion con,Evento ev, List<Localidad> listaL){
        int done=0;
        con.crearConeccion();
        try {
            PreparedStatement consultaBD = con.getConeccion().prepareStatement("UPDATE EVENTOS SET lugar='"+ev.getLugar()+"', equipo1='"+ev.getEquipo1()+"',equipo2='"+ev.getEquipo2()+"',fecha='"+ev.getFecha()+"',hora='"+ev.getHoraInicio()+"' WHERE idEventos="+ev.getIdEvento());
            consultaBD.executeUpdate();
            for (int i=0; i<4; i++){
                PreparedStatement update2 = con.getConeccion().prepareStatement("UPDATE LOCALIDAD SET tipo='"+listaL.get(i).getTipo()+"', precio='"+listaL.get(i).getPrecio()+"',numeroAsientos='"+listaL.get(i).getNumeroAsientos()+"' WHERE idLocalidad="+listaL.get(i).getIdLocalidad());
                done=update2.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al modificar evento(SQL).");
        }
        con.cerrar();
        return done;
    }
    
    public List<Integer> consultaAsientos(Coneccion con, int idLocalidad){
        List<Integer> idAsientos = new ArrayList<>();
         con.crearConeccion();
        try {
            PreparedStatement query = con.getConeccion().prepareStatement("SELECT PUESTO FROM ASIENTOS, BOLETO WHERE ASIENTOS.localidad_idlocalidad="+idLocalidad+" AND BOLETO.asientos_idAsientos=ASIENTOS.idAsientos");
            ResultSet resultado =query.executeQuery();
            while(resultado.next()){
                idAsientos.add(resultado.getInt("puesto"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error en sintaxis SQL (Asientos ocupados).");
        }
        con.cerrar();
        return idAsientos;
    }
    public int deleteEvento(Coneccion con, int idEvento){
        int done=0;
         con.crearConeccion();
        try {
            PreparedStatement query = con.getConeccion().prepareStatement("DELETE FROM EVENTOS WHERE idEventos="+idEvento);
            done = query.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error en sintaxis SQL (Borrar eventos).");
        }
         con.cerrar();
        return done;
    }
}
