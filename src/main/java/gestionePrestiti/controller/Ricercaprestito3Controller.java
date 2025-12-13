/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionePrestiti.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;


/**
 * FXML Controller class
 *
 * @author g16_member
 */
public class Ricercaprestito3Controller implements Initializable {

    @FXML
    private Label ricercaprestito2;
    @FXML
    private Button bottoneChiudiPrestito;
    @FXML
    private Button homeChiusuraPrestito;
    @FXML
    private Label PrestitoSelezionato;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PrestitoSelezionato.setText("Nessun prestito selezionato");
    }    
    
    @FXML
    private void chiudiPrestito(ActionEvent event) {
       System.out.println("Chiusura del prestito selezionato");
    }
    
    @FXML
     private void tornaAllaHome(ActionEvent event) throws IOException {
       Main.setRoot("bibliotecainterfaccia1");
    }
    
}
