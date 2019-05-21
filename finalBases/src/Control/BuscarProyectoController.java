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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private TableView<Usuario> table;

    @FXML
    private TableColumn<Usuario, String> chome_id;

    @FXML
    private TableColumn<Usuario, String> chome_nombre;

    @FXML
    private TableColumn<Usuario, String> chome_direccion;

    @FXML
    private TableColumn<Usuario, String> chome_ciudad;

    @FXML
    private TableColumn<Usuario, String> chome_descripcion;

    ObservableList<Usuario> obslist = FXCollections.observableArrayList();

    @FXML
    private TextField Codigo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

    @FXML
    private void Busqueda(ActionEvent event) {

        String code = Codigo.getText();
        Conectar condb = new Conectar();

        try {
            if (condb.crearConexion()) {
                condb.getConexion();

                Statement st = condb.getSt();
                String sql = "select id, user_log, password_log, phone, correo  from usuario where id=" +code + ";";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    Usuario nusuario = new Usuario(rs.getInt("id"),
                            rs.getString("user_log"),
                            rs.getString("password_log"),
                            rs.getString("correo"), 0, 0, 0, 0);
                    obslist.add(nusuario);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(BuscarProyectoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        chome_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        chome_nombre.setCellValueFactory(new PropertyValueFactory<>("user_log"));
        chome_direccion.setCellValueFactory(new PropertyValueFactory<>("password_log"));
        chome_ciudad.setCellValueFactory(new PropertyValueFactory<>("phone"));
        chome_descripcion.setCellValueFactory(new PropertyValueFactory<>("correos"));

        table.setItems(obslist);
    }

}
