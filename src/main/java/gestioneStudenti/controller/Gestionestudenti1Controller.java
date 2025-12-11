/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneStudenti.controller;

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
public class Gestionestudenti1Controller implements Initializable {

    @FXML
    private Button NuovoStudente;
    @FXML
    private Button RegistroStudenti;
    @FXML
    private Button RicercaStudente;
    @FXML
    private Label labelgestionestudenti;
    @FXML
    private Button homeGestioneStudente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aggiungestudente(ActionEvent event) {
    }

    @FXML
    private void visualizzaregistrostudenti(ActionEvent event) {
    }

    @FXML
    private void ricercastudente(ActionEvent event) {
    }
    
}
