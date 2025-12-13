/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneLibri.controller;

import gestioneLibri.Libro;
import gestioneLibri.ArchivioLibri;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import persistence.GestoreStatoBiblioteca;
import persistence.StatoBiblioteca;

/**
 * FXML Controller class
 *
 * @author g16_member
 */
public class Ricercalibro1Controller implements Initializable {

    @FXML
    private Label ricercalibro;
    @FXML
    private Button bottoneRicercaLibro;
    @FXML
    private Button homeRicercaLibro;
    @FXML
    private TextField barraRicercaLibro;
    
    private ArchivioLibri archivio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        this.archivio = stato.getArchivioLibri();
        
        ricercalibro.setText("Ricerca Libro");
        
        if (barraRicercaLibro != null) barraRicercaLibro.setPromptText("Inserire titolo, autore o ISBN");
        
        bottoneRicercaLibro.disableProperty().bind(
            barraRicercaLibro.textProperty().isEmpty()
        );
    }

    @FXML
    private void ricercaLibro(ActionEvent event) {
        try{
            ricercalibro.setText("");
            
            String Libro = barraRicercaLibro.getText().trim();
            
            if (Libro.isEmpty()) {
                ricercalibro.setStyle("-fx-text-fill: red;");
                ricercalibro.setText("Inserire titolo o codice!");
                return;
            }

            Libro trovato = archivio.cercaPerTitoloOIsbn(Libro);

            if (trovato != null) {
                ricercalibro.setStyle("-fx-text-fill: green;");
                ricercalibro.setText("Libro trovato: " + trovato.getTitolo() + " (" + trovato.getCodice() + ")");
            } else {
                ricercalibro.setStyle("-fx-text-fill: red;");
                ricercalibro.setText("Nessun libro trovato con '" + Libro + "'");
            }

        } catch (IllegalArgumentException ex) {
            ricercalibro.setStyle("-fx-text-fill: red;");
            ricercalibro.setText("Errore: " + ex.getMessage());
        } catch (Exception e) {
            ricercalibro.setStyle("-fx-text-fill: red;");
            ricercalibro.setText("Errore durante la ricerca.");
            e.printStackTrace();
        }
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/bibliotecainterfaccia1.fxml"));
            Stage stage = (Stage) homeRicercaLibro.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
