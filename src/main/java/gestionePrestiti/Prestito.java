/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionePrestiti;

import gestioneLibri.Libro;
import gestioneStudenti.Studente;
import java.time.LocalDate;
import java.io.Serializable;



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
public class Prestito implements Serializable{
    
    private static final long serialVersionUID = 1L;
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
        if (studente == null) {
            throw new IllegalArgumentException("Lo studente non può essere null.");
        }
        this.studente = studente;
    }

    /**
     * @brief Imposta il libro associato al prestito.
     * @param[in] libro Il nuovo libro da associare.
     */
    public void setLibro(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("Il libro non può essere null.");
        }
        this.libro = libro;
    }

    /**
     * @brief Imposta la data di inizio del prestito.
     * @param[in] dataPrestito La nuova data di prestito.
     */
    public void setDataPrestito(LocalDate dataPrestito) {
        if (dataPrestito == null) {
            throw new IllegalArgumentException("La data del prestito non può essere null.");
        }
        this.dataPrestito = dataPrestito;
    }

    /**
     * @brief Imposta la data prevista di restituzione del libro.
     * @param[in] dataRestituzione La nuova data di restituzione.
     */
    public void setDataRestituzione(LocalDate dataRestituzione) {
        if (dataRestituzione == null) {
            throw new IllegalArgumentException("La data di restituzione non può essere null.");
        }
        if (this.dataPrestito != null && dataRestituzione.isBefore(this.dataPrestito)) {
            throw new IllegalArgumentException("La data di restituzione non può precedere la data del prestito.");
        }
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
        return !restituito;
    }

    /**
     * @brief Controlla se il prestito è scaduto rispetto a una data odierna.
     *
     * @param[in] oggi La data di riferimento.
     * @return boolean True se il prestito è scaduto.
     * @post Lo stato dell'oggetto non viene modificato.
     */
    public boolean isScaduto(LocalDate oggi) {
        if (oggi == null) {
            throw new IllegalArgumentException("La data di riferimento non può essere null.");
        }
        return !restituito && dataRestituzione.isBefore(oggi);
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
        if (oggi == null) {
            throw new IllegalArgumentException("La data di riferimento non può essere null.");
        }
        if (sogliaGiorni < 0) {
            throw new IllegalArgumentException("La soglia di giorni non può essere negativa.");
        }

        if (restituito) {
            return false;
        }

        LocalDate limite = oggi.plusDays(sogliaGiorni);
        return (!dataRestituzione.isBefore(oggi)) && (!dataRestituzione.isAfter(limite));
    }
    
    /**
     * @brief Restituisce una rappresentazione testuale del prestito.
     *
     * @return String descrizione leggibile del prestito
     */
    @Override
public String toString() {
    return String.format(
        "%s | %s %s | %s → %s | %s",
        libro.getTitolo(),
        studente.getCognome(),
        studente.getNome(),
        dataPrestito,
        dataRestituzione,
        restituito ? "CHIUSO" : "ATTIVO"
    );
}

}
