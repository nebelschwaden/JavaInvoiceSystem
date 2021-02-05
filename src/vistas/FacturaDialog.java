/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import control.Coneccion;
import control.ControlEventos;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class FacturaDialog extends javax.swing.JDialog {
    
    ControlEventos ce = new ControlEventos();
    private DefaultTableModel modelo;
    double preciou;
    String equipos;
    int cant;
    /**
     * Creates new form FacturaDialog
     */
    public FacturaDialog(java.awt.Frame parent, boolean modal,double preciou,int numBoletos, String equipos) {
        super(parent, modal);
        initComponents();
        this.equipos=equipos;
        this.preciou=preciou;
        this.cant=numBoletos;
        obtainData();
        jTable1.setDefaultEditor(Object.class, null);
        setData();
    }
    public void obtainData(){
        String[] data = new String[5];
        data = ce.facturaInfo(new Coneccion());
        if(data!=null)
        {
        idtxt.setText(data[0]);
        nombretxt.setText(data[1]);
        telftxt.setText(data[2]);
        fechatxt.setText(data[3]);
        correotxt.setText(data[4]);
        }else
        {
            JOptionPane.showMessageDialog(null, "No se pudo generar la factura.");
        }
    }
    private void cargarTabla(){
        modelo = new DefaultTableModel();
        modelo.addColumn("Descripción");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio unitario");
        modelo.addColumn("Precio Total");
        //Hilo reconeccion
        //Database backup
    }
    public void setData(){
        cargarTabla();
        double total = preciou*cant;
        subtotalTxt.setText(Double.toString(total));
        double iva = (total*0.12);
        ivaTxt.setText(Double.toString(iva));
        totalTxt.setText(Double.toString(iva+total));
        String[] rowData = new String[4];
        rowData[0]= "Boleto: "+equipos;
        rowData[1]= Integer.toString(cant);
        rowData[2]= Double.toString(preciou);
        rowData[3]= Double.toString(total);
        modelo.addRow(rowData);
        jTable1.setModel(modelo);
        jTable1.revalidate();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        idtxt = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombretxt = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        telftxt = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fechatxt = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        correotxt = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        subtotalTxt = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ivaTxt = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        totalTxt = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        confirmarFactura = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationByPlatform(true);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Factura", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 11)), javax.swing.BorderFactory.createEtchedBorder())); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(3, 1));

        jPanel2.setLayout(new java.awt.GridLayout(5, 2));

        jLabel1.setFont(new java.awt.Font("MS UI Gothic", 1, 12)); // NOI18N
        jLabel1.setText("Número de Factura:");
        jPanel2.add(jLabel1);

        idtxt.setText("facturaNum");
        jPanel2.add(idtxt);

        jLabel2.setFont(new java.awt.Font("MS UI Gothic", 1, 12)); // NOI18N
        jLabel2.setText("Nombre:");
        jPanel2.add(jLabel2);

        nombretxt.setText("nombreS");
        jPanel2.add(nombretxt);

        jLabel4.setFont(new java.awt.Font("MS UI Gothic", 1, 12)); // NOI18N
        jLabel4.setText("Teléfono:");
        jPanel2.add(jLabel4);

        telftxt.setText("telfE");
        jPanel2.add(telftxt);

        jLabel5.setFont(new java.awt.Font("MS UI Gothic", 1, 12)); // NOI18N
        jLabel5.setText("Fecha:");
        jPanel2.add(jLabel5);

        fechatxt.setText("fechaT");
        jPanel2.add(fechatxt);

        label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        label.setText("Correo:");
        jPanel2.add(label);

        correotxt.setText("correot");
        jPanel2.add(correotxt);

        jPanel1.add(jPanel2);

        jPanel3.setMaximumSize(new java.awt.Dimension(604, 143));
        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        jPanel5.setMaximumSize(new java.awt.Dimension(302, 75));
        jPanel5.setLayout(new java.awt.CardLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Descripción", "Cantidad", "Precio Unitario", "Precio Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1, "card2");

        jPanel3.add(jPanel5);

        jPanel6.setMaximumSize(new java.awt.Dimension(302, 75));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel7);

        jPanel8.setLayout(new java.awt.GridLayout(3, 2));

        jLabel6.setFont(new java.awt.Font("MS UI Gothic", 1, 12)); // NOI18N
        jLabel6.setText("Subtotal:");
        jPanel8.add(jLabel6);

        subtotalTxt.setText("subTxt");
        jPanel8.add(subtotalTxt);

        jLabel7.setFont(new java.awt.Font("MS UI Gothic", 1, 12)); // NOI18N
        jLabel7.setText("IVA:");
        jPanel8.add(jLabel7);

        ivaTxt.setText("iva");
        jPanel8.add(ivaTxt);

        jLabel8.setFont(new java.awt.Font("MS UI Gothic", 1, 12)); // NOI18N
        jLabel8.setText("Total:");
        jPanel8.add(jLabel8);

        totalTxt.setText("tTxt");
        jPanel8.add(totalTxt);

        jPanel6.add(jPanel8);

        jPanel3.add(jPanel6);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        confirmarFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agreeicon.png"))); // NOI18N
        confirmarFactura.setText("Aceptar");
        confirmarFactura.setPreferredSize(new java.awt.Dimension(133, 25));
        confirmarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarFacturaActionPerformed(evt);
            }
        });
        jPanel4.add(confirmarFactura);

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarFacturaActionPerformed
        closeAllDialogs();
    }//GEN-LAST:event_confirmarFacturaActionPerformed
    private void closeAllDialogs()
    {
    Window[] windows = getWindows();
    for (Window window : windows)
        {
        if (window instanceof JDialog)
            {
            window.dispose();
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmarFactura;
    private javax.swing.JLabel correotxt;
    private javax.swing.JLabel fechatxt;
    private javax.swing.JLabel idtxt;
    private javax.swing.JLabel ivaTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel nombretxt;
    private javax.swing.JLabel subtotalTxt;
    private javax.swing.JLabel telftxt;
    private javax.swing.JLabel totalTxt;
    // End of variables declaration//GEN-END:variables
}