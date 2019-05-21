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
public class Usuario {
    String  user_log, password_log,correo, nombre, empresa;
    int id, phone, id_empresa,total_proyectos;
    double calificacion;

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
        
    public Usuario(int id, String user_log, String password_log, String correo, int phone, int id_empresa, double calificacion, int total_proyectos) {
        this.id = id;
        this.user_log = user_log;
        this.password_log = password_log;
        this.correo = correo;
        this.phone = phone;
        this.id_empresa = id_empresa;
        this.calificacion = calificacion;
        this.total_proyectos = total_proyectos;
    }

    public Usuario(String nombre, String empresa, int id, int phone, int total_proyectos, double calificacion) {
        this.nombre = nombre;
        this.empresa = empresa;
        this.id = id;
        this.phone = phone;
        this.total_proyectos = total_proyectos;
        this.calificacion = calificacion;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_log() {
        return user_log;
    }

    public void setUser_log(String user_log) {
        this.user_log = user_log;
    }

    public String getPassword_log() {
        return password_log;
    }

    public void setPassword_log(String password_log) {
        this.password_log = password_log;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public int getTotal_proyectos() {
        return total_proyectos;
    }

    public void setTotal_proyectos(int total_proyectos) {
        this.total_proyectos = total_proyectos;
    }

    @Override
    public String toString() {
        return "Usuario{" + "user_log=" + user_log + ", password_log=" + password_log + ", correo=" + correo + ", nombre=" + nombre + ", empresa=" + empresa + ", id=" + id + ", phone=" + phone + ", id_empresa=" + id_empresa + ", total_proyectos=" + total_proyectos + ", calificacion=" + calificacion + '}';
    }
    
    
    
    
    
}