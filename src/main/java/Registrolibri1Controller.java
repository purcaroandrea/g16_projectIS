/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author laura
 */
public class Registrolibri1Controller implements Initializable {

    @FXML
    private TableView<?> tviem;
    @FXML
    private TableColumn<?, ?> colonnaTitolo;
    @FXML
    private TableColumn<?, ?> colonnaAutore;
    @FXML
    private TableColumn<?, ?> colonnaISBN;
    @FXML
    private TableColumn<?, ?> colonnaAnnoPub;
    @FXML
    private TableColumn<?, ?> colonnaNumCopie;
    @FXML
    private Label registrolibri;
    @FXML
    private Button homeRegistroLibri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
