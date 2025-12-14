package gestionePrestiti.controller;

import gestionePrestiti.Prestito;
import gestionePrestiti.ArchivioPrestiti;
import persistence.GestoreStatoBiblioteca;
import persistence.StatoBiblioteca;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class RicercaPrestitoController implements Initializable {

    @FXML private Label ricercaprestito;
    @FXML private Label labelLibro;
    @FXML private Label labelStudente;

    @FXML private TextField barraRicercaPrestitoISBN;
    @FXML private TextField barraRicercaPrestitoMatCogn;

    @FXML private Button bottoneRicercaPrestito;
    @FXML private Button homeRicercaPrestito;

    private ArchivioPrestiti archivio;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        barraRicercaPrestitoISBN.clear();
        barraRicercaPrestitoMatCogn.clear();

        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        archivio = stato.getArchivioPrestiti();

        ricercaprestito.setText("Ricerca Prestito");
    }

    @FXML
    private void eseguiRicerca(ActionEvent event) {
        String inputLibro = barraRicercaPrestitoISBN.getText().trim();
        String inputStudente = barraRicercaPrestitoMatCogn.getText().trim();

        if (inputLibro.isEmpty() || inputStudente.isEmpty()) {
            ricercaprestito.setStyle("-fx-text-fill: red;");
            ricercaprestito.setText("Inserisci entrambi i campi.");
            return;
        }

        try {
            List<Prestito> risultati = archivio.cercaPrestitiAttivi(inputStudente, inputLibro);

            if (risultati.isEmpty()) {
                ricercaprestito.setStyle("-fx-text-fill: red;");
                ricercaprestito.setText("Nessun prestito trovato.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/prestiti/SelezionaPrestito.fxml")
            );
            Parent root = loader.load();

            SelezionaPrestitoController controller = loader.getController();
            controller.setPrestiti(risultati);

            Stage stage = (Stage) bottoneRicercaPrestito.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception ex) {
            ricercaprestito.setStyle("-fx-text-fill: red;");
            ricercaprestito.setText("Errore: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/view/BibliotecaInterfaccia.fxml")
        );
        Parent root = loader.load();
        Stage stage = (Stage) homeRicercaPrestito.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
