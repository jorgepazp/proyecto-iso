package modelos;

import javax.swing.ImageIcon;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author pazjo
 */
public class Tabla extends DefaultTableModel {
    
    /*
    OBJETIVO: Mostrar en tabla Datos conseguidos de la base de datos.
    Esto es: mostrar los datos correspondientes en la tabla segun la base que esté seleccionada
    generar los logs de asistencia 
    modificar asistencias casos excepcionales -> lo que la profe te asigno
    para mostrar lo ultimo utiliza la clase verGUI ( cuando el usuario clickee en modificar registros
    debe aparecer una ventana que permita modificar solo la fecha de salida del marcaje del usuario, y la fecha de inicio no puede ser
    despues qu ela fecha modificada (validación, no urgente) permitiendo tambien agregar una nota de modificacion
    el cambio en el log se debe hacer en la base de datos una vez realizado todo 
    
    */
    
    
    public Tabla(){
        Object[] nombresColumnas = {"Perfil","RUT","Nombre","Estado","Último registro","Ver Registro","Editar Registro","Eliminar Registro"};
        this.setColumnCount(7);
        this.setColumnIdentifiers(nombresColumnas);
    }
    
   
    //Con lo siguiente, deshabilitamos que se puedan editar las celdas
    @Override
    public boolean isCellEditable(int row, int column){return false;}
    
     @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0: return ImageIcon.class;
            case 6: return ImageIcon.class;
            case 7: return ImageIcon.class;
            case 5: return ImageIcon.class;
            default: return Integer.class;
        }
    }
   
    
}
