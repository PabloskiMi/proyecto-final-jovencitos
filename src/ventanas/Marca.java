/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases.Conexion;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author pablo
 */
public class Marca extends javax.swing.JFrame {

    /**
     * Creates new form Tipo
     */
    public Marca() {

        initComponents();
        this.setSize(new Dimension(685, 620));
        
        setIconImage(getIcoImage());
        
        this.setLocationRelativeTo(null);

    }
    
    
    public Image getIcoImage(){
        Image retVaIue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/logo.png"));
        return retVaIue;
    }
    
    public void EliminarMarca(){
        
        try{
        
        Connection con = Conexion.getConexion();
        PreparedStatement ps = con.prepareStatement("DELETE FROM Marca WHERE Id_Marca = ?");
        
        ps.setInt(1, Integer.parseInt(this.txt_Id.getText()));
        
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Registro eliminado");
        
        Limpiar();
        CargarTabla();
        
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Error en el registro");
            
        }
        
    }
    
    public void ModificarMarca(){
        
        try{
        
        Connection con = Conexion.getConexion();
        PreparedStatement ps = con.prepareStatement("UPDATE Marca SET Nombre = ?, Pais = ?, Descripcion = ? WHERE Id_Marca = ?");
        
        ps.setString(1, this.txt_NombreMarca.getText());
        ps.setString(2, this.cmb_Pais.getSelectedItem().toString());
        ps.setString(3, this.txt_DescripcionMarca.getText());
        ps.setInt(4, Integer.parseInt(this.txt_Id.getText()));
        
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Registro modificado");
        
        Limpiar();
        CargarTabla();
        
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Error en el registro");
            
        }
        
    }
    
    public void CrearMarca(){
        
        try{
        
        Connection con = Conexion.getConexion();
        PreparedStatement ps = con.prepareStatement("INSERT INTO Marca (Nombre, Pais, Descripcion) VALUES (?,?,?)");
        ps.setString(1, this.txt_NombreMarca.getText());
        ps.setString(2, this.cmb_Pais.getSelectedItem().toString());
        ps.setString(3, this.txt_DescripcionMarca.getText());
        
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Registro guardado");
        
        Limpiar();
        CargarTabla();
        
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Error en el registro");
            
        }
        
        
    }
    
    public void CargarTabla(){
        
        DefaultTableModel modeloTabla = (DefaultTableModel)  table_Marca.getModel();
        modeloTabla.setRowCount(0);
        
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;
        
        int[] anchos = {10, 10, 10, 200};
        for (int i = 0; i < table_Marca.getColumnCount(); i++) {
            
            table_Marca.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
            
        }
        
        try{
            
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT Id_Marca, Nombre, Pais, Descripcion FROM Marca");
            
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
            
         }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Error en la tabla");
            
        }
        
    }
    
    public void Limpiar(){
        
        this.txt_Id.setText("");
        this.txt_NombreMarca.setText("");
        this.txt_DescripcionMarca.setText("");
        this.cmb_Pais.setSelectedIndex(0);
        
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
        txt_NombreMarca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_DescripcionMarca = new javax.swing.JTextArea();
        cmb_Pais = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_Marca = new javax.swing.JTable();
        btn_Modificar = new javax.swing.JButton();
        btn_Crear = new javax.swing.JButton();
        btn_Limpiar = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_Consultar = new javax.swing.JButton();
        txt_Id = new javax.swing.JTextField();
        btn_Inicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(630, 430));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(650, 550));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Marca");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));
        jPanel1.add(txt_NombreMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 150, -1));

        jLabel3.setText("País:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jLabel4.setText("Descripción:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, -1));

        txt_DescripcionMarca.setColumns(20);
        txt_DescripcionMarca.setRows(5);
        jScrollPane1.setViewportView(txt_DescripcionMarca);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 230, 120));

        cmb_Pais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USA", "Italia", "Japón", "Alemania" }));
        jPanel1.add(cmb_Pais, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 140, -1));

        table_Marca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "País", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Marca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_MarcaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_Marca);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, 220));

        btn_Modificar.setText("Modificar");
        btn_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 410, 110, -1));

        btn_Crear.setText("Crear");
        btn_Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CrearActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Crear, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 370, 110, -1));

        btn_Limpiar.setText("Limpiar");
        btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 520, 110, -1));

        btn_Eliminar.setText("Eliminar");
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 450, 110, -1));

        btn_Consultar.setText("Consultar");
        btn_Consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ConsultarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Consultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 310, 110, -1));
        jPanel1.add(txt_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 0, -1));

        btn_Inicio.setText("Inicio");
        btn_Inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InicioActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 70, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CrearActionPerformed
        CrearMarca();
    }//GEN-LAST:event_btn_CrearActionPerformed

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        
        Limpiar();
        
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void table_MarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_MarcaMouseClicked
        
        try{
            
            int fila = table_Marca.getSelectedRow();
            int id = Integer.parseInt(table_Marca.getValueAt(fila, 0).toString());
            
            PreparedStatement ps;
            ResultSet rs;
            
                        Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT Id_Marca, Nombre, Pais, Descripcion FROM Marca WHERE Id_Marca = ?");
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
          

            while (rs.next()) {
                this.txt_Id.setText(String.valueOf(id));
                this.txt_NombreMarca.setText(rs.getString("Nombre"));
                if (rs.getString("Pais").equals("USA")) {
                    
                    this.cmb_Pais.setSelectedItem("USA");
                    
                }else if(rs.getString("Pais").equals("Japón")){
                    
                    this.cmb_Pais.setSelectedItem("Japón");
                    
                }
                else if(rs.getString("Pais").equals("Italia")){
                    
                    this.cmb_Pais.setSelectedItem("Italia");
                    
                }
                else if(rs.getString("Pais").equals("Alemania")){
                    
                    this.cmb_Pais.setSelectedItem("Alemania");
                
                }
                
                this.txt_DescripcionMarca.setText(rs.getString("Descripcion"));
                
            }
            
        }catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Error en la tabla de consulta");
            
        }
        
    }//GEN-LAST:event_table_MarcaMouseClicked

    private void btn_ConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ConsultarActionPerformed
        
        CargarTabla();
        
    }//GEN-LAST:event_btn_ConsultarActionPerformed

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
        
        ModificarMarca();
        
    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed
        
        EliminarMarca();
        
    }//GEN-LAST:event_btn_EliminarActionPerformed

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
            java.util.logging.Logger.getLogger(Marca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Marca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Marca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Marca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Marca().setVisible(true);
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
    private javax.swing.JComboBox<String> cmb_Pais;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_Marca;
    private javax.swing.JTextArea txt_DescripcionMarca;
    private javax.swing.JTextField txt_Id;
    private javax.swing.JTextField txt_NombreMarca;
    // End of variables declaration//GEN-END:variables
}
