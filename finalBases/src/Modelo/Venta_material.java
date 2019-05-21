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
public class Venta_material {
    
    String id, precio, descripcion, fotografia, material, usuarion;

    public Venta_material() {
    }

    public Venta_material(String id, String precio, String descripcion, String fotografia, String material, String usuarion) {
        this.id = id;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fotografia = fotografia;
        this.material = material;
        this.usuarion = usuarion;
    }

    public Venta_material(String id, String precio, String material) {
        this.id = id;
        this.precio = precio;
        this.material = material;
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getUsuarion() {
        return usuarion;
    }

    public void setUsuarion(String usuarion) {
        this.usuarion = usuarion;
    }

    @Override
    public String toString() {
        return "Venta_material{" + "id=" + id + ", precio=" + precio + ", descripcion=" + descripcion + ", fotografia=" + fotografia + ", material=" + material + ", usuarion=" + usuarion + '}';
    }
    
     public boolean insertarVenta_material(String sql) {
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

    public boolean insertarVenta_materialImagen(Venta_material obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
