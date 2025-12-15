package biblioteca.controller;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BibliotecaInterfacciaController implements Initializable {

    @FXML private Label labelbiblioteca;
    @FXML private Button Libri;
    @FXML private Button Studenti;
    @FXML private Button Prestiti;
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelbiblioteca.setText("Biblioteca Universitaria");
    }

    @FXML
    private void gestioneLibri(ActionEvent event) {
        caricaInterfaccia("/view/libri/GestioneLibri.fxml");
    }

    @FXML
    private void gestioneStudenti(ActionEvent event) {
        caricaInterfaccia("/view/studenti/GestioneStudenti.fxml");
    }

    @FXML
    private void gestionePrestiti(ActionEvent event) {
        caricaInterfaccia("/view/prestiti/GestionePrestiti.fxml");
    }

    private void caricaInterfaccia(String percorsoFXML) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(percorsoFXML));
            Stage stage = (Stage) Libri.getScene().getWindow(); // qualsiasi bottone va bene
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Nessun alert, solo log
        }
    }
    
  
   
}
