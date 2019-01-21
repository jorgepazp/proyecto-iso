/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import modelos.Asistencia;
import modelos.Base;
import modelos.Brigadista;

/**
 *
 * @author pazjo
 */
public class EditGUI extends javax.swing.JFrame {

    /**
     * Creates new form VerGUI
     */
    public EditGUI() {
        
        initComponents();
        ajustarVentana();
        
    }

        private void ajustarVentana(){
           this.setBackground(negro2);
       /* this.jButton1.setBackground(negro2);
        this.jButton1.setForeground(blanco);
        this.jLabel1.setBackground(blanco);
        this.jLabel1.setForeground(blanco);*/
       this.jList1.setBackground(negro);
       this.jList1.setForeground(blanco);
        this.jPanel1.setBackground(negro2);
        this.jPanel2.setBackground(negro2);
        this.jPanel3.setBackground(negro2);
        this.jPanel4.setBackground(negro2);
        this.jPanel5.setBackground(negro2);
        this.jPanel6.setBackground(negro);
        this.jTextArea1.setBackground(negro1);
        this.jTextArea1.setForeground(blanco);
        this.guardar.setBackground(negro2);
        this.guardar.setForeground(blanco);
        this.jTextArea1.setLineWrap(true);
        this.jTextArea1.setWrapStyleWord(true);
        Font font = new Font("advent Regular", Font.PLAIN, 15);
        jTextArea1.setFont(font);
        
        this.labelHorarioNormal1.setForeground(blanco);
        //this.labelHorarioNormal.setForeground(blanco);
        this.jLabel1.setForeground(blanco);
        this.jLabel2.setForeground(blanco);
        this.jLabel3.setForeground(blanco);
        this.jLabel4.setForeground(blanco);
        //this.jLabel5.setForeground(blanco);
        this.jFormattedTextField2.setBackground(blanco);
        
        this.jLabel6.setForeground(blanco);
        this.jLabel7.setForeground(blanco); 
        }
    
     private Brigadista cache = null;
     private int indiceBase;
     private String rutCache;
        
    public void doChores(ArrayList<Base> data,int index, String rut) throws IOException{
        Brigadista brig = null;
        ArrayList<Brigadista> aux = new ArrayList<Brigadista>();
        aux = data.get(index-1).getBrigadistas();
        
        System.out.println("el numero total de brigadistas para esta base es: "+aux.size());
        
        //en el siguiente bucle buscamos el rut que clickeó el usuario
        for(int i=0;i<aux.size();i++){
            System.out.println(aux.get(i).getName());
            if(aux.get(i).getRut().equals(rut)){
                brig = aux.get(i);
                i=aux.size()*2;
            }
        }
        this.cache = brig;
        this.indiceBase = index;
        this.rutCache = rut;
        System.out.println("la instancia para el brigadista es "+brig);
        setJList(brig.getListaReversed());
        this.setTitle("Modificando registros de: "+ brig.getName());
        setFavicon();
        //this.jLabel6.setText(Integer.toString(brig.getAsistencias().get(0).getId()));
        ArrayList<Asistencia> prueba = brig.getAsistencias();
        System.out.println("La cantidad de asistencias que registra este usuario es :"+prueba.size());
        if(brig.getAsistencias().size()!=0){
            setDatosEnGui(brig.getAsistencias().get(0).getId(),brig);
        }else{
            //MOSTRAR A USUARIO QUE EL BRIGADISTA NO POSEE REGISTROS
            
            //this.setVisible(false);
            ErrorGUI err = new ErrorGUI();
            err.setErrorMessage("Brigadista no posee registros");
            err.setVisible(true);
            err.setLocationRelativeTo(null);
            guardar.setEnabled(false);
       
        }
    }
    
    
    
    private void setDatosEnGui(int target, Brigadista brig){
        try{
            Asistencia aux = getAsistenciaById(target,brig);

            this.jLabel6.setText(aux.getHorarioDeInicio());
            System.out.println("Seteada label 6");

            this.jLabel7.setText(aux.getHorarioDeFin());
            System.out.println("Seteada label 7");
        }catch(Exception e){
            System.out.println("no se encontró marcaje de salida para el brigadista");
            //e.printStackTrace();

            this.jLabel7.setText("--:--:--");
        }
    }
    
    private void setDatosEnGui(String target, Brigadista brig){
        try{
            Asistencia aux = getAsistenciaByFechaInicio(target,brig);
            this.jLabel6.setText(aux.getHorarioDeInicio());
            System.out.println("Seteada label 6");
            this.jLabel7.setText(aux.getHorarioDeFin());
            System.out.println("Seteada label 7");
            this.jTextArea1.setText(aux.getNota());
        }catch(Exception e){
            System.out.println("no se encontró marcaje de salida para el brigadista");
            //e.printStackTrace();
            this.jLabel7.setText("--:--:--");
        }
    }
    
    /*El siguiente metodo devuelve una instancia de ASistencia dado una instancia
    de un Brigadista y una Id de Asistencia, si no encuentra match devuelve null*/
    private Asistencia getAsistenciaById(int target,Brigadista brig){
        if(brig.getAsistencias()!=null){
            //como la lista de asistencias de este brigadista <brig>
            //podemos buscar la asistencia <target> del brigadista
            ArrayList<Asistencia> aux = brig.getAsistencias();
            
            for(int i= 0;i<aux.size();i++){
                if(aux.get(i).getId() == target){
                    return brig.getAsistencias().get(i);
                }
            }
        }
        return null;
    }
    
    /*El siguiente metodo devuelve una instancia de ASistencia dado una instancia
    de un Brigadista y una Id de Asistencia, si no encuentra match devuelve null*/
    private Asistencia getAsistenciaByFechaInicio(String target,Brigadista brig){
        if(brig.getAsistencias()!=null){
            //como la lista de asistencias de este brigadista <brig>
            //podemos buscar la asistencia <target> del brigadista
            ArrayList<Asistencia> aux = brig.getAsistencias();
            String fecha;
            
            for(int i= 0;i<aux.size();i++){
                
                if(aux.get(i).getFechaDeInicio().equals(target)){
                    return brig.getAsistencias().get(i);
                }
            }
        }
        return null;
    }
    
    private void setJList(ArrayList<Asistencia> lista){
        
       // Collections.reverse(lista);
        
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Asistencia a : lista){
        model.addElement( a.getFechaAsistencia());
        }
        this.jList1.setModel(model);
        jList1.setSelectedIndex(0);
        
    }
    
    public void setFavicon() throws IOException{
        Image i = ImageIO.read(getClass().getResource("/images/favicon48.png"));
        setIconImage(i);
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        labelHorarioNormal1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setType(java.awt.Window.Type.POPUP);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "06/01/2019", "05/01/2019", "04/01/2019", "30/12/2018", "29/12/2018" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Horario Marcado :");

        jLabel4.setText("Modificación Horario:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("7:59:26.4");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tooltip.png"))); // NOI18N
        jLabel5.setToolTipText("No se puede modificar el horario de entrada marcado.");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("18:46:36.2");

        try {
            jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField2)
                .addContainerGap())
        );

        jLabel2.setText("ENTRADA");

        jLabel3.setText("SALIDA");

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelHorarioNormal1.setText("Nota:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(labelHorarioNormal1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(labelHorarioNormal1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addContainerGap())
        );

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(guardar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
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

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        
        System.out.println("seleccionaste el elemento "+this.jList1.getSelectedValue());
       // System.out.println("seleccionaste el elemento "+this.jList1.getComponent(this.jList1.getSelectedIndex()).toString());
        setDatosEnGui(this.jList1.getSelectedValue(),cache);
      // Asistencia a = (Asistencia) this.ge();
        
    }//GEN-LAST:event_jList1ValueChanged

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        //jLabel5.
    }//GEN-LAST:event_jLabel5MouseClicked

    public void setConexion(StreamBDD bdd){
        this.sbdd=bdd;
    }
    
    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        // TODO add your handling code here:
        //
        Validators validador = new Validators();
        String horarioNuevo = this.jFormattedTextField2.getText();
        String horarioMarcado = this.jLabel7.getText();
        String fechaMarcaje = this.jList1.getSelectedValue().toString();
        
        String textoNota = this.jTextArea1.getText();
        if(validador.isValidInput(horarioNuevo.split(":"))){
            System.out.println("horario nuevo "+horarioNuevo);
            System.out.println("HOrario marcado "+horarioMarcado);
            if(horarioMarcado.equals("--:--:--")){
                try {
                    //hacerCambio y setear como inactivo a brigadista
                    System.out.println("adsfsaffs");
                    sbdd.updateHorario(instanciaRut,horarioNuevo,fechaMarcaje,textoNota);
                    sbdd.setInactivo(instanciaRut);
                
                    setCambiosOnEdit(horarioNuevo,textoNota);
                } catch (SQLException ex) {
                    Logger.getLogger(EditGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else if (validador.isValidInputHorarioSalida(horarioMarcado, horarioNuevo)){
                try {
                    sbdd.updateHorario(instanciaRut,horarioNuevo,fechaMarcaje,textoNota);
                    setCambiosOnEdit(horarioNuevo,textoNota);
                } catch (SQLException ex) {
                    Logger.getLogger(EditGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
        }else{
            ErrorGUI error = new ErrorGUI();
            error.setErrorMessage("<html>El número ingresado no es valido<br/>Intente nuevamente.</html>");
            error.setVisible(true);
            error.setResizable(false);
            error.setLocationRelativeTo(null);
        }
            
        
    }//GEN-LAST:event_guardarActionPerformed
    
    private void setCambiosOnEdit(String horario,String nota){
        this.jLabel7.setText(horario);
        this.jTextArea1.setText(nota);
    }

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
            java.util.logging.Logger.getLogger(EditGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditGUI().setVisible(true);
            }
        });
    }
      //Colores UI
    Color grid= new Color(60,60,60);
    Color negro2= new Color(23,23,23);
    Color negro1= new Color(43,43,43);
    Color negro = new Color(27,27,27);
    Color blanco = new Color(196,196,196);
    private StreamBDD sbdd;
    private String instanciaRut;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton guardar;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelHorarioNormal1;
    // End of variables declaration//GEN-END:variables

    void setRutInstancia(String toString) {
        this.instanciaRut=toString;
    }
}
