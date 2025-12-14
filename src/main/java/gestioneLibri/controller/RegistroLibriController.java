package gestioneLibri.controller;

import gestioneLibri.Libro;
import gestioneLibri.ArchivioLibri;
import persistence.GestoreStatoBiblioteca;
import persistence.StatoBiblioteca;

import java.io.IOException;
import java.net.URL;
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

public class RegistroLibriController implements Initializable {

    @FXML
    private TableView<Libro> tviem;
    @FXML
    private TableColumn<Libro, String> colonnaTitolo;
    @FXML
    private TableColumn<Libro, String> colonnaAutore;
    @FXML
    private TableColumn<Libro, String> colonnaISBN;
    @FXML
    private TableColumn<Libro, Integer> colonnaAnnoPub;
    @FXML
    private TableColumn<Libro, Integer> colonnaNumCopie;

    @FXML
    private Label registrolibri;
    @FXML
    private Button homeRegistroLibri;

    private ArchivioLibri archivio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registrolibri.setText("Registro Libri");

        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        archivio = stato.getArchivioLibri();

        colonnaTitolo.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitolo())
        );

        colonnaAutore.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAutore())
        );

        colonnaISBN.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getIsbn())
        );

        colonnaAnnoPub.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getAnnoPubblicazione())
        );

        colonnaNumCopie.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getCopieDisponibili())
        );

        ObservableList<Libro> libriOrdinati = FXCollections.observableArrayList(
            archivio.getLibriOrdinatiPerTitolo()
        );
        tviem.setItems(libriOrdinati);
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/BibliotecaInterfaccia.fxml"));
            Stage stage = (Stage) homeRegistroLibri.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // nessun alert
        }
    }
}
