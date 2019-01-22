/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isw;


import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * @author Antony Garcia Gonzalez
 * <br>Un codigo que permite recibir datos desde el Arduino. Debe ser utilizado
 * con el codigo single_data_send.ino corriendo en el Arduino
 */
public class StrArd {
//Se crea una variable tipo PanamaHitek_Arduino
    public static StreamBDD conexion;
    
   // Al inicializarse el arduino, se debe conectar con la base de datos para efectuar la asistencia
    public StrArd(StreamBDD sbdd){
        this.conexion = sbdd;
    }
    
    static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    //Se crea un eventListener para el puerto serie
    static SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        //Si se recibe algun dato en el puerto serie, se ejecuta el siguiente metodo
        public void serialEvent(SerialPortEvent serialPortEvent) {
            try {
                /*
                Los datos en el puerto serie se envian caracter por caracter. Si se
                desea esperar a terminar de recibir el mensaje antes de imprimirlo, 
                el metodo isMessageAvailable() devolvera TRUE cuando se haya terminado
                de recibir el mensaje, el cual podra ser impreso a traves del metodo
                printMessage()
                 */
                if (ino.isMessageAvailable()) {
                    //Se le asigna el mensaje recibido a la variable msg
                    String msg = ino.printMessage();
                    //Se imprime la variable msg
                    System.out.println(msg);
                    conexion.registrarAsistencia(msg);
                    final Runnable runnable =
     (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
if (runnable != null) runnable.run();
                }
            } catch (SerialPortException ex) {
                Logger.getLogger(StrArd.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ArduinoException ex) {
                Logger.getLogger(StrArd.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(StrArd.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(StrArd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };  
    
    //el siguiente método retorna true si se conecto el arduino correctamente, de lo contrario false
    public boolean conexionArduino(){
       try {
            //Se inicializa la conexion con el Arduino en el puerto COM5
            System.out.println("Puertos disponibles"+ino.getPortsAvailable());
            System.out.println(ino.getSerialPorts());
            ino.arduinoRX("COM3", 9600, listener);
            
            return true;
        } catch (ArduinoException ex) {
            //Logger.getLogger(StrArd.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("prueba");
            return false;
        } catch (SerialPortException ex) {
          //  Logger.getLogger(StrArd.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
    public static void main(String[] args) {
        try {
            //Se inicializa la conexion con el Arduino en el puerto COM5
             System.out.println("Puertos disponibles"+ino.getPortsAvailable());
             System.out.println(ino.getSerialPorts());
            ino.arduinoRX("COM3", 9600, listener);
           
        } catch (ArduinoException ex) {
            Logger.getLogger(StrArd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(StrArd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}