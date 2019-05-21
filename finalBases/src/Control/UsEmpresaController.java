/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
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

/**
 * FXML Controller class
 *
 * @author Marlon Méndez
 */
public class UsEmpresaController implements Initializable {

    @FXML
    TextField ueId, ueAdmin;
    
    @FXML 
    PasswordField uePass;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    void ingresar(ActionEvent event){
        Conectar cd = new Conectar();
        String sql = "call getEmpresa('"+ueAdmin.getText()+"','"+uePass.getText()+"')";
        boolean doe = false;
        try{
            if(cd.crearConexion()){
                cd.getConexion().setAutoCommit(false);
                Statement st = cd.getSt();
                ResultSet rs = st.executeQuery(sql);
                String sql2 = "call setEmpresaUser(?,?)";
                if(rs.next()){
                    int ide = rs.getInt("id");
                    if(cd.crearConexion()){
                        cd.getConexion().setAutoCommit(false);
                        PreparedStatement ps = cd.getConexion().prepareStatement(sql2);
                        ps.setInt(1, Integer.parseInt(ueId.getText()));
                        ps.setInt(2,ide);
                        ps.executeUpdate();
                        cd.getConexion().commit();
                        doe = true;
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña erroneos");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsEmpresaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(doe){
            JOptionPane.showMessageDialog(null, "Se registro con exito");
        }else{
            JOptionPane.showMessageDialog(null, "No se registro");
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
