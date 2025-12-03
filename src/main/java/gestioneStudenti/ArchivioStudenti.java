/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneStudenti;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danaiannaccone
 */
public class ArchivioStudenti {
    private List<Studente> studenti;

    public ArchivioStudenti() {
        this.studenti = new ArrayList<>();
    }
    public void aggiungiStudente(Studente studente) {
        // da implementare
    }
    public void modificaStudente(Studente studenteModificato) {
        // da implementare
    }
    public void rimuoviStudente(Studente studente) {
        // da implementare
    }
    public List<Studente> getStudentiOrdinati() {
        return null; // da implementare
    }
    public List<Studente> cercaPerCognome(String cognome) {
        return null; // da implementare
    }
    public Studente cercaPerMatricola(String matricola) {
        return null; // da implementare
    }
}

