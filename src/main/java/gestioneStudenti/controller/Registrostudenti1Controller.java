/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneStudenti.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author laura
 */
public class Registrostudenti1Controller implements Initializable {

    @FXML
    private TableColumn<?, ?> colonnaNome;
    @FXML
    private TableColumn<?, ?> colonnaCognome;
    @FXML
    private TableColumn<?, ?> colonnaMatricola;
    @FXML
    private TableColumn<?, ?> colonnaEmail;
    @FXML
    private TableColumn<?, ?> colonnaLibriPrestito;
    @FXML
    private Label registrostudenti;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
