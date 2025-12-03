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
 *
 * @author danaiannaccone
 */
public class ArchivioPrestiti {
    private Map<String, List<Prestito>> prestitiPerStudente;

    public ArchivioPrestiti() {
        this.prestitiPerStudente=new HashMap<>();
    }
    public Prestito registraPrestito(Studente studente, Libro libro, LocalDate dataPrestito, LocalDate dataPrevistaRestituzione) {
        return null; // da implementare
    }
    public void registraRestituzione(Prestito prestito) {
        // da implementare
    }
    public List<Prestito> getPrestitiPerData() {
        return null; // da implementare
    }
    public int contaPrestitiAttivi(Studente studente) {
        return 0; // da implementare
    }
    public List<Prestito> cercaPrestitiAttivi(String cognomeOMatricola, String titoloLibro) {
        return null; // da implementare
    }
    
}
