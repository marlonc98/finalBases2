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
public class Denuncia {

    String id, denunciante_id, denunciado_id, razon, descripcion;

    public Denuncia() {
    }

    public Denuncia(String id, String denunciante_id, String denunciado_id, String razon, String descripcion) {
        this.id = id;
        this.denunciante_id = denunciante_id;
        this.denunciado_id = denunciado_id;
        this.razon = razon;
        this.descripcion = descripcion;
    }

    public Denuncia(String id, String denunciante_id, String denunciado_id, String razon) {
        this.id = id;
        this.denunciante_id = denunciante_id;
        this.denunciado_id = denunciado_id;
        this.razon = razon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDenunciante_id() {
        return denunciante_id;
    }

    public void setDenunciante_id(String denunciante_id) {
        this.denunciante_id = denunciante_id;
    }

    public String getDenunciado_id() {
        return denunciado_id;
    }

    public void setDenunciado_id(String denunciado_id) {
        this.denunciado_id = denunciado_id;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    
    @Override
    public String toString() {
        return "Denuncia{" + "id=" + id + ", denunciante_id=" + denunciante_id + ", denunciado_id=" + denunciado_id + ", razon=" + razon + ", descripcion=" + descripcion + '}';
    }
    
   
    
 public boolean insertarDenuncia(String sql) {
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

    public boolean insertarDenunciaImagen(Denuncia obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
