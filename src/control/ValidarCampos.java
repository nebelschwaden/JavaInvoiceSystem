/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.toedter.components.JSpinField;
import java.awt.Color;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class ValidarCampos {
    public boolean validarCamposVacios(List<JTextField> campos){
        boolean bandera = false;
            for (int i = 0; i < campos.size(); i++) {
            JTextField txt = campos.get(i);
            
            if(txt.getText().trim().equals(""))
                    {
                txt.setBackground(Color.red);
                bandera = true;
            }
            else{
                txt.setBackground(Color.white);
            }
                txt.revalidate();
        }
            return bandera;
    }
    public boolean validarTamañoCampos(List<JTextField> campos){
        boolean size=false;
        for (int i = 0; i < campos.size(); i++) {
            JTextField txt = campos.get(i);
            if(txt.getText().length()>44)
                    {
                txt.setBackground(Color.red);
                size = true;
                
                    }            
            
             else{
                txt.setBackground(Color.white);
            }
                txt.revalidate();
        }
        return size;
        }
    public void limpiarCamposVacios(List<JTextField> campos){
      
            for (int i = 0; i < campos.size(); i++) {
            JTextField txt = campos.get(i);
            txt.setText("");
            txt.revalidate();
            
        }
            
    }
    public boolean validarSpiners(List<JSpinField> jsp){
        boolean spin = false;
        for(int i=0; i<jsp.size() ; i++){
            if(jsp.get(i).getValue()<=0)
                    {
                    spin=true;
                    }
            switch(i){
                case 0: 
                    if(jsp.get(i).getValue()>16){
                        JOptionPane.showMessageDialog(null, "El número seleccionado supera el límite de asientos disponibles (General Norte).");
                        spin=true;
                    }
                    break;
                case 1: 
                    if(jsp.get(i).getValue()>16){
                        JOptionPane.showMessageDialog(null, "El número seleccionado supera el límite de asientos disponibles (General Sur).");
                        spin=true;
                        }
                    break;
                case 2: 
                    if(jsp.get(i).getValue()>20){
                        JOptionPane.showMessageDialog(null, "El número seleccionado supera el límite de asientos disponibles (Tribuna).");
                        spin=true;
                    }
                    break;
                case 3: 
                    if(jsp.get(i).getValue()>20){
                        JOptionPane.showMessageDialog(null, "El número seleccionado supera el límite de asientos disponibles (Palco).");
                        spin=true;
                    }
                    break;
                
            }
        }
        return spin;
    }
    public boolean validarCaracteres(List<JTextField> campos){
        boolean size = false;
        Pattern regex = Pattern.compile("[$/&+,':;=?@#|]");
       for (int i = 0; i < campos.size(); i++) {
            JTextField txt = campos.get(i);
              Matcher matcher = regex.matcher(txt.getText());
                  if (matcher.find()){
                    size=true;
                    txt.setBackground(Color.blue);
                  }
       }
       return size;
    }
    public boolean validarNumbers(JTextField camp){
        boolean letter=false;
        Pattern regex = Pattern.compile("^[0-9]*$");
        Matcher matcher = regex.matcher(camp.getText());
        if(matcher.find()){
            
        } else {
            letter=true;
        }
        return letter;
    }
    public boolean validarletras(JTextField camp){
        boolean letter=false;
        Pattern regex2 = Pattern.compile("^[\\pL\\pM\\p{Zs}.-]+$");
        Matcher matcher = regex2.matcher(camp.getText());
        if(!matcher.find()){
        letter=true;
        } 
        return letter;
    }
}
