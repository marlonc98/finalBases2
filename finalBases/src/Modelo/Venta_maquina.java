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
public class Venta_maquina {
    
    String id, vendedor, precio, descripcion, fotografia, maquina;

    public Venta_maquina() {
    }

    public Venta_maquina(String id, String vendedor, String precio, String descripcion, String fotografia, String maquina) {
        this.id = id;
        this.vendedor = vendedor;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fotografia = fotografia;
        this.maquina = maquina;
    }

    public Venta_maquina(String id, String precio, String maquina) {
        this.id = id;
        this.precio = precio;
        this.maquina = maquina;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
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

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    @Override
    public String toString() {
        return "Venta_maquina{" + "id=" + id + ", vendedor=" + vendedor + ", precio=" + precio + ", descripcion=" + descripcion + ", fotografia=" + fotografia + ", maquina=" + maquina + '}';
    }
    
     public boolean insertarVenta_maquina(String sql) {
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

    public boolean insertarVenta_maquinaImagen(Venta_maquina obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
