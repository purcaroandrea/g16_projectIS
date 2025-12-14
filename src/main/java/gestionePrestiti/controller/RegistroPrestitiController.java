package gestionePrestiti.controller;

import gestionePrestiti.Prestito;
import gestionePrestiti.ArchivioPrestiti;
import persistence.GestoreStatoBiblioteca;
import persistence.StatoBiblioteca;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

public class RegistroPrestitiController implements Initializable {

    @FXML private TableView<Prestito> tabellaPrestiti;

    @FXML private TableColumn<Prestito, String> colonnaStudente;
    @FXML private TableColumn<Prestito, String> colonnaLibro;
    @FXML private TableColumn<Prestito, LocalDate> colonnaDataPrestito;
    @FXML private TableColumn<Prestito, LocalDate> colonnaDataRestituzione;
    @FXML private TableColumn<Prestito, String> colonnaStatoPrestito;

    @FXML private Label registroprestiti;
    @FXML private Button homeRegistroPrestito;

    private ArchivioPrestiti archivio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        registroprestiti.setText("Registro Prestiti");

        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        archivio = stato.getArchivioPrestiti();

        // ✅ Colonna Studente
        colonnaStudente.setCellValueFactory(p ->
            new SimpleStringProperty(p.getValue().getStudente().toString())
        );

        // ✅ Colonna Libro
        colonnaLibro.setCellValueFactory(p ->
            new SimpleStringProperty(p.getValue().getLibro().toString())
        );

        // ✅ Colonna Data Prestito
        colonnaDataPrestito.setCellValueFactory(p ->
            new SimpleObjectProperty<>(p.getValue().getDataPrestito())
        );

        // ✅ Colonna Data Restituzione
        colonnaDataRestituzione.setCellValueFactory(p ->
            new SimpleObjectProperty<>(p.getValue().getDataRestituzione())
        );

        // ✅ Colonna Stato Prestito (con ritardi)
        colonnaStatoPrestito.setCellValueFactory(p -> {
            Prestito pr = p.getValue();
            boolean ritardo = pr.isAttivo() &&
                              pr.getDataRestituzione().isBefore(LocalDate.now());

            if (ritardo) {
                return new SimpleStringProperty("In ritardo");
            }
            return new SimpleStringProperty(pr.isAttivo() ? "Attivo" : "Chiuso");
        });

        // ✅ Carico i prestiti attivi ORDINATI per data (già fatto dal modello)
        List<Prestito> attiviOrdinati = archivio.getPrestitiPerData();

        ObservableList<Prestito> dati = FXCollections.observableArrayList(attiviOrdinati);
        tabellaPrestiti.setItems(dati);

        // ✅ Evidenziazione righe in ritardo
        tabellaPrestiti.setRowFactory(tv -> new TableRow<Prestito>() {
            @Override
            protected void updateItem(Prestito prestito, boolean empty) {
                super.updateItem(prestito, empty);

                if (empty || prestito == null) {
                    setStyle("");
                    return;
                }

                boolean ritardo = prestito.isAttivo() &&
                                  prestito.getDataRestituzione().isBefore(LocalDate.now());

                if (ritardo) {
                    setStyle("-fx-background-color: #ffb3b3;"); // rosso chiaro
                } else {
                    setStyle("");
                }
            }
        });
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                getClass().getResource("/view/BibliotecaInterfaccia.fxml")
            );
            Stage stage = (Stage) homeRegistroPrestito.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
