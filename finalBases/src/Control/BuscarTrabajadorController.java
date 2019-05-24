/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Ciudad;
import Modelo.Departamento;
import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ganta
 */
public class BuscarTrabajadorController implements Initializable {
    @FXML
    private ChoiceBox btDepartamento, btCiudad;

    @FXML
    private TableColumn bttId, bttNombre, bttTelefono, bttEmpresa, bttCalificacion, bttCantidad;
    
    @FXML
    private TableView btTable;
    
    @FXML
    private TextField btCedula;
    LinkedList<Departamento> departamentos = new LinkedList<>();
    LinkedList<Ciudad> ciudades = new LinkedList<>();
    Usuario user = null;
    Usuario ObjU =  null;
    LinkedList<Usuario> allUsers = new LinkedList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //carga todos los usuarios
        loadUsers("Select * from usuario");
        cargarDepartamentos();
        addListenerChoice();
    }    
    
    void loadUsers(String sql){
        Conectar cd = new Conectar();
        boolean obten = false;
        
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                Statement st = cd.getSt();
                ResultSet rs = st.executeQuery(sql);
                allUsers.clear();
                while(rs.next()){
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre") + " " + rs.getString("apellido");
                    int telefono = rs.getInt("phone");
                    int empresa = rs.getInt("id_empresa");
                    String nombreEmpresa;
                    if(empresa != 0){
                        nombreEmpresa = nombreEmpresa(empresa);
                    }else{
                        nombreEmpresa = "Ninguna";
                    }
                    double calificacion = rs.getDouble("calificacion");
                    int cantidad = rs.getInt("total_proyectos");
                    Usuario nus = new Usuario(nombre, nombreEmpresa, id, telefono, cantidad, calificacion);
                    allUsers.add(nus);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuscarTrabajadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mostrarUsuarios(allUsers);
    }
    
    String nombreEmpresa(int id){
        Conectar cd = new Conectar();
        String sql = "Select nombre from empresa";
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                Statement st = cd.getSt();
                ResultSet rs = st.executeQuery(sql);
                if(rs.next()){
                    return rs.getString("nombre");
                }else{
                    return "Ninguna";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuscarTrabajadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Ninguna";
    }
    void mostrarUsuarios(LinkedList<Usuario> usuarios){
        ObservableList<Usuario> showUser = FXCollections.observableArrayList(usuarios);
        bttNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        bttCalificacion.setCellValueFactory(new PropertyValueFactory("calificacion"));
        bttCantidad.setCellValueFactory(new PropertyValueFactory("total_proyectos"));
        bttEmpresa.setCellValueFactory(new PropertyValueFactory("empresa"));
        bttId.setCellValueFactory(new PropertyValueFactory("id"));
        bttTelefono.setCellValueFactory(new PropertyValueFactory("phone"));
        
        btTable.setItems(showUser);
    }
    
    @FXML
    void filtrar(ActionEvent event){
        filtrar();
    }
    void filtrar(){
        String filtrado = btCedula.getText();
        int cityId = getCityId();
        if(cityId != 0){
            String sql = "call getUserByCity("+cityId+")";
            loadUsers(sql);
        }else{
            loadUsers("Select * from usuario");
        }
        LinkedList<Usuario> nList = new LinkedList<>();
        for(Usuario uss : allUsers){
            if(uss.toString().contains(filtrado)){
                nList.add(uss);
            }
        }
        mostrarUsuarios(nList);
    }
   @FXML
   private void atras(ActionEvent event){
           try {
            FXMLLoader loader =  new FXMLLoader();
            loader.setLocation(getClass().getResource("/Vista/Inicio.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            
            InicioController inicioController = loader.getController();
            inicioController.initData(ObjU);

            Stage main_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            main_stage.setScene(scene);
            main_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
                    btDepartamento.setValue(departamentos.get(0).getNombre());
                }
                ObservableList areList = FXCollections.observableList(lista);
                areList.add("Seleccione Departamento");
                btDepartamento.setItems(areList);
                btDepartamento.setValue("Seleccione Departamento");
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                ObservableList areList = FXCollections.observableList(lista);
                btDepartamento.setItems(areList);
        }

    }
    
    @FXML
    private void cargarCities(ActionEvent event){
        cargarCiudad();
    }
    
    
    @FXML
    private void cargarCiudad(){
        //primero obtenemos el value actual de el choice
        String depVal = btDepartamento.getValue().toString();
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
                btCiudad.setItems(areList);
                if(ciudades.size() > 0){
                    btCiudad.setValue(ciudades.get(0).getNombre());
                    btCiudad.setDisable(false);
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void addListenerChoice() {
        btDepartamento.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    cargarCiudad();
                    filtrar();
            }
        });
        btCiudad.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                filtrar();
            }
        });
    }
    int getCityId(){
        int numCity = 0;
        String depar = btDepartamento.getValue().toString();
        if(depar.equals("Seleccione Departamento")){
            return numCity;
        }
        String idCity = btCiudad.getValue().toString();
        System.out.println("idCity" + idCity);
        //leer ciudad
        for(Ciudad ciudad: ciudades){
            if(ciudad.getNombre().equals(idCity)){
                return ciudad.getId();
            }
        }
        System.out.println("getCi" + numCity);
        return numCity;
    }


}

    