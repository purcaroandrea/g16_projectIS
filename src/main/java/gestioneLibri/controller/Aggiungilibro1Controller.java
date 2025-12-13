/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneLibri.controller;

import gestioneLibri.ArchivioLibri;
import gestioneLibri.Libro;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author g16_members
 */
public class Aggiungilibro1Controller implements Initializable {

    @FXML
    private Label labelaggiungilibro;
    @FXML
    private Label titolo;
    @FXML
    private Label autore;
    @FXML
    private Label ISBN;
    @FXML
    private Label annodipub;
    @FXML
    private Label numcopie;
    @FXML
    private Button bottoneConfermaLibri;
    @FXML
    private Button homeAggiungiLibro;
    @FXML
    private TextField testoTitolo;
    @FXML
    private TextField testoAutore;
    @FXML
    private TextField testoISBN;
    @FXML
    private TextField testoAnnoPub;
    @FXML
    private TextField testoNumCopie;
    
    
     public void setArchivio(ArchivioLibri archivio) {
        this.archivioCorrente = archivio;
        System.out.println("Archivio ricevuto: " + archivio.getTutti().size() + " libri presenti.");
    }        
     
     private void goToGestioneLibri(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestioneLibri/view/Gestionelibri1.fxml"));
        Parent parent = loader.load();
        Gestionelibri1Controller controller = loader.getController();
        controller.setArchivio(this.archivioCorrente);

        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Gestione Biblioteca - Gestione Libri");
        window.show();
    }
    
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/bibliotecainterfaccia1.fxml"));
            Stage stage = (Stage) homeAggiungiLibro.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
    
    private void mostraAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
                    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(testoTitolo.getText().trim().isEmpty()|| testoAutore.getText().trim().isEmpty()|| testoISBN.getText().trim().isEmpty() || testoAnnoPub.getText().trim().isEmpty() ||testoNumCopie.getText().trim().isEmpty()){
            System.out.println("ERRORE: devi compilare tutti i campi");
            return;
            
        }
    }

    @FXML
    private void confermalibri(ActionEvent event) {
        BooleanBinding ex= Bindings.or(testoTitolo.getText().trim().isEmpty() || testoAutore.getText().trim().isEmpty() || 
            testoISBN.getText().trim().isEmpty() || testoAnnoPub.getText().trim().isEmpty() || 
            testoNumCopie.getText().trim().isEmpty()); {
            
            bottoneConfermaLibri.disableProperty().bind(ex);
              return;
        }
        try {
            String titolo = testoTitolo.getText().trim();
            String autore = testoAutore.getText().trim();
            String isbn = testoISBN.getText().trim();
            int annoPubblicazione = Integer.parseInt(testoAnnoPub.getText().trim());
            int copieDisponibili = Integer.parseInt(testoNumCopie.getText().trim());
            
            Libro nuovoLibro = new Libro(titolo, autore, annoPubblicazione, isbn, copieDisponibili);
            
            System.out.println("Libro aggiunto con successo: " + nuovoLibro.toString());
            
            if (archivioCorrente == null) {
                throw new IllegalStateException("L'archivio non è stato iniettato correttamente.");
            }
            archivioCorrente.aggiungiLibro(nuovoLibro);
            
            mostraAlert(AlertType.INFORMATION, "Successo", 
                       "Libro Aggiunto", 
                       "Il libro \"" + nuovoLibro.getTitolo() + "\" è stato aggiunto correttamente all'archivio.");
            
            goToGestioneLibri(event);
            
           
        }
        
         catch (NumberFormatException e) {
             mostraAlert(AlertType.ERROR, "Errore di Formato", 
                       "Input Numerico Non Valido", 
                       "Anno di pubblicazione e Numero di copie devono essere numeri interi validi.");
         } 
        catch (IllegalArgumentException e) {
        
             mostraAlert(AlertType.ERROR, "Errore di Validazione", 
                       "Dati Non Validi", 
                       "Si è verificato un errore: " + e.getMessage());
         }
        catch (IOException e) {
            mostraAlert(AlertType.ERROR, "Errore I/O", 
                       "Errore di Navigazione", 
                       "Impossibile caricare la vista successiva.");
        } 
        catch (IllegalStateException e) {
            mostraAlert(AlertType.ERROR, "Errore Interno", 
                       "Archivio Mancante", 
                       "Impossibile salvare il libro. " + e.getMessage());
        }
    
        
    }
    
}
