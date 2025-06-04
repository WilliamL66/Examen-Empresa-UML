import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoUtil {

    public static void guardarLineas(String nombreArchivo, List<String> lineas) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (String linea : lineas) {
                pw.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en " + nombreArchivo);
        }
    }

    public static List<String> leerLineas(String nombreArchivo) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
        
        }
        return lineas;
    }
}
