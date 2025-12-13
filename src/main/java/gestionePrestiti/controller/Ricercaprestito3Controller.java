package gestionePrestiti.controller;

import gestionePrestiti.Prestito;
import gestionePrestiti.ArchivioPrestiti;
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

public class Ricercaprestito3Controller implements Initializable {

    @FXML private Label labelRicercaPrestito3;
    @FXML private ListView<Prestito> listaPrestiti;
    @FXML private Button bottoneChiudiPrestito;
    @FXML private Button bottoneHomePrestito3;

    private ArchivioPrestiti archivio;
    private Prestito prestitoCorrente;
    private List<Prestito> risultatiRicerca;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        archivio = stato.getArchivioPrestiti();

        bottoneChiudiPrestito.setDisable(true);

        listaPrestiti.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            prestitoCorrente = newVal;
            bottoneChiudiPrestito.setDisable(newVal == null);
        });
    }

    public void setPrestiti(List<Prestito> lista) {
        this.risultatiRicerca = lista;
        aggiornaLista();
    }

    private void aggiornaLista() {
        ObservableList<Prestito> dati = FXCollections.observableArrayList(risultatiRicerca);
        listaPrestiti.setItems(dati);
    }

    @FXML
    private void chiudiPrestito(ActionEvent event) {
        if (prestitoCorrente == null) return;

        try {
            archivio.registraRestituzione(prestitoCorrente);
            GestoreStatoBiblioteca.getInstance().salva();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Prestito chiuso");
            alert.setHeaderText(null);
            alert.setContentText("Il prestito è stato chiuso con successo.");
            alert.showAndWait();

            risultatiRicerca.remove(prestitoCorrente);
            aggiornaLista();

            prestitoCorrente = null;
            bottoneChiudiPrestito.setDisable(true);

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText(null);
            alert.setContentText("Errore durante la chiusura del prestito: " + ex.getMessage());
            alert.showAndWait();
            ex.printStackTrace();
        }
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/bibliotecainterfaccia1.fxml"));
            Stage stage = (Stage) bottoneHomePrestito3.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Nessun messaggio a schermo
        }
    }
}
