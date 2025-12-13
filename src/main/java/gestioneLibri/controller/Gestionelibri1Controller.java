/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneLibri.controller;

import gestioneLibri.ArchivioLibri;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author g16_members
 */
public class Gestionelibri1Controller implements Initializable {

    @FXML
    private Label labelgestionelibri;
    @FXML
    private Button NuovoLibro;
    @FXML
    private Button RegistroLibri;
    @FXML
    private Button RicercaLibro;
    @FXML
    private Button homeGestioneLibri;
    
    
     public void setArchivio(ArchivioLibri archivio) {
        this.archivioCorrente = archivio;
        System.out.println("Gestionelibri1 - Archivio aggiornato con " + archivio.getTutti().size() + " libri.");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aggiungenuovolibro(ActionEvent event)  throws IOException {
    if (this.archivioCorrente == null) {
            this.archivioCorrente = new ArchivioLibri();
    }
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/view.libri/aggiungilibro1.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Gestione Biblioteca - Aggiungi Libro");
        window.show();
    }

    @FXML
    private void visualizzaregistrolibri(ActionEvent event)  throws IOException {
     if (this.archivioCorrente == null) {
        this.archivioCorrente = new ArchivioLibri();
    }

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestioneLibri/view/Registrolibri1.fxml"));
    Parent parent = loader.load();
    Registrolibri1Controller controller = loader.getController();
    controller.setArchivio(this.archivioCorrente); 
     Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(new Scene(parent));
    window.setTitle("Registro Libri");
    window.show();
    
    }

    
    

    @FXML
    private void ricercalibro(ActionEvent event) {
    System.out.println("Naviga verso Ricerca Libro.");
    }
    
    
     private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/bibliotecainterfaccia1.fxml"));
            Stage stage = (Stage) homeGestioneLibri.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    
    }
    
}
