package gestioneLibri.controller;

import gestioneLibri.Libro;
import gestioneLibri.ArchivioLibri;
import persistence.GestoreStatoBiblioteca;
import persistence.StatoBiblioteca;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class Modificalibro1Controller implements Initializable {

    @FXML private Label labelmodificalibro;
    @FXML private TextField TitoloModifica;
    @FXML private TextField AutoreModifica;
    @FXML private TextField ISBNModifica;
    @FXML private TextField AnnoPubModifica;
    @FXML private TextField NumCopieModifica;

    @FXML private Button confermalibrimodifica;
    @FXML private Button homeModificaLibro;

    private ArchivioLibri archivio;
    private Libro libroOriginale;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        archivio = stato.getArchivioLibri();

        ISBNModifica.setEditable(false);
        confermalibrimodifica.setDisable(true);
    }

    public void setLibro(Libro libro) {
        this.libroOriginale = libro;

        TitoloModifica.setText(libro.getTitolo());
        AutoreModifica.setText(libro.getAutore());
        ISBNModifica.setText(libro.getIsbn());
        AnnoPubModifica.setText(String.valueOf(libro.getAnnoPubblicazione()));
        NumCopieModifica.setText(String.valueOf(libro.getCopieDisponibili()));

        BooleanBinding nessunaModifica =
            TitoloModifica.textProperty().isEqualTo(libro.getTitolo())
            .and(AutoreModifica.textProperty().isEqualTo(libro.getAutore()))
            .and(AnnoPubModifica.textProperty().isEqualTo(String.valueOf(libro.getAnnoPubblicazione())))
            .and(NumCopieModifica.textProperty().isEqualTo(String.valueOf(libro.getCopieDisponibili())));

        confermalibrimodifica.disableProperty().bind(nessunaModifica);
    }

    @FXML
    private void salvaModificaLibro(ActionEvent event) {
        try {
            String nuovoTitolo = TitoloModifica.getText().trim();
            String nuovoAutore = AutoreModifica.getText().trim();
            int nuovoAnno = Integer.parseInt(AnnoPubModifica.getText().trim());
            int nuoveCopie = Integer.parseInt(NumCopieModifica.getText().trim());

            Libro modificato = new Libro(
                nuovoTitolo,
                nuovoAutore,
                nuovoAnno,
                libroOriginale.getIsbn(), // non modificabile
                nuoveCopie
            );

            archivio.modificaLibro(modificato);
            GestoreStatoBiblioteca.getInstance().salva();

            labelmodificalibro.setStyle("-fx-text-fill: green;");
            labelmodificalibro.setText("Modifica salvata con successo.");

        } catch (NumberFormatException e) {
            labelmodificalibro.setStyle("-fx-text-fill: red;");
            labelmodificalibro.setText("Anno e copie devono essere numeri interi.");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            labelmodificalibro.setStyle("-fx-text-fill: red;");
            labelmodificalibro.setText(ex.getMessage());
        } catch (IOException ioEx) {
            labelmodificalibro.setStyle("-fx-text-fill: red;");
            labelmodificalibro.setText("Errore nel salvataggio dei dati.");
            ioEx.printStackTrace();
        }
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/bibliotecainterfaccia1.fxml"));
            Stage stage = (Stage) homeModificaLibro.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
