package gestioneStudenti;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.ArrayList;

public class StudenteTest {

private Studente studenteBase;
   
    private static final String NOME_INIZIALE = "Mario";
    private static final String COGNOME_INIZIALE = "Rossi";
    private static final String MATRICOLA_INIZIALE = "M12345";
    private static final String EMAIL_INIZIALE = "mario.rossi@unisa.it";

    @BeforeEach
    public void setUp() {
        
        studenteBase = new Studente(NOME_INIZIALE, COGNOME_INIZIALE, MATRICOLA_INIZIALE, EMAIL_INIZIALE);
    }

    @Test 
    public void testCostruttoreAndGetters() {
        
        assertEquals(NOME_INIZIALE, studenteBase.getNome(), "Il nome non corrisponde a quello atteso.");
        assertEquals(COGNOME_INIZIALE, studenteBase.getCognome(), "Il cognome non corrisponde a quello atteso.");
        assertEquals(MATRICOLA_INIZIALE, studenteBase.getMatricola(), "La matricola non corrisponde a quella attesa.");
        assertEquals(EMAIL_INIZIALE, studenteBase.getEmail(), "L'email non corrisponde a quella attesa.");
    }

    @Test
    public void testSetNomeValido() {
        
        String nuovoNome = "Luigi";
        studenteBase.setNome(nuovoNome);
        assertEquals(nuovoNome, studenteBase.getNome(), "Il nome non è stato aggiornato correttamente.");
    }

    @Test
    public void testSetNomeVuotoLanciaEccezione() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            studenteBase.setNome("");
        }, "Il setter non ha lanciato l'eccezione per nome vuoto.");
    }

    @Test
    public void testSetEmailValida() {
        
        String nuovaEmail = "nuova.email@unisa.it";
        studenteBase.setEmail(nuovaEmail);
        assertEquals(nuovaEmail, studenteBase.getEmail(), "L'email non è stata aggiornata correttamente.");
    }

    @Test
    public void testSetEmailNonValidaLanciaEccezione() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            studenteBase.setEmail("emailnonvalida.it");
        }, "Il setter non ha lanciato l'eccezione per email non valida (senza @).");

        assertThrows(IllegalArgumentException.class, () -> {
            studenteBase.setEmail("email con spazio@dominio.it");
        }, "Il setter non ha lanciato l'eccezione per email con spazio.");
    }

    @Test
    public void testEqualsTrueConStessaMatricola() {
        
        Studente altroStudente = new Studente("Paolo", "Bianchi", MATRICOLA_INIZIALE, "paolo@unisa.it");
        assertTrue(studenteBase.equals(altroStudente), "Gli studenti con la stessa matricola dovrebbero essere uguali.");
    }

    @Test
    public void testEqualsFalseConMatricolaDiversa() {
        
        Studente altroStudente = new Studente("Mario", "Rossi", "A98765", EMAIL_INIZIALE);
        assertFalse(studenteBase.equals(altroStudente), "Gli studenti con matricole diverse non dovrebbero essere uguali.");
    }

    @Test
    public void testHashCodeCoerenteConEquals() {
        
        Studente altroStudente = new Studente("Paolo", "Bianchi", MATRICOLA_INIZIALE.toLowerCase(), "paolo@unisa.it");
        assertEquals(studenteBase.hashCode(), altroStudente.hashCode(), "Gli studenti uguali devono avere lo stesso hashCode.");

        Studente studenteDiverso = new Studente("Mario", "Rossi", "M00000", EMAIL_INIZIALE);
        assertNotEquals(studenteBase.hashCode(), studenteDiverso.hashCode(), "Gli studenti diversi dovrebbero avere hash code diversi.");
    }

    @Test
    public void testCompareToConfrontoPerCognome() {
        
        Studente studentePrima = new Studente("Alice", "Bianchi", "A11111", "a@a.it");
        Studente studenteDopo = new Studente("Carlo", "Verdi", "C33333", "c@c.it");

        assertTrue(studentePrima.compareTo(studenteBase) < 0, "Bianchi dovrebbe venire prima di Rossi.");
        assertTrue(studenteDopo.compareTo(studenteBase) > 0, "Verdi dovrebbe venire dopo Rossi.");
    }

    @Test
    public void testCompareToConfrontoPerNome() {
        
        Studente studentePrima = new Studente("Alberto", COGNOME_INIZIALE, "A11111", "a@a.it"); // Alberto vs Mario (iniziale)
        assertTrue(studentePrima.compareTo(studenteBase) < 0, "Alberto dovrebbe venire prima di Mario con lo stesso cognome.");
    }

    @Test
    public void testSortingList() {
        
        Studente s1 = new Studente("Alice", "Bianchi", "A11111", "a@a.it");
        Studente s2 = new Studente("Mario", "Rossi", "M12345", "m@m.it");
        Studente s3 = new Studente("Carlo", "Rossi", "C22222", "c@c.it");

        ArrayList<Studente> listaNonOrdinata = new ArrayList<>();
        listaNonOrdinata.add(s2);
        listaNonOrdinata.add(s1);
        listaNonOrdinata.add(s3);

        Collections.sort(listaNonOrdinata);

        assertEquals(s1, listaNonOrdinata.get(0), "Il primo elemento dovrebbe essere Bianchi.");
        assertEquals(s3, listaNonOrdinata.get(1), "Il secondo elemento dovrebbe essere Rossi Carlo.");
        assertEquals(s2, listaNonOrdinata.get(2), "Il terzo elemento dovrebbe essere Rossi Mario.");
    }
}