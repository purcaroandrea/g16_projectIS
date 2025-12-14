/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionePrestiti;

import gestioneLibri.Libro;
import gestioneStudenti.Studente;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author g16_member
 */
public class ArchivioPrestitiTest {
    
    private ArchivioPrestiti archivio;
    private Studente studente;
    private Libro libro;

    @BeforeEach
    void setUp() {
        archivio = new ArchivioPrestiti();
        studente = new Studente("Mario", "Rossi", "S123", "mario.rossi@uni.it");
        libro = new Libro("Algoritmi", "Barisano", 2020, "ISBN123", 3);
    }

    @Test
    void testRegistraPrestitoSuccesso() {
        Prestito p = archivio.registraPrestito(
                studente,
                libro,
                LocalDate.now().plusDays(10)
        );

        assertNotNull(p);
        assertEquals(2, libro.getCopieDisponibili());
        assertEquals(1, archivio.contaPrestitiAttivi(studente));
    }

    @Test
void testLimiteTrePrestiti() {
    libro.setCopieDisponibili(4);

    // Primo, secondo e terzo prestito: ok
    archivio.registraPrestito(studente, libro, LocalDate.now().plusDays(5));
    archivio.registraPrestito(studente, libro, LocalDate.now().plusDays(6));
    archivio.registraPrestito(studente, libro, LocalDate.now().plusDays(7));
    
    assertThrows(IllegalArgumentException.class, () ->
        archivio.registraPrestito(
            studente,
            libro,
            LocalDate.now().plusDays(8)
        )
    );
}


    @Test
    void testRegistraRestituzione() {
        Prestito p = archivio.registraPrestito(
                studente,
                libro,
                LocalDate.now().plusDays(7)
        );

        archivio.registraRestituzione(p);

        assertEquals(3, libro.getCopieDisponibili());
        assertEquals(0, archivio.contaPrestitiAttivi(studente));
        assertTrue(p.isRestituito());
    }

    @Test
    void testGetPrestitiPerDataOrdinati() {
        archivio.registraPrestito(studente, libro,
                LocalDate.now().plusDays(10));
        archivio.registraPrestito(studente, libro,
                LocalDate.now().plusDays(5));

        List<Prestito> lista = archivio.getPrestitiPerData();

        assertEquals(2, lista.size());
        assertTrue(lista.get(0).getDataRestituzione()
                .isBefore(lista.get(1).getDataRestituzione()));
    }

    @Test
    void testCercaPrestitiAttivi() {
        archivio.registraPrestito(
                studente,
                libro,
                LocalDate.now().plusDays(10)
        );

        List<Prestito> risultati =
                archivio.cercaPrestitiAttivi("Rossi", "Algoritmi");

        assertEquals(1, risultati.size());
    }
}

