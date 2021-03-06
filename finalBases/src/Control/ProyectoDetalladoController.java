/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marlon Méndez
 */
public class ProyectoDetalladoController implements Initializable {

    
    Usuario ObjU =  null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void obtenerProyecto(){}
    
    @FXML
    private void aceptar(ActionEvent event){
        
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
    
}
