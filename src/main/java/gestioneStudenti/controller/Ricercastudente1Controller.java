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
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import persistence.GestoreStatoBiblioteca;
import persistence.StatoBiblioteca;

/**
 * FXML Controller class
 *
 * @author g16_member
 */
public class Ricercastudente1Controller implements Initializable {

    @FXML
    private Label ricercastudente;
    @FXML
    private Button bottoneRicercaStudente;
    @FXML 
    private Button homeRicercaStudente;
    @FXML
    private TextField barraRicercaStudente;
    
    private ArchivioStudenti archivio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        this.archivio = stato.getArchivioStudenti();
    
        ricercastudente.setText("Ricerca Studente");
        
        if (barraRicercaStudente != null) barraRicercaStudente.setPromptText("Inserire cognome o matricola");
        
        bottoneRicercaStudente.disableProperty().bind(
            barraRicercaStudente.textProperty().isEmpty()
        );
    
    }

    @FXML
    private void ricercaStudente(ActionEvent event) {
        try {
            ricercastudente.setText("");

            String Studente = barraRicercaStudente.getText().trim();

            if (Studente.isEmpty()) {
                ricercastudente.setStyle("-fx-text-fill: red;");
                ricercastudente.setText("Inserire cognome o matricola!");
                return;
            }
        
            Studente trovato = archivio.cercaPerCognomeOMatricola(Studente);

            if (trovato != null) {
                ricercastudente.setStyle("-fx-text-fill: green;");
                ricercastudente.setText("Studente trovato: " + trovato.getNome() + " " + trovato.getCognome());
            } else {
                ricercastudente.setStyle("-fx-text-fill: red;");
                ricercastudente.setText("Nessuno studente trovato con '" + Studente + "'");
            }

        } catch (IllegalArgumentException ex) {
            ricercastudente.setStyle("-fx-text-fill: red;");
            ricercastudente.setText("Errore: " + ex.getMessage());
        } catch (Exception e) {
            ricercastudente.setStyle("-fx-text-fill: red;");
            ricercastudente.setText("Si è verificato un errore durante la ricerca.");
            e.printStackTrace(); 
        }
    }

    @FXML    
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/bibliotecainterfaccia1.fxml"));
            Stage stage = (Stage) homeRicercaStudente.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
