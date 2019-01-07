package modelos;

import java.util.ArrayList;

/**
 *
 * @author pazjo
 */
public class Base {
    private int idBase;
    private String base_nombre;
    private String base_direccion;
    private String base_telefono;
    private ArrayList<Brigadista> brigadistas ;

    public Base(int id,String nombre,String dir,String tel){
        this.idBase=id;
        this.base_direccion=dir;
        this.base_nombre=nombre;
        this.base_telefono=tel;
    }

  
    public String getBase_nombre() {
        return base_nombre;
    }

    public void setBase_nombre(String base_nombre) {
        this.base_nombre = base_nombre;
    }

    public String getBase_direccion() {
        return base_direccion;
    }

    public void setBase_direccion(String base_direccion) {
        this.base_direccion = base_direccion;
    }

    public String getBase_telefono() {
        return base_telefono;
    }

    public void setBase_telefono(String base_telefono) {
        this.base_telefono = base_telefono;
    }

    public int getIdBase() {
        return idBase;
    }

    public void setIdBase(int idBase) {
        this.idBase = idBase;
    }

    public ArrayList<Brigadista> getBrigadistas() {
        return this.brigadistas;
    }

    public void setBrigadistas(ArrayList<Brigadista> brigadista) {
        this.brigadistas = brigadista;
    }
    
    public Brigadista getBrigadistaByRutAndBaseId(ArrayList<Base> data,int index, String rut){
        
        Brigadista brig = null;
        ArrayList<Brigadista> aux = new ArrayList<Brigadista>();
        aux = data.get(index-1).getBrigadistas();
        
        System.out.println("el numero total de brigadistas para esta base es: "+aux.size());
        
        //en el siguiente bucle buscamos el rut que clickeó el usuario
        for(int i=0;i<aux.size();i++){
            System.out.println(aux.get(i).getName());
            if(aux.get(i).getRut().equals(rut)){
                return aux.get(i);
                
            }
        }
        return null;
    }
}
