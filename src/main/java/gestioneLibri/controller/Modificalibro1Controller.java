/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneLibri.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author g16_members
 */
public class Modificalibro1Controller implements Initializable {

    @FXML
    private Label labelmodificalibro;
    @FXML
    private Label titolomodifica;
    @FXML
    private Label autoremodifica;
    @FXML
    private Label ISBNmodifica;
    @FXML
    private Label annodipubmodifica;
    @FXML
    private Label numcopiemodifica;
    @FXML
    private Button confermalibrimodifica;
    @FXML
    private Button homeModificaLibro;

     public void setArchivio(ArchivioLibri archivio) {
        this.archivioCorrente = archivio;
    }
    public void setLibro(Libro libro) {
        this.libroDaModificare = libro;
        popolaCampi(libro);
    }
    
    private void popolaCampi(Libro libro) {
        if (libro != null) {
            testoTitoloModifica.setText(libro.getTitolo());
            testoAutoreModifica.setText(libro.getAutore());
            testoISBNModifica.setText(libro.getIsbn());
            testoAnnoPubModifica.setText(String.valueOf(libro.getAnnoPubblicazione()));
            testoNumCopieModifica.setText(String.valueOf(libro.getCopieDisponibili()));
            
            testoISBNModifica.setEditable(false); 
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         BooleanBinding isAnyFieldEmpty = Bindings.createBooleanBinding(() -> {
        return testoTitolo.getText().trim().isEmpty() ||
               testoAutore.getText().trim().isEmpty() ||
               testoISBN.getText().trim().isEmpty() ||
               testoAnnoPub.getText().trim().isEmpty() ||
               testoNumCopie.getText().trim().isEmpty();
    }, 
    testoTitolo.textProperty(),
    testoAutore.textProperty(),
    testoISBN.textProperty(),
    testoAnnoPub.textProperty(),
    testoNumCopie.textProperty()
    );
    }    
    
     @FXML
    private void confermalibrimodifica(ActionEvent event) {
        
        if (testoTitoloModifica.getText().trim().isEmpty() || testoAutoreModifica.getText().trim().isEmpty() || 
            testoAnnoPubModifica.getText().trim().isEmpty() || testoNumCopieModifica.getText().trim().isEmpty()) {
            
            mostraAlert(AlertType.ERROR, "Errore di Input", 
                       "Compilazione Obbligatoria", 
                       "Devi compilare tutti i campi per poter modificare il libro.");
            return;
        }

        try {
            if (libroDaModificare == null || archivioCorrente == null) {
                throw new IllegalStateException("Riferimento all'archivio o al libro mancante.");
            }
            
            String nuovoTitolo = testoTitoloModifica.getText().trim();
            String nuovoAutore = testoAutoreModifica.getText().trim();
            int nuovoAnno = Integer.parseInt(testoAnnoPubModifica.getText().trim());
            int nuoveCopie = Integer.parseInt(testoNumCopieModifica.getText().trim());

             Libro libroModificato = new Libro(
                nuovoTitolo, 
                nuovoAutore, 
                nuovoAnno, 
                libroDaModificare.getIsbn(), // Manteniamo il vecchio ISBN
                nuoveCopie
            );
            
            archivioCorrente.modificaLibro(libroModificato);
            
            mostraAlert(AlertType.INFORMATION, "Successo", 
                       "Libro Modificato", 
                       "Il libro è stato aggiornato correttamente nell'archivio.");
            
            goToGestioneLibri(event); // Torna alla pagina di Gestione Libri

        } catch (NumberFormatException e) {
            mostraAlert(AlertType.ERROR, "Errore di Formato", 
                       "Input Numerico Non Valido", 
                       "Anno di pubblicazione e Numero di copie devono essere numeri interi validi.");
        } catch (IllegalArgumentException e) {
            // Cattura la validazione logica (es. anno non valido) dai setter di Libro
            mostraAlert(AlertType.ERROR, "Errore di Validazione", 
                       "Dati Non Validi", 
                       "Si è verificato un errore: " + e.getMessage());
        } catch (NoSuchElementException e) {
            mostraAlert(AlertType.ERROR, "Errore Archivio", 
                       "Libro non Trovato", 
                       "Il libro non esiste più nell'archivio per la modifica.");
        } catch (IOException e) {
            mostraAlert(AlertType.ERROR, "Errore I/O", 
                       "Errore di Navigazione", 
                       "Impossibile caricare la vista successiva.");
        } catch (IllegalStateException e) {
             mostraAlert(AlertType.ERROR, "Errore Interno", 
                       "Riferimenti Mancanti", 
                       "Assicurarsi che l'archivio e il libro siano stati iniettati.");
        }
    }
    private void goToGestioneLibri(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view.libri/gestionelibri1.fxml"));
        Parent parent = loader.load();
        
        // PASSA L'ARCHIVIO AL CONTROLLER SUCCESSIVO
        Gestionelibri1Controller controller = loader.getController();
        controller.setArchivio(this.archivioCorrente);

        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.setTitle("Gestione Biblioteca - Gestione Libri");
        window.show();
    }
    private void mostraAlert(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    
}
