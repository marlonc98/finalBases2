/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Certificado_nombre;
import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marlon Méndez
 */
public class AddCertificadoController implements Initializable {
    
    Usuario user = new Usuario(3, "marlonc98", "1234", "marlonc98", 0, 0, 0, 0);
    @FXML
    private VBox acCertificados;
    ObservableList<String> obCertificados;
    
    LinkedList<Certificado_nombre> certificados = new LinkedList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadCerti();
        anadir();
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
    public void anadir(ActionEvent event){
        anadir();
    }
    @FXML
    public void anadir(){
        ChoiceBox nuevo = new ChoiceBox();
        nuevo.setMinWidth(230);
        nuevo.setItems(obCertificados);
        acCertificados.getChildren().add(nuevo);
        nuevo.setValue("Seleccione...");
   }
    
    @FXML
    public void eliminar(ActionEvent event){
        ObservableList<Node> obs = acCertificados.getChildren();
        if(obs.size() > 1){
            acCertificados.getChildren().remove(obs.size() - 1);
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
   
   @FXML
   void insertProjCert(ActionEvent event){
        ObservableList obsl = acCertificados.getChildren();
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
        
        System.out.println(certificadosi.size());
        //obtener ppL
        
        //insertar certificados
        for(Integer intCert: certificadosi){
            Conectar cd = new Conectar();
            System.out.println("conect");
            String sql = "call addCertificateUser(?,?)";
            try{
                if(cd.crearConexion()){
                    cd.getConexion().setAutoCommit(false);
                        PreparedStatement ps = cd.getConexion().prepareStatement(sql);
                        ps.setInt(1, intCert);
                        ps.setInt(2, user.getId());
                        ps.executeUpdate();
                        cd.getConexion().commit();
                }
            } catch (SQLException ex) {
                Logger.getLogger(InsertarProyectoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
    }
        
}
