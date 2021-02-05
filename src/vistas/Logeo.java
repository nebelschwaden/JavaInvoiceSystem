/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import control.*;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class Logeo extends javax.swing.JFrame {

    ValidarCampos vc = new ValidarCampos();
    Coneccion con = new Coneccion();
    ControlPersonas cp = new ControlPersonas();
    /**
     * Creates new form Logeo
     */
    public Logeo() {
        initComponents();
        jButton2.setEnabled(false);
        jButton1.setEnabled(false);
        usuariotxt.setEditable(false);
        passtxt.setEditable(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (validar(con) == 1) {
                }
            }
        }).start();
    }

    public int validar(Coneccion con) {
        int k;
        con.crearConeccion();
        if (con.getConeccion() != null) {
            k = 1;
            JOptionPane.showMessageDialog(null, "Connected!");
            jButton2.setEnabled(true);
            jButton1.setEnabled(true);
            usuariotxt.setEditable(true);
            passtxt.setEditable(true);
            jLabel3.setVisible(false);
        } else {
            k = 0;
        }
        return k;
    }

    private boolean validarLogeo() {
        List<JTextField> listaCamposLogeo = new ArrayList<>();
        listaCamposLogeo.add(usuariotxt);
        listaCamposLogeo.add(passtxt);
        boolean flag = true;
        boolean camposVacios = vc.validarCamposVacios(listaCamposLogeo);
        if (camposVacios) {
            JOptionPane.showMessageDialog(null, "Existen campos vacíos obligatorios.");
            return flag = false;
        }
        boolean tamañoCampos = vc.validarTamañoCampos(listaCamposLogeo);
        if (tamañoCampos) {
            JOptionPane.showMessageDialog(null, "Los datos ingresados superan el límite de caracteres (máximo 44).");
            return flag  = false;
        } 
        return flag;
    }

    public void blockearCopyPaste(java.awt.event.KeyEvent evt, JTextField jtf) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "No se permiten espacios en blanco en los campos.");
            jtf.setText("");
        }
        if (evt.getKeyCode() == KeyEvent.VK_CONTROL) {
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
        usuariotxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passtxt = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(475, 225));
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingresar al Sistema", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS UI Gothic", 1, 14)), javax.swing.BorderFactory.createEtchedBorder())); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        jPanel2.setLayout(new java.awt.GridLayout(3, 3));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Usuario:");
        jPanel2.add(jLabel1);

        usuariotxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usuariotxtKeyReleased(evt);
            }
        });
        jPanel2.add(usuariotxt);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Contraseña:");
        jPanel2.add(jLabel2);

        passtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passtxtKeyReleased(evt);
            }
        });
        jPanel2.add(passtxt);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/loginIcon.png"))); // NOI18N
        jButton2.setText("Ingresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ssCancelIcon.png"))); // NOI18N
        jButton3.setText("Salir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        jPanel1.add(jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(400, 50));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/userIcon.png"))); // NOI18N
        jButton1.setText("Registrarse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(25, 25, 25, 25);
        jPanel3.add(jButton1, gridBagConstraints);

        jLabel3.setText("Conectando...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(9, 43, 9, 43);
        jPanel3.add(jLabel3, gridBagConstraints);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      int i=identificar();
        if(i==1){
            dispose();
                new VentanaAdministrar(con,usuariotxt.getText(),passtxt.getText()).setVisible(true);
                          }
        if(i==2){
            dispose();
                new VentanaEventos(usuariotxt.getText(),passtxt.getText(),con).setVisible(true);
            }    
        if(i==0){
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void usuariotxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuariotxtKeyReleased
        blockearCopyPaste(evt, usuariotxt);
    }//GEN-LAST:event_usuariotxtKeyReleased

    private void passtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passtxtKeyReleased
        blockearCopyPaste(evt, passtxt);
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        int i=identificar();
        if(i==1){
            dispose();
                new VentanaAdministrar(con,usuariotxt.getText(),passtxt.getText()).setVisible(true);
                          }
        if(i==2){
            dispose();
                new VentanaEventos(usuariotxt.getText(),passtxt.getText(),con).setVisible(true);
            }    
        if(i==0){
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
             }
        }
    }//GEN-LAST:event_passtxtKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new DialogCrearModificar(this, true, true,true, null, null,null).setVisible(true);
        dispose();
        //new CrearUsuario().setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed
    public int identificar(){
        int flag=0;
        if (validarLogeo()) {
            if(cp.buscarAdministrador(con, usuariotxt.getText(), passtxt.getText())!=null){
                flag=1;
            }
             if(cp.buscarCliente(con, usuariotxt.getText(), passtxt.getText())!=null){
                flag=2; 
            }
        }
        return flag;
    }
    /** 
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField passtxt;
    private javax.swing.JTextField usuariotxt;
    // End of variables declaration//GEN-END:variables
}
