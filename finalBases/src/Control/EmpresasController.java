package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Marlon Méndez
 */
public class EmpresasController implements Initializable {
    
    
    Usuario ObjU =  null;
    @FXML
    private TextField regemId, regemNombre, regemAdmin;
    
    @FXML
    private PasswordField regemPassword;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("enn");
    }    
    
   @FXML
   public void insertarEmpresa(ActionEvent event){
       int id = Integer.parseInt(regemId.getText());
       String nombre = regemNombre.getText();
       String admin = regemAdmin.getText();
       String pass = regemPassword.getText();
       
       
       boolean in = false;
       Conectar cd = new Conectar();
       try{
       if(cd.crearConexion()){
           cd.getConexion().setAutoCommit(false);
            String sql = "call insertEmpresa(?,?,?,?)";
           PreparedStatement ps = cd.getConexion().prepareStatement(sql);
           ps.setInt(1, id);
           ps.setString(2, nombre);
           ps.setString(3, admin);
           ps.setString(4, pass);
           ps.executeUpdate();
           cd.getConexion().commit();
           in = true;
       }
       } catch (SQLException ex) {
            Logger.getLogger(EmpresasController.class.getName()).log(Level.SEVERE, null, ex);
        }
       if(in){
           JOptionPane.showMessageDialog(null, "Ingresado con exito");
       }else{
           JOptionPane.showMessageDialog(null, "No se ingreso");
       }
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
