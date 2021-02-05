/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import control.Coneccion;
import control.ControlPersonas;
import control.ValidarCampos;
import dao.PersonaDAO;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class DialogCrearModificar extends javax.swing.JDialog {
    ValidarCampos vc1 = new ValidarCampos();
    Coneccion con;
    ControlPersonas controlPersonas = new ControlPersonas();
    boolean crear,cliente;
    String user, pass;
    String[] clientData;
    String[] adminData;
    PersonaDAO pd= new PersonaDAO();
    /**
     * Creates new form DialogCrearModificar
     */
    public DialogCrearModificar(java.awt.Frame parent, boolean modal, boolean idCrear, boolean cliente, String user, String pass, Coneccion con) {
        super(parent, modal);
        initComponents();
        this.crear=idCrear;
        this.cliente=cliente;
        this.user=user;
        this.pass=pass;
        this.con=con;
        if(idCrear && cliente){
            pasaportetxt.setEnabled(false);
            crearB.setText("Crear Usuario.");
        }
        if(idCrear==false && cliente){
            crearB.setText("Guardar Datos.");
            setUserData();
            usuariotxt.setEnabled(false);
            passtxt.setEnabled(false);
        }
        if(idCrear && cliente==false){
            crearB.setText("Crear Administrador.");
            pasaportetxt.setEnabled(false);
            jLabel22.setVisible(false);
            tarjetaB.setVisible(false);
            efectivoB.setVisible(false);
            jLabel21.setVisible(false);
            tarjetatxt.setVisible(false);
            tarjetaB.setEnabled(false);
            efectivoB.setEnabled(false);
            tarjetatxt.setEnabled(false);
            tarjetatxt.setText("No entries.");
        }
        if(idCrear==false && cliente==false){
            adminData=controlPersonas.buscarAdministrador(con, user, pass);
            setAdminData();
            crearB.setText("Guardar Datos.");
            usuariotxt.setEnabled(false);
            passtxt.setEnabled(false);
            jLabel22.setVisible(false);
            tarjetaB.setVisible(false);
            efectivoB.setVisible(false);
            jLabel21.setVisible(false);
            tarjetatxt.setVisible(false);
            tarjetaB.setEnabled(false);
            efectivoB.setEnabled(false);
            tarjetatxt.setEnabled(false);
            tarjetatxt.setText("No entries.");
        }
        
        
    }
    public boolean crearAdministrador(){
        boolean band2=true;
        String[]  datosAdministrador = new  String[9];
        datosAdministrador[0]= usuariotxt.getText();
        datosAdministrador[1]= passtxt.getText();
        datosAdministrador[2]= nombretxt.getText();
        datosAdministrador[3]= apellidoPtxt.getText();
         if (apellidoMtxt.getText().length()>0){
          datosAdministrador[4] = apellidoMtxt.getText();
          }else{
          datosAdministrador[4] = "";
              }
          if(cedulaB.isSelected()){
          datosAdministrador[5]=cedulatxt.getText();
          datosAdministrador[6]=""; 
        }else{
          datosAdministrador[5]="";
          datosAdministrador[6]=pasaportetxt.getText(); 
        }
        if (telefonotxt.getText().length()>0){
          datosAdministrador[7] = telefonotxt.getText();
        }else
        {
          datosAdministrador[7]="";
        }
        datosAdministrador[8]=correotxt.getText();
        if(pd.buscarUser(new Coneccion(), usuariotxt.getText())==1){
               JOptionPane.showMessageDialog(null,"Ese nombre de usuario ya está usado, por favor ingrese otro.");
               band2=false;
        }else{
            if(controlPersonas.guardarAdministrador(new Coneccion(), datosAdministrador)!=0){
               JOptionPane.showMessageDialog(null, "Administrador registrado exitosamente.");
               return band2=true;
            }else{
                JOptionPane.showMessageDialog(null, "No se ha podido registrar.");
                band2=false;
            }
        }
        return band2;
    }
    public String[] buildnewAdmin(){
        String[]  datosAdmin = new  String[9];
            datosAdmin[0]=usuariotxt.getText();
            datosAdmin[1]=passtxt.getText();
            datosAdmin[2]=nombretxt.getText();
            datosAdmin[3]=apellidoPtxt.getText();
            if(apellidoMtxt.getText()==""  || apellidoMtxt.getText().length()<=0){
                datosAdmin[4]="";
            }else{
                datosAdmin[4]=apellidoMtxt.getText();
            }
            if(cedulaB.isSelected())
            {
                 datosAdmin[5]=cedulatxt.getText();
                 datosAdmin[6]="";
               
            }else{
                 datosAdmin[5]="";
                 datosAdmin[6]=pasaportetxt.getText();
            }
            if(telefonotxt.getText()=="" || telefonotxt.getText().length()<=0){
                datosAdmin[7]="";
            }    else{
                datosAdmin[7]=telefonotxt.getText();
            }
            datosAdmin[8]=correotxt.getText();
            
        return datosAdmin; 
    }
    public String[] buildnewClient(){
        String[]  datosCliente = new  String[10];
            datosCliente[0]=usuariotxt.getText();
            datosCliente[1]=passtxt.getText();
            datosCliente[2]=nombretxt.getText();
            datosCliente[3]=apellidoPtxt.getText();
            if(apellidoMtxt.getText().length()==0){
                datosCliente[4]="";
            }else{
                datosCliente[4]=apellidoMtxt.getText();
            }
            if(cedulaB.isSelected())
            {
                 datosCliente[5]=cedulatxt.getText();
                 datosCliente[6]="";
               
            }else{
                 datosCliente[5]="";
                 datosCliente[6]=pasaportetxt.getText();
            }
            if(telefonotxt.getText().length()==0){
                datosCliente[7]="";
            }    else{
                datosCliente[7]=telefonotxt.getText();
            }
            datosCliente[8]=correotxt.getText();
            if(tarjetaB.isSelected()){
            datosCliente[9]=tarjetatxt.getText();
            }else{
            datosCliente[9]="";
            }
        return datosCliente; 
    }
    public void setAdminData(){
        usuariotxt.setText(adminData[0]);
        passtxt.setText(adminData[1]);
        nombretxt.setText(adminData[2]);
        apellidoPtxt.setText(adminData[3]);
       if(adminData[4].length()!=0){
            apellidoMtxt.setText(adminData[4]);
        }else{
         
            apellidoMtxt.setText("");
        }
        if(adminData[5].length()!=0){
        cedulatxt.setText(adminData[5]);
        pasaportetxt.setText("");
        cedulaB.setSelected(true);
        pasaporteB.setSelected(false);
        }else{
        cedulaB.setSelected(false);
        pasaporteB.setSelected(true);
        cedulatxt.setText("");
        pasaportetxt.setText(adminData[6]);
        }
        if(adminData[7].length()==0){
            telefonotxt.setText("");
        }else{
            telefonotxt.setText(adminData[7]);
        }
        correotxt.setText(adminData[8]);
        
    }
     public void setUserData(){
         clientData = controlPersonas.buscarCliente(con, user, pass);
        usuariotxt.setText(clientData[0]);
        passtxt.setText(clientData[1]);
        nombretxt.setText(clientData[2]);
        apellidoPtxt.setText(clientData[3]);
        if(clientData[4].length()==0){
            apellidoMtxt.setText("");
        }else{
            apellidoMtxt.setText(clientData[4]);
        }
        if(clientData[5].length()==0){
            pasaportetxt.setText(clientData[6]);
            cedulatxt.setText("");
            pasaporteB.setSelected(true);
        }else{
            cedulaB.setSelected(true);
            cedulatxt.setText(clientData[5]);
            pasaportetxt.setText("");
        }
        if(clientData[7].length()==0){
            telefonotxt.setText("");
        }else{
            telefonotxt.setText(clientData[7]);
        }
        correotxt.setText(clientData[8]);
        if(clientData[9].length()==0){
            tarjetatxt.setText("");
            tarjetatxt.setEnabled(false);
            efectivoB.setSelected(true);
        }else{
            tarjetaB.setSelected(true);
            tarjetatxt.setText(clientData[9]);
        }
        
    }
    public boolean validarUsuario(){        
        boolean flag = true;
        List<JTextField> listaCreacionUsuario= new ArrayList<>();
        listaCreacionUsuario.add(usuariotxt);
        listaCreacionUsuario.add(passtxt);
        listaCreacionUsuario.add(nombretxt);
        listaCreacionUsuario.add(apellidoPtxt);
        if(apellidoMtxt.getText().length()>0){
        listaCreacionUsuario.add(apellidoMtxt);
        }
        if(cedulaB.isSelected()){
            if(cedulatxt.getText().length()>10){
                JOptionPane.showMessageDialog(null, "Número de cédula inválido");
                return flag=false;
            }
            else{
            listaCreacionUsuario.add(cedulatxt);
            pasaportetxt.setBackground(Color.white);
            }
        }else{
        listaCreacionUsuario.add(pasaportetxt);    
        cedulatxt.setBackground(Color.white);
        }
        if(telefonotxt.getText().length()>0){
        listaCreacionUsuario.add(telefonotxt);
        }
        //listaCreacionUsuario.add(correotxt);
        if (tarjetaB.isSelected()){
        listaCreacionUsuario.add(tarjetatxt);
        }else{
            tarjetatxt.setBackground(Color.white);
        }
        if(correotxt.getText().length()==0){
            correotxt.setBackground(Color.red);
        }else{
            correotxt.setBackground(Color.white);
        }
        if (vc1.validarCamposVacios(listaCreacionUsuario)){
            JOptionPane.showMessageDialog(null, "Existen campos vacíos obligatorios.");  
            return flag=false;
        } 
       
        if(vc1.validarTamañoCampos(listaCreacionUsuario)){
            JOptionPane.showMessageDialog(null, "Los datos ingresados superan el límite de caracteres (máximo 44).");
            return flag=false;
        }
        if (verificarEmail(correotxt)){
            JOptionPane.showMessageDialog(null, "Ingrese un correo válido.");
            return flag=false;
        }
        if(vc1.validarCaracteres(listaCreacionUsuario)){
            JOptionPane.showMessageDialog(null, "Procure no ingresar caracteres especiales en los campos.");
            return flag=false;
        }
        return flag;
    }
    public boolean crearUsuario(){
        boolean band=true;
          String[]  datosPersona = new  String[10];
          datosPersona[0] = usuariotxt.getText();
          datosPersona[1] = passtxt.getText();
          datosPersona[2] = nombretxt.getText();
          datosPersona[3] = apellidoPtxt.getText();
          if (apellidoMtxt.getText().length()>0){
          datosPersona[4] = apellidoMtxt.getText();
          }else{
          datosPersona[4] = "";
              }
          if(cedulaB.isSelected()){
          datosPersona[5]=cedulatxt.getText();
          datosPersona[6]=""; 
        }else{
          datosPersona[5]="";
          datosPersona[6]=pasaportetxt.getText(); 
        }
        if (telefonotxt.getText().length()>0){
          datosPersona[7] = telefonotxt.getText();
        }else
        {
          datosPersona[7]="";
        }
        
            datosPersona[8] = correotxt.getText();
        
          
          if (tarjetaB.isSelected()){
          datosPersona[9] = tarjetatxt.getText(); 
        }else{
          datosPersona[9] = "";
        }
          if(pd.buscarUser(new Coneccion(),usuariotxt.getText())==1){
            JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya está usado, por favor ingrese otro.");
                 band=false;
            }else{
              if(controlPersonas.guardarUsuario(new Coneccion(), datosPersona)!=0){
               JOptionPane.showMessageDialog(null, "Guardado exitosamente.");
                   band=true;
                     }else{
                JOptionPane.showMessageDialog(null, "No se ha podido registrar.");
                band=false;
            }
          }
             
          return band;
    }
    public void blockearCopyPaste(java.awt.event.KeyEvent evt, JTextField jtf){
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            evt.consume();
            jtf.setText("");
             JOptionPane.showMessageDialog(null, "No se permiten espacios en blanco en los campos.");             
        }
        if(evt.getKeyCode()==KeyEvent.VK_CONTROL){
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese los datos directamente desde el teclado.");      
            jtf.setText("");
        }
        
     }
    public boolean verificarEmail(JTextField jTextField){
        if (!(Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", jTextField.getText()))){ 
        return true;        
                } 
        return false;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        usuariotxt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        nombretxt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        apellidoPtxt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        apellidoMtxt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        correotxt = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tarjetaB = new javax.swing.JRadioButton();
        efectivoB = new javax.swing.JRadioButton();
        jLabel23 = new javax.swing.JLabel();
        cedulaB = new javax.swing.JRadioButton();
        pasaportetxt = new javax.swing.JTextField();
        pasaporteB = new javax.swing.JRadioButton();
        crearB = new javax.swing.JButton();
        cancelarB = new javax.swing.JButton();
        passtxt = new javax.swing.JPasswordField();
        telefonotxt = new javax.swing.JTextField();
        cedulatxt = new javax.swing.JTextField();
        tarjetatxt = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Crear Usuario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14)), javax.swing.BorderFactory.createEtchedBorder())); // NOI18N
        jPanel2.setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Ingrese sus datos, los campos obligatorios estan acompañados por un *");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 8, 8, 6);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel13.setText("Usuario*:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(14, 15, 13, 11);
        jPanel1.add(jLabel13, gridBagConstraints);

        usuariotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usuariotxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 326;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 11);
        jPanel1.add(usuariotxt, gridBagConstraints);

        jLabel14.setText("Contraseña*:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(14, 15, 13, 11);
        jPanel1.add(jLabel14, gridBagConstraints);

        jLabel15.setText("Nombre*:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(14, 15, 13, 11);
        jPanel1.add(jLabel15, gridBagConstraints);

        nombretxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombretxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 326;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 11);
        jPanel1.add(nombretxt, gridBagConstraints);

        jLabel16.setText("Apellido Paterno*:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(14, 15, 13, 11);
        jPanel1.add(jLabel16, gridBagConstraints);

        apellidoPtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                apellidoPtxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 326;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 11);
        jPanel1.add(apellidoPtxt, gridBagConstraints);

        jLabel17.setText("Apellido Materno:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(14, 15, 13, 11);
        jPanel1.add(jLabel17, gridBagConstraints);

        apellidoMtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                apellidoMtxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 323;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 11);
        jPanel1.add(apellidoMtxt, gridBagConstraints);

        jLabel18.setText("Cedula*:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(14, 15, 13, 11);
        jPanel1.add(jLabel18, gridBagConstraints);

        jLabel19.setText("Pasaporte:*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(14, 15, 13, 11);
        jPanel1.add(jLabel19, gridBagConstraints);

        correotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                correotxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 326;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 11);
        jPanel1.add(correotxt, gridBagConstraints);

        jLabel20.setText("Correo*:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(14, 15, 13, 11);
        jPanel1.add(jLabel20, gridBagConstraints);

        jLabel21.setText("Num. Tarjeta*:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 21;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(14, 15, 13, 11);
        jPanel1.add(jLabel21, gridBagConstraints);

        jLabel22.setText("Método de Pago Predeterminado*:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(14, 15, 13, 11);
        jPanel1.add(jLabel22, gridBagConstraints);

        buttonGroup1.add(tarjetaB);
        tarjetaB.setSelected(true);
        tarjetaB.setText("Tarjeta");
        tarjetaB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tarjetaBItemStateChanged(evt);
            }
        });
        tarjetaB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarjetaBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 11, 8, 8);
        jPanel1.add(tarjetaB, gridBagConstraints);

        buttonGroup1.add(efectivoB);
        efectivoB.setText("Efectivo");
        efectivoB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                efectivoBItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 8, 8);
        jPanel1.add(efectivoB, gridBagConstraints);

        jLabel23.setText("Teléfono:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(14, 15, 13, 11);
        jPanel1.add(jLabel23, gridBagConstraints);

        buttonGroup2.add(cedulaB);
        cedulaB.setSelected(true);
        cedulaB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulaBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 22;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 4, 11, 4);
        jPanel1.add(cedulaB, gridBagConstraints);

        pasaportetxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasaportetxtActionPerformed(evt);
            }
        });
        pasaportetxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pasaportetxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 326;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 11);
        jPanel1.add(pasaportetxt, gridBagConstraints);

        buttonGroup2.add(pasaporteB);
        pasaporteB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pasaporteBItemStateChanged(evt);
            }
        });
        pasaporteB.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pasaporteBStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 22;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 4, 11, 4);
        jPanel1.add(pasaporteB, gridBagConstraints);

        crearB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/userIcon.png"))); // NOI18N
        crearB.setText("Crear Usuario");
        crearB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(crearB, gridBagConstraints);

        cancelarB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ssCancelIcon.png"))); // NOI18N
        cancelarB.setText("Cancelar");
        cancelarB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanel1.add(cancelarB, gridBagConstraints);

        passtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passtxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 323;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 11);
        jPanel1.add(passtxt, gridBagConstraints);

        telefonotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonotxtActionPerformed(evt);
            }
        });
        telefonotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telefonotxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 11);
        jPanel1.add(telefonotxt, gridBagConstraints);

        cedulatxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulatxtActionPerformed(evt);
            }
        });
        cedulatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cedulatxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 11);
        jPanel1.add(cedulatxt, gridBagConstraints);

        tarjetatxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        tarjetatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tarjetatxtKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 21;
        gridBagConstraints.gridwidth = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 11, 11);
        jPanel1.add(tarjetatxt, gridBagConstraints);

        jPanel2.add(jPanel1, "card2");

        getContentPane().add(jPanel2, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usuariotxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuariotxtKeyReleased
        blockearCopyPaste(evt, usuariotxt);
    }//GEN-LAST:event_usuariotxtKeyReleased

    private void nombretxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombretxtKeyReleased
       if(vc1.validarletras(nombretxt)){
           evt.consume();
           nombretxt.setText("");
       }
        if(evt.getKeyCode()==KeyEvent.VK_ALT || evt.getKeyCode()==KeyEvent.VK_SHIFT||  nombretxt.getText().contains("'")  || nombretxt.getText().contains("@") || nombretxt.getText().contains("=") || nombretxt.getText().contains("+") || nombretxt.getText().contains("*") ){
            evt.consume();
             JOptionPane.showMessageDialog(null, "No ingrese caracteres especiales.");
             nombretxt.setText("");
        }
       
    }//GEN-LAST:event_nombretxtKeyReleased

    private void apellidoPtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoPtxtKeyReleased
        if(vc1.validarletras(apellidoPtxt)){
            evt.consume();
            apellidoPtxt.setText("");
        }
        blockearCopyPaste(evt, apellidoPtxt);
    }//GEN-LAST:event_apellidoPtxtKeyReleased

    private void apellidoMtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoMtxtKeyReleased
        if(vc1.validarletras(apellidoMtxt)){
            evt.consume();
            apellidoMtxt.setText("");
        }
        blockearCopyPaste(evt, apellidoMtxt);
    }//GEN-LAST:event_apellidoMtxtKeyReleased

    private void correotxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_correotxtKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            evt.consume();
            correotxt.setText("");
            JOptionPane.showMessageDialog(null, "No se permiten espacios en blanco en los campos.");
        }
    }//GEN-LAST:event_correotxtKeyReleased

    private void tarjetaBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tarjetaBItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tarjetaBItemStateChanged

    private void tarjetaBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarjetaBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarjetaBActionPerformed

    private void efectivoBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_efectivoBItemStateChanged
        if(efectivoB.isSelected()){
            tarjetatxt.setEnabled(false);

        }else{
            tarjetatxt.setEnabled(true);
        }
    }//GEN-LAST:event_efectivoBItemStateChanged

    private void cedulaBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulaBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cedulaBActionPerformed

    private void pasaportetxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasaportetxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pasaportetxtActionPerformed

    private void pasaportetxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pasaportetxtKeyReleased
        blockearCopyPaste(evt, pasaportetxt);
    }//GEN-LAST:event_pasaportetxtKeyReleased

    private void pasaporteBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pasaporteBItemStateChanged
        if(pasaporteB.isSelected()){
            pasaportetxt.setEnabled(true);
            cedulatxt.setEnabled(false);
        }
        else{
            pasaportetxt.setEnabled(false);
            cedulatxt.setEnabled(true);
        }
    }//GEN-LAST:event_pasaporteBItemStateChanged

    private void pasaporteBStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pasaporteBStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_pasaporteBStateChanged

    private void crearBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearBActionPerformed
        
        if(validarUsuario()){
            if(crear && cliente){
                if(crearUsuario()){
                    dispose();
                    new Logeo().setVisible(true);
                }
             }
            if(crear==false && cliente){
                if(controlPersonas.modificarCliente(new Coneccion(), Integer.parseInt(clientData[10]), buildnewClient())!=0){
                JOptionPane.showMessageDialog(null, "Datos modificados exitosamente.");
                dispose();
             }
            }
            if(crear && cliente==false){
                if(crearAdministrador()){
                dispose();
                }
            }
            if(crear==false & cliente==false){
                if(controlPersonas.modificarAdministrador(new Coneccion(), Integer.parseInt(adminData[9]), buildnewAdmin())!=0){
                JOptionPane.showMessageDialog(null, "Datos guardados exitosamente.");
                dispose();
            }
            }
        }
    }//GEN-LAST:event_crearBActionPerformed

    private void cancelarBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBActionPerformed
        
        if(crear && cliente){
        dispose();
        }
        dispose();
    }//GEN-LAST:event_cancelarBActionPerformed

    private void passtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passtxtKeyReleased
        blockearCopyPaste(evt, passtxt);
    }//GEN-LAST:event_passtxtKeyReleased

    private void cedulatxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulatxtKeyReleased
    if(vc1.validarNumbers(cedulatxt)){
        evt.consume();
        cedulatxt.setText("");
    }
    }//GEN-LAST:event_cedulatxtKeyReleased

    private void cedulatxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulatxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cedulatxtActionPerformed

    private void telefonotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonotxtActionPerformed
       
    }//GEN-LAST:event_telefonotxtActionPerformed

    private void telefonotxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonotxtKeyReleased
        if(vc1.validarNumbers(telefonotxt)){
            evt.consume();
            telefonotxt.setText("");
        }
    }//GEN-LAST:event_telefonotxtKeyReleased

    private void tarjetatxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarjetatxtKeyReleased
        blockearCopyPaste(evt, tarjetatxt);
        if(vc1.validarNumbers(tarjetatxt)){
            evt.consume();
            tarjetatxt.setText("");
        }
    }//GEN-LAST:event_tarjetatxtKeyReleased

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoMtxt;
    private javax.swing.JTextField apellidoPtxt;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton cancelarB;
    private javax.swing.JRadioButton cedulaB;
    private javax.swing.JTextField cedulatxt;
    private javax.swing.JTextField correotxt;
    private javax.swing.JButton crearB;
    private javax.swing.JRadioButton efectivoB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nombretxt;
    private javax.swing.JRadioButton pasaporteB;
    private javax.swing.JTextField pasaportetxt;
    private javax.swing.JPasswordField passtxt;
    private javax.swing.JRadioButton tarjetaB;
    private javax.swing.JFormattedTextField tarjetatxt;
    private javax.swing.JTextField telefonotxt;
    private javax.swing.JTextField usuariotxt;
    // End of variables declaration//GEN-END:variables
}
