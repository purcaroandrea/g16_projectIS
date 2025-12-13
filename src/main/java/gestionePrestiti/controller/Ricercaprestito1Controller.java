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
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author g16_member
 */
public class Ricercaprestito1Controller implements Initializable {

    @FXML
    private Label ricercaprestito;
    @FXML
    private TextField barraRicercaPrestitoISBN;
    @FXML
    private Button bottoneRicercaPrestito;
    @FXML
    private TextField barraRicercaPrestitoMatCogn;
    @FXML
    private Label ricercaperISBN;
    @FXML
    private Label ricercaperMatCogn;
    @FXML
    private Button homeRicercaPrestito;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        barraRicercaPrestitoISBN.clear();
        barraRicercaPrestitoMatCogn.clear();
    }    
    
    @FXML
    private void eseguiRicerca(ActionEvent event) {
       System.out.println(
            barraRicercaPrestitoISBN.getText() + " " +
            barraRicercaPrestitoMatCogn.getText()
        );
}
    
    @FXML
    private void tornaAllaHome(ActionEvent event) throws IOException {
        Main.setRoot("bibliotecainterfaccia1");
    }
    
}
