/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.controller;

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
public class Bibliotecainterfaccia1Controller implements Initializable {

    @FXML
    private Label labelbiblioteca;
    @FXML
    private Button Libri;
    @FXML
    private Button Studenti;
    @FXML
    private Button Prestiti;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void visualizzalibri(ActionEvent event) {
    }

    @FXML
    private void visualizzastudenti(ActionEvent event) {
    }

    @FXML
    private void visualizzaprestiti(ActionEvent event) {
    }
    
}
