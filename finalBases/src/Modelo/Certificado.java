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
public class Certificado {
    
    String id, nombre, fecha_relizacion, empresa_realiza, usuarion;

    public Certificado() {
    }

    public Certificado(String id, String nombre, String fecha_relizacion, String empresa_realiza, String usuarion) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_relizacion = fecha_relizacion;
        this.empresa_realiza = empresa_realiza;
        this.usuarion = usuarion;
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

    public String getFecha_relizacion() {
        return fecha_relizacion;
    }

    public void setFecha_relizacion(String fecha_relizacion) {
        this.fecha_relizacion = fecha_relizacion;
    }

    public String getEmpresa_realiza() {
        return empresa_realiza;
    }

    public void setEmpresa_realiza(String empresa_realiza) {
        this.empresa_realiza = empresa_realiza;
    }

    public String getUsuarion() {
        return usuarion;
    }

    public void setUsuarion(String usuarion) {
        this.usuarion = usuarion;
    }

    @Override
    public String toString() {
        return "Certificado{" + "id=" + id + ", nombre=" + nombre + ", fecha_relizacion=" + fecha_relizacion + ", empresa_realiza=" + empresa_realiza + ", usuarion=" + usuarion + '}';
    }
    
     public boolean insertarCertificado(String sql) {
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

    public boolean insertarCertificadoImagen(Certificado obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
