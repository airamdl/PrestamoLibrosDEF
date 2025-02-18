/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainlibros;

/**
 *
 * @author alumno
 */
public class MainLibros {
    public static void main(String[] args) {
        Libro[] libros = new Libro[9];
        for (int i = 0; i < libros.length; i++) {
            libros[i] = new Libro(i);
        }

        GestorLibros gestorLibros = new GestorLibros(libros);

        Thread[] estudiantes = new Thread[4];
        for (int i = 0; i < estudiantes.length; i++) {
            estudiantes[i] = new Thread(new Estudiante(i, gestorLibros));
            estudiantes[i].start();
        }
    }
}
