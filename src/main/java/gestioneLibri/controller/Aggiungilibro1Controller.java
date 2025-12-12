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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author laura
 */
public class Aggiungilibro1Controller implements Initializable {

    @FXML
    private Label labelaggiungilibro;
    @FXML
    private Label titolo;
    @FXML
    private Label autore;
    @FXML
    private Label ISBN;
    @FXML
    private Label annodipub;
    @FXML
    private Label numcopie;
    @FXML
    private Button bottoneConfermaLibri;
    @FXML
    private Button homeAggiungiLibro;
    @FXML
    private TextField testoTitolo;
    @FXML
    private TextField testoAutore;
    @FXML
    private TextField testoISBN;
    @FXML
    private TextField testoAnnoPub;
    @FXML
    private TextField testoNumCopie;
    
    
            
            
                    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(testoTitolo.getText().trim().isEmpty()|| testoAutore.getText().trim().isEmpty()|| testoISBN.getText().trim().isEmpty() || testoAnnoPub.getText().trim().isEmpty() ||testoNumCopie.getText().trim().isEmpty()){
            System.out.println("ERRORE: devi compilare tutti i campi");
            return;
            
        }
    }

    @FXML
    private void confermalibri(ActionEvent event) {
    }
    
}
