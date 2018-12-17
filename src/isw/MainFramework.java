//vamos que se puede timmm 
package isw;

import java.awt.Color;
import java.awt.Image;
import java.net.MalformedURLException;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author pazjo
 */
public class MainFramework {
    //en esta clase 
    private static GUI gui = new GUI();
    
     public static void main(String[] args) throws SQLException  {
        
         Splash splash = new Splash();
        splash.setVisible(true);
        
        //LO PRIMERO - conexión a la BDD
        System.out.println("Inicializando conexión con BDD");
        StreamBDD sbdd = new StreamBDD();
        sbdd.initComponents();
        System.out.println("Registrando Driver ");
        
        //RUT, NOMBRE COMPLETO,DIRECCION,NAC,NAT,EMAIL,NUMERO 

        Object[] dato = {
            "19462117-5",
            "JORGE PAZ",
            "COIHUE 374",
            "27/06/1997",
            "CHILENA",
            "paz.jorge1@gmail.com",
            "+569 xxxxxxx"
        };
       
       
         //Constructor de JSON's
         ConstructorDatos c = new ConstructorDatos();
         
         //Streaming /Arduino
        StrArd str = new StrArd();
        splash.setVisible(false);
        gui.setVisible(true);
        
       
     }
     
    
}
