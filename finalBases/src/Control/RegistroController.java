/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Ciudad;
import Modelo.Departamento;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ganta
 */
public class RegistroController implements Initializable {
       
    @FXML
    private TextField registroCedula, registroUsuario, registroNombre, 
            registroApellido, registroTelefono;
    
    @FXML
    private PasswordField registroContrasena;
    
    @FXML
    private DatePicker registroNacimiento;
//    
    @FXML
    private ChoiceBox registroDepartamento, registroCiudad;
    
    LinkedList<Departamento> departamentos = new LinkedList<>();
    LinkedList<Ciudad> ciudades = new LinkedList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("entraaa");
//        cargarDepartamentos();
 //       addListenerChoice();
    }    

    @FXML
    private void IrRegistro(ActionEvent event) {
        //lectura
        int cedula = Integer.parseInt(registroCedula.getText());
        String usuario = registroUsuario.getText();
        String contrasena = registroContrasena.getText();
        String nombre = registroNombre.getText();
        String apellido = registroApellido.getText();
        int telefono = Integer.parseInt( registroTelefono.getText());
        Date nacimiento = Date.valueOf(registroNacimiento.getValue());

        String ciudadN = registroCiudad.getValue().toString();
        int numCity = 0;
        //leer ciudad
        for(Ciudad ciudad: ciudades){
            if(ciudad.getNombre().equals(ciudadN)){
                numCity = ciudad.getId();
            }
        }
        
        //registro
        Conectar cd = new Conectar();
        boolean registrado = false;
        try{
            if(cd.crearConexion()){
                Statement st = cd.getConexion().createStatement();
                //add_user(in icedula int(11), in iuser varchar(45), in ipasswordlog varchar(45),
                //in inombre varchar(45), in iapellido varchar(45), in ibornday date, in city int,
                //in iphone int)
                String sqsl = "call add_user("+cedula+",'"+usuario+"','"+contrasena+"','"+
                        nombre +"','" + apellido + "','" +nacimiento + "',"+3 +"," +telefono+")";
                st.execute(sqsl);
                registrado = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se creo el usuario");
        }
        if(registrado){
            JOptionPane.showMessageDialog(null, "Creado exitosamente");
        }
    }
    
    @FXML
    private void cargarDepartamentos(){
        //primero leemos los existentes en mysql
        List<String> lista = new ArrayList<String>();
        Conectar cd = new Conectar();
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                Statement st = cd.getSt();
                String sql = "SELECT * FROM departamento";
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    //carga departamento
                    String nombre = rs.getString("nombre");
                    int id = rs.getInt("id");
                    Departamento nuevo = new Departamento(id, nombre);
                    lista.add(nombre);
                    departamentos.add(nuevo);
                    
                }
                if(departamentos.size() > 0){
                    registroDepartamento.setValue(departamentos.get(0).getNombre());
                    cargarCiudad();
                }
                ObservableList areList = FXCollections.observableList(lista);
                registroDepartamento.setItems(areList);
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                ObservableList areList = FXCollections.observableList(lista);
                registroDepartamento.setItems(areList);
        }

    }
    
    @FXML
    private void cargarCities(ActionEvent event){
        cargarCiudad();
    }
    
    
    @FXML
    private void cargarCiudad(){
        //primero obtenemos el value actual de el choice
        String depVal = registroDepartamento.getValue().toString();
        int depInt = 0;
        for(Departamento departamento: departamentos){
            if(departamento.getNombre() == null ? depVal == null : departamento.getNombre().equals(depVal)){
                depInt = departamento.getId();
            }
        }
        
        ciudades.clear();
        
        //buscamos las ciudades con la id previa
        List<String> lista = new ArrayList<String>();
        Conectar cd = new Conectar();
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                Statement st = cd.getSt();
                String sql = "call getCitiesByDepartment("+depInt+");";
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    //carga departamento
                    String nombre = rs.getString("nombre");
                    int id = rs.getInt("id");
                    Ciudad nuevo = new Ciudad(nombre, id, depInt);
                    lista.add(nombre);
                    ciudades.add(nuevo);
                    
                }
                ObservableList areList = FXCollections.observableList(lista);
                
                registroCiudad.setItems(areList);
                if(ciudades.size() > 0){
                    registroCiudad.setValue(ciudades.get(0).getNombre());
                    registroCiudad.setDisable(false);
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void addListenerChoice() {
        registroDepartamento.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                cargarCiudad();
            }
        });
    }
}
