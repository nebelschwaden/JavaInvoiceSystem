/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import com.toedter.components.JSpinField;
import control.Coneccion;
import control.ControlEventos;
import control.ValidarCampos;
import dao.EventoDAO;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.Localidad;

/**
 *
 * @author PC
 */
public class DialogCrearEvento extends javax.swing.JDialog {
    ValidarCampos vcp = new ValidarCampos();
    ControlEventos ce = new  ControlEventos();
    EventoDAO ev = new EventoDAO();
    boolean crear;
    String[] eventoData;
    List<Localidad> locData;
    int idEvento;
    /**
     * Creates new form DialogCrearEvento
     */
    public DialogCrearEvento(java.awt.Frame parent, boolean modal, boolean crear, int idEvento) {
        super(parent, modal);
        initComponents();
        this.crear=crear;
        this.idEvento=idEvento;
        trashB.setVisible(false);
        if(crear == false){
            obtainEvento();
            trashB.setVisible(true);
            jButton2.setText("Modificar Datos");
        }
    }
    public void obtainEvento(){
        eventoData = ce.infoEvento(new Coneccion(),idEvento);
        locData = ce.infoLocalidad(new Coneccion(), idEvento);
        lugartxt.setText(eventoData[1]);
        fechatxt.setDate(Date.valueOf(eventoData[4]));
        horatxt.setText(eventoData[5]);
        equipo1txt.setText(eventoData[2]);
        equipo2txt.setText(eventoData[3]);
        generalNspin.setValue(locData.get(0).getNumeroAsientos());
        generalSspin.setValue(locData.get(1).getNumeroAsientos());
        tribunaSpin.setValue(locData.get(2).getNumeroAsientos());
        palcoSpin.setValue(locData.get(3).getNumeroAsientos());
        generalNspin.setEnabled(false);
        generalSspin.setEnabled(false);
        tribunaSpin.setEnabled(false);
        palcoSpin.setEnabled(false);
        StringTokenizer st= new StringTokenizer(Double.toString(locData.get(0).getPrecio()), ".");
        StringTokenizer st2= new StringTokenizer(Double.toString(locData.get(1).getPrecio()), ".");
        StringTokenizer st3= new StringTokenizer(Double.toString(locData.get(2).getPrecio()), ".");
        StringTokenizer st4= new StringTokenizer(Double.toString(locData.get(3).getPrecio()), ".");
        dolaresGeneralN.setValue(Integer.parseInt(st.nextToken()));
        dolaresGeneralS.setValue(Integer.parseInt(st2.nextToken()));
        dolaresTribuna.setValue(Integer.parseInt(st3.nextToken()));
        dolaresPalco.setValue(Integer.parseInt(st4.nextToken()));
        centavosGeneralN.setValue(Integer.parseInt(st.nextToken()));
        centavosGeneralS.setValue(Integer.parseInt(st2.nextToken()));
        centavosTribuna.setValue(Integer.parseInt(st3.nextToken()));
        centavosPalco.setValue(Integer.parseInt(st4.nextToken()));
    }
    public void buildEvento(){
       Date sqldate = new Date(fechatxt.getDate().getTime());        
        Time t = java.sql.Time.valueOf(horatxt.getText());
        String[] infoEvento2 = new String[4];
        infoEvento2[0]= lugartxt.getText();
        infoEvento2[1]= equipo1txt.getText();
        infoEvento2[2]= equipo2txt.getText();
        infoEvento2[3]= Integer.toString(idEvento);
        int[] loc2 = new int[8];
        loc2[0] =generalNspin.getValue();
        loc2[1]=generalSspin.getValue();
        loc2[2]=tribunaSpin.getValue();
        loc2[3]=palcoSpin.getValue();
        loc2[4]=locData.get(0).getIdLocalidad();
        loc2[5]=locData.get(1).getIdLocalidad();
        loc2[6]=locData.get(2).getIdLocalidad();
        loc2[7]=locData.get(3).getIdLocalidad();
        double [] precios2 = new double[4];
        precios2[0]= Double.parseDouble(dolaresGeneralN.getValue() + "." + centavosGeneralN.getValue());
        precios2[1]= Double.parseDouble(dolaresGeneralS.getValue() + "." + centavosGeneralN.getValue());
        precios2[2]= Double.parseDouble(dolaresTribuna.getValue() + "." + centavosTribuna.getValue());
        precios2[3]= Double.parseDouble(dolaresPalco.getValue() + "." + centavosPalco.getValue());
        if(ce.modificarEvento(new Coneccion(), infoEvento2, precios2, loc2, sqldate, t)!=0){
               JOptionPane.showMessageDialog(null, "Evento modificado exitosamente.");
            }else{
                JOptionPane.showMessageDialog(null, "No se ha podido modificar.");
            }
    }
    
    
    public void blockearCopyPaste(java.awt.event.KeyEvent evt, JTextField jtf){
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            evt.consume();
            jtf.setText("");
             JOptionPane.showMessageDialog(null, "No se permiten espacios en blanco en los campos.");             
        }
        if(evt.getKeyCode()==KeyEvent.VK_CONTROL ){
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese los datos directamente desde el teclado.");      
            jtf.setText("");
        }
        if(evt.getKeyCode()==KeyEvent.VK_ALT || evt.getKeyCode()==KeyEvent.VK_SHIFT||  jtf.getText().contains("'")  || jtf.getText().contains("@") || jtf.getText().contains("=") || jtf.getText().contains("+") || jtf.getText().contains("*") ){
            evt.consume();
             JOptionPane.showMessageDialog(null, "No ingrese caracteres especiales.");
             jtf.setText("");
        }
     }
    public boolean validarEvento(){
        boolean flag=true;
        List<JTextField> listaEventos= new ArrayList<>();
        listaEventos.add(lugartxt);
        //listaEventos.add(horatxt);
        listaEventos.add(equipo1txt);
        listaEventos.add(equipo2txt);
        if(fechatxt.getDate()==null){
            JOptionPane.showMessageDialog(null, "Ingrese una fecha válida."); 
            return flag=false;
        }
        List<JSpinField> jsp = new ArrayList<>();
        jsp.add(generalNspin);
        jsp.add(generalSspin);
        jsp.add(tribunaSpin);
        jsp.add(palcoSpin);
        jsp.add(dolaresGeneralN);
        jsp.add(dolaresGeneralS);
        jsp.add(dolaresPalco);
        jsp.add(dolaresTribuna);
        if(vcp.validarCaracteres(listaEventos)){
            JOptionPane.showMessageDialog(null,"Procure no ingresar caracteres especiales en los campos.");
            return flag=false;
        }
        if(vcp.validarSpiners(jsp)){
            JOptionPane.showMessageDialog(null, "Asegúrese de establecer asientos y/o precios mayores a cero.");
            return flag=false;
        }      
        if(vcp.validarCamposVacios(listaEventos)){
             JOptionPane.showMessageDialog(null, "Existen campos vacíos obligatorios."); 
             return flag=false;
        }
        if(vcp.validarTamañoCampos(listaEventos)){
             JOptionPane.showMessageDialog(null, "Los datos ingresados superan el límite de caracteres (máximo 44).");
             return flag=false;
        }
        return flag;
    }
    public void crearEvento(){
        Date sqldate = new Date(fechatxt.getDate().getTime());        
        Time t = java.sql.Time.valueOf(horatxt.getText());
        String[] infoEvento = new String[3];
        infoEvento[0]= lugartxt.getText();
        infoEvento[1]= equipo1txt.getText();
        infoEvento[2]= equipo2txt.getText();
        int[] loc = new int[4];
        loc[0] =generalNspin.getValue();
        loc[1]=generalSspin.getValue();
        loc[2]=tribunaSpin.getValue();
        loc[3]=palcoSpin.getValue();
        double [] precios = new double[4];
        precios[0]= Double.parseDouble(dolaresGeneralN.getValue() + "." + centavosGeneralN.getValue());
        precios[1]= Double.parseDouble(dolaresGeneralS.getValue() + "." + centavosGeneralN.getValue());
        precios[2]= Double.parseDouble(dolaresTribuna.getValue() + "." + centavosTribuna.getValue());
        precios[3]= Double.parseDouble(dolaresPalco.getValue() + "." + centavosPalco.getValue());

        if(ce.guardarEvento(new Coneccion(), infoEvento, loc, precios, sqldate, t)!=0){
               JOptionPane.showMessageDialog(null, "Evento guardado exitosamente.");
            }else{
                JOptionPane.showMessageDialog(null, "No se ha podido registrar.");
            }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lugartxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        fechatxt = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        horatxt = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        equipo1txt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        equipo2txt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        generalNspin = new com.toedter.components.JSpinField();
        jLabel12 = new javax.swing.JLabel();
        generalSspin = new com.toedter.components.JSpinField();
        jLabel2 = new javax.swing.JLabel();
        tribunaSpin = new com.toedter.components.JSpinField();
        jLabel11 = new javax.swing.JLabel();
        palcoSpin = new com.toedter.components.JSpinField();
        jLabel13 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        dolaresPalco = new com.toedter.components.JSpinField();
        centavosPalco = new com.toedter.components.JSpinField();
        dolaresTribuna = new com.toedter.components.JSpinField();
        dolaresGeneralS = new com.toedter.components.JSpinField();
        centavosTribuna = new com.toedter.components.JSpinField();
        centavosGeneralS = new com.toedter.components.JSpinField();
        dolaresGeneralN = new com.toedter.components.JSpinField();
        centavosGeneralN = new com.toedter.components.JSpinField();
        trashB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(475, 225));
        setLocationByPlatform(true);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Crear Evento", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14)), javax.swing.BorderFactory.createEtchedBorder())); // NOI18N
        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Lugar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel1, gridBagConstraints);

        lugartxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lugartxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel2.add(lugartxt, gridBagConstraints);

        jLabel3.setText("Fecha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel3, gridBagConstraints);

        fechatxt.setMinSelectableDate(new java.util.Date(1487484101000L));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel2.add(fechatxt, gridBagConstraints);

        jLabel4.setText("Hora de inicio: (hh:mm:ss)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel4, gridBagConstraints);

        horatxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("h:mm:ss"))));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel2.add(horatxt, gridBagConstraints);

        jLabel5.setText("Equipo 1:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel5, gridBagConstraints);

        equipo1txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                equipo1txtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel2.add(equipo1txt, gridBagConstraints);

        jLabel6.setText("Equipo 2:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel6, gridBagConstraints);

        equipo2txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                equipo2txtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel2.add(equipo2txt, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("LOCALIDADES");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel9, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("NUMERO DE ASIENTOS");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel2.add(jLabel8, gridBagConstraints);

        jLabel10.setText("General Norte: (Asientos máx: 16)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel10, gridBagConstraints);

        generalNspin.setMaximum(30);
        generalNspin.setMinimum(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel2.add(generalNspin, gridBagConstraints);

        jLabel12.setText("General Sur: (Asientos máx: 16)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel12, gridBagConstraints);

        generalSspin.setMaximum(30);
        generalSspin.setMinimum(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel2.add(generalSspin, gridBagConstraints);

        jLabel2.setText("Tribuna: (Asientos máx: 20)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel2, gridBagConstraints);

        tribunaSpin.setMaximum(30);
        tribunaSpin.setMinimum(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel2.add(tribunaSpin, gridBagConstraints);

        jLabel11.setText("Palco: (Asientos máx: 20)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel11, gridBagConstraints);

        palcoSpin.setMaximum(30);
        palcoSpin.setMinimum(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 0);
        jPanel2.add(palcoSpin, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Todos los campos son obligatorios.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jLabel13, gridBagConstraints);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/saveIconR.png"))); // NOI18N
        jButton2.setText("Guardar Evento");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jButton2, gridBagConstraints);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelIconS.png"))); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 18;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jButton1, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("PRECIO POR LOCALIDAD");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(jLabel7, gridBagConstraints);

        jLabel14.setText("Dólares");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        jPanel2.add(jLabel14, gridBagConstraints);

        jLabel15.setText("Centavos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        jPanel2.add(jLabel15, gridBagConstraints);

        dolaresPalco.setMaximum(100);
        dolaresPalco.setMinimum(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(dolaresPalco, gridBagConstraints);

        centavosPalco.setMaximum(100);
        centavosPalco.setMinimum(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(centavosPalco, gridBagConstraints);

        dolaresTribuna.setMaximum(100);
        dolaresTribuna.setMinimum(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(dolaresTribuna, gridBagConstraints);

        dolaresGeneralS.setMaximum(100);
        dolaresGeneralS.setMinimum(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(dolaresGeneralS, gridBagConstraints);

        centavosTribuna.setMaximum(100);
        centavosTribuna.setMinimum(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(centavosTribuna, gridBagConstraints);

        centavosGeneralS.setMaximum(100);
        centavosGeneralS.setMinimum(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(centavosGeneralS, gridBagConstraints);

        dolaresGeneralN.setMaximum(100);
        dolaresGeneralN.setMinimum(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 20;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(dolaresGeneralN, gridBagConstraints);

        centavosGeneralN.setMaximum(100);
        centavosGeneralN.setMinimum(0);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(centavosGeneralN, gridBagConstraints);

        trashB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sweet, sweet trash.png"))); // NOI18N
        trashB.setText("Eliminar Evento");
        trashB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trashBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(trashB, gridBagConstraints);

        jPanel1.add(jPanel2, "card2");

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lugartxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lugartxtKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ALT || evt.getKeyCode()==KeyEvent.VK_SHIFT||  lugartxt.getText().contains("'")  || lugartxt.getText().contains("@") || lugartxt.getText().contains("=") || lugartxt.getText().contains("+") || lugartxt.getText().contains("*") ){
            evt.consume();
             JOptionPane.showMessageDialog(null, "No ingrese caracteres especiales.");
             lugartxt.setText("");
        }
    }//GEN-LAST:event_lugartxtKeyReleased

    private void equipo1txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_equipo1txtKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ALT || evt.getKeyCode()==KeyEvent.VK_SHIFT||  lugartxt.getText().contains("'")  || lugartxt.getText().contains("@") || lugartxt.getText().contains("=") || lugartxt.getText().contains("+") || lugartxt.getText().contains("*") ){
            evt.consume();
             JOptionPane.showMessageDialog(null, "No ingrese caracteres especiales.");
            equipo1txt.setText("");
        }
    }//GEN-LAST:event_equipo1txtKeyReleased

    private void equipo2txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_equipo2txtKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ALT || evt.getKeyCode()==KeyEvent.VK_SHIFT||  lugartxt.getText().contains("'")  || lugartxt.getText().contains("@") || lugartxt.getText().contains("=") || lugartxt.getText().contains("+") || lugartxt.getText().contains("*") ){
            evt.consume();
             JOptionPane.showMessageDialog(null, "No ingrese caracteres especiales.");
             equipo2txt.setText("");
        }
    }//GEN-LAST:event_equipo2txtKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(validarEvento()){
            if(crear){
            crearEvento();
            dispose();
            }else{
                buildEvento();
                dispose();
                locData.clear();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void trashBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trashBActionPerformed
    if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este evento?")==0){
        if(ev.deleteEvento(new Coneccion(), idEvento)==1){  
        JOptionPane.showMessageDialog(null, "Evento eliminado.");
        dispose();
        }
    }
    }//GEN-LAST:event_trashBActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.components.JSpinField centavosGeneralN;
    private com.toedter.components.JSpinField centavosGeneralS;
    private com.toedter.components.JSpinField centavosPalco;
    private com.toedter.components.JSpinField centavosTribuna;
    private com.toedter.components.JSpinField dolaresGeneralN;
    private com.toedter.components.JSpinField dolaresGeneralS;
    private com.toedter.components.JSpinField dolaresPalco;
    private com.toedter.components.JSpinField dolaresTribuna;
    private javax.swing.JTextField equipo1txt;
    private javax.swing.JTextField equipo2txt;
    private com.toedter.calendar.JDateChooser fechatxt;
    private com.toedter.components.JSpinField generalNspin;
    private com.toedter.components.JSpinField generalSspin;
    private javax.swing.JFormattedTextField horatxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField lugartxt;
    private com.toedter.components.JSpinField palcoSpin;
    private javax.swing.JButton trashB;
    private com.toedter.components.JSpinField tribunaSpin;
    // End of variables declaration//GEN-END:variables
}
