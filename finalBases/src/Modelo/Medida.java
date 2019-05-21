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
public class Medida {
    
    String id, nombre, Breviacion;

    public Medida() {
    }
    

    public Medida(String id, String nombre, String Breviacion) {
        this.id = id;
        this.nombre = nombre;
        this.Breviacion = Breviacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBreviacion() {
        return Breviacion;
    }

    public void setBreviacion(String Breviacion) {
        this.Breviacion = Breviacion;
    }

    @Override
    public String toString() {
        return "Medida{" + "id=" + id + ", nombre=" + nombre + ", Breviacion=" + Breviacion + '}';
    }
     public boolean insertarMedida(String sql) {
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

    public boolean insertarAlquiler_maquinaImagen(Medida obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean insertarMedidaImagen(Medida obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
