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

/**
 *
 * @author Juan Soto
 */
public class Gestionservicios extends javax.swing.JFrame {
Connection conn;
Statement sent;
String folio;
String producto="";
String medicamento="";
    /**
     * Creates new form Gestionservicios
     */
    public Gestionservicios() {
        initComponents();
        Llenarproducto();
        Llenarmedicamento();
        setLocationRelativeTo(null);
        if(Menu.VCE=="1"){
            B_Nuevo();
        }if(Menu.VCE=="2"){
            B_Editar();
        }if(Menu.VCE=="3"){
            B_Eliminar();
        }
        cmbproducto.setEnabled(false);
        cmbmedicamento.setEnabled(false);
    }

    void Llenarproducto(){
        try{
            conn=Login.getConnection();
            String sql ="Select Nombre from producto";
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
    
    void B_Nuevo(){
        txtID.setEditable(false);
        btnbuscar.setVisible(false);
        lbltitulo.setText("Nuevo Servicio");
    }
    
    void B_Editar(){
        txtID.setEditable(true);
        txtnombre.setEditable(false);
        chkproducto.setEnabled(false);
        chkmedicamento.setEnabled(false);
        txtvalor.setEditable(false);
        lbltitulo.setText("Editar Servicio");
    }
    
    void B_Eliminar(){
        txtID.setEditable(true);
        txtnombre.setEditable(false);
        chkproducto.setEnabled(false);
        chkmedicamento.setEnabled(false);
        txtvalor.setEditable(false);
        lbltitulo.setText("Eliminar Servicio");
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
                new Menu().setVisible(true);
                this.setVisible(false);
            }
            conn.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
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
                new Menu().setVisible(true);
                this.setVisible(false);
            }
            conn.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
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
                new Menu().setVisible(true);
                this.setVisible(false);
            }
            conn.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
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

        txtID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        chkproducto = new javax.swing.JCheckBox();
        chkmedicamento = new javax.swing.JCheckBox();
        cmbproducto = new javax.swing.JComboBox<>();
        cmbmedicamento = new javax.swing.JComboBox<>();
        txtvalor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnfinalizar = new javax.swing.JButton();
        btnmenu = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        lbltitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID asignado:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("¿Requiere de algún producto o medicamento?");

        chkproducto.setText("Producto");
        chkproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkproductoActionPerformed(evt);
            }
        });

        chkmedicamento.setText("Medicamento");
        chkmedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkmedicamentoActionPerformed(evt);
            }
        });

        jLabel4.setText("Costo:");

        btnfinalizar.setText("Finalizar");
        btnfinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfinalizarActionPerformed(evt);
            }
        });

        btnmenu.setText("Menú Principal");
        btnmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmenuActionPerformed(evt);
            }
        });

        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        lbltitulo.setText("Gestión de servicios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(48, 48, 48)
                                .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chkproducto)
                                    .addComponent(jLabel4))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chkmedicamento)
                                    .addComponent(cmbmedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbltitulo)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(30, 30, 30)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(31, 31, 31)
                                .addComponent(btnbuscar)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnfinalizar)
                        .addGap(126, 126, 126)
                        .addComponent(btnmenu)
                        .addGap(102, 102, 102))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbltitulo)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnbuscar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkproducto)
                    .addComponent(chkmedicamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbmedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnfinalizar)
                    .addComponent(btnmenu))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnfinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfinalizarActionPerformed
        // TODO add your handling code here:
        if(Menu.VCE.equals("1")){
                Nueva();
            }if(Menu.VCE.equals("2")){
                Editar();
            }if(Menu.VCE.equals("3")){
            int dialogResult = JOptionPane.showConfirmDialog (null, "¿Está seguro de eliminar este registro?","Alerta",1);
            if(dialogResult == JOptionPane.YES_OPTION){
                Eliminar();
}
            }
        Menu.VCE="0";
    }//GEN-LAST:event_btnfinalizarActionPerformed

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

    private void btnmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmenuActionPerformed
        // TODO add your handling code here:
        Menu.VCE="0";
        new Menu().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnmenuActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        try{
        String sql = "Select Nombre,Productos,Medicamento,Valor from Servicio where ID_servicio ='"
                +txtID.getText()+"'";
            conn = Login.getConnection();
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            if(rs.getString("Nombre")!=""){
                
            
            while (rs.next()){
                txtnombre.setText(rs.getString("Nombre"));
                txtvalor.setText(rs.getString("Valor"));
                producto=rs.getString("Productos");
                medicamento=rs.getString("Medicamento");
                if(producto!=""){
                cmbproducto.setEnabled(true);
                cmbproducto.setSelectedItem(rs.getString(producto));
                chkproducto.setSelected(true);
                }
                if(medicamento!=""){
                cmbmedicamento.setEnabled(true);
                cmbmedicamento.setSelectedItem(medicamento);
                chkmedicamento.setSelected(true);
                }
            }
            txtID.setEditable(false);
            txtnombre.setEditable(true);
            txtvalor.setEditable(true);
            }else{
                JOptionPane.showMessageDialog(null, "No existe el folio");
            }
            conn.close();
            } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error"+e.getMessage());
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

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
            java.util.logging.Logger.getLogger(Gestionservicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestionservicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestionservicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestionservicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestionservicios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnfinalizar;
    private javax.swing.JButton btnmenu;
    private javax.swing.JCheckBox chkmedicamento;
    private javax.swing.JCheckBox chkproducto;
    private javax.swing.JComboBox<String> cmbmedicamento;
    private javax.swing.JComboBox<String> cmbproducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbltitulo;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtvalor;
    // End of variables declaration//GEN-END:variables
}
