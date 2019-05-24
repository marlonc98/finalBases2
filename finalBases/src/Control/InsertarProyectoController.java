/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Certificado;
import Modelo.Certificado_nombre;
import Modelo.Ciudad;
import Modelo.Departamento;
import Modelo.Usuario;
import com.mysql.jdbc.Blob;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.JOptionPane;
import static sun.security.krb5.Confounder.bytes;

/**
 * FXML Controller class
 *
 * @author Ganta
 */
public class InsertarProyectoController implements Initializable {
    Image img;
    LinkedList<Departamento> departamentos = new LinkedList<>();
    LinkedList<Ciudad> ciudades = new LinkedList<>();
    Usuario user = null;
    File image;
    
    @FXML
    ChoiceBox ppDepartamento, ppCiudad, ppVia;
    
    @FXML
    VBox vbCertificados;
    
    @FXML
    ImageView imgPrev;
    
    @FXML
    Button btUploadImg, changeImgBt;
    
    @FXML
    TextField ppNombre, ppDir1, ppDir2, ppDir3, ppPresupuesto;
    
    @FXML
    TextArea ppDescripcion;
    
    
    LinkedList<Certificado_nombre> certificados = new LinkedList<>();
    ObservableList<String> obCertificados;
    
    
    /**
     * Initializes the controller class.
     */

    public void initData(Usuario ObjU) {
        user = ObjU;
        loadCerti();
        loadTipoVias();
        anadir();
        cargarDepartamentos();
        addListenerChoice();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   public void importarVariables( Usuario ObjU ) {
      user = ObjU;
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
                    ppDepartamento.setValue(departamentos.get(0).getNombre());
                    cargarCiudad();
                }
                ObservableList areList = FXCollections.observableList(lista);
                ppDepartamento.setItems(areList);
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                ObservableList areList = FXCollections.observableList(lista);
                ppDepartamento.setItems(areList);
        }

    }

    private void loadTipoVias() {
        List<String> lista = new ArrayList<>();
        lista.add("Avenida");
        lista.add("Autopista");
        lista.add("Calle");
        lista.add("Carrera");
        ObservableList<String> obj = FXCollections.observableArrayList(lista);
        ppVia.setItems(obj);
        ppVia.setValue("Avenida");
    }
    
    @FXML
    private void cargarCities(ActionEvent event){
        cargarCiudad();
    }
    
    @FXML
    private void cargarCiudad(){
        //primero obtenemos el value actual de el choice
        String depVal = ppDepartamento.getValue().toString();
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
                
                ppCiudad.setItems(areList);
                if(ciudades.size() > 0){
                    ppCiudad.setValue(ciudades.get(0).getNombre());
                    ppCiudad.setDisable(false);
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void addListenerChoice() {
        ppDepartamento.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                cargarCiudad();
            }
        });
    }
    
    @FXML
    public void anadir(ActionEvent event){
        //primero obtenemos un list con todos los certificados
        anadir();
    }
    
    @FXML
    public void anadir(){
        ChoiceBox nuevo = new ChoiceBox();
        nuevo.setMinWidth(300);
        nuevo.setItems(obCertificados);
        vbCertificados.getChildren().add(nuevo);
        nuevo.setValue("Seleccione...");
   }
    
    @FXML
    public void eliminar(ActionEvent event){
        ObservableList<Node> obs = vbCertificados.getChildren();
        if(obs.size() > 1){
            vbCertificados.getChildren().remove(obs.size() - 1);
        }
    }

    @FXML
    public void selectImg(ActionEvent event){
        //abrir fileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All image", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        image = fileChooser.showOpenDialog(null);
        
        //hacemos preview
        if(image != null){
            img = new Image(image.toURI().toString());
            imgPrev.setImage(img);
            btUploadImg.setVisible(false);
            changeImgBt.setVisible(true);
            
        }else{
            btUploadImg.setVisible(true);
            changeImgBt.setVisible(false);
        }
//        imgPrev.setImage(new Image(img.getPath()));
    }
    
    private void loadCerti() {
        List<String> certifiA = new ArrayList<>();
        certifiA.add("Seleccione...");
        Conectar cd = new Conectar();
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                Statement st = cd.getSt();
                String sql = "call getCertificates()";
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    Certificado_nombre nuevo = new Certificado_nombre(id, nombre);
                    certificados.add(nuevo);
                    certifiA.add(nombre);
                }
                obCertificados = FXCollections.observableArrayList(certifiA);
    
            }
        } catch (SQLException ex) {
            Logger.getLogger(InsertarProyectoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void insertProject(ActionEvent event){
        try{
            //lectura de datos
            String nombre = ppNombre.getText();
            System.out.println(nombre);
            //leer ciudad
            String ciudadN = ppCiudad.getValue().toString();
            int numCity = 0;
            for(Ciudad ciudad: ciudades){
                if(ciudad.getNombre().equals(ciudadN)){
                    numCity = ciudad.getId();
                }
            }
            String direccion = ppVia.getValue().toString() + ppDir1.getText() + " - " + ppDir2.getText() + "#" + ppDir3.getText();
            String descripcion = ppDescripcion.getText();
            String sql = "call insertProject(?,?,?,?,?,?,?)";
            Double presupuesto = Double.parseDouble(ppPresupuesto.getText() );
            Conectar cd = new Conectar();
            boolean created = false;
            try {
                FileInputStream fis = new FileInputStream(image);
                if(cd.crearConexion()){
                    cd.getConexion().setAutoCommit(false);
                    PreparedStatement ps = cd.getConexion().prepareStatement(sql);
                    ps.setString(1, nombre);
                    ps.setString(2, direccion);
                    ps.setInt(3, user.getId());
                    ps.setInt(4, numCity);
                    ps.setBinaryStream(5, fis, (int)image.length());
                    ps.setString(6, descripcion);
                    ps.setDouble(7, presupuesto);
                    ps.executeUpdate();
                    cd.getConexion().commit();
                    insertProjCert();
                    created = true;
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(InsertarProyectoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(created){
                JOptionPane.showMessageDialog(null, "Ingresado");
            }else{
                JOptionPane.showMessageDialog(null, "No se creo");
            }
            //lectura certificados
            ObservableList<Node> nodos = vbCertificados.getChildren();
            
            //(nombre, direccion, contratante_id, ciudad, foto_inical, descripcion)
            
        } catch (SQLException ex) {
            Logger.getLogger(InsertarProyectoController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

    int getLastProj(){
        int idProj = 0;
        Conectar cd = new Conectar();
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                Statement st = cd.getSt();
                String sql = "call getLastProj()";
                ResultSet rs = st.executeQuery(sql);
                if(rs.next()){
                    idProj = rs.getInt("id");
                }
            }
        }   catch (SQLException ex) {
                Logger.getLogger(InsertarProyectoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        return idProj;
    }
    
    void insertProjCert(){
        ObservableList obsl = vbCertificados.getChildren();
        LinkedList<Integer> certificadosi = new LinkedList<>();
        //obtener id certificados
        for (int i = 0; i < obsl.size() ; i++){
            ChoiceBox<String> cb = (ChoiceBox<String>)obsl.get(i);
            for(Certificado_nombre certi: certificados){
                if(certi.getNombre().equals(cb.getValue()) && !certificadosi.contains(certi.getId())){
                    certificadosi.add(certi.getId());
                }
            }
            
        }
        
        //obtener ppL
        int proj = getLastProj();
        System.out.println(proj);
        
        //insertar certificados
        for(Integer intCert: certificadosi){
            Conectar cd = new Conectar();
            String sql = "call addCertificateLp(?,?)";
            try{
                if(cd.crearConexion()){
                    cd.getConexion().setAutoCommit(false);
                        PreparedStatement ps = cd.getConexion().prepareStatement(sql);
                        ps.setInt(1, intCert);
                        ps.setInt(2, proj);
                        ps.executeUpdate();
                        cd.getConexion().commit();
                }
            } catch (SQLException ex) {
                Logger.getLogger(InsertarProyectoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
    }
   @FXML
   private void atras(ActionEvent event){
           try {
            Parent buscar_trabajador_parent = FXMLLoader.load(getClass().getResource("/Vista/Inicio.fxml"));
            Scene buscar_trabajos_scene = new Scene(buscar_trabajador_parent);
            Stage main_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            main_stage.setScene(buscar_trabajos_scene);
            main_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

   }
   
    
}
