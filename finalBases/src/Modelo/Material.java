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
public class Material {
    
    String id, nombre, referencia,foto, medida, tipo;

    public Material() {
    }

    public Material(String id, String nombre, String referencia, String fot, String medida, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.referencia = referencia;
        this.foto = fot;
        this.medida = medida;
        this.tipo = tipo;
    }

    public Material(String id, String nombre, String fot, String medida, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.medida = medida;
        this.tipo = tipo;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Material{" + "id=" + id + ", nombre=" + nombre + ", referencia=" + referencia + ", foto=" + foto + ", medida=" + medida + ", tipo=" + tipo + '}';
    }
     public boolean insertarMaterial(String sql) {
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

    public boolean insertarMaterialImagen(Material obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
