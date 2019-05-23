/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Proyecto;
import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;



/**
 * FXML Controller class
 *
 * @author johann.montoya
 */
public class ProponentesController implements Initializable {
    Usuario user = new Usuario(3, "marlonc98", "1234", "marlonc98", 0, 0, 0, 0);
        
    @FXML
    private TableColumn pId, pNombre, pNombre_proyecto, 
            pTelefono, pCalificacion, pIdp;
    
    @FXML
    private ChoiceBox pRegistro;
    
    @FXML
    private TableView<Usuario> pTable;
    
    @FXML
    private Button pCulminar, pSelect;
//        
    LinkedList<Proyecto> proyectos = new LinkedList<>();
    ObservableList<String> obs = FXCollections.observableArrayList();
    LinkedList<Usuario> usuarios = new LinkedList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarProyectos();
        addListenerChoice();
        loadPropuestos();
    }
    
    void cargarProyectos(){
        String sql = "call getProjects("+user.getId()+")";
        Conectar cd = new Conectar();
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                Statement st = cd.getSt();
                ResultSet rs = st.executeQuery(sql);
                obs.add("Cualquiera");
                while(rs.next()){
                    Proyecto pp = new Proyecto();
                    pp.setId(rs.getInt("id"));
                    pp.setNombre(rs.getString("nombre"));
                    pp.setEstado_id(rs.getString("estado"));
                    proyectos.add(pp);
                    obs.add(pp.getNombre());
                }
                pRegistro.setItems(obs);
                pRegistro.setValue("Cualquiera");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProponentesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void addListenerChoice(){
        pRegistro.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                loadPropuestos();
            }
        });
    }

    void loadPropuestos(){
        String sql;
        int toSend = 0;
        String choVal = pRegistro.getValue().toString();
        if(choVal.equals("Cualquiera")){
            toSend = user.getId();
            sql = "call getAllUsarios_propuestos("+toSend+")";
            pSelect.setDisable(false);
            pCulminar.setDisable(false);
        }else{
            //calcular toSend
            for(Proyecto proyecto: proyectos){
                if(proyecto.getNombre().equals(choVal)){
                    toSend = proyecto.getId();
                    System.out.println(proyecto.getEstado_id());
                    if(proyecto.getEstado_id().equals("disponible")){
                        pSelect.setDisable(false);
                        pCulminar.setDisable(true);
                    }else{
                        pSelect.setDisable(true);
                        pCulminar.setDisable(false);
                    }
                    break;
                }
            }
            sql = " call getUsarios_propuestos("+toSend+")";
        }
        
        //sql
        Conectar cd = new Conectar();
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                Statement st = cd.getSt();
                ResultSet rs = st.executeQuery(sql);
                usuarios.clear();
                while(rs.next()){
                    Usuario user = new Usuario();
                    user.setId(rs.getInt("userId"));
                    user.setNombre(rs.getString("userNombre"));
                    user.setCalificacion(rs.getDouble("calificacion"));
                    user.setId_proyecto(rs.getInt("id"));
                    user.setNombre_proyecto(rs.getString("nombre"));
                    usuarios.add(user);
                }
                ObservableList<Usuario> userOb = FXCollections.observableArrayList(usuarios);
                pId.setCellValueFactory(new PropertyValueFactory("id"));
                pCalificacion.setCellValueFactory(new PropertyValueFactory("calificacion"));
                pIdp.setCellValueFactory(new PropertyValueFactory("id_proyecto"));
                pNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
                pNombre_proyecto.setCellValueFactory(new PropertyValueFactory("nombre_proyecto"));
                pTelefono.setCellValueFactory(new PropertyValueFactory("phone"));
                pTable.setItems(userOb);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProponentesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //To change body of generated methods, choose Tools | Templates.
    
         @FXML
    private void atras(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/Vista/Inicio.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

       
    @FXML
    private void culminP(ActionEvent event){
        boolean hecho = false;
        String sql = "call completProject(?, ?, ?, ?)";
        Usuario us = pTable.getSelectionModel().getSelectedItem();
        double cali = 0;
        
        //set estad
        String estado = "";
        for(Proyecto proyecto: proyectos){
            if(proyecto.getId() == us.getId_proyecto()){
                estado = proyecto.getEstado_id();
                break;
            }
        }
        if(estado.equals("")){
            JOptionPane.showMessageDialog(null, "Error interno");
            return;
        }
        if(estado.equals("disponible")){
            JOptionPane.showMessageDialog(null, "El estado aun no ha sido asignado a un usuario");
            return;
        }
        do{
            cali = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la calificación para el usuario \n debe ser entre 1 y 5"));
        }while(cali > 5 || cali < 1);
        int totalCali = us.getTotal_proyectos() + 1;
        cali = ((us.getCalificacion()*us.getTotal_proyectos())+cali)/totalCali;
        Conectar cd = new Conectar();
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                PreparedStatement ps = cd.getConexion().prepareStatement(sql);
                ps.setInt(1, us.getId());
                ps.setInt(2, us.getId_proyecto());
                ps.setDouble(3, cali);
                ps.setInt(4, totalCali);
                ps.executeUpdate();
                cd.getConexion().commit();
                hecho = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProponentesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(hecho){
            cargarProyectos();
            loadPropuestos();
            JOptionPane.showMessageDialog(null, "Hecho, gracias por realizar el proyecto con nosotros");
        }else{
            JOptionPane.showMessageDialog(null, "Ocurrio un error");
        }
    }
    
    @FXML
    private void darT(ActionEvent event){
        boolean hecho = false;
        String sql = "call darProj(?, ?)";
        Usuario us = pTable.getSelectionModel().getSelectedItem();
        
        //set estado
        String estado = "";
        for(Proyecto proyecto: proyectos){
            if(proyecto.getId() == us.getId_proyecto()){
                estado = proyecto.getEstado_id();
                break;
            }
        }
        if(estado.equals("")){
            JOptionPane.showMessageDialog(null, "Error interno");
            return;
        }
        if(estado.equals("realizando")){
            JOptionPane.showMessageDialog(null, "El estado ya se encuentra asignado a un usuario");
            return;
        }
        
        //sql
        Conectar cd = new Conectar();
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                PreparedStatement ps = cd.getConexion().prepareCall(sql);
                ps.setInt(1, us.getId());
                ps.setInt(2, us.getId_proyecto());
                ps.executeUpdate();
                cd.getConexion().commit();
                hecho = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProponentesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(hecho){
            cargarProyectos();
            loadPropuestos();
            JOptionPane.showMessageDialog(null, "Se ha asignado al usuario " + us.getNombre());
        }else{
            JOptionPane.showMessageDialog(null, "Ocurrio un error");
        }

    }
    
}

