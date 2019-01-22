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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Asistencia;
import modelos.Base;
import modelos.Brigadista;
/**
 *
 * @author pazjo
 */
class StreamBDD {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    /*public StreamBDD() throws SQLException{
        conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/CEF","postgres","");
        Statement stmt = conn.createStatement();
    }
    */
    //Retrieves the base id dado el nombre de la misma
    public int getIdBase(String nombreBase) throws SQLException{
        //Consulta
        String query = "select base_codigo From base " +
        " where base_nombre = '"+nombreBase+"'";
        rs=stmt.executeQuery(query);
        while(rs.next()){
            return rs.getInt("base_codigo");
        }
        return 1; 
    }
    
    
    /**
     *
     * El siguiente método recibe la id de una base, y transforma la información 
     * a un formato aceptado por la tabla que se muestra en la GUI
     * 
     **/
    public String[][] generaTablas(int idBase) throws SQLException{
        
        //conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/CEF","postgres","");
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
        //Consulta
        String query = "select brigadista_rut,brigadista_nombre_completo,\"isActivo\" From brigadista b, base e\n" +
        " where b.base_codigo = "+idBase+" and b.visible = true GROUP BY brigadista_rut";
           
        rs=statement.executeQuery(query);
         rs.last();
        System.out.println("numero de filas: "+rs.getRow());
        
        int filas = rs.getRow();
        String aux = null; 
        String [][]data = new String[filas][8];
       
        //COLUMNAS
        /*
        1: RUT brigadista
        2: Nombre Completo
        3:ESTADO
        4: ultimo registro
        */
        
        rs.beforeFirst();
        
        while (rs.next()) {
            
            System.out.println("Fila actual: "+rs.getRow());
            System.out.println(rs.getString("brigadista_rut"));
            data[rs.getRow()-1][1] = rs.getString("brigadista_rut");
            data[rs.getRow()-1][2] = rs.getString("brigadista_nombre_completo");
            if(rs.getBoolean("isActivo")){
                aux = "Activo";
            }else aux = "Inactivo";
            data[rs.getRow()-1][3] = aux;
            data[rs.getRow()-1][4] = getUltimaAsistenciaString(rs.getString("brigadista_rut"));
            System.out.println(rs.getString("brigadista_nombre_completo"));
            System.out.println(rs.getBoolean("isActivo"));
           
        }
        
        System.out.println("El numero de filas que contiene esta tabla es: "+data.length);
        return data;
        /*
        for(int i = 0;i<=data.length;i++){
            for(int j =0;j<=6;j++){
                System.out.println(data[i][j]+"+");
            }
            System.out.println();
        }*/
    }
    
    public String getRutAsociadoAUUID(String UUID) throws SQLException{
        //COnsulta
        String query = "SELECT brigadista_rut FROM BRIGADISTA "
                + "WHERE brigadista_uuid = '"+ UUID+"'";
           
        rs=stmt.executeQuery(query);
    while (rs.next()) {
        System.out.println("FLAG:"+rs.getString(1));
        return rs.getString(1);}
    
        return null;
    }
    
    /*
    El siguiente método nos dice si cierto brigadista esta activo actualmente en la base
    */
    public boolean isActivo(String RUT) throws SQLException{
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
    
    public void borrarBrigadista(String rut) throws SQLException{
        String query = "UPDATE brigadista\n" +
        "SET visible = false\n ,brigadista_uuid = null " +
        "WHERE brigadista_rut = '"+rut+"'";
        rs=stmt.executeQuery(query);
    }   
    /*
    *IMPLEMENTACIÓN JORGE PAZ ENTREGA 2
    */
    
    public void registrarAsistencia(String UUID) throws SQLException, ParseException{
        System.out.println("La UUID Recibida es: "+UUID);
        String rutAsociado=getRutAsociadoAUUID(UUID);
        System.out.println(rutAsociado);
        String date = getFecha();
           
        //rs=stmt.executeQuery(query);
        
        //Si el rut Asociado a la tarjeta existe, debemos registrar la asistencia
        if(!(rutAsociado == null)){
        System.out.println("Rut Asociado a tarjeta con UUID: "+UUID);
        //if(!haMarcadoAntes(date,rutAsociado)){
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
        //}
        System.out.println("Registrando asistencia para : "+ rutAsociado);
        
        }else System.out.println("Ningun rut asociado a tarjeta!");
        try{
            registrarUuid(UUID);
            SuccesGUI sc = new SuccesGUI("Se ha registrado una nueva UUID");
            sc.setVisible(true);
            sc.setLocationRelativeTo(null);
        }catch(Exception e){
            
        }
    }
    
    private boolean haMarcadoAntes(String fecha, String rut) throws ParseException, SQLException{
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fec = formatter.parse(fecha);
        String query = "select * from asistencia q, brigadista b\n" +
"	where cast(asistencia_marca_inicio as date) = '"+fec+"' and b.brigadista_rut = '"+rut+"'\n" +
"	and q.brigadista_rut = b.brigadista_rut";
        rs = stmt.executeQuery(query);
        
        if(rs.next()){
            return true;
        }else return false;
        
    }
    
    private void registrarUuid(String uuid) throws SQLException{
        String query = "INSERT INTO public.uuid(\n" +
"	uuid)\n" +
"	VALUES ('"+uuid+"');";
        stmt.executeUpdate(query);
    }
    
    //El siguiente método devuelve la id de la última asistencia registrada para un b rigadista
    public int getUltimaAsistencia(String RUT) throws SQLException{
        String query = "SELECT asistencia_codigo \n" +
        "FROM asistencia\n" +
        "WHERE brigadista_rut = '"+RUT+"'\n" +
        "ORDER BY asistencia_codigo DESC \n" +
        "LIMIT 1";
           
        rs=stmt.executeQuery(query);
        rs.next();
        return rs.getInt(1);    
    }
    
    //actualiza registros en modificacion de asistencias
    //public boolean editRegistro
    
    
    //El siguiente metodo devuelve en String la ultima vez que un brigadista marcó 
    //ya sea el inicio de su turno, o el fin del mismo
    public String getUltimaAsistenciaString(String RUT) throws SQLException{

        ResultSet resultSet ;
        String query = "SELECT asistencia_marca_inicio,asistencia_marca_fin \n" +
        "FROM asistencia\n" +
        "WHERE brigadista_rut = '"+RUT+"'\n" +
        "ORDER BY asistencia_codigo DESC \n" +
        "LIMIT 1";     
        resultSet=stmt.executeQuery(query);
        
        if(resultSet.next()){
        System.out.println("Resultado RS GUAS method: "+ resultSet.getString("asistencia_marca_inicio")+" "+ resultSet.getString("asistencia_marca_fin"));
        if(resultSet.getString("asistencia_marca_fin")==null) return resultSet.getString("asistencia_marca_inicio");
        else return resultSet.getString("asistencia_marca_fin");
        } else return "Sin Registro de Asistencia";
    }
    
    //El siguiente metodo cambia el estado activo de un brigadista según sea el parametro que recibe
    public void setEstadoActivo(boolean bol, String RUT) throws SQLException{
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
        //Connection conn = null;
        ResultSet rs = null;

        try {
        // Step 2 - Open connection
        this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CEF","postgres","");
        
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
    }
    }
    
  public static void main(String[] args) {
     
  }
  public String[] getDatosByRut(String RUT) throws SQLException{

        
        //COnsulta
        String query = "SELECT * FROM BRIGADISTA "
                + "WHERE brigadista_rut = '"+ RUT+"'";
           
        rs=stmt.executeQuery(query);
       
        //Esto va a entrar 1 sola vez
        while(rs.next()){
            String [] datos ={
            rs.getString("brigadista_rut"),
            rs.getString("brigadista_nombre_completo"),
            rs.getString("brigadista_direccion"),
            rs.getString("brigadista_rol"),
            rs.getString("brigadista_nacionalidad"),
            rs.getString("brigadista_correo"),
            rs.getString("brigadista_telefono"),
            rs.getString("brigadista_uuid")
                
            };
            return datos;        
        }
        return null;
  }
  
   public String[] getDatosByRut(String RUT,String base, boolean flag) throws SQLException{

        
        //COnsulta
        String query = "SELECT * FROM BRIGADISTA B, BASE V\n" +
"WHERE b.brigadista_rut = '"+RUT+"' and \"isActivo\"= "+flag+" and v.base_nombre = '"+base+"'" +
"and v.base_codigo = b.base_codigo";
           System.out.println(query);
        rs=stmt.executeQuery(query);
       
        //Esto va a entrar 1 sola vez
        while(rs.next()){
            String [] datos ={
            rs.getString("brigadista_rut"),
            rs.getString("brigadista_nombre_completo"),
            rs.getString("brigadista_direccion"),
            rs.getString("brigadista_rol"),
            rs.getString("brigadista_nacionalidad"),
            rs.getString("brigadista_correo"),
            rs.getString("brigadista_telefono"),
            rs.getString("brigadista_uuid")
                
            };
            return datos;        
        }
        return null;
  }

  /* DEPRECATED*/
  /**
   * void initComponents() throws SQLException{
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
            
            //146.83.194.1426
           // user:   jpaz1501
            //pass:   1946
            
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
   * @throws SQLException 
   */  
  
    
    
    
    void StreamBDD() throws SQLException{
        System.out.println("flag 1 ");
        //initComponents();
        
        System.out.println("flag Driver ");
    }
    
    //METODOS GENERADORES DE DATOS PARA EL SW
    public ArrayList<Base> getDatosSistema() throws SQLException, ParseException{

        ArrayList<Base> bases = new ArrayList<Base>();

        rs = stmt.executeQuery("SELECT * from BASE");
        int codigobase;
        String nombrebase;
        String direccionbase;
        String telefonobase;
        Base aux = null;
        
        while(rs.next()){
            
            codigobase = rs.getInt("base_codigo");
            nombrebase = rs.getString("base_nombre");
            direccionbase = rs.getString("base_telefono");
            telefonobase = rs.getString("base_direccion");
            aux = new Base(codigobase,nombrebase,direccionbase,telefonobase);
            bases.add(aux);
            
        }
        System.out.println("Bases creadas ("+bases.size()+") , generando brigadistas");
        for(int i=0;i<bases.size();i++){
            System.out.println("generando Brigadistas para base n: "+i);
            aux = bases.get(i);
            aux.setBrigadistas(generaBrigadistasPorIdBase(aux.getIdBase()));
        }
        return bases;
    } 
    
    private ArrayList<Brigadista> generaBrigadistasPorIdBase(int idBase) throws SQLException, ParseException{
        ArrayList<Brigadista> brigadistas = new ArrayList<Brigadista>();
        System.out.println("generando brigadista para id de base n : "+idBase);
        rs = stmt.executeQuery("SELECT * from brigadista "
                + "where base_codigo = "+idBase);
        Brigadista aux = null;
        String rut;
        String name;
        String telefono;
        String nacimiento;
        String nacionalidad;
        String email;
        String direccion;
        int codigobase;
        String uuid;
        while(rs.next()){
            
           
            rut = rs.getString("brigadista_rut");
            name = rs.getString("brigadista_nombre_completo");
            telefono = rs.getString("brigadista_telefono");
            nacimiento = rs.getString("brigadista_rol");
            nacionalidad = rs.getString("brigadista_nacionalidad");
            email = rs.getString("brigadista_correo");
            direccion = rs.getString("brigadista_direccion");
            uuid = rs.getString("brigadista_uuid");
            codigobase = rs.getInt("base_codigo");
            aux = new Brigadista(rut,name,telefono,nacimiento,nacionalidad,email,uuid,direccion,codigobase);
            brigadistas.add(aux);
        }
        
        for(int i=0;i<brigadistas.size();i++){
            aux = brigadistas.get(i);
            aux.setAsistencias(generaAsistenciasPorRutBrigadista(aux.getRut()));
        }
        
        return brigadistas;
    }
    
    private ArrayList<Asistencia> generaAsistenciasPorRutBrigadista(String rut) throws SQLException, ParseException{
  
        ArrayList<Asistencia> asistencias = new ArrayList<Asistencia>();

        System.out.println("Generando asistencias para el rut: "+rut);
        rs = stmt.executeQuery("SELECT * from asistencia"
                + " where brigadista_rut = '"+rut+"'");
        Asistencia aux = null;
        
        int id;
        String _rut;
        String inicio;
        String fin;
        String nota;
        Date dateFin = null;
        Date dateInicio = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    
        while(rs.next()){
            
            id = rs.getInt("asistencia_codigo");
            rut = rs.getString("brigadista_rut");
            inicio = rs.getString("asistencia_marca_inicio");
            fin = rs.getString("asistencia_marca_fin");
            nota = rs.getString("asistencia_nota_modificacion");
            
            
            dateInicio = formatter.parse(inicio);
            if(fin != null){    
                dateFin = formatter.parse(fin);
            }else dateFin= null;
            aux = new Asistencia(id,rut,dateInicio,dateFin,nota);
            asistencias.add(aux);
            
        }
        return asistencias;
    }
    
    
    void updateHorario(String instanciaRut, String horarioNuevo,String fechaMarcaje,String nota) throws SQLException {
        String query = "UPDATE asistencia\n" +
        "SET asistencia_marca_fin = TIMESTAMP '"+fechaMarcaje+" "+horarioNuevo+"'\n , asistencia_nota_modificacion = '" +
        nota+"' where asistencia_codigo in (select q.asistencia_codigo from brigadista b, asistencia q\n" +
        "WHERE b.brigadista_rut = '"+instanciaRut+"' \n" +
        "and cast(q.asistencia_marca_inicio as date) = '"+fechaMarcaje+"'\n" +
        "and q.brigadista_rut = b.brigadista_rut)";
        System.out.println("modificacion = "+query);
        try{
        stmt.executeUpdate(query);
        }catch(Exception e){
            
        }
    }

    public ArrayList<String> getListaBases() throws SQLException{
        rs = stmt.executeQuery("select * from base "     );
        ArrayList<String> uuid = new ArrayList<String>();
        while(rs.next()){
            uuid.add(rs.getString("base_nombre"));
        }
        return uuid;
    }
    
    public ArrayList<String> getListaUUIDSinOcupar() throws SQLException{
        rs = stmt.executeQuery("select * from uuid u\n" +
                    "where u.uuid NOT IN(\n" +
                    "select b.brigadista_uuid from brigadista b\n" +
                    "	where u.uuid = b.brigadista_uuid )" 
                    );
        ArrayList<String> uuid = new ArrayList<String>();
        while(rs.next()){
            uuid.add(rs.getString("uuid"));
        }
        return uuid;
    }
    
    public void setInactivo(String instanciaRut) throws SQLException {
        String query = "UPDATE brigadista" +
        " SET \"isActivo\" = false "+
        "WHERE brigadista_rut = '"+instanciaRut+"'";
        System.out.println(query);
        try {
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(StreamBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void updatePerfil(String rut, String dir, String nac, String nat, String correo, String numero, String uuid) throws SQLException {
        String query = "UPDATE brigadista\n" +
        "SET brigadista_direccion = '"+dir+"' ,brigadista_rol = '"+nac +
        "' , brigadista_nacionalidad = '"+nat+ "' , brigadista_correo = '"+correo+"' ,"
        +" brigadista_telefono = '"+numero+"' , brigadista_uuid = '"+uuid+ "' "+
        "WHERE brigadista_rut = '"+rut+"'";
        
        stmt.execute(query);
    }
 
    void crearBrigadista(String rut,String nombre, String dir, String nac, String nat, String correo, String numero, String uuid,String base) throws SQLException{
        int idBase = getIdBase(base);
        String query = "INSERT INTO public.brigadista(\n" +
"	brigadista_rut, base_codigo, brigadista_nombre_completo, brigadista_telefono, brigadista_rol, brigadista_nacionalidad, brigadista_correo, brigadista_uuid, brigadista_direccion, \"isActivo\", visible)\n" +
"	VALUES ('"+rut+"','"+idBase+"','"+ nombre+"','"+ numero+"','" +nac+"','" +nat+"','" +correo+"','"+ uuid+"','"+ dir+"', false ,true );";
        stmt.executeUpdate(query);
    }

}
