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
public class Gestionusuarios extends javax.swing.JFrame {
Connection conn;
Statement sent;
String folio;
String Nivel="";
    /**
     * Creates new form Gestionusuarios
     */
    public Gestionusuarios() {
        initComponents();
        setLocationRelativeTo(null);
        if(Menu.VCE.equals("1")){
            B_nuevo();
        }if(Menu.VCE.equals("2")){
            B_editar();
        }if(Menu.VCE.equals("3")){
            B_eliminar();
        }if(Menu.VCE.equals("4")){
            B_buscar();
        }
    }

void B_nuevo(){
        btnbuscar.setVisible(false);
        txtID.setEditable(false);
        lbltitulo.setText("Nuevo usuario");
}
    
void B_editar(){
        lbltitulo.setText("Editar usuario");
        txtID.setEditable(true);
        txtnombre.setEditable(false);
        txtpaterno.setEditable(false);
        txtmaterno.setEditable(false);
        txtedad.setEditable(false);
        txttelefono.setEditable(false);
        txtdireccion.setEditable(false);
        btnbuscar.setVisible(true);
        jrbusuario.setEnabled(false);
        jrbadmin.setEnabled(false);
        jrbusuario.setSelected(true);
}

void B_eliminar(){
        lbltitulo.setText("Eliminar usuario");
        txtID.setEditable(true);
        txtnombre.setEditable(false);
        txtpaterno.setEditable(false);
        txtmaterno.setEditable(false);
        txtedad.setEditable(false);
        txttelefono.setEditable(false);
        txtdireccion.setEditable(false);
        btnbuscar.setVisible(true);
        jrbusuario.setEnabled(false);
        jrbadmin.setEnabled(false);
        jrbusuario.setSelected(true);
}    

void B_buscar(){
        lbltitulo.setText("Buscar usuario");
        txtID.setEditable(true);
        txtnombre.setEditable(false);
        txtpaterno.setEditable(false);
        txtmaterno.setEditable(false);
        txtedad.setEditable(false);
        txttelefono.setEditable(false);
        txtdireccion.setEditable(false);
        btnbuscar.setVisible(true);
        jrbusuario.setEnabled(false);
        jrbadmin.setEnabled(false);
        jrbusuario.setSelected(true);
}

void Folio(){
    int numero = (int) (Math.random() * 999999999) + 1;
    folio = String.valueOf(numero);
    //JOptionPane.showMessageDialog(null, folio);
}

void Rango(){
    if(jrbusuario.isSelected()){
        Nivel = "0";
    }else if(jrbadmin.isSelected()){
        Nivel = "1";
    }
}

void Nueva(){
        Folio();
        Rango();
        try{
            conn = Login.getConnection();
            String sql = "insert into usuario(ID_Usuario,Nombre,Apellido_Paterno,Apellido_Materno,Edad,Numero,Direccion,Nivel_Administrativo,Password)"
                    +"values('"+folio+"',"
                    +"'"+txtnombre.getText()+"',"
                    +"'"+txtpaterno.getText()+"',"
                    +"'"+txtmaterno.getText()+"',"
                    +"'"+txtedad.getText()+"',"
                    +"'"+txttelefono.getText()+"',"
                    +"'"+txtdireccion.getText()+"',"
                    +"'"+Nivel+"',"
                    +"'"+txtpassword.getText()+"')";
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
    Rango();
        try{
            conn = Login.getConnection();
            String sql = "UPDATE usuario SET Nombre="
                    +"'"+txtnombre.getText()+"',Apellido_Paterno="
                    +"'"+txtpaterno.getText()+"',Apellido_Materno="
                    +"'"+txtmaterno.getText()+"',Edad="
                    +"'"+txtedad.getText()+"',Numero="
                    +"'"+txttelefono.getText()+"',Direccion="
                    +"'"+txtdireccion.getText()+"',Nivel_Administrativo="
                    +"'"+Nivel+"',Password="
                    +"'"+txtpassword.getText()+"' "+"WHERE ID_usuario='"
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
            String sql = "DELETE from usuario WHERE ID_usuario='"
                    +txtID.getText()+"'";
            conn = Login.getConnection();
            sent = conn.createStatement();
            int n = sent.executeUpdate(sql);
            
            if(n>0){
                JOptionPane.showMessageDialog(null,"Usuario eliminado correctamente");
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

        btg1 = new javax.swing.ButtonGroup();
        lbltitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtpaterno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtmaterno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtedad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnfinalizar = new javax.swing.JButton();
        btnmenu = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        jrbadmin = new javax.swing.JRadioButton();
        jrbusuario = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbltitulo.setText("Gestión de usuarios");

        jLabel2.setText("ID asignado:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Apellido Paterno:");

        jLabel5.setText("Apellido Materno:");

        jLabel6.setText("Edad:");

        jLabel7.setText("Teléfono:");

        jLabel8.setText("Direccción:");

        jLabel9.setText("Nivel administrativo:");

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

        btg1.add(jrbadmin);
        jrbadmin.setText("Administrador");

        btg1.add(jrbusuario);
        jrbusuario.setText("Usuario");

        jLabel10.setText("Contraseña:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(txtpaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtmaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(btnbuscar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jrbusuario)
                        .addGap(18, 18, 18)
                        .addComponent(jrbadmin)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(lbltitulo)
                .addContainerGap(191, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btnfinalizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnmenu)
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbltitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtpaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtmaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jrbadmin)
                    .addComponent(jrbusuario))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnfinalizar)
                    .addComponent(btnmenu))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmenuActionPerformed
        // TODO add your handling code here:
        Menu.VCE="0";
        new Menu().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnmenuActionPerformed

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
            }if(Menu.VCE.equals("4")){
                Editar();
        }
    }//GEN-LAST:event_btnfinalizarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        try{
        String sql = "Select Nombre,Apellido_Paterno,Apellido_Materno,Edad,Numero,Direccion,Nivel_Administrativo,Password from usuario where ID_usuario ='"
                +txtID.getText()+"'";
            conn = Login.getConnection();
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            if(rs.getString("Nombre")!=""){
                
            
            while (rs.next()){
                txtnombre.setText(rs.getString("Nombre"));
                txtpaterno.setText(rs.getString("Apellido_Paterno"));
                txtmaterno.setText(rs.getString("Apellido_Materno"));
                txtedad.setText(rs.getString("Edad"));
                txttelefono.setText(rs.getString("Numero"));
                txtdireccion.setText(rs.getString("Direccion"));
                Nivel = rs.getString("Nivel_Administrativo");
                txtpassword.setText(rs.getString("Password"));
            }
            txtID.setEditable(false);
            txtnombre.setEditable(true);
            txtpaterno.setEditable(true);
            txtmaterno.setEditable(true);
            txtedad.setEditable(true);
            txttelefono.setEditable(true);
            txtdireccion.setEditable(true);
            txtpassword.setEditable(true);
            jrbusuario.setEnabled(true);
            jrbadmin.setEnabled(true);
            if(Nivel.equals("0")){
                jrbusuario.setSelected(true);
                jrbadmin.setSelected(false);
            }else if(Nivel.equals("1")){
                jrbadmin.setSelected(true);
                jrbusuario.setSelected(false);
            }
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
            java.util.logging.Logger.getLogger(Gestionusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestionusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestionusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestionusuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestionusuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btg1;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnfinalizar;
    private javax.swing.JButton btnmenu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jrbadmin;
    private javax.swing.JRadioButton jrbusuario;
    private javax.swing.JLabel lbltitulo;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtedad;
    private javax.swing.JTextField txtmaterno;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtpassword;
    private javax.swing.JTextField txtpaterno;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
