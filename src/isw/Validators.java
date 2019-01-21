/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isw;

/*
La siguiente clase la usaremos para validar inputs

*/
public class Validators {
       
    /*
    Recibe un input 'xxxxxxxx' y devuelve 'xx.xxx.xxx-x'
    */
    public String FormatearRUT(String rut) {

            int cont = 0;
            String format;
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            format = "-" + rut.substring(rut.length() - 1);
            for (int i = rut.length() - 2; i >= 0; i--) {
                format = rut.substring(i, i + 1) + format;
                cont++;
                if (cont == 3 && i != 0) {
                    format = "." + format;
                    cont = 0;
                }
            }
            return format;
    }
    
    public String rutify(String astring){
        
        String cutString = astring.substring(0, 12);
        int maxLength = (astring.length() < 12)?astring.length():12;
        astring = astring.substring(0, maxLength);
        return cutString;
        
    }
    
    //El siguiente metodo devuelve un string rut sin puntos ni comillas
    public String getRutEnBruto(String rut){
        String aux = rut;
        aux = aux.replace(".","");
        aux = aux.replace("-","");
        return aux;
    }
    
    //el siguiente metodo recibe un input de horario de salida y de entrada, lo que hace es
    //ver si el horario de salida sucede despues que el horario de entrada
    //devuelve true si el horario es correcto
    public boolean isValidInputHorarioSalida(String horario_entrada, String horario_salida){
        //horario_entrada like "xx:xx:xx"
        try{
        String[] horario_entrada_array = horario_entrada.split(":");
        int entrada_horas    = Integer.parseInt(horario_entrada_array[0]); // HORA DEL MARCAJE DE ENTRADA
        int entrada_minutos  = Integer.parseInt(horario_entrada_array[1]); // MINUTOS DEL MARCAJE DE ENTRADA
        int entrada_segundos = Integer.parseInt(horario_entrada_array[2]); // SEGUNDOS DEL MARCAJE DE ENTRADA
        
        String[] horario_salida_array = horario_salida.split(":");
        int salida_horas    = Integer.parseInt(horario_salida_array[0]); // HORA DEL MARCAJE DE ENTRADA
        int salida_minutos  = Integer.parseInt(horario_salida_array[1]); // MINUTOS DEL MARCAJE DE ENTRADA
        int salida_segundos = Integer.parseInt(horario_salida_array[2]); // SEGUNDOS DEL MARCAJE DE ENTRADA
        
            if(isValidInput(horario_salida_array)){
                if(entrada_horas<salida_horas){
                    if(entrada_minutos<salida_minutos){
                        if(entrada_segundos<salida_segundos){
                            return true;
                        }
                    }
                }
            }   
        }catch(Exception e){
            ErrorGUI error = new ErrorGUI();
            error.setErrorMessage("Ha ocurrido un error");
            error.setVisible(true);
            error.setResizable(false);
            error.setLocationRelativeTo(null);
        }
        
        return true;
    }
    
    
    public boolean isValidInput(String[] array){
        int horas,minutos,segundos;
        
        try{
            horas = Integer.parseInt(array[0]);
            minutos = Integer.parseInt(array[1]);
            segundos = Integer.parseInt(array[2]);
            if(horas>=0 && horas<=23){
                if(minutos>=0&&minutos<=59){
                    if(segundos>=0 && segundos<=59){
                        return true;
                    }
                }
            }
            }catch(NumberFormatException e){
                
            }
        return false;
    }
    
}
