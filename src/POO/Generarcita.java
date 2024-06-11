package POO;


import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Juan Soto
 */
public class Generarcita extends javax.swing.JFrame {
Connection conn;
Statement sent;
String folio;
String VEX="4";
String combo;
int Ncitas=0;
    /**
     * Creates new form Generarcita
     */
    public Generarcita() {
        initComponents();
        
        Llenarcombo();
        setLocationRelativeTo(null);
        lblmonto.setText("");
        if(Menu.VCE.equals("1")){
            B_Nuevo();
        }if(Menu.VCE.equals("2")){
            B_Editar();
        }if(Menu.VCE.equals("3")){
            B_Eliminar();
        }
    }
    
void B_Nuevo(){
    lbltitulo.setText("Nueva cita");
    btnbuscar.setVisible(false);
    btnfinalizar.setText("Generar Cita");
    txtfolio.setEditable(false);
}

void B_Editar(){
    lbltitulo.setText("Editar cita");
    btnfinalizar.setText("Finalizar");
    txtcliente.setEditable(false);
    cmbservicio.setEnabled(false);
    cmbpago.setEnabled(false);
    dpfecha.setEnabled(false);
    dphora.setEnabled(false);
}
    
void B_Eliminar(){
    lbltitulo.setText("Eliminar cita");
    btnfinalizar.setText("Finalizar");
    txtcliente.setEditable(false);
    cmbservicio.setEnabled(false);
    cmbpago.setEnabled(false);
    dpfecha.setEnabled(false);
    dphora.setEnabled(false);
}

void Llenarcombo(){
        try{
            conn=Login.getConnection();
            String sql ="Select Nombre from servicio";
            sent = conn.createStatement();
            
            ResultSet rs = sent.executeQuery(sql);
            while(rs.next()){
                cmbservicio.addItem(rs.getString(1));
               
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

void Folio(){
    int numero = (int) (Math.random() * 999999999) + 1;
    folio = String.valueOf(numero);
    //JOptionPane.showMessageDialog(null, folio);
}

void G_Cita(){
    String nsql = "SELECT Citas from Clientes WHERE Nombre='"
                +txtcliente.getText()+"'";
        try{
            conn = Login.getConnection();
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(nsql);
            rs.next();
            Ncitas = rs.getInt(1);
            conn.close();
        }catch (Exception ex){
            java.util.logging.Logger.getLogger(Generarcita.class.getName()).log(Level.SEVERE, null, ex);
        }
}

void Cita(){
    
    if(Menu.VCE.equals("1")){
        G_Cita();
        try{
            Ncitas=Ncitas+1;
            conn = Login.getConnection();
            String sql = "UPDATE Clientes SET Citas="
                    +"'"+Ncitas+"' WHERE Nombre='"
                    +txtcliente.getText()+"'";
            sent = conn.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){

            }
            conn.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }if(Menu.VCE.equals("3")){
        G_Cita();
        try{
            Ncitas=Ncitas-1;
            conn = Login.getConnection();
            String sql = "UPDATE Clientes SET Citas="
                    +"'"+Ncitas+"' WHERE Nombre='"
                    +txtcliente.getText()+"'";
            sent = conn.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){

            }
            conn.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}

void Nueva(){
        Folio();
        Cita();
        String combo = cmbservicio.getSelectedItem().toString();
        String mpago = cmbpago.getSelectedItem().toString();
        try{
            conn = Login.getConnection();
            String sql = "insert into Citas(Folio,Cliente,Servicio,Dia,Hora,Pago,Tipo_pago)"
                    +"values("+"'"+folio+"','"+txtcliente.getText()+"','"+combo+"',"
                    +"'"+dpfecha.getText()+"',"
                    +"'"+dphora.getTimeStringOrEmptyString()+"',"
                    +"'"+lblmonto.getText()+"',"
                    + "'"+mpago+"')";
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
    
    String comb = cmbpago.getSelectedItem().toString();
        try{
            conn = Login.getConnection();
            String sql = "UPDATE citas SET Cliente="
                    +"'"+txtcliente.getText()+"',Servicio="
                    +"'"+combo+"',Dia="
                    +"'"+dpfecha.getText()+"',Hora="
                    +"'"+dphora.getTimeStringOrEmptyString()+"',Pago="
                    +"'"+lblmonto.getText()+"',Tipo_pago="
                    +"'"+comb+"' "+"WHERE Folio='"
                    +txtfolio.getText()+"'";
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
    Cita();
    try{
            String sql = "DELETE from citas WHERE Folio='"
                    +txtfolio.getText()+"'";
            conn = Login.getConnection();
            sent = conn.createStatement();
            int n = sent.executeUpdate(sql);
            
            if(n>0){
                JOptionPane.showMessageDialog(null,"Cita eliminada correctamente");
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

        btnfinalizar = new javax.swing.JButton();
        lbltitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbservicio = new javax.swing.JComboBox<>();
        cmbpago = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        label$ = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        dpfecha = new com.github.lgooddatepicker.components.DatePicker();
        jLabel7 = new javax.swing.JLabel();
        dphora = new com.github.lgooddatepicker.components.TimePicker();
        lblmonto = new javax.swing.JLabel();
        btnbuscar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtfolio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnfinalizar.setText("Generar cita");
        btnfinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfinalizarActionPerformed(evt);
            }
        });

        lbltitulo.setText("Generar Cita");

        jLabel2.setText("Cliente:");

        jLabel3.setText("Servicio:");

        jLabel4.setText("Tipo de pago:");

        cmbservicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbservicioActionPerformed(evt);
            }
        });

        cmbpago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Al contado", "A crédito" }));

        jLabel5.setText("Fecha:");

        jLabel6.setText("Monto total: ");

        label$.setText("$");

        jButton2.setText("Menú principal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Hora:");

        lblmonto.setText("100");

        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        jLabel8.setText("Folio:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(lbltitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnfinalizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(62, 62, 62))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbservicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label$)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dphora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dpfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtfolio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44)
                        .addComponent(btnbuscar)))
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbltitulo)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtfolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbservicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dpfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(dphora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(label$)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnfinalizar)
                    .addComponent(jButton2))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnfinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfinalizarActionPerformed
        Vexistencia();
        if(VEX.equals("1")){
            if(Menu.VCE.equals("1")){
            Nueva();
            }if(Menu.VCE.equals("2")){
            combo = cmbservicio.getSelectedItem().toString();
            Editar();
            }if(Menu.VCE.equals("3")){
            combo = cmbservicio.getSelectedItem().toString();
            int dialogResult = JOptionPane.showConfirmDialog (null, "¿Está seguro de eliminar la cita seleccionada?","Alerta",1);
            if(dialogResult == JOptionPane.YES_OPTION){
                Eliminar();
}
            }
        }if(VEX=="4"){
            JOptionPane.showMessageDialog(null, "No pasa");
        }
        Menu.VCE="0";
    }//GEN-LAST:event_btnfinalizarActionPerformed

    private void cmbservicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbservicioActionPerformed

        String precio="";
        String nsql = "SELECT Valor from servicio WHERE Nombre='"
                +this.cmbservicio.getSelectedItem().toString()+"'";
        try{
            conn = Login.getConnection();
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(nsql);
            rs.next();
            precio = rs.getString(1);
            conn.close();
        }catch (Exception ex){
            java.util.logging.Logger.getLogger(Generarcita.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblmonto.setText(precio);
    }//GEN-LAST:event_cmbservicioActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new Menu().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        try{
        String sql = "Select Cliente,Dia,Hora,Pago from citas where Folio ='"
                +txtfolio.getText()+"'";
            conn = Login.getConnection();
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            if(rs.getString("cliente")!=""){
                
            
            while (rs.next()){
                txtcliente.setText(rs.getString("Cliente"));
                dpfecha.setText(rs.getString("Dia"));
                dphora.setText(rs.getString("Hora"));
                lblmonto.setText(rs.getString("Pago"));
            }
            txtfolio.setEditable(false);
            txtcliente.setEditable(true);
            cmbservicio.setEnabled(true);
            cmbpago.setEnabled(true);
            dpfecha.setEnabled(true);
            dphora.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }
            conn.close();
            } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error no existe el folio, intente nuevamente");
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
            java.util.logging.Logger.getLogger(Generarcita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Generarcita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Generarcita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Generarcita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Generarcita().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnfinalizar;
    private javax.swing.JComboBox<String> cmbpago;
    private javax.swing.JComboBox<String> cmbservicio;
    private com.github.lgooddatepicker.components.DatePicker dpfecha;
    private com.github.lgooddatepicker.components.TimePicker dphora;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel label$;
    private javax.swing.JLabel lblmonto;
    private javax.swing.JLabel lbltitulo;
    private javax.swing.JTextField txtcliente;
    private javax.swing.JTextField txtfolio;
    // End of variables declaration//GEN-END:variables
}
