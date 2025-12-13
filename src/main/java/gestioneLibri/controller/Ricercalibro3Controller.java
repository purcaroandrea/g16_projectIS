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

import javafx.stage.Stage;

import persistence.GestoreStatoBiblioteca;
import persistence.StatoBiblioteca;

/**
 * FXML Controller class
 *
 * @author g16_member
 */
public class Ricercalibro3Controller implements Initializable {

    @FXML
    private Button bottoneModificaLibro;
    @FXML
    private Button bottoneRimuoviLibro;
    @FXML
    private Label ricercalibro2;
    @FXML
    private Label LibroSelezionato;
    @FXML
    private Button homeRicercaLibro2;
    
    private ArchivioLibri archivio;
    private Libro libroCorrente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        this.archivio = stato.getArchivioLibri();
        
        ricercalibro2.setText("Ricerca Libro");
        LibroSelezionato.setText("Nessun libro selezionato");
        
        bottoneModificaLibro.setOnAction(this::modificaLibro);
        bottoneRimuoviLibro.setOnAction(this::rimuoviLibro);
        homeRicercaLibro2.setOnAction(this::tornaAllaHome);
    }    
    
    @FXML
    private void modificaLibro(ActionEvent event) {
        String libro = LibroSelezionato.getText();
        if (libro == null) {
            ricercalibro2.setStyle("-fx-text-fill: red;");
            ricercalibro2.setText("Seleziona un libro da modificare!");
            return;
        }
        
        ricercalibro2.setStyle("-fx-text-fill: green;");
        ricercalibro2.setText("Modifica libro: " + libroCorrente.getTitolo());
    }
    
    @FXML
    private void rimuoviLibro() {
        String libro = LibroSelezionato.getText();
        if (libro == null) {
            ricercalibro2.setStyle("-fx-text-fill: red;");
            ricercalibro2.setText("Seleziona un libro da rimuovere!");
            return;
        }
        try {
            archivio.rimuoviLibro(libroCorrente);
            GestoreStatoBiblioteca.getInstance().salva();

            ricercalibro2.setStyle("-fx-text-fill: green;");
            ricercalibro2.setText("Libro rimosso: " + libroCorrente.getTitolo());

            LibroSelezionato.setText("Nessun libro selezionato");
            libroCorrente = null;

        } catch (IOException ioEx) {
            ricercalibro2.setStyle("-fx-text-fill: red;");
            ricercalibro2.setText("Errore nel salvataggio dei dati.");
            ioEx.printStackTrace();
        }
    }
    
    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/bibliotecainterfaccia1.fxml"));
            Stage stage = (Stage) homeRicercaLibro2.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
