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

public class Gestioneprestiti1Controller implements Initializable {

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
        caricaInterfaccia("/view/bibliotecainterfaccia1.fxml");
    }

    @FXML
    private void nuovoPrestito(ActionEvent event) {
        caricaInterfaccia("/view/prestiti/aggiungiprestito1.fxml");
    }

    @FXML
    private void registroPrestiti(ActionEvent event) {
        caricaInterfaccia("/view/prestiti/registroprestiti1.fxml");
    }

    @FXML
    private void ricercaPrestito(ActionEvent event) {
        caricaInterfaccia("/view/prestiti/ricercaprestito1.fxml");
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
