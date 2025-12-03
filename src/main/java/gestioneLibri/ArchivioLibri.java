/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneLibri;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danaiannaccone
 */
public class ArchivioLibri {

    private List<Libro> libri;

    public ArchivioLibri() {
        this.libri = new ArrayList<>();
    }
    public void aggiungiLibro(Libro libro) {
        // da implementare
    }
    public void modificaLibro(Libro libroModificato) {
        // da implementare
    }
    public void rimuoviLibro(Libro libro) {
        // da implementare
    }
    public Libro cercaPerIsbn(String isbn) {
        return null; // da implementare
    }
    public List<Libro> cerca(String parametro) {
        return null; // da implementare
    }
    public List<Libro> getLibriOrdinatiPerTitolo() {
        return null; // da implementare
    }
   
}