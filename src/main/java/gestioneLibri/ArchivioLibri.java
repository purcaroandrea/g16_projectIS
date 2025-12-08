/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneLibri;

import java.util.ArrayList;
import java.util.List;

/**
 * @file ArchivioLibri.java
 * @brief La classe rappresenta e gestisce un archivio di oggetti Libro.
 * 
 * Contiene i metodi per l'aggiunta, la modifica, la rimozione e 
 * la ricerca di libri all'interno della collezione interna.
 * 
 * @date Dicembre 6,2025
 * @author g16_member
 *  
 * @see Libro
 */
public class ArchivioLibri {

    /**
    * @brief La lista di oggetti Libro mantenuta nell'archivio.
    * Questa lista viene inizializzata nel costruttore
    */
    private List<Libro> libri;
    
    /**
    * @brief Costruttore predefinito della classe ArchivioLibri.
    * 
    * Inizializza la struttura dati interna per la gestione dei libri.
    * 
    * @post La lista interna 'libri' è stata inizializzata e non è null.
    */
    public ArchivioLibri() {
        this.libri = new ArrayList<>();
    }
    
    /**
    * @brief Aggiunge un libro all'archivio.
    * 
    * @pre Il parametro 'libro' non deve essere null.
    * @param[in] libro L'oggetto Libro da inserire nell'archivio.
    * @post La dimensione dell'archivio è aumentata di uno e l'oggetto libro è presente nella lista.
    */
    public void aggiungiLibro(Libro libro) {
        // da implementare
    }
    
    /**
    * @brief Modifica i dati di un libro presente nell'archivio.
    * 
    *@pre Il libro da modificare deve già esistere nell'archivio.
    * @param[in] libro LibroModificato L'oggetto Libro con i nuovi dati.
    * @post  Il libro corrispondente nell'archivio è stato aggiornato con i nuovi dati.
    */
    public void modificaLibro(Libro libroModificato) {
        // da implementare
    }
    
    /**
    * @brief Rimuove un oggetto Libro dall'archivio.
    *
    * @pre Il parametro 'libro' non deve essere null.
    * @param[in] libro L'oggetto Libro da rimuovere dall'archivio.
    * @post Se l'oggetto era presente, è stato rimosso dalla lista interna e 
    * la dimensione dell'archivio è diminuita di uno.
    */
    public void rimuoviLibro(Libro libro) {
        // da implementare
    }
    
    /**
     * @brief Cerca un Libro nell'archivio tramite codice ISBN.
     *
     * @param[in] stringa_isbn La stringa contenente il codice ISBN del libro da cercare.
     * @pre 'stringa_isbn' non deve essere una stringa vuota o null.
     * @return Libro L'oggetto Libro trovato, oppure null se non viene trovato 
     * nessun libro corrispondente.
     * @post Lo stato dell'archivio non viene modificato.
     */
    public Libro cercaPerIsbn(String isbn) {
        return null; // da implementare
    }
    
    /**
     * @brief Cerca Libri nell'archivio che soddisfano un dato parametro stringa.
     *
     * @param[in] parametro La stringa di ricerca (es. titolo, autore).
     * @pre 'parametro' non deve essere una stringa vuota o null.
     * @return List<Libro> Una lista contenente gli oggetti Libro che corrispondono al parametro, 
     * o una lista vuota se non viene trovato nulla.
     * @post Lo stato dell'archivio non viene modificato.
     */
    public List<Libro> cerca(String parametro) {
        return null; // da implementare
    }
    
    /**
     * @brief Restituisce una lista di tutti i Libri presenti nell'archivio, ordinati per titolo.
     *
     * @return List<Libro> La lista completa dei libri, ordinata per titolo.
     * @post Lo stato dell'archivio non viene modificato; viene restituita una copia ordinata.
     */
    public List<Libro> getLibriOrdinatiPerTitolo() {
        return null; // da implementare
    }
   
}