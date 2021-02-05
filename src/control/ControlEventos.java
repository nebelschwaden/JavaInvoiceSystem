/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import dao.EventoDAO;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import modelo.Evento;
import modelo.Factura;
import modelo.Localidad;
/**
 *
 * @author PC
 */
public class ControlEventos {
    EventoDAO eventoDAO = new EventoDAO();
    public int guardarEvento(Coneccion coneccion,String[] infoEvento,int[] numAsientos,double[] precios,Date fecha, Time hora){
        Evento ev = new Evento(infoEvento[0], infoEvento[1], infoEvento[2], hora, fecha, 0);
        List<Localidad> listaLocalidades= new ArrayList<>();
        listaLocalidades.add(new Localidad("General Norte", precios[0],0,numAsientos[0]));
        listaLocalidades.add(new Localidad("General Sur", precios[1],0,numAsientos[1]));
        listaLocalidades.add(new Localidad("Tribuna", precios[2],0,numAsientos[2]));
        listaLocalidades.add(new Localidad("Palco", precios[3],0,numAsientos[3]));
        return eventoDAO.guardarEvento(coneccion, ev, listaLocalidades);
    }
    public List<Evento> buscarEvento(Coneccion con, String equipo){
        List<Evento> listaEventos = eventoDAO.buscarEvento(con, equipo);
        return listaEventos;
    }
    public List<Evento> botonesEvento(Coneccion con){
        List<Evento> listaEventos = eventoDAO.botonesEvento(con);
        return listaEventos;
    }
    public String[] infoEvento(Coneccion con,int idEvento){
        Evento e = eventoDAO.infoEvento(con, idEvento);
        String[] infoevento = new String[7];
        if(e!=null){
        infoevento[0]= Integer.toString(e.getIdEvento());
        infoevento[1]= e.getLugar();
        infoevento[2]= e.getEquipo1();
        infoevento[3]= e.getEquipo2();
        infoevento[4] =e.getFecha().toString(); 
        infoevento[5]= e.getHoraInicio().toString();
        infoevento[6] =e.getEquipo1()+" vs "+e.getEquipo2();
        }else{
            return infoevento=null;
        }
        
        return infoevento;
    }
    public List<Localidad> infoLocalidad(Coneccion con, int idEvento){
        List<Localidad> listaLocalidades= new ArrayList<>();
        listaLocalidades = eventoDAO.infoLocalidad(con, idEvento);
        return listaLocalidades;
    }
    public double generarFactura(Coneccion con,int idLocalidad, List<Integer> puestos, double precio, int idCliente){
        List<Integer> idAsientos = eventoDAO.idesAsientos(con, idLocalidad, puestos);
        double preciot=0;
        Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
        if(eventoDAO.registrarFactura(new Coneccion(), idCliente, fecha, puestos.size(), idAsientos)!=0){
        preciot=(puestos.size()*precio);
        }
        return preciot;
    }
    public String[] facturaInfo(Coneccion con){
        Factura fac = eventoDAO.buscarFactura(con);
        String[] datosFact = new String[5];
        if(fac!=null){
        datosFact[0] = Integer.toString(fac.getCodigoFactura());
        datosFact[1] = (fac.getNombre()+" "+fac.getApellidoP()+" "+fac.getApellidoM());
        datosFact[2] = fac.getTelefono();
        datosFact[3] = fac.getFecha().toString();
        datosFact[4] = fac.getCorreo();
        }else{
            datosFact=null;
        }
        return datosFact;
    }
    
    public int modificarEvento(Coneccion con, String[] datos, double[] precios, int[] numAsientos, Date fecha, Time hora){
        Evento ev = new Evento(datos[0], datos[1], datos[2], hora, fecha, Integer.parseInt(datos[3]));
        List<Localidad> listaLocalidades= new ArrayList<>();
        listaLocalidades.add(new Localidad("General Norte", precios[0],numAsientos[4],numAsientos[0]));
        listaLocalidades.add(new Localidad("General Sur", precios[1],numAsientos[5],numAsientos[1]));
        listaLocalidades.add(new Localidad("Tribuna", precios[2],numAsientos[6],numAsientos[2]));
        listaLocalidades.add(new Localidad("Palco", precios[3],numAsientos[7],numAsientos[3]));
        return eventoDAO.modificarEvento(con, ev, listaLocalidades);
    }
    
}
