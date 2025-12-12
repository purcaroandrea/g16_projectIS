/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneLibri.controller;

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
public class Gestionelibri1Controller implements Initializable {

    @FXML
    private Label labelgestionelibri;
    @FXML
    private Button NuovoLibro;
    @FXML
    private Button RegistroLibri;
    @FXML
    private Button RicercaLibro;
    @FXML
    private Button homeGestioneLibri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aggiungenuovolibro(ActionEvent event) {
    }

    @FXML
    private void visualizzaregistrolibri(ActionEvent event) {
    }

    @FXML
    private void ricercalibro(ActionEvent event) {
    }
    
}
