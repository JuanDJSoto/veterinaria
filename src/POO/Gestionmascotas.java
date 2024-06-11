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
public class Gestionmascotas extends javax.swing.JFrame {
Connection conn;
Statement sent;
String folio;
String VEX="4";
    /**
     * Creates new form Gestionmascotas
     */
    public Gestionmascotas() {
        initComponents();
        setLocationRelativeTo(null);
        if(Menu.VCE.equals("1")){
            B_Nuevo();
        }if(Menu.VCE.equals("2")){
            B_Editar();
        }if(Menu.VCE.equals("3")){
            B_Eliminar();
        }if(Menu.VCE.equals("4")){
            B_Buscar();
        }
    }
void B_Nuevo(){
    lbltitulo.setText("Nueva mascota");
    btnbuscar.setVisible(false);
    btnfinalizar.setText("Generar registro");
    txtID.setEditable(false);
}

void B_Editar(){
    lbltitulo.setText("Editar mascota");
    btnbuscar.setVisible(true);
    btnfinalizar.setText("Finalizar");
    txtID.setEditable(true);
    txtnombre.setEditable(false);
    txtedad.setEditable(false);
    txttipo.setEditable(false);
    txtraza.setEditable(false);
    txtcliente.setEditable(false);
    txthistorial.setEditable(false);
}

void B_Eliminar(){
    lbltitulo.setText("Eliminar mascota");
    btnbuscar.setVisible(true);
    btnfinalizar.setText("Finalizar");
    txtID.setEditable(true);
    txtnombre.setEditable(false);
    txtedad.setEditable(false);
    txttipo.setEditable(false);
    txtraza.setEditable(false);
    txtcliente.setEditable(false);
    txthistorial.setEditable(false);
}

void B_Buscar(){
    lbltitulo.setText("Buscar mascota");
    btnbuscar.setVisible(true);
    btnfinalizar.setText("Finalizar");
    txtID.setEditable(true);
    txtnombre.setEditable(false);
    txtedad.setEditable(false);
    txttipo.setEditable(false);
    txtraza.setEditable(false);
    txtcliente.setEditable(false);
    txthistorial.setEditable(false);
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
            String sql = "insert into Animales(ID_Animal,Nombre,Edad,Tipo,Raza,Dueno,Historial)"
                    +"values("+"'"+folio+"','"+txtnombre.getText()+"','"+txtedad.getText()+"',"
                    +"'"+txttipo.getText()+"',"
                    +"'"+txtraza.getText()+"',"
                    +"'"+txtcliente.getText()+"',"
                    + "'"+txthistorial.getText()+"')";
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
    
        try{
            conn = Login.getConnection();
            String sql = "UPDATE animales SET Nombre="
                    +"'"+txtnombre.getText()+"',Edad="
                    +"'"+txtnombre.getText()+"',Tipo="
                    +"'"+txttipo.getText()+"',Raza="
                    +"'"+txtraza.getText()+"',Dueno="
                    +"'"+txtcliente.getText()+"',Historial="
                    +"'"+txthistorial.getText()+"' "+"WHERE ID_Animal='"
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
            String sql = "DELETE from animales WHERE ID_Animal='"
                    +txtID.getText()+"'";
            conn = Login.getConnection();
            sent = conn.createStatement();
            int n = sent.executeUpdate(sql);
            
            if(n>0){
                JOptionPane.showMessageDialog(null,"Mascota eliminada correctamente");
                new Menu().setVisible(true);
                this.setVisible(false);
            }
            conn.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
}

void Vexistencia(){
    
        try{
        String sql = "Select Nombre from clientes where Nombre ='"
                +txtcliente.getText()+"'";
        conn = Login.getConnection();
        sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            String Exi=rs.getString(1);
            if(Exi!=""){
                JOptionPane.showMessageDialog(null, "Si existe");
                VEX="1";
            }
            conn.close();
            } catch (SQLException e){
            int seleccion = JOptionPane.showOptionDialog( null,"Es necesario registrar al cliente, ¿Desea hacerlo ahora?",
  "Selector de opciones",JOptionPane.YES_NO_OPTION,
   JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
  new Object[] { "Si", "No" },"Si");
     
 if (seleccion == 0){
            Menu.VCE="1";
            
           new Gestionclientes().setVisible(true);
           this.setVisible(false);
 }
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

        txttipo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtraza = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txthistorial = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtedad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lbltitulo = new javax.swing.JLabel();
        btnfinalizar = new javax.swing.JButton();
        btnmenu = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setText("Raza:");

        jLabel7.setText("Dueño:");

        jLabel8.setText("Historial:");

        jLabel2.setText("ID asignado:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Edad:");

        jLabel5.setText("Tipo de mascota:");

        lbltitulo.setText("Gestion mascotas");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnfinalizar)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttipo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtraza, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtnombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtedad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txthistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbltitulo))
                                .addGap(18, 18, 18)
                                .addComponent(btnbuscar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnmenu)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbltitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtraza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txthistorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnfinalizar)
                    .addComponent(btnmenu))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnfinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfinalizarActionPerformed
        // TODO add your handling code here:
        Vexistencia();
        if(VEX.equals("1")){
            if(Menu.VCE.equals("1")){
                Nueva();
            }if(Menu.VCE.equals("2")){
                Editar();
            }if(Menu.VCE.equals("3")){
            int dialogResult = JOptionPane.showConfirmDialog (null, "¿Está seguro de eliminar este registro?","Alerta",1);
            if(dialogResult == JOptionPane.YES_OPTION){
                Eliminar();
}
            }if(Menu.VCE.equals("4")){
                Editar();
        }
            }
        Menu.VCE="0";
    
    }//GEN-LAST:event_btnfinalizarActionPerformed

    private void btnmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmenuActionPerformed
        // TODO add your handling code here:
        Menu.VCE="0";
        new Menu().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnmenuActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        try{
        String sql = "Select Nombre,Edad,Tipo,Raza,Dueno,Historial from animales where ID_Animal ='"
                +txtID.getText()+"'";
            conn = Login.getConnection();
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            if(rs.getString("Dueno")!=""){
                
            
            while (rs.next()){
                txtnombre.setText(rs.getString("Nombre"));
                txtedad.setText(rs.getString("Edad"));
                txttipo.setText(rs.getString("Tipo"));
                txtraza.setText(rs.getString("Raza"));
                txtcliente.setText(rs.getString("Dueno"));
                txthistorial.setText(rs.getString("Historial"));
            }
            txtID.setEditable(false);
            txtnombre.setEditable(true);
            txtedad.setEditable(true);
            txttipo.setEditable(true);
            txtraza.setEditable(true);
            txtcliente.setEditable(true);
            txthistorial.setEditable(true);
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
            java.util.logging.Logger.getLogger(Gestionmascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestionmascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestionmascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestionmascotas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestionmascotas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnfinalizar;
    private javax.swing.JButton btnmenu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lbltitulo;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtcliente;
    private javax.swing.JTextField txtedad;
    private javax.swing.JTextField txthistorial;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtraza;
    private javax.swing.JTextField txttipo;
    // End of variables declaration//GEN-END:variables
}
