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
public class Certificado_nombre {
        String nombre;
        int id;

    public Certificado_nombre() {
    }

    public Certificado_nombre(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Tipo_material{" + "id=" + id + ", nombre=" + nombre + '}';
    }
    
    
     public boolean insertarCertificado_nombre(String sql) {
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

    public boolean insertarAlquiler_maquinaImagen(Certificado_nombre obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
