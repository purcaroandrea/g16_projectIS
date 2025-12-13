/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionePrestiti;

import gestioneLibri.Libro;
import gestioneStudenti.Studente;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author g16_member
 */
public class PrestitoTest {
    

    private Prestito prestito;
    private Studente studente;
    private Libro libro;

    @BeforeEach
    void setUp() {
        studente = new Studente("Federica", "Bianchi", "S456", "federica.bianchi@uni.it");
        libro = new Libro("Java", "D'Aliasi", 2019, "ISBN999", 1);
        prestito = new Prestito(
                studente,
                libro,
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        );
    }

    @Test
    void testPrestitoAttivoAllInizio() {
        assertTrue(prestito.isAttivo());
        assertFalse(prestito.isRestituito());
    }

    @Test
    void testSetRestituito() {
        prestito.setRestituito(true);
        assertTrue(prestito.isRestituito());
        assertFalse(prestito.isAttivo());
    }

    @Test
    void testPrestitoScaduto() {
        boolean scaduto = prestito.isScaduto(LocalDate.now().plusDays(10));
        assertTrue(scaduto);
    }

    @Test
    void testPrestitoInScadenza() {
        boolean inScadenza = prestito.isInScadenza(LocalDate.now(), 10);
        assertTrue(inScadenza);
    }

    @Test
    void testDataRestituzioneNonValida() {
        assertThrows(IllegalArgumentException.class, () ->
            prestito.setDataRestituzione(LocalDate.now().minusDays(1))
        );
    }
}
