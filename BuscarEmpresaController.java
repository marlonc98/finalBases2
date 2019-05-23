/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Ganta
 */
public class BuscarEmpresaController implements Initializable {

    @FXML
    private TableView<?> emptable;
    @FXML
    private TableColumn<?, ?> idemp;
    @FXML
    private TableColumn<?, ?> nomemp;
    @FXML
    private TableColumn<?, ?> lugemp;
    @FXML
    private ChoiceBox<?> filt_ubic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
