/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionePrestiti.controller;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import gestionePrestiti.Prestito;


/**
 * FXML Controller class
 *
 * @author g16_member
 */
public class Registroprestiti1Controller implements Initializable {

    @FXML
    private TableView<Prestito> tabellaPrestiti;
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
        colonnaISBN.setCellValueFactory(
            p -> new SimpleStringProperty(p.getValue().getLibro().getIsbn())
        );
        colonnaMatricola.setCellValueFactory(
            p -> new SimpleStringProperty(p.getValue().getStudente().getMatricola())
        );
        colonnaDataPrestito.setCellValueFactory(
            p -> new SimpleObjectProperty<>(p.getValue().getDataPrestito())
        );
    }    
    
    @FXML
   private void tornaAllaHome(ActionEvent event) throws IOException {
        Main.setRoot("bibliotecainterfaccia1");
    }
    
}
