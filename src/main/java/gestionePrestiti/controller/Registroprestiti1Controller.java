/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionePrestiti.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author laura
 */
public class Registroprestiti1Controller implements Initializable {

    @FXML
    private TableColumn<?, ?> colonnaISBN;
    @FXML
    private TableColumn<?, ?> colonnaMatricola;
    @FXML
    private TableColumn<?, ?> colonnaDataPrestito;
    @FXML
    private Label registroprestiti;
    @FXML
    private Button homeRegistroPrestito;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
