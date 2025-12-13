/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionePrestiti.controller;

import gestioneLibri.ArchivioLibri;
import gestioneLibri.Libro;
import gestionePrestiti.ArchivioPrestiti;
import gestioneStudenti.ArchivioStudenti;
import gestioneStudenti.Studente;
import persistence.ArchivioRepository;
import persistence.StatoBiblioteca;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author g16_member
 */
public class Aggiungiprestito1Controller implements Initializable {

    @FXML
    private Label labelaggiungiprestito;
    @FXML
    private Label labelISBNprestito;
    @FXML
    private Label labelmatricolaprestito;
    @FXML
    private Label labeldataprestito;
    @FXML
    private TextField testoISBNprestito;
    @FXML
    private TextField testoDataPrestito;
    @FXML
    private TextField testoMatricolaPrestito;
    @FXML
    private Button bottoneConfermaPrestito;
    @FXML
    private Button homeAggiungiPrestito;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       testoISBNprestito.clear();
        testoMatricolaPrestito.clear();
        testoDataPrestito.clear();
    }

    @FXML
    private void confermaprestito(ActionEvent event) {
        String isbn = testoISBNprestito.getText();
    String matricola = testoMatricolaPrestito.getText();
    String data = testoDataPrestito.getText();

    // Placeholder corretto: la logica reale sarà nel modello
    System.out.println("Prestito richiesto: ISBN=" + isbn +
                       ", Matricola=" + matricola +
                       ", Data=" + data);
    }
    
    
    @FXML
    private void salvaISBNprestito(MouseEvent event) {
       String isbn = testoISBNprestito.getText().trim();

    if (isbn.isEmpty()) {
        mostraErrore("ISBN non valido", "Inserire un ISBN.");
        return;
    }

    if (archivioLibri.cercaPerIsbn(isbn) == null) {
        mostraErrore("Libro non trovato", "Nessun libro con questo ISBN.");
    }
    }

    @FXML
    private void salvadataprestito(MouseEvent event) {
      String data = testoDataPrestito.getText().trim();

    try {
        LocalDate.parse(data);
    } catch (Exception e) {
        mostraErrore(
            "Data non valida",
            "Inserire la data nel formato YYYY-MM-DD."
        );
    }
    }

    @FXML
    private void salvamatricolaprestito(MouseEvent event) {
        String matricola = testoMatricolaPrestito.getText().trim();

    if (matricola.isEmpty()) {
        mostraErrore("Matricola non valida", "Inserire una matricola.");
        return;
    }

    if (archivioStudenti.cercaPerMatricola(matricola) == null) {
        mostraErrore(
            "Studente non trovato",
            "Nessuno studente con questa matricola."
        );
    }
    } 
    
    @FXML
    private void tornaAllaHome(ActionEvent event) {
       try {
            Parent root = FXMLLoader.load(
                getClass().getResource("/view/bibliotecainterfaccia1.fxml")
            );
            Stage stage = (Stage) homeAggiungiPrestito.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      
}
