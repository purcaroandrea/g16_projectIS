package gestioneStudenti.controller;

import gestioneStudenti.Studente;
import gestioneStudenti.ArchivioStudenti;

import java.io.IOException;
import java.net.URL;
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

public class RicercaStudenteController implements Initializable {

    @FXML private Label ricercastudente;
    @FXML private TextField barraRicercaStudente;
    @FXML private Button bottoneRicercaStudente;
    @FXML private Button homeRicercaStudente;

    private ArchivioStudenti archivio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        this.archivio = stato.getArchivioStudenti();

        ricercastudente.setText("Ricerca Studente");
        barraRicercaStudente.setPromptText("Inserire cognome o matricola");

        // ✅ Il bottone si abilita solo se c’è testo
        bottoneRicercaStudente.disableProperty().bind(
                barraRicercaStudente.textProperty().isEmpty()
        );
    }

    @FXML
    private void confermaRicerca(ActionEvent event) {

        ricercastudente.setStyle("-fx-text-fill: black;");
        String input = barraRicercaStudente.getText().trim();

        if (input.isEmpty()) {
            ricercastudente.setStyle("-fx-text-fill: red;");
            ricercastudente.setText("Inserire cognome o matricola!");
            return;
        }

        try {
            //Ricerca per matricola
            Studente trovato = archivio.cercaPerMatricola(input);

            //Se non trovato → ricerca per cognome
            if (trovato == null) {
                List<Studente> lista = archivio.cercaPerCognome(input);
                if (!lista.isEmpty()) {
                    trovato = lista.get(0);
                }
            }

            //Nessun risultato
            if (trovato == null) {
                ricercastudente.setStyle("-fx-text-fill: red;");
                ricercastudente.setText("Nessuno studente trovato con '" + input + "'");
                return;
            }

            // Cambio schermata
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/studenti/SelezionaStudente.fxml")
            );

            Parent root = loader.load();

            // Recupero controller della schermata successiva
            SelezionaStudenteController controller = loader.getController();

            //Passo direttamente l’oggetto Studente
            controller.setStudente(trovato);

            //Cambio scena
            Stage stage = (Stage) bottoneRicercaStudente.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ex) {
            ricercastudente.setStyle("-fx-text-fill: red;");
            ricercastudente.setText("Errore: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/view/BibliotecaInterfaccia.fxml")
            );
            Stage stage = (Stage) homeRicercaStudente.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
