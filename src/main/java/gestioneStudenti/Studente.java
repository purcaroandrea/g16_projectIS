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
 * @file Studente.java
 * @brief Rappresenta uno studente registrato nel sistema.
 *
 * Contiene i dati anagrafici dello studente, la matricola, l’email.
 *
 * @author g16_member
 * @date Dicembre 7, 2025
 */
public class Studente implements Comparable<Studente> {

    private String nome;
    private String cognome;
    private String matricola;
    private String email;

    /**
     * @brief Costruttore completo dello studente.
     *
     * @param[in] nome Nome dello studente.
     * @param[in] cognome Cognome dello studente.
     * @param[in] matricola Matricola univoca dello studente.
     * @param[in] email Email istituzionale.
     * @post Lo studente è creato con i dati forniti.
     */
    public Studente(String nome, String cognome, String matricola, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.email = email;
    }

    // ----------- Getter & Setter -----------
    // @brief Restituisce il nome dello studente.
    public String getNome() {
        return nome;
    }

    // @brief Restituisce il cognome dello studente.
    public String getCognome() {
        return cognome;
    }

    // @brief Restituisce la matricola dello studente.
    public String getMatricola() {
        return matricola;
    }

    // @brief Restituisce l’email dello studente.
    public String getEmail() {
        return email;
    }

    /**
     * @brief Imposta il nome dello studente.
     *
     * @pre Il parametro 'nome' non deve essere null o vuoto.
     * @param[in] nome Nuovo nome da assegnare allo studente.
     * @post L'attributo 'nome' dello studente è aggiornato.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @brief Imposta il cognome dello studente.
     *
     * @pre Il parametro 'cognome' non deve essere null o vuoto.
     * @param[in] cognome Nuovo cognome da assegnare allo studente.
     * @post L'attributo 'cognome' dello studente è aggiornato.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @brief Imposta la matricola dello studente.
     *
     * @pre Il parametro 'matricola' non deve essere null o vuoto.
     * @param[in] matricola Nuova matricola da assegnare.
     * @post L'attributo 'matricola' dello studente è aggiornato.
     */
    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    /**
     * @brief Imposta l'email istituzionale dello studente.
     *
     * @pre Il parametro 'email' non deve essere null o vuoto e deve rispettare
     * un formato valido.
     * @param[in] email Nuova email da assegnare.
     * @post L'attributo 'email' dello studente è aggiornato.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    // ----------- Metodi di confronto e utilità -----------
    /**
     * @brief Verifica l’uguaglianza tra studenti tramite la loro matricola.
     *
     * @param[in] o Oggetto da confrontare.
     * @return boolean True se le matricole coincidono.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Studente)) {
            return false;
        }
        Studente s = (Studente) o;
        return matricola != null && matricola.equalsIgnoreCase(s.matricola);
    }

    /**
     * @brief Genera l'hashCode dello studente basato sulla matricola.
     *
     * @return int Valore hash coerente con equals(), in modo che due studenti
     * con la stessa matricola restituiscano lo stesso hashCode.
     * @see equals(Object o)
     */
    @Override
    public int hashCode() {
        return matricola != null ? matricola.toLowerCase().hashCode() : 0;
    }

    /**
     * @brief Confronta due studenti per cognome e, in caso di parità, per nome.
     *
     * @param[in] altro Lo studente da confrontare.
     * @return int Risultato del confronto alfabetico.
     * @post Lo stato interno dell’oggetto non viene modificato.
     */
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

    /**
     * @brief Restituisce una rappresentazione testuale compatta dello studente.
     *
     * @return String Cognome Nome - Matricola.
     */
    @Override
    public String toString() {
        return cognome + " " + nome + "-" + matricola;
    }

}
