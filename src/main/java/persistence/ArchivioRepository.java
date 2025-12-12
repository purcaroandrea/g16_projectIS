/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.*;
/**
 *
 * @author danaiannaccone
 */
public class ArchivioRepository {
    private String filePath;
    
    public ArchivioRepository(String filePath) {
        this.filePath = filePath;
    }
    
    public void salva(StatoBiblioteca stato) throws IOException {
        if (stato == null) {
            throw new IllegalArgumentException("Lo stato da salvare non può essere null.");
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(stato);
        }
    }
    
    public StatoBiblioteca carica() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = in.readObject();
            return (StatoBiblioteca) obj;
        }
    }
}
