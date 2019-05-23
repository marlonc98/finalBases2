/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Control.Conectar;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author johann.montoya
 */
public class Proyecto {
    
    String nombre, direccion, estado_id, contratante_id,
            contatado_id, opinion_contratante, opinion_contratado,
            calificacion_contratante, calificacion_contratado, ciudad, 
            foto_inicial, foto_final, descriccion;
    int id;
    double presupuesto;
    String encargado;

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }
    
    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }
   
    

    public Proyecto() {
    }

    public Proyecto(int id, String nombre, String direccion, String estado_id, String contratante_id, String contatado_id, String opinion_contratante, String opinion_contratado, String calificacion_contratante, String calificacion_contratado, String ciudad, String foto_inicial, String foto_final, String descriccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estado_id = estado_id;
        this.contratante_id = contratante_id;
        this.contatado_id = contatado_id;
        this.opinion_contratante = opinion_contratante;
        this.opinion_contratado = opinion_contratado;
        this.calificacion_contratante = calificacion_contratante;
        this.calificacion_contratado = calificacion_contratado;
        this.ciudad = ciudad;
        this.foto_inicial = foto_inicial;
        this.foto_final = foto_final;
        this.descriccion = descriccion;
    }

    public Proyecto(int id, String nombre, String direccion, String estado_id, String contratante_id, String contatado_id, String ciudad, String descriccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estado_id = estado_id;
        this.contratante_id = contratante_id;
        this.contatado_id = contatado_id;
        this.ciudad = ciudad;
        this.descriccion = descriccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(String estado_id) {
        this.estado_id = estado_id;
    }

    public String getContratante_id() {
        return contratante_id;
    }

    public void setContratante_id(String contratante_id) {
        this.contratante_id = contratante_id;
    }

    public String getContatado_id() {
        return contatado_id;
    }

    public void setContatado_id(String contatado_id) {
        this.contatado_id = contatado_id;
    }

    public String getOpinion_contratante() {
        return opinion_contratante;
    }

    public void setOpinion_contratante(String opinion_contratante) {
        this.opinion_contratante = opinion_contratante;
    }

    public String getOpinion_contratado() {
        return opinion_contratado;
    }

    public void setOpinion_contratado(String opinion_contratado) {
        this.opinion_contratado = opinion_contratado;
    }

    public String getCalificacion_contratante() {
        return calificacion_contratante;
    }

    public void setCalificacion_contratante(String calificacion_contratante) {
        this.calificacion_contratante = calificacion_contratante;
    }

    public String getCalificacion_contratado() {
        return calificacion_contratado;
    }

    public void setCalificacion_contratado(String calificacion_contratado) {
        this.calificacion_contratado = calificacion_contratado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFoto_inicial() {
        return foto_inicial;
    }

    public void setFoto_inicial(String foto_inicial) {
        this.foto_inicial = foto_inicial;
    }

    public String getFoto_final() {
        return foto_final;
    }

    public void setFoto_final(String foto_final) {
        this.foto_final = foto_final;
    }

    public String getDescriccion() {
        return descriccion;
    }

    public void setDescriccion(String descriccion) {
        this.descriccion = descriccion;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", estado_id=" + estado_id + ", contratante_id=" + contratante_id + ", contatado_id=" + contatado_id + ", opinion_contratante=" + opinion_contratante + ", opinion_contratado=" + opinion_contratado + ", calificacion_contratante=" + calificacion_contratante + ", calificacion_contratado=" + calificacion_contratado + ", ciudad=" + ciudad + ", foto_inicial=" + foto_inicial + ", foto_final=" + foto_final + ", descriccion=" + descriccion + '}';
    }
            
    
     public boolean insertarProyecto(String sql) {
          Conectar objCon = new Conectar();

        if (objCon.crearConexion()) {
            try {
                Statement sentencia = objCon.getConexion().createStatement();
                sentencia.executeUpdate(sql);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }    

    public boolean insertarProyectoImagen(Proyecto obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
