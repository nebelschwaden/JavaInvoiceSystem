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
public class Localidad {
    public String tipo;
    public double precio;
    public int idLocalidad,numeroAsientos;
   


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

 
    public String getTipo() {
        return tipo;
    }

    public void setNumeroAsientos(int numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
    }

    public int getNumeroAsientos() {
        return numeroAsientos;
    }

    public Localidad(String tipo, double precio, int idLocalidad, int numeroAsientos) {
        this.tipo = tipo;
        this.precio = precio;
        this.idLocalidad = idLocalidad;
        this.numeroAsientos = numeroAsientos;
    }

  

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

     

    public double getPrecio() {
        return precio;
    }

  

  
}
