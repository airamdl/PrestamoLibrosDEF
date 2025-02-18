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

public class Estudiante implements Runnable {
    private int id;
    private GestorLibros gestorLibros;
    private Random rand;

    public Estudiante(int id, GestorLibros gestorPrestamos) {
        this.id = id;
        this.gestorLibros = gestorPrestamos;
        this.rand = new Random();
    }

    @Override
    public void run() {
        while (true) {
            // Coge libros
            Libro[] libros = gestorLibros.tomarLibros(id);
            System.out.println("El estudiante " + id + " tomo los libros " + libros[0].getId() + " y " + libros[1].getId());

          
            int tiempoUso = rand.nextInt(3) + 1; 
            try {
                Thread.sleep(tiempoUso * 1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
               
            }

            
            gestorLibros.devolverLibros(libros, id);
            System.out.println("El estudiante " + id + " devolvio los libros " + libros[0].getId() + " y " + libros[1].getId());

            // Descanso
            int tiempoDescanso = rand.nextInt(2) + 1; 
            try {
                Thread.sleep(tiempoDescanso * 1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
