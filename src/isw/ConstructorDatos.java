/*
    la siguiente clase debe construir los datos de asistencia de lsos brigadistas
    
 */
package isw;

import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.Asistencia;
import modelos.Base;
import modelos.Registro;

/**
 *
 * @author pazjo
 */
class ConstructorDatos {
    
    public ArrayList<Base> bases;
    private StreamBDD conexion_a_BDD;
    
    /*
    El siguiente método debe retornar en cascada los datos para todo el modelo, esto es
    llenar un arraylist con las asistencias, luego llenar un arraylist con Registro que contengan las asistencias
    y llenar un AL con las bases que contengan estos registros
    de este modo es mas facil acceder a la base de datos
    */

    ConstructorDatos(StreamBDD sbdd) {
        this.conexion_a_BDD = sbdd;
    }
    
    
    //el siguiente metodo recibe un rs
    //y llena el arraylist 
    public void llenarBases(ResultSet rs){
        
    } 
    

    
   // private obtener

    ArrayList<Base> getDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
