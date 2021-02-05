/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte;

import control.Coneccion;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author PC
 */
public class GenerarReporte {
    public void reporte(int idEvento){
       Coneccion coneccion = new Coneccion();
       Connection cn = coneccion.crearConeccion();
        try {
            JasperReport reporte = (JasperReport)JRLoader.loadObjectFromFile("src/reporte/resumenVentas.jasper"); 
            Map parametro = new HashMap();
            parametro.put("pidEvento", idEvento);
            JasperPrint j = JasperFillManager.fillReport(reporte, parametro,cn);
            JasperViewer jv = new JasperViewer(j,false);
            jv.setVisible(true);
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al general el reporte.");
        }
          
    }
}
