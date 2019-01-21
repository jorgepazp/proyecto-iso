/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author pazjo
 */
public class Asistencia {
    private int _idAsistencia;
    private String _rut;
    private Date _inicio;
    private Date _fin;
    private boolean activo;
    private String nota;

    public Asistencia(int id, String rut, Date ini, Date fin, String note){
        this._idAsistencia=id;
        this._rut = rut;
        this._inicio = ini;
        this._fin = fin;
        this.nota = note;
    }
    public String getFechaAsistencia(){
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        return df.format(this._inicio);
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }



    public int getIdAsistencia() {
        return _idAsistencia;
    }

    public void setIdAsistencia(int _idAsistencia) {
        this._idAsistencia = _idAsistencia;
    }

    public String getRut() {
        return _rut;
    }

    public void setRut(String _rut) {
        this._rut = _rut;
    }


    public Date getInicio() {
        return _inicio;
    }

    public void setInicio(Date _inicio) {
        this._inicio = _inicio;
    }

    public Date getFin() {
        return _fin;
    }

    public void setFin(Date _fin) {
        this._fin = _fin;
    }

    public String getHorarioDeInicio(){
        
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(_inicio);
    }
    
    /*
    El siguiente metodo se diferencia en el tipo de fecha que utiliza, pues devuelve la fecha pero en formato
    dia, mes , año
    */
    public String getFechaDeInicio(){
        
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(_inicio);
    }
    
    
    public String getHorarioDeFin(){
        
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(_fin);
    }
    
    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
    
    public int getId(){
        return this._idAsistencia;
    }
}
