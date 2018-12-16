//vamos que se puede timmm 
package isw;

import java.awt.Image;
import java.net.MalformedURLException;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author pazjo
 */
public class MainFramework {
    //en esta clase 
    private static GUI gui = new GUI();
    
     public static void main(String[] args) throws SQLException  {
     
        //LO PRIMERO - conexión a la BDD
        System.out.println("Inicializando conexión con BDD");
        StreamBDD sbdd = new StreamBDD();
        sbdd.initComponents();
        System.out.println("Registrando Driver ");
        
         //Constructor de JSON's
         ConstructorDatos c = new ConstructorDatos();
         
         //Streaming /Arduino
        StrArd str = new StrArd();

       
     }
     
    
}
