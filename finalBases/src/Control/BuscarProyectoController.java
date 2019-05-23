/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Ciudad;
import Modelo.Departamento;
import Modelo.Proyecto;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ganta
 */
public class BuscarProyectoController implements Initializable {
    
    @FXML
    private ChoiceBox<String> btDepartamento, btCiudad;
    
    @FXML
    private TextField btCedula;
    @FXML
    private TableView<Proyecto> btTable;

    @FXML
    private TableColumn btId, btNombre, btEncargado, btPresupuesto, btCity;    
    
    LinkedList<Departamento> departamentos = new LinkedList<>();
    LinkedList<Ciudad> ciudades = new LinkedList<>();
    ObservableList<Proyecto> obslist = FXCollections.observableArrayList();
    LinkedList<Proyecto> allProjects = new LinkedList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadProjects("call getAllProjects()");
        cargarDepartamentos();
        addListenerChoice();
    }

    @FXML
    private void atras(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/Vista/Inicio.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    
    void loadProjects(String sql){
        boolean made = false;
        Conectar cd = new Conectar();
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                Statement st = cd.getSt();
                ResultSet rs = st.executeQuery(sql);
                allProjects.clear();
                while(rs.next()){
                    Proyecto np = new Proyecto();
                    np.setId(rs.getInt("id"));
                    np.setNombre(rs.getString("nombre"));
                    np.setCiudad(rs.getString("ciudad"));
                    np.setPresupuesto(rs.getDouble("presupuesto"));
                    np.setEncargado(rs.getString("userName"));
                    allProjects.add(np);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuscarProyectoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mostrarProyectos(allProjects);
    }
    
    @FXML
    private void filtrar(ActionEvent event){
        filtrar();
    }
    
    void filtrar(){
        String filtrado = btCedula.getText();
        int cityId = getCityId();
        if(cityId != 0){
            String sql = "call getProjectsByCity("+cityId+")";
            loadProjects(sql);
        }else{
            loadProjects("call getAllProjects()");
        }
        LinkedList<Proyecto> nList = new LinkedList<>();
        for(Proyecto uss : allProjects){
            if(uss.toString().contains(filtrado)){
                nList.add(uss);
            }
        }
        mostrarProyectos(nList);
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
        String depar = btDepartamento.getValue();
        if(depar.equals("Seleccione Departamento")){
            return numCity;
        }
        String idCity = btCiudad.getValue();
        //leer ciudad
        for(Ciudad ciudad: ciudades){
            if(ciudad.getNombre().equals(idCity)){
                return ciudad.getId();
            }
        }
        return numCity;
    }

    void mostrarProyectos(LinkedList<Proyecto> proyectos){
        ObservableList<Proyecto> showProject = FXCollections.observableArrayList(proyectos);
        btId.setCellValueFactory(new PropertyValueFactory("id"));
        btCity.setCellValueFactory(new PropertyValueFactory("ciudad"));
        btEncargado.setCellValueFactory(new PropertyValueFactory("encargado"));
        btNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        btPresupuesto.setCellValueFactory(new PropertyValueFactory("presupuesto"));
        
        btTable.setItems(showProject);
    }
    
    @FXML
    void detallar(ActionEvent event){
        try{
            Proyecto pp = btTable.getSelectionModel().getSelectedItem();
            int idP = pp.getId();
            System.out.println(idP);
            
        }catch(NullPointerException n){
            JOptionPane.showMessageDialog(null, "No se has seleccionado ningun proyecto");
        }
        
    }
}
