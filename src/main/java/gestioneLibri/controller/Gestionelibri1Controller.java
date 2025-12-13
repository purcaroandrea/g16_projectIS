package gestioneLibri.controller;

import gestioneLibri.ArchivioLibri;
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

public class Gestionelibri1Controller implements Initializable {

    @FXML private Label labelgestionelibri;
    @FXML private Button NuovoLibro;
    @FXML private Button RegistroLibri;
    @FXML private Button RicercaLibro;
    @FXML private Button homeGestioneLibri;

    private ArchivioLibri archivioCorrente;

    public void setArchivio(ArchivioLibri archivio) {
        this.archivioCorrente = archivio;
        System.out.println("Gestionelibri1 - Archivio aggiornato con " + archivio.getTutti().size() + " libri.");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelgestionelibri.setText("Gestione Libri");
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        caricaInterfaccia("/view/bibliotecainterfaccia1.fxml");
    }

    @FXML
    private void nuovoLibro(ActionEvent event) {
        caricaInterfaccia("/view/libri/aggiungilibro1.fxml");
    }

    @FXML
    private void registroLibri(ActionEvent event) {
        caricaInterfaccia("/view/libri/registrolibri1.fxml");
    }

    @FXML
    private void ricercaLibro(ActionEvent event) {
        caricaInterfaccia("/view/libri/ricercalibro1.fxml");
    }

    private void caricaInterfaccia(String percorsoFXML) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(percorsoFXML));
            Stage stage = (Stage) homeGestioneLibri.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Nessun messaggio a schermo
        }
    }
}
