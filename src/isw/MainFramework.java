//vamos que se puede timmm 
package isw;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.UIManager;
import modelos.Base;
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
    
    private static ArrayList<Base> datos;
    
     public static void main(String[] args) throws SQLException, InterruptedException, ParseException, IOException  {
        
        Splash splash = new Splash();
        splash.setVisible(true);
        
        //LO PRIMERO - conexión a la BDD
        System.out.println("Inicializando conexión con BDD");
        StreamBDD sbdd = new StreamBDD();
        GUI gui = new GUI(sbdd);
        estadoConnBDD = sbdd.conectarBDD();
        
        gui.setEstadoLabels(gui.labelEstadoBdd, estadoConnBDD);
        System.out.println("Base de datos conectada");
       gui.setModeloComboBases();
         ConstructorDatos c = new ConstructorDatos(sbdd);
        datos = sbdd.getDatosSistema();
        gui.setFuenteDeDatos(datos);
       
        System.out.println("iniciando pretty print");
        prettyPrint(datos);
        System.out.println("fin pretty print");
        
        
        gui.renderTabla(sbdd.generaTablas(1));
         //Streaming /Arduino
        StrArd str = new StrArd(sbdd);
        try{
        estadoArduino= str.conexionArduino();    
        }catch(Exception e){
            System.out.println("JIJI");
        }
        
        gui.setEstadoLabels(gui.labelEstadoArduino,estadoArduino);
        splash.setVisible(false);
        gui.setVisible(true);
        /*Thread.sleep(5000);
        gui.setModeloTabla();
        gui.addRowToTable();
       */
     }
     
    public static void prettyPrint( ArrayList<Base> data){
        for(int i=0;i< data.size();i++){
            System.out.println("Base n:"+i+1+ " ,Nombre base = "+data.get(i).getBase_nombre());
            System.out.println("Consiguiendo brigadistas para la base");
            for(int j=0;j<data.get(i).getBrigadistas().size();j++){
                System.out.println("Brigadista n°"+j+" en base n°"+i+1);
                System.out.println(data.get(i).getBrigadistas().get(j).getName());
                System.out.println("_______________________");
            }
            System.out.println("__________________________________________");
        }
    }
}
