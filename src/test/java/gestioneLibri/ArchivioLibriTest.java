package gestioneLibri;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArchivioLibriTest {
    
    private ArchivioLibri archivio;
    	private Libro libro1;
    	private Libro libro2;
    	private Libro libro3;
    
    @BeforeEach
    public void setUp() {
        archivio = new ArchivioLibri();
    	libro1 = new Libro("La Divina Commedia", "Mondadori", 2016, "9788804671657", 5);
	libro2 = new Libro("Divergent", "Veronica Roth", 2011,"9791221213614", 1);
	libro3 = new Libro("Harry Potter", "Rowling", 1999,"9788893814522", 3);

	archivio.aggiungiLibro(libro1);
        archivio.aggiungiLibro(libro2);
    }

    @Test
    public void testAggiungiLibroSuccesso() {
        archivio.aggiungiLibro(libro3);
    	assertEquals(3, archivio.getTutti().size());
    	assertNotNull(archivio.cercaPerIsbn("9788893814522"));
    }
    
    @Test
    public void testAggiungiLibroNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            archivio.aggiungiLibro(null);
        });
    }
    
    @Test
    public void testAggiungiLibroDuplicato() {
        assertThrows(IllegalArgumentException.class, () -> {
            archivio.aggiungiLibro(libro1);
        });

        assertEquals(2, archivio.getTutti().size());
    }

    @Test
    public void testModificaLibroSuccesso() {

        Libro modificato = new Libro("La Divina Commedia-Edizione Speciale", "Mondadori", 2016, "9788804671657", 10);

	archivio.modificaLibro(modificato);

	Libro risultato = archivio.cercaPerIsbn("9788804671657");
        assertEquals("La Divina Commedia-Edizione Speciale", risultato.getTitolo());
        assertEquals(10, risultato.getCopieDisponibili());
    }
    
    @Test
    public void testModificaLibroNonEsistente() {
        Libro libroInesistente = new Libro("Inesistente", "Nessuno", 2000,"9999999999999", 1);
        assertThrows(IllegalStateException.class, () -> {
            archivio.modificaLibro(libroInesistente);
        });
    }

    @Test
    public void testRimuoviLibroSuccesso() {
        archivio.rimuoviLibro(libro1);
        assertEquals(1, archivio.getTutti().size());
        assertNull(archivio.cercaPerIsbn("9788804671657"));
    }
    
    @Test
    public void testRimuoviLibroNonEsistente() {
        Libro libroInesistente = new Libro("Inesistente", "Nessuno", 2000,"9999999999999", 1);
        assertThrows(IllegalStateException.class, () -> {
            archivio.rimuoviLibro(libro1);
        });
    }

    @Test
    public void testCercaPerIsbnEsistente() {
        Libro trovato = archivio.cercaPerIsbn("9788804671657");
        assertNotNull(trovato);
        assertEquals(libro1, trovato);
    }
    
    @Test
    public void testCercaPerIsbnNonEsistente() {
        Libro nonTrovato = archivio.cercaPerIsbn("9788804671658");
        assertNull(nonTrovato);
    }
    
    @Test
    public void testCercaPerIsbnNonValido() {
        assertThrows(IllegalArgumentException.class, () -> {
            archivio.cercaPerIsbn("");
        });
    }

    @Test
    public void testCercaPerTitolo() {
        List<Libro> risultati = archivio.cercaPerTitolo("Divergent");
        assertEquals(1, risultati.size());
        assertEquals(libro2, risultati.get(0));;
    }

    @Test
    public void testCercaPerAutore() {
        List<Libro> risultati = archivio.cercaPerAutore("Mondadori");
        assertEquals(1, risultati.size());
    }

    @Test
    public void testGetLibriOrdinatiPerTitolo() {
        archivio.aggiungiLibro(libro3);
        List<Libro> ordinati = archivio.getLibriOrdinatiPerTitolo();

        assertEquals("Divergent", ordinati.get(0).getTitolo());
        assertEquals("Harry Potter", ordinati.get(1).getTitolo());
        assertEquals("La Divina Commedia", ordinati.get(2).getTitolo());
    }

    @Test
    public void testGetTutti() {
        List<Libro> lista = archivio.getTutti();
        lista.clear();

        assertEquals(2, archivio.getTutti().size());
    }
    
}
