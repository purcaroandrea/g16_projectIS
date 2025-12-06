/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.prestiti;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author laura
 */
public class Gestioneprestiti1Controller implements Initializable {

    @FXML
    private Label labelgestioneprestito;
    @FXML
    private Button NuovoPrestito;
    @FXML
    private Button RegistroPrestiti;
    @FXML
    private Button RicercaPrestito;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aggiungenuovoprestito(ActionEvent event) {
    }

    @FXML
    private void visualizzaregistroprestiti(ActionEvent event) {
    }

    @FXML
    private void ricercaprestito(ActionEvent event) {
    }
    
}
