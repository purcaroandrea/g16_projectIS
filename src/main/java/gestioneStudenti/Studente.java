/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneStudenti;

import gestionePrestiti.Prestito;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danaiannaccone
 */
public class Studente implements Comparable<Studente>{
    private String nome;
    private String cognome;
    private String matricola;
    private String email;
    private List<Prestito> prestitiAttivi; //FORSE DA RIMUOVERE PERCHE GESTITA IN PRESTITI

    public Studente(String nome, String cognome, String matricola, String email, List<Prestito> prestitiAttivi) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.email = email;
        this.prestitiAttivi = new ArrayList<>();//anche qui
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getMatricola() {
        return matricola;
    }

    public String getEmail() {
        return email;
    }

    public List<Prestito> getPrestitiAttivi() {
        return prestitiAttivi;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrestitiAttivi(List<Prestito> prestitiAttivi) {
        this.prestitiAttivi = prestitiAttivi;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Studente)) return false;
        Studente s = (Studente) o;
        return matricola != null && matricola.equalsIgnoreCase(s.matricola);
    }

    @Override
    public int hashCode() {
        return matricola != null ? matricola.toLowerCase().hashCode() : 0;
    }

    @Override
    public int compareTo(Studente altro) {
        // prima confronto per cognome
        int confrontoCognome = this.cognome.compareToIgnoreCase(altro.cognome);
        if (confrontoCognome != 0) {
            return confrontoCognome;
        }
        // se cognomi uguali → confronto nomi
        return this.nome.compareToIgnoreCase(altro.nome);
    }

    @Override
    public String toString() {
        return cognome + " " + nome + "-" + matricola;
    }
    
    
}
