package gestioneLibri.controller;

import gestioneLibri.ArchivioLibri;
import gestioneLibri.Libro;
import persistence.GestoreStatoBiblioteca;
import persistence.StatoBiblioteca;

import java.io.IOException;
import java.net.URL;
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

public class Aggiungilibro1Controller implements Initializable {

    @FXML private Label labelaggiungilibro;
    @FXML private Label titolo;
    @FXML private Label autore;
    @FXML private Label ISBN;
    @FXML private Label annodipub;
    @FXML private Label numcopie;

    @FXML private TextField testoTitolo;
    @FXML private TextField testoAutore;
    @FXML private TextField testoISBN;
    @FXML private TextField testoAnnoPub;
    @FXML private TextField testoNumCopie;

    @FXML private Button bottoneConfermaLibri;
    @FXML private Button homeAggiungiLibro;

    private ArchivioLibri archivio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        archivio = stato.getArchivioLibri();

        // ✅ Bottone conferma abilitato solo se tutti i campi sono pieni
        bottoneConfermaLibri.disableProperty().bind(
            Bindings.createBooleanBinding(() ->
                testoTitolo.getText().trim().isEmpty() ||
                testoAutore.getText().trim().isEmpty() ||
                testoISBN.getText().trim().isEmpty() ||
                testoAnnoPub.getText().trim().isEmpty() ||
                testoNumCopie.getText().trim().isEmpty(),
                testoTitolo.textProperty(),
                testoAutore.textProperty(),
                testoISBN.textProperty(),
                testoAnnoPub.textProperty(),
                testoNumCopie.textProperty()
            )
        );
    }

    @FXML
    private void confermaLibro(ActionEvent event) {
        String titolo = testoTitolo.getText().trim();
        String autore = testoAutore.getText().trim();
        String isbn = testoISBN.getText().trim();
        String annoStr = testoAnnoPub.getText().trim();
        String copieStr = testoNumCopie.getText().trim();

        try {
            if (archivio.cercaPerIsbn(isbn) != null) {
                mostraErrore("ISBN già presente in archivio.");
                return;
            }

            int anno = Integer.parseInt(annoStr);
            int copie = Integer.parseInt(copieStr);

            if (copie <= 0) {
                mostraErrore("Il numero di copie deve essere maggiore di 0.");
                return;
            }

            Libro nuovo = new Libro(titolo, autore, anno, isbn, copie);
            archivio.aggiungiLibro(nuovo);
            GestoreStatoBiblioteca.getInstance().salva();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Libro aggiunto");
            alert.setHeaderText(null);
            alert.setContentText("Libro aggiunto con successo.");
            alert.showAndWait();

            testoTitolo.clear();
            testoAutore.clear();
            testoISBN.clear();
            testoAnnoPub.clear();
            testoNumCopie.clear();

        } catch (NumberFormatException e) {
            mostraErrore("Anno e copie devono essere numeri interi.");
        } catch (Exception ex) {
            mostraErrore("Errore: " + ex.getMessage());
        }
    }

    private void mostraErrore(String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/bibliotecainterfaccia1.fxml"));
            Stage stage = (Stage) homeAggiungiLibro.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
