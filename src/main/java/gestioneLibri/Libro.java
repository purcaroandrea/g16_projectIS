/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneLibri;

/**
 *
 * @author danaiannaccone
 */
public class Libro {
    private String titolo;
    private String autore;
    private int annoPubblicazione;
    private String isbn;
    private int copieDisponibili;
    
    public Libro(String titolo,String autore, int annoPubblicazione, String isbn, int copieDisponibili){
        this.titolo=titolo;
        this.autore=autore;
        this.annoPubblicazione=annoPubblicazione;
        this.isbn=isbn;
        this.copieDisponibili=copieDisponibili;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getCopieDisponibili() {
        return copieDisponibili;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setCopieDisponibili(int copieDisponibili) {
        this.copieDisponibili = copieDisponibili;
    }
    
    public boolean haCopie() {
        return copieDisponibili > 0;
    }

    public void decrementaCopie() {
        copieDisponibili--;
    }

    public void incrementaCopie() {
        copieDisponibili++;
    }

    @Override
    public String toString() {
        return titolo + " - " + autore + " - "  + isbn;
    }
    
}
