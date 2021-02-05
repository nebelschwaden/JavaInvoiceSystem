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
public class Asiento {
    public int idAsiento,puesto;

    public Asiento(int idAsiento, int puesto) {
        this.idAsiento = idAsiento;
        this.puesto = puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public int getPuesto() {
        return puesto;
    }

    
    
    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }



    public int getIdAsiento() {
        return idAsiento;
    }

    
            
}
