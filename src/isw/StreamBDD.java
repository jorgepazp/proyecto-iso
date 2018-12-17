/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isw;
    import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author pazjo
 */
class StreamBDD {
    


/**
 * @author imssbora
 */

  public static void main(String[] args) {
      Object [][] hola = new Object[4][3];
      System.out.println("hola "+hola.length);
String jdbcUrl = "jdbc:postgresql://146.83.194.142:5432/paz18\"+\"user=jpaz1501&password=1946";
    String username = "jpaz1501";
    String password = "1946";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      // Step 1 - Load driver
     // Class.forName("org.postgresql.Driver"); // Class.forName() is not needed since JDBC 4.0

      // Step 2 - Open connection
      conn = DriverManager.getConnection(jdbcUrl);

      // Step 3 - Execute statement
      stmt = conn.createStatement();
      rs = stmt.executeQuery("SELECT version()");

      // Step 4 - Get result
      if (rs.next()) {
        System.out.println(rs.getString(1));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {

        // Step 5 Close connection
        if (stmt != null) {
          stmt.close();
        }
        if (rs != null) {
          rs.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

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
            String url="jdbc:postgresql://146.83.194.142/phppgadmin";
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
                    url,
                    "jpaz18", "1946");
 
            boolean valid = connection.isValid(50000);
            System.out.println("Conectando...");
            System.out.println(valid);
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
