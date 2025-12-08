/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionePrestiti;

import gestioneLibri.Libro;
import gestioneStudenti.Studente;
import java.time.LocalDate;

/**
 * @file Prestito.java
 * @brief Rappresenta un singolo prestito di un libro in favore di uno studente.
 *
 * Contiene le informazioni relative allo studente, al libro, alle date di
 * prestito e restituzione e allo stato del prestito.
 *
 * @author g16_member
 * @date Dicembre 7, 2025
 */
public class Prestito {

    private Studente studente;
    private Libro libro;
    private LocalDate dataPrestito;
    private LocalDate dataRestituzione;
    private boolean restituito;

    /**
     * @brief Costruttore completo per inizializzare un prestito.
     *
     * @param[in] studente Lo studente che prende in prestito il libro.
     * @param[in] libro Il libro preso in prestito.
     * @param[in] dataPrestito Data in cui è stato effettuato il prestito.
     * @param[in] dataRestituzione Data prevista di restituzione.
     * @pre studente, libro, dataPrestito e dataRestituzione non devono essere
     * null.
     * @post Il prestito è inizializzato e segnato come non restituito.
     */
    public Prestito(Studente studente, Libro libro, LocalDate dataPrestito, LocalDate dataRestituzione) {
        this.studente = studente;
        this.libro = libro;
        this.dataPrestito = dataPrestito;
        this.dataRestituzione = dataRestituzione;
        this.restituito = false;
    }

// ----------- Getter & Setter -----------
    //@brief Restituisce lo studente associato al prestito.    
    public Studente getStudente() {
        return studente;
    }

    // @brief Restituisce il libro preso in prestito.
    public Libro getLibro() {
        return libro;
    }

    //@brief Restituisce la data in cui è stato ottenuto il prestito.
    public LocalDate getDataPrestito() {
        return dataPrestito;
    }

    // @brief Restituisce la data prevista di restituzione.
    public LocalDate getDataRestituzione() {
        return dataRestituzione;
    }

    /**
     * @brief Indica se il prestito è stato restituito.
     * @return boolean True se il libro è stato restituito.
     */
    public boolean isRestituito() {
        return restituito;
    }

    /**
     * @brief Imposta lo studente associato al prestito.
     * @param[in] studente Il nuovo studente da associare.
     */
    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    /**
     * @brief Imposta il libro associato al prestito.
     * @param[in] libro Il nuovo libro da associare.
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * @brief Imposta la data di inizio del prestito.
     * @param[in] dataPrestito La nuova data di prestito.
     */
    public void setDataPrestito(LocalDate dataPrestito) {
        this.dataPrestito = dataPrestito;
    }

    /**
     * @brief Imposta la data prevista di restituzione del libro.
     * @param[in] dataRestituzione La nuova data di restituzione.
     */
    public void setDataRestituzione(LocalDate dataRestituzione) {
        this.dataRestituzione = dataRestituzione;
    }

    /**
     * @brief Imposta lo stato di restituzione del prestito.
     * @param[in] restituito True per indicare che il libro è stato restituito.
     */
    public void setRestituito(boolean restituito) {
        this.restituito = restituito;
    }

// ----------- Metodi di stato -----------
    /**
     * @brief Controlla se il prestito è ancora attivo (non restituito).
     *
     * @return boolean True se il prestito non è ancora restituito.
     * @post Lo stato dell'oggetto non viene modificato.
     * @see IF-11, UC-11
     */
    public boolean isAttivo() {
        return false; // da implementare
    }

    /**
     * @brief Controlla se il prestito è scaduto rispetto a una data odierna.
     *
     * @param[in] oggi La data di riferimento.
     * @return boolean True se il prestito è scaduto.
     * @post Lo stato dell'oggetto non viene modificato.
     */
    public boolean isScaduto(LocalDate oggi) {
        return false; // da implementare
    }

    /**
     * @brief Controlla se il prestito è in scadenza entro una soglia di giorni.
     *
     * @param[in] oggi La data di riferimento.
     * @param[in] sogliaGiorni Numero di giorni entro cui considerare la
     * scadenza.
     * @return boolean True se la data di restituzione è entro la soglia.
     * @post Lo stato dell'oggetto non viene modificato.
     */
    public boolean isInScadenza(LocalDate oggi, int sogliaGiorni) {
        return false; // da implementare
    }
}
