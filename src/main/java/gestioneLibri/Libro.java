/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneLibri;

/**
 * @file Libro.java
 * @brief Rappresenta un singolo oggetto Libro all'interno della biblioteca.
 *
 * Questa classe contiene tutti gli attributi del libro (titolo, autore, anno di
 * pubblicazione, ISBN, copie) e implementa l'interfaccia Comparable per
 * permettere l'ordinamento.
 *
 * @author g16_member
 * @date Dicembre 6, 2025
 *
 */
/**
 * @brief Il titolo del libro.
 * @brief L'autore del libro.
 * @brief Il codice ISBN del libro, usato come identificativo unico.
 * @brief L'anno di pubblicazione del libro (valore numerico).
 * @brief Il numero di copie attualmente disponibili per il prestito.
 */
public class Libro implements Comparable<Libro> {

    private String titolo;
    private String autore;
    private int annoPubblicazione;
    private String isbn;
    private int copieDisponibili;

    /**
     * @brief Costruttore completo per inizializzare un oggetto Libro.
     *
     * @param[in] titolo Il titolo del libro.
     * @param[in] autore L'autore del libro.
     * @param[in] isbn Il codice identificativo ISBN (stringa).
     * @param[in] annoPubblicazione L'anno in cui il libro è stato pubblicato.
     * @param[in] copieDisponibili Il numero di copie attualmente non in
     * prestito.
     *
     * @pre I parametri stringa non devono essere null o vuoti.
     * @post L'oggetto Libro è inizializzato con i valori forniti.
     */
    public Libro(String titolo, String autore, int annoPubblicazione, String isbn, int copieDisponibili) {
        this.titolo = titolo;
        this.autore = autore;
        this.annoPubblicazione = annoPubblicazione;
        this.isbn = isbn;
        this.copieDisponibili = copieDisponibili;
    }

    //@brief Restituisce il titolo del libro.
    public String getTitolo() {
        return titolo;
    }

    //@brief Restituisce l'autore del libro.
    public String getAutore() {
        return autore;
    }

    //@brief Restituisce l'anno di pubblicazione del libro.
    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    //@brief Restituisce l'ISBNdel libro.
    public String getIsbn() {
        return isbn;
    }

    //@brief Restituisce le copie disponibili del libro.
    public int getCopieDisponibili() {
        return copieDisponibili;
    }

    /**
     * @brief Imposta un nuovo titolo per il libro.
     *
     * @pre La stringa del titolo non deve essere null o vuota.
     * @param[in] titolo Il nuovo titolo da assegnare al libro.
     * @post L'attributo 'titolo' dell'oggetto è stato aggiornato.
     */
    public void setTitolo(String titolo) {
       if (titolo == null || titolo.trim().isEmpty()) {
            throw new IllegalArgumentException("Il titolo non può essere null o vuoto.");
        }
        this.titolo = titolo.trim();
    }

    /**
     * @brief Imposta un nuovo autore per il libro.
     *
     * @pre La stringa dell'autore non deve essere null o vuota.
     * @param[in] autore Il nuovo autore da assegnare al libro.
     * @post L'attributo 'autore' dell'oggetto è stato aggiornato.
     */
    public void setAutore(String autore) {
       if (autore == null || autore.trim().isEmpty()) {
            throw new IllegalArgumentException("L'autore non può essere null o vuoto.");
        }
        this.autore = autore.trim();
    }

    /**
     * @brief Imposta un nuovo anno di pubblicazione per il libro.
     *
     * @pre L'intero non deve essere minore o uguale a 0.
     * @param[in] annoPubblicazione Il nuovo anno di pubblicazione da assegnare
     * al libro.
     * @post L'attributo 'annoPubblicazione' dell'oggetto è stato aggiornato.
     */
    public void setAnnoPubblicazione(int annoPubblicazione) {
       if (annoPubblicazione <= 0) {
            throw new IllegalArgumentException("Anno di pubblicazione non valido.");
        }
        this.annoPubblicazione = annoPubblicazione;
    }

    /**
     * @brief Imposta un nuovo isbn per il libro.
     *
     * @pre La stringa dell' isbn non deve essere null o vuota.
     * @param[in] isbn Il nuovo isbn da assegnare al libro.
     * @post L'attributo 'isbn' dell'oggetto è stato aggiornato.
     */
    public void setIsbn(String isbn) {
       if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ISBN non può essere null o vuoto.");
        }
        this.isbn = isbn.trim();
    }

    /**
     * @brief Imposta un nuovo numero di copie disponibili per il libro.
     *
     * @pre L'intero delle copie disponibili non deve essere minore o uguale a
     * 0.
     * @param[in] copieDisponibili Le nuove copie disponibili da assegnare al
     * libro.
     * @post L'attributo 'copieDisponibili' dell'oggetto è stato aggiornato.
     */
    public void setCopieDisponibili(int copieDisponibili) {
      if (copieDisponibili < 0) {
            throw new IllegalArgumentException("Il numero di copie disponibili non può essere negativo.");
        }
        this.copieDisponibili = copieDisponibili;
    }

    /**
     * @brief Verifica se ci sono copie del libro disponibili per il prestito.
     *
     * @return boolean True se 'copieDisponibili' è maggiore di 0, altrimenti
     * False.
     * @post Lo stato interno dell'oggetto non viene modificato.
     */
    public boolean haCopie() {
        return copieDisponibili > 0;
    }

    /**
     * @brief Diminuisce di una unità il contatore delle copie disponibili.
     *
     * Usato tipicamente al momento del prestito di un libro.
     *
     * @pre 'copieDisponibili' deve essere maggiore di 0.
     * @post 'copieDisponibili' è diminuito di 1.
     */
    public void decrementaCopie() {
        if (copieDisponibili <= 0) {
            throw new IllegalStateException("Impossibile decrementare: nessuna copia disponibile per il prestito.");
        }
        copieDisponibili--;
    }

    /**
     * @brief Aumenta di una unità il contatore delle copie disponibili.
     *
     * Usato tipicamente al momento della restituzione di un libro.
     *
     * @post 'copieDisponibili' è aumentato di 1.
     */
    public void incrementaCopie() {
        copieDisponibili++;
    }

    /**
     * @brief Restituisce una rappresentazione in formato stringa dell'oggetto
     * Libro.
     *
     * Utile per la stampa a console o per il debugging.
     *
     * @return String Una stringa che descrive l'oggetto (Titolo, Autore, ISBN).
     * @post Lo stato interno dell'oggetto non viene modificato.
     */
    @Override
    public String toString() {
        return titolo + " - " + autore + " - " + isbn;
    }

    /**
     * @brief Confronta questo oggetto Libro con un altro oggetto.
     *
     * Il confronto è basato sul codice ISBN, che è l'identificativo unico.
     *
     * @param[in] o L'oggetto da confrontare.
     * @return boolean True se l'ISBN dell'oggetto è uguale a quello del
     * parametro, altrimenti False.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Libro)) {
            return false;
        }
        Libro libro = (Libro) o;
        return isbn != null && isbn.equalsIgnoreCase(libro.isbn);
    }

    /**
     * @brief Genera l'hash code per l'oggetto Libro.
     *
     * L'hash code viene generato unicamente basandosi sul codice ISBN, in
     * accordo con il metodo equals().
     *
     * @return int L'hash code dell'oggetto.
     * @post Lo stato interno dell'oggetto non viene modificato.
     */
    @Override
    public int hashCode() {
        return isbn != null ? isbn.toLowerCase().hashCode() : 0;
    }

    /**
     * @brief Confronta questo Libro con l'oggetto specificato per
     * l'ordinamento.
     *
     * L'ordinamento naturale è definito comparando i titoli dei due libri.
     *
     * @param[in] altroLibro L'oggetto Libro da confrontare.
     * @return int Un valore negativo se il titolo di questo libro precede
     * quello dell'altro, 0 se i titoli sono uguali, un valore positivo
     * altrimenti.
     * @post Lo stato interno dell'oggetto non viene modificato.
     */
    @Override
    public int compareTo(Libro altro) {
        return this.titolo.compareToIgnoreCase(altro.titolo);
    }

}
