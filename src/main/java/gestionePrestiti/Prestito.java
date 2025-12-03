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
 *
 * @author danaiannaccone
 */
public class Prestito {
    private Studente studente;
    private Libro libro;
    private LocalDate dataPrestito;
    private LocalDate dataRestituzione;
    private boolean restituito;

    public Prestito(Studente studente, Libro libro, LocalDate dataPrestito, LocalDate dataRestituzione) {
        this.studente=studente;
        this.libro=libro;
        this.dataPrestito=dataPrestito;
        this.dataRestituzione=dataRestituzione;
        this.restituito=false;
    }

    public Studente getStudente() {
        return studente;
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getDataPrestito() {
        return dataPrestito;
    }

    public LocalDate getDataRestituzione() {
        return dataRestituzione;
    }

    public boolean isRestituito() {
        return restituito;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setDataPrestito(LocalDate dataPrestito) {
        this.dataPrestito = dataPrestito;
    }

    public void setDataRestituzione(LocalDate dataRestituzione) {
        this.dataRestituzione = dataRestituzione;
    }

    public void setRestituito(boolean restituito) {
        this.restituito = restituito;
    }
    
    public boolean isAttivo() {
        return false; // da implementare
    }
    
    public boolean isScaduto(LocalDate oggi) {
        return false; // da implementare
    }
    
    public boolean isInScadenza(LocalDate oggi, int sogliaGiorni) {
        return false; // da implementare
    }
}
    