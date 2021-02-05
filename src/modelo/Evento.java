/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.sql.Time;


/**
 *
 * @author PC
 */
public class Evento {
    public String lugar, equipo1, equipo2;
    public Time horaInicio;
    public Date fecha;
    public int idEvento;
     
    public String getEquipo1() {
        return equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }
    
    public Time getHoraInicio() {
        return horaInicio;
    }

    public String getLugar() {
        return lugar;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }



    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public Evento(String lugar, String equipo1, String equipo2, Time horaInicio, Date fecha, int idEvento) {
        this.lugar = lugar;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.horaInicio = horaInicio;
        this.fecha = fecha;
        this.idEvento = idEvento;
    }

    


    
}
