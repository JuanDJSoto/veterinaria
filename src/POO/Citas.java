/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package POO;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
/**
 *
 * @author Juan Soto
 */
public class Citas extends javax.swing.JFrame {
Connection conn;
Statement sent;
DefaultTableModel model;
String folio;
String VEX="4";
String combo;
String precio="";
String IDC="0";
int Ncitas=0;
    /**
     * Creates new form Citas
     */
    public Citas() {
        initComponents();
        setLocationRelativeTo(null);
        lblfolio.setText("");
        Llenarcombo();
        //cmbservicio.addItem("xd");
        Llenar();
        Limpiar();
        if(Menu.Nivel.equals("0")){
            btnEliminar.setVisible(false);
        }
    }
void a(){
    Date date = java.sql.Date.valueOf(dpfecha.getDate());
    JOptionPane.showMessageDialog(null, date);
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

void Llenar(){
        try {
            conn = Login.getConnection();
            String[] titulos ={"Folio","Cliente","Servicio","Dia","Hora","Monto total","Tipo de pago"};
            String sql = "Select * from citas";
            model = new DefaultTableModel(null, titulos);
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            String[] fila = new String[7];
            while (rs.next()){
                fila[0]=rs.getString("Folio");
                fila[1]=rs.getString("Cliente");
                fila[2]=rs.getString("Servicio");
                fila[3]=rs.getString("Dia");
                fila[4]=rs.getString("Hora");
                fila[5]=rs.getString("Pago");
                fila[6]=rs.getString("Tipo_pago");
                model.addRow(fila);
            }
            tblDB.setModel(model);
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }

void ChecarCampos(){
    if(txtcliente.getText().equals("") || cmbservicio.toString().equals("") || cmbpago.toString().equals("") || dpfecha.toString().equals("") || dphora.toString().equals("")&& lblfolio.getText().equals("") && btnEliminar.isEnabled() == true && btnModificar.isEnabled() == true){
        btnAgregar.setEnabled(false);
    }
    else{
        btnAgregar.setEnabled(true);
    }
}

void Limpiar(){
    lblfolio.setText("");
    txtcliente.setText("");
    cmbservicio.setSelectedItem(false);
    cmbpago.setSelectedItem(false);
    dpfecha.setText("");
    dphora.setText("");
    btnModificar.setEnabled(false);
    btnEliminar.setEnabled(false);
    btnAgregar.setEnabled(false);
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
            conn.endRequest();
            conn.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
}

void Cita(){
    G_Cita();
    if(Menu.VCE.equals("1")){
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
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }if(Menu.VCE.equals("3")){
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
                    +"'"+precio+"',"
                    + "'"+mpago+"')";
            sent = conn.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                JOptionPane.showMessageDialog(null,"Datos guardados");
                
            }
            conn.close();
        }catch (SQLException e){
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
                    +"'"+precio+"',Tipo_pago="
                    +"'"+comb+"' "+"WHERE Folio='"
                    +lblfolio.getText()+"'";
            sent = conn.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                JOptionPane.showMessageDialog(null,"Datos guardados");
                
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
                    +lblfolio.getText()+"'";
            conn = Login.getConnection();
            sent = conn.createStatement();
            int n = sent.executeUpdate(sql);
            
            if(n>0){
                JOptionPane.showMessageDialog(null,"Cita eliminada correctamente");
                
            }
            conn.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    Limpiar();
        Llenar();
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
                //JOptionPane.showMessageDialog(null, "Si existe");
                VEX="1";
            }
            conn.endRequest();
            conn.close();
            } catch (SQLException e){
            int seleccion = JOptionPane.showOptionDialog( null,"Es necesario registrar al cliente, ¿Desea hacerlo ahora?",
  "Selector de opciones",JOptionPane.YES_NO_OPTION,
   JOptionPane.QUESTION_MESSAGE,null,// null para icono por defecto.
  new Object[] { "Si", "No" },"Si");
     
 if (seleccion == 0){
            VEX="0";
            
           new GestionCliente().setVisible(true);
           this.setVisible(false);
 }else{
     VEX="0";
 }
        }
        
}

void combo(){
    
        String nsql = "Select Valor from servicio WHERE Nombre= '"
        +cmbservicio.getSelectedItem().toString()+"'";
        try{
            conn = Login.getConnection();
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(nsql);
            precio = rs.getString(1);
            conn.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
            
        }
        //lblmonto.setText(precio);
}

void Deuda(){
        try{
            conn=Login.getConnection();
            String sql ="Select ID_Cliente from Clientes where Nombre = '"
                    +txtcliente.getText()+"'";
            sent = conn.createStatement();
            ResultSet rs = sent.executeQuery(sql);
            IDC = rs.getString(1);
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Folio();
        try{
            conn = Login.getConnection();
            String sql = "insert into Deudas(ID_Deuda,ID_Cliente,Nombre,Monto,Abonado,Restante)"
                    +"values("+"'"+folio+"','"+IDC+"','"+txtcliente.getText()+"',"
                    +"'"+precio+"',"
                    +"'0','"+precio+"')";
            sent = conn.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                JOptionPane.showMessageDialog(null,"Deuda generada");
                
            }
            conn.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        Limpiar();
        Llenar();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dphora = new com.github.lgooddatepicker.components.TimePicker();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbservicio = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cmbpago = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        lbltitulo = new javax.swing.JLabel();
        dpfecha = new com.github.lgooddatepicker.components.DatePicker();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JTextField();
        lblfolio = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDB = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dphora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dphoraMouseExited(evt);
            }
        });

        jLabel3.setText("Servicio:");

        jLabel4.setText("Tipo de pago:");

        cmbservicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbservicioItemStateChanged(evt);
            }
        });
        cmbservicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmbservicioMouseExited(evt);
            }
        });
        cmbservicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbservicioActionPerformed(evt);
            }
        });

        jLabel8.setText("ID:");

        cmbpago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Al contado", "A crédito" }));
        cmbpago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cmbpagoMouseExited(evt);
            }
        });

        jLabel5.setText("Fecha:");

        lbltitulo.setText("Gestión de Citas");

        dpfecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dpfechaMouseExited(evt);
            }
        });

        jLabel2.setText("Cliente:");

        jLabel7.setText("Hora:");

        txtcliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtclienteKeyReleased(evt);
            }
        });

        lblfolio.setText("lblFolio");

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
        tblDB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblDBKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblDB);

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

        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(310, 310, 310)
                                .addComponent(lbltitulo))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(lblfolio)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cmbservicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(cmbpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(dpfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dphora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(330, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbltitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblfolio)
                    .addComponent(jLabel2)
                    .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cmbservicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cmbpago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dpfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(dphora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnAgregar)
                    .addComponent(btnVolver))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbservicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbservicioActionPerformed
        //combo();
    }//GEN-LAST:event_cmbservicioActionPerformed

    private void tblDBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDBMouseClicked
        // TODO add your handling code here:
        if (evt.getButton()==1){
            int fila = tblDB.getSelectedRow();
            try{
                conn = Login.getConnection();
                //Habilitar();
                String sql = "Select * from citas where Folio='" + tblDB.getValueAt(fila, 0)+"'";
                sent = conn.createStatement();
                ResultSet rs = sent.executeQuery(sql);
                if(rs.getString("Folio")!=""){
                    rs.next();
                    lblfolio.setText(rs.getString("Folio"));
                    txtcliente.setText(rs.getString("Cliente"));
                    String a;
                    a=rs.getString("Servicio");
                    cmbservicio.setSelectedItem(a);
                    dpfecha.setText(rs.getString("Dia"));
                    dphora.setText(rs.getString("Hora"));
                    
                    cmbpago.setSelectedItem(rs.getString("Tipo_pago"));
                    //lblmonto.setText(rs.getString("Pago"));
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
        combo = cmbservicio.getSelectedItem().toString();
        Vexistencia();
        if (VEX.equals("1")){
            if(cmbpago.getSelectedItem().toString().equals("A crédito")){
                combo();
                int dialogResult = JOptionPane.showConfirmDialog (null, "La deuda generada por el servicio sería de: $"+precio+". ¿Desea continuar?","Costo",1);
                if(dialogResult == JOptionPane.YES_OPTION){
                Nueva();
                Deuda();
                Limpiar();
                Llenar();
                }
            }if(cmbpago.getSelectedItem().toString().equals("Al contado")){
                combo();
                int dialog2 = JOptionPane.showConfirmDialog (null, "El costo total del servicio sería de: $"+precio+". ¿Desea continuar?","Costo",1);
                if(dialog2 == JOptionPane.YES_OPTION){
                Nueva();
                Limpiar();
                Llenar();
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "No se guardaron los datos");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        new Menu().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        combo = cmbservicio.getSelectedItem().toString();
        if(cmbpago.getSelectedItem().toString().equals("A crédito")){
                combo();
                int dialogResult = JOptionPane.showConfirmDialog (null, "La deuda generada por el servicio sería de: $"+precio+". ¿Desea continuar?","Costo",1);
                if(dialogResult == JOptionPane.YES_OPTION){
                Editar();
                Deuda();
                Limpiar();
                Llenar();
                }
            }if(cmbpago.getSelectedItem().toString().equals("Al contado")){
                combo();
                int dialog2 = JOptionPane.showConfirmDialog (null, "El costo total del servicio sería de: $"+precio+". ¿Desea continuar?","Costo",1);
                if(dialog2 == JOptionPane.YES_OPTION){
                Editar();
                Limpiar();
                Llenar();
                }
            }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog (null, "¿Está seguro de eliminar la cita seleccionada?","Alerta",1);
        if(dialogResult == JOptionPane.YES_OPTION){
            Eliminar();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cmbservicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbservicioItemStateChanged

    }//GEN-LAST:event_cmbservicioItemStateChanged

    private void txtclienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclienteKeyReleased
        // TODO add your handling code here:
        ChecarCampos();
    }//GEN-LAST:event_txtclienteKeyReleased

    private void cmbservicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbservicioMouseExited
        // TODO add your handling code here:
        ChecarCampos();
    }//GEN-LAST:event_cmbservicioMouseExited

    private void cmbpagoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbpagoMouseExited
        // TODO add your handling code here:
        ChecarCampos();
    }//GEN-LAST:event_cmbpagoMouseExited

    private void dpfechaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dpfechaMouseExited
        // TODO add your handling code here:
        //ChecarCampos();
    }//GEN-LAST:event_dpfechaMouseExited

    private void dphoraMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dphoraMouseExited
        // TODO add your handling code here:
        ChecarCampos();
    }//GEN-LAST:event_dphoraMouseExited

    private void tblDBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDBKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDBKeyTyped

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
            java.util.logging.Logger.getLogger(Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Citas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Citas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbpago;
    private javax.swing.JComboBox<String> cmbservicio;
    private com.github.lgooddatepicker.components.DatePicker dpfecha;
    private com.github.lgooddatepicker.components.TimePicker dphora;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblfolio;
    private javax.swing.JLabel lbltitulo;
    private javax.swing.JTable tblDB;
    private javax.swing.JTextField txtcliente;
    // End of variables declaration//GEN-END:variables
}
