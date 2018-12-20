//vamos que se puede timmm 
package isw;

import java.awt.Color;
import java.awt.Image;
import java.net.MalformedURLException;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import modelos.Tabla;

/**
 *
 * @author pazjo
 */
public class MainFramework {
    //en esta clase 
   // private static GUI gui = new GUI();

    
    //UNUSED VARIABLES
    //Variable que contiene la tabla original de la base actual en selección.
    private static Tabla tabla_original = new Tabla(); //-> debe cambiarse cada vez que  
                                                        /* el jComboBox de la base se cambia, es decir, si en el combo se muestra
                                                        concepción, la tabla a la que referencia el sistema debe ser un respaldo 
                                                        de esta misma, para fines practicos.
                                                        , para ello, usar un action listener
                                                        Buscar sobre ComboBox y como setear un listener */
    
    //variable que contiene tabla auxiliar
    private static Tabla tabla_auxiliar = new Tabla(); //-> Cuando el usuario requiera buscar un brigadista en especifico en la tabla   
                                                        /* o cuando se quiera generar una tabla sin perder la anterior se usa esta variable*/
    
    private static boolean estadoConnBDD;
    private static boolean estadoArduino;
    private static StrArd conArd; // variable que contiene la conexion con el arduino (punto de marcaje
    
     public static void main(String[] args) throws SQLException, InterruptedException  {
        
        Splash splash = new Splash();
        splash.setVisible(true);
        
        //LO PRIMERO - conexión a la BDD
        System.out.println("Inicializando conexión con BDD");
        StreamBDD sbdd = new StreamBDD();
        GUI gui = new GUI(sbdd);
        gui.setEstadoLabels(gui.labelEstadoBdd, sbdd.conectarBDD());
        System.out.println("Base de datos conectada");
       
       
         //Constructor de JSON's -- UNUSED
         
         //UNUSED ConstructorDatos c = new ConstructorDatos();
         /*sbdd.getUltimaAsistenciaString("19.462.117-5");*/
         gui.renderTabla(sbdd.generaTablas(1));
         //Streaming /Arduino
        StrArd str = new StrArd(sbdd);
        estadoArduino= str.conexionArduino();
        gui.setEstadoLabels(gui.labelEstadoArduino,estadoArduino);
        splash.setVisible(false);
        gui.setVisible(true);
        /*Thread.sleep(5000);
        gui.setModeloTabla();
        gui.addRowToTable();
       */
     }
     
    
}
