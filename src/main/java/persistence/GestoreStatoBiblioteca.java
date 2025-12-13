package persistence;

import gestioneLibri.ArchivioLibri;
import gestionePrestiti.ArchivioPrestiti;
import gestioneStudenti.ArchivioStudenti;

import java.io.IOException;

/**
 * @brief Gestisce il caricamento e il salvataggio dello stato della biblioteca.
 *
 */
public class GestoreStatoBiblioteca {

    private static GestoreStatoBiblioteca instance;

    private StatoBiblioteca stato;
    private ArchivioRepository repository;

    /**
     * @brief Costruttore privato: carica lo stato da file oppure crea uno stato nuovo.
     */
    private GestoreStatoBiblioteca() {
        repository = new ArchivioRepository("biblioteca.dat");

        try {
            stato = repository.carica();
        } catch (Exception e) {
            // Se il file non esiste o non è leggibile, creo uno stato nuovo
            stato = new StatoBiblioteca(
                new ArchivioLibri(),
                new ArchivioStudenti(),
                new ArchivioPrestiti()
            );
        }
    }

    /**
     * @brief Restituisce l'unica istanza del gestore.
     */
    public static GestoreStatoBiblioteca getInstance() {
        if (instance == null) {
            instance = new GestoreStatoBiblioteca();
        }
        return instance;
    }

    /**
     * @brief Restituisce lo stato completo della biblioteca.
     */
    public StatoBiblioteca getStato() {
        return stato;
    }

    /**
     * @brief Salva lo stato corrente su file.
     *
     * @throws IOException Se si verifica un errore durante il salvataggio.
     */
    public void salva() throws IOException {
        repository.salva(stato);
    }
}
