package gestionePrestiti.controller;

import gestioneLibri.Libro;
import gestioneStudenti.Studente;
import gestionePrestiti.Prestito;
import gestionePrestiti.ArchivioPrestiti;
import persistence.GestoreStatoBiblioteca;
import persistence.StatoBiblioteca;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class AggiungiPrestitoController implements Initializable {

    @FXML private Label labelaggiungiprestito;
    @FXML private Label labelISBNprestito;
    @FXML private Label labelmatricolaprestito;
    @FXML private Label labeldataprestito;
    @FXML private Label labeldatarestituzione;

    @FXML private TextField testoISBNprestito;
    @FXML private TextField testoMatricolaPrestito;
    @FXML private TextField testoDataPrestito;
    @FXML private TextField testoDataRestituzione;

    @FXML private Button bottoneConfermaPrestito;
    @FXML private Button homeAggiungiPrestito;
    

    private ArchivioPrestiti archivioPrestiti;
    private ListView<Libro> catalogoLibri;
    private ListView<Studente> elencoStudenti;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        archivioPrestiti = stato.getArchivioPrestiti();

        // ✅ Bottone conferma abilitato solo se tutti i campi sono pieni
        bottoneConfermaPrestito.disableProperty().bind(
            Bindings.createBooleanBinding(() ->
                testoISBNprestito.getText().trim().isEmpty() ||
                testoMatricolaPrestito.getText().trim().isEmpty() ||
                testoDataPrestito.getText().trim().isEmpty() || 
                testoDataRestituzione.getText().trim().isEmpty() ||,
                testoISBNprestito.textProperty(),
                testoMatricolaPrestito.textProperty(),
                testoDataPrestito.textProperty(),
                testoDataRestituzione.textProperty()
            )
        );
    }

    @FXML
    private void confermaPrestito(ActionEvent event) {
        String isbn = testoISBNprestito.getText().trim();
        String matricola = testoMatricolaPrestito.getText().trim();
        String dataStr = testoDataPrestito.getText().trim();
        String dataRest = testoDataRestituzione.getText().trim();

        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        Libro libro = stato.getArchivioLibri().cercaPerIsbn(isbn);
        Studente studente = stato.getArchivioStudenti().cercaPerMatricola(matricola);

        if (libro == null) {
            mostraErrore("ISBN non valido: nessun libro trovato.");
            return;
        }

        if (studente == null) {
            mostraErrore("Matricola non valida: nessuno studente trovato.");
            return;
        }

        LocalDate dataPrestito;
        try {
            dataPrestito = LocalDate.parse(dataStr);
        } catch (DateTimeParseException e) {
            mostraErrore("Data non valida. Usa il formato YYYY-MM-DD.");
            return;
        }

        LocalDate dataRestituzione = dataPrestito.plusDays(30); // restituzione prevista tra 30 giorni

        try {
            Prestito nuovo = archivioPrestiti.registraPrestito(studente, libro, dataPrestito, dataRestituzione);
            GestoreStatoBiblioteca.getInstance().salva();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Prestito registrato");
            alert.setHeaderText(null);
            alert.setContentText("Prestito registrato con successo.");
            alert.showAndWait();

            testoISBNprestito.clear();
            testoMatricolaPrestito.clear();
            testoDataPrestito.clear();

        } catch (Exception ex) {
            mostraErrore("Errore: " + ex.getMessage());
        }
    }
    
    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/view/BibliotecaInterfaccia.fxml")
            );
            Stage stage = (Stage) homeAggiungiPrestito.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostraErrore(String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }
}
