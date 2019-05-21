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
public class Resena {
    
    String id, id_realiza, id_venta_maquina,id_venta_material, 
            id_alquiler_maquina,comentario,calificacion;

    public Resena() {
    }

    public Resena(String id, String id_realiza, String id_venta_maquina, String id_venta_material, String id_alquiler_maquina, String comentario, String calificacion) {
        this.id = id;
        this.id_realiza = id_realiza;
        this.id_venta_maquina = id_venta_maquina;
        this.id_venta_material = id_venta_material;
        this.id_alquiler_maquina = id_alquiler_maquina;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public Resena(String id, String id_realiza, String comentario, String calificacion) {
        this.id = id;
        this.id_realiza = id_realiza;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_realiza() {
        return id_realiza;
    }

    public void setId_realiza(String id_realiza) {
        this.id_realiza = id_realiza;
    }

    public String getId_venta_maquina() {
        return id_venta_maquina;
    }

    public void setId_venta_maquina(String id_venta_maquina) {
        this.id_venta_maquina = id_venta_maquina;
    }

    public String getId_venta_material() {
        return id_venta_material;
    }

    public void setId_venta_material(String id_venta_material) {
        this.id_venta_material = id_venta_material;
    }

    public String getId_alquiler_maquina() {
        return id_alquiler_maquina;
    }

    public void setId_alquiler_maquina(String id_alquiler_maquina) {
        this.id_alquiler_maquina = id_alquiler_maquina;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "Resena{" + "id=" + id + ", id_realiza=" + id_realiza + ", id_venta_maquina=" + id_venta_maquina + ", id_venta_material=" + id_venta_material + ", id_alquiler_maquina=" + id_alquiler_maquina + ", comentario=" + comentario + ", calificacion=" + calificacion + '}';
    }
    
     public boolean insertarResena(String sql) {
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

    public boolean insertarResenaImagen(Resena obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
