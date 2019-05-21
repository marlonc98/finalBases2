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
public class Negocio {
    
    String id, comprador, vendedor,estado, fecha_inicio, fecha_culminado, 
            id_vmaq, id_vmat, id_amaq;

    public Negocio() {
    }

    public Negocio(String id, String comprador, String estado, String fecha_inicio, String fecha_culminado, String id_vmaq, String id_vmat, String id_amaq) {
        this.id = id;
        this.comprador = comprador;
        this.estado = estado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_culminado = fecha_culminado;
        this.id_vmaq = id_vmaq;
        this.id_vmat = id_vmat;
        this.id_amaq = id_amaq;
    }

    public Negocio(String id, String comprador, String vendedor, String estado, String fecha_inicio) {
        this.id = id;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.estado = estado;
        this.fecha_inicio = fecha_inicio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_culminado() {
        return fecha_culminado;
    }

    public void setFecha_culminado(String fecha_culminado) {
        this.fecha_culminado = fecha_culminado;
    }

    public String getId_vmaq() {
        return id_vmaq;
    }

    public void setId_vmaq(String id_vmaq) {
        this.id_vmaq = id_vmaq;
    }

    public String getId_vmat() {
        return id_vmat;
    }

    public void setId_vmat(String id_vmat) {
        this.id_vmat = id_vmat;
    }

    public String getId_amaq() {
        return id_amaq;
    }

    public void setId_amaq(String id_amaq) {
        this.id_amaq = id_amaq;
    }

    @Override
    public String toString() {
        return "Negocio{" + "id=" + id + ", comprador=" + comprador + ", vendedor=" + vendedor + ", estado=" + estado + ", fecha_inicio=" + fecha_inicio + ", fecha_culminado=" + fecha_culminado + ", id_vmaq=" + id_vmaq + ", id_vmat=" + id_vmat + ", id_amaq=" + id_amaq + '}';
    }
    
      public boolean insertarNegocio(String sql) {
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

    public boolean insertarNegocioImagen(Negocio obje, String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
