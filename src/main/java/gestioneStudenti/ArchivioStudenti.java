/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneStudenti;

import java.util.ArrayList;
import java.util.List;

/**
 * @file ArchivioStudenti.java
 * @brief La classe gestisce l'archivio degli studenti registrati nel sistema.
 *
 * La classe permette di aggiungere, modificare, rimuovere studenti
 * e di eseguire ricerche per cognome o matricola.
 * Consente inoltre di ottenere una lista ordinata degli studenti.
 *
 * @author g16_member
 * @date Dicembre 7, 2025
 *
 * @see Studente
 */



public class ArchivioStudenti {
    /**
     * @brief Lista degli studenti presenti nell’archivio.
     */
    private List<Studente> studenti;

    /**
     * @brief Costruttore predefinito dell'archivio studenti.
     *
     * Inizializza la lista interna.
     * 
     * @post L'archivio è pronto all'uso ed è inizialmente vuoto.
     */
    public ArchivioStudenti() {
        this.studenti = new ArrayList<>();
    }
    
    /**
     * @brief Aggiunge un nuovo studente all’archivio.
     *
     * @pre Lo studente deve avere matricola valida e non già presente in archivio.
     * @param[in] studente Lo studente da aggiungere.
     * @post Lo studente viene inserito nell’archivio.
     */
    public void aggiungiStudente(Studente studente) {
        // da implementare
    }
    
    /**
     * @brief Modifica i dati di uno studente già presente in archivio.
     *
     * @pre La matricola dello studente modificato deve essere già registrata.
     * @param[in] studenteModificato L’oggetto studente con i nuovi dati.
     * @post I dati dello studente vengono aggiornati.
     */
    public void modificaStudente(Studente studenteModificato) {
        // da implementare
    }
    
    /**
     * @brief Rimuove uno studente dall’archivio.
     *
     * @pre Lo studente deve essere presente nell’archivio.
     * @param[in] studente Lo studente da rimuovere.
     * @post Lo studente non è più presente nell’archivio.
     */
    public void rimuoviStudente(Studente studente) {
        // da implementare
    }
    
    /**
     * @brief Restituisce la lista di studenti ordinati alfabeticamente.
     *
     * L’ordinamento è definito dal metodo compareTo di Studente.
     *
     * @return List<Studente> Lista ordinata degli studenti.
     * @post Lo stato interno dell’archivio non viene modificato.
     * @see Studente#compareTo(Studente)
     */
    public List<Studente> getStudentiOrdinati() {
        return null; // da implementare
    }
    
    /**
     * @brief Cerca studenti in base al cognome (match case-insensitive).
     *
     * @param[in] cognome Il cognome da cercare.
     * @return List<Studente> Lista degli studenti che soddisfano il criterio.
     * @post L’archivio rimane immutato.
     */
    public List<Studente> cercaPerCognome(String cognome) {
        return null; // da implementare
    }
    
    /**
     * @brief Cerca uno studente tramite matricola.
     *
     * @param[in] matricola La matricola dello studente ricercato.
     * @return Studente Lo studente trovato, oppure null se non esiste.
     * @post Lo stato interno dell’archivio non viene modificato.
     */
    public Studente cercaPerMatricola(String matricola) {
        return null; // da implementare
    }
}

