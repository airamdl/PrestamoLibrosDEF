/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainlibros;

/**
 *
 * @author alumno
 */
import java.util.Random;
public class GestorLibros {
    private Libro[] libros;
    private boolean[] librosDisponibles;

    public GestorLibros(Libro[] libros) {
        this.libros = libros;
        this.librosDisponibles = new boolean[libros.length];
        for (int i = 0; i < librosDisponibles.length; i++) {
            librosDisponibles[i] = true;
        }
    }

    public synchronized Libro[] tomarLibros(int idEstudiante) {
        Random rand = new Random();
        int libro1, libro2;

        
        do {
            libro1 = rand.nextInt(libros.length);
            if (!librosDisponibles[libro1]) {
                System.out.println("Estudiante " + idEstudiante + 
                                 " quiere el libro " + libro1 + " pero está en uso. Esperando...");
                try {
                    wait(); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (!librosDisponibles[libro1]);

        
        do {
            libro2 = rand.nextInt(libros.length);
            if (!librosDisponibles[libro2] || libro2 == libro1) {
                System.out.println("Estudiante " + idEstudiante + 
                                 " quiere el libro " + libro2 + " pero está en uso o es el mismo. Esperando...");
                try {
                    wait(); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (!librosDisponibles[libro2] || libro2 == libro1);

        
        librosDisponibles[libro1] = false;
        librosDisponibles[libro2] = false;

        System.out.println("Estudiante " + idEstudiante + 
                           " tomó los libros " + libro1 + " y " + libro2);

        return new Libro[]{libros[libro1], libros[libro2]};
    }

    public synchronized void devolverLibros(Libro[] librosDevueltos, int idEstudiante) {
        for (Libro libro : librosDevueltos) {
            librosDisponibles[libro.getId()] = true;
            System.out.println("Estudiante " + idEstudiante + 
                               " devolvió el libro " + libro.getId());
        }
        notifyAll(); 
    }
}