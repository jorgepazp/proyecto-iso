/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isw;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelos.Tabla;


/**
 *
 * @author pazjo
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    
    public void setModeloTabla(){
        Tabla t = new Tabla();
        
        tabla.setModel(t);  
        
    }
    
 
    
    public GUI() {
        
        initComponents();
        
        try {
            setFavicon();
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        setEstadoLabels(labelEstadoBdd,false);
        setEstadoLabels(labelEstadoArduino,true);   
        setModeloTabla();
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        
        Object [][] d = new Object[3][5];
        d[0][1]="Jorge Paz";
        d[0][2]="Activo";
        d[0][3]="Febrero";
        d[1][1]="Jamaikins";
        d[1][2]="Inactivo";
        d[1][3]="Febreroasfasfs";
        d[2][1]="Jorge ples";
        d[2][2]="Activosaf";
        d[2][3]="Febrero";
        
        
        renderTabla(d);
        addRowToTable();
       /* addRowToTable();
        addRowToTable();
        addRowToTable();
        addRowToTable();
        addRowToTable();
        addRowToTable();
        addRowToTable();*/
           // tabla.setFont(new Font("Tahoma", Font.BOLD, 20));
           setTemaUI();
    }   
    public void setTemaUI(){
        tabla.getTableHeader().setReorderingAllowed(false);
          
          
           tabla.setForeground(Color.LIGHT_GRAY);
           jScrollPane1.setBackground(Color.BLACK);
           jScrollPane1.setForeground(Color.red);
           
           
           Color grid= new Color(60,60,60);
           Color negro2= new Color(23,23,23);
           Color negro1= new Color(43,43,43);
           Color negro = new Color(27,27,27);
           Color blanco = new Color(196,196,196);
            tabla.setGridColor(grid);
            tabla.setBackground(negro1);
           arduinolabel.setForeground(blanco);
           baseidlabel.setForeground(blanco);
           baselabel.setForeground(blanco);
           bddlabel.setForeground(blanco);
           estadoconlabel.setForeground(blanco);
           labelIdBase.setForeground(blanco);

           jScrollPane1.getViewport().setBackground(negro);
           jPanel1.setBackground(negro);
           jPanel2.setBackground(negro);
           this.getContentPane().setBackground( negro);
          logoPanel.setBackground(negro);
          jScrollPane1.getVerticalScrollBar().setBackground(negro);
          comboBases.setBackground(negro);
          comboBases.setForeground(Color.WHITE);
        tabla.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            l.setBorder(new LineBorder(Color.gray, 1));
            l.setForeground(Color.WHITE);
            l.setBackground(negro2);
            
            

            return l;
        }
    });
    }
    
//el siguiente método permite añadir filas a una tabla dado un objeto de datos
    public void addRowToTable(/*Object data*/){
        Tabla t = (Tabla) tabla.getModel();
       
        Object[] d = {profile,"Jorge Osvaldo Paz Pavez",2,3,v,e}; 
        t.addRow(d);
        tabla.setModel(t);
    }
    
    /*
    El sigiente método construye la tabla desde 0 dado una matriz con datos
    */
    public void renderTabla(Object[][] data){
       Tabla t = new Tabla();//(Tabla) tabla.getModel();
       
       Object [] array = new Object[6];
       int i,j;
       for(i=0;i<data.length;i++){
           for(j=0;j<6;j++){
               System.out.println(j);
               if(j==1||j==2||j==3){
                   System.out.println("Prueba"+array[j]);
                   array[j] = data[i][j];
               }else if (j==0){
                   array[j]=profile;
               }else if (j==4){
                   array[j]=v;
               }else if (j==5){                  
                   array[j]=e;
               }
               
           }
           t.addRow(array);
       }
       tabla.setModel(t);
       TableColumn column = tabla.getColumnModel().getColumn(0);
            column.setMinWidth(105);
            column.setMaxWidth(105);
            column.setPreferredWidth(105);
            column = tabla.getColumnModel().getColumn(4);
            column.setMinWidth(100);
            column.setMaxWidth(100);
            column.setPreferredWidth(100);
            column = tabla.getColumnModel().getColumn(5);
            column.setMinWidth(100);
            column.setMaxWidth(100);
            column.setPreferredWidth(100);
    }
    
    public void setFavicon() throws IOException{
        Image i = ImageIO.read(getClass().getResource("/images/favicon48.png"));
        setIconImage(i);
    }

    public void setEstadoLabels(JLabel label, boolean state){
        
        if(state){
            //si el state es true, es por que la conexión fue correcta
            label.setForeground(new java.awt.Color(140, 200, 60));
            label.setText("Conectado");
        }else{ //Si es false, la conexión no se hizo, hence se setea ROJO
            label.setForeground(new java.awt.Color(240, 70, 70));
            label.setText("Desconectado");
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

        jPanel1 = new javax.swing.JPanel();
        logoPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        baselabel = new javax.swing.JLabel();
        comboBases = new javax.swing.JComboBox<>();
        estadoconlabel = new javax.swing.JLabel();
        bddlabel = new javax.swing.JLabel();
        arduinolabel = new javax.swing.JLabel();
        labelEstadoBdd = new javax.swing.JLabel();
        labelEstadoArduino = new javax.swing.JLabel();
        baseidlabel = new javax.swing.JLabel();
        labelIdBase = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ImageIcon perfil = new ImageIcon(getClass().getResource("/images/defaultprofile.png"));
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Control de Asistencia V1 - Contra el Fuego");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        logoPanel.setPreferredSize(new java.awt.Dimension(480, 200));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/applogo.png"))); // NOI18N
        jLabel7.setText("jLabel7");

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logoPanelLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logoPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        baselabel.setText("BASE:");

        comboBases.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Concepción", "Pichilemu", "Osorno", "Los Ángeles", "Curicó", "Talca", "Chillan", "Temuco", "Valdivia", "Futrono", " " }));
        comboBases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBasesActionPerformed(evt);
            }
        });

        estadoconlabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        estadoconlabel.setText("ESTADOS DE CONEXIONES");

        bddlabel.setText("Base de datos:");

        arduinolabel.setText("Arduino:");

        labelEstadoBdd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelEstadoBdd.setForeground(new java.awt.Color(153, 0, 0));
        labelEstadoBdd.setText("jLabel5");

        labelEstadoArduino.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelEstadoArduino.setText("jLabel6");

        baseidlabel.setText("ID. Base:");

        labelIdBase.setText("Concepción");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(baselabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(baseidlabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelIdBase))
                            .addComponent(comboBases, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(arduinolabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelEstadoArduino))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(bddlabel)
                                    .addGap(67, 67, 67)
                                    .addComponent(labelEstadoBdd)))
                            .addComponent(estadoconlabel))))
                .addGap(110, 110, 110)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baselabel)
                    .addComponent(comboBases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baseidlabel)
                    .addComponent(labelIdBase))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(estadoconlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bddlabel)
                    .addComponent(labelEstadoBdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arduinolabel)
                    .addComponent(labelEstadoArduino))
                .addGap(38, 38, 38))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 60, 60)));

        /*
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {perfil, null, null, null}
            },
            new String [] {
                "Perfil", "Estado", "Último registro", "Herramientas"
            }
        )
        {public boolean isCellEditable(int row, int column){return false;}}
    );
    */
    tabla.setCellSelectionEnabled(true);
    tabla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    tabla.setRowHeight(100);
    tabla.setRowMargin(2);
    tabla.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tablaMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(tabla);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBasesActionPerformed
        // TODO add your handling code here:
        labelIdBase.setText(comboBases.getSelectedItem().toString());
    }//GEN-LAST:event_comboBasesActionPerformed

    //listener abre perfiles
    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
         System.out.println("deja de clickear wetajiji ="+evt.getClickCount());
         if(evt.getClickCount() ==2){
             JTable target = (JTable)evt.getSource();
         int row = target.getSelectedRow();
         int column = target.getSelectedColumn();
         //Si la columna es 0, es por que se seleccionó el perfil de algun usuario
         if(column == 0){
             //linkear row al perfil
            p = new Profile();
            p.setVisible(true);
            p.setResizable(false);
            Object[] dato = {
            "19462117-5",
            "JORGE PAZ",
            "COIHUE 374",
            "27/06/1997",
            "CHILENA",
            "paz.jorge1@gmail.com",
            "+569 xxxxxxx"
        };
            p.setTodo(dato);
         }else if (column ==4){
         //Si la columna es 4, es por que se selecciono ver los registros de algun usuario
             vG= new VerGUI();
             vG.setResizable(false);
             vG.setVisible(true);   
         }else if (column == 5){
         //Si la columna es 5, es por que se selecciono editar los registros de algun usuario
            eG= new EditGUI();
            eG.setResizable(false);
            eG.setVisible(true);
         }
         }
        tabla.addMouseListener(new MouseAdapter() {
        
});
    }//GEN-LAST:event_tablaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    
        
    }
    public ImageIcon profile = new ImageIcon(getClass().getResource("/images/defaultprofile.png"));
    public    ImageIcon v = new ImageIcon(getClass().getResource("/images/botonver.png"));
    public     ImageIcon e = new ImageIcon(getClass().getResource("/images/botoneditar.png"));
    
    public EditGUI eG;
    public VerGUI vG;
    public Profile p;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel arduinolabel;
    private javax.swing.JLabel baseidlabel;
    private javax.swing.JLabel baselabel;
    private javax.swing.JLabel bddlabel;
    private javax.swing.JComboBox<String> comboBases;
    private javax.swing.JLabel estadoconlabel;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelEstadoArduino;
    private javax.swing.JLabel labelEstadoBdd;
    private javax.swing.JLabel labelIdBase;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
