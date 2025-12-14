package gestioneStudenti.controller;

import gestioneStudenti.Studente;
import gestioneStudenti.ArchivioStudenti;
import gestionePrestiti.ArchivioPrestiti;
import gestionePrestiti.Prestito;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import persistence.GestoreStatoBiblioteca;
import persistence.StatoBiblioteca;

public class RegistroStudentiController implements Initializable {

    @FXML private TableView<Studente> tabellaStudenti;

    @FXML private TableColumn<Studente, String> colonnaNome;
    @FXML private TableColumn<Studente, String> colonnaCognome;
    @FXML private TableColumn<Studente, String> colonnaMatricola;
    @FXML private TableColumn<Studente, String> colonnaEmail;
    @FXML private TableColumn<Studente, String> colonnaLibriPrestito;

    @FXML private Label registrostudenti;
    @FXML private Button homeRegistroStudenti;

    private ArchivioStudenti archivioStudenti;
    private ArchivioPrestiti archivioPrestiti;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        archivioStudenti = stato.getArchivioStudenti();
        archivioPrestiti = stato.getArchivioPrestiti();

        // ✅ Colonne dirette
        colonnaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colonnaCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        colonnaMatricola.setCellValueFactory(new PropertyValueFactory<>("matricola"));
        colonnaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // ✅ Colonna dinamica: libri presi in prestito
        colonnaLibriPrestito.setCellValueFactory(cellData -> {
            Studente s = cellData.getValue();

            List<Prestito> prestiti = archivioPrestiti.getPrestitiPerStudente(s);

            String titoli = prestiti.isEmpty()
                    ? "Nessuno"
                    : prestiti.stream()
                              .map(p -> p.getLibro().getTitolo())
                              .collect(Collectors.joining(", "));

            return new SimpleStringProperty(titoli);
        });

        // ✅ Carico i dati nella tabella
        ObservableList<Studente> studenti =
                FXCollections.observableArrayList(archivioStudenti.getTutti());

        tabellaStudenti.setItems(studenti);
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/view/BibliotecaInterfaccia.fxml")
            );
            Stage stage = (Stage) homeRegistroStudenti.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
