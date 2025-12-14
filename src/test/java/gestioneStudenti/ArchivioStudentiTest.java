/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioneStudenti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


/**
 *
 * @author g16_members
 */
public class ArchivioStudentiTest {
    
    private ArchivioStudenti archivio;
    private Studente s1, s2, s3;

    @BeforeEach
    public void setUp() {
        archivio = new ArchivioStudenti();

        s1 = new Studente("A100", "Mario", "Rossi", "mario.r@mail.it");
        s2 = new Studente("B200", "Giulia", "Bianchi", "giulia.b@mail.it");
        s3 = new Studente("C300", "Luca", "Verdi", "luca.v@mail.it");

        archivio.aggiungiStudente(s1);
        archivio.aggiungiStudente(s2);
    }

    @Test
    public void testAggiungiStudenteSuccesso() {
        Studente s4 = new Studente("D400", "Anna", "Neri", "anna@mail.it");
        archivio.aggiungiStudente(s4);
        assertEquals(3, archivio.getTutti().size(), "L'archivio dovrebbe contenere 3 studenti.");
        assertNotNull(archivio.cercaPerMatricola("D400"), "Lo studente D400 dovrebbe essere trovato.");
    }

    @Test
    public void testAggiungiStudenteMatricolaDuplicata() {
        Studente sDuplicato = new Studente("A100", "Nome", "Cognome", "mail@mail.it");
        assertThrows(IllegalArgumentException.class, () -> {
            archivio.aggiungiStudente(sDuplicato);
        }, "Dovrebbe lanciare IllegalArgumentException per matricola duplicata.");
    }

    @Test
    public void testModificaStudenteSuccesso() {
        Studente s1Modificato = new Studente("A100", "Marco", "Gialli", "marco.g@mail.it");
        archivio.modificaStudente(s1Modificato);

        Studente trovato = archivio.cercaPerMatricola("A100");
        assertEquals("Marco", trovato.getNome(), "Il nome dovrebbe essere stato modificato.");
        assertEquals("Gialli", trovato.getCognome(), "Il cognome dovrebbe essere stato modificato.");
        assertEquals("marco.g@mail.it", trovato.getEmail(), "L'email dovrebbe essere stata modificata.");
    }

    @Test
    public void testModificaStudenteNonTrovato() {
        Studente sNonEsistente = new Studente("Z999", "Nome", "Cognome", "mail@mail.it");
        assertThrows(IllegalStateException.class, () -> {
            archivio.modificaStudente(sNonEsistente);
        }, "Dovrebbe lanciare IllegalStateException se lo studente da modificare non è trovato.");
    }

    @Test
    public void testRimuoviStudenteSuccesso() {
        archivio.rimuoviStudente(s2);
        assertEquals(1, archivio.getTutti().size(), "L'archivio dovrebbe contenere 1 studente dopo la rimozione.");
        assertNull(archivio.cercaPerMatricola("B200"), "Lo studente B200 non dovrebbe essere più trovato.");
    }

    @Test
    public void testRimuoviStudenteNonTrovato() {
        Studente sNonEsistente = new Studente("Z999", "Nome", "Cognome", "mail@mail.it");
        assertThrows(IllegalStateException.class, () -> {
            archivio.rimuoviStudente(sNonEsistente);
        }, "Dovrebbe lanciare IllegalStateException se lo studente da rimuovere non è trovato.");
    }

    @Test
    public void testCercaPerCognomeTrovato() {
        List<Studente> risultati = archivio.cercaPerCognome("Rossi");
        assertEquals(1, risultati.size(), "Dovrebbe trovare 1 studente con cognome 'Rossi'.");
        assertEquals("A100", risultati.get(0).getMatricola());
    }

    @Test
    public void testCercaPerCognomeParziale() {
        Studente sParziale = new Studente("D400", "Paolo", "Marossi", "p.m@mail.it");
        archivio.aggiungiStudente(sParziale);

        List<Studente> risultati = archivio.cercaPerCognome("rossi");
        assertEquals(2, risultati.size(), "Dovrebbe trovare 2 studenti ('Rossi' e 'Marossi').");
    }

    @Test
    public void testCercaPerCognomeNonTrovato() {
        List<Studente> risultati = archivio.cercaPerCognome("Gialli");
        assertTrue(risultati.isEmpty(), "Non dovrebbe trovare nessuno studente con cognome 'Gialli'.");
    }

    @Test
    public void testCercaPerMatricolaTrovato() {
        Studente trovato = archivio.cercaPerMatricola("A100");
        assertNotNull(trovato, "Lo studente A100 dovrebbe essere trovato.");
        assertEquals("Mario", trovato.getNome());
    }

    @Test
    public void testCercaPerMatricolaNonTrovata() {
        Studente trovato = archivio.cercaPerMatricola("Z999");
        assertNull(trovato, "Lo studente Z999 non dovrebbe essere trovato.");
    }

    @Test
    public void testGetTutti() {
        List<Studente> tutti = archivio.getTutti();
        assertEquals(2, tutti.size());
        tutti.clear();
        assertEquals(2, archivio.getTutti().size(), "La lista ritornata non dovrebbe influenzare l'archivio.");
    }

    @Test
    public void testGetStudentiOrdinati() {
        archivio.aggiungiStudente(s3); // s1 (Rossi), s2 (Bianchi), s3 (Verdi)
        List<Studente> ordinati = archivio.getStudentiOrdinati();
        assertEquals(3, ordinati.size());
        assertEquals("Bianchi", ordinati.get(0).getCognome());
        assertEquals("Rossi", ordinati.get(1).getCognome());
        assertEquals("Verdi", ordinati.get(2).getCognome());
    }
}
