package gestioneStudenti.controller;

import gestioneStudenti.Studente;
import gestioneStudenti.ArchivioStudenti;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
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

public class ModificaStudenteController implements Initializable {

    @FXML private Button ConfermaModificaStudente;
    @FXML private Button homeModificaStudente;

    @FXML private TextField ModificaNome;
    @FXML private TextField ModificaCognome;
    @FXML private TextField ModificaMatricola; // NON modificabile
    @FXML private TextField ModificaEmail;

    @FXML private Label modificastudente;

    private ArchivioStudenti archivio;
    private Studente studenteOriginale;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        this.archivio = stato.getArchivioStudenti();

        // La matricola non deve essere modificabile
        ModificaMatricola.setEditable(false);

        // Disabilito il bottone finché non ho lo studente
        ConfermaModificaStudente.setDisable(true);
    }

    public void setStudenteDaModificare(Studente s) {
        this.studenteOriginale = s;

        // Precarico i campi
        ModificaNome.setText(s.getNome());
        ModificaCognome.setText(s.getCognome());
        ModificaMatricola.setText(s.getMatricola());
        ModificaEmail.setText(s.getEmail());

        // ✅ Binding: abilita il bottone SOLO se almeno un campo è cambiato
        BooleanBinding nessunaModifica =
                ModificaNome.textProperty().isEqualTo(s.getNome())
                .and(ModificaCognome.textProperty().isEqualTo(s.getCognome()))
                .and(ModificaEmail.textProperty().isEqualTo(s.getEmail()));

        ConfermaModificaStudente.disableProperty().bind(nessunaModifica);
    }

    @FXML
    private void salvaModificaStudente(ActionEvent event) {
        try {
            // Creo un nuovo Studente con i dati modificati
            Studente modificato = new Studente(
                    ModificaNome.getText().trim(),
                    ModificaCognome.getText().trim(),
                    studenteOriginale.getMatricola(), // NON modificabile
                    ModificaEmail.getText().trim()
            );

            archivio.modificaStudente(modificato);

            GestoreStatoBiblioteca.getInstance().salva();

            modificastudente.setStyle("-fx-text-fill: green;");
            modificastudente.setText("Modifica salvata con successo.");

        } catch (IllegalArgumentException | IllegalStateException ex) {
            modificastudente.setStyle("-fx-text-fill: red;");
            modificastudente.setText(ex.getMessage());
        } catch (IOException ioEx) {
            modificastudente.setStyle("-fx-text-fill: red;");
            modificastudente.setText("Errore nel salvataggio dei dati.");
            ioEx.printStackTrace();
        }
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/view/BibliotecaInterfaccia.fxml")
            );
            Stage stage = (Stage) homeModificaStudente.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
