package gestioneStudenti.controller;

import gestionePrestiti.ArchivioPrestiti;
import gestioneStudenti.Studente;
import gestioneStudenti.ArchivioStudenti;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import persistence.GestoreStatoBiblioteca;
import persistence.StatoBiblioteca;

public class SelezionaStudenteController implements Initializable {

    @FXML private Label ricercastudente2;
    @FXML private Button homeRimozioneStudente;

    @FXML private ListView<Studente> listaStudenti;

    @FXML private Button bottoneModificaStudente;
    @FXML private Button bottoneRimuoviStudente;

    private ArchivioStudenti archivio;
    private Studente studenteCorrente;

    private List<Studente> risultatiRicerca;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        this.archivio = stato.getArchivioStudenti();

        ricercastudente2.setText("Ricerca Studente");

        // ✅ Bottoni disabilitati finché non selezioni uno studente
        bottoneModificaStudente.setDisable(true);
        bottoneRimuoviStudente.setDisable(true);

        // ✅ Listener sulla selezione della ListView
        listaStudenti.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            studenteCorrente = newVal;

            if (newVal != null) {
                bottoneModificaStudente.setDisable(false);
                bottoneRimuoviStudente.setDisable(false);
            } else {
                bottoneModificaStudente.setDisable(true);
                bottoneRimuoviStudente.setDisable(true);
            }
        });
    }

    // ✅ Chiamato da Ricercastudente1Controller per passare 1 studente
   public void setStudente(Studente s) {
        risultatiRicerca = Arrays.asList(s);
        aggiornaLista();
    }

    // ✅ Chiamato da Ricercastudente1Controller per passare più studenti
    public void setStudenti(List<Studente> lista) {
        risultatiRicerca = lista;
        aggiornaLista();
    }

    private void aggiornaLista() {
        ObservableList<Studente> dati = FXCollections.observableArrayList(risultatiRicerca);
        listaStudenti.setItems(dati);
    }

    @FXML
    private void modificaStudente(ActionEvent event) {
        if (studenteCorrente == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/studenti/ModificaStudente.fxml")
            );

            Parent root = loader.load();

            ModificaStudenteController controller = loader.getController();
            controller.setStudenteDaModificare(studenteCorrente);

            Stage stage = (Stage) bottoneModificaStudente.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            // Nessun messaggio a schermo, solo log
            e.printStackTrace();
        }
    }

    @FXML
    private void rimuoviStudente(ActionEvent event) {
        if (studenteCorrente == null) return;
        
         // ✅ Recupero archivio prestiti
        StatoBiblioteca stato = GestoreStatoBiblioteca.getInstance().getStato();
        ArchivioPrestiti archivioPrestiti = stato.getArchivioPrestiti();


        // ✅ Controllo prestiti attivi
        boolean haPrestitiAttivi = archivioPrestiti.contaPrestitiAttivi(studenteCorrente) > 0;

        if (haPrestitiAttivi) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Operazione non consentita");
            alert.setHeaderText(null);
            alert.setContentText("Impossibile rimuovere lo studente: ha prestiti attivi.");
            alert.showAndWait();
            return;
        }

        try {
            archivio.rimuoviStudente(studenteCorrente);
            GestoreStatoBiblioteca.getInstance().salva();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rimozione completata");
            alert.setHeaderText(null);
            alert.setContentText("Studente rimosso con successo.");
            alert.showAndWait();

            risultatiRicerca.remove(studenteCorrente);
            aggiornaLista();

            studenteCorrente = null;
            bottoneModificaStudente.setDisable(true);
            bottoneRimuoviStudente.setDisable(true);

        } catch (Exception ex) {
            ricercastudente2.setStyle("-fx-text-fill: red;");
            ricercastudente2.setText("Errore: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    private void tornaAllaHome(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/view/BibliotecaInterfaccia.fxml")
            );
            Stage stage = (Stage) homeRimozioneStudente.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            // Nessun messaggio a schermo
            e.printStackTrace();
        }
    }
}
