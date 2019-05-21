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
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author johann.montoya
 */
public class ProponentesController implements Initializable {
    
    
    
    
    @FXML
    private TableColumn id, nombre, nombre_proyecto, 
            telefono, calificacion;
    
    @FXML
    private ChoiceBox registro;
    
    @FXML
    private Button atras, selecionarTrabajador;
//    
   
    
    LinkedList<Proyecto> proyectos = new LinkedList<>();
    LinkedList<Usuario> usuarios = new LinkedList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //To change body of generated methods, choose Tools | Templates.
    
     
       
    
}
