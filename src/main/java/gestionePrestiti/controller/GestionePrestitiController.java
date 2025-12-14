package gestionePrestiti.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GestionePrestitiController implements Initializable {

    @FXML private Label labelgestioneprestito;
    @FXML private Button NuovoPrestito;
    @FXML private Button RegistroPrestiti;
    @FXML private Button RicercaPrestito;
    @FXML private Button homeGestionePrestiti;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelgestioneprestito.setText("Gestione Prestiti");
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        caricaInterfaccia("/view/BibliotecaInterfaccia.fxml");
    }

    @FXML
    private void nuovoPrestito(ActionEvent event) {
        caricaInterfaccia("/view/prestiti/AggiungiPrestito.fxml");
    }

    @FXML
    private void registroPrestiti(ActionEvent event) {
        caricaInterfaccia("/view/prestiti/RegistroPrestiti.fxml");
    }

    @FXML
    private void ricercaPrestito(ActionEvent event) {
        caricaInterfaccia("/view/prestiti/RicercaPrestito.fxml");
    }

    private void caricaInterfaccia(String percorsoFXML) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(percorsoFXML));
            Stage stage = (Stage) homeGestionePrestiti.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Nessun messaggio a schermo
        }
    }
}
