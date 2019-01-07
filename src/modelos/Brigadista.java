/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author pazjo
 */
public class Brigadista {
    private String rut;
    private String name;
    private String telefono;
    private String nacimiento;
    private boolean wasReversed=false;
    
    public String getName() {
        return name;
    }
    
    public ArrayList<Asistencia> getAsistencias(){
        return this.asistencias;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigo_base() {
        return codigo_base;
    }

    public void setCodigo_base(int codigo_base) {
        this.codigo_base = codigo_base;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    private String nacionalidad;
    private String email;
    private String direccion;
    private int codigo_base;
    private String uuid;
    private ArrayList<Asistencia> asistencias;
    
     public Brigadista(String rutBrigadista,String nombre,String telefono,String nac
             ,String nat,String correo,String _uuid,String dir,int cod){
        this.rut=rutBrigadista;
        this.name=nombre;
        this.telefono=telefono;
        this.nacimiento=nac;
        this.nacionalidad=nat;
        this.email=correo;
        this.uuid=_uuid;
        this.direccion=dir;
        this.codigo_base=cod;
        this.wasReversed = false;
    }
     
     public void setAsistencias(ArrayList<Asistencia> as){
         this.asistencias=as;
     }
    
     public String getRut(){
         return this.rut;
     }
    //public int idBrigadista;
     
     public ArrayList<Asistencia> getListaReversed(){
         ArrayList<Asistencia> aux = this.asistencias;
         
        if(!wasReversed){
        
         Collections.reverse(aux);
         this.wasReversed = true;
        }
         return aux;
     }
     
     public Asistencia getAsistenciaPorId(int id){
       return null;  
     }
}
