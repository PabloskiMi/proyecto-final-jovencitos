/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases.Conexion;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pablo
 */
public class Vehiculo extends javax.swing.JFrame {

    /**
     * Creates new form Vehiculo
     */
    public Vehiculo() {

        initComponents();
        this.cmb_Tipo.setModel(getListTipo());
        this.cmb_Marca.setModel(getListMarca());

        setIconImage(getIcoImage());

        this.setLocationRelativeTo(null);
    }

    public Image getIcoImage() {
        Image retVaIue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/logo.png"));
        return retVaIue;
    }

    public DefaultComboBoxModel getListTipo() {
        DefaultComboBoxModel listaTipo = new DefaultComboBoxModel();

        ResultSet rs;

        try {

            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Tipo");

            rs = ps.executeQuery();

            while (rs.next()) {

                listaTipo.addElement(rs.getString("Nombre"));

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error en el llenar la lista");

        }

        return listaTipo;
    }

    public DefaultComboBoxModel getListMarca() {
        DefaultComboBoxModel listaMarca = new DefaultComboBoxModel();

        ResultSet rs;

        try {

            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Marca");

            rs = ps.executeQuery();

            while (rs.next()) {

                listaMarca.addElement(rs.getString("Nombre"));

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error en el llenar la lista");

        }

        return listaMarca;
    }

    public void EliminarVehiculo() {

        try {

            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Vehiculo WHERE Id_Vehiculo = ?");

            ps.setInt(1, Integer.parseInt(this.txt_Id.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro eliminado");

            Limpiar();
            CargarTabla();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error en el registro");

        }

    }

    public void ModificarVehiculo() {

        double precioCompra, precioVenta;

        precioCompra = Double.parseDouble(this.txt_PrecioCompra.getText());
        precioVenta = Double.parseDouble(this.txt_PrecioVenta.getText());

        try {

            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE Vehiculo SET Nombre = ?, Anio = ?, Id_Tipo = ?, Color = ?, NumeroSerie = ?, Codigo = ?, Id_Marca = ?, Version = ?, PrecioCompra = ?, PrecioVenta = ?, FechaCompra = ?, Descripcion = ? WHERE Id_Vehiculo = ?");

            ps.setString(1, this.txt_NombreVehiculo.getText());
            ps.setString(2, this.txt_Anio.getText());
            ps.setInt(3, this.cmb_Tipo.getSelectedIndex() + 1);
            ps.setString(4, this.txt_Codigo.getText());
            ps.setString(5, this.txt_NumeroDeSerie.getText());
            ps.setString(6, this.txt_Codigo.getText());
            ps.setInt(7, this.cmb_Marca.getSelectedIndex() + 1);
            ps.setString(8, this.txt_Version.getText());
            ps.setDouble(9, precioCompra);
            ps.setDouble(10, precioVenta);
            ps.setString(11, this.txt_FechaCompra.getText());
            ps.setString(12, this.txt_DescripcionVehiculo.getText());
            ps.setInt(13, Integer.parseInt(this.txt_Id.getText()));

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro modificado");

            Limpiar();
            CargarTabla();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error en el registro");

        }

    }

    public void CrearVehiculo() {

        double precioCompra, precioVenta;

        precioCompra = Double.parseDouble(this.txt_PrecioCompra.getText());
        precioVenta = Double.parseDouble(this.txt_PrecioVenta.getText());

        try {

            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO Vehiculo (Nombre, Anio, Id_Tipo, Color, NumeroSerie, Codigo, Id_Marca, Version, PrecioCompra, PrecioVenta, FechaCompra, Descripcion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, this.txt_NombreVehiculo.getText());
            ps.setString(2, this.txt_Anio.getText());
            ps.setInt(3, this.cmb_Tipo.getSelectedIndex() + 1);
            ps.setString(4, this.txt_Codigo.getText());
            ps.setString(5, this.txt_NumeroDeSerie.getText());
            ps.setString(6, this.txt_Codigo.getText());
            ps.setInt(7, this.cmb_Marca.getSelectedIndex() + 1);
            ps.setString(8, this.txt_Version.getText());
            ps.setDouble(9, precioCompra);
            ps.setDouble(10, precioVenta);
            ps.setString(11, this.txt_FechaCompra.getText());
            ps.setString(12, this.txt_DescripcionVehiculo.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro guardado");

            Limpiar();
            CargarTabla();

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error en el registro");

        }

    }

    public void CargarTabla() {

        DefaultTableModel modeloTabla = (DefaultTableModel) table_Vehiculo.getModel();
        modeloTabla.setRowCount(0);

        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        int[] anchos = {10, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
        for (int i = 0; i < table_Vehiculo.getColumnCount(); i++) {

            table_Vehiculo.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);

        }

        try {

            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT Id_Vehiculo, Nombre, Anio, Id_Tipo, Color, NumeroSerie, Codigo, Id_Marca, Version, PrecioCompra, PrecioVenta, FechaCompra, Descripcion FROM Vehiculo");

            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();

            while (rs.next()) {

                Object[] fila = new Object[columnas];
                for (int indice = 0; indice < columnas; indice++) {

                    fila[indice] = rs.getObject(indice + 1);

                }

                modeloTabla.addRow(fila);

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error en la tabla");

        }

    }

    public void Limpiar() {

        this.txt_Id.setText("");
        this.txt_NombreVehiculo.setText("");
        this.txt_Anio.setText("");
        this.cmb_Tipo.setSelectedIndex(0);
        this.txt_Color.setText("");
        this.txt_NumeroDeSerie.setText("");
        this.txt_Codigo.setText("");
        this.cmb_Marca.setSelectedIndex(0);
        this.txt_Version.setText("");
        this.txt_PrecioCompra.setText("");
        this.txt_PrecioVenta.setText("");
        this.txt_FechaCompra.setText("");
        this.txt_DescripcionVehiculo.setText("");

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_NombreVehiculo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_DescripcionVehiculo = new javax.swing.JTextArea();
        cmb_Tipo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_Vehiculo = new javax.swing.JTable();
        btn_Modificar = new javax.swing.JButton();
        btn_Crear = new javax.swing.JButton();
        btn_Limpiar = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_Consultar = new javax.swing.JButton();
        txt_Id = new javax.swing.JTextField();
        txt_Anio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_Color = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_NumeroDeSerie = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_Codigo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cmb_Marca = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txt_Version = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_PrecioCompra = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_PrecioVenta = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_FechaCompra = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btn_Inicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(650, 550));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Vehículo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, -1, -1));

        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));
        jPanel1.add(txt_NombreVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 150, -1));

        jLabel3.setText("Tipo:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, -1));

        jLabel4.setText("Descripción:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, -1, -1));

        txt_DescripcionVehiculo.setColumns(20);
        txt_DescripcionVehiculo.setRows(5);
        jScrollPane1.setViewportView(txt_DescripcionVehiculo);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 140, 310, 190));

        jPanel1.add(cmb_Tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 140, -1));

        table_Vehiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Año", "Tipo", "Color", "Número De Serie", "Código", "Marca", "Versión", "Precio Compra", "Precio Venta", "Fecha Compra", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Vehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_VehiculoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_Vehiculo);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 1120, 220));

        btn_Modificar.setText("Modificar");
        btn_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 110, -1));

        btn_Crear.setText("Crear");
        btn_Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CrearActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Crear, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 110, -1));

        btn_Limpiar.setText("Limpiar");
        btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 350, 110, -1));

        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, 110, -1));

        btn_Consultar.setText("Consultar");
        btn_Consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConsultarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 110, -1));
        jPanel1.add(txt_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 0, -1));
        jPanel1.add(txt_Anio, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 150, -1));

        jLabel5.setText("Año:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));
        jPanel1.add(txt_Color, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 150, -1));

        jLabel6.setText("Color:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));
        jPanel1.add(txt_NumeroDeSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 150, -1));

        jLabel7.setText("Número De Serie");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));
        jPanel1.add(txt_Codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 150, -1));

        jLabel8.setText("Código:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, -1, -1));

        jPanel1.add(cmb_Marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 140, -1));

        jLabel9.setText("Marca:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, -1, -1));
        jPanel1.add(txt_Version, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, 150, -1));

        jLabel10.setText("Versión:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, -1, -1));
        jPanel1.add(txt_PrecioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, 150, -1));

        jLabel11.setText("Precio Compra:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 250, -1, -1));
        jPanel1.add(txt_PrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 130, 150, -1));

        jLabel12.setText("Precio Venta:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, -1, -1));
        jPanel1.add(txt_FechaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 150, -1));

        jLabel13.setText("Fecha Compra:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, -1, -1));

        btn_Inicio.setText("Inicio");
        btn_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InicioActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 40, 70, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void table_VehiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_VehiculoMouseClicked

        try {

            int fila = table_Vehiculo.getSelectedRow();
            int id = Integer.parseInt(table_Vehiculo.getValueAt(fila, 0).toString());

            double precioCompra, precioVenta;

            PreparedStatement ps;
            ResultSet rs;

            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT Id_Vehiculo, Nombre, Anio, Id_Tipo, Color, NumeroSerie, Codigo, Id_Marca, Version, PrecioCompra, PrecioVenta, FechaCompra, Descripcion FROM Vehiculo WHERE Id_Vehiculo = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                this.txt_Id.setText(String.valueOf(id));
                this.txt_NombreVehiculo.setText(rs.getString("Nombre"));
                this.txt_Anio.setText(rs.getString("Anio"));
                this.cmb_Tipo.setSelectedIndex(rs.getInt("Id_Tipo") - 1);
                this.txt_Color.setText(rs.getString("Color"));
                this.txt_NumeroDeSerie.setText(rs.getString("NumeroSerie"));
                this.txt_Codigo.setText(rs.getString("Codigo"));
                this.cmb_Marca.setSelectedIndex(rs.getInt("Id_Marca") - 1);
                this.txt_Version.setText(rs.getString("Version"));
                precioCompra = rs.getDouble("PrecioCompra");
                precioVenta = rs.getDouble("PrecioVenta");
                this.txt_PrecioCompra.setText(String.valueOf(precioCompra));
                this.txt_PrecioVenta.setText(String.valueOf(precioVenta));
                this.txt_FechaCompra.setText(rs.getString("FechaCompra"));
                this.txt_DescripcionVehiculo.setText(rs.getString("Descripcion"));

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error en la tabla de consulta");

        }

    }//GEN-LAST:event_table_VehiculoMouseClicked

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed

        ModificarVehiculo();

    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void btn_CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CrearActionPerformed
        CrearVehiculo();
    }//GEN-LAST:event_btn_CrearActionPerformed

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed

        Limpiar();

    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed

        EliminarVehiculo();

    }//GEN-LAST:event_btn_EliminarActionPerformed

    private void btn_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConsultarActionPerformed

        CargarTabla();

    }//GEN-LAST:event_btn_ConsultarActionPerformed

    private void btn_InicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InicioActionPerformed

        Principal principal = new Principal();
        principal.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_InicioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Consultar;
    private javax.swing.JButton btn_Crear;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_Inicio;
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton btn_Modificar;
    private javax.swing.JComboBox<String> cmb_Marca;
    private javax.swing.JComboBox<String> cmb_Tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_Vehiculo;
    private javax.swing.JTextField txt_Anio;
    private javax.swing.JTextField txt_Codigo;
    private javax.swing.JTextField txt_Color;
    private javax.swing.JTextArea txt_DescripcionVehiculo;
    private javax.swing.JTextField txt_FechaCompra;
    private javax.swing.JTextField txt_Id;
    private javax.swing.JTextField txt_NombreVehiculo;
    private javax.swing.JTextField txt_NumeroDeSerie;
    private javax.swing.JTextField txt_PrecioCompra;
    private javax.swing.JTextField txt_PrecioVenta;
    private javax.swing.JTextField txt_Version;
    // End of variables declaration//GEN-END:variables
}
