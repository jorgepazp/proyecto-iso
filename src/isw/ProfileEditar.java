/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isw;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;



/**
 *
 * @author pazjo
 */
public class ProfileEditar extends javax.swing.JFrame {

    /**
     * Creates new form ProfileWdw
     */
    public String rut;      // RUT
    public String nombre;   //Nombre Completo
    public String dir;      //Direccion
    public String nac;      // Nacimiento
    public String nat;      //Nacionalidad 
    public String cor;      //email
    public String num;      // numero
    public String uuid;
    Color grid= new Color(60,60,60);
           Color negro2= new Color(23,23,23);
           Color negro1= new Color(43,43,43);
           Color negro = new Color(27,27,27);
           Color blanco = new Color(196,196,196);
    public ProfileEditar() throws IOException {
        initComponents();
        setUI();
        setFavicon();
        
    }

    private StreamBDD sbdd;
    public void setConexion(StreamBDD con){
        sbdd = con;
    }       
    
    public void setCombos() throws SQLException{
      ArrayList<String> listaUuid =  sbdd.getListaUUIDSinOcupar();
      listaUuid.add(0, uuid);
      String[] label = new String[listaUuid.size()];
      label = listaUuid.toArray(label);
      DefaultComboBoxModel model = new DefaultComboBoxModel(label);
       jComboBox1.setModel(model);
       
       //aRooms.toArray(new String[aRooms.size()])
    }
    
    public void setTodo(String [] datos){

    
        
        setRut(datos[0]);
        setNombre(datos[1]);
        setDir( datos[2]);
        setNac(datos[3]);
        setNat(datos[4]);
        setCor(datos[5]);
        setNum( datos[6]);
        setUUID( datos[7]);
        
    }
    
    public void setUI(){
        this.setLocationRelativeTo(null);
        jCor.setForeground(blanco);
        jDir.setForeground(blanco);
        jNac.setForeground(blanco);
        jName.setForeground(blanco);
        jNat.setForeground(blanco);
        jNum.setForeground(blanco);
        jRut.setForeground(blanco);
        jCor.setBackground(negro2);
        jDir.setBackground(negro2);
        jNac.setBackground(negro2);
        jName.setBackground(negro2);
        jNat.setBackground(negro2);
        jNum.setBackground(negro2);
        jRut.setBackground(negro2);
        
        jLabel2.setForeground(blanco);
        jLabel3.setForeground(blanco);
        jLabel4.setForeground(blanco);
        jLabel5.setForeground(blanco);
        jLabel6.setForeground(blanco);
        jLabel7.setForeground(blanco);
        jLabel8.setForeground(blanco);
        jLabel9.setForeground(blanco);
        jLabel10.setForeground(blanco);
        jLabel1.setForeground(blanco);
        
        jPanel1.setBackground(negro);
        jComboBox1.setBackground(negro);
        jComboBox1.setForeground(blanco);
        jButton1.setBackground(negro);
        jButton1.setForeground(blanco);
        
        
    }
    
    public String getRut() {
        return rut;
    }
    public String getUUID() {
        return uuid;
    }

    public void setRut(String rut) {
        this.rut = rut;
        this.jRut.setText(rut);
    }
 public void setFavicon() throws IOException{
        Image i = ImageIO.read(getClass().getResource("/images/favicon48.png"));
        setIconImage(i);
    }
    public void setUUID(String uuid) {
        this.uuid = uuid;
//        this.jLabel11.setText(uuid);
        if(uuid.equals("000 , 000 , 000 , 000")){
            //this.jLabel11.setForeground(new java.awt.Color(240, 70, 70));
        }//else
           // this.jLabel11.setForeground(blanco);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.jName.setText(nombre);
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
        this.jDir.setText(dir);
    }

    public String getNac() {
        return nac;
    }

    public void setNac(String nac) {
        this.nac = nac;
        this.jNac.setText(nac);
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
        this.jNat.setText(nat);
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
        this.jCor.setText(cor);
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
        this.jNum.setText(num);
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
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jRut = new javax.swing.JLabel();
        jName = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jDir = new javax.swing.JTextField();
        jNac = new javax.swing.JTextField();
        jNat = new javax.swing.JTextField();
        jCor = new javax.swing.JTextField();
        jNum = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Perfil de Usuario");

        jLabel9.setText("Teléfono:");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/defaultprofile.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Perfil de Brigadista");

        jLabel3.setText("RUT:");

        jLabel5.setText("Dirección:");

        jLabel6.setText("Fecha Nac:");

        jLabel7.setText("Nacionalidad:");

        jLabel8.setText("Correo:");

        jRut.setText("JOrge Osvaldo Paz Pavez");

        jName.setText("jLabel10");

        jLabel10.setText("UUID:");

        jDir.setText("jTextField1");
        jDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDirActionPerformed(evt);
            }
        });

        jNac.setText("jTextField1");

        jNat.setText("jTextField1");

        jCor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCorActionPerformed(evt);
            }
        });

        jNum.setText("jTextField1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tooltip.png"))); // NOI18N
        jLabel11.setToolTipText("El rut no se puede modificar.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addComponent(jCor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jNum)
                    .addComponent(jNac)
                    .addComponent(jNat)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(8, 8, 8))
                    .addComponent(jDir, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jName)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jRut))
                            .addComponent(jLabel11))
                        .addGap(13, 13, 13)
                        .addComponent(jDir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jNac, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jNat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jCor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jNum, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(12, 12, 12))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDirActionPerformed

    private void jCorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // GUARDAR
            sbdd.updatePerfil(jRut.getText(),jDir.getText(),
                    jNac.getText(),jNat.getText(),jCor.getText(),jNum.getText(),jComboBox1.getSelectedItem().toString());
            SuccesGUI sc = new SuccesGUI("Brigadista modificado.");
                    sc.setResizable(false);
                    sc.setVisible(true);
                    sc.setLocationRelativeTo(null);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileEditar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(ProfileEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfileEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfileEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfileEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ProfileEditar().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(ProfileEditar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JTextField jCor;
    private javax.swing.JTextField jDir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jNac;
    private javax.swing.JLabel jName;
    private javax.swing.JTextField jNat;
    private javax.swing.JTextField jNum;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jRut;
    // End of variables declaration//GEN-END:variables
}
