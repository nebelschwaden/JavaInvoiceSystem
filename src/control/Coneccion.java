/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author PC
 */
public class Coneccion extends Thread{
     private Connection coneccion;
        public Coneccion(){            
        }
    public void setConeccion(Connection coneccion) {
        this.coneccion = coneccion;
    }
    public Connection getConeccion() {
        return coneccion;
    }
    public Connection crearConeccion(){
        if(coneccion == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                coneccion= DriverManager.getConnection(VariablesBD.URL, VariablesBD.USER, VariablesBD.PASS);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al crear la conección.");
            } catch (SQLException ex) {
               try {
                    Thread.sleep(2000);
                    JOptionPane.showMessageDialog(null, "Error al establecer conección, click en aceptar para reintentar.");
                } catch (InterruptedException ex1) {
                    Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex1);
                }
                crearConeccion(); 
            }
        }
        return coneccion;
    }
    public void cerrar(){
         if(coneccion != null){
             try {
                 coneccion.close();
             } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Error al cerrar la conección.");
             }
            
        }
    }
}