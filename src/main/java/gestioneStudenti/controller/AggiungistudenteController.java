/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneStudenti.controller;

import gestioneStudenti.Studente;
import gestioneStudenti.ArchivioStudenti;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author laura
 */
public class AggiungistudenteController implements Initializable {

    @FXML
    private Button bottoneConfermaStudente;
    @FXML
    private Label labelaggiuntastudente;
    @FXML
    private Label labelnome;
    @FXML
    private Label labelcognome;
    @FXML
    private Label labelmatricola;
    @FXML
    private Label labelemail;
    
    // campi del form
    @FXML
    private TextField testoNome;
    @FXML
    private TextField testoCognome;
    @FXML
    private TextField testoMatricola;
    @FXML
    private TextField testoEmail;
    
    @FXML private Label messageLabel;

    private ArchivioStudenti archivio;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (messageLabel != null) messageLabel.setText("");
        if (testoNome != null) testoNome.setPromptText("Nome");
        if (testoCognome != null) testoCognome.setPromptText("Cognome");
        if (testoMatricola != null) testoMatricola.setPromptText("Matricola");
        if (testoEmail != null) testoEmail.setPromptText("Email");
    }    

    public void setArchivio(ArchivioStudenti archivio) {
        this.archivio = archivio;
    }
    
    @FXML
    private void confermastudenti(ActionEvent event) {
        messageLabel.setText("");
        try {
            String nome = testoNome.getText();
            String cognome = testoCognome.getText();
            String matricola = testoMatricola.getText();
            String email = testoEmail.getText();

            Studente s = new Studente(nome, cognome, matricola, email);

            if (archivio != null) {
                archivio.aggiungiStudente(s);
            }

            messageLabel.setStyle("-fx-text-fill: green;");
            messageLabel.setText("Studente creato: " + s.toString());

            testoNome.clear();
            testoCognome.clear();
            testoMatricola.clear();
            testoEmail.clear();

        } catch (IllegalArgumentException ex) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Errore: " + ex.getMessage());
        } catch (Exception ex) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Errore imprevisto.");
            ex.printStackTrace();
        }
    }
    }
    
