/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionePrestiti;

import gestioneLibri.Libro;
import gestioneStudenti.Studente;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @file ArchivioPrestiti.java
 * @brief La classe gestisce l'archivio dei prestiti dei libri ai relativi
 * studenti.
 *
 * La classe permette di registrare prestiti e restituzioni, visualizzare i
 * prestiti attivi, effettuare ricerche e contare i prestiti attivi per
 * studente.
 *
 * @author g16_member
 * @date Dicembre 7, 2025
 *
 * @see Prestito
 */
public class ArchivioPrestiti {

    /**
     * @brief Mappa che associa a ciascuno studente la lista dei suoi prestiti
     * attivi. La chiave è la matricola dello studente.
     */
    private Map<String, List<Prestito>> prestitiPerStudente;

    /**
     * @brief Costruttore predefinito di ArchivioPrestiti.
     *
     * Inizializza la struttura dati interna.
     *
     * @post L'archivio dei prestiti è inizializzato e pronto all'uso.
     */
    public ArchivioPrestiti() {
        this.prestitiPerStudente = new HashMap<>();
    }

    /**
     * @brief Registra un nuovo prestito per uno studente.
     *
     * @pre studente e libro non devono essere null.
     * @pre Il libro deve avere almeno una copia disponibile.
     * @pre Lo studente non deve avere più di 3 prestiti attivi.
     * @pre dataPrestito e dataPrevistaRestituzione non devono essere null.
     * @param[in] studente Lo studente che prende in prestito il libro.
     * @param[in] libro Il libro da prestare.
     * @param[in] dataPrestito La data in cui avviene il prestito.
     * @param[in] dataPrevistaRestituzione La data prevista di restituzione.
     * @return Prestito L’oggetto Prestito creato, altrimenti null se il
     * prestito non può essere effettuato.
     * @post Il prestito viene registrato e le copie disponibili del libro
     * vengono aggiornate.
     * @see UC-11, IF-11
     */
    public Prestito registraPrestito(Studente studente, Libro libro, LocalDate dataPrestito, LocalDate dataPrevistaRestituzione) {
        return null; // da implementare
    }

    /**
     * @brief Registra la restituzione di un libro precedentemente preso in
     * prestito.
     *
     * @pre Il prestito deve essere presente tra i prestiti attivi.
     * @param[in] prestito Il prestito da chiudere.
     * @post Il libro risulta restituito, le copie disponibili aggiornate e il
     * prestito rimosso dai prestiti attivi.
     * @see UC-14, IF-14
     */
    public void registraRestituzione(Prestito prestito) {
        // da implementare
    }

    /**
     * @brief Restituisce la lista di tutti i prestiti attivi, ordinati per data
     * prevista di restituzione.
     *
     * @return List<Prestito> Lista dei prestiti attivi.
     * @post Lo stato dell'archivio non viene modificato.
     * @see UC-12, IF-12
     */
    public List<Prestito> getPrestitiPerData() {
        return null; // da implementare
    }

    /**
     * @brief Conta il numero di prestiti attivi di uno studente.
     *
     * @param[in] studente Lo studente da controllare.
     * @return int Numero di prestiti attivi.
     * @post Lo stato dell'archivio non viene modificato.
     */
    public int contaPrestitiAttivi(Studente studente) {
        return 0; // da implementare
    }

    /**
     * @brief Cerca prestiti attivi tramite cognome o matricola dello studente e
     * titolo del libro.
     *
     * @param[in] cognomeOMatricola Cognome o matricola dello studente.
     * @param[in] titoloLibro Titolo del libro preso in prestito.
     * @return List<Prestito> Lista dei prestiti che soddisfano i criteri.
     * @post Lo stato dell'archivio non viene modificato.
     * @see UC-13, IF-13
     */
    public List<Prestito> cercaPrestitiAttivi(String cognomeOMatricola, String titoloLibro) {
        return null; // da implementare
    }

    /**
     * @brief Restituisce tutti i prestiti associati allo studente specificato.
     *
     * @pre studente non deve essere null.
     * @param[in] studente Lo studente di cui si vogliono ottenere i prestiti.
     * @return List<Prestito> La lista di tutti i prestiti associati allo
     * studente. Se lo studente non ha prestiti registrati, viene restituita una
     * lista vuota.
     * @post L'archivio rimane invariato: nessun prestito viene modificato,
     * aggiunto o rimosso dall'archivio a seguito della chiamata.
     */
    public List<Prestito> getPrestitiPerStudente(Studente studente) {
        return null;
    }

    /**
     * @brief Restituisce la lista di tutti i prestiti attualmente registrati.
     *
     * È utilizzato per operazioni di controllo globale o salvataggio su file.
     *
     * @return List<Prestito> L'elenco di tutti i prestiti presenti
     * nell'archivio.
     * @post Lo stato interno dell’archivio non viene modificato.
     * @note La lista restituita potrebbe essere la lista interna; modifiche
     * esterne potrebbero influenzare l’archivio.
     */
    public List<Prestito> getTutti() {
        return null;
    }
}
