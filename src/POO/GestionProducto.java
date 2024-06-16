/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package POO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author gordi
 */
public class GestionProducto extends javax.swing.JFrame {
Connection conn;
Statement sent;
String folio;
DefaultTableModel model;
    /**
     * Creates new form GestionProducto
     */
    public GestionProducto() {
        initComponents();
        if(Menu.Nivel.equals("0")){
        btnEliminar.setVisible(false);
    }
        txtID.setText("");
        setLocationRelativeTo(null);
        Llenar();
    }

    void Folio(){
    int numero = (int) (Math.random() * 999999999) + 1;
    folio = String.valueOf(numero);
    //JOptionPane.showMessageDialog(null, folio);
}

void Nueva(){
        Folio();
        try{
            conn = Login.getConnection();
            String sql = "insert into Producto (ID_producto,Nombre,Tipo,Valor_venta,Valor_compra,Existencia)"
                    +"values('"+folio+"',"
                    +"'"+txtnombre.getText()+"',"
                    +"'"+txttipo.getText()+"',"
                    +"'"+txtventa.getText()+"',"
                    +"'"+txtcompra.getText()+"',"
                    +"'"+txtstock.getText()+"')";
            sent = conn.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                JOptionPane.showMessageDialog(null,"Datos guardados");
                
            }
            conn.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        Llenar();
        Limpiar();
}

void Editar(){
        try{
            conn = Login.getConnection();
            String sql = "UPDATE Producto SET Nombre="
                    +"'"+txtnombre.getText()+"',Tipo="
                    +"'"+txttipo.getText()+"',Valor_venta="
                    +"'"+txtventa.getText()+"',Valor_compra="
                    +"'"+txtcompra.getText()+"',Existencia="
                    +"'"+txtstock.getText()+"' WHERE ID_producto='"
                    +txtID.getText()+"'";
            sent = conn.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                JOptionPane.showMessageDialog(null,"Datos guardados");
                
            }
            conn.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        Llenar();
        Limpiar();
}

void Eliminar(){
    try{
            String sql = "DELETE from producto WHERE ID_producto='"
                    +txtID.getText()+"'";
            conn = Login.getConnection();
            sent = conn.createStatement();
            int n = sent.executeUpdate(sql);
            
            if(n>0){
                JOptionPane.showMessageDialog(null,"Producto eliminado correctamente");
            }
            conn.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    Llenar();
    Limpiar();
}

void Llenar(){
        try {
            conn = Login.getConnection();
            String[] titulos ={"ID","Nombre","Tipo","Valor de venta","Valor de compra","Stock"};
            String sql = "Select * from producto";
            model = new DefaultTableModel(null, titulos);
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            String[] fila = new String[6];
            while (rs.next()){
                fila[0]=rs.getString("ID_producto");
                fila[1]=rs.getString("Nombre");
                fila[2]=rs.getString("Tipo");
                fila[3]=rs.getString("Valor_venta");
                fila[4]=rs.getString("Valor_compra");
                fila[5]=rs.getString("Existencia");
                
                model.addRow(fila);
            }
            tblDB.setModel(model);
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }

void ChecarCampos(){
    if(txtnombre.getText().equals("") || txttipo.getText().equals("") || txtcompra.getText().equals("") || txtventa.getText().equals("") || txtstock.getText().equals("") && txtID.getText().equals("") && btnEliminar.isEnabled() == true && btnModificar.isEnabled() == true){
        btnAgregar.setEnabled(false);
    }
    else{
        btnAgregar.setEnabled(true);
    }
}

void Limpiar(){
    txtcompra.setText("");
    txtventa.setText("");
    txtstock.setText("");
    txttipo.setText("");
    txtID.setText("");
    txtnombre.setText("");
    btnModificar.setEnabled(false);
    btnEliminar.setEnabled(false);
    btnAgregar.setEnabled(false);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEliminar = new javax.swing.JButton();
        txttipo = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        txtcompra = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDB = new javax.swing.JTable();
        txtventa = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtstock = new javax.swing.JTextField();
        txtID = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txttipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttipoKeyReleased(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel4.setText("Valor de Compra Unitario:");

        btnVolver.setText("Volver al Menu");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        txtcompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcompraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcompraKeyTyped(evt);
            }
        });

        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Gestion de Productos");
        lblTitulo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel5.setText("Valor de Venta Unitario:");

        tblDB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDBMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDB);

        txtventa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtventaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtventaKeyTyped(evt);
            }
        });

        jLabel6.setText("Stock:");

        jLabel1.setText("ID:");

        txtstock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtstockKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstockKeyTyped(evt);
            }
        });

        txtID.setText("labelID");

        jLabel2.setText("Nombre:");

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtventa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtID)
                                .addGap(75, 75, 75)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttipo))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(283, 283, 283))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID)
                    .addComponent(jLabel2)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txttipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtcompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnAgregar)
                    .addComponent(btnVolver))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDBMouseClicked
        // TODO add your handling code here:
        if (evt.getButton()==1){
            int fila = tblDB.getSelectedRow();
            try{
                conn = Login.getConnection();
            //Habilitar();
            String sql = "Select * from producto where ID_producto='" + tblDB.getValueAt(fila, 0)+"'";
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            if(rs.getString("Nombre")!=""){
            rs.next();
            txtID.setText(rs.getString("ID_producto"));
            txtnombre.setText(rs.getString("Nombre"));
            txttipo.setText(rs.getString("Tipo"));
            txtventa.setText(rs.getString("Valor_venta"));
            txtcompra.setText(rs.getString("Valor_compra"));
            txtstock.setText(rs.getString("Existencia"));
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
            btnEliminar.setEnabled(true);
            btnModificar.setEnabled(true);
        }
    }//GEN-LAST:event_tblDBMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        Nueva();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        Editar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog (null, "¿Está seguro de eliminar el medicamento seleccionado?","Alerta",1);
            if(dialogResult == JOptionPane.YES_OPTION){
                Eliminar();
            }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        new Menu().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
        // TODO add your handling code here:
        ChecarCampos();
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txttipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttipoKeyReleased
        // TODO add your handling code here:
        ChecarCampos();
    }//GEN-LAST:event_txttipoKeyReleased

    private void txtcompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcompraKeyReleased
        // TODO add your handling code here:
        ChecarCampos();
    }//GEN-LAST:event_txtcompraKeyReleased

    private void txtventaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtventaKeyReleased
        // TODO add your handling code here:
        ChecarCampos();
    }//GEN-LAST:event_txtventaKeyReleased

    private void txtstockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockKeyReleased
        // TODO add your handling code here:
        ChecarCampos();
    }//GEN-LAST:event_txtstockKeyReleased

    private void txtcompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcompraKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        
        boolean numero = key >= 48 && key <= 57;
        
        if(!numero){
            evt.consume();
        }
    }//GEN-LAST:event_txtcompraKeyTyped

    private void txtventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtventaKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        
        boolean numero = key >= 48 && key <= 57;
        
        if(!numero){
            evt.consume();
        }
    }//GEN-LAST:event_txtventaKeyTyped

    private void txtstockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        
        boolean numero = key >= 48 && key <= 57;
        
        if(!numero){
            evt.consume();
        }
    }//GEN-LAST:event_txtstockKeyTyped

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
            java.util.logging.Logger.getLogger(GestionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblDB;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtcompra;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtstock;
    private javax.swing.JTextField txttipo;
    private javax.swing.JTextField txtventa;
    // End of variables declaration//GEN-END:variables
}
