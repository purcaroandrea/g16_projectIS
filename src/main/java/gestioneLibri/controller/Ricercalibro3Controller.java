package gestioneLibri.controller;

import gestioneLibri.Libro;
import gestioneLibri.ArchivioLibri;
import persistence.GestoreStatoBiblioteca;
import persistence.StatoBiblioteca;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class Ricercalibro3Controller implements Initializable {

    @FXML private Label ricercalibro2;
    @FXML private ListView<Libro> listaLibri;
    @FXML private Button bottoneModificaLibro;
    @FXML private Button bottoneRimuoviLibro;
    @FXML private Button homeRicercaLibro2;

    private ArchivioLibri archivio;
    private Libro libroCorrente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        archivio = stato.getArchivioLibri();

        ricercalibro2.setText("Ricerca Libro");
        bottoneModificaLibro.setDisable(true);
        bottoneRimuoviLibro.setDisable(true);

        listaLibri.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            libroCorrente = newVal;
            bottoneModificaLibro.setDisable(newVal == null);
            bottoneRimuoviLibro.setDisable(newVal == null);
        });
    }

    public void setLibri(List<Libro> risultati) {
        ObservableList<Libro> dati = FXCollections.observableArrayList(risultati);
        listaLibri.setItems(dati);
    }

    @FXML
    private void modificaLibro(ActionEvent event) {
        if (libroCorrente == null) {
            ricercalibro2.setStyle("-fx-text-fill: red;");
            ricercalibro2.setText("Seleziona un libro da modificare!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/libri/modificalibro1.fxml")
            );
            Parent root = loader.load();

            Modificalibro1Controller controller = loader.getController();
            controller.setLibro(libroCorrente);

            Stage stage = (Stage) bottoneModificaLibro.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            ricercalibro2.setStyle("-fx-text-fill: red;");
            ricercalibro2.setText("Errore nel caricamento della schermata di modifica.");
            e.printStackTrace();
        }
    }

    @FXML
    private void rimuoviLibro(ActionEvent event) {
        if (libroCorrente == null) {
            ricercalibro2.setStyle("-fx-text-fill: red;");
            ricercalibro2.setText("Seleziona un libro da rimuovere!");
            return;
        }

        try {
            archivio.rimuoviLibro(libroCorrente);
            GestoreStatoBiblioteca.getInstance().salva();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Libro rimosso");
            alert.setHeaderText(null);
            alert.setContentText("Il libro è stato rimosso con successo.");
            alert.showAndWait();

            listaLibri.getItems().remove(libroCorrente);
            libroCorrente = null;
            bottoneModificaLibro.setDisable(true);
            bottoneRimuoviLibro.setDisable(true);

        } catch (Exception ex) {
            ricercalibro2.setStyle("-fx-text-fill: red;");
            ricercalibro2.setText("Errore: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/bibliotecainterfaccia1.fxml"));
            Stage stage = (Stage) homeRicercaLibro2.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
