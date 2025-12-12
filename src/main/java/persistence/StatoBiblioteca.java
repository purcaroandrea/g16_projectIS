/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import gestioneLibri.ArchivioLibri;
import gestionePrestiti.ArchivioPrestiti;
import gestioneStudenti.ArchivioStudenti;
import java.io.Serializable;

/**
 *
 * @author danaiannaccone
 */
public class StatoBiblioteca implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private ArchivioLibri archivioLibri;
    private ArchivioStudenti archivioStudenti;
    private ArchivioPrestiti archivioPrestiti;
    
    public StatoBiblioteca(ArchivioLibri archivioLibri, ArchivioStudenti archivioStudenti, ArchivioPrestiti archivioPrestiti) {
        this.archivioLibri = archivioLibri;
        this.archivioStudenti = archivioStudenti;
        this.archivioPrestiti = archivioPrestiti;
    }
    
    public ArchivioLibri getArchivioLibri() {
        return archivioLibri;
    }

    public ArchivioStudenti getArchivioStudenti() {
        return archivioStudenti;
    }

    public ArchivioPrestiti getArchivioPrestiti() {
        return archivioPrestiti;
    }
    public void setArchivioLibri(ArchivioLibri archivioLibri) {
        if (archivioLibri == null) {
            throw new IllegalArgumentException("ArchivioLibri non può essere null.");
        }
        this.archivioLibri = archivioLibri;
    }

    public void setArchivioStudenti(ArchivioStudenti archivioStudenti) {
        if (archivioStudenti == null) {
            throw new IllegalArgumentException("ArchivioStudenti non può essere null.");
        }
        this.archivioStudenti = archivioStudenti;
    }

    public void setArchivioPrestiti(ArchivioPrestiti archivioPrestiti) {
        if (archivioPrestiti == null) {
            throw new IllegalArgumentException("ArchivioPrestiti non può essere null.");
        }
        this.archivioPrestiti = archivioPrestiti;
    }
}
