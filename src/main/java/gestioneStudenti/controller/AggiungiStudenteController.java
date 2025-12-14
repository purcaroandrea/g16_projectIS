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

public class AggiungiStudenteController implements Initializable {

    @FXML private Button bottoneConfermaStudente;
    @FXML private Button homeAggiungiStudente;

    @FXML private TextField testoNome;
    @FXML private TextField testoCognome;
    @FXML private TextField testoMatricola;
    @FXML private TextField testoEmail;

    @FXML private Label messageLabel;

    private ArchivioStudenti archivio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        this.archivio = stato.getArchivioStudenti();

        messageLabel.setText("");

        // ✅ Binding: disattiva il bottone se almeno un campo è vuoto
        bottoneConfermaStudente.disableProperty().bind(
            testoNome.textProperty().isEmpty()
            .or(testoCognome.textProperty().isEmpty())
            .or(testoMatricola.textProperty().isEmpty())
            .or(testoEmail.textProperty().isEmpty())
        );
    }

    @FXML
    private void confermastudenti(ActionEvent event) {
        messageLabel.setText("");

        String nome = testoNome.getText().trim();
        String cognome = testoCognome.getText().trim();
        String matricola = testoMatricola.getText().trim();
        String email = testoEmail.getText().trim();

        try {
            Studente s = new Studente(nome, cognome, matricola, email);
            archivio.aggiungiStudente(s);
            GestoreStatoBiblioteca.getInstance().salva();

            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("Studente aggiunto: " + s.getNome() + " " + s.getCognome());

            testoNome.clear();
            testoCognome.clear();
            testoMatricola.clear();
            testoEmail.clear();

        } catch (IllegalArgumentException ex) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText(ex.getMessage());
        } catch (IOException ioEx) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Errore nel salvataggio dei dati.");
            ioEx.printStackTrace();
        }
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/BibliotecaInterfaccia.fxml"));
            Stage stage = (Stage) homeAggiungiStudente.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
