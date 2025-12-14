package gestioneLibri.controller;

import gestioneLibri.Libro;
import gestioneLibri.ArchivioLibri;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

public class RicercaLibroController implements Initializable {

    @FXML private Label ricercalibro;
    @FXML private Button bottoneRicercaLibro;
    @FXML private Button homeRicercaLibro;
    @FXML private TextField barraRicercaLibro;

    private ArchivioLibri archivio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        this.archivio = stato.getArchivioLibri();

        ricercalibro.setText("Ricerca Libro");
        barraRicercaLibro.setPromptText("Inserire titolo, autore o ISBN");

        bottoneRicercaLibro.disableProperty().bind(
            barraRicercaLibro.textProperty().isEmpty()
        );
    }

    @FXML
    private void confermaRicerca(ActionEvent event) {
        ricercalibro.setStyle("-fx-text-fill: black;");
        String input = barraRicercaLibro.getText().trim();

        if (input.isEmpty()) {
            ricercalibro.setStyle("-fx-text-fill: red;");
            ricercalibro.setText("Inserire titolo, autore o ISBN!");
            return;
        }

        try {
            List<Libro> risultati = new ArrayList<>();

            // Ricerca per ISBN
            Libro trovato = archivio.cercaPerIsbn(input);
            if (trovato != null) {
                risultati.add(trovato);
            }

            // Se non trovato per ISBN → cerca per titolo
            if (risultati.isEmpty()) {
                risultati.addAll(archivio.cercaPerTitolo(input));
            }

            // Se ancora vuoto → cerca per autore
            if (risultati.isEmpty()) {
                risultati.addAll(archivio.cercaPerAutore(input));
            }

            if (risultati.isEmpty()) {
                ricercalibro.setStyle("-fx-text-fill: red;");
                ricercalibro.setText("Nessun libro trovato con '" + input + "'");
                return;
            }

            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/libri/SelezionaLibro.fxml")
            );
            Parent root = loader.load();

            SelezionaLibroController controller = loader.getController();
            controller.setLibri(risultati);

            Stage stage = (Stage) bottoneRicercaLibro.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ex) {
            ricercalibro.setStyle("-fx-text-fill: red;");
            ricercalibro.setText("Errore: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/BibliotecaInterfaccia.fxml"));
            Stage stage = (Stage) homeRicercaLibro.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
