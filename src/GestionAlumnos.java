//Gestion de alummos BC3 e III ejercicio 1
import java.io.*;
import java.util.*;

public class GestionAlumnosPDF {

    public static void main(String[] args) {
        String archivoEntrada = "alumnos.txt";
        String archivoSalida = "resultado.txt";

        try {
            Map<String, List<Double>> notasPorAlumno = leerFichero(archivoEntrada);

            List<Alumno> alumnosConMedia = calcularMedias(notasPorAlumno);

            List<Alumno> aprobadosOrdenados = new ArrayList<>();
            for (Alumno a : alumnosConMedia) {
                if (a.getMedia() >= 5.0) {
                    aprobadosOrdenados.add(a);
                }
            }
            aprobadosOrdenados.sort(Comparator.comparingDouble(Alumno::getMedia).reversed());

            // Encontrar el alumno con mejor nota media usando Iterator/bucle o Collections max
            Alumno mejorAlumno = encontrarMejor(alumnosConMedia);

            // 4. Guarde los resultados en un fichero resultado.txt.
            // 5. Guarde en resultado.txt solo los alumnos aprobados.
            escribirResultados(archivoSalida, alumnosConMedia, aprobadosOrdenados, mejorAlumno);

            System.out.println("Proceso completado. Revisa " + archivoSalida);
        }
    }
}
