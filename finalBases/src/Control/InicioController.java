/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

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
 * @author Ganta
 */
public class InicioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("entra");
    }    

    @FXML
    private void InsertProyecto(ActionEvent event) {
        cambiarPestana(event, "Insertar Proyecto");
    }

    @FXML
    private void InsertTrabajador(ActionEvent event) {
        cambiarPestana(event, "Insertar Trabajador");
    }

    @FXML
    private void addCerti(ActionEvent event){
        cambiarPestana(event, "addCertificado");
    }
    @FXML
    private void BusqTraba(ActionEvent event) {
        cambiarPestana(event, "Buscar trabajador");
    }

    @FXML
    private void BusqProy(ActionEvent event) {
        cambiarPestana(event, "Buscar proyecto");
    }
    
    @FXML
    private void ingUser(ActionEvent event){
        cambiarPestana(event, "Empresas");
    }
    
    @FXML
    private void insertUserEmpresa(ActionEvent event){
        cambiarPestana(event, "usEmpresa");
    }
    
    @FXML
    private void tusProyectos(ActionEvent event){
        System.out.println("loogg");
        cambiarPestana(event, "proponentes");
    }
    
    void cambiarPestana(ActionEvent event,String url){
        System.out.println("entraaaaaa");
        try {
            Parent buscar_trabajador_parent = FXMLLoader.load(getClass().getResource("/Vista/"+url+".fxml"));
            Scene buscar_trabajos_scene = new Scene(buscar_trabajador_parent);
            Stage main_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            main_stage.setScene(buscar_trabajos_scene);
            main_stage.show();
            System.out.println("su mama");
        } catch (IOException ex) {
            System.out.println("su papa");
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
