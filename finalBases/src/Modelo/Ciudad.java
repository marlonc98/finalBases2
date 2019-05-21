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
public class Ciudad {
    
    String nombre; 
    int id,  id_departameto;

    public Ciudad() {
    }

    public Ciudad(String nombre, int id, int id_departameto) {
        this.nombre = nombre;
        this.id = id;
        this.id_departameto = id_departameto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_departameto() {
        return id_departameto;
    }

    public void setId_departameto(int id_departameto) {
        this.id_departameto = id_departameto;
    }


    @Override
    public String toString() {
        return "Ciudad{" + "id=" + id + ", nombre=" + nombre + ", id_departameto=" + id_departameto + '}';
    }
    
      public boolean insertarCiudad(String sql) {
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

    public boolean insertarCiudadImagen(Ciudad obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
