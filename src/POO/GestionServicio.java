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
public class GestionServicio extends javax.swing.JFrame {
Connection conn;
Statement sent;
DefaultTableModel model;
String folio;
String producto="";
String medicamento="";
    /**
     * Creates new form GestionServicio
     */
    public GestionServicio() {
        initComponents();
        Llenarproducto();
        Llenarmedicamento();
        Llenar();
        setLocationRelativeTo(null);
        
        cmbproducto.setEnabled(false);
        cmbmedicamento.setEnabled(false);
        txtID.setText("");
        if(Menu.Nivel.equals("0")){
            btnEliminar.setVisible(false);
        }
    }

    void Llenarproducto(){
        try{
            conn=Login.getConnection();
            String sql ="Select Nombre from Producto";
            sent = conn.createStatement();
            
            ResultSet rs = sent.executeQuery(sql);
            while(rs.next()){
                cmbproducto.addItem(rs.getString(1));
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    void Llenarmedicamento(){
        try{
            conn=Login.getConnection();
            String sql ="Select Nombre from medicamentos";
            sent = conn.createStatement();
            
            ResultSet rs = sent.executeQuery(sql);
            while(rs.next()){
                cmbmedicamento.addItem(rs.getString(1));
               
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    void GS_producto(){
        if(chkproducto.isSelected()){
            producto=cmbproducto.getSelectedItem().toString();
        }else{
            producto="no";
        }
    }
    
    void GS_medicamento(){
        if(chkmedicamento.isSelected()){
            medicamento=cmbmedicamento.getSelectedItem().toString();
        }else{
            medicamento="no";
        }
    }
    
    void Folio(){
    int numero = (int) (Math.random() * 999999999) + 1;
    folio = String.valueOf(numero);
    //JOptionPane.showMessageDialog(null, folio);
}
    
    void Nueva(){
        Folio();
        GS_producto();
        GS_medicamento();
        try{
            conn = Login.getConnection();
            String sql = "insert into Servicio(ID_servicio,Nombre,Productos,Medicamento,Valor)"
                    +"values("+"'"+folio+"','"+txtnombre.getText()+"','"+producto+"',"
                    +"'"+medicamento+"',"
                    + "'"+txtvalor.getText()+"')";
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
    GS_producto();
    GS_medicamento();
        try{
            conn = Login.getConnection();
            String sql = "UPDATE Servicio SET Nombre="
                    +"'"+txtnombre.getText()+"',Productos="
                    +"'"+producto+"',Medicamento="
                    +"'"+medicamento+"',Valor="
                    +"'"+txtvalor.getText()+"' "+"WHERE ID_servicio='"
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
            String sql = "DELETE from Servicio WHERE ID_servicio='"
                    +txtID.getText()+"'";
            conn = Login.getConnection();
            sent = conn.createStatement();
            int n = sent.executeUpdate(sql);
            
            if(n>0){
                JOptionPane.showMessageDialog(null,"Servicio eliminado correctamente");
                
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
            String[] titulos ={"ID","Nombre","Productos","Medicamento","Valor"};
            String sql = "Select * from servicio";
            model = new DefaultTableModel(null, titulos);
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            String[] fila = new String[5];
            while (rs.next()){
                fila[0]=rs.getString("ID_servicio");
                fila[1]=rs.getString("Nombre");
                fila[2]=rs.getString("Productos");
                fila[3]=rs.getString("Medicamento");
                fila[4]=rs.getString("Valor");
                model.addRow(fila);
            }
            tblDB.setModel(model);
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }

void ChecarCampos(){
    if(txtnombre.getText().equals("") || txtvalor.getText().equals("") && txtID.getText().equals("") && btnEliminar.isEnabled() == true && btnModificar.isEnabled() == true){
        btnAgregar.setEnabled(false);
    }
    else{
        btnAgregar.setEnabled(true);
    }
}

    void Limpiar(){
    txtvalor.setText("");
    txtID.setText("");
    txtnombre.setText("");
    chkproducto.setSelected(false);
    chkmedicamento.setSelected(false);
    cmbproducto.setSelectedItem(false);
    cmbmedicamento.setSelectedItem(false);
    cmbproducto.setEnabled(false);
    cmbmedicamento.setEnabled(false);
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
        btnAgregar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDB = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        chkproducto = new javax.swing.JCheckBox();
        cmbproducto = new javax.swing.JComboBox<>();
        chkmedicamento = new javax.swing.JCheckBox();
        cmbmedicamento = new javax.swing.JComboBox<>();
        txtvalor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver al Menu");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Gestion de Servicios");
        lblTitulo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

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

        jLabel1.setText("ID:");

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

        jLabel4.setText("¿Requiere de algún producto o medicamento?");

        chkproducto.setText("Producto");
        chkproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkproductoActionPerformed(evt);
            }
        });

        cmbproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbproductoActionPerformed(evt);
            }
        });

        chkmedicamento.setText("Medicamento");
        chkmedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkmedicamentoActionPerformed(evt);
            }
        });

        cmbmedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbmedicamentoActionPerformed(evt);
            }
        });

        txtvalor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtvalorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtvalorKeyTyped(evt);
            }
        });

        jLabel5.setText("Costo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(327, 327, 327)
                                .addComponent(lblTitulo))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtID)
                                            .addGap(75, 75, 75)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(109, 109, 109)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(194, 194, 194)
                                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(chkproducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chkmedicamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbmedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(27, 27, 27)
                .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264))
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
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkproducto)
                    .addComponent(cmbproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkmedicamento)
                    .addComponent(cmbmedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnAgregar)
                    .addComponent(btnVolver))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkproductoActionPerformed
        // TODO add your handling code here:
        if(chkproducto.isSelected()){
            cmbproducto.setEnabled(true);
        }else{
            cmbproducto.setEnabled(false);
        }
    }//GEN-LAST:event_chkproductoActionPerformed

    private void chkmedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkmedicamentoActionPerformed
        // TODO add your handling code here:
        if(chkmedicamento.isSelected()){
            cmbmedicamento.setEnabled(true);
        }else{
            cmbmedicamento.setEnabled(false);
        }
    }//GEN-LAST:event_chkmedicamentoActionPerformed

    private void cmbproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbproductoActionPerformed
        // TODO add your handling code here:
        if(chkproducto.isSelected()){
            cmbproducto.setEnabled(true);
        }else{
            cmbproducto.setEnabled(false);
        }
    }//GEN-LAST:event_cmbproductoActionPerformed

    private void cmbmedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmedicamentoActionPerformed
        // TODO add your handling code here:
        if(chkmedicamento.isSelected()){
            cmbmedicamento.setEnabled(true);
        }else{
            cmbmedicamento.setEnabled(false);
        }
    }//GEN-LAST:event_cmbmedicamentoActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        new Menu().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void tblDBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDBMouseClicked
        // TODO add your handling code here:
        Limpiar();
        if (evt.getButton()==1){
            int fila = tblDB.getSelectedRow();
            try{
                conn = Login.getConnection();
            //Habilitar();
            String nsql = "Select * from servicio where ID_servicio = '" + tblDB.getValueAt(fila, 0)+"'";
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(nsql);
            
            rs.next();
            if( !"".equals(rs.getString("ID_servicio"))){
            txtID.setText(rs.getString("ID_servicio"));
            txtnombre.setText(rs.getString("Nombre"));
            producto=rs.getString("Productos");
                medicamento=rs.getString("Medicamento");
            txtvalor.setText(rs.getString("Valor"));
                if(!"no".equals(producto)){
                cmbproducto.setEnabled(true);
                cmbproducto.setSelectedItem("Productos");
                chkproducto.setSelected(true);
                cmbproducto.setSelectedItem(producto);
                }
                if(!"no".equals(medicamento)){
                cmbmedicamento.setEnabled(true);
                cmbmedicamento.setSelectedItem("Medicamento");
                chkmedicamento.setSelected(true);
                cmbmedicamento.setSelectedItem(medicamento);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Error no existe");
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
        int dialogResult = JOptionPane.showConfirmDialog (null, "¿Está seguro de eliminar el servicio seleccionado?","Alerta",1);
            if(dialogResult == JOptionPane.YES_OPTION){
                Eliminar();
            }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
        // TODO add your handling code here:
        ChecarCampos();
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txtvalorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvalorKeyReleased
        // TODO add your handling code here:
        ChecarCampos();
    }//GEN-LAST:event_txtvalorKeyReleased

    private void txtvalorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvalorKeyTyped
        // TODO add your handling code here:
        int key = evt.getKeyChar();
        
        boolean numero = key >= 48 && key <= 57;
        
        if(!numero){
            evt.consume();
        }
    }//GEN-LAST:event_txtvalorKeyTyped

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
            java.util.logging.Logger.getLogger(GestionServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionServicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JCheckBox chkmedicamento;
    private javax.swing.JCheckBox chkproducto;
    private javax.swing.JComboBox<String> cmbmedicamento;
    private javax.swing.JComboBox<String> cmbproducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblDB;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtvalor;
    // End of variables declaration//GEN-END:variables
}
