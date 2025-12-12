/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneStudenti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @file ArchivioStudenti.java
 * @brief La classe gestisce l'archivio degli studenti registrati nel sistema.
 *
 * La classe permette di aggiungere, modificare, rimuovere studenti e di
 * eseguire ricerche per cognome o matricola. Consente inoltre di ottenere una
 * lista ordinata degli studenti.
 *
 * @author g16_member
 * @date Dicembre 7, 2025
 *
 * @see Studente
 */
public class ArchivioStudenti {

    // @brief Lista degli studenti presenti nell’archivio.
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
     * @pre Lo studente deve avere matricola valida e non già presente in
     * archivio.
     * @param[in] studente Lo studente da aggiungere.
     * @post Lo studente viene inserito nell’archivio.
     */
    public void aggiungiStudente(Studente studente) {
        if (studente == null) {
            throw new IllegalArgumentException("Lo studente da aggiungere non può essere null.");
        }
        String mat = studente.getMatricola();
        if (mat == null || mat.trim().isEmpty()){
            throw new IllegalArgumentException("La matricola dello studente non può essere vuota.");
        }
        if (cercaPerMatricola(mat) != null) {
            throw new IllegalArgumentException("Esiste già uno studente con matricola: " + mat);
        }

        studenti.add(studente);
    }

    /**
     * @brief Modifica i dati di uno studente già presente in archivio.
     *
     * @pre La matricola dello studente modificato deve essere già registrata.
     * @param[in] studenteModificato L’oggetto studente con i nuovi dati.
     * @post I dati dello studente vengono aggiornati.
     */
    public void modificaStudente(Studente studenteModificato) {
        if (studenteModificato == null) {
            throw new IllegalArgumentException("Lo studente modificato non può essere null.");
        }
        
        String mat = studenteModificato.getMatricola();
        if (mat == null || mat.trim().isEmpty()) {
            throw new IllegalArgumentException("La matricola dello studente modificato non può essere vuota.");
        }
        
        for (Studente s : studenti) {
        if (s.getMatricola() != null && s.getMatricola().equalsIgnoreCase(mat)) {
            // Aggiorno SOLO i campi modificabili
            s.setNome(studenteModificato.getNome());
            s.setCognome(studenteModificato.getCognome());
            s.setEmail(studenteModificato.getEmail());
            // La matricola NON va cambiata
            return;
            }
        }
        throw new IllegalStateException("Modifica fallita: nessuno studente con matricola " + mat + " trovato.");
    }

    /**
     * @brief Rimuove uno studente dall’archivio.
     *
     * @pre Lo studente deve essere presente nell’archivio.
     * @param[in] studente Lo studente da rimuovere.
     * @post Lo studente non è più presente nell’archivio.
     */
    public void rimuoviStudente(Studente studente) {
        if (studente == null) {
            throw new IllegalArgumentException("Lo studente da rimuovere non può essere null.");
        }
        String mat = studente.getMatricola();
        if (mat == null || mat.trim().isEmpty()){
            throw new IllegalArgumentException("La matricola dello studente da rimuovere non può essere vuota.");
        }
        boolean rimosso = studenti.removeIf(s ->
                s.getMatricola() != null && s.getMatricola().equalsIgnoreCase(mat)
        );

        if (!rimosso) {
            throw new IllegalStateException("Rimozione fallita: nessuno studente con matricola " + mat + " trovato.");
        }
    }

    /**
     * @brief Restituisce la lista di studenti ordinati alfabeticamente.
     *
     * L’ordinamento è definito dal metodo compareTo di Studente.
     *
     * @return List<Studente> Lista ordinata degli studenti.
     * @post Lo stato interno dell’archivio non viene modificato.
     * @note La lista restituita è una copia ordinata della lista interna.
     * @see Studente#compareTo(Studente)
     */
    public List<Studente> getStudentiOrdinati() {
       List<Studente> copia = new ArrayList<>(studenti);
       Collections.sort(copia);
       return copia;
    }

    /**
     * @brief Cerca studenti in base al cognome (match case-insensitive).
     *
     * @param[in] cognome Il cognome da cercare.
     * @return List<Studente> Lista degli studenti che soddisfano il criterio.
     * @post L’archivio rimane immutato.
     */
    public List<Studente> cercaPerCognome(String cognome) {
       if (cognome == null || cognome.trim().isEmpty()) {
            throw new IllegalArgumentException("Il cognome per la ricerca non può essere vuoto.");
        }

        List<Studente> risultati = new ArrayList<>();
        String target = cognome.trim().toLowerCase();

        for (Studente s : studenti) {
            String cognomeStud = s.getCognome();
            if (cognomeStud != null && cognomeStud.toLowerCase().contains(target)) {
                risultati.add(s);
            }
        }

        return risultati;
    }

    /**
     * @brief Cerca uno studente tramite matricola.
     *
     * @param[in] matricola La matricola dello studente ricercato.
     * @return Studente Lo studente trovato, oppure null se non esiste.
     * @post Lo stato interno dell’archivio non viene modificato.
     */
    public Studente cercaPerMatricola(String matricola) {
        if (matricola == null || matricola.trim().isEmpty()) {
            throw new IllegalArgumentException("La matricola per la ricerca non può essere vuota.");
        }

        String target = matricola.trim();

        for (Studente s : studenti) {
            if (s.getMatricola() != null && s.getMatricola().equalsIgnoreCase(target)) {
                return s;
            }
        }

        return null;
    }

    /**
     * @brief Restituisce l'elenco completo degli studenti registrati.
     *
     * È utile per operazioni di esportazione, analisi o visualizzazioni
     * generali.
     *
     * @return List<Studente> La lista interna di tutti gli studenti presenti,
     * senza applicare ordinamenti o criteri di ricerca.
     */
    public List<Studente> getTutti() {
        return new ArrayList<>(studenti);
    }
}
