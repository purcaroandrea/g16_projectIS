/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionePrestiti.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author g16_member
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
    @FXML
    private Button homeGestionePrestiti;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelgestioneprestito.setText("Gestione Prestiti");
    }    

    @FXML
    private void aggiungenuovoprestito(ActionEvent event) throws IOException {
        Main.setRoot("aggiungiprestito1");
    }

    @FXML
    private void visualizzaregistroprestiti(ActionEvent event) throws IOException {
        Main.setRoot("registroprestiti1");
    }

    @FXML
    private void ricercaprestito(ActionEvent event) throws IOException {
        Main.setRoot("ricercaprestito1");
    }
    
    @FXML
    private void tornaAllaHome(ActionEvent event) throws IOException {
        Main.setRoot("bibliotecainterfaccia1");
    }
}

