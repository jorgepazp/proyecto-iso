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
    
    public Tabla(){
        Object[] nombresColumnas = {"Perfil","Nombre","Estado","Último registro","Ver Registro","Editar Registro"};
        this.setColumnCount(5);
        this.setColumnIdentifiers(nombresColumnas);
        
  
    }
    


    //Con lo siguiente, deshabilitamos que se puedan editar las celdas
    @Override
    public boolean isCellEditable(int row, int column){return false;}
    
     @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0: return ImageIcon.class;
            case 5: return ImageIcon.class;
            case 4: return ImageIcon.class;
            default: return Integer.class;
        }
    }
   
    
}
