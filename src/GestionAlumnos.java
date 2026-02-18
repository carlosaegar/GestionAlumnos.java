//Gestion de alummos BC3 e III ejercicio 1 HECHO EN CLASE
import java.io.*;
import java.util.*;

public class GestionAlumnos {

    public static void main(String[] args) {
        String archivoEntrada = "alumnos.txt";
        String archivoSalida = "resultado.txt";

Map<String, List<Double>> mapaAlumnos = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoEntrada))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");

                if (partes.length == 2) {
                    String nombre = partes[0];
                    double nota = Double.parseDouble(partes[1]);

                    mapaAlumnos.putIfAbsent(nombre, new ArrayList<>());
                    mapaAlumnos.get(nombre).add(nota);
                }
            }
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }


 //Uso de BufferWritter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {

            List<AlumnoResultado> resultados = new ArrayList<>();
            double sumaTotal = 0;

            for (var entry : mapaAlumnos.entrySet()) {
                double suma = 0;
                for (double n : entry.getValue()) suma += n;
                double media = suma / entry.getValue().size();
                resultados.add(new AlumnoResultado(entry.getKey(), media));
            }

            // Ordenar por nota media (de mayor a menor)
            resultados.sort((a, b) -> Double.compare(b.media, a.media));

            // Escribir en el archivo de salida
            writer.write("--- RESULTADOS ---\n");
            for (AlumnoResultado al : resultados) {
                String estado = al.media >= 5 ? "Aprobado" : "Suspenso";
                writer.write(al.nombre + " - Media: " + String.format("%.2f", al.media) + " (" + estado + ")\n");
            }

            if (!resultados.isEmpty()) {
                writer.write("\nMejor alumno: " + resultados.get(0).nombre);
            }

            System.out.println("Fichero '" + archivoSalida + "' generado con éxito.");

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    // Clase auxiliar para manejar los resultados fácilmente
    static class AlumnoResultado {
        String nombre;
        double media;

        AlumnoResultado(String nombre, double media) {
            this.nombre = nombre;
            this.media = media;
        }
    }



}


