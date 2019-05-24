/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import Modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Ganta
 */
public class LoginController implements Initializable {
    
    Usuario ObjU =  null;
    @FXML
    private TextField loginUser;
    
    @FXML
    private PasswordField loginPassword;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void LogIn(ActionEvent event) {
        
        String usuario = loginUser.getText();
        String contrasena = loginPassword.getText();
        Conectar cd = new Conectar();
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                Statement st = cd.getSt();
                String sql = "call log_in('"+usuario+"','"+contrasena+"')";
                ResultSet rs = st.executeQuery(sql);
             
                if(rs.next()){
                    //carga usuario
                    String usuarioA = rs.getString("user_log");
                    String contrasenaA = rs.getString("password_log");
                    int id = rs.getInt("id");
                    
                     ObjU = new Usuario();
                     ObjU.setId(id);
                     ObjU.setUser_log(usuarioA);
                     ObjU.setPassword_log(contrasenaA);
                    //manda a nueva pestaña
                    cambiarPestana(event, "Inicio");
                }else{
                    //no inicia sesion
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña erronea");
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void irRegistro(ActionEvent event) {
                    cambiarPestana(event, "Registro");
    }
        
    void cambiarPestana(ActionEvent event, String url){
        try {
            FXMLLoader loader =  new FXMLLoader();
            loader.setLocation(getClass().getResource("/Vista/"+url+".fxml"));
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
