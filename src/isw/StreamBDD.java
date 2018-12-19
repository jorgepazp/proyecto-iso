/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isw;
    import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author pazjo
 */
class StreamBDD {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;


    public String getRutAsociadoAUUID(String UUID) throws SQLException{
        conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/CEF","postgres","");
        stmt = conn.createStatement();
        
        //COnsulta
        String query = "SELECT brigadista_rut FROM BRIGADISTA "
                + "WHERE brigadista_uuid = '"+ UUID+"'";
           
        rs=stmt.executeQuery(query);
    while (rs.next()) {
        System.out.println("FLAG:"+rs.getString(1));
        return rs.getString(1);
        /*
    for (int i = 1; i <= columnsNumber; i++) {
        if (i > 1) System.out.print(",  ");
        
        String columnValue = rs.getString(i);
        System.out.print(columnValue + " " + rsmd.getColumnName(i));
    }
    System.out.println("");*/
}
    
        return null;
    }
    
    /*
    El siguiente método nos dice si cierto brigadista esta activo actualmente en la base
    */
    
    
    public boolean isActivo(String RUT) throws SQLException{
        conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/CEF","postgres","");
        stmt = conn.createStatement();
        
        //COnsulta
        String query = "SELECT \"isActivo\" FROM BRIGADISTA "
                + "WHERE brigadista_rut = '"+ RUT+"'";
           
        rs=stmt.executeQuery(query);
        rs.next();
        System.out.println("FLAG BOOL:"+rs.getBoolean(1));
        return rs.getBoolean(1);    
    
    }
    
    //El siguiente metodo, nos devuelve un string con 
    //la fecha exacta en el momento que se llama al mismo
    //en formato AAAA-MM-DD HH:mm:ss 
    public String getFecha(){
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String text = formatter.format(date);
        System.out.println("la fecha es: "+text);
        
        return text; 
    }
    
    public void registrarAsistencia(String UUID) throws SQLException{
        System.out.println("La UUID Recibida es: "+UUID);
        String rutAsociado=getRutAsociadoAUUID(UUID);
        System.out.println(rutAsociado);
        String date = getFecha();
        conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/CEF","postgres","");
        stmt = conn.createStatement();
        
        
           
        //rs=stmt.executeQuery(query);
        
        //Si el rut Asociado a la tarjeta existe, debemos registrar la asistencia
        if(!(rutAsociado == null)){
        System.out.println("Rut Asociado a tarjeta con UUID: "+UUID);
        
        System.out.println(isActivo(rutAsociado));
        if(isActivo(rutAsociado)){
        //Si está activo,debemos cerrar la ultima asistencia perteneciente al brigadista
        //para ello debemos conseguir la ultima tupla de asistencia asociada al brigadista.
        int idUltimaAsis = getUltimaAsistencia(rutAsociado);
        String query ="UPDATE public.asistencia SET  asistencia_marca_fin=TIMESTAMP '"+date+"' " +
        "WHERE asistencia_codigo="+idUltimaAsis+";";
            stmt.execute(query);
            //por ultimo, ahora debemos setear el estado del brigadista como inactivo (ya que termino el turno)
            setEstadoActivo(false,rutAsociado);
        }else{
        //Si está inactivo debemos crear una nueva asistencia registrada al brigadista
        //COnsulta
        String query ="INSERT INTO public.asistencia( brigadista_rut, asistencia_marca_inicio, asistencia_marca_fin, asistencia_nota_modificacion)" +
        "VALUES ( '"+rutAsociado+"', TIMESTAMP '"+date+"', null, '');";
            
            stmt.execute(query);
            //Ahora que el brigadista marcó, debemos registrarlo como activo en la base de datos; para ello 
            //Utilizaremos el método setEstadoActivo, pasando como parametro TRUE para que se setee como activo
            //el brigadista en cuestión
            setEstadoActivo(true,rutAsociado);
        }
        
        System.out.println("Registrando asistencia para : "+ rutAsociado);
        
        }else System.out.println("Ningun rut asociado a tarjeta!");
    }
    
    //El siguiente método devuelve la id de la última asistencia registrada para un b rigadista
    public int getUltimaAsistencia(String RUT) throws SQLException{
        conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/CEF","postgres","");
        stmt = conn.createStatement();
        String query = "SELECT asistencia_codigo \n" +
        "FROM asistencia\n" +
        "WHERE brigadista_rut = '"+RUT+"'\n" +
        "ORDER BY asistencia_codigo DESC \n" +
        "LIMIT 1";
           
        rs=stmt.executeQuery(query);
        rs.next();
        return rs.getInt(1);    
    }
    
    //El siguiente metodo cambia el estado activo de un brigadista según sea el parametro que recibe
    public void setEstadoActivo(boolean bol, String RUT) throws SQLException{
        conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/CEF","postgres","");
        stmt = conn.createStatement();
        String state;
        //Seteamos el string...
        if(bol){
            state = "true";
        }else state = "false";
        String query = "UPDATE public.brigadista SET  \"isActivo\"="+state+" WHERE brigadista_rut='"+RUT+"';";
        stmt.execute(query);
    }
    
    //El siguiente método se usa para conectar el programa a la BDD, devuelve true si la conexion fue 
    //exitosa y false de lo contrario
    public boolean conectarBDD(){
        System.out.println("Conectando a base de datos.. ");
        String jdbcUrl = "jdbc:postgresql://localhost:5432/ISO";
        String username = "postgres";
        String password = "";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
        // Step 2 - Open connection
        conn = DriverManager.getConnection(jdbcUrl,username,password);

        // Step 3 - Execute statement
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT version()");
        
      if (rs.next()) {
        System.out.println(rs.toString());
      }
      return true;

    } catch (SQLException e) {
        
        e.printStackTrace();
        return false;
    }/* finally {
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
    }*/
    }
    
  public static void main(String[] args) {
     
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
        //initComponents();
        
        System.out.println("flag Driver ");
    }
}
