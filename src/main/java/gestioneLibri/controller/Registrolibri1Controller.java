/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneLibri.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author g16_members
 */
public class Registrolibri1Controller implements Initializable {

    @FXML
    private TableView<?> tviem;
    @FXML
    private TableColumn<?, ?> colonnaTitolo;
    @FXML
    private TableColumn<?, ?> colonnaAutore;
    @FXML
    private TableColumn<?, ?> colonnaISBN;
    @FXML
    private TableColumn<?, ?> colonnaAnnoPub;
    @FXML
    private TableColumn<?, ?> colonnaNumCopie;
    @FXML
    private Label registrolibri;
    @FXML
    private Button homeRegistroLibri;

    /**
     * Initializes the controller class.
     */
    public void setArchivio(ArchivioLibri archivio) {
        this.archivioCorrente = archivio;
        caricaDatiArchivio();
    }
    
    private void caricaDatiArchivio() {
        if (archivioCorrente == null) {
            System.err.println("Errore: ArchivioLibri non è stato iniettato correttamente.");
            return;
        }
        colonnaTitolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
        colonnaAutore.setCellValueFactory(new PropertyValueFactory<>("autore"));
        colonnaISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colonnaAnnoPub.setCellValueFactory(new PropertyValueFactory<>("annoPubblicazione"));
        colonnaNumCopie.setCellValueFactory(new PropertyValueFactory<>("copieDisponibili"));
        
        ObservableList<Libro> data = FXCollections.observableArrayList(
                archivioCorrente.getLibriOrdinatiPerTitolo() // Usiamo la lista ordinata per un output pulito
        );
        
        tviem.setItems(data);
    }
    
    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/bibliotecainterfaccia1.fxml"));
            Stage stage = (Stage) homeRegistroLibri.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
