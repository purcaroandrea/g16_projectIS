/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneStudenti.controller;

import gestioneStudenti.Studente;
import gestioneStudenti.ArchivioStudenti;

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
public class Ricercastudente3Controller implements Initializable {


    @FXML
    private Button bottoneModificaStudente;
    @FXML
    private Button bottoneRimuoviStudente;
    @FXML
    private Label ricercastudente2;
    @FXML
    private Label StudenteSelezionato;
    @FXML
    private Button homeRimozioneStudente; 
    
    private ArchivioStudenti archivio;
    private Studente studenteCorrente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        this.archivio = stato.getArchivioStudenti();
        
        ricercastudente2.setText("Ricerca Studente");
        StudenteSelezionato.setText("Nessuno studente selezionato");
        bottoneModificaStudente.setOnAction(this::modificaStudente);
        bottoneRimuoviStudente.setOnAction(this::rimuoviStudente);
        homeRimozioneStudente.setOnAction(this::tornaAllaHome);
    }

    @FXML
    private void modificaStudente(ActionEvent event) {
        String studente = StudenteSelezionato.getText();
        if (studente == null) {
            ricercastudente2.setStyle("-fx-text-fill: red;");
            ricercastudente2.setText("Seleziona uno studente da modificare!");
            return;
        }
        ricercastudente2.setStyle("-fx-text-fill: green;");
        ricercastudente2.setText("Modifica studente: " + studenteCorrente.getNome() + " " + studenteCorrente.getCognome());
    }
    
    @FXML
    private void rimuoviStudente(ActionEvent event) {
        String studente = StudenteSelezionato.getText();
        if (studente == null) {
            ricercastudente2.setStyle("-fx-text-fill: red;");
            ricercastudente2.setText("Seleziona uno studente da rimuovere!");
            return;
        }
        try {
            archivio.rimuoviStudente(studenteCorrente);
            GestoreStatoBiblioteca.getInstance().salva();

            ricercastudente2.setStyle("-fx-text-fill: green;");
            ricercastudente2.setText("Studente rimosso: " + studenteCorrente.getNome() + " " + studenteCorrente.getCognome());

            StudenteSelezionato.setText("Nessuno studente selezionato");
            studenteCorrente = null;

        } catch (IOException ioEx) {
            ricercastudente2.setStyle("-fx-text-fill: red;");
            ricercastudente2.setText("Errore nel salvataggio dei dati.");
            ioEx.printStackTrace();
        }
    }
    
    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/bibliotecainterfaccia1.fxml"));
            Stage stage = (Stage) homeRimozioneStudente.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
