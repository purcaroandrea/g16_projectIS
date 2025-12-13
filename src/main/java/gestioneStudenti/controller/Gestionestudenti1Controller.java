package gestioneStudenti.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Gestionestudenti1Controller implements Initializable {

    @FXML private Button NuovoStudente;
    @FXML private Button RegistroStudenti;
    @FXML private Button RicercaStudente;
    @FXML private Button homeGestioneStudente;

    @FXML private Label labelgestionestudenti;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void aggiungiStudente(ActionEvent event) {
        caricaInterfaccia("/view/studenti/aggiungistudente.fxml");
    }

    @FXML
    private void visualizzaRegistroStudenti(ActionEvent event) {
        caricaInterfaccia("/view/studenti/registrostudenti1.fxml");
    }

    @FXML
    private void ricercaStudente(ActionEvent event) {
        caricaInterfaccia("/view/studenti/ricercastudente1.fxml");
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        caricaInterfaccia("/view/bibliotecainterfaccia1.fxml");
    }

    
    private void caricaInterfaccia(String percorsoFXML) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(percorsoFXML));
            Stage stage = (Stage) NuovoStudente.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
