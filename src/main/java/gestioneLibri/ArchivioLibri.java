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
 * Contiene i metodi per l'aggiunta, la modifica, la rimozione e la ricerca di
 * libri all'interno della collezione interna.
 *
 * @date Dicembre 6,2025
 * @author g16_member
 *
 * @see Libro
 */
public class ArchivioLibri {

    /**
     * @brief La lista di oggetti Libro mantenuta nell'archivio. Questa lista
     * viene inizializzata nel costruttore
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
     * @pre Il parametro 'libro' non deve essere null e l'isbn associato al
     * libro deve essere univoco: non deve esistere nell'archivio un altro libro
     * con lo stesso ISBN. .
     * @param[in] libro L'oggetto Libro da inserire nell'archivio.
     * @post La dimensione dell'archivio è aumentata di uno e l'oggetto libro è
     * presente nella lista.
     */
    public void aggiungiLibro(Libro libro) {
        if(libro== null){
        throw new IllegalArgumentException("Il libro da aggiungere non può essere null.");
        //return;
        }
        if(cercaPerIsbn(libro.getIsbn())!= null){
        throw new IllegalArgumentException("Esiste già un libro con ISBN: " + libro.getIsbn());
        //return;
        }
        this.libri.add(libro);
    }

    /**
     * @brief Modifica i dati di un libro presente nell'archivio.
     *
     * @pre Il libro da modificare deve già esistere nell'archivio.
     * @param[in] libroModificato L'oggetto Libro con i nuovi dati.
     * @post Il libro corrispondente nell'archivio è stato aggiornato con i
     * nuovi dati.
     */
    public void modificaLibro(Libro libroModificato) {
        if(libroModificato == null){
         throw new IllegalArgumentException("L'oggetto Libro modificato non può essere null.");
            //return;
        }
        Libro libroOriginale = cercaPerIsbn(libroModificato.getIsbn());

        if (libroOriginale != null) {
            libroOriginale.aggiornaDati(libroModificato);
        } else {
            throw new NoSuchElementException("Libro con ISBN " + libroModificato.getIsbn() + " non trovato per la modifica.");
        }
        
    }
    /**
     * @brief Rimuove un oggetto Libro dall'archivio.
     *
     * @pre Il parametro 'libro' non deve essere null.
     * @param[in] libro L'oggetto Libro da rimuovere dall'archivio.
     * @post Se l'oggetto era presente, è stato rimosso dalla lista interna e la
     * dimensione dell'archivio è diminuita di uno.
     */
    public void rimuoviLibro(Libro libro) {
        if (libro == null) {
           throw new IllegalArgumentException("Il libro da rimuovere non può essere null.");
            //return;
        }
        this.libri.remove(libro);
    if (!rimosso) {
            throw new NoSuchElementException("Rimozione fallita: Libro con ISBN " + libro.getIsbn() + " non trovato nell'archivio.");
        }
    }

    /**
     * @brief Cerca un Libro nell'archivio tramite codice ISBN.
     *
     * @param[in] isbn La stringa contenente il codice ISBN del libro da
     * cercare.
     * @pre 'isbn' non deve essere una stringa vuota o null.
     * @return Libro L'oggetto Libro trovato, oppure null se non viene trovato
     * nessun libro corrispondente.
     * @post Lo stato dell'archivio non viene modificato.
     */
    public Libro cercaPerIsbn(String isbn) {
       if (isbn == null || isbn.trim().isEmpty()) {
       throw new IllegalArgumentException("L'ISBN per la ricerca non può essere null o vuoto.");
           //return null; 
       }
       String isbnRicerca = isbn.trim();
       for (Libro libro : libri) {
            if (libro.getIsbn().equals(isbn)) {
                return libro; 
            }
        }

        return null;
    }
    /**
     * @brief Cerca Libri nell'archivio che abbiano il titolo specificato dalla
     * stringa passata come parametro.
     *
     * @param[in] titolo La stringa che contiene il criterio di ricerca.
     * @pre 'titolo' non deve essere una stringa vuota o null.
     * @return List<Libro> Una lista contenente gli oggetti Libro che
     * corrispondono al titolo richiesto, o una lista vuota se non viene trovato
     * nulla.
     * @post Lo stato dell'archivio non viene modificato.
     */
    public List<Libro> cercaPerTitolo(String titolo) {
        List<Libro> risultati = new ArrayList<>();
        if (titolo == null || titolo.trim().isEmpty()) {
            throw new IllegalArgumentException("Il titolo per la ricerca non può essere null o vuoto.");
            //return risultati; 
        }
             List<Libro> risultati = new ArrayList<>();
            String titoloRicerca = titolo.trim().toLowerCase();

        for (Libro libro : libri) {
            if (libro.getTitolo().toLowerCase().contains(titoloRicerca)) {
                risultati.add(libro);
            }
        }
        return risultati;
        }
    

    /**
     * @brief Cerca Libri nell'archivio che abbiano l'autore specificato dalla
     * stringa passata come parametro.
     *
     * @param[in] autore La stringa che contiene il criterio di ricerca.
     * @pre 'autore' non deve essere una stringa vuota o null.
     * @return List<Libro> Una lista contenente gli oggetti Libro che
     * corrispondono all'autore richiesto, o una lista vuota se non viene
     * trovato nulla.
     * @post Lo stato dell'archivio non viene modificato.
     */
    public List<Libro> cercaPerAutore(String autore) {
   
    if (autore == null || autore.trim().isEmpty()) {
        throw new IllegalArgumentException("L'autore per la ricerca non può essere null o vuoto.");    
        //return risultati; 
        }
     List<Libro> risultati = new ArrayList<>();
    String autoreRicerca = autore.trim().toLowerCase();

        for (Libro libro : libri) {
            if (libro.getAutore().toLowerCase().contains(autoreRicerca)) {
                risultati.add(libro);
            }
        }

        return risultati;
    }

    /**
     * @brief Restituisce una lista di tutti i Libri presenti nell'archivio,
     * ordinati per titolo.
     *
     * @return List<Libro> La lista completa dei libri, ordinata per titolo.
     * @post Lo stato dell'archivio non viene modificato; viene restituita una
     * copia ordinata.
     */
    public List<Libro> getLibriOrdinatiPerTitolo() {
    List<Libro> copiaLibri = new ArrayList<>(this.libri);
    copiaLibri.sort(Comparator.comparing(Libro::getTitolo));
        
        return copiaLibri;
    }

    /**
     * @brief Restituisce l'intera collezione dei libri registrati nel sistema.
     *
     * È utilizzato per operazioni generali come salvataggio su file.
     *
     * @return List<Libro> La lista interna di tutti i libri presenti
     * nell'archivio, senza applicare ordinamenti o filtri.
     * @post Lo stato dell’archivio non viene modificato.
     */
    public List<Libro> getTutti() {
    return new ArrayList<>(this.libri);
    }

}
