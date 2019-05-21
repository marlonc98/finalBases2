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
public class Ubicacion {
    
    String id, direccion, ciudad, empresa, telefono;

    public Ubicacion() {
    }

    public Ubicacion(String id, String direccion, String ciudad, String empresa, String telefono) {
        this.id = id;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.empresa = empresa;
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Ubicacion{" + "id=" + id + ", direccion=" + direccion + ", ciudad=" + ciudad + ", empresa=" + empresa + ", telefono=" + telefono + '}';
    }
    
      public boolean insertarUbicacion(String sql) {
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

    public boolean insertarUbicacionImagen(Ubicacion obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
