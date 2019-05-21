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
public class Alquiler_maquina {
    
    
    String id, precio,id_maquina, id_user, 
            descripcion, fotografia, cantidad_dias, negociable,
            descuento_por_mayor, descuento_tiempo;

    public Alquiler_maquina() {
    }

    public Alquiler_maquina(String id, String precio, String id_maquina, String id_user, String descripcion, String fotografia, String cantidad_dias, String negociable, String descuento_por_mayor, String descuento_tiempo) {
        this.id = id;
        this.precio = precio;
        this.id_maquina = id_maquina;
        this.id_user = id_user;
        this.descripcion = descripcion;
        this.fotografia = fotografia;
        this.cantidad_dias = cantidad_dias;
        this.negociable = negociable;
        this.descuento_por_mayor = descuento_por_mayor;
        this.descuento_tiempo = descuento_tiempo;
    }

    public Alquiler_maquina(String id, String precio, String id_maquina, String id_user, String descripcion, String cantidad_dias, String negociable, String descuento_por_mayor, String descuento_tiempo) {
        this.id = id;
        this.precio = precio;
        this.id_maquina = id_maquina;
        this.id_user = id_user;
        this.descripcion = descripcion;
        this.cantidad_dias = cantidad_dias;
        this.negociable = negociable;
        this.descuento_por_mayor = descuento_por_mayor;
        this.descuento_tiempo = descuento_tiempo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getId_maquina() {
        return id_maquina;
    }

    public void setId_maquina(String id_maquina) {
        this.id_maquina = id_maquina;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getCantidad_dias() {
        return cantidad_dias;
    }

    public void setCantidad_dias(String cantidad_dias) {
        this.cantidad_dias = cantidad_dias;
    }

    public String getNegociable() {
        return negociable;
    }

    public void setNegociable(String negociable) {
        this.negociable = negociable;
    }

    public String getDescuento_por_mayor() {
        return descuento_por_mayor;
    }

    public void setDescuento_por_mayor(String descuento_por_mayor) {
        this.descuento_por_mayor = descuento_por_mayor;
    }

    public String getDescuento_tiempo() {
        return descuento_tiempo;
    }

    public void setDescuento_tiempo(String descuento_tiempo) {
        this.descuento_tiempo = descuento_tiempo;
    }

    @Override
    public String toString() {
        return "Alquiler_maquina{" + "id=" + id + ", precio=" + precio + ", id_maquina=" + id_maquina + ", id_user=" + id_user + ", descripcion=" + descripcion + ", fotografia=" + fotografia + ", cantidad_dias=" + cantidad_dias + ", negociable=" + negociable + ", descuento_por_mayor=" + descuento_por_mayor + ", descuento_tiempo=" + descuento_tiempo + '}';
    }
    
    
     public boolean insertarAlquiler_maquina(String sql) {
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

    public boolean insertarAlquiler_maquinaImagen(Alquiler_maquina obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
