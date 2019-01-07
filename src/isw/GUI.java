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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import modelos.Base;
import modelos.Tabla;


/**
 *
 * @author pazjo
 */
public class GUI extends javax.swing.JFrame {

 
    private ArrayList<Base> data = new ArrayList<Base>();
    /**
     * Creates new form GUI
     */
   
    
    public void setModeloTabla(){
        Tabla t = new Tabla();
        
        tabla.setModel(t);  
        
    }
    
    //El siguiente método, hace que la tabla que está actualmente visible ( en interfaz) sea "escondida"
    //si llamamos a este metodo mostrara en la tabla de la interfaz la matriz de datos que reciba en 
    
    
 
    
    public GUI(StreamBDD bdd) {
        this.sbdd = bdd;
        initComponents();
        
        try {
            setFavicon();
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setEstadoLabels(labelEstadoArduino,true);  
        
        setModeloTabla();
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        
        
        
       
           // tabla.setFont(new Font("Tahoma", Font.BOLD, 20));
           setTemaUI();
    }   
    
    //El siguiente metodo le da los datos al gui para usarlos en verGui y EditarGui entre otros
    public void setFuenteDeDatos(ArrayList<Base> datos){
        this.data = datos;
    }
    
    //lo siguiente configura los colores del tema. -ignorar
    public void setTemaUI(){
        tabla.getTableHeader().setReorderingAllowed(false);
          
            jButton3.setBackground(negro1);
            jButton3.setForeground(blanco);
          
            jButton2.setBackground(negro1);
            jButton2.setForeground(blanco);
            jButton2.setEnabled(false);

            
            jTabbedPane1.setBackground(negro);
            jTabbedPane1.setForeground(grid);
            jTabbedPane1.setBorder(BorderFactory.createLineBorder(grid, 1));
            panelModificar.setBackground(negro);
            panelCrear.setBackground(negro);
            panelEliminar.setBackground(negro);
            panelBuscar.setBackground(negro);
            
            
           tabla.setForeground(Color.LIGHT_GRAY);
           jScrollPane1.setBackground(Color.BLACK);
           jScrollPane1.setForeground(Color.red);
           
           jButton1.setBackground(negro2);
           jButton1.setForeground(blanco);
           
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
          jPanel3.setBackground(negro);
          labelBuscar.setForeground(blanco);
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
        jTabbedPane1.setUI(new BasicTabbedPaneUI() {
   @Override
   protected void installDefaults() {
       super.installDefaults();
       highlight = negro;
       lightHighlight = negro1;
       shadow = negro1;
       darkShadow = negro2;
       focus = negro;
   }
});
    }
    
//el siguiente método permite añadir filas a una tabla dado un objeto de datos
    public void addRowToTable(/*Object data*/){
        Tabla t = (Tabla) tabla.getModel();
       
        Object[] d = {profile,"19462rut","Jorge Osvaldo Paz Pavez",2,3,v,e}; 
        t.addRow(d);
        tabla.setModel(t);
    }
    
    /*
    Cuando se quiera agregar una fila a la tabla que se esta mostrando en pantalla
    usar el siguiente metodo
    */
    public void agregarFilaATabla(String[] data){
        Tabla t = (Tabla) tabla.getModel();
       
        t.addRow(data);
        
        tabla.setModel(t);
    }
    
    /*
    El sigiente método construye la tabla desde 0 dado una matriz con datos
    */
    public void setConexionBDD(StreamBDD conexion){
        this.sbdd = conexion;
    }
    
    public StreamBDD getConexionBDD(){
        return this.sbdd;
    }
    
    public void renderTabla(String[][] data){
       Tabla t = new Tabla();//(Tabla) tabla.getModel();
       
       Object [] array = new Object[7];
       int i,j;
       
       
       
       for(i=0;i<data.length;i++){
           for(j=0;j<=6;j++){
               
               if(j==1||j==2||j==3||j==4){
                   
                   array[j] = data[i][j];
               }else if (j==0){
                   array[j]=profile;
               }else if (j==5){
                   array[j]=v;
               }else if (j==6){                  
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
            column = tabla.getColumnModel().getColumn(5);
            column.setMinWidth(100);
            column.setMaxWidth(100);
            column.setPreferredWidth(100);
            column = tabla.getColumnModel().getColumn(6);
            column.setMinWidth(100);
            column.setMaxWidth(100);
            column.setPreferredWidth(100);
    }
    
    
    //El siguiente metodo setea el favicon
    public void setFavicon() throws IOException{
        Image i = ImageIO.read(getClass().getResource("/images/favicon48.png"));
        setIconImage(i);
    }

    /*
    El siguiente método recibe un label y un estado (verdadero o falso)
    si es verdadero, setea el color del label recibido a verde
    si es falso, setea el color a rojo
    */
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
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelBuscar = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        labelBuscar = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        panelCrear = new javax.swing.JPanel();
        panelModificar = new javax.swing.JPanel();
        panelEliminar = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        estadoconlabel = new javax.swing.JLabel();
        bddlabel = new javax.swing.JLabel();
        arduinolabel = new javax.swing.JLabel();
        labelEstadoArduino = new javax.swing.JLabel();
        labelEstadoBdd = new javax.swing.JLabel();
        labelIdBase = new javax.swing.JLabel();
        baseidlabel = new javax.swing.JLabel();
        baselabel = new javax.swing.JLabel();
        comboBases = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ImageIcon perfil = new ImageIcon(getClass().getResource("/images/defaultprofile.png"));
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Control de Asistencia V1 - Contra el Fuego");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        logoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        logoPanel.setPreferredSize(new java.awt.Dimension(480, 200));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/applogo.png"))); // NOI18N

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(186, 186, 186))
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Herramientas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(196, 196, 196))); // NOI18N

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        labelBuscar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelBuscar.setText("Buscar por RUT:");

        jButton1.setText("Buscar");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Actualizar Tabla");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBuscarLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(labelBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(446, Short.MAX_VALUE))
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBuscarLayout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .addGroup(panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(labelBuscar)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Buscar", panelBuscar);

        javax.swing.GroupLayout panelCrearLayout = new javax.swing.GroupLayout(panelCrear);
        panelCrear.setLayout(panelCrearLayout);
        panelCrearLayout.setHorizontalGroup(
            panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 968, Short.MAX_VALUE)
        );
        panelCrearLayout.setVerticalGroup(
            panelCrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 189, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Crear", panelCrear);

        javax.swing.GroupLayout panelModificarLayout = new javax.swing.GroupLayout(panelModificar);
        panelModificar.setLayout(panelModificarLayout);
        panelModificarLayout.setHorizontalGroup(
            panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 968, Short.MAX_VALUE)
        );
        panelModificarLayout.setVerticalGroup(
            panelModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 189, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Editar", panelModificar);

        javax.swing.GroupLayout panelEliminarLayout = new javax.swing.GroupLayout(panelEliminar);
        panelEliminar.setLayout(panelEliminarLayout);
        panelEliminarLayout.setHorizontalGroup(
            panelEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 968, Short.MAX_VALUE)
        );
        panelEliminarLayout.setVerticalGroup(
            panelEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 189, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Borrar", panelEliminar);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        estadoconlabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        estadoconlabel.setText("ESTADOS DE CONEXIONES");

        bddlabel.setText("Base de datos:");

        arduinolabel.setText("Arduino:");

        labelEstadoArduino.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelEstadoArduino.setText("jLabel6");

        labelEstadoBdd.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelEstadoBdd.setForeground(new java.awt.Color(153, 0, 0));
        labelEstadoBdd.setText("jLabel5");

        labelIdBase.setText("1");

        baseidlabel.setText("ID. Base:");

        baselabel.setText("BASE:");

        comboBases.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Concepción", "Pichilemu", "Osorno", "Los Ángeles", "Curicó", "Talca", "Chillan", "Temuco", "Valdivia", "Futrono", " " }));
        comboBases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBasesActionPerformed(evt);
            }
        });

        jButton2.setText("Reconectar");
        jButton2.setToolTipText("Función no implementada en esta versión");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(estadoconlabel)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(arduinolabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelEstadoArduino))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(bddlabel)
                                .addGap(67, 67, 67)
                                .addComponent(labelEstadoBdd)))))
                .addGap(65, 65, 65))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(baselabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(baseidlabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelIdBase))
                    .addComponent(comboBases, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baselabel)
                    .addComponent(comboBases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baseidlabel)
                    .addComponent(labelIdBase))
                .addGap(18, 18, 18)
                .addComponent(estadoconlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bddlabel)
                    .addComponent(labelEstadoBdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arduinolabel)
                    .addComponent(labelEstadoArduino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
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

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jScrollPane1)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(29, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    //listener abre perfiles
    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        System.out.println("deja de clickear jiji ="+evt.getClickCount());
        if(evt.getClickCount() ==2){
            JTable target = (JTable)evt.getSource();
            int row = target.getSelectedRow();
            int column = target.getSelectedColumn();
            //Si la columna es 0, es por que se seleccionó el perfil de algun usuario

            System.out.println("fila = "+row +" columna : "+ column);
            if(column == 0){
                //linkear row al perfil
                System.out.println(tabla.getValueAt(row, 1)); //con esto se consigue el rut del user seleccionado
                try {
                    p = new Profile();
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                p.setVisible(true);
                p.setResizable(false);
                
                try {
                    // p.jPael1.setBackground(negro);
                    p.setTodo(sbdd.getDatosByRut((String) tabla.getValueAt(row, 1)));
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else if (column ==5){
                //Si la columna es 4, es por que se selecciono ver los registros de algun usuario
                vG= new VerGUI();
                vG.setResizable(false);
                vG.setVisible(true);
                vG.setLocationRelativeTo(null);
                vG.setBackground(negro2);
                
                try {
                    vG.doChores(data, Integer.parseInt(labelIdBase.getText()), tabla.getValueAt(row, 1).toString());
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }else if (column == 6){
                //Si la columna es 5, es por que se selecciono editar los registros de algun usuario

                eG= new EditGUI();
                eG.setResizable(false);
                eG.setVisible(true);
                eG.setLocationRelativeTo(null);

            }
        }
        tabla.addMouseListener(new MouseAdapter() {

        });
    }//GEN-LAST:event_tablaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton2ActionPerformed

    private void comboBasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBasesActionPerformed
        // TODO add your handling code here:
       // labelIdBase.setText(comboBases.getSelectedItem().toString());
        String nombrebase = comboBases.getSelectedItem().toString();
        System.out.println(nombrebase+" -< nombre base");
        int id;
        try {
            id = this.sbdd.getIdBase(nombrebase);
            labelIdBase.setText(String.valueOf(id));
            renderTabla(sbdd.generaTablas(id));
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_comboBasesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        //Si la siguiente condicion se repite es por que no se ha ingresado nada para buscar
        //
        if(!jTextField1.equals(null)){
            String filtro = jTextField1.getText();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String nombrebase = comboBases.getSelectedItem().toString();
        System.out.println(nombrebase+" -< nombre base");
        int id;
        try {
            id = this.sbdd.getIdBase(nombrebase);
            //labelIdBase.setText(String.valueOf(id));
            renderTabla(sbdd.generaTablas(id));
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
                new GUI(sbdd).setVisible(true);
            }
        });
    
        
    }
    
    public static StreamBDD sbdd; //variable que contiene la conexión con la base de datos,
    // realizar consultas con este parametro
    public ImageIcon profile = new ImageIcon(getClass().getResource("/images/defaultprofile.png"));
    public    ImageIcon v = new ImageIcon(getClass().getResource("/images/botonver.png"));
    public     ImageIcon e = new ImageIcon(getClass().getResource("/images/botoneditar.png"));
    
    //Colores UI
    Color grid= new Color(60,60,60);
    Color negro2= new Color(23,23,23);
    Color negro1= new Color(43,43,43);
    Color negro = new Color(27,27,27);
    Color blanco = new Color(196,196,196);
    
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelBuscar;
    public javax.swing.JLabel labelEstadoArduino;
    public javax.swing.JLabel labelEstadoBdd;
    private javax.swing.JLabel labelIdBase;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelCrear;
    private javax.swing.JPanel panelEliminar;
    private javax.swing.JPanel panelModificar;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
