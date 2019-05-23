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
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class BuscarProyectoController implements Initializable {

    @FXML
    private TableView<Usuario> pTable;

    @FXML
    private TableColumn pId, pNombre, pTelefono, pCalificacion, pIdp, pNombre_proyecto;
    
    @FXML
    private ChoiceBox<String> pRegistro;

    @FXML
    private TextField Codigo;
            
    LinkedList<Departamento> departamentos = new LinkedList<>();
    LinkedList<Ciudad> ciudades = new LinkedList<>();
    ObservableList<Proyecto> obslist = FXCollections.observableArrayList();
    LinkedList<Proyecto> allProjects = new LinkedList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadProjects("Select * from proyecto");
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
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
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuscarProyectoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
