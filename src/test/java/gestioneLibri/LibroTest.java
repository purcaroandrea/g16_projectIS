package gestioneLibri;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {
    
    private Libro libroDiProva;
    
    @BeforeEach
    public void setUp() {
        libroDiProva = new Libro("Divergent", "Veronica Roth", 2011,"9791221213614", 7);
    }

    @Test
    public void testCostruttoreEGetter() {
        assertEquals("Divergent", libroDiProva.getTitolo());
        assertEquals("Veronica Roth", libroDiProva.getAutore());
        assertEquals(2011, libroDiProva.getAnnoPubblicazione());
        assertEquals("9791221213614", libroDiProva.getIsbn());
        assertEquals(7, libroDiProva.getCopieDisponibili());
    }
    
    @Test
    public void testSetter() {
        String nuovoTitolo = "Divergent-Edizione Speciale";
        String nuovoAutore = "Mondadori";
        int nuovoAnno = 2025;
        int nuoveCopie = 10;
        
        libroDiProva.setTitolo(nuovoTitolo);
        libroDiProva.setAutore(nuovoAutore);
        libroDiProva.setAnnoPubblicazione(nuovoAnno);
        libroDiProva.setCopieDisponibili(nuoveCopie);
        
        assertEquals(nuovoTitolo, libroDiProva.getTitolo());
        assertEquals(nuovoAutore, libroDiProva.getAutore());
        assertEquals(nuovoAnno, libroDiProva.getAnnoPubblicazione());
        assertEquals(nuoveCopie, libroDiProva.getCopieDisponibili());
        
        assertEquals("9791221213614", libroDiProva.getIsbn());
    }

    @Test
    public void testHaCopieTrue() {
        libroDiProva.setCopieDisponibili(7);
        assertTrue(libroDiProva.haCopie());
    }
    
    @Test
    public void testHaCopieFalse() {
        libroDiProva.setCopieDisponibili(0);
        assertFalse(libroDiProva.haCopie());
    }

    @Test
    public void testIncrementaCopie() {
        libroDiProva.setCopieDisponibili(7);
        libroDiProva.incrementaCopie();
        assertEquals(8, libroDiProva.getCopieDisponibili());
    }

    @Test
    public void testDecrementaCopie() {
        libroDiProva.setCopieDisponibili(7);
        libroDiProva.decrementaCopie();
        assertEquals(6, libroDiProva.getCopieDisponibili());
    }

    @Test
    public void testToString() {
        String risultato = libroDiProva.toString();
        assertTrue(risultato.contains("Divergent"));
        assertTrue(risultato.contains("9791221213614"));
    }

    @Test
    public void testEquals() {
        //Stesso ISBN
        Libro libroUguale = new Libro( "Titolo Diverso", "Autore Diverso", 2020, libroDiProva.getIsbn(), 9);
        assertTrue(libroDiProva.equals(libroUguale));

        //ISBN diverso)
        Libro libroDiverso = new Libro(libroDiProva.getTitolo(), libroDiProva.getAutore(), libroDiProva.getAnnoPubblicazione(), "9791221213614", libroDiProva.getCopieDisponibili());
        assertFalse(libroDiProva.equals(libroDiverso));
    }

    @Test
    public void testHashCode() {
        // stesso ISBN
        Libro libroUguale = new Libro( "Titolo Diverso", "Autore Diverso", 2020, libroDiProva.getIsbn(), 9);
        assertEquals(libroDiProva.hashCode(), libroUguale.hashCode());
        
        //ISBN diverso
        Libro libroDiverso = new Libro(libroDiProva.getTitolo(), libroDiProva.getAutore(), libroDiProva.getAnnoPubblicazione(), "9791221213614", libroDiProva.getCopieDisponibili());
        assertNotEquals(libroDiProva.hashCode(), libroDiverso.hashCode());
    }

    @Test
    public void testCompareTo() {
        //Confronto per titolo
        Libro libroA = new Libro("Alice nel Paese delle Meraviglie", "Autore A", 2000, "A1", 1);
        Libro libroZ = new Libro("Zanna Bianca", "Autore Z", 2000, "Z1", 1);
        Libro libroIdentico = new Libro("Alice nel Paese delle Meraviglie", "Autore X", 2000, "B2", 1);
        
        //Ordine alfabetico
        assertTrue(libroA.compareTo(libroZ) < 0);
        
        // Ordine alfabetico inverso
        assertTrue(libroZ.compareTo(libroA) > 0);
        
        // Titoli identici
        assertEquals(0, libroA.compareTo(libroIdentico));
    }
    
}
