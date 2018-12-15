/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isw;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.Driver;
/**
 *
 * @author pazjo
 */
class StreamBDD {
    
    void initComponents() throws SQLException{
    try {
            // We register the PostgreSQL driver
            // Registramos el driver de PostgresSQL
            System.out.println("Registrando Driver ");
            try { 
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            Connection connection = null;
            // Database connect
            // Conectamos con la base de datos
            
            //CREDENCIALES BDD
            /*
            146.83.194.1426
            user:   jpaz1501
            pass:   1946
            */
            System.out.println("Conectando...");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://146.83.194.142:5432",
                    "jpaz1501", "1946");
 
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
    
    }
    
    
    
    void StreamBDD() throws SQLException{
        System.out.println("flag 1 ");
        initComponents();
        System.out.println("flag Driver ");
    }
}
